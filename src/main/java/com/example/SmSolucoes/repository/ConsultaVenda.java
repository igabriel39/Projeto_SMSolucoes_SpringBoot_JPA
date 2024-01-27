package com.example.SmSolucoes.repository;

//Interface criada para receber a consulta das vendas
public interface ConsultaVenda {

    int getIdVenda();
    String getCliente();
    String getdataVenda();
    float getvalorVenda();
}
