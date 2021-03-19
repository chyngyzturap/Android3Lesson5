package com.pharos.android3lesson2.ui;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.pharos.android3lesson2.data.AppDatabase;

public class App extends Application {
    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "FilmDatabase")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
