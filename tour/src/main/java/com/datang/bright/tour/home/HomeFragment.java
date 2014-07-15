package com.datang.bright.tour.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datang.bright.tour.R;
import com.datang.bright.tour.hotel.HotelActivity;
import com.datang.bright.tour.spot.SpotActivity;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by l on 14-7-9.
 */
public class HomeFragment extends Fragment {

    private ViewPager adViewPager;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;
    private final int AD_SCROLL_DURATION_MILLS = 5000;//切换间隔时间，单位秒

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container);
        initViewPager(view);
        return view;
    }


    private void initViewPager(View root) {

        //从布局文件中获取ViewPager父容器
//        LinearLayout pagerLayout = (LinearLayout) findViewById(R.id.view_pager_content);
//        //创建ViewPager
//        adViewPager = new ViewPager(this) ;

        //获取屏幕像素相关信息
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        //根据屏幕信息设置ViewPager广告容器的宽高
//        adViewPager.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels, dm.heightPixels * 2 / 5));
//

        //将ViewPager容器设置到布局文件父容器中
//        pagerLayout.addView(adViewPager);

        final TestFragmentAdapter mAdapter = new TestFragmentAdapter(this.getFragmentManager());

        final ViewPager mPager = (ViewPager) root.findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable);
            }
        });
        final CirclePageIndicator mIndicator = (CirclePageIndicator) root.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = mPager.getCurrentItem();
                int count = mAdapter.getCount();
                if (currentItem++ == count) {
                    mIndicator.setCurrentItem(0);
                } else {
                    mIndicator.setCurrentItem(currentItem % count);
                }
                mHandler.removeCallbacks(mRunnable);
            }
        };
        mIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mHandler.postDelayed(mRunnable, AD_SCROLL_DURATION_MILLS);
                // This space for rent
            }
        });


        root.findViewById(R.id.iv_spot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeFragment.this.getActivity(), SpotActivity.class));
            }
        });
        root.findViewById(R.id.iv_hotel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeFragment.this.getActivity(), HotelActivity.class));
            }
        });

    }
}
