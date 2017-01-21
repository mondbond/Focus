package focus.focus.mainscreen;

import javax.inject.Singleton;

import dagger.Component;
import focus.focus.AppComponent;
import focus.focus.mainscreen.presenter.MainScreenPresenter;
import focus.focus.mainscreen.view.MainFragment;
import focus.focus.util.MainScope;
@MainScope
@Singleton
@Component(dependencies = {AppComponent.class}, modules = {MainScreenModule.class})
public interface MainScreenComponent {

    void inject(MainFragment fragment);

}
