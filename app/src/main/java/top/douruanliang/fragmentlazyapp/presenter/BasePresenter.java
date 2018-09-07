package top.douruanliang.fragmentlazyapp.presenter;

import top.douruanliang.fragmentlazyapp.model.BaseModel;
import top.douruanliang.fragmentlazyapp.view.BaseView;

/**
 * author: dourl
 * created on: 2018/9/7 下午2:00
 * description:
 */
public  abstract class BasePresenter<V extends BaseView,M extends BaseModel>  {

    protected V view;
    protected M model;

    public BasePresenter() {
        model = createModel();
    }

    void attachView(V view){
        this.view = view;
    }

    void detachView(V view){
        this.view = null;
    }
    protected abstract M createModel();
}
