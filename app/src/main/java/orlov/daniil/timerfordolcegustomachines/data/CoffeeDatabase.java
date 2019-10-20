package orlov.daniil.timerfordolcegustomachines.data;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Capsule.class, Brew.class}, version = 1)
public abstract class CoffeeDatabase extends RoomDatabase {

    private static final String DB_NAME = "coffeeDatabase.db";
    private static volatile CoffeeDatabase instance;

    public abstract CapsuleDao getCapsuleDao();
    public abstract BrewDao getBrewDao();

    public static synchronized CoffeeDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static CoffeeDatabase create(final Context context){
        return Room.databaseBuilder(
                context,
                CoffeeDatabase.class,
                DB_NAME)
                .addCallback(new Callback() {
                    public void onCreate(SupportSQLiteDatabase db) {
                        // do something after database has been created
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                BrewDao brewDao = getInstance(context).getBrewDao();
                                brewDao.insert(new Brew("test_db", false, false));
                                int brewId = brewDao.getBrewByName("test_db").id;
                                getInstance(context).getCapsuleDao().insert(new Capsule(brewId, "coffe_db", 42));

                                List<Brew> brewList = getInstance(context).getBrewDao().getAll();
                                List<Capsule> capsuleList = getInstance(context).getCapsuleDao().getAll();
                                Log.v("SQL REQUEST DB: ", brewList.size() + " ||||| " + capsuleList.get(1));
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

//    private static RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
//        public void onCreate(SupportSQLiteDatabase db) {
//            // do something after database has been created
//            super.onCreate(db);
//            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
//                @Override
//                public void run() {
//                    getInstance(context).getCapsuleDao().insert(new Capsule(1, "coffe", 42));
//                    getInstance(context).getBrewDao().insert(new Brew("test", false, false));
//                    List<Brew> brewList = getInstance(context).getBrewDao().getAll();
//                    List<Capsule> capsuleList = getInstance(context).getCapsuleDao().getAll();
//                    Log.v("SQL REQUEST: ", brewList.toString() + " ||||| " + capsuleList.toString());
//                }
//            });
//        }
//
//        public void onOpen(SupportSQLiteDatabase db) {
//            // do something every time database is open
//            super.onOpen(db);
//        }
//    };
}
