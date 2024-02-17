package com.entidades.sistema;

public class ItensVenda
{
    private Produto p;
    private int quantidade;
    private double subtotal;
    private int id_venda;

    public ItensVenda(Produto p, int quantidade, int id_venda)
    {
        this.p = p;
        this.quantidade = quantidade;
        this.subtotal = this.quantidade * p.getPreco();
        this.id_venda = id_venda;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }


}