package com.example.demo.bean;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by MrDing
 * Date: 2017/3/18.
 * Time:19:26
 */
public class QueryConditon implements Serializable {

    private Date fromDate;

    private Date endDate;

    private int page;

    private int rows;


    public static QueryConditon create() {
        return new QueryConditon();
    }

    public QueryConditon fromDate(Date fromDate) {
        setFromDate(fromDate);
        return this;
    }

    public QueryConditon endDate(Date endDate) {
        setEndDate(endDate);
        return this;
    }


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "QueryConditon{" +
                "fromDate=" + fromDate +
                ", endDate=" + endDate +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
