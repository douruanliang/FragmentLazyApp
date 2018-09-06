package top.douruanliang.fragmentlazyapp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

public class MvpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
        Fresco.initialize(this);
    }
}
