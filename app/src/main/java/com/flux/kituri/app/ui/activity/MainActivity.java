package com.flux.kituri.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.flux.kituri.R;
import com.flux.kituri.app.business.backgroundService.UpdateService;
import com.flux.kituri.app.ui.fragment.DiscoverFragment;
import com.flux.kituri.app.ui.fragment.LessonsFragment;
import com.flux.kituri.app.ui.fragment.MeFragment;
import com.flux.kituri.app.ui.fragment.ScheduledFragment;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.ui.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottom_navigation_bar;
    private TextView tv_submit;
    private Button btn_submit;
    //private Fragment mContent;

//    private LessonsFragment mLessonsFragment;
//    private ScheduledFragment mScheduledFragment;
//    private DiscoverFragment mDiscoverFragment;
//    private MeFragment mMeFragment;

    static public final String tags[] = {LessonsFragment.TAG, ScheduledFragment.TAG, DiscoverFragment.TAG, MeFragment.TAG};

    static public final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomBar(savedInstanceState);
    }

    private void initBottomBar(Bundle savedInstanceState) {
        bottom_navigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_CLASSIC);
        bottom_navigation_bar
                //setBadgeItem(numberBadgeItem)
                .addItem(new BottomNavigationItem(R.drawable.bb_menu_lessons, getString(R.string.cap_bb_menu_lessons)).setActiveColorResource(R.color.flux_main_color))
                .addItem(new BottomNavigationItem(R.drawable.bb_menu_scheduled, getString(R.string.cap_bb_menu_scheduled)).setActiveColorResource(R.color.flux_main_color))
                .addItem(new BottomNavigationItem(R.drawable.bb_menu_discover, getString(R.string.cap_bb_menu_discover)).setActiveColorResource(R.color.flux_main_color))
                .addItem(new BottomNavigationItem(R.drawable.bb_menu_me, getString(R.string.cap_bb_menu_me)).setActiveColorResource(R.color.flux_main_color))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        bottom_navigation_bar.setTabSelectedListener(this);
        onTabSelected(0);
    }

    @Override
    public ArrayList<Store> initFlux() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, UpdateService.class));
    }

    private void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(R.id.content_frame, new LessonsFragment());
                break;
            case 1:
                replaceFragment(R.id.content_frame, new ScheduledFragment());
                break;
            case 2:
                replaceFragment(R.id.content_frame, new DiscoverFragment());
                break;
            case 3:
                replaceFragment(R.id.content_frame, new MeFragment());
                break;

            default:
                replaceFragment(R.id.content_frame, new LessonsFragment());
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
