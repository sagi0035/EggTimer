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


    // the seekbar is a variable that will allow us to determine how long we want the timer to go for
    SeekBar timerSeekBar;
    TextView textView;



    // so on the button click we will start the timing
    public void buttonClicker(View view) {



        // so first we will disable the seekbar so that a new time cannot ben set
        timerSeekBar.setEnabled(false);
        // so we here create the timer and keep track of its tick
        CountDownTimer countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 1000, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("Click", "Successful");
                // so we will send to the minutes/secs tracker so the textview follows the progress of the timer
                doStuff((int) l / 1000);

            }

            @Override
            public void onFinish() {
                // so on the finish we will set the recording to play
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                // and we once again enable the seekbar
                timerSeekBar.setEnabled(true);

            }
        }.start();
    }



    public void doStuff(int rem) {

        // so we set the minutes and the seconds
        int minutes = rem / 60;
        int seconds = rem - (minutes*60);

        // so if the seconds are less than 10 we will add a 0 and if not we do not
        if (seconds < 10) {
            // so the text view will show the current time left
            textView.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
        } else {
            textView.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // so we initialise our views
        timerSeekBar = (SeekBar) findViewById(R.id.seekBar2);
        textView = (TextView) findViewById(R.id.textView4);

        // we set a max value of 10 minutes for the timer so as to not have the timer be to long
        timerSeekBar.setMax(600);
        // and at start time we will set it to 0
        timerSeekBar.setProgress(0);

        // and this to track the seekbar (and changes to it)
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // so we send it to the function that gets all the minutes and seconds
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
