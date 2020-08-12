package orlov.daniil.timerfordolcegustomachines;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailedCoffeeActivity extends AppCompatActivity {

    private int brewId = -1;
    private String brewName;
    private String brewColor;
    private boolean doubleCap;

    private View linearSingleCap;
    private View linearDoubleCap;
    private ImageButton firstCapStart;
    private ImageButton secondCapStart;
    private TextView singeCapVol;
    private TextView singleCapBrew;
    private TextView doubleFirstBrewType;
    private TextView doubleFirstVol;
    private TextView doubleSecondBrewType;
    private TextView doubleSecondVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_coffee);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        getExtras();
        getViews();
        setViews();
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


    private void getViews() {
        linearSingleCap = findViewById(R.id.singleCapLinearLayout);
        linearDoubleCap = findViewById(R.id.doubleCapVerticalLayout);
        if (doubleCap) {
            doubleFirstBrewType = (TextView) findViewById(R.id.doubleFirstBrewType);
            doubleFirstVol = (TextView) findViewById(R.id.doubleFirstVol);
            doubleSecondBrewType = (TextView) findViewById(R.id.doubleSecondBrewType);
            doubleSecondVol = (TextView) findViewById(R.id.doubleSecondVol);
            firstCapStart = (ImageButton) findViewById(R.id.doubleFirstStartButton);
            secondCapStart = (ImageButton) findViewById(R.id.doubleSecondStartButton);
        } else {
            singeCapVol = (TextView) findViewById(R.id.singleCapBrewVolume);
            singleCapBrew = (TextView) findViewById(R.id.singleCapBrewName);
            firstCapStart = (ImageButton) findViewById(R.id.singleCupStartButton);
        }
    }

    private void setViews() {
        if (doubleCap) {
            linearSingleCap.setVisibility(View.GONE);
            linearDoubleCap.setVisibility(View.VISIBLE);
            if (brewId >= 0) {
                doubleFirstVol.setText(String.valueOf(brewId));
                doubleSecondVol.setText(String.valueOf(brewId));
            }
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
            if (brewId >= 0) singeCapVol.setText(String.valueOf(brewId));
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
}