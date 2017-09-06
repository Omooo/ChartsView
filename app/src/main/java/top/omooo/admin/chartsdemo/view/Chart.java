package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Omooo on 2017/9/5.
 */

public class Chart extends BaseView {
    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //画刻度
    @Override
    protected void drawColumn(Canvas canvas, Paint mPaint) {
        if (columnInfo != null) {
            float cellWidth = width / axisDivideSizeX;
            for (int i = 0; i < columnInfo.length; i++) {
                mPaint.setColor(columnInfo[i][1]);
                float leftTopY = originalY - height * (columnInfo[i][0]) / axisDivideSizeY;
                canvas.drawRect(originalX + cellWidth * (i + 1), leftTopY, originalX + cellWidth*(i + 2), originalY, mPaint);
            }
        }else
            return;
    }

    @Override
    protected void drawYAxisScale(Canvas canvas, Paint mPaint) {
        float cellHeight = height / axisDivideSizeY;
        for (int i = 0; i < axisDivideSizeY-1; i++) {
            canvas.drawText( String.valueOf(i), originalX - 30, originalY - cellHeight * i + 10, mPaint);
        }
    }
    //画Y轴刻度值
    @Override
    protected void drawYAxisScaleValue(Canvas canvas, Paint mPaint) {
        float cellHeight = height / axisDivideSizeY;
        for (int i = 1; i < axisDivideSizeY; i++) {
            canvas.drawText(String.valueOf(i), originalX - 30, originalY - cellHeight * i + 10, mPaint);
        }
    }
    //画X轴刻度值
    @Override
    protected void drawXAxisScaleValue(Canvas canvas, Paint mPaint) {
//        mPaint.setColor(Color.GRAY);
//        mPaint.setTextSize(12);
//        mPaint.setFakeBoldText(true);
        float cellWidth = width / axisDivideSizeX;
        for (int i = 0; i < axisDivideSizeX; i++) {
            canvas.drawText(i + "", cellWidth * i + originalX - 35, originalY + 30, mPaint);
        }
    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint mPaint) {
        float cellWidth = width / axisDivideSizeX;
        for (int i=0;i<axisDivideSizeX-1;i++) {
            canvas.drawLine(cellWidth * (i + 1) + originalX, originalY, cellWidth * (i + 1) + originalX, originalY - 10, mPaint);
        }
    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint mPaint) {
        canvas.drawLine(originalX, originalY, originalX, originalY - height, mPaint);
    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.BLACK);
        //设置画笔宽度
        mPaint.setStrokeWidth(5);
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        //画横轴(X)
        canvas.drawLine(originalX, originalY, originalX + width, originalY, mPaint);
    }
}
