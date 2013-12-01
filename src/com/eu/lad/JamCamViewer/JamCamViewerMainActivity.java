package com.eu.lad.JamCamViewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Enumeration;
import java.util.Hashtable;

public class JamCamViewerMainActivity extends Activity implements View.OnClickListener {

    protected final String cameraBaseURL = "http://ichef.bbci.co.uk/travelnews/national/trafficcameras/highwaysagency/%s/serveimage";
    protected Hashtable<Integer, String> cameras;

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cameras = new Hashtable<Integer, String>();
        cameras.put(58350, "M40 J1A-J2");
        cameras.put(58368, "M40 J1A-J2 curve");

        LinearLayout buttonGrid = (LinearLayout) findViewById(R.id.buttonGrid);

        Enumeration<Integer> cameraKeys = cameras.keys();
        while(cameraKeys.hasMoreElements()) {
            Integer camId = cameraKeys.nextElement();
            String location = cameras.get(camId);

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

    public void onClick(View view) {
        Integer camId = view.getId();
        String camURL = String.format(cameraBaseURL, camId);

        Intent intent = new Intent(this, JamCamView.class);
        intent.putExtra(CAM_URL, camURL);

        startActivity(intent);

    }


/*
    for (int i = 1; i <= 20; i++) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Button btn = new Button(this);
        btn.setId(i);
        final int id_ = btn.getId();
        btn.setText("button " + id_);
        btn.setBackgroundColor(Color.rgb(70, 80, 90));
        linear.addView(btn, params);
        btn1 = ((Button) findViewById(id_));
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
    */
}
