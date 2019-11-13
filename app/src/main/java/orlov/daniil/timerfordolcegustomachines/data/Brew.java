package orlov.daniil.timerfordolcegustomachines.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "coffee_brews",
indices = {@Index(value = "name", unique = true)})
public class Brew {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    @NonNull
    public boolean favorite;

    @NonNull
    @ColumnInfo(name = "double_cup")
    public boolean doubleCup;

    @NonNull
    public String color;

    public Brew(String name, boolean favorite, final boolean doubleCup, final String color){
        this.name = name;
        this.favorite = favorite;
        this.doubleCup = doubleCup;
        this.color = color;
    }

    @NonNull
    @Override
    public String toString() {
        return  "id: " + id + " name: " + name;
    }
}
