package top.douruanliang.fragmentlazyapp.mvp.base;

/**
 * author: dourl
 * created on: 2018/9/13 下午6:55
 * description:
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V view;
    protected M model;

    public BasePresenter(M model) {
        this.model = createModel();
    }

    void attachView(V view) {
        this.view = view;
    }

    void detachView() {
        this.view = null;
    }

    abstract M createModel();
}
