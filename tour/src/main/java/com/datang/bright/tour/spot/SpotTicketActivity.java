package com.datang.bright.tour.spot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.datang.bright.tour.R;

/**
 * Created by l on 14-7-15.
 */
public class SpotTicketActivity  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_spot_ticket);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpotTicketActivity.this.finish();
            }
        });
    }
}
