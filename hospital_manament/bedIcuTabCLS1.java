package com.example.hospital_manament;

public class bedIcuTabCLS1 {
    private String icr;

    public bedIcuTabCLS1(String icr) {
        this.icr = icr;
    }

    public String getIcr() {
        return icr;
    }

    public void setIcr(String icr) {
        this.icr = icr;
    }

    @Override
    public String toString() {
        return icr+"\n";
    }
}
