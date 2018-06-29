package top.omooo.admin.chartsdemo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SSC on 2018/6/27.
 */

public class ScrollViewBehavior extends CoordinatorLayout.Behavior<View> {
    public ScrollViewBehavior() {
    }

    public ScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否要让Behavior处理滑动
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    //具体滑动处理
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        int offsetY = target.getScrollY();
        child.setScrollY(offsetY);
    }
}
