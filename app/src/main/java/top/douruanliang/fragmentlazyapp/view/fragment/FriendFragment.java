package top.douruanliang.fragmentlazyapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.view.RectView;

/**
 * author: dourl
 * created on: 2018/8/3 下午6:00
 * description:
 */
public class FriendFragment extends Fragment {
    private RectView mRectView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_friend,container,false);
        mRectView = view.findViewById(R.id.mView_id);
       return  view;
    }


}
