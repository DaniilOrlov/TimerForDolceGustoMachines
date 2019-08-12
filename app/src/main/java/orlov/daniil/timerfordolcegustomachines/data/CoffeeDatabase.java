package orlov.daniil.timerfordolcegustomachines.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Capsule.class, Brew.class}, version = 1)
public abstract class CoffeeDatabase extends RoomDatabase {

    private static final String DB_NAME = "coffeeDatabase.db";
    private static volatile CoffeeDatabase instance;

    static synchronized CoffeeDatabase getInstance(Context context){
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
                .build();
    }

    public abstract CapsuleDao getCapsuleDao();
    public abstract BrewDao getBrewDao();
}
