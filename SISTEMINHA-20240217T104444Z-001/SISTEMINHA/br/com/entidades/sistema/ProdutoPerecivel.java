package com.entidades.sistema;

public class ProdutoPerecivel extends Produto
{
    private String validade;

    public ProdutoPerecivel (int codigo,String nome, double preco, int quantEstoque, String validade)
    {
        super(codigo,nome,preco,quantEstoque);
        this.validade = validade;
    }

    public String getValidade()
    {
        return validade;
    }


    public void setValidade(String validade) {
        this.validade = validade;
    }
}