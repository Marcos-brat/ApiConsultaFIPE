package com.example.fipe_prova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(String url) {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    public Veiculo getMarca()
    {
        return this.retrofit.create(Veiculo.class);
    }

    public Veiculo getModelo()
    {
        return this.retrofit.create(Veiculo.class);
    }

    public Veiculo getAno()
    {
        return this.retrofit.create(Veiculo.class);
    }

    public Veiculo getCarroSelecionado()
    {
        return this.retrofit.create(Veiculo.class);
    }
}
