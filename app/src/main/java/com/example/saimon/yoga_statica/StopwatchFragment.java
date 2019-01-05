package com.example.saimon.yoga_statica;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int DELAY = 1000;

    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_RUNNING = "running";

    private static final String FORMAT = "%02d:%02d";

    private Button resetBtn, start_Btn, stopBtn;
    private TextView timer_Assana, timer_Training, myString;
    private int seconds = 0;
    private boolean running = true;
    int time2 = 20000;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);


        initViews(view);
        checkBundle(savedInstanceState);
        startTimer();
        reverseTimer();
        runTimer();
        return view;
    }

    CountDownTimer cTimer = null;
    void startTimer() {
        cTimer = new CountDownTimer(time2, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
            }
        };
        cTimer.start();
    }

    private void reverseTimer() {
        new CountDownTimer(time2, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer_Assana.setText("" + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                cTimer.cancel();
                timer_Assana.setText("Out");
            }
        }.start();
    }
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / SECONDS_IN_HOUR;
                int minutes = (seconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE;
                int secs = seconds % 60;

                String time = String.format(Locale.US, "%d:%02d:%02d", hours, minutes, secs);
                timer_Training.setText(time);
                 if(running) {
                    seconds++;
                }
                handler.postDelayed(this, DELAY);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_SECONDS, seconds);
        outState.putBoolean(KEY_RUNNING, running);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }
    private void initViews(View view) {
        timer_Training = view.findViewById(R.id.timerTraining);
        timer_Assana = view.findViewById(R.id.timerAssan);
    }

    private void checkBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(KEY_SECONDS);
            running = savedInstanceState.getBoolean(KEY_RUNNING);
        }
    }
}