package focus.focus.settingscreen.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Map;
import javax.inject.Inject;
import focus.focus.App;
import focus.focus.R;
import focus.focus.settingscreen.DaggerSettingsComponent;
import focus.focus.settingscreen.SettingSeekBarListener;
import focus.focus.settingscreen.SettingsComponent;
import focus.focus.settingscreen.SettingsContract;
import focus.focus.settingscreen.SettingsModule;
import focus.focus.settingscreen.presenter.SettingsPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements SettingsContract.View {

    SettingsComponent component;

    @Inject
    SettingsPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;

    TextView titleText;
    SeekBar seekBar1;
    TextView result1;
    SeekBar seekBar2;
    TextView result2;
    SeekBar seekBar3;
    TextView result3;
    SeekBar seekBar4;
    TextView result4;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        component = DaggerSettingsComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .settingsModule(new SettingsModule(this))
                .build();
        component.inject(this);

        titleText = (TextView) v.findViewById(R.id.tvTitle);
        result1 = (TextView) v.findViewById(R.id.tvResult1);
        seekBar1 = (SeekBar) v.findViewById(R.id.sb1);
        result2 = (TextView) v.findViewById(R.id.tvResult2);
        seekBar2 = (SeekBar) v.findViewById(R.id.sb2);
        result3 = (TextView) v.findViewById(R.id.tvResult3);
        seekBar3 = (SeekBar) v.findViewById(R.id.sb3);
        result4 = (TextView) v.findViewById(R.id.tvResult4);
        seekBar4 = (SeekBar) v.findViewById(R.id.sb4);

        Map<String, ? > allPref = sharedPreferences.getAll();

        result1.setText(allPref.get(SettingsActivity.TASK).toString());
        seekBar1.setProgress(Integer.parseInt(allPref.get(SettingsActivity.TASK).toString()));
        result2.setText(allPref.get(SettingsActivity.BREAK).toString());
        seekBar2.setProgress(Integer.parseInt(allPref.get(SettingsActivity.BREAK).toString()));
        result3.setText(allPref.get(SettingsActivity.LONG_BREAK).toString());
        seekBar3.setProgress(Integer.parseInt(allPref.get(SettingsActivity.LONG_BREAK).toString()));
        result4.setText(allPref.get(SettingsActivity.COUNT_BREAK).toString());
        seekBar4.setProgress(Integer.parseInt(allPref.get(SettingsActivity.COUNT_BREAK).toString()));

        seekBar1.setOnSeekBarChangeListener(new SettingSeekBarListener(result1, presenter));
        seekBar2.setOnSeekBarChangeListener(new SettingSeekBarListener(result2, presenter));
        seekBar3.setOnSeekBarChangeListener(new SettingSeekBarListener(result3, presenter));
        seekBar4.setOnSeekBarChangeListener(new SettingSeekBarListener(result4, presenter));

        return v;
    }

    public void setRetainInstance(boolean retain)
    {
    }

}
