package top.douruanliang.fragmentlazyapp.mvp.base;

/**
 * author: dourl
 * created on: 2018/9/13 下午7:26
 * description:
 */
public class TestPresenter extends Contract.Presenter {
    public TestPresenter(Contract.LoginModel model) {
        super(model);
    }

    @Override
    void request() {

    }

    @Override
    Contract.LoginModel createModel() {
        return null;
    }
}
