package top.douruanliang.fragmentlazyapp.base;


import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * 作者：dourl on 2018/5/3 20:23
 * 邮箱：douruanliang@sina.com
 */
public abstract class BaseLazyFragment extends Fragment{
    private static final String TAG = BaseLazyFragment.class.getName();
    protected boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            this.isVisible = true;
            onVisible();
        } else {
            this.isVisible = false;
            onInvisible();
        }
    }
    /**
     * when the  fragment is not visible to user
     */
    protected void onInvisible() {
        Log.e(TAG,"do  onInvisible");
    }

    protected void onVisible() {
        Log.e(TAG,"do  onVisible");
        lazyLoad();
    }

    protected abstract void lazyLoad();

}

