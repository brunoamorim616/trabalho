package br.senai.sc.sisloj.dao;

import br.senai.sc.sisloj.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends ConnectionFactory {

    private Connection con;

    public ClienteDao() {
        this.con = this.getConnection();
    }

    public void inserir(Cliente cli) throws SQLException {

        String sql = "insert into cliente "
                + "(nomcli, endcli, "
                + "baicli, comcli, cepcli, celcli) "
                + "values (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, cli.getNomcli());
            st.setString(2, cli.getEndcli());
            st.setString(3, cli.getBaicli());
            st.setString(4, cli.getComcli());
            st.setLong(5, cli.getCepcli());
            st.setLong(6, cli.getCelcli());

            st.execute();
            st.close();
        }

        this.con.close();

    }
    
    public void eliminar(int codcli) throws SQLException {

        String sql = "delete from cliente where codcli = ?";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setInt(1, codcli);
            st.execute();
            st.close();
        }

        this.con.close();

    }

    public void alterar(Cliente cli) throws SQLException {

        String sql = "update cliente set nomcli = ?, endcli = ?, baicli = ?, "
                + "comcli = ?, cepcli = ?, celcli = ? where codcli = ?";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, cli.getNomcli());
            st.setString(2, cli.getEndcli());
            st.setString(3, cli.getBaicli());
            st.setString(4, cli.getComcli());
            st.setLong(5, cli.getCepcli());
            st.setLong(6, cli.getCelcli());
            st.setLong(7, cli.getCodcli());

            st.execute();
            st.close();
        }

        this.con.close();

    }

    public List<Cliente> listarClientes() throws SQLException {
        String sql = "select * from cliente";
        List<Cliente> clientes = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();

            clientes = new ArrayList<Cliente>();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodcli(rs.getInt("codcli"));
                c.setNomcli(rs.getString("nomcli"));
                c.setEndcli(rs.getString("endcli"));
                c.setBaicli(rs.getString("baicli"));
                c.setComcli(rs.getString("comcli"));
                c.setCepcli(rs.getLong("cepcli"));
                c.setCelcli(rs.getLong("celcli"));

                clientes.add(c);
            }

            rs.close();
            st.close();

        }

        this.con.close();
        return clientes;
    }

    public Cliente getCliente(int codcli) throws SQLException {
        String sql = "select * from cliente where codcli = ?";
        Cliente cliente = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setInt(1, codcli);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCodcli(rs.getInt("codcli"));
                    cliente.setNomcli(rs.getString("nomcli"));
                    cliente.setEndcli(rs.getString("endcli"));
                    cliente.setBaicli(rs.getString("baicli"));
                    cliente.setComcli(rs.getString("comcli"));
                    cliente.setCepcli(rs.getLong("cepcli"));
                    cliente.setCelcli(rs.getLong("celcli"));
                }
            }
            st.close();
        }

        this.con.close();
        return cliente;
    }

}