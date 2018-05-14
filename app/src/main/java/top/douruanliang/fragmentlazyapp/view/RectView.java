package top.douruanliang.fragmentlazyapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 作者：dourl on 2018/5/5 11:53
 * 邮箱：douruanliang@sina.com
 */
public class RectView extends View {
    //自定义画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public RectView(Context context) {
        super(context);
        initDraw();
    }
    public RectView(Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
        initDraw();
    }
    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw(){
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth((float)1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //控件的宽/高
        int width = getWidth();
        int height = getHeight();
        canvas.drawRect(0,0,width,height,mPaint);

    }
}
