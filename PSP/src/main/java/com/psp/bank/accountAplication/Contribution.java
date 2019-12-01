package com.psp.bank.accountAplication;

public class Contribution {

    private int contributionQuantity = 10000;

    public int firtsContribution(){
        return contributionQuantity += 100;
    }
    public int secondContribution(){
        return contributionQuantity += 200;
    }
    public int thirdContribution(){
        return contributionQuantity -= 300;
    }
}
