package focus.focus.mainscreen;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import focus.focus.mainscreen.presenter.MainScreenPresenter;
import focus.focus.util.MainScope;

@Module
public class MainScreenModule {

    private MainScreenContract.View view;

    @Inject
    MainScreenPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;

    public MainScreenModule(MainScreenContract.View view)
    {
        this.view = view;
    }

    @Provides
    public MainScreenContract.View providesView()
    {
        return view;
    }

    @Provides
    @Singleton
    public Timer providesTimer() {
        return new Timer();
    }
}
