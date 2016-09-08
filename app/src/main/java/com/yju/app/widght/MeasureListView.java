package com.yju.app.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MeasureListView extends ListView {
	public MeasureListView(Context context) {
		super(context);
	}

	public MeasureListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
