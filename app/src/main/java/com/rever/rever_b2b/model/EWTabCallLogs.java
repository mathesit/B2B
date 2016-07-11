package com.rever.rever_b2b.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rever.rever_b2b.R;

import java.util.List;

/**
 * Created by Bharath on 6/15/2016.
 */
public class EWTabCallLogs {
    private String case_id;
    private String eq_stock_id, sr_id, consumer_id, consumer_name, consumer_mobile,
            consumer_email, case_status, created_on, call_category, sr_status, sr_no,
            created_by;
    public EWTabCallLogs
            (String case_id, String eq_stock_id,String sr_id, String consumer_id, String consumer_name, String consumer_mobile,
             String consumer_email, String case_status, String created_on, String call_category, String sr_status, String sr_no,
             String created_by) {
        this.case_id = case_id;
        this.eq_stock_id = eq_stock_id;
        this.sr_id = sr_id;
        this.consumer_id = consumer_id;
        this.consumer_name = consumer_name;
        this.consumer_mobile = consumer_mobile;
        this.consumer_email = consumer_email;
        this.case_status = case_status;
        this.created_on = created_on;
        this.call_category = call_category;
        this.sr_status = sr_status;
        this.sr_no = sr_no;
        this.created_by = created_by;
    }

    public String getcase_id() { return case_id;}
    public String geteq_stock_id() { return eq_stock_id;}
    public String getsr_id() { return sr_id;}
    public String getconsumer_id() { return consumer_id;}
    public String getconsumer_name() { return consumer_name;}
    public String getconsumer_mobile() { return consumer_mobile;}
    public String getconsumer_email() { return consumer_email;}
    public String getcase_status() { return case_status;}
    public String getcreated_on() { return created_on;}
    public String getcall_category() { return call_category;}
    public String getsr_status() { return sr_status;}
    public String getsr_no() { return sr_no;}
    public String getcreated_by() { return created_by;}

    public void setcase_id(String case_id) {this.case_id = case_id;}
    public void seteq_stock_id(String eq_stock_id) {this.eq_stock_id = eq_stock_id;}
    public void setsr_id(String sr_id) {this.sr_id = sr_id;}
    public void setconsumer_id(String consumer_id) {this.consumer_id = consumer_id;}
    public void setconsumer_name(String consumer_name) {this.consumer_name = consumer_name;}
    public void setconsumer_mobile(String consumer_mobile) {this.consumer_mobile = consumer_mobile;}
    public void setconsumer_email(String consumer_email) {this.consumer_email = consumer_email;}
    public void setcase_status(String case_status) {this.case_status = case_status;}
    public void setcreated_on(String created_on) {this.created_on = created_on;}
    public void setcall_category(String call_category) {this.call_category = call_category;}
    public void setsr_status(String sr_status) {this.sr_status = sr_status;}
    public void setsr_no(String sr_no) {this.sr_no = sr_no;}
    public void setcreated_by(String created_by) {this.created_by = created_by;}


    //    HashMap<String, String> hm =new HashMap<>();
//    hm.put("case_id", b.getcase_id());
//    hm.put("eq_stock_id", b.geteq_stock_id());
//    hm.put("sr_id", b.getsr_id());
//    hm.put("consumer_id", b.getconsumer_id());
//    hm.put("consumer_name", b.getconsumer_name());
//    hm.put("consumer_mobile", b.getconsumer_mobile());
//    hm.put("consumer_email", b.getconsumer_email());
//    hm.put("case_status", b.getcase_status());
//    hm.put("created_on", b.getcreated_on());
//    hm.put("call_category", b.getcall_category());
//    hm.put("sr_status", b.getsr_status());
//    hm.put("sr_no", b.getsr_no());
//    hm.put("created_by", b.getcreated_by());
//    callLogslistview.add(hm);

}
