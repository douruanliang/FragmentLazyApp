package top.douruanliang.fragmentlazyapp.mvp;

import android.os.Handler;
import android.os.Message;

import java.util.logging.LogRecord;

/**
 * author: dourl
 * created on: 2018/9/7 下午2:22
 * description:
 */
public class HttpModel {

    private Callback mCallback;

    public HttpModel(Callback callback){
        this.mCallback = callback;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCallback.onResult((String) msg.obj);
        }
    };


    public void request(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = mHandler.obtainMessage();
                    msg.obj = "从网络获取到的数据";
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
