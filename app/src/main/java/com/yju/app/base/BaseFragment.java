package com.yju.app.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;


public class BaseFragment extends Fragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	protected void cancelLoadingDialog() {
		if (!getActivity().isFinishing()) {
		}
	}
}