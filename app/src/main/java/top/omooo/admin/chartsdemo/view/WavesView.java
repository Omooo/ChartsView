package top.omooo.admin.chartsdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Omooo on 2018/1/30.
 */

public class WavesView extends View implements View.OnClickListener{
    private int mWaveLength;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mCenterY;
    private  int mWaveCount;
    private int offset;

    private Path mPath;
    private Paint mPaint;


    private ValueAnimator mValueAnimatior;
    public WavesView(Context context) {
        super(context);
    }

    public WavesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.LTGRAY);
        mWaveLength = 800;

    }

    public WavesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 需要计算出屏幕能容纳多少个波形
        mPath = new Path();
        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h / 2;
        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5); // 计算波形的个数
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLength,mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offset,mCenterY + 60,-mWaveLength / 2 + i * mWaveLength + offset,mCenterY);
            mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + offset,mCenterY - 60,i * mWaveLength + offset,mCenterY);
        }
        mPath.lineTo(mScreenWidth,mScreenHeight);
        mPath.lineTo(0,mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath,mPaint);

    }


    @Override
    public void onClick(View v) {
        mValueAnimatior = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimatior.setDuration(1000);
        mValueAnimatior.setInterpolator(new LinearInterpolator());
        mValueAnimatior.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimatior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimatior.start();

    }
}
