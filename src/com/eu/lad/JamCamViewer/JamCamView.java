package com.eu.lad.JamCamViewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class JamCamView extends Activity {

    WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jam_cam_view);

        webView = (WebView) findViewById(R.id.webView);
        // webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void onResume() {
        super.onResume();
        updateWebView();
    }


    private void updateWebView() {
        Intent intent = getIntent();
        String camURL = intent.getStringExtra(JamCamViewerMainActivity.CAM_URL);

        webView.loadUrl(camURL);
        webView.refreshDrawableState();
    }

    public void onRefreshCameraPressed(View view) {
        webView.reload();
    }

    public void onReturnPressed(View view) {
        // super.onPause();
        finish();
    }
}