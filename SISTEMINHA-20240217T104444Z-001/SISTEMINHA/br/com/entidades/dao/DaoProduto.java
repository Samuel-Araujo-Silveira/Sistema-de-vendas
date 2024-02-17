package com.entidades.dao;
import com.entidades.sistema.*;
import com.entidades.conexao.*;
import java.sql.*;
import java.util.ArrayList;


public class DaoProduto
{
    public void adicionarProduto(Produto p)
    {
        String sql = "INSERT INTO produto (codigo,nome,preco,quant_estoque,material,validade) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getNome());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, p.getQuantEstoque());
            ps.setString(5, p.getMaterial());
            ps.setString(6, p.getValidade());

            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void preencherProdutos(ArrayList<Produto> produtos)
    {
        Produto produtoAuxiliar = null;
        String sql = "SELECT * FROM produto";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);

            while(rs.next())
            {
                if(rs.getString("validade") != null)
                {
                    produtoAuxiliar = new ProdutoPerecivel(0, "NULO",0,0,"NULO");
                }
                else if(rs.getString("material") != null)
                {
                    produtoAuxiliar = new ProdutoNaoPerecivel(0, "NULO",0,0,"NULO");

                }
                produtoAuxiliar.setCodigo(rs.getInt("codigo"));
                produtoAuxiliar.setNome(rs.getString("nome"));
                produtoAuxiliar.setPreco(rs.getDouble("preco"));
                produtoAuxiliar.setQuantEstoque(rs.getInt("quant_Estoque"));

                if(produtoAuxiliar instanceof ProdutoPerecivel)
                {
                    produtoAuxiliar.setValidade(rs.getString("validade"));
                }
                else if(produtoAuxiliar instanceof ProdutoNaoPerecivel)
                {
                    produtoAuxiliar.setMaterial(rs.getString("material"));
                }
                produtos.add(produtoAuxiliar);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarProdutos()
    {
        String sql = "SELECT codigo,nome,preco,quant_estoque,material,validade FROM produto";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);

            while(rs.next())
            {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getDouble(3));
                System.out.println(rs.getInt(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));
                System.out.println();
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void atualizarProduto(int quant, double preco, int codigo)
    {
        String sql = "UPDATE produto SET preco = ?, quant_estoque = ? WHERE codigo = ?";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, quant);
            ps.setDouble(2, preco);
            ps.setInt(3, codigo);
            ps.execute();
            ps.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deletarProduto(int codigo)
    {
        String sql = "DELETE FROM produto WHERE codigo = ?";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
            ps.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean retornarQuantEmEstoque(int codigo, int quantCompra)
    {
        int quantEstoque=0;
        String sql = "SELECT quant_estoque FROM produto WHERE codigo = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
            rs = ps.executeQuery();

            if (rs.next())
            {
                quantEstoque = rs.getInt("quant_estoque");
            }
            if(quantCompra <= quantEstoque)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public double retornaPreco(int codigo)
    {
        double valor=0;
        String sql = "SELECT preco FROM produto WHERE codigo = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();

            if(rs.next())
            {
                return valor = rs.getDouble("preco");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return valor;
    }



}