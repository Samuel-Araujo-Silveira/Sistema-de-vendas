package com.entidades.dao;
import com.entidades.sistema.*;
import com.entidades.conexao.*;
import java.sql.*;
import java.util.ArrayList;
public class DaoCliente
{
    public void cadastrarCliente(Cliente cliente)
    {
        int id;
        String sql = "INSERT INTO cliente (nome,endereco) VALUES(?,?)";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.execute();
            ps.close();
            defineId(cliente);

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void preencherClientes(ArrayList<Cliente> clientes)
    {
        Cliente clienteAuxiliar;
        String sql = "SELECT * FROM cliente";
        Statement s = null;
        ResultSet rs = null;

        try
        {
            s = Conexao.getConexao().createStatement();
            rs = s.executeQuery(sql);

            while(rs.next())
            {
                clienteAuxiliar = new Cliente("NULO", "NULO");
                clienteAuxiliar.setEndereco(rs.getString("endereco"));
                clienteAuxiliar.setNome(rs.getString("nome"));
                clienteAuxiliar.setId(rs.getInt("id"));
                clientes.add(clienteAuxiliar);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void defineId(Cliente c)
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
            c.setId(id);
            rs.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cliente> mostraClientes()
    {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id,nome,endereco FROM cliente";
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
                System.out.println(rs.getString(3));
                System.out.println();

            }
            return clientes;

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void editarCliente(Cliente cliente, int id)
    {
        String sql = "UPDATE cliente SET nome = ?, endereco = ? WHERE id = ?";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deletarCliente(int id)
    {
        String sql = "DELETE FROM cliente WHERE id = ?";
        PreparedStatement ps = null;

        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}