package com.psp.bank.objects;

public class Partner {

    int parnetNumber;
    int parnetCash = 300;

    public int getParnetNumber() { return parnetNumber; }
    public void setParnetNumber(int parnetNumber) {
        this.parnetNumber = parnetNumber;
    }

    public int getParnetCash() {
        return parnetCash;
    }
    public void setParnetCash(int parnetCash) {
        this.parnetCash = parnetCash;
    }

    public Partner(int parnetNumber, int parnetCash) {
        this.parnetNumber = parnetNumber;
        this.parnetCash = parnetCash;
    }
}
