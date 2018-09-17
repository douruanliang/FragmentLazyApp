package top.douruanliang.fragmentlazyapp.ac;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * author: dourl
 * created on: 2018/9/12 下午4:33
 * description:创建RoomDatabase:
 */
@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao mUserDao();
}
