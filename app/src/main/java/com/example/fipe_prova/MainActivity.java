package com.example.fipe_prova;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button carros, caminhoes, motos;


    private void getCarros()
    {
        Intent intent = new Intent(this, selecao_carros.class);
        startActivity(intent);
        finish();
    }

    private void getMotos()
    {
        Intent intent = new Intent(this, selecao_carros.class);
        startActivity(intent);
        finish();

    }

    private void getCaminhoes()
    {
        Intent intent = new Intent(this, selecao_carros.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carros = findViewById(R.id.btCarros);
        caminhoes = findViewById(R.id.btCaminhoes);
        motos = findViewById(R.id.btMotos);

        carros.setOnClickListener(e->{getCarros();});
        caminhoes.setOnClickListener(e->{getCaminhoes();});
        motos.setOnClickListener(e->{getMotos();});

    }
}