package top.douruanliang.fragmentlazyapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * 作者：dourl on 2018/5/5 10:48
 * 邮箱：douruanliang@sina.com
 */
public class InvalidTextView extends AppCompatTextView {

    //自定义画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public InvalidTextView(Context context) {
        super(context);
        initDraw();
    }
    public InvalidTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }
    public InvalidTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }
    //设置画笔相关属性
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
        canvas.drawLine(0,height/2,width,height/2,mPaint);
    }
}
