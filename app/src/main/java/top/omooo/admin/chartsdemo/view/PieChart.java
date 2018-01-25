package top.omooo.admin.chartsdemo.view;

/**
 * Created by Omooo on 2018/1/24.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.bean.PieBean;

/**
 * 饼状图
 */
public class PieChart extends View {

    private Paint mPaint=new Paint();
    private int[] colors = new int[]{Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GRAY};
    private int mWidth, mHeight;
    private List<PieBean> mPieBeans=new ArrayList<>();
    private float mStartAngle = 0;

    private void init() {
        mPaint.setStyle(Paint.Style.FILL);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);

        mPieBeans.add(new PieBean("A", 100));
    }
    public PieChart(Context context) {
        super(context);
        init();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
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
        canvas.drawColor(Color.WHITE);

        if (null == mPieBeans) {
            return;
        }
        //将坐标原点移到中心点
        canvas.translate(mWidth / 2, mHeight / 2);
        //半价
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);
        float currentAngle = mStartAngle;
        for (int i = 0; i < mPieBeans.size(); i++) {
            mPaint.setColor(mPieBeans.get(i).getColor());
            //画扇形
            canvas.drawArc(rectF, currentAngle, mPieBeans.get(i).getAngle(), true, mPaint);
            //画短线先确定点的坐标
            double posAngle = mStartAngle + (mPieBeans.get(i).getAngle()) / 2;
            if (posAngle >= 0 && posAngle <= 180) {
                canvas.drawLine(r * Float.parseFloat(String.valueOf(Math.cos(posAngle))),
                        r * Float.parseFloat(String.valueOf(Math.sin(posAngle))),
                        r * Float.parseFloat(String.valueOf(Math.cos(posAngle))),
                        r * Float.parseFloat(String.valueOf(Math.sin(posAngle))) + 100, mPaint);
            } else {
                canvas.drawLine(r * Float.parseFloat(String.valueOf(Math.cos(posAngle))),
                        r * Float.parseFloat(String.valueOf(Math.sin(posAngle))),
                        r * Float.parseFloat(String.valueOf(Math.cos(posAngle)))+100,
                        r * Float.parseFloat(String.valueOf(Math.sin(posAngle))), mPaint);
            }
            currentAngle = currentAngle + mPieBeans.get(i).getAngle();
        }
    }

    public void setData(List<PieBean> pieBeans) {
        mPieBeans = pieBeans;
        if (null == pieBeans || pieBeans.size() == 0) {
            return;
        }
        float sum = 0;
        for (int i = 0; i < pieBeans.size(); i++) {
            PieBean bean = pieBeans.get(i);
            sum += bean.getData();
            bean.setColor(colors[i]);
        }
        for (int i = 0; i < pieBeans.size(); i++) {
            PieBean bean = pieBeans.get(i);
            float angle = (bean.getData() / sum) * 360;
            bean.setAngle(angle);
        }
        //刷新
        invalidate();
    }

}
