package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 8/2/2016.
 */
public class EWReportsSelectedCol {

    private int column_id;
    private String display_name;
    private String report_column;
    private String view_order;

    public EWReportsSelectedCol(int column_id,String display_name,String report_column,String view_order){

        this.column_id = column_id;
        this.display_name = display_name;
        this.report_column = report_column;
        this.view_order = view_order;

    }
    public int getcolumn_id(){return column_id;}
    public String getdisplay_name(){return display_name;}
    public String getreport_column(){return report_column;}
    public String getview_order(){return view_order;}

    public void setcolumn_id(int column_id){this.column_id = column_id;}
    public void setdisplay_name(String display_name){this.display_name = display_name;}
    public void setreport_column(String report_column){this.report_column = report_column;}
    public void setview_order(String view_order){this.view_order = view_order;}
}
