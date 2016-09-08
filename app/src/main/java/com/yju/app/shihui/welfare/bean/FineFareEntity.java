package com.yju.app.shihui.welfare.bean;

import java.util.List;

/**
 * Created by user on 2016/8/25.
 */
public class FineFareEntity {

    public List<PanicBean> panic;

    public static class PanicBean {
        public String id;
        public long endtime;
        public String pic;
        public int type;
        public String href;
        public String title;
        public String share;
    }
}
