package top.douruanliang.fragmentlazyapp.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: dourl
 * created on: 2018/9/17 上午10:16
 * description:
 */
public class TimerTextView extends AppCompatTextView {
    private long mTime = 0;
    private boolean run = true; // 是否启动了
    private OnCountDownCallBack mOnCountDownCallBack;

    public TimerTextView(Context context) {
        super(context);
    }

    public TimerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (run) {
                        long time = mTime;
                        TimerTextView.this.setVisibility(VISIBLE);
                        if (time > 0) {
                            int remainTime = (int) (mTime / 1000L);
                            TimerTextView.this.setText("倒计时" + formatTime(remainTime));
                            mTime = mTime - 1000;
                            mHandler.sendEmptyMessageDelayed(0, 1000);
                        } else {
                            stop();
                            TimerTextView.this.setVisibility(GONE);
                        }
                    } else {
                        TimerTextView.this.setVisibility(GONE);

                    }
                    break;
            }

        }
    };

    /**
     * 设置倒计时(指定时间比如：倒计时 5分钟)
     * @param time
     */
    public void setTime(long time) {
        mTime = time;
        if (mTime > 0) {
            mHandler.removeMessages(0);
            mHandler.sendEmptyMessage(0);
            TimerTextView.this.setVisibility(VISIBLE);
        } else {
            TimerTextView.this.setVisibility(View.GONE);
        }

    }

    /**
     * 设置倒计时(自定义起始、结束时间：)
     * @param startTime
     * @param endTime
     */
    public void setTime(long startTime, long endTime) {
        mTime = endTime - startTime;
        if (mTime > 0) {
            mHandler.removeMessages(0);
            mHandler.sendEmptyMessage(0);
            TimerTextView.this.setVisibility(VISIBLE);
        } else {
            TimerTextView.this.setVisibility(View.GONE);
        }

    }

    //停止
    public void stop() {
        run = false;
        mOnCountDownCallBack.onFinish();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /**
     * 自定义时间格式
     *
     * @param time
     * @return
     */
    public static String formatTime(int time) {
        String hh = time / 3600 > 9 ? time / 3600 + "" : "0" + time / 3600;
        String mm = (time % 3600) / 60 > 9 ? (time % 3600) / 60 + "" : "0" + (time % 3600) / 60;
        String ss = (time % 3600) % 60 > 9 ? (time % 3600) % 60 + "" : "0" + (time % 3600) % 60;
        return hh + ":" + mm + ":" + ss;
    }

    public void setOnCountDownCallBack(OnCountDownCallBack onCountDownCallBack) {
        mOnCountDownCallBack = onCountDownCallBack;
    }

    /**
     * 结束的回调方法
     */
    public interface OnCountDownCallBack {
        void onFinish();
    }
}
