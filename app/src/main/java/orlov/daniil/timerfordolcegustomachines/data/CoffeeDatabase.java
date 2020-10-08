package orlov.daniil.timerfordolcegustomachines.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

@Database(entities = {Capsule.class, Brew.class}, version = 1)
public abstract class CoffeeDatabase extends RoomDatabase {

    private static final String DB_NAME = "coffeeDatabase.db";
    private static volatile CoffeeDatabase instance;

    public abstract CapsuleDao getCapsuleDao();

    public abstract BrewDao getBrewDao();

    public static synchronized CoffeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static CoffeeDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                CoffeeDatabase.class,
                DB_NAME)
                .addCallback(new Callback() {
                    public void onCreate(SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                BrewDao brewDao = getInstance(context).getBrewDao();
                                brewDao.insert(InitData.getBrewList());
                                String[] brewNames = InitData.getBrewNames();
                                Map<String, Integer> brewIds = new HashMap<>();
                                for(String brewName : brewNames){
                                    Integer brewId = brewDao.getBrewByName(brewName).id;
                                    brewIds.put(brewName, brewId);
                                }
                                CapsuleDao capsuleDao = getInstance(context).getCapsuleDao();
                                List<Capsule> capsuleList = InitData.getCapsuleList(brewIds);
                                capsuleDao.insert(capsuleList);
                            }
                        });
                    }

                    public void onOpen(SupportSQLiteDatabase db) {
                        // do something every time database is open
                        super.onOpen(db);
                    }
                })
                .build();
    }
}
