package com.example.hospital_manament;

public class AppoPatient {

    private String n;
    private String m;
    private String hi;
    private String pm;
    private String bills;


    public AppoPatient() {
    }


    public AppoPatient(String n, String m, String hi, String pm,  String bills) {
        this.n = n;
        this.m = m;
        this.hi = hi;
        this.pm = pm;
        this.bills = bills;
    }

    @Override
    public String toString() {
        return n+"~"+m+"~"+hi+"~"+pm+"~"+bills+"\n";
    }



    public String getBills() {
        return bills;
    }

    public void setBills(String bills) {
        this.bills = bills;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

}
