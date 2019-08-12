package orlov.daniil.timerfordolcegustomachines.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "capsules")
public class Capsule {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ForeignKey(entity = Brew.class,
            parentColumns = "id",
            childColumns = "brew_id")
    @ColumnInfo(name = "brew_id")
    public int brewId;

    @NonNull
    @ColumnInfo(name = "capsule_type")
    public String capsuleType;

    @ColumnInfo(name = "brew_time")
    public int brewTime;
}
