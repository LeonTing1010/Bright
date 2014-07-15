package com.datang.bright.tour.spot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.bright.tour.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by l on 14-7-14.
 */
public class SpotPullListViewAdater extends BaseAdapter {


    private final LayoutInflater mInflater;
    private final Context mContext;
    List<Spot> mSpotList;

    public SpotPullListViewAdater(Context context, List<Spot> spotList) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mSpotList = spotList;
    }


    @Override
    public int getCount() {
        return mSpotList == null ? 0 : mSpotList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSpotList == null ? null : mSpotList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (mSpotList == null || mSpotList.isEmpty()) return view;

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.lv_spot_item, null);
            holder.imageURI = (ImageView) view.findViewById(R.id.iv_spot_image);
            holder.title = (TextView) view.findViewById(R.id.tv_spot_title);
            holder.level = (TextView) view.findViewById(R.id.tv_spot_level);
            holder.status = (TextView) view.findViewById(R.id.tv_spot_status);
            holder.price = (TextView) view.findViewById(R.id.tv_spot_price);
            holder.loc = (TextView) view.findViewById(R.id.tv_spot_loc);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Spot spot = mSpotList.get(i);
        Picasso.with(mContext).load(spot.imageURI).placeholder(spot.resource).into(holder.imageURI);
        holder.title.setText(spot.title);
        holder.level.setText(spot.level);
        holder.status.setText(spot.status);
        holder.price.setText(spot.price);
        holder.loc.setText(spot.loc);
        return view;
    }

    private static class ViewHolder {
        ImageView imageURI;
        TextView title;
        TextView price;
        TextView loc;
        TextView level;
        TextView status;

    }
}
