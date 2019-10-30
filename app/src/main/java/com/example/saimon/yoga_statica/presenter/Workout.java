package com.example.saimon.yoga_statica.presenter;

import com.example.saimon.yoga_statica.R;

public class Workout {
    private String name;
    private String descriptionList;
    private String description;
    private int imageResourceId;


    public static final Workout[] workouts = {
            new Workout(SlimApp.context.getString(R.string.trikonasana_name),
                    SlimApp.context.getString(R.string.trikonasanaList),
                    SlimApp.context.getString(R.string.trikonasana),
                    R.raw.trikonasana),
            new Workout(SlimApp.context.getString(R.string.virabxadrasana_name),
                    SlimApp.context.getString(R.string.virabxadrasanaList),
                    SlimApp.context.getString(R.string.virabxadrasana),
                    R.raw.virabxadrasana),
            new Workout(SlimApp.context.getString(R.string.virabxadrasana_one_name),
                    SlimApp.context.getString(R.string.virabxadrasana_oneList),
                    SlimApp.context.getString(R.string.virabxadrasana_one),
                    R.raw.virabxadrasana_one),
            new Workout(SlimApp.context.getString(R.string.bxudzhangasana_name),
                    SlimApp.context.getString(R.string.bxudzhangasanaList),
                    SlimApp.context.getString(R.string.bxudzhangasana),
                    R.raw.bxudzhangasana),
            new Workout(SlimApp.context.getString(R.string.navasana_name),
                    SlimApp.context.getString(R.string.navasanaList),
                    SlimApp.context.getString(R.string.navasana),
                    R.raw.navasana),
            new Workout(SlimApp.context.getString(R.string.gomukxasana_name),
                    SlimApp.context.getString(R.string.gomukxasanaList),
                    SlimApp.context.getString(R.string.gomukxasana),
                    R.raw.gomukxasana),
            new Workout(SlimApp.context.getString(R.string.radzhakapotasana_name),
                    SlimApp.context.getString(R.string.radzhakapotasanaList),
                    SlimApp.context.getString(R.string.radzhakapotasana),
                    R.raw.radzhakapotasana),
            new Workout(SlimApp.context.getString(R.string.bhekasana_name),
                    SlimApp.context.getString(R.string.bhekasanaList),
                    SlimApp.context.getString(R.string.bhekasana),
                    R.raw.bhekasana),
            new Workout(SlimApp.context.getString(R.string.shavasana_name),
                    SlimApp.context.getString(R.string.shavasanaList),
                    SlimApp.context.getString(R.string.shavasana),
                    R.raw.shavasana),
            new Workout(SlimApp.context.getString(R.string.pashchimottanasana_name),
                    SlimApp.context.getString(R.string.pashchimottanasanaList),
                   SlimApp.context.getString(R.string.pashchimottanasana),
                   R.raw.pashchimottanasana),
            new Workout(SlimApp.context.getString(R.string.virabxadrasanathree_name),
                    SlimApp.context.getString(R.string.virabxadrasanathreeList),
                    SlimApp.context.getString(R.string.virabxadrasanathree),
                    R.raw.virabxadrasanathree),
//            new Workout(SlimApp.context.getString(R.string._name),
//                    SlimApp.context.getString(R.string.List),
//                    SlimApp.context.getString(R.string.),
//                    R.drawable.),

    };

    private Workout(String name, String descriptionList, String description, int imageResourceId) {
        this.name = name;
        this.descriptionList = descriptionList;;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }
    public String getDescriptionList(){
        return descriptionList;
    }
    public String getDescription() {
        return description;

    }
    public int getImageResourceId() {
        return imageResourceId;
    }
}

