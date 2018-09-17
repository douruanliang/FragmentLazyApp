package top.douruanliang.fragmentlazyapp.ac;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * author: dourl
 * created on: 2018/9/12 下午3:07
 * description:
 */
@Entity
public class User {
    @PrimaryKey
    private int id;
    private String name;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
