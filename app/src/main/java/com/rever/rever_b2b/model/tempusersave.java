package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 7/16/2016.
 */
public class tempusersave {

    private int user_id;
    private String companyId1, userSessionToken1, userFirstName1, userCountryCode1,
            userCity1, userEmail1, userType1,userLastName1,userPostal1,userAddLine2,userMobile1,userAddLine1,userMiddleName1,userState1;

    public tempusersave(int user_id,String companyId1,String userSessionToken1,String userFirstName1,String userCountryCode1,
                        String userCity1, String userEmail1, String userType1,String userLastName1,String userPostal1,String userAddLine2,String userMobile1,
                        String userAddLine1,String userMiddleName1,String userState1){
        this.user_id = user_id;
        this.companyId1 = companyId1;
        this.userSessionToken1 = userSessionToken1;
        this.userFirstName1 = userFirstName1;
        this.userCountryCode1 = userCountryCode1;
        this.userCity1 = userCity1;
        this.userEmail1 = userEmail1;
        this.userType1 = userType1;
        this.userLastName1 =userLastName1;
        this.userPostal1 =userPostal1;
        this.userAddLine2 =userAddLine2;
        this.userMobile1 =userMobile1;
        this.userAddLine1 =userAddLine1;
        this.userMiddleName1 =userMiddleName1;
        this.userState1 =userState1;


    }

    public int getuser_id(){ return user_id; }
    public String getcompanyId1(){return companyId1;}
    public String getuserSessionToken1(){return userSessionToken1;}
    public String getuserFirstName1(){return userFirstName1;}
    public String getuserCountryCode1(){return userCountryCode1;}
    public String getuserCity1(){return userCity1;}
    public String getuserEmail1(){return userEmail1;}
    public String getuserType1(){return userType1;}
    public String getuserLastName1(){return userLastName1;}
    public String getuserPostal1(){return userPostal1;}
    public String getuserAddLine2(){return userAddLine2;}
    public String getuserMobile1(){return userMobile1;}
    public String getuserAddLine1(){return userAddLine1;}
    public String getuserMiddleName1(){return userMiddleName1;}
    public String getuserState1(){return userState1;}

    public void setuserState1(String userState1){ this.userState1 = userState1; }
    public void setuser_id(int user_id){ this.user_id = user_id; }
    public void setcompanyId1(String companyId1){this.companyId1 = companyId1;}
    public void setuserSessionToken1(String userSessionToken1){this.userSessionToken1 = userSessionToken1;}
    public void setuserFirstName1(String userFirstName1){this.userFirstName1 = userFirstName1;}
    public void setuserCountryCode1(String userCountryCode1){this.userCountryCode1 = userCountryCode1;}
    public void setuserCity1(String userCity1){this.userCity1 = userCity1;}
    public void setuserEmail1(String userEmail1){this.userEmail1 = userEmail1;}
    public void setuserType1(String userType1){this.userType1 = userType1;}
    public void setuserLastName1(String userLastName1){this.userLastName1 = userLastName1;}
    public void setuserPostal1(String userPostal1){this.userPostal1 = userPostal1;}
    public void setuserAddLine2(String userAddLine2){this.userAddLine2 = userAddLine2;}
    public void setuserMobile1(String userMobile1){this.userMobile1 = userMobile1;}
    public void setuserAddLine1(String userAddLine1){this.userAddLine1 = userAddLine1;}
    public void setuserMiddleName1(String userMiddleName1){this.userMiddleName1 = userMiddleName1;}
}
