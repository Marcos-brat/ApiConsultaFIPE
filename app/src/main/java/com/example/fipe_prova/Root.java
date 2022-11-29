package com.example.fipe_prova;

import java.util.ArrayList;

public class Root{
    public String nome;
    public String codigo;

    public class Ano{
        public String nome;
        public String codigo;
    }

    public class Modelo{
        public String nome;
        public int codigo;
    }

    public class ModeloAno{
        public ArrayList<Modelo> modelos;
        public ArrayList<Ano> anos;
    }
}


