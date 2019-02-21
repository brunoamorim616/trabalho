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