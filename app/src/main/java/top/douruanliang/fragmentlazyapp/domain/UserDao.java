package top.douruanliang.fragmentlazyapp.domain;

import top.douruanliang.fragmentlazyapp.base.db.BaseDao;


/**
 * 作者：dourl on 2018/5/16 21:01
 * 邮箱：douruanliang@sina.com
 */
public class UserDao extends BaseDao {

    @Override
    public String createTable() {
        return "create table if not exists tb_user(name varchar(20),password varchar(10),age int)";
    }
}
