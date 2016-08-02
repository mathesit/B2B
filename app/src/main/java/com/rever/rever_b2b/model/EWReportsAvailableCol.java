package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 8/2/2016.
 */
public class EWReportsAvailableCol {

    private  int config_id;
    private  String column_id;
    private  String view_name;
    private  String column_name;
    private  String display_name;
    private  String data_type;
    private  String format;
    private  String column_identifier;
    private  String order_by_column;
    private  String qry_string;
    private  String is_displayable_column;
    private  String column_with_datatype;

//    "reportsallcolumn": [
//    {
//        "config_id": 6,
//            "column_id": 157,
//            "view_name": "RST_REPORT_CALL_LOG_CASES",
//            "column_name": "SERIAL_NO",
//            "display_name": "Serial No.",
//            "data_type": "TXT",
//            "format": "",
//            "column_identifier": "SERIAL_NO",
//            "order_by_column": "",
//            "qry_string": "",
//            "is_displayable_column": "1",
//            "column_with_datatype": "SERIAL_NO|TXT"
//    }

    public EWReportsAvailableCol(int config_id,String column_id,String view_name,
                                 String column_name,String display_name,String data_type,String format
            ,String column_identifier,String order_by_column,String qry_string,String is_displayable_column,String column_with_datatype){

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

    }

    public int getconfig_id(){return  config_id;}
    public String getcolumn_id(){return  column_id;}
    public String getview_name(){return  view_name;}
    public String getcolumn_name(){return  column_name;}
    public String getdisplay_name(){return  display_name;}
    public String getdata_type(){return  data_type;}
    public String getformat(){return  format;}
    public String getcolumn_identifier(){return  column_identifier;}
    public String getorder_by_column(){return  order_by_column;}
    public String getqry_string(){return  qry_string;}
    public String getis_displayable_column(){return  is_displayable_column;}
    public String getcolumn_with_datatype(){return  column_with_datatype;}

    public void setconfig_id(int  config_id){this. config_id = config_id;}
    public void setcolumn_id(String  column_id){this. column_id = column_id;}
    public void setview_name(String  view_name){this. view_name = view_name;}
    public void setcolumn_name(String  column_name){this. column_name = column_name;}
    public void setdisplay_name(String  display_name){this. display_name = display_name;}
    public void setdata_type(String  data_type){this. data_type = data_type;}
    public void setformat(String  format){this. format = format;}
    public void setcolumn_identifier(String  column_identifier){this. column_identifier = column_identifier;}
    public void setorder_by_column(String  order_by_column){this. order_by_column = order_by_column;}
    public void setqry_string(String  qry_string){this. qry_string = qry_string;}
    public void setis_displayable_column(String  is_displayable_column){this. is_displayable_column = is_displayable_column;}
    public void setcolumn_with_datatype(String  column_with_datatype){this. column_with_datatype = column_with_datatype;}

}
