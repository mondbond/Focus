package focus.focus.settingscreen;

import android.widget.SeekBar;
import android.widget.TextView;
import focus.focus.R;
import focus.focus.settingscreen.presenter.SettingsPresenter;
import focus.focus.settingscreen.view.SettingsActivity;

public class SettingSeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private TextView tv;
    private SettingsPresenter presenter;

    public SettingSeekBarListener(TextView tv, SettingsPresenter presenter)
    {
        this.tv = tv;
        this.presenter = presenter;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        i++;
        tv.setText(Integer.toString(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int result = Integer.parseInt(tv.getText().toString());

        switch (tv.getId())
        {
            case R.id.tvResult1:
                presenter.savePreferences(SettingsActivity.TASK, result);
                break;
            case R.id.tvResult2:
                presenter.savePreferences(SettingsActivity.BREAK, result);
                break;
            case R.id.tvResult3:
                presenter.savePreferences(SettingsActivity.LONG_BREAK, result);
                break;
            case R.id.tvResult4:
                presenter.savePreferences(SettingsActivity.COUNT_BREAK, result);
                break;
        }
    }
}
