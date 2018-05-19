package top.douruanliang.fragmentlazyapp.base.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;


/**
 * 作者：dourl on 2018/5/14 21:25
 * 邮箱：douruanliang@sina.com
 */
public class DaoManagerFactory {
    //外置数据库路径
    private String sqliteDatabasePath;
    //真正操作数据库的
    private SQLiteDatabase sqLiteDatabase;


    public static DaoManagerFactory instanse;
        //    new DaoManagerFactory();

    public static  DaoManagerFactory getInstance()
    {
        if(instanse == null){
            // have a  problerm;

            instanse = new DaoManagerFactory();
        }
        return  instanse;
    }
    //私有的构造方法
    private  DaoManagerFactory() {

        if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            this.sqliteDatabasePath = Environment.getExternalStorageDirectory().getAbsolutePath();

        }else{
             //提示
            Log.i("DaoManagerFactory","无SD卡");
            return;

        }
        File file = new File(sqliteDatabasePath,"/teacher.db");
        openDatabase(file);
    }

    /**
     * 打开数据库没有则创建
     */
    private void openDatabase(File file) {

        this.sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase(file,null);
    }

    /**
     *
     * @param clazz   操作数据类（xxxDTO）
     * @param entityClass  实体类 xxx
     * @param <T>
     * @param <M>
     * @return
     */
    public  synchronized  <T extends BaseDao<M>,M> T getDataHelper(Class<T> clazz,Class<M> entityClass)
    {
        BaseDao baseDao=null;
        try {
             //通过反射获取实例对象
            baseDao=clazz.newInstance();
            //初始化表等
            baseDao.init(entityClass,sqLiteDatabase);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) baseDao;
    }
}
