package top.omooo.admin.chartsdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import top.omooo.admin.chartsdemo.R;

/**
 * Created by SSC on 2018/3/4.
 */

public class LoadingText extends View {
    private Paint mPaint = new Paint();
    private Bitmap dstBmp;
    private RectF dstRect;
    private RectF rectF = new RectF();
    private float percent;
    private float mMoveWidth = 50; //移动view宽度

    private Xfermode mXfermode;
    private PorterDuff.Mode mPorterDuffMode = PorterDuff.Mode.SRC_IN;


    public LoadingText(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingText(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mXfermode = new PorterDuffXfermode(mPorterDuffMode);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int resultW = widthSize;
        int resultH = heightSize;

        if (widthMode == MeasureSpec.AT_MOST) {
            resultW = dstBmp.getWidth();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            resultH = dstBmp.getHeight();
        }
        setMeasuredDimension(resultW, resultH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
        int saveCount = canvas.saveLayer(dstRect, mPaint, Canvas.ALL_SAVE_FLAG);

        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
        //绘制目标图
        canvas.drawBitmap(dstBmp, null, dstRect, mPaint);
        // 设置混合模式
        mPaint.setXfermode(mXfermode);

        mPaint.setColor(getResources().getColor(R.color.paintValue));
        mPaint.setStyle(Paint.Style.FILL);
        // 绘制源图
        rectF.set(percent * (getWidth() + getHeight()) - getHeight(), 0, percent * (getWidth() + getHeight()) - getHeight() + mMoveWidth, getHeight());

        canvas.skew(0.5f, 0);
        canvas.drawRect(rectF, mPaint);
        // 清除混合模式
        mPaint.setXfermode(null);
        // 还原画布
        canvas.restoreToCount(saveCount);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dstRect = new RectF(0, 0, getWidth(), getHeight());
    }


    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                percent = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setBitmap(int drawableId) {
        dstBmp = BitmapFactory.decodeResource(getResources(), drawableId);
    }

    public void start() {
        startAnim();
    }
}