package com.example.hospital_manament;

public class AddoForRelease {

    private String n;
    private String e;
    private String p;
    private String a;
    private String g;
    private String b;
    private String d;
    private String r;
    private String day;
    private String due;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }


    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public AddoForRelease(String n, String e, String p, String a, String g, String b,
                          String d, String r, String day, String due) {
        this.n = n;
        this.e = e;
        this.p = p;
        this.a = a;
        this.g = g;
        this.b = b;
        this.d = d;
        this.r = r;
        this.day = day;
        this.due = due;
    }

    @Override
    public String toString() {
        return e+"~"+n+"~"+p+"~"+a+"~"+b+"~"+g+"~"+r+"~"+day+"~"+due+"~"+d+"\n";
    }
}
