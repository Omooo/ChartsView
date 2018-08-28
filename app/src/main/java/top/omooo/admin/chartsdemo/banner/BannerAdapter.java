package top.omooo.admin.chartsdemo.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private BannerViewPager mBannerViewPager;
    private int mItemCount = 1;
    private List<BannerItemBean> mItemBeans;
    private List<ImageView> mImageViews;
    private ImageView.ScaleType mScaleType;

    public BannerAdapter(BannerViewPager bannerViewPager) {
        mBannerViewPager = bannerViewPager;
        mImageViews = new ArrayList<>();
    }

    public List<BannerItemBean> getItemBeans() {
        return mItemBeans;
    }

    public void setItemBeans(List<BannerItemBean> itemBeans) {
        mItemBeans = itemBeans;
        if (null != mItemBeans) {
            mItemCount = mItemBeans.size();
        }
    }

    public ImageView.ScaleType getScaleType() {
        return mScaleType == null ? ImageView.ScaleType.CENTER_CROP : mScaleType;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        mScaleType = scaleType;
    }

    @Override
    public int getCount() {
        return mItemBeans == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mImageViews.size() <= mItemCount) {
            for (int i = 0; i < mItemCount; i++) {
                ImageView imageView = new ImageView(container.getContext());
                imageView.setScaleType(getScaleType());
                mBannerViewPager.displayImage(container.getContext(), imageView, mItemBeans.get(i).getImageUrl());
                mImageViews.add(imageView);
            }
        }
        ImageView imageView = mImageViews.get(position % mItemCount);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBannerViewPager.onBannerItemClick(v);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position % mItemCount));
    }
}
