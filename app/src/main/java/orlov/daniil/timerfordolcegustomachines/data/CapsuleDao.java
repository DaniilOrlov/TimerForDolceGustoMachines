package orlov.daniil.timerfordolcegustomachines.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CapsuleDao {
    @Query("SELECT * FROM capsules")
    List<Capsule> getAllCapsules();

    @Query("SELECT * FROM capsules WHERE id=:id")
    Capsule getCapsuleById(int id);

    @Query("SELECT * FROM capsules WHERE brew_id=:brewId")
    Capsule getCapsuleByBrewId(int brewId);

    @Insert
    void insert(Capsule... capsules);

    @Insert
    void insert(Capsule capsule);

    @Insert
    void insert(List<Capsule> capsuleList);

    @Update
    void update(Capsule... capsules);

    @Delete
    void delete(Capsule... capsules);

    @Query("DELETE FROM capsules WHERE id=:id")
    void deleteById(int id);
}
