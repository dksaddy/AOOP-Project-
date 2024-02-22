package com.example.hospital_manament;

public class Doctor {
    String name;
    String dob;
    String gender;
    String bloodGr;
    String department;
    String phNo;
    String email;
    String pass;


    @Override
    public String toString() {
        return name+"~"+dob+"~"+gender+"~"+bloodGr+"~"+department+"~"+phNo+"~"+email+"~"+pass+"\n";
    }



    public Doctor() {
    }



    public Doctor(String name, String dob, String gender, String bloodGr, String department,
                  String phNo, String email, String pass)
    {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodGr = bloodGr;
        this.department = department;
        this.phNo = phNo;
        this.email = email;
        this.pass = pass;
    }


}
