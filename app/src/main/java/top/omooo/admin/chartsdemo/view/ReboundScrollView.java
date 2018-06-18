package top.omooo.admin.chartsdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by SSC on 2018/6/14.
 */

public class ReboundScrollView extends NestedScrollView {

    private static final String TAG = "ReboundScrollView";

    //移动因子，比如手指滑动了100dp，而View则移动50dp，目的是达到一个延迟的效果
    private static final float MOVE_FACTOR = 0.5f;

    //手指松开后，回到正常位置所需的动画时间
    private static final int ANIM_TIME = 300;

    //ScrollView的唯一子View
    private View contentView;

    //手指按下时的Y值，用于在移动时候计算移动距离
    private float startY;

    //用于记录正常的布局位置
    private Rect originalRect = new Rect();

    //手指按下时记录是否可以继续下拉
    private boolean canPullDown = false;

    //手指按下时记录是否可以继续上拉
    private boolean canPullUp = false;

    //在手指滑动的过程中记录是否移动了布局
    private boolean isMoved = false;
    private boolean mCanPullDwon;

    public ReboundScrollView(Context context) {
        super(context);
        init();
    }

    public ReboundScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReboundScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFillViewport(true);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (contentView == null) {
            return;
        }
        //唯一子View的位置
        originalRect.set(contentView.getLeft(), contentView.getTop(),
                contentView.getRight(), contentView.getBottom());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (contentView == null) {
            return super.dispatchTouchEvent(ev);
        }
        //手指是否移动到了当前ScrollView控件之外
        boolean isTouchOutOfScrollView = ev.getY() >= this.getHeight() || ev.getY() <= 0;
        //移动到了当前ScrollView控件之外后消费该事件
        if (isTouchOutOfScrollView) {
            //如果当前contentView已经被移动，首先把布局移到原来位置后消费事件
            if (isMoved) {
                boundBack();
                return true;
            }
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                canPullDown = isCanPullDown();
                canPullUp = isCanPullUp();
                //记录按下的Y值
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                boundBack();
                mOnReboundScrollListener.onActionUp();
                break;
            case MotionEvent.ACTION_MOVE:
                //在移动的过程中，既没有滚动到可以上拉的程度，也没有滚动到可以下拉的程度
                if (!canPullUp && !canPullDown) {
                    startY = ev.getY();
                    canPullDown = isCanPullDown();
                    canPullUp = isCanPullUp();
                    break;
                }

                //计算手指移动的距离
                float nowY = ev.getY();
                int deltaY = (int) (nowY - startY);

                //是否应该移动布局
                boolean shouldMove = (canPullDown && deltaY > 0) ;
                if (shouldMove && !isChildScrolling) {
                    //计算偏移量
                    int offset = (int) (deltaY * MOVE_FACTOR);
                    //随着手指移动而移动布局
                    contentView.layout(originalRect.left, originalRect.top + offset,
                            originalRect.right, originalRect.bottom + offset);
                    //记录移动了布局
                    isMoved = true;
                    mOnReboundScrollListener.onOverScrollDown(deltaY);
                }

                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    //判断是否滚动到了顶部
    private boolean isCanPullDown() {
        return getScrollY() == 0 ||
                contentView.getHeight() < getHeight() + getScrollY();
    }

    //判断是否滚动了底部
    private boolean isCanPullUp() {
//        return  contentView.getHeight() <= getHeight() + getScrollY();
        //滑动到底部之后不能在上拉
        return false;
    }

    //将布局移动到原位置
    private void boundBack() {
        //如果没移动布局，则跳过
        if (!isMoved) {
            return;
        }
        // 开启动画
        TranslateAnimation anim = new TranslateAnimation(0, 0, contentView.getTop(),
                originalRect.top);
        anim.setDuration(ANIM_TIME);

        contentView.startAnimation(anim);

        // 设置回到正常的布局位置
        contentView.layout(originalRect.left, originalRect.top,
                originalRect.right, originalRect.bottom);

        //将标志位设回false
        canPullDown = false;
        canPullUp = false;
        isMoved = false;
    }

    private OnReboundScrollListener mOnReboundScrollListener;

    public void setOnReboundScrollListener(OnReboundScrollListener listener) {
        mOnReboundScrollListener = listener;
    }

    public interface OnReboundScrollListener {

        //下拉过界回调
        void onOverScrollDown(float dy);

        //手指抬起回调
        void onActionUp();

    }

    /**
     * 在ScrollView的子View（RecycleView）在滑动状态时，禁止ScrollView可拉伸
     * 其实可以理解为：在RecycleView还未滑动到顶部时，是不允许ScrollView可拉伸
     */
    private boolean isChildScrolling;
    public void isChildScrolling(boolean isScrolling) {
        this.isChildScrolling = isScrolling;
    }
}
