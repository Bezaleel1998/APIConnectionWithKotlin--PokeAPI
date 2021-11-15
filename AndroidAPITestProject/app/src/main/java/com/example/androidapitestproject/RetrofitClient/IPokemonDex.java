package com.example.androidapitestproject.RetrofitClient;

import com.example.androidapitestproject.Models.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {

    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
