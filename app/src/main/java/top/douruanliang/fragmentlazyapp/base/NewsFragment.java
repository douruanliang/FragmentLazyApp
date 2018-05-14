package top.douruanliang.fragmentlazyapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：dourl on 2018/5/3 20:32
 * 邮箱：douruanliang@sina.com
 */
public class NewsFragment extends BaseLazyFragment {
    private boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible){
            return;
        }
    }
}
