package com.example.fipe_prova;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Veiculo {
    //carros
    @GET("/fipe/api/v1/carros/marcas")
    Call<List<Root>> BuscaCarroMarca();

    @GET("/fipe/api/v1/carros/marcas/{marca}/modelos")
    Call<List<Root.ModeloAno>> BuscaCarroModelo(@Path("marca") String marca);

    @GET("/fipe/api/v1/carros/marcas/{modelo}/modelos/{ano}/anos")
    Call<List<Root>> BuscaCarroAno(@Query("ano") String ano, @Query("modelo") String modelo);

    @GET("/fipe/api/v1/carros/marcas/{modelo}/modelos/{ano}/anos/{selecao}")
    Call<VeiculoSelecionado> BuscaCarroSelecionado(@Query("ano") String ano, @Query("modelo") String modelo, @Query("selecao") String selecao);



    //Caminhoes
    @GET("/fipe/api/v1/caminhoes/marcas")
    Call<List<Root>> BuscaCaminhaoMarca(@Path("marca") String marca);
    @GET("/fipe/api/v1/caminhoes/marcas/{modelo}/modelos")
    Call<List<Root>> BuscaCaminhaoModelo(@Path("modelo") String modelo);
    @GET("/fipe/api/v1/caminhoes/{modelo}/modelos/{ano}/anos")
    Call<Root> BuscaCaminhaoAno(@Query("ano") String ano, @Query("modelo") String modelo);

    //Motos
    @GET("/fipe/api/v1/motos/marcas")
    Call<List<Root>> BuscaMotosMarca(@Path("marca") String marca);
    @GET("/fipe/api/v1/motos/{modelo}/modelos")
    Call<List<Root>> BuscaMotosModelo(@Path("modelo") String modelo);
    @GET("/fipe/api/v1/motos/{modelo}/modelos/{ano}/anos")
    Call<List<Root>> BuscaMotosAno(@Query("ano") String ano, @Query("modelo") String modelo);



}
