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

public class RoutePoint {

    protected final String cameraBaseURL = "http://www.trafficengland.com/trafficcamera.aspx?cameraUri=http://public.hanet.org.uk/cctvpublicaccess/html/%s.html";

    private int mRPCameraId;
    private String mRPDescription;

    public RoutePoint(int cameraId, String cameraDescription) {
        mRPCameraId = cameraId;
        mRPDescription = cameraDescription;
    }

    public int getCameraId() {
        return mRPCameraId;
    }

    public String getCameraDescription() {
        return mRPDescription;
    }

    public String getCameraURL() {
        return String.format(cameraBaseURL, mRPCameraId);
    }

}
