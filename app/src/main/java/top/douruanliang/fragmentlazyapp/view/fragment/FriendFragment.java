package top.douruanliang.fragmentlazyapp.view.fragment;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.TextView;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.model.NameViewModle;
import top.douruanliang.fragmentlazyapp.view.RectView;
import top.douruanliang.fragmentlazyapp.view.activity.Custom2Activity;

/**
 * author: dourl
 * created on: 2018/8/3 下午6:00
 * description:
 */
public class FriendFragment extends Fragment {
    private static final String TAG = "FriendFragment";
    private RectView mRectView;
    Button mbtnQuery;

    private View v1,v2;

    private TextView nameLiveDataview;
    private NameViewModle mNameViewModle;
    //修改 实例方式
    public static  FriendFragment getInstance(){
        return new FriendFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNameViewModle = ViewModelProviders.of(this).get(NameViewModle.class);
        //添加观察者 当数据变化时会 通过回调方法 通知观察者
        mNameViewModle.getCurrentName().observe(this,(String name) ->{
            nameLiveDataview.setText(name);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_friend,container,false);
        mRectView = view.findViewById(R.id.mView_id);
        //查询
        mbtnQuery = view.findViewById(R.id.btn_query);
        mbtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.setClass(getActivity(),Custom2Activity.class);
                //startActivity(intent);
                //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                mNameViewModle.getCurrentName().setValue("doauruanliang");
            }
        });


         v1 = view.findViewById(R.id.tv_rect);
         v2 = view.findViewById(R.id.tv_circle);
        nameLiveDataview = view.findViewById(R.id.tv_current_name);

       return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //获取Outline
        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        };
        //获取Outline
        ViewOutlineProvider viewOutlineProvider2 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };
        //重新设置形状
        v1.setOutlineProvider(viewOutlineProvider1);
        v2.setOutlineProvider(viewOutlineProvider2);
    }
}
