package com.datang.bright.tour.spot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.datang.bright.tour.R;

import java.util.List;

/**
 * Created by l on 14-7-15.
 */
public class SpotTicketPullListViewAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final Context mContext;
    List<SpotTicket> mSpotTicketList;

    public SpotTicketPullListViewAdapter(Context context, List<SpotTicket> SpotTicketList) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mSpotTicketList = SpotTicketList;
    }


    @Override
    public int getCount() {
        return mSpotTicketList == null ? 0 : mSpotTicketList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSpotTicketList == null ? null : mSpotTicketList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (mSpotTicketList == null || mSpotTicketList.isEmpty()) return view;

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.lv_spot_ticket_item, null);
            holder.title = (TextView) view.findViewById(R.id.tv_spot_detail_ticket_title);
            holder.price = (TextView) view.findViewById(R.id.tv_spot_detail_ticket_price);
            holder.time = (TextView) view.findViewById(R.id.tv_spot_detail_ticket_time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        SpotTicket spotTicket = mSpotTicketList.get(i);
        holder.title.setText(spotTicket.title);
        holder.time.setText(spotTicket.time);
        holder.price.setText(spotTicket.price);
        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,SpotTicketActivity.class);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    private static class ViewHolder {
        TextView title;
        TextView price;
        TextView time;

    }
}
