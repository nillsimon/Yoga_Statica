package com.example.saimon.yoga_statica;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.arch.lifecycle.ViewModel;


public class WorkoutDetailFragment extends Fragment {

    private WorkoutDetailFragment mViewModel;
    public static WorkoutDetailFragment newInstance() {
        return new WorkoutDetailFragment();
    }
    private final String STOPWATCH_FRAGMENT_TAG = "geekbrains.lesson6.slim.stopwatch_fragment_tag";
    // Переменная, по которой мы будем брать соответствующее упражнение в классе Workout
    private int workoutId;
    TextView title;
    TextView description, textprofit;
    ImageView imageResourceId;
    // Основной метод для создания Фрагмента (по аналогии с onCreate  у Activity)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Надуваем Фрагмент
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
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

    private void setFields() {
        Workout workout = Workout.workouts[workoutId];

        title.setText(workout.getName());
        description.setText(workout.getDescription());
        imageResourceId.setImageResource(workout.getImageResourceId());
    }

    private void initStopwatch() {
        FragmentManager fragmentManager = getChildFragmentManager(); //не support! Именно child.
        StopwatchFragment stopwatchFragment =
                (StopwatchFragment)fragmentManager.findFragmentByTag(STOPWATCH_FRAGMENT_TAG);

        if(stopwatchFragment == null) {
            stopwatchFragment = new StopwatchFragment();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.stopwatchContainer, stopwatchFragment, STOPWATCH_FRAGMENT_TAG);
        transaction.commit();
    }

    // Публичный метод, с помощью которого мы из Activity передаем во фрагмент нужное значение для упражнения
    public void setWorkout(int id) {
        this.workoutId = id;
    }
}

