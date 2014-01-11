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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.LinkedList;

public class RoutePointDisplayPageAdapter extends FragmentStatePagerAdapter {

    protected LinkedList<RoutePoint> mRoutePoints;

    public RoutePointDisplayPageAdapter(FragmentManager fm, LinkedList<RoutePoint> routePoints) {
        super(fm);
        mRoutePoints = routePoints;
    }

    @Override
    public Fragment getItem(int i) {
        RoutePoint rp = mRoutePoints.get(i);
        Fragment fragment = new RoutePointDisplayFragment();
        Bundle args = new Bundle();
        args.putString(RoutePointDisplayFragment.ROUTEPOINTURL, rp.getCameraURL());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return mRoutePoints.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        RoutePoint rp = mRoutePoints.get(i);
        return rp.getCameraDescription();
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

}
