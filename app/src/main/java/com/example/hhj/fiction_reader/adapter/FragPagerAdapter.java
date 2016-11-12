package com.example.hhj.fiction_reader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by backfire on 2016/10/22.
 */
public class FragPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragment;
    private List<String> titleList;

    public FragPagerAdapter(FragmentManager fragmentManager,List<String> titleList,List<Fragment> mFragment){
        super(fragmentManager);
        this.titleList = titleList;
        this.mFragment = mFragment;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return  mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  titleList.get(position);
    }
}
