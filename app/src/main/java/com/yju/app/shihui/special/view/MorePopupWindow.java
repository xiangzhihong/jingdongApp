package com.yju.app.shihui.special.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yju.app.R;
import com.yju.app.shihui.special.adapter.PopupAdapter;
import com.yju.app.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MorePopupWindow extends PopupWindow {

    @BindView(R.id.popup_listView)
    ListView popupListView;
    @BindView(R.id.popup_layout)
    LinearLayout popupLayout;

    private Context mContext;
    private OnItemClickListener mOnItemClickListener=null;
    private List<String> mList=null;
    private LayoutInflater inflater = null;
    private View mView=null;
    private PopupAdapter adapter=null;

    public MorePopupWindow(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mOnItemClickListener=onItemClickListener;
        init();
    }

    public MorePopupWindow(Context context, OnItemClickListener onItemClickListener, ArrayList<String> list) {
        this.mContext = context;
        this.mList = list;
        this.mOnItemClickListener=onItemClickListener;
        init();
    }

    public void setList(List<String> mList) {
       this.mList=mList;
    }

    private void init() {
        initView();
        setPopupView();
    }

    private void initAdapter() {
        adapter = new PopupAdapter(mContext);
        popupListView.setAdapter(adapter);
    }

    private void initView() {
        if (null==mContext)return;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.popupwindow_layout, null);
        ButterKnife.bind(this,mView);
        popupLayout.setGravity(Gravity.RIGHT);
        if (mOnItemClickListener!=null)
            popupListView.setOnItemClickListener(mOnItemClickListener);
    }


    private void setPopupView() {
        setContentView(mView);
        setWidth(UIUtils.getScreenWidth(mContext)/3);
        setHeight(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
//        setAnimationStyle(R.style.anim_push_top);
        setBackgroundDrawable(new ColorDrawable(0x33000000));
    }

    public void show(View view,int marginTop) {
        adapter = new PopupAdapter(mContext);
        popupListView.setAdapter(adapter);
        adapter.setList(mList);
        showAsDropDown(view, 0, marginTop);
    }

}
