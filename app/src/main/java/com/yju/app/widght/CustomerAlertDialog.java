
package com.yju.app.widght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.utils.UIUtils;


public class CustomerAlertDialog implements View.OnClickListener, DialogInterface.OnDismissListener {

    private View parent = null;
    private TextView title = null;
    private TextView content = null;
    private TextView cancel = null;
    private TextView submit = null;
    private TextView confirm = null;
    private FrameLayout container = null;

    protected Context mContext = null;
    protected Dialog mDialog = null;
    protected Window mWindow = null;
    // 0取消·确定 简约风格； 1 确定 简约风格； 2取消·确定 主题风格； 3 确定 主题风格
    protected int windowStyleType = 0;
    protected LayoutInflater inflaterFactory = null;

    protected OnClickButtonListener onClickListener = null;
    protected View onClickView = null;
    protected Object mData = null;

    public CustomerAlertDialog(Context context) {
        this(context, 2);
    }

    public CustomerAlertDialog(Context context, int windowStyleType) {
        this.mContext = context;
        this.windowStyleType = windowStyleType;
        init();
    }


    public void show(View v) {
        if (mContext instanceof Activity && ((Activity) mContext).isFinishing()) {
            return;
        }
        onClickView = v;
        mDialog.show();
    }

    private void init() {
        inflaterFactory = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
        initDialog();
    }

    private void initDialog() {
        mDialog = new Dialog(mContext, R.style.style_dialog);
        mDialog.setContentView(parent);
        mDialog.setOnDismissListener(this);
        mWindow = mDialog.getWindow();
//        mWindow.setWindowAnimations(R.style.centerInCenterOutStyle);
        int width = (int) (UIUtils.getScreenWidth(mContext) * (3.0f / 4.0));
        mWindow.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void initView() {
        int layoutId = R.layout.layout_dialog;
        parent = inflaterFactory.inflate(layoutId, null);
        title = UIUtils.getView(parent, R.id.title);
        content = UIUtils.getView(parent, R.id.content);
        cancel = UIUtils.getView(parent, R.id.cancel_view);
        cancel.setOnClickListener(this);
        submit = UIUtils.getView(parent, R.id.submit);
        submit.setOnClickListener(this);
        View operationBar = UIUtils.getView(parent, R.id.operation_bar);
        operationBar.setVisibility(windowStyleType == 0 || windowStyleType == 2 ? View.VISIBLE : View.GONE);
        confirm = UIUtils.getView(parent, R.id.confirm);
        confirm.setVisibility(windowStyleType == 0 || windowStyleType == 2 ? View.GONE : View.VISIBLE);
        confirm.setOnClickListener(this);
        container = UIUtils.getView(parent, R.id.container);

//        if (Build.VERSION.SDK_INT <= 11 && windowStyleType == 0) {
//            cancel.setBackgroundResource(R.drawable.alert_bg);
//            submit.setBackgroundResource(R.drawable.left_around_bg);
//        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
         if (dialog!=null)
             dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit) {
            submit(v);
        } else if (v.getId() == R.id.confirm) {
            submit(v);
        } else if (v.getId() == R.id.cancel_view) {
            cancel(v);
        }
    }

    public void submit(View v) {
        mDialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(onClickView, ClickType.CONFIRM);
        }
    }

    public void cancel(View v) {
        mDialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(onClickView, ClickType.CANCEL);
        }
    }

    public CustomerAlertDialog setContentView(View view) {
        if (view != null) {
            container.addView(view);
        }
        return this;
    }

    public CustomerAlertDialog setCancelable(boolean bool) {
        mDialog.setCancelable(bool);
        return this;
    }

    public CustomerAlertDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public CustomerAlertDialog setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            this.title.setText(title);
        }
        return this;
    }

    public CustomerAlertDialog setContent(CharSequence content) {
        if (!TextUtils.isEmpty(content)) {
            this.content.setText(content);
            this.content.setVisibility(View.VISIBLE);
        }else {
            this.content.setVisibility(View.GONE);
        }
        return this;
    }

    public CustomerAlertDialog setSubmitName(CharSequence name) {
        if (!TextUtils.isEmpty(name)) {
            submit.setText(name);
            confirm.setText(name);
        }
        return this;
    }

    public CustomerAlertDialog setCancelName(CharSequence name) {
        if (!TextUtils.isEmpty(name)) {
            cancel.setText(name);
        }
        return this;
    }

    public void show() {
        show(mWindow.getDecorView());
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getConfirm() {
        return confirm;
    }

    public TextView getCancel() {
        return cancel;
    }

    public TextView getSubmit() {
        return submit;
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    public CustomerAlertDialog setOnClickListener(OnClickButtonListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public interface OnClickButtonListener {
        void onClick(View v, ClickType type);
    }

    public enum ClickType {
        CONFIRM, CANCEL, UNKNOWN;

        @Override
        public String toString() {
            return name();
        }
    }

    public <T extends Object> T getData() {
        return (T) mData;
    }

    public void setData(Object obj) {
        this.mData = obj;
    }

}
