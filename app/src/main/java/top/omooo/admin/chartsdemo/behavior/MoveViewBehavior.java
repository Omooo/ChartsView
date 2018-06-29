package top.omooo.admin.chartsdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import top.omooo.admin.chartsdemo.R;

/**
 * Created by SSC on 2018/6/27.
 */

public class MoveViewBehavior extends CoordinatorLayout.Behavior<View> {
    public MoveViewBehavior() {
    }

    public MoveViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //确定是否依赖某个View
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.tv_dependency;
    }

    //当依赖的View发生变化时，child需要响应什么（位置、大小、状态）
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offsetY = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offsetY);
        return super.onDependentViewChanged(parent, child, dependency);
    }

}
