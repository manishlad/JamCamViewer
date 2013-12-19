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
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class JamCamViewerMainFragment extends Fragment implements View.OnClickListener {

    protected Button addNewCamera;
    protected View mainGridView;

    private JamCamInventory inventory;
    private OnCameraSelectedListener cameraSelectedListener;

    // Container Activity must implement this interface
    public interface OnCameraSelectedListener {
        public void cameraSelected(int camera);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            cameraSelectedListener = (OnCameraSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCameraSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inventory = ((JamCamViewerMainActivity) getActivity()).getInventory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainGridView = inflater.inflate(R.layout.main_grid, container, false);

        addNewCamera = (Button) mainGridView.findViewById(R.id.add_new_camera);
        addNewCamera.setOnClickListener(this);

        renderButtonGrid();

        return mainGridView;
    }

    public void onClick(View view) {
        if(view == addNewCamera) {
            AddCameraDialog dialog = new AddCameraDialog();
            dialog.setArguments(getActivity().getIntent().getExtras());
            dialog.setTargetFragment(this, JamCamViewerMainActivity.ADD_NEW_CAMERA_REQUEST);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            dialog.show(transaction, "AddNewCamera");
        }
        else {
            Integer camId = view.getId();
            cameraSelectedListener.cameraSelected(camId);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == JamCamViewerMainActivity.ADD_NEW_CAMERA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                int camId = getActivity().getIntent().getIntExtra(AddCameraDialog.CAMERA_ID, 0);
                String camDescription = getActivity().getIntent().getStringExtra(AddCameraDialog.CAMERA_DESCRIPTION);

                inventory.addCamera(camId, camDescription);

                renderButtonGrid();
            }
        }
    }

    private void renderButtonGrid() {
        LinearLayout buttonGrid = (LinearLayout) mainGridView.findViewById(R.id.buttonGrid);
        buttonGrid.removeAllViews();

        for (Pair<Integer, String> camera : inventory.getAll()) {
            Integer camId = camera.first;
            String location = camera.second;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            Button btn = new Button(getActivity());
            btn.setId(camId);
            btn.setText(location);

            buttonGrid.addView(btn, params);

            btn.setOnClickListener(this);
        }
    }

}