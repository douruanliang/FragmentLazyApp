package top.douruanliang.fragmentlazyapp.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;
import java.util.HashMap;

import top.douruanliang.fragmentlazyapp.MainActivity;
import top.douruanliang.fragmentlazyapp.R;
import top.douruanliang.fragmentlazyapp.base.MyAdapter;
import top.douruanliang.fragmentlazyapp.base.db.DividerItemDecoration;

public class Custom2Activity extends AppCompatActivity {
    private RecyclerView Rv;
    private ArrayList<HashMap<String,Object>> listItem;
    private MyAdapter myAdapter;
    private static final int NOTIFICATION_ID_HEADSUP = 0x01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_custom2);
        getWindow().setExitTransition(new Fade());
        CollapsingToolbarLayout collapsingToolbarLayout  = findViewById(R.id.ctl_layout);
        collapsingToolbarLayout.setTitle("加班");
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.YELLOW);


        initData();
        initView();
        initToolBar();

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Headsup Notification")
                .setContentText("I am a Headsup notification.");

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentText("Heads-Up Notification on Android 5.0")
                .setFullScreenIntent(pendingIntent, true);

        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_ID_HEADSUP, builder.build());


    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main);
        // 指定ToolBar的标题
        // 将toolBar和actionBar进行关联
        setSupportActionBar(toolbar);

        //想要的效果
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
           actionBar.setHomeButtonEnabled(true);




    }
    // 绑定数据到RecyclerView
    public void initView(){

        Rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        //用自定义分割线类设置分割线
        Rv.addItemDecoration(new DividerItemDecoration(this));

        //为ListView绑定适配器
        myAdapter = new MyAdapter(this,listItem);
        Rv.setAdapter(myAdapter);
    }
    // 初始化显示的数据
    public void initData(){
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        HashMap<String, Object> map6 = new HashMap<String, Object>();

        HashMap<String, Object> map7 = new HashMap<String, Object>();
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        HashMap<String, Object> map10 = new HashMap<String, Object>();
        HashMap<String, Object> map11 = new HashMap<String, Object>();
        HashMap<String, Object> map12 = new HashMap<String, Object>();

        map1.put("ItemTitle", "美国谷歌公司已发出");
        map1.put("ItemText", "发件人:谷歌 CEO Sundar Pichai");
        listItem.add(map1);

        map2.put("ItemTitle", "国际顺丰已收入");
        map2.put("ItemText", "等待中转");
        listItem.add(map2);

        map3.put("ItemTitle", "国际顺丰转件中");
        map3.put("ItemText", "下一站中国");
        listItem.add(map3);

        map4.put("ItemTitle", "中国顺丰已收入");
        map4.put("ItemText", "下一站广州华南理工大学");
        listItem.add(map4);

        map5.put("ItemTitle", "中国顺丰派件中");
        map5.put("ItemText", "等待派件");
        listItem.add(map5);

        map6.put("ItemTitle", "华南理工大学已签收");
        map6.put("ItemText", "收件人:Carson");
        listItem.add(map6);


        map7.put("ItemTitle", "美国谷歌公司已发出");
        map7.put("ItemText", "发件人:谷歌 CEO Sundar Pichai");
        listItem.add(map7);

        map8.put("ItemTitle", "国际顺丰已收入");
        map8.put("ItemText", "等待中转");
        listItem.add(map8);

        map9.put("ItemTitle", "国际顺丰转件中");
        map9.put("ItemText", "下一站中国");
        listItem.add(map9);

        map10.put("ItemTitle", "中国顺丰已收入");
        map10.put("ItemText", "下一站广州华南理工大学");
        listItem.add(map10);

        map11.put("ItemTitle", "中国顺丰派件中");
        map11.put("ItemText", "等待派件");
        listItem.add(map12);

        map12.put("ItemTitle", "华南理工大学已签收");
        map12.put("ItemText", "收件人:Carson");
        listItem.add(map12);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
