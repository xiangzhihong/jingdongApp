package com.yju.app.shihui.estateservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.shihui.estateservice.entity.EstatePayEntity.DataBean;
import com.yju.app.shihui.estateservice.entity.EstatePayEntity.DetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EstatePayExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DataBean> groupList;

    public EstatePayExpandableAdapter(Context context,
                                      List<DataBean> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    public void setListData(List<DataBean> groupList) {
        this.groupList = groupList;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
            return null == groupList ? 0 : groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return null == groupList.get(groupPosition).detail ? 0
                : groupList.get(groupPosition).detail.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).detail.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupList.get(groupPosition).hashCode();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).detail.get(childPosition)
                .hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        GroupViewHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_estate_group_layout, null);
            groupHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }
        initGroupView(groupHolder, isExpanded, groupPosition);
        return convertView;
    }


    class GroupViewHolder {

        @BindView(R.id.group_image)
        CheckBox groupImage;
        @BindView(R.id.group_title)
        TextView groupTitle;
        @BindView(R.id.group_price)
        TextView groupPrice;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
            groupImage.setBackgroundResource(R.drawable.unselect_icon);
            groupTitle.setText("");
            groupPrice.setText("￥0.00");
        }
    }

    private void initGroupView(GroupViewHolder groupHolder, boolean isExpanded, int groupPosition) {
        if (isExpanded) {
            groupHolder.groupPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.arrow_up_icon, 0);
        } else {
            groupHolder.groupPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.arrow_down_icon, 0);
        }

        groupHolder.groupTitle.setText(groupList.get(groupPosition).end_time_string);
        groupHolder.groupPrice.setText("￥" + groupList.get(groupPosition).due);

    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_estate_child_layout, null);
            childHolder = new ChildViewHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) convertView.getTag();
        }
        initChildView(childHolder,groupPosition, childPosition);
        return convertView;
    }


    class ChildViewHolder {

        @BindView(R.id.child_title)
        TextView childTitle;
        @BindView(R.id.child_price)
        TextView childPrice;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
            childTitle.setText("");
            childPrice.setText("￥0.00");
        }
    }

    private void initChildView(ChildViewHolder childHolder, int groupPosition, int childPosition) {
        childHolder.childTitle.setText(groupList.get(groupPosition).detail.get(childPosition).fee_name);
        childHolder.childPrice.setText("￥" + groupList.get(groupPosition).detail.get(childPosition).fee_due);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
