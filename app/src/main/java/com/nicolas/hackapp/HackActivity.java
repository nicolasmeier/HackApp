package com.nicolas.hackapp;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class HackActivity extends Activity {

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private Button mButton;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack);
        this.getActionBar().hide();

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mButton = (Button) findViewById(R.id.button);

        mProgress.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        mProgress.setScaleY(40f);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHack();
            }
        });

    }

    public void startHack(){
        mProgressStatus = 0;
        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doHack(mProgressStatus);

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
                try {
                    Thread.sleep(3000);
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(0);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public int doHack(int i){
        int a = i + 1;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a;
    }

}
