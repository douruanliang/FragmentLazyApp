package top.douruanliang.fragmentlazyapp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private InvalidTextView mInvalidText;
    private RectView mRectView;
    private Button mbtnInsert;

    IBaseDao<User> userBaseDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constom_layout);
        mInvalidText = findViewById(R.id.miv_text);
        mInvalidText.setText("我基本上已经费了");
        mRectView = findViewById(R.id.mView_id);

        mbtnInsert = findViewById(R.id.btn_insert);
        userBaseDao = DaoManagerFactory.getInstance().getDataHelper(UserDao.class,User.class);

    }

    public void  insert(View view){
        User s = new User("dou","123456");


        Log.i("Tag","-----insert---------");
        userBaseDao.insert(s);


    }
}
