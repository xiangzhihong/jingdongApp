package com.yju.app.shihui.welfare.bean;

import java.util.List;

/**
 * Created by user on 2016/8/26.
 * 专卖
 */
public class SpecialSaleEntity {

    public LiveBeanEityty live;

    public static class LiveBeanEityty {
        public int nums;
        public int products;
        public int visitors;
        public String pic;
        public int type;
        public int countries;
        public int sellers;
        public String name;

        public List<LivesBean> lives;

        public static class LivesBean {
            public int id;
            public String pic;
            public long endtime;
            public String content;
            public int visitors;
            public String country;
            public String sellername;
            public boolean hasvedio;
        }
    }
}
