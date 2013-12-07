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
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class JamCamViewerMainActivity extends Activity implements View.OnClickListener {

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";
    public final static int ADD_NEW_CAMERA_REQUEST = 1;

    protected final String cameraBaseURL = "http://www.trafficengland.com/trafficcamera.aspx?cameraUri=http://public.hanet.org.uk/cctvpublicaccess/html/%s.html";
    protected Button addNewCamera;
    protected Intent jamCamView;

    private JamCamInventory inventory;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        inventory = new JamCamInventory();

        // Initialise the Intent for the JamCamView activity
        jamCamView = new Intent(this, JamCamView.class);

        addNewCamera = (Button) findViewById(R.id.add_new_camera);
        addNewCamera.setOnClickListener(this);

        renderLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if(requestCode == ADD_NEW_CAMERA_REQUEST) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = resultData.getExtras();

                int camId = bundle.getInt(AddCameraDialog.CAMERA_ID);
                System.out.println(camId);
                String camDescription = bundle.getString(AddCameraDialog.CAMERA_DESCRIPTION);
                System.out.println(camDescription);

                inventory.addCamera(camId, camDescription);

                renderLayout();
            }
        }

    }

    public void onClick(View view) {
        if(view == addNewCamera) {
            Intent addCamera = new Intent(this, AddCameraDialog.class);
            startActivityForResult(addCamera, ADD_NEW_CAMERA_REQUEST);
        }
        else {
            Integer camId = view.getId();
            String camURL = String.format(cameraBaseURL, camId);

            jamCamView.putExtra(CAM_URL, camURL);

            startActivity(jamCamView);
        }
    }

    private void renderLayout() {
        LinearLayout buttonGrid = (LinearLayout) findViewById(R.id.buttonGrid);
        buttonGrid.removeAllViews();

        for (Pair<Integer, String> camera : inventory.getAll()) {
            Integer camId = camera.first;
            String location = camera.second;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            Button btn = new Button(this);
            btn.setId(camId);
            btn.setText(location);

            buttonGrid.addView(btn, params);

            btn.setOnClickListener(this);
        }
    }

}
