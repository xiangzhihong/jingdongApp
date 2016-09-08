package com.yju.app.shihui.welfare.bean;

import java.util.List;

/**
 * Created by user on 2016/8/24.
 */
public class BannerEntity {

    public OperationBean operation;

    public List<BannerBean> banner;

    public static class OperationBean {

        public ActivityBean activity;

        public List<ListBean> list;

        public static class ActivityBean {
            public int id;
            public int type;
            public String pic;
            public String href;
            public String spic;
            public String title;
            public String sharelink;
        }

        public static class ListBean {
            public int id;
            public int type;
            public String pic;
            public String href;
            public String title;
            public String sharelink;
        }
    }

    public static class BannerBean {
        public int id;
        public int type;
        public String pic;
        public String href;
        public String title;
        public String sharelink;
    }
}
