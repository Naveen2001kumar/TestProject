package com.base.msgapplication.Dto;
 import java.util.Date;

public class SummaryReport {

    private int id;
  private Date  date ;
  private String vendorName;
  private String status;
  private int count;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SummaryReport(int id, Date date, String vendorName, String status, int count) {
        this.id = id;
        this.date = date;
        this.vendorName = vendorName;
        this.status = status;
        this.count = count;
    }
    public SummaryReport(){

    }
}
