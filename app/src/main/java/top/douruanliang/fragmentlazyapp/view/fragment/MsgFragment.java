package top.douruanliang.fragmentlazyapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.view.InvalidTextView;

/**
 * author: dourl
 * created on: 2018/8/3 下午5:58
 * description:
 */
public class MsgFragment extends Fragment {
    private InvalidTextView mInvalidText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_msg,container,false);

        mInvalidText = view.findViewById(R.id.miv_text);
        mInvalidText.setText("我基本上已经费了");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
