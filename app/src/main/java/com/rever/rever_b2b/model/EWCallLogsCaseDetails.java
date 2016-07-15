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
 * Created by Bharath on 7/14/2016.
 */
public class EWCallLogsCaseDetails {
    private String case_id;
    private String log_id, log_desc, logged_by, logged_on, sr_id,
            sr_number, sr_status;

    public EWCallLogsCaseDetails
            (String case_id, String log_id,String sr_id, String log_desc, String logged_by, String logged_on,
             String sr_number, String sr_status) {
        this.case_id = case_id;
        this.log_id = log_id;
        this.sr_id = sr_id;
        this.log_desc = log_desc;
        this.logged_by = logged_by;
        this.logged_on = logged_on;
        this.sr_number = sr_number;
        this.sr_status = sr_status;
    }

    public String getcase_id() { return case_id;}
    public String getlog_id() { return log_id;}
    public String getsr_id() { return sr_id;}
    public String getlog_desc() { return log_desc;}
    public String getlogged_by() { return logged_by;}
    public String getlogged_on() { return logged_on;}
    public String getsr_number() { return sr_number;}
    public String getsr_status() { return sr_status;}


    public void setcase_id(String case_id) {this.case_id = case_id;}
    public void setlog_id(String log_id) {this.log_id = log_id;}
    public void setsr_id(String sr_id) {this.sr_id = sr_id;}
    public void setlog_desc(String log_desc) {this.log_desc = log_desc;}
    public void setlogged_by(String logged_by) {this.logged_by = logged_by;}
    public void setlogged_on(String logged_on) {this.logged_on = logged_on;}
    public void setsr_number(String sr_number) {this.sr_number = sr_number;}
    public void setsr_status(String sr_status) {this.sr_status = sr_status;}

}
