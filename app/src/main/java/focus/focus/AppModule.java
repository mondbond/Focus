package focus.focus;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import focus.focus.mainscreen.view.MainActivity;

@Module
public class AppModule {

    Application app;
    SharedPreferences sharedPreferences;

    public AppModule (Application app)
    {
        this.app = app;
    }

    @Provides
    public Application providesgetApp()
    {
        return app;
    }

    @Provides
    SharedPreferences providesSharedPreference() {
        sharedPreferences = app.getSharedPreferences(MainActivity.MAIN_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    @Provides
    public Context providesContext()
    {
        return app.getApplicationContext();
    }
}
