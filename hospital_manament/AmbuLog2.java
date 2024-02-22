package com.example.hospital_manament;

public class AmbuLog2 {
    private String n;
    private String e;
    private String p;
    private String ra;
    private String t;
    private String pc;
    private String sa;
    private String b;

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

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public AmbuLog2(String n, String e, String p, String ra, String t, String pc, String sa, String b) {
        this.n = n;
        this.e = e;
        this.p = p;
        this.ra = ra;
        this.t = t;
        this.pc = pc;
        this.sa = sa;
        this.b = b;
    }

    @Override
    public String toString() {
        return n+"~"+e+"~"+p+"~"+ra+"~"+t+"~"+pc+"~"+sa+"~"+b+"\n";
    }

}
