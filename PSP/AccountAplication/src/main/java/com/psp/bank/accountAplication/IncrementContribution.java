package com.psp.bank.accountAplication;

public class IncrementContribution implements Runnable{

    private Contribution contributionQuantity;
    private int partnerNumber;

    public IncrementContribution(Contribution contributionQuantity, int partnerNumber) {
        this.contributionQuantity = contributionQuantity;
        this.partnerNumber = partnerNumber;
    }

    public void run() {
    }
}
