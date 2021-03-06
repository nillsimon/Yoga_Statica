package com.simon.yoga_statica.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.yoga_statica.home.MainActivity;
import com.example.saimon.yoga_statica.R;
import com.simon.yoga_statica.presenter.Workout;

public class ListFragment extends Fragment {
    private int workoutId;
    private int listTextId;
    private TextView listTitle;
    private TextView listDescription;
    private ImageView listImageResourceId;
    private RecyclerView recyclerItemView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
        initWorkoutList(rootView);
        return rootView;
    }

    public void initWorkoutList(View rootview) {
       RecyclerView workoutRecyclerView = rootview.findViewById(R.id.recyclerFragmentView);
       LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       workoutRecyclerView.setLayoutManager(layoutManager);
       workoutRecyclerView.setAdapter(new MyAdapter());

    }
    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView workoutNameTextView;
        private TextView workoutNameTextView2;
        private ImageView imageWorkout;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.frame_item, parent, false));
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
    public void setWorkout(int id) {
        this.listTextId = id;
    }
}


