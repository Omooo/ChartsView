package top.omooo.admin.chartsdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.omooo.admin.chartsdemo.view.Chart;

public class MainActivity extends AppCompatActivity {

    private Chart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (Chart) findViewById(R.id.chartView);
        mChart.setXAxisScaleValue(10, 9);
        mChart.setYAxisScaleValue(10, 7);

        int[][] columnInfo = new int[][]{
                {6, Color.BLUE},
                {5, Color.GREEN},
                {9, Color.RED},
                {2, Color.GRAY},
                {1, Color.MAGENTA},
                {3, Color.CYAN},
                {8, Color.YELLOW},
        };
        mChart.setColumnInfo(columnInfo);
    }
}
