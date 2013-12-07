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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCameraDialog extends Activity implements View.OnClickListener {

    public final static String CAMERA_ID = "com.eu.lad.JamCamViewer.CAMERA_ID";
    public final static String CAMERA_DESCRIPTION = "com.eu.lad.JamCamViewer.CAMERA_DESCRIPTION";

    protected Button okButton;
    protected Button cancelButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_camera_dialog);

        okButton = (Button) findViewById(R.id.add_camera_ok_button);
        okButton.setOnClickListener(this);

        cancelButton = (Button) findViewById(R.id.add_camera_cancel_button);
        cancelButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        Intent intent = getIntent();

        if(view==okButton) {
            EditText cam_id = (EditText) findViewById(R.id.camera_id);
            EditText cam_description = (EditText) findViewById(R.id.camera_description);

            intent.putExtra(CAMERA_ID, new Integer(cam_id.getText().toString()));
            intent.putExtra(CAMERA_DESCRIPTION, cam_description.getText().toString());

            this.setResult(RESULT_OK, intent);
            finish();
        }
        else if(view==cancelButton) {
            this.setResult(RESULT_CANCELED, intent);
            finish();
        }
    }

}

