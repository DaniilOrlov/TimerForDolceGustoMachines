package orlov.daniil.timerfordolcegustomachines.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "capsules",
        foreignKeys = @ForeignKey(entity =
                Brew.class,
                parentColumns = "id",
                childColumns = "brew_id",
                onDelete = CASCADE),
        indices = {@Index("brew_id")})
public class Capsule {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "brew_id")
    public final int brewId;

    @NonNull
    @ColumnInfo(name = "capsule_type")
    public final String capsuleType;

    @NonNull
    public final int volume;

    @NonNull
    @ColumnInfo(name = "brew_time")
    public final int brewTime;

    public Capsule(final int brewId, final String capsuleType, int volume, int brewTime){
        this.brewId = brewId;
        this.capsuleType = capsuleType;
        this.volume = volume;
        this.brewTime = brewTime;
    }

    @NonNull
    @Override
    public String toString() {
        return  "id: " + id + " brewId: " + brewId;
    }
}
