package scpexsto.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Altair Lage
 */
@Entity
public class Modelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "REVISAO", nullable = false, length = 30)
    private String revisao;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "IT")
    private String it;

    @Column(name = "TIPO")
    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRevisao() {
        return revisao;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("id: ").append(this.getId());
        str.append(", modelo: ").append(this.getModelo());
        str.append(", descricao: ").append(this.getDescricao());
        str.append(", IT: ").append(this.getIt());
        str.append(", tipo: ").append(this.getTipo());

        return str.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.modelo != null ? this.modelo.hashCode() : 0);
        hash = 47 * hash + (this.revisao != null ? this.revisao.hashCode() : 0);
        hash = 47 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        hash = 47 * hash + (this.it != null ? this.it.hashCode() : 0);
        hash = 47 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
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
        final Modelo other = (Modelo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.modelo == null) ? (other.modelo != null) : !this.modelo.equals(other.modelo)) {
            return false;
        }
        if ((this.revisao == null) ? (other.revisao != null) : !this.revisao.equals(other.revisao)) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if ((this.it == null) ? (other.it != null) : !this.it.equals(other.it)) {
            return false;
        }
        if ((this.tipo == null) ? (other.tipo != null) : !this.tipo.equals(other.tipo)) {
            return false;
        }
        return true;
    }

}
