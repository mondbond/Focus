package focus.focus.settingscreen;

import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Component;
import focus.focus.AppComponent;
import focus.focus.settingscreen.presenter.SettingsPresenter;
import focus.focus.settingscreen.view.SettingsFragment;
import focus.focus.util.MainScope;

/**
 * Created by User on 13.01.2017.
 */
@Component(dependencies = {AppComponent.class}, modules = {SettingsModule.class})
public interface SettingsComponent {

    void inject (SettingsFragment fragment);

}
