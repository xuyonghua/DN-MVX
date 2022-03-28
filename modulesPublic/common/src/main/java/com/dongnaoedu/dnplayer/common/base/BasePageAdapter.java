package com.dongnaoedu.dnplayer.common.base;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BasePageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList;
    private final String[] titles;

    public BasePageAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragmentList, String[] titles) {
        super(fm, behavior);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
