package top.douruanliang.fragmentlazyapp.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * author: dourl
 * created on: 2018/8/1 下午2:26
 * description:
 */
public class KTrendVIew extends LinearLayout {
    private Context mContext;
    private String id;

    public KTrendVIew(Context context) {
        super(context);
        init(context);
    }

    public KTrendVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KTrendVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

    }
}
