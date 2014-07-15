package com.datang.bright.tour.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datang.bright.tour.R;

/**
 * Created by l on 14-7-10.
 */
public class MeFragment extends Fragment {
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv = (TextView) getView().findViewById(R.id.titleTv);
        tv.setText("me");
    }
}
