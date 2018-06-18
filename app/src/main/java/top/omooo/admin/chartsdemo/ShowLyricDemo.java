package top.omooo.admin.chartsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import top.omooo.admin.chartsdemo.utils.AnaLyricUtil;
import top.omooo.admin.chartsdemo.view.LoadingText;

/**
 * Created by Omooo on 2018/2/6.
 */

public class ShowLyricDemo extends AppCompatActivity {
    private TextView mView;
    private AnaLyricUtil mLyricUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyricdemo);

        mView = (TextView) findViewById(R.id.tv_lyric);
        mLyricUtil = new AnaLyricUtil();
        Toast.makeText(this, "2333", Toast.LENGTH_SHORT).show();

        LoadingText mLoadingText = new LoadingText(this);
        mLoadingText.setBitmap(R.drawable.icon_launch);
        mLoadingText.start();
    }
}
