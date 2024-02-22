package com.example.hospital_manament;

public class Doctor1 {
    private String n,p,e,d;


    public Doctor1() {
    }

    public Doctor1(String n, String p, String e, String d) {
        this.n = n;
        this.p = p;
        this.e = e;
        this.d = d;
    }

    @Override
    public String toString() {
        return n+"~"+p+"~"+e+"~"+d+"\n";
    }


    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }


}
