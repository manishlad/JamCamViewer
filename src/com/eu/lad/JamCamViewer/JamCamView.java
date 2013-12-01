package com.eu.lad.JamCamViewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class JamCamView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jam_cam_view);

        Intent intent = getIntent();
        String camURL = intent.getStringExtra(JamCamViewerMainActivity.CAM_URL);

        WebView webView = (WebView) findViewById(R.id.webView);
//        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(camURL);

    }
}