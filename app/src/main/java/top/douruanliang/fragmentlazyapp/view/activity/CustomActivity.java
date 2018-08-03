package top.douruanliang.fragmentlazyapp.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.base.db.DaoManagerFactory;
import top.douruanliang.fragmentlazyapp.base.db.annotation.IBaseDao;
import top.douruanliang.fragmentlazyapp.domain.User;
import top.douruanliang.fragmentlazyapp.domain.UserDao;
import top.douruanliang.fragmentlazyapp.view.InvalidTextView;
import top.douruanliang.fragmentlazyapp.view.RectView;
import top.douruanliang.fragmentlazyapp.view.fragment.FriendFragment;
import top.douruanliang.fragmentlazyapp.view.fragment.IpInfoFragment;
import top.douruanliang.fragmentlazyapp.view.fragment.MsgFragment;


/**
 * 作者：dourl on 2018/5/5 11:16
 * 邮箱：douruanliang@sina.com
 */
public class CustomActivity extends AppCompatActivity {
    private static final String TAG = "CustomActivity";


    private Button mbtnInsert;

    private Button mbtnUpdate,mbtnQuery;
    IBaseDao<User> userBaseDao;
    private List<Fragment> mFragments;
    private String[] mTabTitles = {"消息", "好友", "动态"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constom_layout);


        //添加按钮
        mbtnInsert = findViewById(R.id.btn_insert);
        //更新按钮
        mbtnUpdate =  findViewById(R.id.btn_update);
        //查询
        mbtnQuery = findViewById(R.id.btn_query);
        mbtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instent = new Intent();
                instent.setClass(CustomActivity.this,Custom2Activity.class);
                startActivity(instent);
            }
        });
        //userBaseDao = DaoManagerFactory.getInstance().getDataHelper(UserDao.class,User.class);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_main);
        initToolBar();
        initFragment();

        MyFragmetPagerAdapter myFragmetPagerAdapter = new MyFragmetPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(myFragmetPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        MsgFragment msgFragment = new MsgFragment();
        IpInfoFragment ipInfoFragment = new IpInfoFragment();
        FriendFragment friendFragment = new FriendFragment();

        mFragments.add(msgFragment);
        mFragments.add(friendFragment);
        mFragments.add(ipInfoFragment);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main);
        // 指定ToolBar的标题
        toolbar.setTitle("douruanliang");
        // 将toolBar和actionBar进行关联
         setSupportActionBar(toolbar);
        
    }


    /*public void  insert(View view){
        User s = new User();
        s.setName("douruanliang");
        s.setPassword("666666");
        Log.i("Tag","-----insert---------");
        userBaseDao.insert(s);

    }


    public void  update(View view){
        User w = new User();
        w.setName("douruanliang");


        User e = new User();
        e.setName("徐帆");
        Log.i("Tag","-----insert---------");
        userBaseDao.update(e,w);
    }

    public void query(View view)
    {
        User where=new User();
        where.setName("徐帆");
        List<User> list=userBaseDao.query(where);
        Log.i(TAG,"共 查询到  "+list.size()+"  条数据");
        for (User user:list)
        {
            Log.i(TAG,user.toString());
        }
    }*/


    class MyFragmetPagerAdapter extends FragmentPagerAdapter{
        private Context mContext;

        public MyFragmetPagerAdapter(FragmentManager fm,Context mContext) {
            super(fm);
            this.mContext =mContext;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }
}
