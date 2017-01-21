package focus.focus.settingscreen.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import focus.focus.R;

public class SettingsActivity extends AppCompatActivity {

    SettingsFragment settingsFragment;

    public static final String TASK = "TASK";
    public static final String BREAK = "BREAK";
    public static final String LONG_BREAK = "LONG BREAK";
    public static final String COUNT_BREAK = "COUNT BREAK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        settingsFragment = (SettingsFragment) getLastCustomNonConfigurationInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(settingsFragment == null) {
            settingsFragment = new SettingsFragment();
        }

        ft.replace(R.id.activity_setting, settingsFragment);
        ft.commit();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return settingsFragment;
    }
}
