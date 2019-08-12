package orlov.daniil.timerfordolcegustomachines.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BrewDao {
    @Query("SELECT * FROM coffee_brews")
    List<Brew> getAll();

    @Query("SELECT * FROM coffee_brews WHERE id=:id")
    Brew getBrew(int id);

    @Insert
    void insert(Brew... brews);

    @Insert
    void insert(Brew brew);

    @Insert
    void insert(List<Brew> brewList);

    @Update
    void update(Brew... brews);

    @Delete
    void delete(Brew... brews);
}
