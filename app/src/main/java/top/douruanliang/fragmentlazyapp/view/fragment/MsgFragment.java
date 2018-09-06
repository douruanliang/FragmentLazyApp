package top.douruanliang.fragmentlazyapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.view.InvalidTextView;

/**
 * author: dourl
 * created on: 2018/8/3 下午5:58
 * description:
 */
public class MsgFragment extends Fragment {
    private InvalidTextView mInvalidText;

    private SimpleDraweeView mSimpleDraweeView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_msg,container,false);

        mInvalidText = view.findViewById(R.id.miv_text);
        mInvalidText.setText("我基本上在中间");

        mSimpleDraweeView =view.findViewById(R.id.sdv_2);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String url = "http://ww3.sinaimg.cn/large/610dc034jw1f6m4aj83g9j20zk1hcww3.jpg";

        String url2 = "https://timgsa.baidu.com/" +
                "timg?image&quality=80&size=b9999_10000&sec=1534401205145&di" +
                "=84131c3102da4804d60e14fcaaca50be&imgtype=0" +
                "&src=http%3A%2F%2Fpic35.nipic.com%2F20131119%2F3101644_075445358000_s2.png";
        //mSimpleDraweeView.setImageURI(url2);
    }
}
