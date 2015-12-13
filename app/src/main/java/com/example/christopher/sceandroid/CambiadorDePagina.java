package com.example.christopher.sceandroid;

import android.support.v4.view.ViewPager;
import android.view.animation.Animation;

/**
 * Created by Christopher on 12/12/2015.
 */
public class CambiadorDePagina {
    private static ViewPager pageViewer;

    public static void cambiarPagina(int i,Animation at){
        pageViewer.setAnimation(at);
        pageViewer.setCurrentItem(i+1);
    }
    public static ViewPager getPageViewer() {
        return pageViewer;
    }

    public static void setPageViewer(ViewPager pageViewer) {
        CambiadorDePagina.pageViewer = pageViewer;
    }
}
