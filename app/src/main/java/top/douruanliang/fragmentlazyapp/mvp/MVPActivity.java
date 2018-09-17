package top.douruanliang.fragmentlazyapp.mvp;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import top.douruanliang.fragmentlazyapp.R;

/**
 * author: dourl
 * created on: 2018/9/7 下午2:15
 * description: 最简单的mvp
 */
public class MVPActivity extends AppCompatActivity implements MVPView {


    private Button button;
    private TextView textView;
    private Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.id_text_view);
        mPresenter = new Presenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mPresenter.request();
                //performAnimate();
                performAnimate(textView, textView.getWidth(), 500);
            }
        });
    }

    @Override
    public void updateTV(String text) {
        textView.setText(text);
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

    //自定义动画一
    private void performAnimate() {
        ViewWrapper mWrapper = new ViewWrapper(textView);
        ObjectAnimator.ofInt(mWrapper, "width", 500).setDuration(5000).start();
    }

    //自定义动画二
    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mIntEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前动画的进度值 1-100 之间
                int currentValue = (int) animation.getAnimatedValue();

                float fraction = currentValue / 100;
                target.getLayoutParams().width = mIntEvaluator.evaluate(fraction, start, end);
                target.requestLayout();

            }
        });
        valueAnimator.setDuration(5000).start();
    }
}
