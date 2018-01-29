package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.R;
import top.omooo.admin.chartsdemo.bean.RadarMapBean;

/**
 * Created by Omooo on 2018/1/29.
 */

public class RadarMapView extends View {

    private Paint mPaint = new Paint(); //画多边形
    private Paint mPaintData = new Paint(); //画数据区域
    private int mWidth;
    private int mHeight;
    private int mCount; //正多边形的边数
    private float mAngle;   //多边形每一份的角度
    private float mRadius;  //多边形最大半径
    private static final float MAX_LENGTH = 100;  //设置最大值为100
    private static final String TAG = "RadarMapView";
    private boolean isFirstPaint = true;
    private List<RadarMapBean> mRadarMapBeans = new ArrayList<>();

    public void initPaint() {
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30f);
        mPaint.setStrokeWidth(3f);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaintData.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintData.setStrokeWidth(3f);
        mPaintData.setAlpha(127);
        mPaintData.setColor(getResources().getColor(R.color.paintValue));


    }

    public RadarMapView(Context context) {
        super(context);
        initPaint();

    }

    public RadarMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RadarMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w / 2;
        mHeight = h / 2;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth, mHeight);

        Path path = new Path();
        //多边形间距
        float r = mRadius / (mCount - 1);

        //画每一个多边形
        for (int i = 1; i < mCount; i++) {
            //当前多边形的半径
            float curRadius = r * i;
            path.reset();
            for (int j = 0; j < mCount + 1; j++) {
                if (j == 0) {
                    path.moveTo(curRadius, 0);
                } else {
                    float x = (float) (curRadius * Math.cos(mAngle * j));
                    float y = (float) (curRadius * Math.sin(mAngle * j));
                    //画多边形的每一条边
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }

        //画最外层多边形的顶点到中心点的直线以及绘制文字
        path.reset();
        Path pathData = new Path();
        for (int i = 0; i < mCount; i++) {
            String text = mRadarMapBeans.get(0).getNames()[i].toString();
            int value = mRadarMapBeans.get(0).getValues()[i];
            float percentage = value / MAX_LENGTH;
            //最外层多边形的每个顶点的坐标值
            float x = (float) (mRadius * Math.cos(mAngle * i));
            float y = (float) (mRadius * Math.sin(mAngle * i));
            //数据占比的坐标值
            float valueX = x * percentage;
            float valueY = y * percentage;
            Log.i(TAG, value + " " + x + " " + valueX + " " + y + " " + valueY);
            canvas.drawCircle(valueX, valueY, 5, mPaintData);

            //绘制数据区域
            if (isFirstPaint) {
                pathData.moveTo(valueX, valueY);
                isFirstPaint = false;
            }
            pathData.lineTo(valueX, valueY);
            canvas.drawPath(pathData, mPaintData);
            path.lineTo(x, y);
            if (x >= 0) {
                canvas.drawText(text + ": " + value, x + 30, y, mPaint);
            } else {
                canvas.drawText(text + ": " + value, x - 90, y, mPaint);
            }
            canvas.drawPath(path, mPaint);
            path.moveTo(0, 0);
        }
    }

    public void setData(List<RadarMapBean> beans) {
        mRadarMapBeans = beans;
        mCount = beans.get(0).getCount();
        mAngle = (float) (Math.PI * 2 / mCount);
    }

}
