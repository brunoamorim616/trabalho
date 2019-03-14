package SistGestDao;

import SistGestDao.ConnectionFactory;
import SistGestModelo.Colaborador;
import java.sql.Connection;
import SistGestViews.CadastroColaborador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDao extends ConnectionFactory {
    
    private Connection con;

    public ColaboradorDao() {
        this.con = this.getConnection();
    }
    
    public void inserir(Colaborador col) throws SQLException {

        String sql = "insert into cliente (usuario, senha, nome, rua, bairro, cep, cidade, estado, telefone)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, col.getUsuario());
            st.setString(2, col.getSenha());
            st.setString(3, col.getNome());
            st.setString(4, col.getRua());
            st.setString(5, col.getBairro());
            st.setLong(6, col.getCep());
            st.setString(7, col.getCidade());
            st.setString(8, col.getEstado());
            st.setLong(9, col.getTelefone());
            
            st.execute();
            st.close();
        }

        this.con.close();

    }

}
