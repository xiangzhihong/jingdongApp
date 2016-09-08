package com.yju.app.shihui.welfare.bean;

import java.util.List;

/**
 * Created by user on 2016/9/1.
 * 时速达entity
 */
public class SpeedHourEntity {

    public TopicBean topic;

    public static class TopicBean {
        public long nextupdatetime;
        public List<ItemsBean> items;

        public static class ItemsBean {
            public int id;
            public String theme;
            public int products;
            public int users;
            public String href;
            public boolean follow;
            public int topictype;

            public List<ListBean> list;

            public static class ListBean {
                public String id;
                public int price;
                public String pic;
            }
        }
    }
}
