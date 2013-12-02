package com.eu.lad.JamCamViewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Vector;

public class JamCamViewerMainActivity extends Activity implements View.OnClickListener {

    public final static String CAM_URL = "com.eu.lad.JamCamViewer.CAM_URL";

    protected final String cameraBaseURL = "http://www.trafficengland.com/trafficcamera.aspx?cameraUri=http://public.hanet.org.uk/cctvpublicaccess/html/%s.html";
    protected Intent jamCamView;

    private Vector<Pair<Integer, String>> cameras;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        seedBaseData();

        // Initialise the Intent for the JamCamView activity
        jamCamView = new Intent(this, JamCamView.class);

        LinearLayout buttonGrid = (LinearLayout) findViewById(R.id.buttonGrid);

        for (Pair<Integer, String> camera : cameras) {
            Integer camId = camera.first;
            String location = camera.second;

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

    private void seedBaseData() {
        cameras = new Vector<Pair<Integer, String>>();
        cameras.add(new Pair<Integer, String>(58299, "M40 J1"));
        cameras.add(new Pair<Integer, String>(58316, "M40 J1A"));
        cameras.add(new Pair<Integer, String>(58350, "M40 J1A-J2"));
        cameras.add(new Pair<Integer, String>(58368, "M40 J1A-J2 curve"));
        cameras.add(new Pair<Integer, String>(55020, "M25 J16 under M40"));
        cameras.add(new Pair<Integer, String>(54975, "M25 J16-J15"));
        cameras.add(new Pair<Integer, String>(54965, "M25 J16-J15"));
        cameras.add(new Pair<Integer, String>(52280, "M4 J4B"));
        cameras.add(new Pair<Integer, String>(52288, "M4 J4B"));
        cameras.add(new Pair<Integer, String>(52296, "M4 J4B-J5"));
        cameras.add(new Pair<Integer, String>(52306, "M4 J5"));
        cameras.add(new Pair<Integer, String>(52350, "M4 J5-J6"));
    }
}
