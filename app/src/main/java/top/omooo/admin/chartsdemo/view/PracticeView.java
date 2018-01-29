package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Omooo on 2018/1/26.
 */

public class PracticeView extends View {
    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private int mCount = 6;
    private float mRadius;

    private void init() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20f);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public PracticeView(Context context) {
        super(context);
        init();
    }

    public PracticeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PracticeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        drawPolygon(canvas);

    }

    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        float r = mRadius/(mCount-1);//r是蜘蛛丝之间的间距
        for(int i=1;i<mCount;i++){//中心点不用绘制
            float curR = r*i;//当前半径
            path.reset();
            for(int j=0;j<mCount;j++){
                if(j==0){
                    path.moveTo(curR,0);
                }else{
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (curR * Math.cos(360 / mCount * j));
                    float y = (float) (curR * Math.sin(360 / mCount * j));
                    path.lineTo(x,y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mPaint);
        }
    }
}
