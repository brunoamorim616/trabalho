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
    
    public List<Cliente> listarClientes() throws SQLException {
        String sql = "select * from cliente";
        
        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            
            List <Cliente> clientes = new ArrayList<Cliente>();
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodcli(rs.getInt("codcli"));
                c.setNomcli(rs.getString("nomcli"));
                c.setEndcli(rs.getString("endcli"));
                c.setBaicli(rs.getString("baicli"));
                c.setComcli(rs.getString("comcli"));
                c.setCepcli(rs.getInt("cepcli"));
                c.setCelcli(rs.getInt("celcli"));
                
                clientes.add(c);
            }
            
            rs.close();
            st.close();
            this.con.close();
            
            return clientes;
            
        }
    }
    
}