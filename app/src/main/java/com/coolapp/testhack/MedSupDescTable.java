package com.coolapp.testhack;

/**
 * Created by Ruchi on 2015-05-30.
 */
public class MedSupDescTable {
    String titles,add1,add2,Desc;
    long Contact;
    double lat,log;

    public MedSupDescTable(String titles, String add1, String add2, long Contact, String Desc, double lat, double log) {
        super();
        this.titles = titles;
        this.add1=add1;
        this.add2=add2;
        this.Contact = Contact;
        this.Desc=Desc;
        this.lat = lat;
        this.log = log;
       
    }
    public MedSupDescTable() {
        super();
        this.titles = null;
        this.add1=null;
        this.add2=null;
        this.Contact = 0;
        this.Desc=null;
        this.lat=0;
        this.log=0;
    }

    public String gettitles() {
        return titles;
    }

    public void settitles(String titles) {
        this.titles = titles;
    }

    public String getadd1() {
        return add1;
    }

    public void setadd1(String titles) {
        this.add1 = add1;
    }

    public String getadd2() {
        return add2;
    }

    public void setadd2(String titles) {
        this.add2 = add2;
    }

    public long getContact() {
        return Contact;
    }

    public void setContact(long Contact) {
        this.Contact = Contact;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc= Desc;
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

}
