package com.example.seana.eggtimerfinal;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView textView;



    public void buttonClicker(View view) {



        timerSeekBar.setEnabled(false);
        CountDownTimer countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 1000, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("Click", "Successful");
                doStuff((int) l / 1000);

            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                timerSeekBar.setEnabled(true);

            }
        }.start();
    }



    public void doStuff(int rem) {

        int minutes = rem / 60;
        int seconds = rem - (minutes*60);

        if (seconds < 10) {
            textView.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
        } else {
            textView.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerSeekBar = (SeekBar) findViewById(R.id.seekBar2);
        textView = (TextView) findViewById(R.id.textView4);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(0);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                doStuff(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });









    }
}
