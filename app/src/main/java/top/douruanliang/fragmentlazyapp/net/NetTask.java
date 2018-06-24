package top.douruanliang.fragmentlazyapp.net;


/**
 * 定义获取网络数据接口
 * @param <T>
 */
public interface NetTask <T>{

    void execute(T data,LoadTaskCallBack loadTaskCallBack);
}
