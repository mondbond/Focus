package focus.focus.mainscreen.presenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import javax.inject.Inject;
import focus.focus.R;
import focus.focus.mainscreen.MainScreenContract;
import focus.focus.mainscreen.Timer;
import focus.focus.mainscreen.view.MainActivity;
import focus.focus.settingscreen.view.SettingsActivity;

public class MainScreenPresenter implements MainScreenContract.Presenter, Timer.Callback {

    public MainScreenContract.View view;

    @Inject
    Timer timer;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Context context;

    private int sec;
    private int min;
    private int count = 0;
    private boolean work = true;
    String status;
    private int ticker;

    public final String STATUS_ZERO = "zero";
    public final String STATUS_WORK = "work";
    public final String STATUS_PAUSE = "pause";

    @Inject
    public MainScreenPresenter (MainScreenContract.View view) {
        this.view = view;
        status = STATUS_ZERO;
    }

    public void checkFirstStart() {
        if(!sharedPreferences.getBoolean(MainActivity.FIRST_START, false))
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean(MainActivity.FIRST_START, true);

            editor.putInt(SettingsActivity.TASK, 40);
            editor.putInt(SettingsActivity.BREAK, 10);
            editor.putInt(SettingsActivity.LONG_BREAK, 15);
            editor.putInt(SettingsActivity.COUNT_BREAK, 3);
            editor.apply();
        }
    }

    public void beforeStart() {
        timer.resgisterCallback(this);
        int time;
        int count = sharedPreferences.getInt(SettingsActivity.COUNT_BREAK, 0);
        if(work) {
            time = sharedPreferences.getInt(SettingsActivity.TASK, 0);
        } else if (!work && this.count/count == 0){
            time = sharedPreferences.getInt(SettingsActivity.LONG_BREAK, 0);
        }else{
            time = sharedPreferences.getInt(SettingsActivity.BREAK, 0);
        }

        if(!status.equals(STATUS_WORK))
        {
            sec = 60;
            ticker = 0;
        }

        min = time - 1;
        view.initiate(min, sec);
        timer.setSeconds(time*60000);
    }

    public void onTick() {
        ticker++;
        --sec;
        if (min != 0 && sec == 0) {
            min -= 1;
            sec = 60;
        }
        view.beat(min, sec, ticker);
    }

    public void setCount() {
        if(work) {
            this.count += 1;
            view.setCount(count);
        }
        work = !work;
    }

    public void notificate() {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder notificationBuiler = new Notification.Builder(context);
        if(work)
        {
            notificationBuiler.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.working)
                    .setContentTitle(context.getString(R.string.notification_title_work))
                    .setContentText(context.getString(R.string.notification_text_work));
        }else {
            notificationBuiler.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.resting)
                    .setContentTitle(context.getString(R.string.notification_title_rest))
                    .setContentText(context.getString(R.string.notification_text_rest));
        }
        Notification notification = notificationBuiler.build();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
        status = STATUS_ZERO;
    }

    public void start() {
        if(status.equals(STATUS_PAUSE)) {
            resume();
        }else if(status.equals(STATUS_WORK))
        {

        }else if (status.equals(STATUS_ZERO)){
            timer.create();
            status = STATUS_WORK;
        }
    }

    public void pause() {
        timer.pause();
        status = STATUS_PAUSE;
    }

    public void resume() {
        timer.resume();
        status = STATUS_WORK;
    }
}
