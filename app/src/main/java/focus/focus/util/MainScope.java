package focus.focus.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by User on 13.01.2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainScope {
}
