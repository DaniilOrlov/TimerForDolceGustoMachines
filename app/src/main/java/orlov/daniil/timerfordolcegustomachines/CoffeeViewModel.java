package orlov.daniil.timerfordolcegustomachines;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import orlov.daniil.timerfordolcegustomachines.data.Brew;
import orlov.daniil.timerfordolcegustomachines.data.CoffeeRepository;

public class CoffeeViewModel extends AndroidViewModel {
    private CoffeeRepository mRepository;
    private LiveData<List<Brew>> mAllBrews;

    public CoffeeViewModel(Application application) {
        super(application);
        mRepository = new CoffeeRepository(application);
        mAllBrews = mRepository.getAllBrews();
    }

    LiveData<List<Brew>> getmAllBrews() {
        return mAllBrews;
    }

    public void insert(Brew brew) {
        mRepository.insert(brew);
    }
}
