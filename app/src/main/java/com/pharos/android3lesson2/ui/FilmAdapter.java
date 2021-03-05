package com.pharos.android3lesson2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pharos.android3lesson2.R;
import com.pharos.android3lesson2.models.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
private List<Film> filmList = new ArrayList<>();
private OnClickListener listener;

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list, parent,false);
        return new FilmHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
holder.onBind(filmList.get(position));

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setClickListener(OnClickListener listener){
        this.listener = listener;
    }

public void setFilmList(List<Film> body){
        filmList.addAll(body);
        notifyDataSetChanged();
}

    public class FilmHolder extends RecyclerView.ViewHolder{
        private TextView textTitle, textDirector;
        public FilmHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtTitle);
            textDirector = itemView.findViewById(R.id.txtDirector);
        }

        public void onBind(Film film) {
textTitle.setText(film.getTitle());
textDirector.setText(film.getDirector());
itemView.setOnClickListener(v -> listener.onClick(filmList.get(getAdapterPosition())));
        }
    }
    public interface OnClickListener {
        void onClick(Film film);
    }
}
