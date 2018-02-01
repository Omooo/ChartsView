package top.omooo.admin.chartsdemo.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import top.omooo.admin.chartsdemo.R;

/**
 * Created by Omooo on 2018/1/31.
 */

public class NativeEditView extends android.support.v7.widget.AppCompatEditText implements TextWatcher{

    private RotateDrawable mRotateDrawable;
    private ValueAnimator alphaAnimator = ValueAnimator.ofInt(0, 255);
    private ValueAnimator rotateAnimator = ValueAnimator.ofInt(0, 10000);

    private void init() {
        setIconVisiable(false,getCompoundDrawables());
    }

    private void setIconVisiable(boolean b, Drawable[] drawables) {
        if (b) {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], getResources().getDrawable(R.drawable.edit_icon), drawables[3]);
            mRotateDrawable = (RotateDrawable) getCompoundDrawables()[2];
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], null, drawables[3]);
        }
    }
    public NativeEditView(Context context) {
        super(context);
        init();
    }

    public NativeEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NativeEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0 && before > 0) {
            //从有文字到删除文字时
            return;
        }
        if (start == 0 && s.length() > 0) {
            //从无文字到有文字
            setIconVisiable(true, getCompoundDrawables());
            setAnimator();
            startAnimatorSet();
        }
    }
    //启动动画
    private void startAnimatorSet() {
        AnimatorSet setVisible = new AnimatorSet();
        setVisible.playTogether(alphaAnimator, rotateAnimator);
        setVisible.start();
    }
    private void setAnimator() {
        alphaAnimator.setDuration(1000);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotateDrawable.setAlpha((Integer) animation.getAnimatedValue());
            }
        });

        rotateAnimator.setDuration(1000);
        rotateAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotateDrawable.setLevel((Integer) animation.getAnimatedValue());
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (getCompoundDrawables()[2] != null) {
                    if (getWidth() - getTotalPaddingRight() < event.getX() && getWidth() - getPaddingRight() > event.getX()) {
                        this.setText("");
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
