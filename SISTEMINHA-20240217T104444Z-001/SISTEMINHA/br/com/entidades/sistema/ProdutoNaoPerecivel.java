package com.entidades.sistema;

public class ProdutoNaoPerecivel extends Produto
{
    private String material;

    public ProdutoNaoPerecivel(int codigo, String nome, double preco, int quantEstoque, String material)
    {
        super(codigo,nome,preco,quantEstoque);
        this.material = material;
    }

    public String getMaterial()
    {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}