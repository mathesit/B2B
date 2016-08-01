package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 8/1/2016.
 */
public class EwTabReports {

    private Integer id;
    private String report_title;
    private String config_description;
    private String config_name;
    private String show_details;
    private String created_on;
    public EwTabReports
            (Integer id,  String report_title,String config_description,String config_name,String show_details,String created_on) {
        this.id = id;
        this.report_title = report_title;
        this.config_description = config_description;
        this.config_name = config_name;
        this.show_details = show_details;
        this.created_on = created_on;

    }

    public Integer getreportsId() { return id;}
    public String getreport_title() { return report_title;}

    public String getconfig_description() { return config_description;}
    public String getconfig_name() { return config_name;}
    public String getshow_details() { return show_details;}
    public String getcreated_on() { return created_on;}


    public void setreportsId(Integer id) {this.id = id;}
    public void setreport_title(String report_title) {this.report_title = report_title;}

    public void setconfig_description(String config_description) {this.config_description = config_description;}
    public void setconfig_name(String config_name) {this.config_name = config_name;}
    public void setshow_details(String show_details) {this.show_details = show_details;}
    public void setcreated_on(String created_on) {this.created_on = created_on;}

}
