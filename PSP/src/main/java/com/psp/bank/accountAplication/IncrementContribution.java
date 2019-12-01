package com.psp.bank.accountAplication;

public class IncrementContribution implements Runnable{

    private Contribution contributionQuantity;

    public IncrementContribution(Contribution contributionQuantity) {
        this.contributionQuantity = contributionQuantity;
    }

    public void run() {
        contributionQuantity.firtsContribution();
        contributionQuantity.secondContribution();
        contributionQuantity.thirdContribution();
    }
}
