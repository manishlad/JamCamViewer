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
import android.webkit.WebView;
import android.widget.Button;

public class JamCamView extends Activity implements View.OnClickListener {

    protected WebView webView;
    protected Button refreshCameraButton;
    protected Button returnHomeButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jam_cam_view);

        webView = (WebView) findViewById(R.id.webView);
        // webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        refreshCameraButton = (Button) findViewById(R.id.refresh_camera_button);
        refreshCameraButton.setOnClickListener(this);

        returnHomeButton = (Button) findViewById(R.id.return_home_button);
        returnHomeButton.setOnClickListener(this);
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
        else if (view == returnHomeButton) {
            finish();
        }
    }

    private void updateWebView() {
        Intent intent = getIntent();
        String camURL = intent.getStringExtra(JamCamViewerMainActivity.CAM_URL);

        webView.loadUrl(camURL);
        webView.refreshDrawableState();
    }

}