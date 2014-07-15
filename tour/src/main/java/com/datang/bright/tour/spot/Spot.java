package com.datang.bright.tour.spot;

import com.google.gson.Gson;

/**
 * Created by l on 14-7-14.
 */
public class Spot {
    String imageURI;
    int resource;
    String title;
    String price;
    String loc;
    String level;
    String status;

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
