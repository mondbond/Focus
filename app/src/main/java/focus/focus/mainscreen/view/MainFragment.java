package focus.focus.mainscreen.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import javax.inject.Inject;
import focus.focus.App;
import focus.focus.R;
import focus.focus.mainscreen.DaggerMainScreenComponent;
import focus.focus.mainscreen.MainScreenComponent;
import focus.focus.mainscreen.MainScreenContract;
import focus.focus.mainscreen.MainScreenModule;
import focus.focus.mainscreen.presenter.MainScreenPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainScreenContract.View {

    private TextView minutesTv;
    private TextView secondsTv;
    private TextView countTv;
    private Button startBtn;
    private Button pauseBtn;
    private ProgressBar timerPB;

    MainScreenComponent component;

    @Inject
    MainScreenPresenter presenter;

    public MainFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = DaggerMainScreenComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build();
        component.inject(this);

        presenter.checkFirstStart();

        presenter.beforeStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        minutesTv = (TextView) v.findViewById(R.id.minutesTimer);
        secondsTv = (TextView) v.findViewById(R.id.secoundsTimer);
        countTv = (TextView) v.findViewById(R.id.countTv);
        startBtn = (Button) v.findViewById(R.id.startBtn);
        pauseBtn = (Button) v.findViewById(R.id.pauseBtn);
        timerPB = (ProgressBar) v.findViewById(R.id.timerProgressBar);

         startBtn = (Button) v.findViewById(R.id.startBtn);
         startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.start();
            }});

        pauseBtn = (Button) v.findViewById(R.id.pauseBtn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.pause();
            }});

        timerPB = (ProgressBar) v.findViewById(R.id.timerProgressBar);

        return v;
    }

    public void initiate(int min, int sec) {
        if(min < 0) {
            min = 0;
        }
        minutesTv.setText(Integer.toString(min));
        secondsTv.setText(Integer.toString(sec));
        timerPB.setMax((min+1)*60);
        timerPB.setProgress(0);
    }

    public void beat(int min, int sec, int tick) {
        minutesTv.setText(Integer.toString(min));
        secondsTv.setText(Integer.toString(sec));
        timerPB.setProgress(tick);
    }

    public void setCount(int count)
    {
        countTv.setText(Integer.toString(count));
    }
}
