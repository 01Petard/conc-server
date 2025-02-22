package com.hzx.conc.common.utils.date;

/**
 * @Author: wwf
 * @Date: 2019/6/27 10:47
 * @Description:
 */
public class MonthVo {

    private String startDate;
    private String endDate;

    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public MonthVo(String startDate, String endDate, String month) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.month = month;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MonthVo{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
