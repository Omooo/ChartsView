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
    private Path mPath = new Path();
    private int mWidth;
    private int mHeight;

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);


    }


}
