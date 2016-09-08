/*******************************************************************************
 * Copyright 2012 momock.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.yju.app.base.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewHelper {

	private static void cleanViewGroup(View view) {
		if (view != null) {
			if (view instanceof ViewGroup)
				try {
					ViewGroup viewGroup = (ViewGroup) view;
					int childCount = viewGroup.getChildCount();
					for (int index = 0; index < childCount; index++) {
						View child = viewGroup.getChildAt(index);
						cleanViewGroup(child);
					}
				} catch (Exception e) {
				}

			cleanView(view);
		}
	}

	private static void cleanView(View view) {
		try {
			view.setBackgroundDrawable(null);
		} catch (Exception e) {
		}

		if (view instanceof ImageView)
			try {
				ImageView imageView = (ImageView) view;
				imageView.setImageDrawable(null);
			} catch (Exception e) {
			}
	}
	public static void clean(View view){
		cleanViewGroup(view);
	}
}
