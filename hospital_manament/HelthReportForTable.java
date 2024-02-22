package com.example.hospital_manament;

public class HelthReportForTable {

    private String n;
    private String e;
    private String bg;
    private String age;
    private String rev;
    private String se;
    private String sed;

    public HelthReportForTable(String n, String e, String bg, String age, String rev, String se, String sed) {
        this.n = n;
        this.e = e;
        this.bg = bg;
        this.age = age;
        this.rev = rev;
        this.se = se;
        this.sed = sed;
    }

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

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getSed() {
        return sed;
    }

    public void setSed(String sed) {
        this.sed = sed;
    }


    @Override
    public String toString() {
        return n+"~"+e+"~"+bg+"~"+age+"~"+rev+"~"+se+"~"+sed+"\n";
    }
}
