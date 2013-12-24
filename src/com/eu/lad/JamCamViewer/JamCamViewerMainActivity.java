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

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class JamCamViewerMainActivity extends Activity
    implements JamCamViewerMainFragment.OnCameraSelectedListener {

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";
    public final static int ADD_NEW_CAMERA_REQUEST = 1;

    private JamCamInventory inventory;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialise the camera inventory
        inventory = new JamCamInventory(this);

        // Check that the activity is using the layout version with
        // the main_fragment_container FrameLayout
        if (findViewById(R.id.main_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            JamCamViewerMainFragment firstFragment = new JamCamViewerMainFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.main_fragment_container, firstFragment);
            transaction.commit();
        }

    }

    public void cameraSelected(int camera) {
        JamCamView jamCamViewFragment = new JamCamView();
        getIntent().putExtra(CAM_URL, inventory.getCameraURL(camera));
        jamCamViewFragment.setArguments(getIntent().getExtras());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.main_fragment_container, jamCamViewFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public JamCamInventory getInventory() {
        return inventory;
    }

}
