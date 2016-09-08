package com.yju.app.shihui.estateservice.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;
import com.yju.app.shihui.estateservice.adapter.EstateAddressAdapter;
import com.yju.app.shihui.estateservice.entity.EstateAddressEntity;
import com.yju.app.utils.UIUtils;
import com.yju.swipemenu.SwipeMenu;
import com.yju.swipemenu.SwipeMenuCreator;
import com.yju.swipemenu.SwipeMenuItem;
import com.yju.swipemenu.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/9/2.
 * 物业缴费地址管理
 */
public class EstateAddressActivity extends BaseActivity {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.address_listView)
    SwipeMenuListView addressListView;

    private EstateAddressAdapter mEstateAddressAdapter = null;
    private List<EstateAddressEntity> addressList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estate_address);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.back)
    public void backClick() {
        finish();
    }

    private void init() {
        initTitle();
        initData();
        initListView();
    }

    private void initTitle() {
        titleName.setText("缴费地址");
    }


    private void initData() {
        addressList = new ArrayList<>();
        EstateAddressEntity entity = new EstateAddressEntity();
        entity.name = "绿地共和城";
        entity.address = "闸北区广中西路299号";
        addressList.add(entity);

        entity = new EstateAddressEntity();
        entity.name = "绿地延长路";
        entity.address = "闸北区延长西路299号";
        addressList.add(entity);

        entity = new EstateAddressEntity();
        entity.name = "绿地橄榄城";
        entity.address = "静安区299号";
        addressList.add(entity);

        entity = new EstateAddressEntity();
        entity.name = "绿地橄榄城";
        entity.address = "静安区299号";
        addressList.add(entity);
    }


    private void initListView() {
        mEstateAddressAdapter = new EstateAddressAdapter(EstateAddressActivity.this);
        mEstateAddressAdapter.setList(addressList);
        addressListView.setAdapter(mEstateAddressAdapter);
        addressListView.setMenuCreator(creator);
        initClick();
    }

    private void initClick() {
        addressListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    // delete
                    case 0:
                        addressList.remove(position);
                        mEstateAddressAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });


    }

    private SwipeMenuCreator creator = new SwipeMenuCreator() {
        @Override
        public void create(SwipeMenu menu) {
            // 创建delete的view
            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
            deleteItem.setBackground(R.color.c20);
            deleteItem.setWidth(UIUtils.dp2px(getApplicationContext(), 90));
            deleteItem.setTitle("删除");
            deleteItem.setTitleSize(18);
            deleteItem.setTitleColor(Color.WHITE);

            menu.addMenuItem(deleteItem);
        }
    };


    public static void open(Context context) {
        Intent intent = new Intent(context, EstateAddressActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.create_address)
    public void createClick() {
        EstateCreateAddressActivity.open(this);
    }
}
