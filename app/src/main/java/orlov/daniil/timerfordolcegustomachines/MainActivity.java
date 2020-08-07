package orlov.daniil.timerfordolcegustomachines;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import orlov.daniil.timerfordolcegustomachines.data.Brew;
import orlov.daniil.timerfordolcegustomachines.data.BrewDao;
import orlov.daniil.timerfordolcegustomachines.data.Capsule;
import orlov.daniil.timerfordolcegustomachines.data.CapsuleDao;
import orlov.daniil.timerfordolcegustomachines.data.CoffeeDatabase;

public class MainActivity extends AppCompatActivity {

    int brewTime = 0; //time for coffee brewing
    CountDownTimer timerV; //variable for timer
    String checkStatus = "";
    boolean vibrationOnOff;

    private CoffeeViewModel mCoffeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setUpRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.vibration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.vibration_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CoffeeListAdapter adapter = new CoffeeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCoffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
        mCoffeeViewModel.getmAllBrews().observe(this, new Observer<List<Brew>>() {
            @Override
            public void onChanged(List<Brew> brews) {
                adapter.setBrews(brews);
            }
        });
    }

    /*
    creates a toast, that notify user to cancel existing countdown before starting a new one
    this method is called when checkStatus != 0
     */
    private void toastOneCup() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, getString(R.string.toast_one_cup), duration);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /*
     * timer for coffee brewing, displays time till end of brewing
     * @param brewTimeForTimer brew time set by selected button
     */
    public void timer(int brewTimeForTimer, final ImageButton button) {


        timerV = new CountDownTimer(brewTimeForTimer + 100, 1000) {
            final TextView viewTimer = (TextView) findViewById(R.id.timerView);

            public void onTick(long millisUntilFinished) {
                viewTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                viewTimer.setText("0");
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Vibrate for 500 milliseconds
                vibrator.vibrate(500);
                button.setImageResource(R.drawable.play);
                checkStatus = "";
            }
        }.start();
    }

    /*
    starts timer at setted time and sets stop image for ImageButton
    @param reference to ImageButton to set stop image
     */
    private void start(ImageButton button) {
        button.setImageResource(R.drawable.stop);
        timer(brewTime, button);
    }

    /*
    stops timer, sets 0 to timer ImageView and sets start image for ImageButton
    @param reference to ImageButton to set start image
     */
    private void cancel(ImageButton button) {
        timerV.cancel();
        brewTime = 0;
        timer(brewTime, button);
    }

    public void espresso(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.espressoButton); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "espresso";
                start(button);
                break;
            case "espresso":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void espresso_decaffeinato(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.espressoDecaffeinatoButton); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "espresso_decaffeinato";
                start(button);
                break;
            case "espresso_decaffeinato":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void espresso_intenso(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.espressoIntensoButton); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "espresso_intenso";
                start(button);
                break;
            case "espresso_intenso":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void espresso_intenso_decaffeinato(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.espressoIntensoDecaffeinato); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "espresso_intenso_decaffeinato";
                start(button);
                break;
            case "espresso_intenso_decaffeinato":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void yunnan_espresso(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.yunnanEspresso); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "yunnan_espresso";
                start(button);
                break;
            case "yunnan_espresso":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }


    public void espresso_caramel(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.espressoCaramel); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 15000; //sets time for timer when specific button is clicked
                checkStatus = "espresso_caramel";
                start(button);
                break;
            case "espresso_caramel":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }


    public void espresso_buondi(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.espressoBuondi); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 13000; //sets time for timer when specific button is clicked
                checkStatus = "espresso_buondi";
                start(button);
                break;
            case "espresso_buondi":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }


    public void ristretto(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.ristrettoId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 12000; //sets time for timer when specific button is clicked
                checkStatus = "ristretto";
                start(button);
                break;
            case "ristretto":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void ristretto_ardenza(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.ristrettoArdenza); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 12000; //sets time for timer when specific button is clicked
                checkStatus = "ristretto_ardenza";
                start(button);
                break;
            case "ristretto_ardenza":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void barista(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.baristaId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 12000; //sets time for timer when specific button is clicked
                checkStatus = "barista";
                start(button);
                break;
            case "barista":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void sical(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.sicalId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 12000; //sets time for timer when specific button is clicked
                checkStatus = "sical";
                start(button);
                break;
            case "sical":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void bonka(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.bonkaId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 12000; //sets time for timer when specific button is clicked
                checkStatus = "bonka";
                start(button);
                break;
            case "bonka":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void americano(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.americanoId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 53000; //sets time for timer when specific button is clicked
                checkStatus = "americano";
                start(button);
                break;
            case "americano":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void americano_intenso(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.americanoIntenso); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 53000; //sets time for timer when specific button is clicked
                checkStatus = "americano_intenso";
                start(button);
                break;
            case "americano_intenso":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void grande(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.grandeId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 42000; //sets time for timer when specific button is clicked
                checkStatus = "grande";
                start(button);
                break;
            case "grande":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void grande_intenso(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.grandeIntenso); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 45000; //sets time for timer when specific button is clicked
                checkStatus = "grande_intenso";
                start(button);
                break;
            case "grande_intenso":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void grande_mild(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.grandeMild); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 37000; //sets time for timer when specific button is clicked
                checkStatus = "grande_mild";
                start(button);
                break;
            case "grande_mild":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void zoegas_dark(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.zoegasDark); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 37000; //sets time for timer when specific button is clicked
                checkStatus = "zoegas_dark";
                start(button);
                break;
            case "zoegas_dark":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void zoegas_intenzo(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.zoegasIntenzo); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 37000; //sets time for timer when specific button is clicked
                checkStatus = "zoegas_intenzo";
                start(button);
                break;
            case "zoegas_intenzo":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void zoegas_mollbergs(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.zoegasMollbergs); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 37000; //sets time for timer when specific button is clicked
                checkStatus = "zoegas_mollbergs";
                start(button);
                break;
            case "zoegas_mollbergs":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void zoegas_skanerost(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.zoegasSkanerost); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 37000; //sets time for timer when specific button is clicked
                checkStatus = "zoegas_skanerost";
                start(button);
                break;
            case "zoegas_skanerost":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void dallmayr_crema(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.dallmayrCrema); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "dallmayr_crema";
                start(button);
                break;
            case "dallmayr_crema":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void dallmayr_prodomo(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.dallmayrProdomo); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "dallmayr_prodomo";
                start(button);
                break;
            case "dallmayr_prodomo":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void lungo(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.lungoId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 28000; //sets time for timer when specific button is clicked
                checkStatus = "lungo";
                start(button);
                break;
            case "lungo":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void lungo_decaffeinato(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.lungoDecaffeinato); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 28000; //sets time for timer when specific button is clicked
                checkStatus = "lungo_decaffeinato";
                start(button);
                break;
            case "lungo_decaffeinato":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void lungo_intenso(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.lungoIntenso); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 28000; //sets time for timer when specific button is clicked
                checkStatus = "lungo_intenso";
                start(button);
                break;
            case "lungo_intenso":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void lungo_mild(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.lungoMild); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 28000; //sets time for timer when specific button is clicked
                checkStatus = "lungo_mild";
                start(button);
                break;
            case "lungo_mild":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void buongiorno(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.buongiornoId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 28000; //sets time for timer when specific button is clicked
                checkStatus = "buongiorno";
                start(button);
                break;
            case "buongiorno":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cafe_au_lait(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cafeAuLait); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 33000; //sets time for timer when specific button is clicked
                checkStatus = "cafe_au_lait";
                start(button);
                break;
            case "cafe_au_lait":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void ricore_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.ricoreLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 33000; //sets time for timer when specific button is clicked
                checkStatus = "ricore_latte";
                start(button);
                break;
            case "ricore_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cortado(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cortadoId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 16000; //sets time for timer when specific button is clicked
                checkStatus = "cortado";
                start(button);
                break;
            case "cortado":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cortado_decaffeinato(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cortadoDecaffeinato); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 16000; //sets time for timer when specific button is clicked
                checkStatus = "cortado_decaffeinato";
                start(button);
                break;
            case "cortado_decaffeinato":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cappuccino_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cappuccinoMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 35000; //sets time for timer when specific button is clicked
                checkStatus = "cappuccino_milk";
                start(button);
                break;
            case "cappuccino_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cappuccino_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cappuccinoCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 13000; //sets time for timer when specific button is clicked
                checkStatus = "cappuccino_coffee";
                start(button);
                break;
            case "cappuccino_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cappuccino_ice_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cappuccinoIceMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 25000; //sets time for timer when specific button is clicked
                checkStatus = "cappuccino_ice_milk";
                start(button);
                break;
            case "cappuccino_ice_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cappuccino_skinny_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cappuccinoSkinnyMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 35000; //sets time for timer when specific button is clicked
                checkStatus = "cappuccino_skinny_milk";
                start(button);
                break;
            case "cappuccino_skinny_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cappuccino_skinny_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cappuccinoSkinnyCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 13000; //sets time for timer when specific button is clicked
                checkStatus = "cappuccino_skinny_coffee";
                start(button);
                break;
            case "cappuccino_skinny_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void cappuccino_ice_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.cappuccinoIceCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 25000; //sets time for timer when specific button is clicked
                checkStatus = "cappuccino_ice_coffee";
                start(button);
                break;
            case "cappuccino_ice_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void freddo_cappuccino_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.freddoCappuccinoMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 18000; //sets time for timer when specific button is clicked
                checkStatus = "freddo_cappuccino_milk";
                start(button);
                break;
            case "freddo_cappuccino_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void freddo_cappuccino_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.freddoCappuccinoCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 18000; //sets time for timer when specific button is clicked
                checkStatus = "freddo_cappuccino_coffee";
                start(button);
                break;
            case "freddo_cappuccino_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void iced_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.icedCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 25000; //sets time for timer when specific button is clicked
                checkStatus = "iced_coffee";
                start(button);
                break;
            case "iced_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }


    public void latte_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "latte_milk";
                start(button);
                break;
            case "latte_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "latte_coffee";
                start(button);
                break;
            case "latte_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_skinny_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteSkinnyMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "latte_skinny_milk";
                start(button);
                break;
            case "latte_skinny_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_skinny_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteSkinnyCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "latte_skinny_coffee";
                start(button);
                break;
            case "latte_skinny_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_vanilla_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteVanillaMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "latte_vanilla_milk";
                start(button);
                break;
            case "latte_vanilla_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_vanilla_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteVanillaCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "latte_vanilla_coffee";
                start(button);
                break;
            case "latte_vanilla_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_caramel_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteCaramelMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "latte_caramel_milk";
                start(button);
                break;
            case "latte_caramel_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_caramel_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteCaramelCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "latte_caramel_coffee";
                start(button);
                break;
            case "latte_caramel_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_unsweetened_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteUnsweetenedMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 30000; //sets time for timer when specific button is clicked
                checkStatus = "latte_unsweetened_milk";
                start(button);
                break;
            case "latte_unsweetened_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void latte_unsweetened_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.latteUnsweetenedCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 14000; //sets time for timer when specific button is clicked
                checkStatus = "latte_unsweetened_coffee";
                start(button);
                break;
            case "latte_unsweetened_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void mocha_coffee(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.mochaCoffee); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 21000; //sets time for timer when specific button is clicked
                checkStatus = "mocha_coffee";
                start(button);
                break;
            case "mocha_coffee":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void mocha_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.mochaMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 19000; //sets time for timer when specific button is clicked
                checkStatus = "mocha_milk";
                start(button);
                break;
            case "mocha_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milo_chocolate(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.miloChocolate); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 17000; //sets time for timer when specific button is clicked
                checkStatus = "milo_chocolate";
                start(button);
                break;
            case "milo_chocolate":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milo_milk(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.miloMilk); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 17000; //sets time for timer when specific button is clicked
                checkStatus = "milo_milk";
                start(button);
                break;
            case "milo_milk":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void choco_chococino(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.chocoChococino); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 19000; //sets time for timer when specific button is clicked
                checkStatus = "choco_chococino";
                start(button);
                break;
            case "choco_chococino":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milk_chococino(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.milkChococino); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "milk_chococino";
                start(button);
                break;
            case "milk_chococino":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milk_chococino_caramel(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.milkChococinoCaramel); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 19000; //sets time for timer when specific button is clicked
                checkStatus = "milk_chococino_caramel";
                start(button);
                break;
            case "milk_chococino_caramel":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void choco_chococino_caramel(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.chocoChococinoCaramel); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "choco_chococino_caramel";
                start(button);
                break;
            case "choco_chococino_caramel":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void nesquik(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.nesquikId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 26000; //sets time for timer when specific button is clicked
                checkStatus = "nesquik";
                start(button);
                break;
            case "nesquik":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void nescau(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.nescauId); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "nescau";
                start(button);
                break;
            case "nescau":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void nestea_lemon(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.nesteaLemon); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 38000; //sets time for timer when specific button is clicked
                checkStatus = "nestea_lemon";
                start(button);
                break;
            case "nestea_lemon":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void nestea_peach(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.nesteaPeach); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 38000; //sets time for timer when specific button is clicked
                checkStatus = "nestea_peach";
                start(button);
                break;
            case "nestea_peach":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void marrakesh_tea(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.marrakeshTea); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 29000; //sets time for timer when specific button is clicked
                checkStatus = "marrakesh_tea";
                start(button);
                break;
            case "marrakesh_tea":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void macaron_tea(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.maсaronTea); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 29000; //sets time for timer when specific button is clicked
                checkStatus = "macaron_tea";
                start(button);
                break;
            case "macaron_tea":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void honey_tea(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.honeyTea); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 29000; //sets time for timer when specific button is clicked
                checkStatus = "honey_tea";
                start(button);
                break;
            case "honey_tea":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milk_chai_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.milkChaiLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "milk_chai_latte";
                start(button);
                break;
            case "milk_chai_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void tea_chai_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.teaChaiLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "tea_chai_latte";
                start(button);
                break;
            case "tea_chai_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milk_tea_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.milkTeaLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "milk_tea_latte";
                start(button);
                break;
            case "milk_tea_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void tea_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.teaLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "tea_latte";
                start(button);
                break;
            case "tea_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void milk_green_tea_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.milkGreenTeaLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "milk_green_tea_latte";
                start(button);
                break;
            case "milk_green_tea_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

    public void green_tea_latte(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.greenTeaLatte); //refer to espresso ImageButton
        switch (checkStatus) {
            case "":
                brewTime = 22000; //sets time for timer when specific button is clicked
                checkStatus = "green_tea_latte";
                start(button);
                break;
            case "green_tea_latte":
                cancel(button); //cancels timer when button is clicked again
                break;
            default:
                toastOneCup(); //notify user to cancel existing countdown before starting a new one
                break;
        }
    }

}
