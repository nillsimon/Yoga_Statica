package com.example.saimon.yoga_statica;
class Workout {
    private String name;
    private String descriptionList;
    private String description;
    private int imageResourceId;

    static final Workout[] workouts = {
            new Workout(SlimApp.context.getString(R.string.riveting_name),
                    SlimApp.context.getString(R.string.rivetingList),
                    SlimApp.context.getString(R.string.riveting),
                    R.drawable.riveting),
            new Workout(SlimApp.context.getString(R.string.kick_back_name),
                    SlimApp.context.getString(R.string.kick_backList),
                    SlimApp.context.getString(R.string.kick_back),
                    R.drawable.kickback),
            new Workout(SlimApp.context.getString(R.string.squat_name),
                    SlimApp.context.getString(R.string.squatList),
                    SlimApp.context.getString(R.string.squat),
                    R.drawable.squat),
            new Workout(SlimApp.context.getString(R.string.twisting_name),
                    SlimApp.context.getString(R.string.twistingList),
                    SlimApp.context.getString(R.string.twists),
                    R.drawable.twists),
            new Workout(SlimApp.context.getString(R.string.jumping_name),
                    SlimApp.context.getString(R.string.jumpingList),
                    SlimApp.context.getString(R.string.jumping),
                    R.drawable.jumping),
            new Workout(SlimApp.context.getString(R.string.boat_name),
                    SlimApp.context.getString(R.string.boatList),
                    SlimApp.context.getString(R.string.boat),
                    R.drawable.boat),
            new Workout(SlimApp.context.getString(R.string.twisting_name),
                    SlimApp.context.getString(R.string.twistingList),
                    SlimApp.context.getString(R.string.twisting),
                    R.drawable.twisting)
    };

    // В объекте Workout хранится имя и описание
    private Workout(String name, String descriptionList, String description, int imageResourceId) {
        this.name = name;
        this.descriptionList = descriptionList;;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    String getName() {
        return name;
    }

    String getDescriptionList(){
        return descriptionList;
    }

    String getDescription() {
        return description;
    }

    int getImageResourceId() {
        return imageResourceId;
    }
}

