package com.macernow.djstava.m3u8player;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    private VideoView myVideoView;
    private int position = 0;
    private MediaController mediaControls;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the layout from video_main.xml
        setContentView(R.layout.activity_main);

        if (mediaControls == null) {
            mediaControls = new MediaController(MainActivity.this);
        }

        // Find your VideoView in your video_main.xml layout
        myVideoView = (VideoView) findViewById(R.id.videoView);

        try {
            myVideoView.setMediaController(mediaControls);
//            myVideoView.setVideoURI(Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8"));
            myVideoView.setVideoURI(Uri.parse("http://pl.youku.com/playlist/m3u8?vid=XMTg2ODA2ODU4MA==&type=hd2&ts=1482064531&keyframe=1&did=7be904b348e25ae182914464a9194f0c&ep=tMRuKmRnUh0hWW663EULEprWRhqmi8bxu0iWrXER1vWcyTXBUBFU7RUz2OkS83zh&sid=048206453081320003f16&token=1252&ctype=20&ev=1&oip=2032840751"));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();
        myVideoView.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }
}
