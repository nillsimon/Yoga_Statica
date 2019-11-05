package com.simon.yoga_statica.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saimon.yoga_statica.R;
import com.simon.yoga_statica.presenter.Workout;


public class AsanaFragment extends Fragment {

    private AsanaFragment mViewModel;
    public static AsanaFragment newInstance() {
        return new AsanaFragment();
    }
    private final String STOPWATCH_FRAGMENT_TAG = "com.example.saimon.yoga_statica";
    private int workoutId;
    TextView title;
    TextView description;
    ImageView imageResourceId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asana, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setFields();
        initStopwatch();
    }

    private void initViews(View view) {
        title = view.findViewById(R.id.textTitle);
        description = view.findViewById(R.id.textDescription);
        imageResourceId = view.findViewById(R.id.imageWorkout);
    }

    public void setFields() {
        Workout workout = Workout.workouts[workoutId];
        title.setText(workout.getName());
        description.setText(workout.getDescription());
        imageResourceId.setImageResource(workout.getImageResourceId());
    }

    private void initStopwatch() {
        FragmentManager fragmentManager = getChildFragmentManager(); //не support! Именно child.
        TimerFragment stopwatchFragment =
                (TimerFragment)fragmentManager.findFragmentByTag(STOPWATCH_FRAGMENT_TAG);
        if(stopwatchFragment == null) {
            stopwatchFragment = new TimerFragment();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.stopwatchContainer, stopwatchFragment, STOPWATCH_FRAGMENT_TAG);
        transaction.commit();
    }
    public void setWorkout(int id) {
        this.workoutId = id;
    }

}

