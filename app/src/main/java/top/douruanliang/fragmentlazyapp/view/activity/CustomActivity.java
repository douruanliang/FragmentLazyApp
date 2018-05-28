package top.douruanliang.fragmentlazyapp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.base.db.DaoManagerFactory;
import top.douruanliang.fragmentlazyapp.base.db.annotation.IBaseDao;
import top.douruanliang.fragmentlazyapp.domain.User;
import top.douruanliang.fragmentlazyapp.domain.UserDao;
import top.douruanliang.fragmentlazyapp.view.InvalidTextView;
import top.douruanliang.fragmentlazyapp.view.RectView;


/**
 * 作者：dourl on 2018/5/5 11:16
 * 邮箱：douruanliang@sina.com
 */
public class CustomActivity extends Activity {
    private static final String TAG = "CustomActivity";
    private InvalidTextView mInvalidText;
    private RectView mRectView;
    private Button mbtnInsert;

    private Button mbtnUpdate,mbtnQuery;
    IBaseDao<User> userBaseDao;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constom_layout);
        mInvalidText = findViewById(R.id.miv_text);
        mInvalidText.setText("我基本上已经费了");
        mRectView = findViewById(R.id.mView_id);
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
        userBaseDao = DaoManagerFactory.getInstance().getDataHelper(UserDao.class,User.class);



    }


    public void  insert(View view){
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
    }
}
