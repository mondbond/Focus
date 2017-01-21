package focus.focus;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {

    SharedPreferences getSharedPreference();

    Context getContext();
}
