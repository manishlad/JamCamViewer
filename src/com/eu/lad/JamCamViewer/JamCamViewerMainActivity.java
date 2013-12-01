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

    protected final String cameraBaseURL = "http://www.trafficengland.com/trafficcamera.aspx?cameraUri=http://public.hanet.org.uk/cctvpublicaccess/html/%s.html";
    protected Hashtable<Integer, String> cameras;

    protected Intent jamCamView;

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cameras = new Hashtable<Integer, String>();
        cameras.put(58299, "M40 J1");
        cameras.put(58316, "M40 J1A");
        cameras.put(58350, "M40 J1A-J2");
        cameras.put(58368, "M40 J1A-J2 curve");
        cameras.put(55020, "M25 J16 under M40");
        cameras.put(54975, "M25 J16-J15");
        cameras.put(54965, "M25 J16-J15");
        cameras.put(52280, "M4 J4B");
        cameras.put(52288, "M4 J4B");
        cameras.put(52296, "M4 J4B-J5");
        cameras.put(52306, "M4 J5");
        cameras.put(52350, "M4 J5-J6");

        // Initialise the Intent for the JamCamView activity
        jamCamView = new Intent(this, JamCamView.class);

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

        jamCamView.putExtra(CAM_URL, camURL);

        startActivity(jamCamView);

    }
}
