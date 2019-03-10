package com.example.saimon.yoga_statica.view;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.saimon.yoga_statica.R;

import java.util.prefs.Preferences;

public class SettingsFragment extends PreferenceFragmentCompat {




    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }

}
