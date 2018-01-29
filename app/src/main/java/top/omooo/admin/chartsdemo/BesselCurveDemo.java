package top.omooo.admin.chartsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import top.omooo.admin.chartsdemo.view.SecondBesselCurveView;

/**
 * Created by Omooo on 2018/1/29.
 */

public class BesselCurveDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SecondBesselCurveView view = new SecondBesselCurveView(this);
        setContentView(view);

    }
}
