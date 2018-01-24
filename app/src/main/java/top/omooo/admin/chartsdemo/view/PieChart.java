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

/**
 * 饼状图
 */
public class PieChart extends View {

    // 1.创建一个画笔
    private Paint mPaint=new Paint();
    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.RED);     //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);     //设置画笔宽度
    }
    // 3.在构造方法中调用
    public PieChart(Context context) {
        super(context);
        initPaint();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);

        canvas.drawCircle(100, 100, 50, mPaint);
    }
}
