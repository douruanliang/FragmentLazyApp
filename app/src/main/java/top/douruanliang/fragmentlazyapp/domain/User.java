package top.douruanliang.fragmentlazyapp.domain;

import top.douruanliang.fragmentlazyapp.base.db.annotation.DbFiled;
import top.douruanliang.fragmentlazyapp.base.db.annotation.DbTable;

/**
 * 作者：dourl on 2018/5/14 21:20
 * 邮箱：douruanliang@sina.com
 */
@DbTable("tb_user")
public class User {

    @DbFiled("name")
    public String name;
    @DbFiled("password")
    public String password;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
