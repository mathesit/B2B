package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/25/2016.
 */
public class EWPendingQuote {
    private String quoteCount;

    public EWPendingQuote(String quoteCount) {
        this.quoteCount = quoteCount;
    }

    public String getQuoteCount(){
        return quoteCount;
    }
    public void setQuoteCount(String quoteCount){
        this.quoteCount = quoteCount;
    }
}
