package com.example.androidapitestproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.androidapitestproject.Models.Pokemon;
import com.example.androidapitestproject.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    Context context;
    List<Pokemon> pokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //load image
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokeImage);
        //SetName
        holder.pokeName.setText(pokemonList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView pokeImage;
        TextView pokeName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pokeImage = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokeName = (TextView) itemView.findViewById(R.id.txt_pokemon_name);
        }
    }
}
