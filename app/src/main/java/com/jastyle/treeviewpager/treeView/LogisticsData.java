package com.jastyle.treeviewpager.treeView;

public class LogisticsData {

    private String title;//标题
    private String context;//内容
    private String ftime;
    private String time;//时间

    public String getTitle() {
        return title;
    }

    public LogisticsData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContext() {
        return context;
    }

    public LogisticsData setContext(String context) {
        this.context = context;
        return this;
    }

    public String getFtime() {
        return ftime;
    }

    public LogisticsData setFtime(String ftime) {
        this.ftime = ftime;
        return this;

    }

    public String getTime() {
        return time;
    }

    public LogisticsData setTime(String time) {
        this.time = time;
        return this;
    }


    @Override
    public String toString() {
        return "LogisticsData{" +
                "context='" + context + '\'' +
                ", ftime='" + ftime + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}