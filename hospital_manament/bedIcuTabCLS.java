package com.example.hospital_manament;

public class bedIcuTabCLS {
    private String hd;

    public bedIcuTabCLS(String hd) {
        this.hd = hd;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    @Override
    public String toString() {
        return hd+"\n";
    }

}

