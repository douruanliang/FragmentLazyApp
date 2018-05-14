package top.douruanliang.fragmentlazyapp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.view.InvalidTextView;
import top.douruanliang.fragmentlazyapp.view.RectView;


/**
 * 作者：dourl on 2018/5/5 11:16
 * 邮箱：douruanliang@sina.com
 */
public class CustomActivity extends Activity {
    private InvalidTextView mInvalidText;
    private RectView mRectView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constom_layout);
        mInvalidText = findViewById(R.id.miv_text);
        mInvalidText.setText("我基本上已经费了");
        mRectView = findViewById(R.id.mView_id);

    }
}
