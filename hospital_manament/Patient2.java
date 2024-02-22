package com.example.hospital_manament;

public class Patient2 {
    private String n, e, hc, med, bill;


    public Patient2() {
    }

    public Patient2(String n, String e, String hc, String med, String bill) {
        this.n = n;
        this.e = e;
        this.hc = hc;
        this.med = med;
        this.bill = bill;
    }

    @Override
    public String toString() {
        return n+"~"+e+"~"+hc+"~"+med+"~"+bill+"\n";
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

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
