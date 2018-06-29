package top.omooo.admin.chartsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SSC on 2018/6/27.
 */

public class MoveViewActivity extends AppCompatActivity {
    @BindView(R.id.tv_child)
    TextView mTvChild;
    @BindView(R.id.tv_dependency)
    TextView mTvDependency;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_move_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_child, R.id.tv_dependency})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_child:
                break;
            case R.id.tv_dependency:
                ViewCompat.offsetTopAndBottom(view, 50);
                break;
        }
    }
}
