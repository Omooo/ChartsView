package top.omooo.admin.chartsdemo;

/**
 * Created by Omooo on 2018/1/24.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.bean.PieBean;
import top.omooo.admin.chartsdemo.view.PieChart;

/**
 * 绘制饼图
 */
public class PieChartDemo extends AppCompatActivity {
    private List<PieBean> mPieBeans = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PieChart pieChart = new PieChart(this);
        setContentView(pieChart);

        mPieBeans.add(new PieBean("A", 150));
        mPieBeans.add(new PieBean("A", 80));
        mPieBeans.add(new PieBean("A", 200));
        mPieBeans.add(new PieBean("A", 100));
        mPieBeans.add(new PieBean("A", 180));
        pieChart.setData(mPieBeans);

    }
}
