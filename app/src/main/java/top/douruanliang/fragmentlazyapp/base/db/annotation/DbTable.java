package top.douruanliang.fragmentlazyapp.base.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：dourl on 2018/5/14 21:12
 * 邮箱：douruanliang@sina.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTable {
    //注解在类上 ，表名
    String value();
}
