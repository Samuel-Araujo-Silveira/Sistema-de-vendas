package com.entidades.sistema;

public class Caixa
{
    private double dinheiroEmCaixa;

    public Caixa(double dinheiroEmCaixa)
    {
        this.dinheiroEmCaixa = dinheiroEmCaixa;
    }

    public double getDinheiroEmCaixa()
    {
        return dinheiroEmCaixa;
    }

    public void setDinheiroEmCaixa(double dinheiroEmCaixa)
    {
        this.dinheiroEmCaixa += dinheiroEmCaixa;
    }
}