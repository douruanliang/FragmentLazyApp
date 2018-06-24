package top.douruanliang.fragmentlazyapp.net;


/**
 *
 *回调监听接口
 *
 * 定义网络访问回调的各种状态
 * @param <T>
 */
public interface LoadTaskCallBack<T> {

    void onStart();
    void onFailed();

    void onSuccess(T t);

    void onFinish();
}
