package SistGestDao;

import SistGestDao.ConnectionFactory;
import SistGestModelo.Colaborador;
import java.sql.Connection;
import SistGestViews.CadastroColaborador;
import java.awt.CardLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ColaboradorDao extends ConnectionFactory {

    JOptionPane msg = new JOptionPane();
    private Connection con;

    public ColaboradorDao() {
        this.con = this.getConnection();
    }

    public void inserir(Colaborador col) throws SQLException {

        String sql = "insert into colaborador (usuario, senha, nome, rua, bairro, cep, cidade, estado, telefone, tipo, equipe_id)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, col.getUsuario());
            st.setString(2, col.getSenha());
            st.setString(3, col.getNome());
            st.setString(4, col.getRua());
            st.setString(5, col.getBairro());
            st.setString(6, col.getCep());
            st.setString(7, col.getCidade());
            st.setString(8, col.getEstado());
            st.setString(9, col.getTelefone());
            st.setString(10, col.getTipo());
            st.setInt(11, col.getEquipe_id());

            st.execute();
            st.close();
        }
    }

    public Colaborador getColaborador(String usuario, String senha) throws SQLException {
        String sql = "select * from colaborador where usuario = ? and senha = ?";

        Colaborador c = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, usuario);
            st.setString(2, senha);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Colaborador();
                    c.setId(rs.getInt("id"));
                    c.setTipo(rs.getString("tipo"));
                    c.setBairro(rs.getString("bairro"));
                    c.setCep(rs.getString("cep"));
                    c.setCidade(rs.getString("cidade"));
                    c.setEquipe_id(rs.getInt("equipe_id"));
                    c.setEstado(rs.getString("estado"));
                    c.setNome(rs.getString("nome"));
                    c.setRua(rs.getString("rua"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setSenha(rs.getString("senha"));
                    c.setUsuario(rs.getString("usuario"));
                }
            }
            st.close();
        }

        this.con.close();
        return c;

    }
    
    public Colaborador getColaborador(int idColaborador) throws SQLException {
        String sql = "select * from colaborador where id = ?";

        Colaborador c = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setInt(1, idColaborador);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Colaborador();
                    c.setId(rs.getInt("id"));
                    c.setTipo(rs.getString("tipo"));
                    c.setBairro(rs.getString("bairro"));
                    c.setCep(rs.getString("cep"));
                    c.setCidade(rs.getString("cidade"));
                    c.setEquipe_id(rs.getInt("equipe_id"));
                    c.setEstado(rs.getString("estado"));
                    c.setNome(rs.getString("nome"));
                    c.setRua(rs.getString("rua"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setSenha(rs.getString("senha"));
                    c.setUsuario(rs.getString("usuario"));
                }
            }
            st.close();
        }

        this.con.close();
        return c;

    }
    
    public List<Colaborador> listarColaboradores() throws SQLException {
        String sql = "select * from colaborador";
        List<Colaborador> clientes = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();

            clientes = new ArrayList<Colaborador>();
// "insert into colaborador (usuario, senha, nome, rua, bairro, cep, cidade, estado, telefone, tipo, equipe_id)"
            while (rs.next()) {
                Colaborador c = new Colaborador();
                c.setId(rs.getInt("id"));
                c.setEquipe_id(rs.getInt("equipe_id"));
                c.setTipo(rs.getString("tipo"));
                c.setUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                c.setNome(rs.getString("nome"));
                c.setRua(rs.getString("rua"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                c.setCep(rs.getString("cep"));
                c.setTelefone(rs.getString("telefone"));

                clientes.add(c);
            }

            rs.close();
            st.close();

        }

        this.con.close();
        return clientes;
    }
    
    public void alterar(Colaborador col) throws SQLException {

        String sql2 = "insert into colaborador (usuario, senha, nome, rua, bairro, "
                + "cep, cidade, estado, telefone, tipo, equipe_id)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sql = "update colaborador set usuario = ?, senha = ?, nome = ?, rua = ?, "
                + "bairro = ?, cep = ?, cidade = ?, estado = ?, telefone = ?,"
                + " tipo = ?, equipe_id = ? where id = ?";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, col.getUsuario());
            st.setString(2, col.getSenha());
            st.setString(3, col.getNome());
            st.setString(4, col.getRua());
            st.setString(5, col.getBairro());
            st.setString(6, col.getCep());
            st.setString(7, col.getCidade());
            st.setString(8, col.getEstado());
            st.setString(9, col.getTelefone());
            st.setString(10, col.getTipo());
            st.setInt(11, col.getEquipe_id());
            st.setInt(12, col.getId());
            
            st.execute();
            st.close();
        }


        this.con.close();

    }
    
    public void eliminar(int id) throws SQLException {

        String sql = "delete from colaborador where id = ?";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setInt(1,id);
            st.execute();
            st.close();
        }

        this.con.close();

    }

}
