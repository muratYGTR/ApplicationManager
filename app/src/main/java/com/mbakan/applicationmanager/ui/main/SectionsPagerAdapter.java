package com.mbakan.applicationmanager.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mbakan.applicationmanager.AllApplicationsFragment;
import com.mbakan.applicationmanager.MyApplicationsFragment;
import com.mbakan.applicationmanager.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{ R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return AllApplicationsFragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return MyApplicationsFragment.newInstance();
            default:
                return MyApplicationsFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}