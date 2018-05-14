package top.douruanliang.fragmentlazyapp.base.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import top.douruanliang.fragmentlazyapp.base.db.annotation.BaseDao;

/**
 * 作者：dourl on 2018/5/14 21:25
 * 邮箱：douruanliang@sina.com
 */
public class BaseDaoFactory {
    //外置数据库路径
    private String sqliteDatabasePath;
    //真正操作数据库的
    private SQLiteDatabase sqLiteDatabase;

    public BaseDaoFactory(){
        sqliteDatabasePath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/teacher.db";
        openDatabase();

    }

    /**
     * 打开数据库没有则创建
     */
    private void openDatabase() {
        this.sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
    }

    public  synchronized  <T extends BaseDao<M>,M> T getDataHelper(Class<T> clazz,Class<M> entityClass)
    {
        BaseDao baseDao=null;
        try {
             //通过反射获取实例对象
            baseDao=clazz.newInstance();
           // baseDao.init(entityClass,sqLiteDatabase);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) baseDao;
    }
}
