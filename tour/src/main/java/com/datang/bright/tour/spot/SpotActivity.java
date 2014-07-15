package com.datang.bright.tour.spot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
 * Created by l on 14-7-10.
 */
public class SpotActivity extends FragmentActivity implements BottomFragment.Callbacks {


    private PullToRefreshListView mPullRefreshListView;
    private LinkedList<Spot> mListItems;
    private SpotPullListViewAdater mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_spot);

        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_pull_spot);

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
                Toast.makeText(SpotActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
            }
        });


        mListItems = new LinkedList<Spot>();
        Spot spot = new Spot();
        spot.resource = R.drawable.spot1;
        spot.title = "黄果树风景名胜区";
        spot.level = "5A景区";
        spot.status = "|售卖中";
        spot.price = "¥50起";
        spot.loc = "贵州省安顺市黄果树风景名胜区";
        mListItems.add(spot);

        mAdapter = new SpotPullListViewAdater(this, mListItems);


        // You can also just use setListAdapter(mAdapter) or
        // mPullRefreshListView.setAdapter(mAdapter)
        mPullRefreshListView.setAdapter(mAdapter);

        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SpotActivity.this,SpotDetailActivity.class);
//                intent.getExtras().putString("spot", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        mAdapter.notifyDataSetChanged();

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpotActivity.this.finish();
            }
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, List<Spot>> {

        @Override
        protected List<Spot> doInBackground(Void... params) {
            List spotList = new ArrayList();
            int[] images = new int[]{R.drawable.spot2, R.drawable.spot3};
            for (int count = 0; count < 2; count++) {
                Spot spot = new Spot();
                spot.resource = images[count];
                spot.title = "黄果树风景名胜区";
                spot.level = "5A景区";
                spot.status = "|售卖中";
                spot.price = "¥50起";
                spot.loc = "贵州省安顺市黄果树风景名胜区";
                spotList.add(spot);
            }
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return spotList;
        }

        @Override
        protected void onPostExecute(List<Spot> result) {
            mListItems.addAll(result);
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    @Override
    public void onItemSelected(int item) {

    }
}
