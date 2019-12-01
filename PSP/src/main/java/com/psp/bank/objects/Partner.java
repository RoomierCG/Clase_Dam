package com.psp.bank.objects;

public class Partner {

    int carnetNumber;
    int carnetCash;

    public int getCarnetNumber() { return carnetNumber; }
    public void setCarnetNumber(int carnetNumber) {
        this.carnetNumber = carnetNumber;
    }

    public int getCarnetCash() {
        return carnetCash;
    }
    public void setCarnetCash(int carnetCash) {
        this.carnetCash = carnetCash;
    }

    public Partner(int carnetNumber) {
        this.carnetNumber = carnetNumber;
        this.carnetCash = 300;
    }
}
