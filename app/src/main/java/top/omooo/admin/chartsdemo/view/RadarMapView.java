package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.bean.RadarMapBean;

/**
 * Created by Omooo on 2018/1/28.
 */

public class RadarMapView extends View {

    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private int mCount;
    private float mRadius;
    private List<RadarMapBean> mRadarMapBeans = new ArrayList<>();

    public void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        mPaint.setTextSize(20);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
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
        mWidth = w;
        mHeight = h;
        mRadius = Math.min(mWidth, mHeight) / 2 * 0.8f;
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(mWidth, mCount);

//        Path path = new Path();
//        float r = mRadius / (mCount - 1);
//        for (int i = 0; i < mCount; i++) {
//            float curRadius = r * i;
//            path.reset();
//            //画每一层的线
//            for (int j = 0; j < mCount; j++) {
//                if (j == 0) {
//                    path.moveTo(curRadius, 0);
//                } else {
//                    float x = (float) (mRadius * Math.cos((j + 1) * 360 / mCount));
//                    float y = (float) (mRadius * Math.sin((j + 1) * 360 / mCount));
//                    path.lineTo(x, y);
//                }
//            }
//            path.close();
//            canvas.drawPath(path, mPaint);
//        }
        drawPolygon(canvas);
    }

    public void setData(List<RadarMapBean> beans) {
        mRadarMapBeans = beans;
        mCount = beans.get(0).getCount();
    }
    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        float r = mRadius/(mCount-1);//r是蜘蛛丝之间的间距
        for(int i=1;i<mCount;i++){//中心点不用绘制
            float curR = r*i;//当前半径
            path.reset();
            for(int j=0;j<mCount;j++){
                if(j==0){
                    path.moveTo(mWidth+curR,mHeight);
                }else{
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (mWidth+curR * Math.cos(360 / mCount * j));
                    float y = (float) (mHeight+curR * Math.sin(360 / mCount * j));
                    path.lineTo(x,y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mPaint);
        }
    }
}
