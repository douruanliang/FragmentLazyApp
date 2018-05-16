package top.douruanliang.fragmentlazyapp.base.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Field;
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
        return 0;
    }

    @Override
    public int delete(T where) {
        return 0;
    }

    @Override
    public List<T> query(T where) {
        return null;
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        return null;
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
     * @param entity
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
}
