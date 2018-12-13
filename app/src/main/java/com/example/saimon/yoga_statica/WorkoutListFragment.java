package com.example.saimon.yoga_statica;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkoutListFragment extends Fragment {
    private int workoutId;
    private int listTextId;
    private TextView listTitle;
    private TextView listDescription;
    private ImageView listImageResourceId;
    private RecyclerView recyclerItemView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.workout_list_fragment, container, false);
        initWorkoutList(rootView);

        return rootView;// Вернем View фрагмента нашей Activity
    }

    public void initWorkoutList(View rootview) {
        // Найдем наш RecyclerView
        RecyclerView workoutRecyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerFragmentView);
        // Создадим LinearLayoutManager. Обратите внимание, как мы теперь получаем контекст
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // Обозначим ориентацию
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // Назначим нашему RecyclerView созданный ранее layoutManager
        workoutRecyclerView.setLayoutManager(layoutManager);
        // Назначим нашему RecyclerView адаптер
        workoutRecyclerView.setAdapter(new MyAdapter());

    }
    // Класс, который содержит в себе все элементы списка
    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView workoutNameTextView;
        private TextView workoutNameTextView2;
        private ImageView imageWorkout;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.workout_list_item, parent, false));
            workoutNameTextView = itemView.findViewById(R.id.listTextTitle);
            workoutNameTextView2 = itemView.findViewById(R.id.listTextDescription);
            imageWorkout = itemView.findViewById(R.id.imageListWorkout);
        }

        void bind(final int position) {

            Workout workout = Workout.workouts[workoutId];

            String category = Workout.workouts[position].getName();
            String category2 = Workout.workouts[position].getDescriptionList();
            int category3 = Workout.workouts[position].getImageResourceId();



            workoutNameTextView.setText(category);
            workoutNameTextView2.setText(category2);
            imageWorkout.setImageResource(category3);


            workoutNameTextView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ((MainActivity) getActivity()).initDetailFragment(position);
                    } catch (NullPointerException exc) {
                        exc.printStackTrace();
                    }
                }
            });
            workoutNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ((MainActivity) getActivity()).initDetailFragment(position);
                    } catch (NullPointerException exc) {
                        exc.printStackTrace();
                    }
                }
            });
            imageWorkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ((MainActivity) getActivity()).initDetailFragment(position);
                    } catch (NullPointerException exc) {
                        exc.printStackTrace();
                    }
                }
            });
        }

    }

    // Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public @NonNull MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Обратите внимание, как мы теперь получаем контекст
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return ListTitle.listArrTitle.length;
        }
    }

    // Публичный метод, с помощью которого мы из Activity передаем во фрагмент нужное значение для упражнения
    public void setWorkout(int id) {
        this.listTextId = id;
    }
}


