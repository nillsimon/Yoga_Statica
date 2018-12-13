package com.example.saimon.yoga_statica;

public class ListTitle {

    private String name;
    private String description;
    private int imageResourceId;
    private boolean listItemCheck;

    static final ListTitle[] listArrTitle = {
            new ListTitle(SlimApp.context.getString(R.string.riveting_name),
                    SlimApp.context.getString(R.string.rivetingList),
                    R.drawable.riveting),
            new ListTitle(SlimApp.context.getString(R.string.kick_back_name),
                    SlimApp.context.getString(R.string.kick_backList),
                    R.drawable.kickback),
            new ListTitle(SlimApp.context.getString(R.string.squat_name),
                    SlimApp.context.getString(R.string.squatList),
                    R.drawable.squat),
            new ListTitle(SlimApp.context.getString(R.string.twists_name),
                    SlimApp.context.getString(R.string.twistsList),
                    R.drawable.twists),
            new ListTitle(SlimApp.context.getString(R.string.jumping_name),
                    SlimApp.context.getString(R.string.jumpingList),
                    R.drawable.jumping),
            new ListTitle(SlimApp.context.getString(R.string.boat_name),
                    SlimApp.context.getString(R.string.boatList),
                    R.drawable.boat),
            new ListTitle(SlimApp.context.getString(R.string.twisting_name),
                    SlimApp.context.getString(R.string.twistingList),
                    R.drawable.twisting)
    };

    // В объекте Workout хранится имя и описание
    private ListTitle(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;

    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    int getImageResourceId() {
        return imageResourceId;

    }
}
