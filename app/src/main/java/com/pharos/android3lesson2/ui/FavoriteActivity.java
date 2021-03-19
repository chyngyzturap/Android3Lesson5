package com.pharos.android3lesson2.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pharos.android3lesson2.R;
import com.pharos.android3lesson2.data.FilmsDao;
import com.pharos.android3lesson2.data.RetrofitBuilder;
import com.pharos.android3lesson2.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pharos.android3lesson2.ui.MainActivity.KEY;

public class FavoriteActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {
    private RecyclerView recyclerView;
    private FilmAdapter adapter;
    private FilmsDao filmsDao = App.instance.getDatabase().getFilmDao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        init();
        load();

    }

    private void load() {
        adapter.setFilmList(filmsDao.getFavoriteFilms());
    }


    private void init() {
        recyclerView = findViewById(R.id.recyclerList);
        adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(Film film) {
        Toast.makeText(this, "choose: " + film.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(KEY, film.getId());
        startActivity(intent);
    }
}