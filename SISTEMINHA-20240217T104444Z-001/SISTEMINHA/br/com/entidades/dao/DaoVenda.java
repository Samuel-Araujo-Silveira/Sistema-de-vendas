package com.entidades.dao;
import com.entidades.conexao.Conexao;
import com.entidades.sistema.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.entidades.sistema.*;
import com.entidades.conexao.*;
import java.sql.*;

public class DaoVenda
{
    public void cadastrarVenda(Venda venda)
    {
        int id;
        String sql = "INSERT INTO venda (id_cliente,valor_total) VALUES(?,?)";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, venda.getCliente().getId());
            ps.setDouble(2, venda.getValorTotal());
            ps.execute();
            ps.close();
            defineIdVenda(venda);

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void defineIdVenda(Venda v)
    {
        int id=0;
        String sql = "SELECT LAST_INSERT_ID() AS id";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);
            if(rs.next())
            {
                id = rs.getInt("id");
            }
            v.setId_venda(id);
            rs.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void mostraVendas()
    {
        String sql = "SELECT v.id, v.valor_total, c.nome, c.endereco, i.nome_produto, i.quantidade_comprada FROM venda v INNER JOIN cliente c ON v.id_cliente = c.id INNER JOIN itens_venda i ON i.id_venda = v.id";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);

            while(rs.next())
            {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getDouble(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getInt(6));
                System.out.println();
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public double getValorDeTodasAsCompras()
    {
        double total=0;
        String sql = "SELECT SUM(valor_total) AS TOTAL FROM venda;";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);

            if(rs.next())
            {
                return total = rs.getDouble("TOTAL");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return 0;
    }

}