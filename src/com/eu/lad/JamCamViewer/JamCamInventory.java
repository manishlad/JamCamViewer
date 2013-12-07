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

import android.util.Pair;

import java.util.Vector;

public class JamCamInventory {

    protected final String cameraBaseURL = "http://www.trafficengland.com/trafficcamera.aspx?cameraUri=http://public.hanet.org.uk/cctvpublicaccess/html/%s.html";

    private Vector<Pair<Integer, String>> cameras;

    public JamCamInventory() {
        cameras = new Vector<Pair<Integer, String>>();
        seedBaseData();
    }

    public Vector<Pair<Integer, String>> getAll() {
        return cameras;
    }

    public String getCameraURL(int camId) {
        String url = null;
        if(getCamera(camId) != null) {
            url = String.format(cameraBaseURL, camId);
        }
        return url;
    }

    private Pair<Integer, String> getCamera(int camId) {
        Pair<Integer, String> cam = null;
        for (Pair<Integer, String> camera : cameras) {
            if(camera.first == camId)
                cam = camera;
        }
        return cam;
    }

    public void addCamera(int cameraId, String cameraDescription) {
        Pair<Integer, String> camera = new Pair<Integer, String>(cameraId, cameraDescription);
        cameras.add(camera);
    }

    private void seedBaseData() {
        addCamera(58299, "M40 J1");
        addCamera(58316, "M40 J1A");
        addCamera(58350, "M40 J1A-J2");
        addCamera(58368, "M40 J1A-J2 curve");
        addCamera(55020, "M25 J16 under M40");
        addCamera(54975, "M25 J16-J15");
        addCamera(54965, "M25 J16-J15");
        addCamera(52280, "M4 J4B");
        addCamera(52288, "M4 J4B");
        addCamera(52296, "M4 J4B-J5");
        addCamera(52306, "M4 J5");
        addCamera(52350, "M4 J5-J6");
    }

}
