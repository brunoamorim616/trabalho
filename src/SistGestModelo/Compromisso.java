
package SistGestModelo;

public class Compromisso {
    private String dataCriacao;
    private String dataCompromisso;
    private String titulo;
    private String descricao;
    private int colaborador_id;
    private int equipe_id;

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataCompromisso() {
        return dataCompromisso;
    }

    public void setDataCompromisso(String dataCompromisso) {
        this.dataCompromisso = dataCompromisso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getColaborador_id() {
        return colaborador_id;
    }

    public void setColaborador_id(int colaborador_id) {
        this.colaborador_id = colaborador_id;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    
}
