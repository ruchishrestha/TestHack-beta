package com.coolapp.testhack;

/**
 * Created by Ruchi on 2015-05-29.
 */
public class LocDescTable {
    String loc_det,loc_name,relief;
    int bol;
    double lat,log;

    public LocDescTable(String loc_name, double lat, double log, String loc_det, int bol) {
        super();
        this.loc_name = loc_name;
        this.lat = lat;
        this.log = log;
        this.loc_det = loc_det;
        this.bol=bol;
    }
    public LocDescTable() {
        super();
        this.loc_name = null;
        this.lat= 0;
        this.log = 0;
        this.loc_det = null;
        this.bol=0;

    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(Double log) {
        this.log = log;
    }

    public String getLoc_det() {
        return loc_det;
    }

    public void setLoc_det(String loc_det) {
        this.loc_det = loc_det;
    }

    public int getbol() {
        return bol;
    }

    public void setBol(int bol) {
        this.bol= bol;
    }

    public String getrelief() {
        return relief;
    }

    public void setrelief(String relief) {
        this.relief= relief;
    }
}
