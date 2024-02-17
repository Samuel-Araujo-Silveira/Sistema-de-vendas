package com.entidades.dao;
import com.entidades.sistema.*;
import com.entidades.conexao.*;
import java.sql.*;

public class DaoCaixa
{
    public void cadastrarCaixa(Caixa caixa)
    {
        String sql = "INSERT INTO caixa (valor_caixa) VALUES (?)";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, caixa.getDinheiroEmCaixa());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCaixa()
    {
        String sql = "SELECT valor_caixa FROM caixa";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);

            while(rs.next())
            {
                System.out.println(rs.getDouble("valor_caixa"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarCaixa(Caixa caixa)
    {
        String sql = "UPDATE caixa SET valor_caixa = ?";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, caixa.getDinheiroEmCaixa());
            ps.execute();
            ps.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}