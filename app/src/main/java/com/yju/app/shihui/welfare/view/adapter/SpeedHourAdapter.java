package com.yju.app.shihui.welfare.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yju.app.R;
import com.yju.app.shihui.welfare.bean.SpeedHourEntity.TopicBean.ItemsBean.ListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeedHourAdapter extends RecyclerView.Adapter<SpeedHourHolder> {

    private List<ListBean> specailList;
    private LayoutInflater mInflater;
    private Context mContext=null;

    public SpeedHourAdapter(Context context) {
        this.mContext=context;
        mInflater = LayoutInflater.from(context);
    }

    public void setList(List<ListBean> list) {
        this.specailList = list;
        notifyDataSetChanged();
    }


    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    @Override
    public SpeedHourHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_speedhour_layout, parent, false);

        SpeedHourHolder holder = new SpeedHourHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SpeedHourHolder holder, final int position) {
        ListBean bean = specailList.get(position);
        if (bean != null) {
            holder.speedImage.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(bean.pic).error(R.drawable.welfare_default_icon).into(holder.speedImage);
            holder.speedName.setText("同仁堂枸杞茶");
            holder.speedPrice.setText("￥"+Math.random()*100);
        }

        holder.speedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (mOnItemClickListener!=null){
                  mOnItemClickListener.onItemClick(holder.speedView,position);
              }
            }
        });
    }

    @Override
    public int getItemCount() {
        return specailList.size();
    }
}


class SpeedHourHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.speed_view)
    LinearLayout speedView;
    @BindView(R.id.speed_image)
    ImageView speedImage;
    @BindView(R.id.speed_name)
    TextView speedName;
    @BindView(R.id.speed_price)
    TextView speedPrice;

    public SpeedHourHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setTag(this);
    }

}