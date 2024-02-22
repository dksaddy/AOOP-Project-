package com.example.hospital_manament;

public class AmbuLog {
    String name, mail, phn, radrs, thana, pc, sadrs, price;

    public AmbuLog(String name, String mail, String phn, String radrs, String thana, String pc, String sadrs, String price) {
        this.name = name;
        this.mail = mail;
        this.phn = phn;
        this.radrs = radrs;
        this.thana = thana;
        this.pc = pc;
        this.sadrs = sadrs;
        this.price = price;
    }

    @Override
    public String toString() {
        return name+"~"+mail+"~"+phn+"~"+radrs +"~"+ thana+"~"+pc+"~"+sadrs+"~"+price+"\n";
    }
}
