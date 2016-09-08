package com.yju.app.shihui.welfare.view.lamp;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yju.app.utils.UIUtils;

/**
 * 横向跑马灯
 */
public class HorizonScrollTextView extends TextView{

    private Context mContext=null;
	private boolean mStopMarquee=false;
    private String mText;  
    private float mCoordinateX;  
    private float mTextWidth;

    public HorizonScrollTextView(Context context) {
        super(context);
       this.mContext=context;
    }
   
    public HorizonScrollTextView(Context context, AttributeSet attrs) {  
        super(context, attrs);
        this.mContext=context;
    }


    public void setText(String text) {
        this.mText = text;  
        mTextWidth = getPaint().measureText(mText);  
        if (mHandler.hasMessages(0))  
            mHandler.removeMessages(0);  
        mHandler.sendEmptyMessageDelayed(0, 500);
    }  
   
    
    @Override 
    protected void onAttachedToWindow() {  
        mStopMarquee = false;  
        if (!(mText == null || mText.isEmpty()))  
            mHandler.sendEmptyMessageDelayed(0, 500);
        super.onAttachedToWindow();  
    }  
   
   
    @Override 
    protected void onDetachedFromWindow() {  
        mStopMarquee = true;  
        if (mHandler.hasMessages(0))  
            mHandler.removeMessages(0);  
        super.onDetachedFromWindow();  
    }  
   
    @Override
    protected void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        if (!(mText == null || mText.isEmpty()))  
            canvas.drawText(mText, mCoordinateX, UIUtils.dp2px(mContext,14), getPaint());
    }  
   
    private Handler mHandler = new Handler() {  
        @Override 
        public void handleMessage(Message msg) {  
            switch (msg.what) {  
            case 0:  
                if (Math.abs(mCoordinateX) > (mTextWidth + 5)) {  
                    mCoordinateX = 0;  
                    invalidate();  
                    if (!mStopMarquee) {  
                        sendEmptyMessageDelayed(0,500);  
                    }  
                } else {  
                    mCoordinateX -= 1;  
                    invalidate();  
                    if (!mStopMarquee) {  
                        sendEmptyMessageDelayed(0, 30);  
                    }  
                }  
                break;  
            }  
            super.handleMessage(msg);  
        }  
    }; 
}
