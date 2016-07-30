package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/8/2016.
 */

public class QuotationDetails {
    private String quot_id,quotBrand,quotModel,quotSerialNo,quotProductType,quotEmail,quotConsumer,quotCreate,quotCreatedBy,quotationStatus,
            quotService,quotServAmt,quotServSrp,quotServMark,quotApproved,quotChargeable,quotCurrency,quotReceivedFrom,quotCreatedOn,
            quotSentTo,quotUpdatedOn,quotUpdatedBy,quotStatusHistory;

    public QuotationDetails(){

    }
    public QuotationDetails(String quot_id,String quotBrand,String quotModel,String quotSerialNo,String quotProductType,String quotEmail,
                            String quotConsumer,String createdOn, String quotCreatedBy,String quotStatus,
                            String quotService,String quotApproved,String quotChargeable,//String quotCurrency,
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
        this.quotCreate = quotCreate;
        this.quotCreatedBy = quotCreatedBy;
        this.quotationStatus = quotationStatus;
        this.quotService = quotService;
      //  this.quotCurrency = quotCurrency;
        this.quotApproved = quotApproved;
        this.quotServAmt = quotServAmt;
        this.quotServSrp = quotServSrp;
        this.quotServMark =quotServMark;
        this.quotChargeable = quotChargeable;
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
    public String getQuotCreate(){
        return quotCreate;
    }
   /* public String getQuotCurrency(){
        return quotCurrency;
    }*/
    public String getQuotCreatedBy(){
        return  quotCreatedBy;
    }
    public String getQuotationStatus(){
        return quotationStatus;
    }
    public String getQuotService(){
        return quotService;
    }
    public String getQuotApproved(){
        return quotApproved;
    }
    public String getQuotServAmt(){
        return quotServAmt;
    }
    public String getQuotServSrp(){
        return quotServSrp;
    }
    public String getQuotServMark(){
        return quotServMark;
    }

    public String getQuotChargeable(){
        return  quotChargeable;
    }
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
    public void setQuotCreatedBy(String quotCreatedBy){
        this.quotCreatedBy = quotCreatedBy;
    }
    public void setQuotationStatus(String quotationStatus){
        this.quotationStatus = quotationStatus;
    }
    public void setQuotService(String quotService){
        this.quotService = quotService;
    }
   public void setQuotCreate(String quotCreate){
        this.quotCreate = quotCreate;
    }
    public void setQuotServAmt(String quotServAmt){
        this.quotServAmt = quotServAmt;
    }
    public void setQuotServSrp(String quotServSrp){
        this.quotServSrp = quotServSrp;
    }
    public void setQuotServMark(String quotServMark){
        this.quotServMark =quotServMark;
    }
    public void setQuotApproved(String quotApproved){
        this.quotApproved = quotApproved;
    }
    public void setQuotChargeable(String quotChargeable){
        this.quotChargeable = quotChargeable;
    }
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


