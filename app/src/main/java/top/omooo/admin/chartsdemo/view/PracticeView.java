package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private Picture mPicture;
    private int mWidth;
    private int mHeight;

    private void init() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20f);
        mPaint.setStrokeWidth(50f);
        mPaint.setStyle(Paint.Style.FILL);
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
//        recording();
//        canvas.drawPicture(mPicture);
        String text = "Omooo";
        canvas.drawText(text, 0, 0, mPaint);
        canvas.drawText(text, 0, 4, 0, 100, mPaint);
    }

    public void recording() {
        mPicture = new Picture();
        //开始录制
        Canvas canvas = mPicture.beginRecording(mWidth, mHeight);
        canvas.drawCircle(0, 0, 100, mPaint);
        //结束录制
        mPicture.endRecording();
    }
}
