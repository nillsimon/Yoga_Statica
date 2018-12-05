package com.example.saimon.yoga_statica;

import android.os.Bundle;
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

public class StopwatchFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int DELAY = 1000;

    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_RUNNING = "running";

    private Button resetBtn, startBtn, stopBtn;
    private TextView timerTV;
    private int seconds = 0;
    private boolean running = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        initViews(view);
        setOnClickListeners();
        checkBundle(savedInstanceState);
        runTimer();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_SECONDS, seconds);
        outState.putBoolean(KEY_RUNNING, running);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,  "In onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,  "In onStop()");
    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // your code
        }
    }*/

    private void initViews(View view) {
        resetBtn = view.findViewById(R.id.resetBtn);
        startBtn = view.findViewById(R.id.startBtn);
        stopBtn = view.findViewById(R.id.stopBtn);
        timerTV = view.findViewById(R.id.timerTV);
    }

    private void setOnClickListeners() {
        resetBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }

    private void checkBundle(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt(KEY_SECONDS);
            running = savedInstanceState.getBoolean(KEY_RUNNING);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.resetBtn) {
            resetTimer();
        } else if(view.getId() == R.id.startBtn) {
             running = true;
        } else if(view.getId() == R.id.stopBtn) {
            running = false;
        }
    }

    private void resetTimer() {
        running = false;
        seconds = 0;
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
                timerTV.setText(time);
                if(running) {
                    seconds++;
                }

                handler.postDelayed(this, DELAY);
            }
        });
    }
}
