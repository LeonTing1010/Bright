package com.datang.bright.tour.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.datang.bright.tour.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by l on 14-7-10.
 */
public class MainActivity extends FragmentActivity implements BottomFragment.Callbacks {
    private static final String MENU = "menu";
    private FragmentManager mFragmentManager;
    private int curMenu;
    private Map<Integer, Fragment> mFragments;


    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mFragments = new HashMap<Integer, Fragment>();

        mFragmentManager = getSupportFragmentManager();
        mFragments.put(R.id.menu_bt_order, mFragmentManager.findFragmentById(R.id.fragement_order));
        mFragments.put(R.id.menu_bt_home, mFragmentManager.findFragmentById(R.id.fragement_home));
        mFragments.put(R.id.menu_bt_me, mFragmentManager.findFragmentById(R.id.fragement_me));
        mFragments.put(R.id.bottom_fragment, mFragmentManager.findFragmentById(R.id.bottom_fragment));

        if (savedInstanceState != null) {
            showFragment(savedInstanceState.getInt(MENU));
        } else {
            showFragment(R.id.menu_bt_home);
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MENU, curMenu);
    }


    @Override
    public void onItemSelected(int item) {
        curMenu = item;
        showFragment(item);

    }


    private void showFragment(int menuId) {
        FragmentTransaction mFragTransaction = mFragmentManager.beginTransaction();
        for (Fragment fragment : mFragments.values()) {
            mFragTransaction.hide(fragment);
        }
        mFragTransaction.show(mFragments.get(menuId)).show(mFragments.get(R.id.bottom_fragment)).commit();
    }
}