package com.example.fipe_prova;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class selecao_carros extends AppCompatActivity {

    private Spinner spMarca, spModelo, spAno;
    private Button showDados, force;
    private List<Root> dados;
    private  List<Root.ModeloAno> ModeloAno;
    private List<String> modelo;
    private List<String> ano;
    private List<String> marca;
    private String marcaSelecionada, modeloSelcionado, anoSelecionado;
    private TextView tx;
    private ArrayAdapter<Veiculo> adapter;
    private VeiculoSelecionado veic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_veiculos);
        spAno = findViewById(R.id.spAno);
        spMarca = findViewById(R.id.spMarca);
        spModelo = findViewById(R.id.spModelo);
        tx = findViewById(R.id.tvDadosVeiculo);
        showDados = findViewById((R.id.btMostrar));
        this.getCarros();
        showDados.setOnClickListener(e->mostraDados());
        force = findViewById(R.id.btForcar);
        force.setOnClickListener(e->forceExecution());
    }

    private void forceExecution() {
        getCarros();
    }


    private void getCarros()
    {
        marca = new ArrayList<>();
        Call<List<Root>> call = new RetrofitConfig("https://parallelum.com.br/").getMarca().BuscaCarroMarca();
        call.enqueue(new Callback<List<Root>>() {


            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                selecao_carros.this.dados = response.body();
                int i;
                for(i = 0; dados.size() > i; i++)
                {
                    marca.add(dados.get(i).nome);
                }

                ArrayAdapter aa = new ArrayAdapter(selecao_carros.this, android.R.layout.simple_dropdown_item_1line, marca);

                spMarca.setAdapter(aa);
                spMarca.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                marcaSelecionada = dados.get(position).codigo;
                                getModelos();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        }
                );

            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                finish();

            }
        });
    }

    private void getModelos()
    {
        modelo = new ArrayList<>();
        ano = new ArrayList<>();
        Call<List<Root.ModeloAno>> call = (Call<List<Root.ModeloAno>>) new RetrofitConfig("https://parallelum.com.br/").getModelo().BuscaCarroModelo(marcaSelecionada);

        call.enqueue(new Callback<List<Root.ModeloAno>>() {
            @Override
            public void onResponse(Call<List<Root.ModeloAno>> call, Response<List<Root.ModeloAno>> response) {
                selecao_carros.this.ModeloAno = response.body();

                for(int i = 0; i < dados.size(); i++)
                {
                    modelo.add(ModeloAno.get(0).modelos.get(i).nome);
                }

                for(int i = 0; i < dados.size(); i++)
                {
                    ano.add(ModeloAno.get(0).anos.get(i).nome);
                }

                ArrayAdapter aa = new ArrayAdapter(selecao_carros.this, android.R.layout.simple_dropdown_item_1line, selecao_carros.this.modelo);

                spModelo.setAdapter(aa);
                spModelo.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                modeloSelcionado = dados.get(position).codigo;
                                //getAno();

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        }
                );

                ArrayAdapter aaa = new ArrayAdapter(selecao_carros.this, android.R.layout.simple_dropdown_item_1line, selecao_carros.this.ano);

                spAno.setAdapter(aaa);
                spAno.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                anoSelecionado = dados.get(position).codigo;

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        }
                );

            }

            @Override
            public void onFailure(Call<List<Root.ModeloAno>> call, Throwable t) {

            }


        });
    }

    /*private void getAno()
    {
        ano  = new ArrayList<>();
        Call<List<Root>> call = (Call<List<Root>>) new RetrofitConfig("https://parallelum.com.br/fipe/api/v1/").getAno(modeloSelcionado);
        call.enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                selecao_carros.this.dados = response.body();

                for(int i = 0; i <dados.size(); i++)
                {
                    ano.add(dados.get(i).nome);
                }



            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {

            }
        });
    }*/


    public void mostraDados()
    {
        Call<VeiculoSelecionado> call = (Call<VeiculoSelecionado>) new RetrofitConfig("https://parallelum.com.br/fipe/api/v1/").getCarroSelecionado().BuscaCarroSelecionado(marcaSelecionada, modeloSelcionado, anoSelecionado);
        call.enqueue(new Callback<VeiculoSelecionado>() {
            @Override
            public void onResponse(Call<VeiculoSelecionado> call, Response<VeiculoSelecionado> response) {
                selecao_carros.this.veic = response.body();
            }

            @Override
            public void onFailure(Call<VeiculoSelecionado> call, Throwable t) {

            }
        });

        showDados.setText(dados.toString());
    }


}