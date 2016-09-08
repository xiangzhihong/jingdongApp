package com.yju.app.shihui.welfare.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yju.app.R;
import com.yju.app.shihui.detail.ProductDetailsActivity;
import com.yju.app.shihui.welfare.bean.FineFareEntity.PanicBean;
import com.yju.app.utils.ToastUtils;
import com.yju.app.widght.image.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareAdapter extends PagerAdapter {

    private Context mContext;
    private List<PanicBean> dataList = new ArrayList<>();

    public WelfareAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<PanicBean> list) {
        if (list.size() <= 0) {
            dataList.clear();
            notifyDataSetChanged();
            return;
        }
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return /*Integer.MAX_VALUE*/dataList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

            PanicBean data = dataList.get(position);

            ViewHolder viewHolder = null;
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.item_finefare_layout, null);
            if (viewHolder == null) {
                viewHolder = new ViewHolder(view);
            }
            bindView(viewHolder, data);

            container.addView(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);

            return view;
    }

    private void bindView(ViewHolder viewholder, final PanicBean data) {
        Glide.with(mContext).load(data.pic).into(viewholder.welfareImage);

        viewholder.welfareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsActivity.open(mContext);
            }
        });
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    class ViewHolder {
        @BindView(R.id.welfare_image)
        RoundedImageView welfareImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
            welfareImage.setBackground(mContext.getResources().getDrawable(R.drawable.welfare_default_icon));
        }
    }

}
