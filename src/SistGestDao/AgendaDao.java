
package SistGestDao;

import SistGestDao.ConnectionFactory;
import SistGestModelo.Compromisso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaDao extends ConnectionFactory  {
    private Connection con;

    public AgendaDao() {
        this.con = this.getConnection();
    }

    public void inserir(Compromisso comp) throws SQLException {

        String sql = "insert into agenda "
                + "(dataCriacao, dataCompromisso, titulo, descricao, colaborador_id, equipe_id) "
                + "values (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, "2019-03-11 19:00:00");
            st.setString(2, "2019-03-11 21:00:00");
            st.setString(3, comp.getTitulo());
            st.setString(4, comp.getDescricao());
            st.setInt(5, comp.getColaborador_id());
            st.setInt(6, comp.getEquipe_id());

            st.execute();
            st.close();
        }

        this.con.close();

    }
    
    public void eliminar(int colaborador_id) throws SQLException {

        String sql = "delete from agenda where colaborador_id = ?";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setInt(1, colaborador_id);
            st.execute();
            st.close();
        }

        this.con.close();

    }

    public void alterar(Compromisso comp) throws SQLException {

        String sql2 = "insert into agenda "
                + "(dataCriacao, dataCompromisso, "
                + "titulo, descricao)"
                + "values (?, ?, ?, ?);";

        String sql = "update agenda set dataCriacao = ?, dataCompromisso = ?, titulo = ?, "
                + "descricao = ?, where colaborador_id = ?";

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setString(1, comp.getDataCriacao());
            st.setString(2, comp.getDataCompromisso());
            st.setString(3, comp.getTitulo());
            st.setString(4, comp.getDescricao());

            st.execute();
            st.close();
        }

        this.con.close();

    }

    public List<Compromisso> listaCompromisso() throws SQLException {
        String sql = "select * from cliente";
        List<Compromisso> clientes = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();

            clientes = new ArrayList<Compromisso>();

            while (rs.next()) {
                Compromisso c = new Compromisso();
                c.setDataCriacao(rs.getString("dataCriacao"));
                c.setDataCompromisso(rs.getString("dataCompromisso"));
                c.setTitulo(rs.getString("titulo"));
                c.setDescricao(rs.getString("descricao"));
                c.setColaborador_id(rs.getInt("colaborador_id"));
                c.setEquipe_id(rs.getInt("equipe_id"));

                clientes.add(c);
            }

            rs.close();
            st.close();

        }

        this.con.close();
        return clientes;
    }

    public Compromisso getCompromisso(int colaborador_id) throws SQLException {
        String sql = "select * from cliente where codcli = ?";
        Compromisso compr = null;

        try (PreparedStatement st = this.con.prepareStatement(sql)) {
            st.setInt(1, colaborador_id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    compr = new Compromisso();
                    compr.setDataCriacao(rs.getString("dataCriacao"));
                    compr.setDataCompromisso(rs.getString("dataCompromisso"));
                    compr.setTitulo(rs.getString("titulo"));
                    compr.setDescricao(rs.getString("descricao"));
                    compr.setColaborador_id(rs.getInt("colaborador_id"));
                    compr.setEquipe_id(rs.getInt("equipe_id"));
                }
            }
            st.close();
        }

        this.con.close();
        return compr;
    }

}
