package focus.focus.settingscreen;

import javax.inject.Inject;
import dagger.Module;
import dagger.Provides;
import focus.focus.App;

@Module
public class SettingsModule {

    private SettingsContract.View view;

    @Inject
    App app;

    public SettingsModule(SettingsContract.View view)
    {
        this.view = view;
    }

    @Provides
    public SettingsContract.View providesSettingView()
    {
        return view;
    }
}
