package top.omooo.admin.chartsdemo.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.R;

public class BannerActivity extends AppCompatActivity {

    private BannerViewPager mBannerViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mBannerViewPager = findViewById(R.id.mBanner);
        mBannerViewPager.setData(getData(), new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, String url, ImageView imageView) {
                Glide.with(context).load(url).into(imageView);
            }
        }).setPagerTransformer(null)
                .setIsAutoPlay(true)
                .setDelayTimes(3000)
                .setOnBannerItemClickListener(new BannerViewPager.OnBannerItemClickListener() {
                    @Override
                    public void OnClickListener(View view, int currentItem) {
                        Toast.makeText(BannerActivity.this, "" + currentItem, Toast.LENGTH_SHORT).show();
                    }
                }).setIsHaveTitle(true);
    }

    private List<BannerItemBean> getData() {
        List<BannerItemBean> data = new ArrayList<>();
        String url = "https://i.loli.net/2018/03/03/5a9a735867aab.jpg";
        for (int i = 0; i < 5; i++) {
            data.add(new BannerItemBean(url, "2333"));
        }
        return data;
    }
}
