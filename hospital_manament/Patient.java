package com.example.hospital_manament;

public class Patient {
    String name;
    String dob;
    String gender;
    String bloodGr;
    String address;
    String phNo;
    String email;
    String pass;


    public Patient(String name, String phNo, String address, String email) {
        this.name = name;
        this.phNo = phNo;
        this.address = address;
        this.email = email;
    }

    public Patient(String name, String dob, String gender, String bloodGr, String address, String phNo,
                   String email, String pass) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodGr = bloodGr;
        this.address = address;
        this.phNo = phNo;
        this.email = email;
        this.pass = pass;

    }

    public Patient() {
    }

    public Patient(String name, String bloodGr, String dob, String phNo, String email) {
        this.name = name;
        this.bloodGr = bloodGr;
        this.dob = dob;
        this.phNo = phNo;
        this.email = email;
    }

    @Override
    public String toString() {
        return name+"~"+dob+"~"+gender+"~"+bloodGr+"~"+address+"~"+phNo+"~"+email+"~"+pass+"\n";
    }


}
