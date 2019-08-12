package orlov.daniil.timerfordolcegustomachines.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "coffee_brews",
indices = {@Index("name")})
public class Brew {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public boolean favorite;

    @ColumnInfo(name = "double_cup")
    public boolean doubleCup;
}
