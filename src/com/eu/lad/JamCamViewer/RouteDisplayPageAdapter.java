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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.LinkedList;

public class RouteDisplayPageAdapter extends FragmentStatePagerAdapter {

    protected LinkedList<Route> mRouteInventory;

    public RouteDisplayPageAdapter(FragmentManager fm, LinkedList<Route> routeList) {
        super(fm);
        mRouteInventory = routeList;
    }

    @Override
    public Fragment getItem(int i) {
/*        Route r = mRouteInventory.get(i);
        Fragment fragment = new RouteDisplayFragment();
        Bundle args = new Bundle();
        args.putSerializable(RouteDisplayFragment.ROUTEKEY, r);
        fragment.setArguments(args);
        return fragment;
*/
        return null;
    }

    @Override
    public int getCount() {
        return mRouteInventory.size();
    }

//    @Override
//    public CharSequence getPageTitle(int i) {
//        Route r = mRouteInventory.get(i);
//        return r.getRouteLabel();
//    }

}
