package com.example.christopher.sceandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.Animation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorDEPagina extends FragmentPagerAdapter implements Serializable{
    // List of fragments which are going to set in the view pager widget
    List<Fragment> fragments;
    private static final long serialVersionUID = -1445L;
    /**
     * Constructor
     *
     * @param fm
     *            interface for interacting with Fragment objects inside of an
     *            Activity
     */
    public AdaptadorDEPagina(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
    }

    /**
     * Add a new fragment in the list.
     *
     * @param fragment
     *            a new fragment
     */
    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int arg0) {
        return this.fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}