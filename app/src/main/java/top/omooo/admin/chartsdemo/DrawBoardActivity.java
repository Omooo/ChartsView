package top.omooo.admin.chartsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import top.omooo.admin.chartsdemo.view.DrawBoardView;

/**
 * Created by SSC on 2018/6/29.
 */

public class DrawBoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawBoardView(this));
    }
}
