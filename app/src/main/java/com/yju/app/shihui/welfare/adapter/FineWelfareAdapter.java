/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yju.app.shihui.welfare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yju.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 精品福利适配器
 */
public class FineWelfareAdapter extends RecyclerView.Adapter<FineWelfareAdapter.SimpleViewHolder> {

    private final Context mContext;
    private final List<Integer> mList;
    private int mCurrentItemId = 0;
    private CallBack mCallBack = null;


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
       public ImageView welfareImage;

        public SimpleViewHolder(View view) {
            super(view);
            welfareImage = (ImageView) view.findViewById(R.id.welfare_image);
        }
    }

    public FineWelfareAdapter(Context context, int itemCount, CallBack callBack) {
        this(context, itemCount);
        this.mCallBack = callBack;
    }

    public FineWelfareAdapter(Context context, int itemCount) {
        mContext = context;
        mList = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            addItem(i);
        }

    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mList.add(position, id);
        notifyItemInserted(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_finefare_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        View itemView = holder.itemView;
        itemView.setTag(position);
        setListener(holder);
        mCallBack.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    private void setListener(SimpleViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onItemClickListener();
            }
        });

    }

    public interface CallBack {

        void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

        void onItemClickListener();
    }
}
