package com.example.saimon.yoga_statica;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutListFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.workout_list_fragment, container, false);
        initWorkoutList(rootView);

        return rootView;// Вернем View фрагмента нашей Activity
    }

    private void initWorkoutList(View rootView) {
        // Найдем наш RecyclerView
        RecyclerView workoutRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
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

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.workout_list_item, parent, false));
            workoutNameTextView = itemView.findViewById(R.id.workoutNameTextView);
        }

        void bind(final int position) {
            String category = Workout.workouts[position].getName();
            workoutNameTextView.setText(category);
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
            return Workout.workouts.length;
        }
    }
}

