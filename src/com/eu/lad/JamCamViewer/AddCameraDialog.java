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
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCameraDialog extends DialogFragment implements View.OnClickListener {

    public final static String CAMERA_ID = "com.eu.lad.JamCamViewer.CAMERA_ID";
    public final static String CAMERA_DESCRIPTION = "com.eu.lad.JamCamViewer.CAMERA_DESCRIPTION";

    protected View dialogView;
    protected Button okButton;
    protected Button cancelButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogView = inflater.inflate(R.layout.add_camera_dialog, container, false);

        getDialog().setTitle(R.string.add_new_camera);

        okButton = (Button) dialogView.findViewById(R.id.add_camera_ok_button);
        okButton.setOnClickListener(this);

        cancelButton = (Button) dialogView.findViewById(R.id.add_camera_cancel_button);
        cancelButton.setOnClickListener(this);

        return dialogView;
    }

    public void onClick(View view) {
        Intent intent = getActivity().getIntent();
        int resultCode = Activity.RESULT_CANCELED;

        if(view==okButton) {
            EditText camera_id = (EditText) dialogView.findViewById(R.id.camera_id);
            EditText camera_description = (EditText) dialogView.findViewById(R.id.camera_description);

            String cam_id = camera_id.getText().toString();
            String cam_desc = camera_description.getText().toString();

            if ((cam_id != null) && !cam_id.isEmpty() &&
                    (cam_desc != null) && !cam_desc.isEmpty()) {
                intent.putExtra(CAMERA_ID, Integer.parseInt(cam_id));
                intent.putExtra(CAMERA_DESCRIPTION, cam_desc);
            }
            else {
                Toast.makeText(getActivity(), "Invalid data", Toast.LENGTH_SHORT).show();
            }

            resultCode = Activity.RESULT_OK;
        }
        else if(view==cancelButton) {
            resultCode = Activity.RESULT_CANCELED;
        }

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, null);
        dismiss();
    }

}

