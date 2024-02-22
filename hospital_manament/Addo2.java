package com.example.hospital_manament;

public class Addo2 {
         String pMail, pName, phn, pAdrs, bg, gender, room, duration, bill, disease;

    public Addo2(String pMail, String pName, String phn, String pAdrs, String bg,
                 String gender, String room, String duration, String bill, String disease) {

        this.pMail = pMail;
        this.pName = pName;
        this.phn = phn;
        this.pAdrs = pAdrs;
        this.bg = bg;
        this.gender = gender;
        this.room = room;
        this.duration = duration;
        this.bill = bill;
        this.disease = disease;
    }

    @Override
    public String toString() {
        return pMail+"~"+pName+"~"+phn+"~"+pAdrs+"~"+bg+"~"+gender+"~"+room+"~"+duration+"~"+bill+"~"+disease+"\n";
    }
}
