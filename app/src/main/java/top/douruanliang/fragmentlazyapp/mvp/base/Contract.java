package top.douruanliang.fragmentlazyapp.mvp.base;

/**
 * author: dourl
 * created on: 2018/9/13 下午7:08
 * description:
 */
public interface Contract {

    interface LoginModel extends BaseModel {
        void getData(Callback callback);
    }

    interface View extends BaseView {
        void updateUi();
    }

    abstract class Presenter extends BasePresenter<View, LoginModel> {

        public Presenter(LoginModel model) {
            super(model);
        }

        abstract void request();

        void requests(){
            model.getData(new Callback() {
                @Override
                public void onResult(String text) {
                    view.updateUi();
                }
            });
        }
    }

    class Presenters extends BasePresenter<View,LoginModel>{

        public Presenters(LoginModel model) {
            super(model);
        }

        @Override
        LoginModel createModel() {
            return null;
        }
    }
}
