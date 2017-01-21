package focus.focus.settingscreen.presenter;

import android.content.SharedPreferences;
import javax.inject.Inject;

import focus.focus.settingscreen.SettingsContract;

public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View view;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public SettingsPresenter(SettingsContract.View view)
    {
        this.view = view;
    }

    public void savePreferences(String name, int result) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, result);
        editor.apply();
    }
}
