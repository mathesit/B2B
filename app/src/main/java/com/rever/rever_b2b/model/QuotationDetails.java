package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/8/2016.
 */


public class QuotationDetails {
    private String quot_id,quotBrand,quotModel,quotSerialNo,quotProductType,quotEmail,quotConsumer,createdOn,quotCreatedBy,quotStatus,
            quotService,quotServAmt,quotServSrp,quotServMark,quotCurrency,quotReceivedFrom,quotCreatedOn,quotSentTo,quotUpdatedOn,quotUpdatedBy,quotStatusHistory;
  //  quotOnsite,quotOnsiteAmt,quotOnsiteSrp,quotOnsiteMark,
    public QuotationDetails(){

    }
    public QuotationDetails(String quot_id,String quotBrand,String quotModel,String quotSerialNo,String quotProductType,String quotEmail,
                            String quotConsumer,String createdOn, String quotCreatedBy,String quotStatus,
                            String quotService,//String quotCurrency,
                            String quotServAmt,String quotServSrp,String quotServMark, String quotReceivedFrom,String quotCreatedOn,
                            String quotSentTo,String quotUpdatedOn,String quotUpdatedBy, String quotStatusHistory) {
                                    //String quotOnsite,String quotOnsiteSrp,String quotOnsiteMark,String quotOnsiteAmt,
        this.quot_id = quot_id;
        this.quotBrand = quotBrand;
        this.quotModel = quotModel;
        this.quotSerialNo = quotSerialNo;
        this.quotProductType = quotProductType;
        this.quotEmail = quotEmail;
        this.quotConsumer = quotConsumer;
        this.createdOn = createdOn;
        this.quotCreatedBy = quotCreatedBy;
        this.quotStatus = quotStatus;
        this.quotService = quotService;
      //  this.quotCurrency = quotCurrency;
      //  this.quotOnsite = quotOnsite;
        this.quotServAmt = quotServAmt;
        this.quotServSrp = quotServSrp;
        this.quotServMark =quotServMark;
        /*this.quotOnsiteAmt = quotOnsiteAmt;
        this.quotOnsiteSrp = quotOnsiteSrp;
        this.quotOnsiteMark = quotOnsiteMark;*/
        this.quotReceivedFrom = quotReceivedFrom;
        this.quotCreatedOn = quotCreatedOn;
        this.quotSentTo = quotSentTo;
        this.quotUpdatedOn = quotUpdatedOn;
        this.quotUpdatedBy =  quotUpdatedBy;
        this.quotStatusHistory =  quotStatusHistory;
    }

    public String getQuot_id(){
        return quot_id;
    }
    public String getQuotBrand(){
        return quotBrand;
    }
    public String getQuotModel(){
        return quotModel;
    }
    public String getQuotSerialNo(){
        return quotSerialNo;
    }
    public String getQuotProductType(){
        return quotProductType;
    }
    public String getQuotEmail(){
        return quotEmail;
    }
    public String getQuotConsumer(){
        return quotConsumer;
    }
    public String getCreatedOn(){
        return createdOn;
    }
   /* public String getQuotCurrency(){
        return quotCurrency;
    }*/
    public String getQuotCreatedBy(){
        return  quotCreatedBy;
    }
    public String getQuotStatus(){
        return quotStatus;
    }
    public String getQuotService(){
        return quotService;
    }
   /* public String getQuotOnsite(){
        return quotOnsite;
    }*/
    public String getQuotServAmt(){
        return quotServAmt;
    }
    public String getQuotServSrp(){
        return quotServSrp;
    }
    public String getQuotServMark(){
        return quotServMark;
    }

 /*   public String getQuotOnsiteAmt(){
        return  quotOnsiteAmt;
    }
    public String getQuotOnsiteSrp(){
        return quotOnsiteSrp;
    }
    public String getQuotOnsiteMark(){
        return quotOnsiteMark;
    }*/
    public String getQuotReceivedFrom(){
        return quotReceivedFrom;
    }
    public String getQuotCreatedOn(){
        return quotCreatedOn;
    }
    public String getQuotSentTo(){
        return quotSentTo;
    }
    public String getQuotUpdatedOn(){
        return  quotUpdatedOn;
    }
    public String getQuotUpdatedBy(){
        return quotUpdatedBy;
    }
    public String getQuotStatusHistory(){
        return  quotStatusHistory;
    }
    public void setQuot_id(String quot_id){
        this.quot_id = quot_id;
    }
    public void setQuotBrand(String quotBrand){
        this.quotBrand = quotBrand;
    }
  /*  public void setQuotCurrency(String quotCurrency){
        this.quotCurrency = quotCurrency;
    }*/
    public void setQuotModel(String quotModel){
        this.quotModel = quotModel;
    }
    public void setQuotSerialNo(String quotSerialNo){
        this.quotSerialNo = quotSerialNo;
    }
    public void setQuotProductType(String quotProductType){
        this.quotProductType = quotProductType;
    }
    public void setQuotEmail(String quotEmail){
        this.quotEmail = quotEmail;
    }
    public void setQuotConsumer(String quotConsumer){
        this.quotConsumer = quotConsumer;
    }
    public void setCreatedOn(String createdOn){
        this.createdOn = createdOn;
    }
    public void setQuotCreatedBy(String quotCreatedBy){
        this.quotCreatedBy = quotCreatedBy;
    }
    public void setQuotStatus(String quotStatus){
        this.quotStatus = quotStatus;
    }
    public void setQuotService(String quotService){
        this.quotService = quotService;
    }
  /*  public void setQuotOnsite(String quotOnsite){
        this.quotOnsite = quotOnsite;
    }*/
    public void setQuotServAmt(String quotServAmt){
        this.quotServAmt = quotServAmt;
    }
    public void setQuotServSrp(String quotServSrp){
        this.quotServSrp = quotServSrp;
    }
    public void setQuotServMark(String quotServMark){
        this.quotServMark =quotServMark;
    }
    /*public void setQuotOnsiteAmt(String quotOnsiteAmt){
        this.quotOnsiteAmt = quotOnsiteAmt;
    }
    public void setQuotOnsiteSrp(String quotOnsiteSrp){
        this.quotOnsiteSrp = quotOnsiteSrp;
    }
    public void setQuotOnsiteMark(String quotOnsiteMark){
        this.quotOnsiteMark = quotOnsiteMark;
    }*/
    public void setQuotReceivedFrom(String quotReceivedFrom){
        this.quotReceivedFrom = quotReceivedFrom;
    }
    public void setQuotCreatedOn(String quotCreatedOn){
        this.quotCreatedOn = quotCreatedOn;
    }
    public void setQuotSentTo(String quotSentTo){
        this.quotSentTo = quotSentTo;
    }
    public void setQuotUpdatedOn(String quotUpdatedOn){
        this.quotUpdatedOn = quotUpdatedOn;
    }
    public void setQuotUpdatedBy(String quotUpdatedBy){
        this.quotUpdatedBy =  quotUpdatedBy;
    }
    public void setQuotStatusHistory(String quotStatusHistory){
        this.quotStatusHistory =  quotStatusHistory;
    }


}


