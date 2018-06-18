package top.omooo.admin.chartsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SSC on 2018/6/18.
 */

public class MPPieChartActivity extends AppCompatActivity {
    @BindView(R.id.pie_chart)
    PieChart mPieChart;
    @BindView(R.id.btn_show_percent)
    Button mBtnShowPercent;
    @BindView(R.id.btn_show_type)
    Button mBtnShowType;
    @BindView(R.id.btn_anim_x)
    Button mBtnAnimX;
    @BindView(R.id.btn_anim_y)
    Button mBtnAnimY;
    @BindView(R.id.btn_anim_xy)
    Button mBtnAnimXy;
    @BindView(R.id.btn_show_text)
    Button mBtnShowText;
    @BindView(R.id.btn_save_pic)
    Button mBtnSavePic;
    @BindView(R.id.btn_anim_rotating)
    Button mBtnAnimRotating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_pie);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        //使用百分比显示
        mPieChart.setUsePercentValues(true);
        //设置对饼状图的描述
        mPieChart.getDescription().setEnabled(true);
        //设置饼状图的左上右下的偏移量，类似外边距
        mPieChart.setExtraOffsets(5, 5, 5, 5);
        //设置饼状图背景色
        mPieChart.setBackgroundColor(Color.WHITE);
        //设置饼状图转动阻力摩擦系数 [0,1]
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文字
        mPieChart.setDrawCenterText(true);
        mPieChart.setCenterText("总支出 1683.00");
        mPieChart.setCenterTextColor(Color.BLACK);
        mPieChart.setCenterTextSize(20f);
        //设置中间圆环属性
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        //设置图标旋转起始角度
        mPieChart.setRotationAngle(0);
        //设置可手动旋转
        mPieChart.setRotationEnabled(true);
        //设置点击高亮
        mPieChart.setHighlightPerTapEnabled(true);

        //设置数据
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(800, "房租"));
        entries.add(new PieEntry(200, "衣服鞋包"));
        entries.add(new PieEntry(300, "一般"));
        entries.add(new PieEntry(150, "买菜"));
        entries.add(new PieEntry(233, "零食"));

        setData(entries);
        //设置动画
        mPieChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);


        //获取饼状图的图例
        Legend legend = mPieChart.getLegend();
        legend.setEnabled(true);
    }

    @OnClick({R.id.btn_show_percent, R.id.btn_show_type, R.id.btn_anim_x, R.id.btn_anim_y, R.id.btn_anim_xy, R.id.btn_show_text, R.id.btn_save_pic,R.id.btn_anim_rotating})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_show_percent:
                for (IDataSet<?> set : mPieChart.getData().getDataSets()) {
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }
                break;
            case R.id.btn_show_type:
                if (mPieChart.isDrawHoleEnabled()) {
                    mPieChart.setDrawHoleEnabled(false);
                } else {
                    mPieChart.setDrawHoleEnabled(true);
                }
                break;
            case R.id.btn_anim_x:
                mPieChart.animateX(1500);
                break;
            case R.id.btn_anim_y:
                mPieChart.animateY(1500);
                break;
            case R.id.btn_anim_xy:
                mPieChart.animateXY(1500,1500);
                break;
            case R.id.btn_show_text:
                if (mPieChart.isDrawCenterTextEnabled()) {
                    mPieChart.setDrawCenterText(false);
                } else {
                    mPieChart.setDrawCenterText(true);
                }
                break;
            case R.id.btn_save_pic:
                mPieChart.saveToPath("pic" + System.currentTimeMillis(), "");
                break;
            case R.id.btn_anim_rotating:
                mPieChart.spin(1500, mPieChart.getRotationAngle(), mPieChart.getRotationAngle() + 360, Easing.EasingOption.EaseInCubic);
                break;
        }
        mPieChart.invalidate();
    }

    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "总支出");
        //饼状图 Item之间的间隔
        dataSet.setSliceSpace(0);
        //设置饼状图Item被选中的时候变化的距离
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#66CCCC"));
        colors.add(Color.parseColor("#FF9966"));
        colors.add(Color.parseColor("#FF6666"));
        colors.add(Color.parseColor("#CCFFFF"));
        colors.add(Color.parseColor("#FFCC99"));
        dataSet.setColors(colors);
        //最终数据
        PieData data = new PieData(dataSet);
        //设置数据为百分比样式
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValue(null);
        mPieChart.invalidate();
    }
}
