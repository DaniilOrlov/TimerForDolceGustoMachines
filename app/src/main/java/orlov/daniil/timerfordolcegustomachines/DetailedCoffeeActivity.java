package orlov.daniil.timerfordolcegustomachines;

import androidx.annotation.MainThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import orlov.daniil.timerfordolcegustomachines.data.Capsule;
import orlov.daniil.timerfordolcegustomachines.data.CapsuleDao;
import orlov.daniil.timerfordolcegustomachines.data.CoffeeDatabase;

public class DetailedCoffeeActivity extends AppCompatActivity {

    Disposable disposable;

    private int brewId = -1;
    private String brewName;
    private String brewColor;
    private boolean doubleCap;
    private List<Capsule> capsuleList;

    private View linearSingleCap;
    private View linearDoubleCap;
    private ImageButton firstCapStart;
    private ImageButton secondCapStart;
    private TextView singeCapVol;
    private TextView singleCapBrew;
    private TextView doubleFirstBrewType;
    private TextView doubleFirstCapDescription;
    private TextView doubleFirstVol;
    private TextView doubleSecondBrewType;
    private TextView doubleSecondCapDescription;
    private TextView doubleSecondVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_coffee);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        getExtras();
        getViews();
        getCapsuleData();
//        setViews();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            brewId = extras.getInt("EXTRA_BREW_ID", -1);
            brewName = extras.getString("EXTRA_BREW_NAME");
            brewColor = extras.getString("EXTRA_BREW_COLOR");
            doubleCap = extras.getBoolean("EXTRA_DOUBLE_CAP");
        }
    }

    private void getCapsuleData() {
        final CapsuleDao capsuleDao = CoffeeDatabase
                .getInstance(this)
                .getCapsuleDao();
        disposable = capsuleDao.getCapsuleByBrewId(brewId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Capsule>>() {
                    @Override
                    public void accept(List<Capsule> capsules) throws Exception {
                        setViews(capsules);
                    }
                });
    }


    private void getViews() {
        linearSingleCap = findViewById(R.id.singleCapLinearLayout);
        linearDoubleCap = findViewById(R.id.doubleCapVerticalLayout);
        if (doubleCap) {
            doubleFirstBrewType = (TextView) findViewById(R.id.doubleFirstBrewType);
            doubleFirstCapDescription = (TextView) findViewById(R.id.doubleFirstCapDescription);
            doubleFirstVol = (TextView) findViewById(R.id.doubleFirstVol);
            doubleSecondBrewType = (TextView) findViewById(R.id.doubleSecondBrewType);
            doubleSecondCapDescription = (TextView) findViewById(R.id.doubleSecondCapDescription);
            doubleSecondVol = (TextView) findViewById(R.id.doubleSecondVol);
            firstCapStart = (ImageButton) findViewById(R.id.doubleFirstStartButton);
            secondCapStart = (ImageButton) findViewById(R.id.doubleSecondStartButton);
        } else {
            singeCapVol = (TextView) findViewById(R.id.singleCapBrewVolume);
            singleCapBrew = (TextView) findViewById(R.id.singleCapBrewName);
            firstCapStart = (ImageButton) findViewById(R.id.singleCupStartButton);
        }
    }

    private void setViews(List<Capsule> capsules) {
        if (doubleCap) {
            linearSingleCap.setVisibility(View.GONE);
            linearDoubleCap.setVisibility(View.VISIBLE);
            Capsule capsule1 = capsules.get(0);
            Capsule capsule2 = capsules.get(1);
            doubleFirstVol.setText(capsule1.volume + getString(R.string.cap_volume));
            doubleSecondVol.setText(capsule2.volume + getString(R.string.cap_volume));
            doubleFirstCapDescription.setText(capsule1.capsuleType);
            doubleSecondCapDescription.setText(capsule2.capsuleType);
            if (brewName != null) {
                doubleFirstBrewType.setText(brewName);
                doubleSecondBrewType.setText(brewName);
            }
            if (brewColor != null) {
                firstCapStart.setBackgroundColor(Color.parseColor(brewColor));
                secondCapStart.setBackgroundColor(Color.parseColor(brewColor));
            }
        } else {
            linearDoubleCap.setVisibility(View.GONE);
            linearSingleCap.setVisibility(View.VISIBLE);
            String capVolume = capsules.get(0).volume + getString(R.string.cap_volume);
            if (brewId >= 0) singeCapVol.setText(capVolume);
            if (brewName != null) singleCapBrew.setText(brewName);
            if (brewColor != null) firstCapStart.setBackgroundColor(Color.parseColor(brewColor));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}