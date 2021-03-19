package com.pharos.android3lesson2.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.pharos.android3lesson2.models.Film;

import java.util.List;

@Dao
public interface FilmsDao {

    @Query("SELECT * from film where isFavorite = 1")
    List<Film> getFavoriteFilms();

    @Query("SELECT * FROM film WHERE id = :id")
    Film getFilmById(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Film film);

    @Delete
    void delete(Film film);

    @Update
    void update(Film film);
}
