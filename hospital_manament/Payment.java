package com.example.hospital_manament;

public class Payment {
    String email, due;

    public Payment(String email, String due) {
        this.email = email;
        this.due = due;
    }

    @Override
    public String toString() {
        return email+"~"+due+"\n";
    }
}
