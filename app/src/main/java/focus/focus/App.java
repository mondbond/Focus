package focus.focus;

import android.app.Application;

public class App extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent
                = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent()
    {
        return  mAppComponent;
    }
}
