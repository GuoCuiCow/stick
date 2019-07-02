package com.cuiguo.stick;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cuiguo.stick.dummy.DummyContent;

public class ScrollingActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appBar;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpage);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new MyViewAdapter(getSupportFragmentManager()));
        appBar = findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onBackPressed() {
        if (appBar.getVisibility() == View.GONE) {
            appBar.setVisibility(View.VISIBLE);
            CoordinatorLayout.Behavior behavior =
                    ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
                int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
                if (topAndBottomOffset != 0) {
                    appBarLayoutBehavior.setTopAndBottomOffset(0);
                }
            }
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        Log.e("tag", "i=" + i);
        Log.e("tag", "appBarLayout.getHeight()" + appBarLayout.getHeight());
        if (!isFirst && i == 0) {
            isFirst = true;
        }
        //超过高度就隐藏
        if (isFirst && appBarLayout.getHeight() + i == 0) {
            appBarLayout.setVisibility(View.GONE);
            isFirst = false;
        }
    }
}
