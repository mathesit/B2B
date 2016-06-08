package com.rever.rever_b2b.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rever.rever_b2b.R;

/**
 * Created by Oviya on 6/8/2016.
 */
public class ServiceRequestExtended extends Fragment {
    private View rootView;
    private LinearLayout linearService,linearRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_service, container, false);
        initViews();
        return rootView;
    }

    private void initViews(){
        linearService = (LinearLayout)rootView.findViewById(R.id.LinearServiceRequest);
        linearRequest= (LinearLayout)rootView.findViewById(R.id.LinearServiceRequestDetails);

    }

}

