package top.omooo.admin.chartsdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import top.omooo.admin.chartsdemo.view.ProgressView;

/**
 * Created by Omooo on 2018/2/4.
 */

public class ProgressDemo extends AppCompatActivity {
    private ProgressView mProgressView;
    private Timer mTimer;
    private int progress = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mProgressView = (ProgressView) findViewById(R.id.progress);
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mProgressView.setProgress(progress);
                progress++;
                if (progress > 100) {
                    mTimer.cancel();
                }
            }
        }, 100, 1000);
    }
}
