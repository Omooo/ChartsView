package top.omooo.admin.chartsdemo.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class DefaultIndicator extends BaseIndicator {
    private Paint mPaint;

    public DefaultIndicator(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setState(false);
    }

    @Override
    public void setState(boolean isChecked) {
        if (isChecked) {
            mPaint.setColor(0xffffffff);
        } else {
            mPaint.setColor(0x88ffffff);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getMeasuredWidth() * 0.5f, getMeasuredHeight() * 0.5f);
        canvas.drawCircle(0, 0, getMeasuredWidth() * 0.5f, mPaint);
    }
}
