package com.example.hospital_manament;

public class Appo2 {
    String pName, pMail, disease, med, bill;

    public Appo2(String pName, String pMail, String disease, String med, String bill) {
        this.pName = pName;
        this.pMail = pMail;
        this.disease = disease;
        this.med = med;
        this.bill = bill;
    }

    @Override
    public String toString() {
        return pName+"~"+pMail+"~"+disease+"~"+med+"~"+bill+"\n";
    }
}
