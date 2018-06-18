package top.omooo.admin.chartsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import top.omooo.admin.chartsdemo.R;
import top.omooo.admin.chartsdemo.bean.BillBean;

/**
 * Created by SSC on 2018/6/14.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillHolder>{

    private Context mContext;
    private List<BillBean> mBillBeans;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    private View mHeaderView;
    private View mFooterView;


    public BillAdapter(Context context, List<BillBean> billBeans) {
        mContext = context;
        mBillBeans = billBeans;
    }

    @Override
    public BillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new BillHolder(LayoutInflater.from(mContext).inflate(R.layout.view_bill_header, parent, false));
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new BillHolder(LayoutInflater.from(mContext).inflate(R.layout.view_bill_footer, parent, false));
        }
        return new BillHolder(LayoutInflater.from(mContext).inflate(R.layout.view_bill_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BillHolder holder, int position) {


        if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) {
            return;
        }
        position = position - 1;
        holder.mTextView.setText(mBillBeans.get(position).getText());
        holder.mImageView.setImageResource(mBillBeans.get(position).getIconId());
        if (mBillBeans.get(position).isChick()) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Item 被点击", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView != null && mFooterView != null) {
            return mBillBeans.size() + 2;
        } else {
            return mBillBeans.size();
        }
    }

    class BillHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        BillHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_type);
            mTextView = itemView.findViewById(R.id.tv_bill);
        }
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
//        notifyItemInserted(getItemCount() - 1);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
//        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_HEADER;
        } else if (position == 0) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }
}
