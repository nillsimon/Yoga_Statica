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
import android.widget.Toast;

import com.example.saimon.yoga_statica.R;
import com.example.saimon.yoga_statica.view.AsanaFragment;
import com.example.saimon.yoga_statica.view.ListFragment;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int DELAY = 1000;
    private int seconds = 0;
    TextView timer_Training;
    TextView timer_Asana;
    MediaPlayer mediaPlayer;

    public int time2 = 30000;
    FrameLayout container;
    private boolean running = true;
    boolean isStarted = false;
    boolean isReversStarted = false;
    boolean isChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beeps_m);

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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.time_30:
                time2 = 30000;
                Toast.makeText(this, "Время асаны 30 секунд", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.time_45:
                time2 = 45000;
                Toast.makeText(this, "Время асаны 45 секунд", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.time_90:
                time2 = 90000;
                Toast.makeText(this, "Время асаны 90 секунд", Toast.LENGTH_SHORT).show();

                return true;
                case R.id.home:
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onClickStart(View v) {

        megaTimer();
        startTimer();
        reverseTimer();
        timer_Asana = findViewById(R.id.timerAsana);
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
        if (!isReversStarted){
            new CountDownTimer(time2, 1000) {
                public void onTick(long millisUntilFinished) {
                    int seconds = (int) (millisUntilFinished / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    timer_Asana.setText("" + String.format("%02d", minutes)
                            + ":" + String.format("%02d", seconds));
                    isReversStarted = true;
                }

                public void onFinish() {
                    mediaPlayer.start();
                    cTimer.cancel();
                    timer_Asana.setText("Bravo!");
                    isReversStarted = false;
                }
            }.start();
        }
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
