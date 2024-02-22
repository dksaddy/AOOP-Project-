package com.example.hospital_manament;

public class Patient1 {
     String n, e, p, a, dn, de, dd, phc, bill;

    @Override
    public String toString() {
        return n+"~"+e+"~"+p+"~"+a+"~"+dn+"~"+de+"~"+dd+"~"+phc+"~"+bill+"\n";
    }

    public Patient1(String n, String e, String p, String a, String dn, String de, String dd, String phc, String bill) {
        this.n = n;
        this.e = e;
        this.p = p;
        this.a = a;
        this.dn = dn;
        this.de = de;
        this.dd = dd;
        this.phc = phc;
        this.bill = bill;
    }

    public Patient1() {
    }
}
