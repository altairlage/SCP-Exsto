package scpexsto.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSE_SOLUCAO")
public class ClasseSolucao implements Serializable {

    public static String TIPO_AUTOMACAO = "Automação";
    public static String TIPO_ELETRONICA = "Eletrônica";
    public static String TIPO_ELETRICA = "Elétrica";

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    public static List<String> getTiposClasseSolucao() {
        List<String> lista = new ArrayList<String>();

        lista.add(TIPO_AUTOMACAO);
        lista.add(TIPO_ELETRONICA);
        lista.add(TIPO_ELETRICA);

        return lista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        hash = 23 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClasseSolucao other = (ClasseSolucao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if ((this.tipo == null) ? (other.tipo != null) : !this.tipo.equals(other.tipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClasseSolucao{" + "id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + '}';
    }
}
