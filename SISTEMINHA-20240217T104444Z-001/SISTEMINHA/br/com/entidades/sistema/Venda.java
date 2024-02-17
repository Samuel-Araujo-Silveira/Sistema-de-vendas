package com.entidades.sistema;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Venda
{
    private ArrayList<ItensVenda> itens;
    private Cliente cliente;
    private double valorTotal;
    private int id_venda;

    public Venda(ArrayList<ItensVenda> itens, Cliente cliente)
    {
        this.itens = new ArrayList<>();
        this.itens = itens;
        this.cliente = cliente;
        defineValorTotal(this.itens);
    }

    public void defineValorTotal(ArrayList<ItensVenda> itens)
    {
        for(ItensVenda i : itens)
        {
            this.valorTotal += i.getSubtotal();
        }
    }
    public double getValorTotal()
    {
        return valorTotal;
    }

    public ArrayList<ItensVenda> getItens()
    {
        return itens;
    }

    public void setItens(ArrayList<ItensVenda> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId_venda()
    {
        return id_venda;
    }

    public void setId_venda(int id_venda)
    {
        this.id_venda = id_venda;
    }

}