package com.entidades.sistema;

public class Produto
{
    private String nome;
    private double preco;
    private int quantEstoque;
    private int codigo;

    public Produto(int codigo, String nome, double preco, int quantEstoque)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantEstoque = quantEstoque;
    }
    public String getMaterial(){return null;};
    public String getValidade(){return null;};

    public void setMaterial(String material){};
    public void setValidade(String validade){};
    public String getNome()
    {
        return nome;
    }

    public double getPreco()
    {
        return preco;
    }

    public int getQuantEstoque()
    {
        return quantEstoque;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public void setQuantEstoque(int quantEstoque)
    {
        this.quantEstoque = quantEstoque;
    }
}