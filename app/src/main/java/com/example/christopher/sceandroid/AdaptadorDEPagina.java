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
    private static final long serialVersionUID = -923983291075894458L;
    ViewPager pageViewer;

    /**
     * Constructor
     *
     * @param fm
     *            interface for interacting with Fragment objects inside of an
     *            Activity
     */
    public AdaptadorDEPagina(FragmentManager fm,ViewPager wP) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        this.pageViewer=wP;
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
    public void cambiarVista(int i,Animation at){
        pageViewer.setAnimation(at);
        pageViewer.setCurrentItem(i+1);
    }

}