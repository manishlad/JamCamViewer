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

import java.util.LinkedList;

public class JamCamViewerMainActivity extends FragmentActivity {

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";

    private LinkedList<Route> routeInventory;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Check that the activity is using the layout version with
        // the main_fragment_container FrameLayout
        if (findViewById(R.id.route_pager) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Initialise the route inventory
            routeInventory = new LinkedList<Route>();
            seedBaseData();

            // Set-up the tabs in the Action Bar
            final ActionBar actionBar = getActionBar();
            // Specify that tabs should be displayed in the action bar.
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            // Add a tab for each route in the routeInventory
            for (Route r : routeInventory) {
                ActionBar.Tab routeTab = actionBar.newTab();
                routeTab.setText(r.getRouteLabel());
                routeTab.setTabListener(this.getTabListener());
                actionBar.addTab(routeTab);
            }
        }

    }

    // Create a tab listener that is called when the user changes tabs.
    private ActionBar.TabListener getTabListener() {
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // Create a new Fragment to contain the Route
                // that corresponds to the selected Action Bar tab
                RouteDisplayFragment routeDisplayFragment = new RouteDisplayFragment();

                // Pass the selected Route as an argument to the Fragment
                Route route = routeInventory.get(tab.getPosition());
                Bundle args = new Bundle();
                args.putSerializable(RouteDisplayFragment.ROUTE_ID, route);
                routeDisplayFragment.setArguments(args);

                // Replace the fragment in the 'route_pager' FrameLayout
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.route_pager, routeDisplayFragment);
                transaction.commit();
            }

            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // hide the given tab
                // Do nothing for now
            }

            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // probably ignore this event
                // Do nothing for now
            }
        };
        
        return tabListener;
    }

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