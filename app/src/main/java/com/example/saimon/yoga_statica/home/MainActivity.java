package com.example.saimon.yoga_statica.home;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.saimon.yoga_statica.R;
import com.example.saimon.yoga_statica.view.AsanaFragment;
import com.example.saimon.yoga_statica.view.ListFragment;
import com.example.saimon.yoga_statica.view.SettingsFragment;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int DELAY = 1000;
    private int seconds = 0;
    TextView timer_Training, timer_Assana;
    MediaPlayer mediaPlayer;

    public int time2 = 30000;
    FrameLayout container;
    private boolean running = true;
    boolean isStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // ButterKnife.bind(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, AsanaFragment.newInstance())
                    .commitNow();
        }
        initViews();
        initListFragment();
        showAsFragmentAtStart();
    }

    public void initViews() {
        container = findViewById(R.id.fragmentContainer);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beeps_s);

    }

    private void showAsFragmentAtStart() {
        if (container.getTag().equals("tablet_display")) {
            initDetailFragment(0);

        }
    }

    public void initDetailFragment(int position) {
        AsanaFragment detailFragment = new AsanaFragment();
        detailFragment.setWorkout(position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    void initListFragment() {
        if (container.getTag().equals("usual_display")) {
            ListFragment listFragment = new ListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, listFragment);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        } else {

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the VisualizerActivity
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickStart(View v) {

        megaTimer();
        startTimer();
        reverseTimer();
        timer_Assana = findViewById(R.id.timerAsana);
        timer_Training = findViewById(R.id.timerTraining);

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
    public void reverseTimer() {
        new CountDownTimer(time2, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer_Assana.setText("" + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                mediaPlayer.start();
                cTimer.cancel();
                timer_Assana.setText("Bravo");
            }
        }.start();
    }
    private void megaTimer() {
        if(isStarted == false){
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
                    isStarted = true;
                }
                handler.postDelayed(this, DELAY);
            }
        });
    }
    }


}
