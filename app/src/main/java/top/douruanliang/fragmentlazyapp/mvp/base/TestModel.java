package top.douruanliang.fragmentlazyapp.mvp.base;

/**
 * author: dourl
 * created on: 2018/9/13 下午7:22
 * description:
 */
public class TestModel implements Contract.LoginModel {
    @Override
    public void getData(Callback callback) {
        callback.onResult("我的新数据");
    }
}
