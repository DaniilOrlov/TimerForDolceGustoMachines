package orlov.daniil.timerfordolcegustomachines.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import orlov.daniil.timerfordolcegustomachines.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey);
    }
}
