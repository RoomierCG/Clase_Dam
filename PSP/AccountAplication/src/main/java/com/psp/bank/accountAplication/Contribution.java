package com.psp.bank.accountAplication;

public class Contribution {

    private int contributionQuantity = 10000;
    private int contribution = 0;

    public int firtsContribution(){
        contribution = 100;
        contributionQuantity += contribution;
        return contribution;
    }
    public int secondContribution(){
        contribution = 200;
        contributionQuantity += contribution;
        return contribution;
    }
    public int thirdContribution(){
        contribution = 300;
        contributionQuantity -= contribution;
        return contribution;
    }
    public int fourContribution(){
        return contributionQuantity;
    }
}
