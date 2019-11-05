package com.simon.yoga_statica.view;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import com.example.saimon.yoga_statica.R;

public class SettingsFragment extends PreferenceFragmentCompat {




    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }

}
