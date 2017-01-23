package focus.focus.mainscreen.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import focus.focus.R;
import focus.focus.settingscreen.view.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;

    public static final String FIRST_START = "firstStart";
    public static final String MAIN_PREFERENCES = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (getSupportFragmentManager().getFragments() != null && !getSupportFragmentManager().getFragments().isEmpty()) {
            mainFragment = (MainFragment) getSupportFragmentManager().getFragments().get(0);
        } else {
            mainFragment = new MainFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.conteiner, mainFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);

        return super.onOptionsItemSelected(menuItem);
    }
}
