/*
    The Android Open Source Project
    Copyright (c) 2014-7-22 wangzheng <iswangzheng@gmail.com>

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    @author wangzheng  DateTime 2014-7-22
 */

package com.yju.app.widght.autoviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


import com.yju.app.R;

import java.util.TimerTask;

@SuppressLint("NewApi")
public class ViewPagerIndicator extends LinearLayout {

	private ViewPager vp = null;
	private CirclePageIndicator cpi = null;
//	private Timer timer = null;
	private int index = 0;
	private boolean isScroll = false;
	private boolean isActive = false;

	public ViewPagerIndicator(Context context) {
		this(context,null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (vp == null)
			init(context);
	}

	public ViewPagerIndicator(Context context, int layoutId) {
		super(context);
		if (vp == null)
			init(context, layoutId);
	}

	private void init(Context context) {// viewpager_indicator_layout
		init(context, -1);
	}

	private void init(Context context, int layoutId) {
		if (layoutId == -1) {
			inflate(context, R.layout.viewpager_indicator_layout, this);
		} else {
			inflate(context, layoutId, this);
		}
		vp = (ViewPager) findViewById(R.id.pager);
		cpi = (CirclePageIndicator) findViewById(R.id.indicator);
//		timer = new Timer();
	}

	private int pagerCount = 0;

	public void setAdapter(PagerAdapter adapter) {
		if (adapter != null) {
			vp.setAdapter(adapter);
			cpi.setViewPager(vp);
			cpi.setOnPageChangerListener(listener);
			cpi.setCurrentItem(0);
			index = 0;
			if (!isActive) {
				try {
//					timer.schedule(new Tasker(), 3000, 5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				isActive = true;
			}
			pagerCount = adapter.getCount();
			if (pagerCount == 1)
				cpi.setVisibility(View.GONE);
			else
				cpi.setVisibility(View.VISIBLE);
		}
	}

	class Tasker extends TimerTask {
		@Override
		public void run() {
			if (!isScroll) {
				post(new Runnable() {
					public void run() {
						vp.setCurrentItem(index);
						++index;
						if (index > pagerCount - 1)
							index = 0;
					}
				});
			}
		}
	}

	private OnPageChangeListener listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			index = position;
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			switch (state) {
			case 0:
				isScroll = false;
				break;
			case 1:
				isScroll = true;
				break;
			case 2:
				isScroll = false;
				break;

			default:
				break;
			}
		}
	};

	public void stop() {
		isScroll = true;
	}

	public void reStart() {
		isScroll = false;
	}

	public void destory() {
//		timer.cancel();
		isActive = false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isScroll = true;
			break;
		case MotionEvent.ACTION_UP:
			isScroll = false;
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}


	public void setViewPagerMinHeight(int minHeight){
		if (vp!=null)
        vp.setMinimumHeight(minHeight);
	}
	public void setOffscreenPageLimit(int siez){
		if (vp!=null)
			vp.setOffscreenPageLimit(siez);
	}

}
