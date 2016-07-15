package com.rever.rever_b2b.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rever.rever_b2b.R;

/**
 * Created by Oviya on 7/13/2016.
 */
public class DashboardExtended extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_dashboard, container, false);
        return rootView;
    }

}
