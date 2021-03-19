package com.pharos.android3lesson2.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pharos.android3lesson2.models.Film;

@Database(entities = {Film.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FilmsDao getFilmDao();
}
