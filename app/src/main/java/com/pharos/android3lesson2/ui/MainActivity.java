package com.pharos.android3lesson2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.pharos.android3lesson2.R;
import com.pharos.android3lesson2.data.RetrofitBuilder;
import com.pharos.android3lesson2.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {
    public static final String KEY = "modelKey";
    private RecyclerView recyclerView;
    private FilmAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        init();
        load();

    }

    private void load() {
        RetrofitBuilder.getInstance()
                .getFilms()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response != null){
                            adapter.setFilmList(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {

                    }
                });
    }


    private void init() {
        recyclerView = findViewById(R.id.recyclerList);
        adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(Film film) {
        Toast.makeText(this, "choose: "+ film.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(KEY, film.getId());
        startActivity(intent);
    }
}