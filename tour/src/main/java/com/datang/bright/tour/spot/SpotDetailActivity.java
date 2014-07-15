package com.datang.bright.tour.spot;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.datang.bright.tour.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by l on 14-7-14.
 */
public class SpotDetailActivity  extends Activity{

    private PullToRefreshListView mPullRefreshListView;
    private LinkedList<SpotTicket> mListItems;
    private SpotTicketPullListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotdetail);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpotDetailActivity.this.finish();
            }
        });

        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_pull_spot_detail);

        // Set a listener to be invoked when the list should be refreshed.
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });

        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(SpotDetailActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
            }
        });


        mListItems = new LinkedList<SpotTicket>();
        SpotTicket spotTicket = new SpotTicket();
        spotTicket.time="16:30";
        spotTicket.title = "黄果树风景名胜区";
        spotTicket.price = "¥180";

        mListItems.add(spotTicket);

        mAdapter = new SpotTicketPullListViewAdapter(this, mListItems);


        // You can also just use setListAdapter(mAdapter) or
        // mPullRefreshListView.setAdapter(mAdapter)
        mPullRefreshListView.setAdapter(mAdapter);

        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SpotDetailActivity.this,SpotDetailActivity.class);
//                intent.getExtras().putString("spot", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        mAdapter.notifyDataSetChanged();
    }


    private class GetDataTask extends AsyncTask<Void, Void, List<SpotTicket>> {

        @Override
        protected List<SpotTicket> doInBackground(Void... params) {
            List spotList = new ArrayList();
            int[] images = new int[]{R.drawable.spot2, R.drawable.spot3};
            for (int count = 0; count < 2; count++) {
                SpotTicket spotTicket = new SpotTicket();
                spotTicket.title = "黄果树风景名胜区";
                spotTicket.price = "¥50起";
                spotTicket.time = "16:30";
                spotList.add(spotTicket);
            }
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return spotList;
        }

        @Override
        protected void onPostExecute(List<SpotTicket> result) {
            mListItems.addAll(result);
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}
