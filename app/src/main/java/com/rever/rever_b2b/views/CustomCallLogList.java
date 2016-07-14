package com.rever.rever_b2b.views;

        import android.app.Activity;
        import android.app.Dialog;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.support.v4.content.ContextCompat;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.rever.rever_b2b.R;
        import com.rever.rever_b2b.model.EWTabCallLogs;
        import com.rever.rever_b2b.utils.MasterCache;

        import java.util.List;

public class CustomCallLogList extends ArrayAdapter<EWTabCallLogs> {

    private Activity activity;

    public CustomCallLogList(Activity activity, int resource, List<EWTabCallLogs> Userra) {
        super(activity, resource, Userra);
        this.activity = activity;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_call_log_cases, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EWTabCallLogs user = getItem(position);

        holder.name.setText(user.getconsumer_name());
        holder.num.setText(user.getconsumer_mobile());
        holder.createdby.setText(user.getcreated_by());
        holder.caseId.setText(user.getcase_id());
        holder.date.setText(user.getcreated_on());
        holder.enquiry.setText(user.getcall_category());


        holder.Dbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.cl_case_details_popup);
                TextView dialogButton = (TextView) dialog.findViewById(R.id.closeNewCase);

                dialogButton.setOnClickListener(new View.OnClickListener()

                            {
                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();
                                    EWCallLogsFragment m = new EWCallLogsFragment();
                                    m.GetCallLogsTask(MasterCache.listPosition_id);
                                }
                            }

                );

                dialog.show();
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        private TextView name,num,createdby,caseId,date,enquiry,Dbutton,Closebutton;


        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.txtewCLName);
            num = (TextView) v.findViewById(R.id.txtewCLnum);
            createdby = (TextView) v.findViewById(R.id.txtewCLcreatedbyname);
            caseId= (TextView) v.findViewById(R.id.txtewCLCaseId);;
            date = (TextView) v.findViewById(R.id.txtewCLDate);
            enquiry = (TextView) v.findViewById(R.id.txtewCLenquiry);
            Dbutton =(TextView) v.findViewById(R.id.txtewCLdetailsbtn);
            Closebutton =(TextView) v.findViewById(R.id.txtewCLclosecasebtn);
        }
    }

}
