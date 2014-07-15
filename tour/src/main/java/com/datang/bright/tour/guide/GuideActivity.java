package com.datang.bright.tour.guide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datang.bright.tour.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-7-9.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        // 初始化页面
        initViews();

        // 初始化底部小点
        initDots();
    }


    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);
        String[] titles = new String[]{
                "\n智慧旅行\n超过1亿人使用的手机应用", "摇一摇手机\n或者看看附近的人\n认识更多的朋友", "\n \n旅行，是一个生活方式"
        };
        views = new ArrayList<View>(3);
        int index = 0;
        for (; index < 2; index++) {
            // 初始化引导图片列表
            final View page = inflater.inflate(R.layout.whatsnew, null);
            ((TextView) page.findViewById(R.id.tv_title)).setText(titles[index]);
            Picasso.with(this).load(R.drawable.start2).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    page.findViewById(R.id.rl_bg).setBackground(new BitmapDrawable(bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

            views.add(page);
        }
        View page = inflater.inflate(R.layout.whatsnew_end, null);
        ((TextView) page.findViewById(R.id.tv_title)).setText(titles[index]);
        views.add(page);
        // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views, this);

        vp = (ViewPager) findViewById(R.id.whatsnew_viewpager);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_page_indicator);

        dots = new ImageView[views.size()];

        // 循环取得小点图片
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setImageResource(R.drawable.page);// 都设为灰色
        }

        currentIndex = 0;
        dots[currentIndex].setImageResource(R.drawable.page_now);// 设置为白色，即选中状态
    }

    private void setCurrentDot(int position) {
        if (position < 0 || position > views.size() - 1
                || currentIndex == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    // 当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    // 当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    // 当新的页面被选中时调用
    @Override
    public void onPageSelected(int arg0) {
        // 设置底部小点选中状态
        setCurrentDot(arg0);
    }

}
