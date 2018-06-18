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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import top.omooo.admin.chartsdemo.R;

/**
 * Created by SSC on 2018/3/4.
 */

public class LoadingText1 extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mRectF;
    private RectF rectF = new RectF();
    private float percent;
    private float mMoveWidth = 50;  //移动View的宽度

    private Xfermode mXfermode;
    private PorterDuff.Mode mMode;

    private void init(){
        mPaint = new Paint();
        mRectF = new RectF();
        //在源图像和目标图像的重合区域显示源图像
        mMode = PorterDuff.Mode.SRC_IN;
        mXfermode = new PorterDuffXfermode(mMode);
    }

    public LoadingText1(Context context) {
        super(context);
        init();
    }

    public LoadingText1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingText1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int resultW = widthSize;
        int resultH = heightSize;

        if (widthMode == MeasureSpec.AT_MOST) {
            resultW = mBitmap.getWidth();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            resultH = mBitmap.getHeight();
        }
        setMeasuredDimension(resultW, resultH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将绘制操作保存到新的图层，将图像合成的处理放到离屏缓存中进行
        int saveCount = canvas.saveLayer(mRectF, mPaint, Canvas.ALL_SAVE_FLAG);

        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);

        //绘制目标图像
        canvas.drawBitmap(mBitmap, null, mRectF, mPaint);
        //设置混合模式
        mPaint.setXfermode(mXfermode);

        mPaint.setColor(getResources().getColor(R.color.paintValue));
        mPaint.setStyle(Paint.Style.FILL);

        //绘制原图
        rectF.set(percent * (getWidth() + getHeight()) - getHeight(), 0, percent * (getWidth() + getHeight()) - getHeight() + mMoveWidth, getHeight());
        canvas.skew(0.5f, 0);
        canvas.drawRect(rectF, mPaint);
        //清除混合模式
        mPaint.setXfermode(null);
        //还原画布
        canvas.restoreToCount(saveCount);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(0, 0, getWidth(), getHeight());
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
        mBitmap = BitmapFactory.decodeResource(getResources(), drawableId);
    }

    public void start() {
        startAnim();
    }
}
