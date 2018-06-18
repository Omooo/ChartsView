package top.omooo.admin.chartsdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.omooo.admin.chartsdemo.adapter.BillAdapter;
import top.omooo.admin.chartsdemo.bean.BillBean;
import top.omooo.admin.chartsdemo.view.ReboundScrollView;

/**
 * Created by SSC on 2018/6/12.
 */

public class PocketActivity extends AppCompatActivity {
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.rv_bills)
    RecyclerView mRvBills;
    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;
    @BindView(R.id.reboundView)
    ReboundScrollView mReboundView;
    @BindView(R.id.header_layout)
    ConstraintLayout mHeaderLayout;


    private boolean isHeaderVisiable = false;

    private List<BillBean> mBillBeanList = new ArrayList<>();

    private static final String TAG = "PocketActivity";

    private ValueAnimator mValueAnimator;

    //判断mTvTitle是否已经拉伸
    private boolean isExtent = false;
    //初始 mTvTitle宽度
    private int startWidth = 250;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pocket);
        ButterKnife.bind(this);

        initView();
//        startActivity(new Intent(this, DemoActivity.class));

        mReboundView.setOnReboundScrollListener(new ReboundScrollView.OnReboundScrollListener() {

            @Override
            public void onActionUp() {
                mTvTitle.setText("默认账本");
            }

            @Override
            public void onOverScrollDown(float dy) {
                Log.i(TAG, "onOverScrollDown: " + dy);
                if (dy > 200 && dy < 400) {
                    mTvTitle.setText("下拉同步");
                } else if (dy >= 400) {
                    mTvTitle.setText("释放同步");
                }
            }
        });


        mRvBills.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * 滚动状态回调
             * @param recyclerView
             * @param newState 0 ：静止
             *                 1 ： 用户手指滑动
             *                 2 ： 惯性滑动
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i(TAG, "onScrollStateChanged: " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(TAG, "onScrolled: " + dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (dy > 0) {
                    //向上滑动
                    stretchAnim(true);
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == mBillBeanList.size()) {
                        mHeaderLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    stretchAnim(false);
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == mBillBeanList.size() + 1) {
                        mHeaderLayout.setVisibility(View.GONE);
                    }
                }
                if (layoutManager.findLastCompletelyVisibleItemPosition() == mBillBeanList.size() + 1) {
                    //滑动了顶部
                    mReboundView.isChildScrolling(false);
                } else {
                    mReboundView.isChildScrolling(true);
                }
            }
        });
    }

    private void initView() {
        //线性倒序展示列表
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        manager.setStackFromEnd(true);
        mRvBills.setLayoutManager(manager);
        for (int i = 0; i < 20; i++) {
            mBillBeanList.add(new BillBean(true, R.drawable.ic_fish, "支出：" + 10 * i));
        }
        BillAdapter adapter = new BillAdapter(this, mBillBeanList);
        mRvBills.setAdapter(adapter);
        adapter.setHeaderView(LayoutInflater.from(this).inflate(R.layout.view_bill_footer, null));
        adapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.view_bill_header, null));
    }

    @OnClick({R.id.iv_header, R.id.tv_title, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header:
                if (isHeaderVisiable) {
                    ObjectAnimator animationUp = ObjectAnimator.ofFloat(mLlParent, "translationY", 320, 0);
                    animationUp.setDuration(800);
                    animationUp.start();
                    isHeaderVisiable = false;
                }
                break;
            case R.id.tv_title:
                if (!isHeaderVisiable && !isExtent) {
                    ObjectAnimator animationDown = ObjectAnimator.ofFloat(mLlParent, "translationY", 0, 320);
                    animationDown.setDuration(800);
                    animationDown.start();
                    isHeaderVisiable = true;
                }
                if (isExtent) {
                    Toast.makeText(this, "跳转到搜索页面...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_add:
                Toast.makeText(this, "添加Item", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //拉伸或缩放搜索框以及右上角Icon透明度渐变
    private void stretchAnim(boolean isStretch) {
        if (isStretch) {
            mValueAnimator = ValueAnimator.ofInt(startWidth, 1000);
            mValueAnimator.setDuration(2000);
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (Integer) animation.getAnimatedValue();
                    if (currentValue > 800) {
                        mTvTitle.setText("搜索框...");
                    }
                    mTvTitle.getLayoutParams().width = currentValue;
                    mTvTitle.requestLayout();
                }
            });
            mValueAnimator.start();

            isExtent = true;

            ObjectAnimator animator = ObjectAnimator.ofFloat(mIvAdd, "alpha", 1, 0.9f, 0.7f, 0.5f, 0.2f, 0);
            animator.setDuration(2000);
            animator.setRepeatCount(0);
            animator.start();
            mIvAdd.setVisibility(View.VISIBLE);
        } else {
            mValueAnimator = ValueAnimator.ofInt(mTvTitle.getMeasuredWidth(), 250);
            mValueAnimator.setDuration(2000);
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (Integer) animation.getAnimatedValue();
                    if (currentValue < 800) {
                        mTvTitle.setText("默认账本");
                    }
                    mTvTitle.getLayoutParams().width = currentValue;
                    mTvTitle.requestLayout();
                }
            });
            mValueAnimator.start();

            isExtent = false;

            ObjectAnimator animator = ObjectAnimator.ofFloat(mIvAdd, "alpha", 0, 0.2f, 0.5f, 0.7f, 0.9f, 1);
            animator.setDuration(2000);
            animator.setRepeatCount(0);
            animator.start();
            mIvAdd.setVisibility(View.VISIBLE);
        }
    }
}
