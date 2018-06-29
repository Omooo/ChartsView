package top.omooo.admin.chartsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SSC on 2018/6/18.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_chart)
    Button mBtnChart;
    @BindView(R.id.btn_demo)
    Button mBtnDemo;
    @BindView(R.id.btn_lyric)
    Button mBtnLyric;
    @BindView(R.id.btn_native_edit)
    Button mBtnNativeEdit;
    @BindView(R.id.btn_piechart)
    Button mBtnPiechart;
    @BindView(R.id.btn_pocket)
    Button mBtnPocket;
    @BindView(R.id.btn_progress_bar)
    Button mBtnProgressBar;
    @BindView(R.id.btn_radarmap)
    Button mBtnRadarmap;
    @BindView(R.id.btn_waves)
    Button mBtnWaves;
    @BindView(R.id.btn_mp_pie_chart)
    Button mBtnMpPieChart;
    @BindView(R.id.btn_move)
    Button mBtnMove;
    @BindView(R.id.btn_scroll)
    Button mBtnScroll;
    @BindView(R.id.btn_board)
    Button mBtnBoard;
    @BindView(R.id.btn_rain)
    Button mBtnRain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_chart, R.id.btn_demo, R.id.btn_lyric, R.id.btn_native_edit, R.id.btn_piechart, R.id.btn_pocket, R.id.btn_progress_bar, R.id.btn_radarmap, R.id.btn_waves, R.id.btn_mp_pie_chart,
            R.id.btn_move, R.id.btn_scroll, R.id.btn_board, R.id.btn_rain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_chart:
                startActivity(new Intent(this, ChartActivity.class));
                break;
            case R.id.btn_demo:
                startActivity(new Intent(this, DemoActivity.class));
                break;
            case R.id.btn_lyric:
                startActivity(new Intent(this, ShowLyricDemo.class));
                break;
            case R.id.btn_native_edit:
                startActivity(new Intent(this, NativeEditDemo.class));
                break;
            case R.id.btn_piechart:
                startActivity(new Intent(this, PieChartDemo.class));
                break;
            case R.id.btn_pocket:
                startActivity(new Intent(this, PocketActivity.class));
                break;
            case R.id.btn_progress_bar:
                startActivity(new Intent(this, ProgressDemo.class));
                break;
            case R.id.btn_radarmap:
                startActivity(new Intent(this, RadarMapDemo.class));
                break;
            case R.id.btn_waves:
                startActivity(new Intent(this, WavesDemo.class));
                break;
            case R.id.btn_mp_pie_chart:
                startActivity(new Intent(this, MPPieChartActivity.class));
                break;
            case R.id.btn_move:
                startActivity(new Intent(this, MoveViewActivity.class));
                break;
            case R.id.btn_scroll:
                startActivity(new Intent(this, ScrollViewActivity.class));
                break;
            case R.id.btn_board:
                startActivity(new Intent(this, DrawBoardActivity.class));
                break;
            case R.id.btn_rain:
                break;
        }
    }

}
