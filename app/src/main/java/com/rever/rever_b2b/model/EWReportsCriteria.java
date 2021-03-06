package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 8/2/2016.
 */
public class EWReportsCriteria {


    private int report_id;
    private String config_id;
    private String column_id;
    private String view_name;
    private String column_name;
    private String display_name;
    private String data_type;
    private String format;
    private String column_identifier;
    private String order_by_column;
    private String qry_string;
    private String is_displayable_column;
    private String column_with_datatype;
    private String criteria_id;
    private String criteria_column;
    private String criteria_type;
    private String default_criteria_value;
    private String default_range_from;
    private String default_range_to;
    private String criteria_order;
    private String join_view;

    public EWReportsCriteria(int report_id,String config_id,String column_id,String view_name,
                             String column_name,String display_name,String data_type,String format,
                             String column_identifier,String order_by_column,String qry_string,String is_displayable_column,
                             String column_with_datatype,String criteria_id,String criteria_column,String criteria_type,
                             String default_criteria_value,String default_range_from,String default_range_to,String criteria_order,
                             String join_view){

        this.report_id = report_id;
        this.config_id = config_id;
        this.column_id = column_id;
        this.view_name = view_name;
        this.column_name = column_name;
        this.display_name = display_name;
        this.data_type = data_type;
        this.format = format;
        this.column_identifier = column_identifier;
        this.order_by_column = order_by_column;
        this.qry_string = qry_string;
        this.is_displayable_column = is_displayable_column;
        this.column_with_datatype = column_with_datatype;
        this.criteria_id = criteria_id;
        this.criteria_column = criteria_column;
        this.criteria_type = criteria_type;
        this.default_criteria_value = default_criteria_value;
        this.default_range_from = default_range_from;
        this.default_range_to = default_range_to;
        this.criteria_order = criteria_order;
        this.join_view = join_view;

    }

    public int getreport_id(){return report_id;}
    public String getconfig_id(){return config_id;}
    public String getcolumn_id(){return column_id;}
    public String getview_name(){return view_name;}
    public String getcolumn_name(){return column_name;}
    public String getdisplay_name(){return display_name;}
    public String getdata_type(){return data_type;}
    public String getformat(){return format;}
    public String getcolumn_identifier(){return column_identifier;}
    public String getorder_by_column(){return order_by_column;}
    public String getqry_string(){return qry_string;}
    public String getis_displayable_column(){return is_displayable_column;}
    public String getcolumn_with_datatype(){return column_with_datatype;}
    public String getcriteria_id(){return criteria_id;}
    public String getcriteria_column(){return criteria_column;}
    public String getcriteria_type(){return criteria_type;}
    public String getdefault_criteria_value(){return default_criteria_value;}
    public String getdefault_range_from(){return default_range_from;}
    public String getdefault_range_to(){return default_range_to;}
    public String getcriteria_order(){return criteria_order;}
    public String getjoin_view(){return join_view;}



    public void setreport_id(int report_id) {this.report_id = report_id;}
    public void setconfig_id(String config_id) {this.config_id = config_id;}
    public void setcolumn_id(String column_id) {this.column_id = column_id;}
    public void setview_name(String view_name) {this.view_name = view_name;}
    public void setcolumn_name(String column_name) {this.column_name = column_name;}
    public void setdisplay_name(String display_name) {this.display_name = display_name;}
    public void setdata_type(String data_type) {this.data_type = data_type;}
    public void setformat(String format) {this.format = format;}
    public void setcolumn_identifier(String column_identifier) {this.column_identifier = column_identifier;}
    public void setorder_by_column(String order_by_column) {this.order_by_column = order_by_column;}
    public void setqry_string(String qry_string) {this.qry_string = qry_string;}
    public void setis_displayable_column(String is_displayable_column) {this.is_displayable_column = is_displayable_column;}
    public void setcolumn_with_datatype(String column_with_datatype) {this.column_with_datatype = column_with_datatype;}
    public void setcriteria_id(String criteria_id) {this.criteria_id = criteria_id;}
    public void setcriteria_column(String criteria_column) {this.criteria_column = criteria_column;}
    public void setcriteria_type(String criteria_type) {this.criteria_type = criteria_type;}
    public void setdefault_criteria_value(String default_criteria_value) {this.default_criteria_value = default_criteria_value;}
    public void setdefault_range_from(String default_range_from) {this.default_range_from = default_range_from;}
    public void setdefault_range_to(String default_range_to) {this.default_range_to = default_range_to;}
    public void setcriteria_order(String criteria_order) {this.criteria_order = criteria_order;}
    public void setjoin_view(String join_view) {this.join_view = join_view;}

}
