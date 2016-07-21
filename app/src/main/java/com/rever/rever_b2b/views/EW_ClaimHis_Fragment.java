package com.rever.rever_b2b.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rever.rever_b2b.R;
import com.rever.rever_b2b.utils.MasterCache;

/**
 * Created by Bharath on 6/3/2016.
 */
public class EW_ClaimHis_Fragment extends Fragment {
    private View rootView;
    private EditText edtchemail,edtchalteremail,edtchserialno,edtchbrand,
            edtchproducttype,edtchmodel,edtchwarrno,edtchwarrmonths,edtchEWPdate,edtchEWSdate,edtchEWExpdate,edtchprovider,
            edtchamountClaimed,edtchmaxclaim,edtchclaimlimit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_claim_history, container, false);
        initView();
        Settextfields();
        return rootView;
    }

    public void initView(){
        edtchemail = (EditText) rootView.findViewById(R.id.edtchEmail);
        edtchalteremail = (EditText) rootView.findViewById(R.id.edtchAlternateemail);
        edtchserialno = (EditText) rootView.findViewById(R.id.edtchSerialNo);
        edtchbrand = (EditText) rootView.findViewById(R.id.edtchBrand);
        edtchproducttype = (EditText) rootView.findViewById(R.id.edtchProductType);
        edtchmodel = (EditText) rootView.findViewById(R.id.edtchModel);
        edtchwarrno = (EditText) rootView.findViewById(R.id.edtchWarrantyNo);
        edtchwarrmonths = (EditText) rootView.findViewById(R.id.edtchWarrantyMonths);
        edtchEWPdate = (EditText) rootView.findViewById(R.id.edtchEWPDate);
        edtchEWSdate = (EditText) rootView.findViewById(R.id.edtchEWStartDate);
        edtchEWExpdate = (EditText) rootView.findViewById(R.id.edtchEWExpiresDate);
        edtchprovider = (EditText) rootView.findViewById(R.id.edtchprovider);
        edtchamountClaimed = (EditText) rootView.findViewById(R.id.edtchAmountClaimed);
        edtchmaxclaim = (EditText) rootView.findViewById(R.id.edtchMaxClaimAmount);
        edtchclaimlimit = (EditText) rootView.findViewById(R.id.edtchAvailClaimLimit);



    }
    public void Settextfields(){
        int id= MasterCache.ch_warranty_id.get(0);

        edtchemail.setText(MasterCache.ch_email_id.get(id));
        edtchalteremail.setText(MasterCache.ch_email_id.get(id));
        edtchserialno.setText(MasterCache.ch_serial_no.get(id));
        edtchbrand.setText(MasterCache.ch_brand.get(id));
        edtchproducttype.setText(MasterCache.ch_product_type.get(id));
        edtchmodel.setText(MasterCache.ch_model_name.get(id));
        edtchwarrno.setText(MasterCache.ch_warranty_no.get(id));
        edtchwarrmonths.setText(MasterCache.warrMonths.get(id));
        edtchEWPdate.setText(MasterCache.ch_purchased_date.get(id));
        edtchEWSdate.setText(MasterCache.ch_ew_start_date.get(id));
        edtchEWExpdate.setText(MasterCache.ch_ew_expiry_date.get(id));
        edtchprovider.setText(MasterCache.ch_warranty_provider_name.get(id));
        edtchamountClaimed.setText(MasterCache.ch_total_claimed_amt.get(id));
        edtchmaxclaim.setText(MasterCache.ch_max_claimable_amt.get(id));
        edtchclaimlimit.setText(MasterCache.ch_claimable_balance.get(id));

    }

}
