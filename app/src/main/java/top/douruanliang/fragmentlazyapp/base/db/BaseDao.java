package top.douruanliang.fragmentlazyapp.base.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import top.douruanliang.fragmentlazyapp.base.db.annotation.DbFiled;
import top.douruanliang.fragmentlazyapp.base.db.annotation.DbTable;
import top.douruanliang.fragmentlazyapp.base.db.annotation.IBaseDao;

/**
 * 作者：dourl on 2018/5/14 20:54
 * 邮箱：douruanliang@sina.com
 */
public abstract class BaseDao <T> implements IBaseDao<T> {

    /**]
     * 持有数据库操作类的引用
     */
    private SQLiteDatabase database;
    /**
     * 保证实例化一次
     */
    private boolean isInit=false;

    /**
     * 持有操作数据库表所对应的java类型
     * User
     */
    private Class<T> entityClass;
    /**
     * 维护这表名与成员变量名的映射关系
     * key---》表名
     * value --》Field
     * class  methoFiled
     * {
     *     Method  setMthod
     *     Filed  fild
     * }
     */
    private Map<String,Field> cacheFieldMap;

    /**
     * 表名
     */
    private String tableName;
    public BaseDao() {
        super();
    }

    @Override
    public Long insert(T entity) {
        Map<String,String> map=getValues(entity);
        ContentValues contentValues=getContentValues(map);
        Long result=database.insert(tableName,null,contentValues);
        return result;
    }

    @Override
    public int update(T entity, T where) {
        int result=-1;
        Map values=getValues(entity);
        Condition condition = new Condition(getValues(where));
        result =database.update(tableName,getContentValues(values),condition.whereClause,condition.WhereArgs);
        return result;
    }

    /**
     * 删除
     * @param where
     * @return
     */
    @Override
    public int delete(T where) {
        Map values=getValues(where);

        Condition condition=new Condition(values);

        int result=database.delete(tableName,condition.getWhereClause(),condition.getWhereArgs());

        return result;
    }

    @Override
    public List<T> query(T where) {
        return query(where,null,null,null);
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        Map values=getValues(where);
        String limitString=null;
        if(startIndex!=null&&limit!=null)
        {
            limitString=startIndex+" , "+limit;
        }

        Condition condition=new Condition(values);

        Cursor cursor=null;

        cursor=database.query(tableName,null,condition.getWhereClause(),condition.getWhereArgs(),null,null,orderBy,limitString);

        List<T> result=getReslut(cursor,where);
        cursor.close();
        return result;
    }

