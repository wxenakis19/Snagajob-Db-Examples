package com.example.williamxenakis.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by william.xenakis on 6/20/17.
 */

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM People")
    List<PersonEntity> getAll();

    @Query("SELECT * FROM People WHERE UUID IN (:UUID)")
    List<PersonEntity> loadAllByUUID(String[] UUID);

    @Query("SELECT * FROM People WHERE name LIKE :name")
    PersonEntity findByName(String name);

    @Query("SELECT * FROM People WHERE UUID LIKE :UUID")
    PersonEntity findByUUID(String UUID);

    @Insert
    void insertAll(PersonEntity... People);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(PersonEntity... People);

    @Update
    public void updateUsers(PersonEntity... People);

    @Delete
    void delete(PersonEntity user);
}
