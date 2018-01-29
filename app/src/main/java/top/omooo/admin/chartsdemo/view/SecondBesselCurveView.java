package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Omooo on 2018/1/29.
 */

/**
 * 二阶贝塞尔曲线
 */
public class SecondBesselCurveView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private PointF startPoint,endPoint, controlPoint;

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }
    public SecondBesselCurveView(Context context) {
        super(context);
        init();
    }

    public SecondBesselCurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SecondBesselCurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w / 2;
        mHeight = h / 2;

        startPoint = new PointF(mWidth-200, mHeight);
        endPoint = new PointF(mWidth + 200, mHeight);
        controlPoint = new PointF(mWidth, mHeight - 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数据点和控制点
        canvas.drawPoint(startPoint.x, startPoint.y, mPaint);
        canvas.drawPoint(endPoint.x, endPoint.y, mPaint);
        canvas.drawPoint(controlPoint.x, controlPoint.y, mPaint);

        //画辅助线
        mPaint.setAlpha(127);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y, mPaint);
        canvas.drawLine(endPoint.x, endPoint.y, controlPoint.x, controlPoint.y, mPaint);

        //画贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //根据触摸点更新控制点
        controlPoint.x = event.getX();
        controlPoint.y = event.getY();
        invalidate();
        return true;
    }

}
