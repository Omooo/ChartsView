package top.omooo.admin.chartsdemo.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import top.omooo.admin.chartsdemo.R;

public class BannerViewPager extends FrameLayout {

    private static final int MSG_WHAT = 0;
    private int mDelayTimes;
    private ViewPager mViewPager;
    private ViewPager.PageTransformer mPageTransformer;
    private BannerAdapter mBannerAdapter;
    private int mItemCount;
    private LinearLayout mIndicatorsLayout;
    private TextView mTextTitle;
    private boolean isAutoPlay;
    private OnBannerItemClickListener mOnBannerItemClickListener;
    private BaseIndicator mBaseIndicator;
    private ImageLoaderInterface mImageLoaderInterface;
    private boolean isHaveTitle;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isAutoPlay) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                //只能用内部类了
                this.sendEmptyMessageDelayed(MSG_WHAT, mDelayTimes);
            }
        }
    };

    public BannerViewPager(@NonNull Context context) {
        this(context,null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        isHaveTitle = false;
        isAutoPlay = true;
        mItemCount = 1;
        mDelayTimes = 3000;
        initView();
        initListener();
        mHandler.sendEmptyMessageDelayed(MSG_WHAT, mDelayTimes);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(position);
                for (int i = 0; i < mIndicatorsLayout.getChildCount(); i++) {
                    if (i == position % mItemCount) {
                        ((BaseIndicator) mIndicatorsLayout.getChildAt(i)).setState(true);
                    } else {
                        ((BaseIndicator) mIndicatorsLayout.getChildAt(i)).setState(true);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        View.inflate(getContext(), R.layout.view_banner_layout, this);
        mViewPager = findViewById(R.id.viewPager);
        mIndicatorsLayout = findViewById(R.id.bannerIndicators);
        mTextTitle = findViewById(R.id.tv_title);
    }

    public BannerViewPager setData(List<BannerItemBean> data, ImageLoaderInterface imageLoaderInterface) {
        mImageLoaderInterface = imageLoaderInterface;
        mBannerAdapter = new BannerAdapter(this);
        mBannerAdapter.setItemBeans(data);
        mItemCount = data.size();
        mViewPager.setAdapter(mBannerAdapter);
        mViewPager.setCurrentItem(mItemCount * 1000);
        setIndicators(data.size());
        setTitle(0);
        return this;
    }

    private void setIndicators(int size) {
        mIndicatorsLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            if (mBaseIndicator == null) {
                DefaultIndicator indicator = new DefaultIndicator(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        getRatioDimension(getContext(), 20, false), getRatioDimension(getContext(), 20, false)
                );
                layoutParams.setMargins(getRatioDimension(getContext(), 10, true), 0,
                        getRatioDimension(getContext(), 10, true), 0);
                indicator.setLayoutParams(layoutParams);
                mIndicatorsLayout.addView(indicator);
            } else {
                BaseIndicator baseIndicator = mBaseIndicator;
                ViewParent vp = baseIndicator.getParent();
                if (vp != null) {
                    ViewGroup parent = (ViewGroup) vp;
                    parent.removeView(baseIndicator);
                }
                mIndicatorsLayout.addView(baseIndicator);
            }
        }
        ((BaseIndicator) mIndicatorsLayout.getChildAt(0)).setState(true);
    }

    public static int getRatioDimension(Context context, int value, boolean isWidth) {
        DisplayMetrics displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        float STANDARD_WIDTH = 1080F;
        float STANDARD_HEIGHT = 1920F;
        if (isWidth) {
            return (int) (value / STANDARD_WIDTH * widthPixels);
        } else {
            return (int) (value / STANDARD_HEIGHT * heightPixels);
        }
    }

    private void setTitle(int position) {
        if (isHaveTitle) {
            String title = mBannerAdapter.getItemBeans().get(position % mItemCount).getTitle();
            mTextTitle.setText(title);
            mTextTitle.setVisibility(VISIBLE);
        } else {
            mTextTitle.setVisibility(GONE);
        }
    }

    public boolean isAutoPlay() {
        return isAutoPlay;
    }

    public BannerViewPager setIsAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    public BannerViewPager setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        this.mOnBannerItemClickListener = listener;
        return this;
    }

    private BaseIndicator getIndicatorView() {
        return mBaseIndicator;
    }

    private BannerViewPager setIndicators(BaseIndicator indicators) {
        mBaseIndicator = indicators;
        mViewPager.setCurrentItem(mItemCount * 1000);
        setIndicators(mItemCount);
        setTitle(0);
        return this;
    }

    public BannerViewPager setPagerTransformer(ViewPager.PageTransformer transformer) {
        mPageTransformer = transformer;
        mViewPager.setPageTransformer(true, mPageTransformer);
        return this;
    }

    public BannerViewPager setDelayTimes(int delayTimes) {
        this.mDelayTimes = delayTimes;
        return this;
    }

    public boolean isHaveTitle() {
        return isHaveTitle;
    }

    public BannerViewPager setIsHaveTitle(boolean isHaveTitle) {
        this.isHaveTitle = isHaveTitle;
        setTitle(mViewPager.getCurrentItem());
        return this;
    }

    public void displayImage(Context context, ImageView imageView, String url) {
        mImageLoaderInterface.displayImage(context, url, imageView);
    }

    public interface OnBannerItemClickListener {
        void OnClickListener(View view, int currentItem);
    }

    public void onBannerItemClick(View view) {
        if (null != mOnBannerItemClickListener) {
            mOnBannerItemClickListener.OnClickListener(view, mViewPager.getCurrentItem() % mItemCount);
        }
    }
}