    private List<T> getReslut(Cursor cursor, T where) {
        ArrayList list=new ArrayList();
        Object item;
        /*  遍历游标所持有的数据
            每次遍历一条数据 生成一个对象
            并且添加值 list集合
         */
        while (cursor.moveToNext())
        {
            Class<?> clazz=where.getClass();
            try {
                item=clazz.newInstance();
                Iterator iterator=cacheFieldMap.entrySet().iterator();
                while (iterator.hasNext())
                {   //  "userName" -> User.userName(Field)；
                    Map.Entry<String,Field> entry= (Map.Entry<String, Field>) iterator.next();
                    String columnName=entry.getKey();
                    Field field=entry.getValue();

                    Integer colmunIndex=cursor.getColumnIndex(columnName);
                     //对象类型
                    Class type=field.getType();

                    if(colmunIndex!=-1)
                    {
                        if(type==String.class)
                        {
                            field.set(item,cursor.getString(colmunIndex));
                        }else if(type==Integer.class)
                        {
                            field.set(item,cursor.getInt(colmunIndex));
                        }else  if(type==Long.class)
                        {
                            field.set(item,cursor.getLong(colmunIndex));
                        }else if(type==Double.class)
                        {
                            field.set(item,cursor.getDouble(colmunIndex));
                        }else if(type==byte[].class)
                        {
                            field.set(item,cursor.getBlob(colmunIndex));
                        }else
                        {
                            continue;
                        }
                    }


                }
                list.add(item);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        return list;
    }

    @Override
    public List<T> query(String sql) {
        return null;
    }


    private ContentValues getContentValues(Map<String,String> map) {
        ContentValues contentValues=new ContentValues();
        Set keys=map.keySet();
        Iterator iterator=keys.iterator();
        while (iterator.hasNext())
        {
            String key= (String) iterator.next();
            String value=map.get(key);
            if(value!=null)
            {
                contentValues.put(key,value);
            }
        }
        return contentValues;
    }
    private Map<String,String> getValues(T entity) {
        HashMap<String,String> result=new HashMap<>();
        Iterator fieldsIterator=cacheFieldMap.values().iterator();
        while (fieldsIterator.hasNext())
        {
            Field colmunToFiled= (Field) fieldsIterator.next();
            String  cacheKey=null;
            String cacheVuale=null;
            if(colmunToFiled.getAnnotation(DbFiled.class) != null)
            {
                cacheKey=colmunToFiled.getAnnotation(DbFiled.class).value();
            }else
            {
                cacheKey=colmunToFiled.getName();
            }
            try {
                if(null==colmunToFiled.get(entity))
                {
                    continue;
                }
                cacheVuale=colmunToFiled.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            result.put(cacheKey,cacheVuale);
        }
        return result;
    }

    /**
     * 不暴露 模块耦合
     * @param entity  将要操作的DAO
     * @param sqLiteDatabase
     * @return
     */
    protected synchronized boolean init(Class<T> entity,SQLiteDatabase sqLiteDatabase){
        if(!isInit)
        {
            entityClass=entity;
            //持有引用
            database=sqLiteDatabase;
            if (entity.getAnnotation(DbTable.class)==null)
            {
                tableName=entity.getClass().getSimpleName();
            }else
            {
                tableName=entity.getAnnotation(DbTable.class).value();
            }
            if(!database.isOpen())
            {
                return  false;
            }
           if(!TextUtils.isEmpty(createTable()))
            {
                database.execSQL(createTable());
            }
            cacheFieldMap=new HashMap<>();
            initCacheMap();

            isInit=true;
        }
        return  isInit;
    }

    private void initCacheMap() {
        String sql="select * from "+this.tableName+" limit 1,0";
        Cursor cursor=null;
        try {
            cursor=this.database.rawQuery(sql,null);
            String[] columnNames=cursor.getColumnNames();
            Field []  columnFields=entityClass.getFields();
            for (Field field:columnFields)
            {
                field.setAccessible(true);
            }

            for (String columnName:columnNames) {
                Field colmunToFiled = null;
                //开始找对应关系
                for (Field field : columnFields) {
                    String fieldName = null;
                    if (field.getAnnotation(DbTable.class) != null) {
                        fieldName = field.getAnnotation(DbFiled.class).value();
                    } else {
                        fieldName = field.getName();
                    }

                    if (columnName.equals(fieldName)) {
                        colmunToFiled=field;
                        break;
                    }
                }

                if (colmunToFiled != null) {
                    cacheFieldMap.put(columnName, colmunToFiled);
                }
            }
        }catch (Exception e)
        {

        }finally {
            cursor.close();
        }
    }
    /**
     * 抽象方法 供每个集体的Dao去实现
     * @return
     */
    public  abstract String createTable();

    /**
     * 内部条件类
     */
    class Condition
    {
        private String whereClause;
        private String[] WhereArgs;
        public  Condition(Map<String,String> map)
        {
            ArrayList list=new ArrayList();
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(" 1=1 ");
            Set keys=map.keySet();
            Iterator iterator=keys.iterator();
            while (iterator.hasNext())
            {
                String key= (String) iterator.next();
                String value=map.get(key);
                if(value!=null)
                {
                    stringBuilder.append(" and "+key+" =? ");
                    list.add(value);
                }
            }
            this.whereClause=stringBuilder.toString();
            this.WhereArgs= (String[]) list.toArray(new String[list.size()]);
        }

        public String getWhereClause() {
            return whereClause;
        }



        public String[] getWhereArgs() {
            return WhereArgs;
        }
    }
}
