package com.pharos.android3lesson2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharos.android3lesson2.R;
import com.pharos.android3lesson2.data.FilmsDao;
import com.pharos.android3lesson2.data.RetrofitBuilder;
import com.pharos.android3lesson2.models.Film;

import java.security.PrivateKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private String id;
    private View v;
    private TextView txtTitle, txtDesc, txtDirector, txtProducer, txtRelease;
    private ImageView imgViewFavorite;
    private Film film;
    private FilmsDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dao = App.instance.getDatabase().getFilmDao();
        init();
        if (getIntent() != null) getData();
        callRF();
    }

    private void callRF() {
        RetrofitBuilder.getInstance()
                .getFilmById(id)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        v.setVisibility(View.VISIBLE);
                        if (response.isSuccessful())
                            setData(response.body());
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                        Log.e("tag", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    private void setData(Film body) {
        dao.insert(body);
        film = dao.getFilmById(body.getId());
        txtTitle.setText(body.getTitle());
        txtDirector.setText(String.format("Director: " + body.getDirector()));
        txtProducer.setText(String.format("Producer: " + body.getProducer()));
        txtRelease.setText(String.format("Release date: " + body.getReleaseDate()));
        txtDesc.setText(body.getDescription());


        if (film.isFavorite())
            imgViewFavorite.setImageResource(R.drawable.ic_favorite);
         else
            imgViewFavorite.setImageResource(R.drawable.ic_favorite_border);

    }

    private void init() {
        v = findViewById(R.id.detailHolder);
        txtTitle = findViewById(R.id.detailTitle);
        txtDesc = findViewById(R.id.detailDesc);
        txtDirector = findViewById(R.id.detailDirector);
        txtProducer = findViewById(R.id.detailProducer);
        txtRelease = findViewById(R.id.detailRelease);
        imgViewFavorite = findViewById(R.id.iv_favorite);



        imgViewFavorite.setOnClickListener(v -> {
            film.setFavorite(!film.isFavorite());

            if (film.isFavorite())
                imgViewFavorite.setImageResource(R.drawable.ic_favorite);
            else
                imgViewFavorite.setImageResource(R.drawable.ic_favorite_border);

            dao.update(film);
        });
    }

    private void getData() {
        id = getIntent().getStringExtra(MainActivity.KEY);
    }

}