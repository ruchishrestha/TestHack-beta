package com.coolapp.testhack;

/**
 * Created by Ruchi on 2015-05-30.
 */
public class RentDescTable {
    String ownadd1,ownadd2,ownname1,desc1;
    long phone1,mobileno1,rprice1;
    double lat,log;
    public RentDescTable(String ownname1, String ownadd1, String ownadd2, double lat, double log, long phone1, long mobileno1,String desc1,long rprice1) {
        super();
        this.ownname1 = ownname1;
        this.ownadd1=ownadd1;
        this.ownadd2=ownadd2;
        this.lat = lat;
        this.log = log;
        this.phone1 = phone1;
        this.mobileno1=mobileno1;
        this.desc1=desc1;
        this.rprice1=rprice1;
    }
    public RentDescTable() {
        super();
        this.ownname1 = null;
        this.lat= 0;
        this.log = 0;
        this.phone1 = 0;
        this.mobileno1=0;
        this.desc1=null;
        this.rprice1=0;
    }

    public String getownname1() {
        return ownname1;
    }

    public void setownname1(String ownname1) {
        this.ownname1 = ownname1;
    }

    public String getOwnadd1() {
        return ownadd1;
    }

    public void setOwnadd1(String ownname1) {
        this.ownadd1 = ownadd1;
    }

    public String getOwnadd2() {
        return ownadd2;
    }

    public void setOwnadd2(String ownname1) {
        this.ownadd2 = ownadd2;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public long getphone1() {
        return phone1;
    }

    public void setphone1(long phone1) {
        this.phone1 = phone1;
    }

    public long getmobileno1() {
        return mobileno1;
    }

    public void setmobileno1(long mobileno1) {
        this.mobileno1= mobileno1;
    }

    public String getdesc1() {
        return desc1;
    }

    public void setdesc1(String desc1) {
        this.desc1= desc1;
    }

    public long getRprice1() {
        return rprice1;
    }

    public void setRprice1(long rprice1) {
        this.rprice1= rprice1;
    }
}
