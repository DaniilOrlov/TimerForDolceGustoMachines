package orlov.daniil.timerfordolcegustomachines.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BrewDao {
    @Query("SELECT * FROM coffee_brews")
    LiveData<List<Brew>> getAllBrews();

    @Query("SELECT * FROM coffee_brews WHERE id=:id")
    Brew getBrewById(int id);

    @Query("SELECT * FROM coffee_brews WHERE name=:name")
    Brew getBrewByName(String name);

    @Query("SELECT name FROM coffee_brews")
    List<String> getBrewNames();

    @Insert
    void insert(Brew... brews);

    @Insert
    void insert(Brew brew);

    @Insert
    void insert(List<Brew> brewList);

    @Update
    void update(Brew... brews);

    @Delete
    void delete(Brew brew);

    @Query("DELETE FROM coffee_brews")
    void deleteAll();

    @Query("DELETE FROM coffee_brews WHERE id=:id")
    void deleteById(int id);
}
