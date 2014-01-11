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

import java.util.LinkedList;

public class Route {

    private String routeLabel;
    private LinkedList<RoutePoint> mRoute;

    public Route(String label) {
        routeLabel = label;
        mRoute = new LinkedList<RoutePoint>();
    }

    public void addRoutePoint(int cameraId, String cameraDescription) {
        RoutePoint rp = new RoutePoint(cameraId, cameraDescription);
        mRoute.add(rp);
    }

    public String getRouteLabel() {
        return routeLabel;
    }

    public LinkedList<RoutePoint> getRoutePoints() {
        return mRoute;
    }

    public RoutePoint getRoutePoint(int cameraId) {
        RoutePoint routePoint = null;
        for (RoutePoint rp : mRoute) {
            if (rp.getCameraId() == cameraId)
                routePoint = rp;
        }
        return routePoint;
    }
}
