package com.example.connectfour;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GameOptionsFragment extends Fragment {
    private RadioButton easy;
    private RadioButton med;
    private RadioButton hard;
    public static final String GAME_MODE = "com.example.connectfour.gameoptions";
    private final String TAG = "GameOptions";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_options, container, false);

        // Extract level ID from SharedPreferences
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        int modeId = sharedPref.getInt("mode", R.string.easyMode);

        // Select the radio button matching the level ID
        int radioId = R.id.radio_easy;

        if (modeId == R.string.easyMode) {
            radioId = R.id.radio_easy;
        }
        else if (modeId == R.string.mediumMode) {
            radioId = R.id.radio_medium;
        }
        else if (modeId == R.string.hardMode) {
            radioId = R.id.radio_hard;
        }

        RadioButton radio = rootView.findViewById(radioId);
        radio.setChecked(true);

        // Add click callback to all radio buttons
        RadioGroup modeRadioGroup = rootView.findViewById(R.id.mode_radio_group);
        for (int i = 0; i < modeRadioGroup.getChildCount(); i++) {
            radio = (RadioButton) modeRadioGroup.getChildAt(i);
            radio.setOnClickListener(this::onLevelSelected);
        }

        return rootView;
    }

    public void onLevelSelected(View view) {
        int levelId = R.string.easyMode;
        if (view.getId() == R.id.radio_easy) {
            levelId = R.string.easyMode;
        } else if (view.getId() == R.id.radio_medium) {
            levelId = R.string.easyMode;
        } else if (view.getId() == R.id.radio_hard) {
            levelId = R.string.easyMode;
        }

        // Save selected level ID in SharedPreferences
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("mode", levelId);
        editor.apply();
    }
}
