package com.yju.app.shihui.estateservice.entity;

import java.util.List;

/**
 * Created by user on 2016/9/5.
 */
public class EstatePayEntity {

    public ResultBean result;
    public int apistatus;

    public static class ResultBean {
        public String advance_word;
        public ArrearageBean arrearage;
        public OwnerBean owner;
        public DueBean due;
        public String empty_word;
        public AdvanceBean advance;
    }

    public static class AdvanceBean {
        public double total;
        public int num;
        public List<DataBean> data;
    }

    public static class DataBean {
        public int index;
        public String end_time;
        public String due;
        public String end_time_string;
        public List<DetailBean> detail;
    }

    public static class DetailBean {
        public String fee_due;
        public String fee_name;
    }

    public static class DueBean {
        public double total;
        public int num;
        public List<DataBean> data;
    }

    public static class OwnerBean {
        public String address;
        public String name;
    }

    public static class ArrearageBean {
        public int total;
        public int num;
        public List<?> data;
    }

}
