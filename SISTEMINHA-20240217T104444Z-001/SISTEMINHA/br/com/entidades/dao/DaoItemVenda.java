package com.entidades.dao;
import com.entidades.sistema.*;
import com.entidades.conexao.*;
import java.sql.*;
public class DaoItemVenda
{
    public void adicionarItemVenda(ItensVenda item, int quantidade)
    {
        String sql = "INSERT INTO itens_venda (id_venda,nome_produto,quantidade_comprada,id_produto,subtotal) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, item.getId_venda());
            ps.setString(2, item.getP().getNome());
            ps.setInt(3, quantidade);
            ps.setInt(4, item.getP().getCodigo());
            ps.setDouble(5, quantidade*item.getP().getPreco());
            ps.execute();
            definirIdVenda(item);
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void definirIdVenda(ItensVenda i)
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
            i.setId_venda(id);
            rs.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}