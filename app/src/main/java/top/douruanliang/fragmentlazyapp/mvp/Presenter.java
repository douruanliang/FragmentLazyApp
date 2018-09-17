package top.douruanliang.fragmentlazyapp.mvp;

/**
 * author: dourl
 * created on: 2018/9/7 下午2:29
 * description:
 */
public class Presenter {
    //同时持有 View /model
    private MVPView view ;
    private HttpModel model;

    public Presenter(MVPView view) {
        this.view = view;
        //目的是 示例对象 并通过接口回调 view显示
        model = new HttpModel(new Callback() {
            @Override
            public void onResult(String text) {
                Presenter.this.view.updateTV(text);
            }
        });
    }
    //请求数据
    public void request(){
        model.request();
    }
}
