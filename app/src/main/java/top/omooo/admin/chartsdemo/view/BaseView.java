package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import top.omooo.admin.chartsdemo.R;

/**
 * Created by Omooo on 2017/9/5.
 */

public abstract class BaseView extends View {

    public Paint mPaint;

    //X坐标轴最大值
    public float maxAxisValueX = 900;
    //X坐标轴刻度线数量
    public int axisDivideSizeX = 9;
    //Y坐标轴最大值
    public float maxAxisValueY = 700;
    //Y坐标轴刻度线数量
    public int axisDivideSizeY = 7;
    //第一维度为值，第二维度为颜色
    public int[][] columnInfo;
    //视图宽高
    public int width;
    public int height;
    //起点的X Y坐标值
    public final int originalX=100;
    public final int originalY=800;

    private String mGraphTitle;
    private String mXAxisName;
    private String mYAxisName;

    private float mAxisTextSize;
    public BaseView(Context context) {
        this(context,null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.DemoStyle);

        mGraphTitle = typedArray.getString(R.styleable.DemoStyle_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.DemoStyle_xAxisName);
        mYAxisName = typedArray.getString(R.styleable.DemoStyle_yAxisName);
        mAxisTextSize = typedArray.getDimension(R.styleable.DemoStyle_axisTextSize,25);

        if (typedArray != null) {
            typedArray.recycle();
        }
        initPaint(context);
    }
    //数据源
    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }
    //设置X轴的最大值以及等份数
    public void setXAxisScaleValue(float maxAxisValueX,int dividedSize) {
        this.maxAxisValueX = maxAxisValueX;
        this.axisDivideSizeX = dividedSize;
    }
    //设置Y轴的最大值以及等份数
    public void setYAxisScaleValue(float maxAxisValueY,int dividedSize) {
        this.maxAxisValueY = maxAxisValueY;
        this.axisDivideSizeY = dividedSize;
    }
    private void initPaint(Context context) {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        width = getWidth() - originalX - 100;
        height = (originalY > getHeight() ? getHeight() : originalY) - 400;

        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        drawTitle(canvas, mPaint);
        drawXAxisScale(canvas, mPaint);//刻度
        drawYAxisScale(canvas, mPaint);
        drawXAxisScaleValue(canvas, mPaint);//刻度值
        drawYAxisScaleValue(canvas, mPaint);
        drawXAxisArrow(canvas, mPaint);//箭头
        drawYAxisArrow(canvas, mPaint);
        drawColumn(canvas, mPaint);//柱条
    }

    private void drawYAxisArrow(Canvas canvas, Paint mPaint) {
        Path mpathY = new Path();
        mpathY.moveTo(originalX, originalY - height - 30);
        mpathY.lineTo(originalX - 10, originalY - height);
        mpathY.lineTo(originalX + 10, originalY - height);

        mpathY.close();
        canvas.drawPath(mpathY, mPaint);
        canvas.drawText(mYAxisName, originalX - 50, originalY - height - 30, mPaint);

    }

    private void drawXAxisArrow(Canvas canvas, Paint mPaint) {
        Path mpathX = new Path();
        mpathX.moveTo(originalX + width + 30, originalY);
        mpathX.lineTo(originalX + width, originalY - 10);
        mpathX.lineTo(originalX + width, originalY + 10);

        mpathX.close();
        canvas.drawPath(mpathX, mPaint);
        canvas.drawText(mXAxisName, originalX + width, originalY + 30, mPaint);

    }

    private void drawTitle(Canvas canvas, Paint mPaint) {
        //画标题
        if (mGraphTitle != null) {
            //设置画笔绘制文字的属性
            mPaint.setTextSize(mAxisTextSize);
//            mPaint.setFakeBoldText(true);
            canvas.drawText(mGraphTitle, (getWidth()/2)-(mPaint.measureText(mGraphTitle)/2), originalY + 100, mPaint);
        }
    }
    protected abstract void drawColumn(Canvas canvas, Paint mPaint);

    protected abstract void drawYAxisScale(Canvas canvas, Paint mPaint);

    protected abstract void drawYAxisScaleValue(Canvas canvas, Paint mPaint);

    protected abstract void drawXAxisScaleValue(Canvas canvas, Paint mPaint);

    protected abstract void drawXAxisScale(Canvas canvas, Paint mPaint);

    protected abstract void drawYAxis(Canvas canvas, Paint mPaint);

    protected abstract void drawXAxis(Canvas canvas, Paint mPaint);
}
