package com.yju.app.base.manager;

import android.app.Activity;

import java.util.Stack;

public final class ActivityManager {
    private final static Stack<Activity> activityStack = new Stack<>();

    public static void popActivity() {
        Activity activity = activityStack.pop();
        if (activity != null) {
            activity.finish();
        }
    }

    public static void popActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public static void pushActivity(Activity activity) {
        activityStack.push(activity);
    }

    public static Activity currentActivity() {
        return activityStack.peek();
    }

    public static void popAllActivity() {
        while (!activityStack.empty()){
            popActivity();
        }
    }
}
