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

    public void onResume() {
        super.onResume();
        updateWebView();
    }

    public void onClick(View view) {
        if(view == refreshCameraButton) {
            webView.reload();
        }
        else if (view == returnHomeButton) {
            // super.onPause();
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