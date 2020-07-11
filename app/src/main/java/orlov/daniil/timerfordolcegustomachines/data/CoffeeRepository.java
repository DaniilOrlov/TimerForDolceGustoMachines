package orlov.daniil.timerfordolcegustomachines.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CoffeeRepository {
    private BrewDao mBrewDao;
    private LiveData<List<Brew>> mAllBrews;

    public CoffeeRepository(Application application) {
        CoffeeDatabase db = CoffeeDatabase.getInstance(application);
        mBrewDao = db.getBrewDao();
        mAllBrews = mBrewDao.getAllBrews();
    }

    public LiveData<List<Brew>> getAllBrews() {
        return mAllBrews;
    }

    public void insert (final Brew brew) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    mBrewDao.insert(brew);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
