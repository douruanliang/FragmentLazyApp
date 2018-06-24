package top.douruanliang.fragmentlazyapp.net;


/**
 * 主要 给view 绑定 Presenter
 * @param <T>
 */
public interface BaseView <T>{
    void setPresenter(T t);
}
