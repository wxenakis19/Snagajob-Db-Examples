package com.example.williamxenakis.myapplication;

import android.arch.persistence.room.Database;

/**
 * Created by william.xenakis on 6/20/17.
 */

@Database(entities = {PersonEntity.class}, version = 2)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {
    public abstract PersonDAO PersonDAO();
}
