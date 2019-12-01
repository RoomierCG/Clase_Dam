package com.psp.bank.accountAplication;

public class IncrementThread implements Runnable{

    private Contribution contributionQuantity;

    public IncrementThread(Contribution contributionQuantity) {
        this.contributionQuantity = contributionQuantity;
    }

    public void run() {
    }
}
