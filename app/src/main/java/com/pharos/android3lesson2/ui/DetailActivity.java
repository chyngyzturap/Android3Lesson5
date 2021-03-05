package com.pharos.android3lesson2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pharos.android3lesson2.R;
import com.pharos.android3lesson2.data.RetrofitBuilder;
import com.pharos.android3lesson2.models.Film;

import java.security.PrivateKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private String id;
    private View v;
    private TextView txtTitle, txtDesc, txtDirector, txtProducer,txtRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        if (getIntent()!=null) getData();
        callRF();
    }

    private void callRF() {
        RetrofitBuilder.getInstance()
                .getFilmById(id)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        v.setVisibility(View.VISIBLE);
                        setData(response.body());
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                        Log.e("tag", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    private void setData(Film body) {
txtTitle.setText(body.getTitle());
txtDirector.setText(String.format("Director: "+body.getDirector()));
txtProducer.setText(String.format("Producer: "+body.getProducer()));
txtRelease.setText(String.format("Release date: "+body.getReleaseDate()));
txtDesc.setText(body.getDescription());
    }

    private void init() {
        v = findViewById(R.id.detailHolder);
        txtTitle = findViewById(R.id.detailTitle);
        txtDesc = findViewById(R.id.detailDesc);
        txtDirector = findViewById(R.id.detailDirector);
        txtProducer = findViewById(R.id.detailProducer);
        txtRelease = findViewById(R.id.detailRelease);
    }

    private void getData(){
        id = getIntent().getStringExtra(MainActivity.KEY);
    }

}