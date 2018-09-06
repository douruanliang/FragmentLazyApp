package top.douruanliang.fragmentlazyapp.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/**
 * author: dourl
 * created on: 2018/9/5 下午1:47
 * description:
 */
public class CheckedView extends View implements Checkable{

    private boolean mChecked = false;
    public CheckedView(Context context) {
        super(context);
    }

    public CheckedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
         this.mChecked =checked;
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
