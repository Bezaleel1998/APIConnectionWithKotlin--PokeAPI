package com.example.androidapitestproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidapitestproject.Adapter.PokemonListAdapter;
import com.example.androidapitestproject.Common.Common;
import com.example.androidapitestproject.Common.ItemOffsetDecoration;
import com.example.androidapitestproject.Models.Pokedex;
import com.example.androidapitestproject.RetrofitClient.IPokemonDex;
import com.example.androidapitestproject.RetrofitClient.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PokemonList extends Fragment {


    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView poke_list_recView;


    static PokemonList instance;

    public  static PokemonList getInstance() {
        if (instance == null){
            instance = new PokemonList();
        }
        return instance;
    }

    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstance();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        poke_list_recView = (RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
        poke_list_recView.setHasFixedSize(true);
        poke_list_recView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        poke_list_recView.addItemDecoration(itemOffsetDecoration);

        fetchData();

        return view;

    }

    private void fetchData() {

        compositeDisposable.add(iPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {

                        Common.commonPokemonList = pokedex.getPokemon();
                        PokemonListAdapter adapter = new PokemonListAdapter(getActivity(),Common.commonPokemonList);

                        poke_list_recView.setAdapter(adapter);

                    }
                })
        );


    }


}