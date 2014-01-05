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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

public class RoutePointDisplayFragment extends Fragment implements View.OnClickListener {

    public static final String ROUTEPOINTURL = "ROUTE_POINT_CAMERA_URL";

    protected WebView webView;
    protected Button refreshCameraButton;

    private String mRoutePointCameraUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View routePointView = inflater.inflate(R.layout.jam_cam_view, container, false);

        Bundle args = getArguments();
        mRoutePointCameraUrl = args.getString(ROUTEPOINTURL);

        webView = (WebView) routePointView.findViewById(R.id.webView);
        // webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        refreshCameraButton = (Button) routePointView.findViewById(R.id.refresh_camera_button);
        refreshCameraButton.setOnClickListener(this);

        return routePointView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWebView();
    }

    public void onClick(View view) {
        if(view == refreshCameraButton) {
            webView.reload();
        }
    }

    private void updateWebView() {
        //Intent intent = getActivity().getIntent();
        //String camURL = intent.getStringExtra(JamCamViewerMainActivity.CAM_URL);

        webView.loadUrl(mRoutePointCameraUrl);
        webView.refreshDrawableState();
    }

}