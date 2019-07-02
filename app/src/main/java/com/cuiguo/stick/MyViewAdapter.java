package com.cuiguo.stick;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



/**
 * @author: CuiGuo
 * @date: 2019/7/2
 * @info:
 */
public class MyViewAdapter extends FragmentPagerAdapter {

    String[] titles =new String[]{"消息", "发现", "我的", "其他"};

    public MyViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ItemFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
