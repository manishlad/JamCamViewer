/*
    JamCamViewer
    Copyright (C) 2013  Manish Lad

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.eu.lad.JamCamViewer;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import java.util.LinkedList;

public class JamCamViewerMainActivity extends FragmentActivity {
//    implements JamCamViewerMainFragment.OnCameraSelectedListener {

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";
    public final static int ADD_NEW_CAMERA_REQUEST = 1;

    protected RouteDisplayPageAdapter mRouteDisplayPageAdapter;
    //protected ViewPager mViewPager;
    protected FrameLayout mFrameLayout;

//    private JamCamInventory inventory;

    private LinkedList<Route> routeInventory;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        // Initialise the camera inventory
//        inventory = new JamCamInventory(this);
//
//        // Check that the activity is using the layout version with
//        // the main_fragment_container FrameLayout
//        if (findViewById(R.id.main_fragment_container) != null) {
//
//            // However, if we're being restored from a previous state,
//            // then we don't need to do anything and should return or else
//            // we could end up with overlapping fragments.
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            // Create a new Fragment to be placed in the activity layout
//            JamCamViewerMainFragment firstFragment = new JamCamViewerMainFragment();
//
//            // In case this activity was started with special instructions from an
//            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());
//
//            // Add the fragment to the 'fragment_container' FrameLayout
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.add(R.id.main_fragment_container, firstFragment);
//            transaction.commit();
//        }


        // Initialise the route inventory
        routeInventory = new LinkedList<Route>();
        seedBaseData();

//        mRouteDisplayPageAdapter = new RouteDisplayPageAdapter(getSupportFragmentManager(), routeInventory);
//        mViewPager = (ViewPager) findViewById(R.id.route_pager);
//        mViewPager.setAdapter(mRouteDisplayPageAdapter);

        RouteDisplayFragment rpf = new RouteDisplayFragment();
        Route r = routeInventory.get(0);
        Bundle args = new Bundle();
        args.putSerializable(RouteDisplayFragment.ROUTEKEY, r);
        rpf.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.route_pager, rpf);
        transaction.commit();


        final ActionBar actionBar = getActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Add a tab for each route in the routeInventory
        for (Route route : routeInventory) {
            ActionBar.Tab routeTab = actionBar.newTab();
            routeTab.setText(route.getRouteLabel());
            routeTab.setTabListener(this.getTabListener());
            actionBar.addTab(routeTab);
        }

//        mViewPager.setOnPageChangeListener(
//                new ViewPager.SimpleOnPageChangeListener() {
//                    @Override
//                    public void onPageSelected(int position) {
//                        // When swiping between pages, select the
//                        // corresponding tab.
//                        getActionBar().setSelectedNavigationItem(position);
//                    }
//                });

    }

    // Create a tab listener that is called when the user changes tabs.
    private ActionBar.TabListener getTabListener() {
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // show the given tab
                // When the tab is selected, switch to the
                // corresponding page in the ViewPager.
//                mViewPager.setCurrentItem(tab.getPosition());


                RouteDisplayFragment rpf = new RouteDisplayFragment();
                Route r = routeInventory.get(tab.getPosition());
                Bundle args = new Bundle();
                args.putSerializable(RouteDisplayFragment.ROUTEKEY, r);
                rpf.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.route_pager, rpf);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };
        
        return tabListener;
    }
    
    
//    public void cameraSelected(int camera) {
//        JamCamView jamCamViewFragment = new JamCamView();
//        getIntent().putExtra(CAM_URL, inventory.getCameraURL(camera));
//        jamCamViewFragment.setArguments(getIntent().getExtras());
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.main_fragment_container, jamCamViewFragment);
//        transaction.addToBackStack(null);
//
//        transaction.commit();
//    }

//    public JamCamInventory getInventory() {
//        return inventory;
//    }


    private void seedBaseData() {
        if (routeInventory.isEmpty()) {
            Route route = new Route("Sample Route 1");

            route.addRoutePoint(58299, "M40 J1");
            route.addRoutePoint(58316, "M40 J1A");
            route.addRoutePoint(58350, "M40 J1A-J2");
            route.addRoutePoint(58368, "M40 J1A-J2 curve");

            routeInventory.add(route);


            route = new Route("Sample Route 2");

            route.addRoutePoint(55020, "M25 J16 under M40");
            route.addRoutePoint(54975, "M25 J16-J15");
            route.addRoutePoint(54965, "M25 J16-J15");

            routeInventory.add(route);


            route = new Route("Sample Route 3");

            route.addRoutePoint(52280, "M4 J4B");
            route.addRoutePoint(52288, "M4 J4B");
            route.addRoutePoint(52296, "M4 J4B-J5");
            route.addRoutePoint(52306, "M4 J5");
            route.addRoutePoint(52350, "M4 J5-J6");

            routeInventory.add(route);

        }

    }
}