package top.omooo.admin.chartsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.bean.RadarMapBean;
import top.omooo.admin.chartsdemo.view.RadarMapView;

/**
 * Created by Omooo on 2018/1/29.
 */

public class RadarMapDemo extends AppCompatActivity {
    private List<RadarMapBean> mRadarMapBeans = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RadarMapView mMapView = new RadarMapView(this);
        setContentView(mMapView);

        mRadarMapBeans.add(new RadarMapBean(6, new String[]{"A", "B", "C", "D", "E", "F"}, new int[]{60, 80, 50, 100, 90, 40}));
        mMapView.setData(mRadarMapBeans);

    }
}
