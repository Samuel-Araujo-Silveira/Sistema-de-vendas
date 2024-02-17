package com.entidades.sistema;
import com.entidades.conexao.*;
import com.entidades.sistema.*;
import com.entidades.dao.*;



public class Cliente
{
    private String nome;
    private String endereco;
    private int id;
    public Cliente(String nome, String endereco)
    {
        this.nome = nome;
        this.endereco = endereco;
    }
    public String getNome()
    {
        return nome;
    }
    public String getEndereco()
    {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}