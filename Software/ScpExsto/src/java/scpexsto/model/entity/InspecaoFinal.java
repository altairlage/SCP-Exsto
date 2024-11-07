package scpexsto.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Altair Lage
 */
@Entity
@Table(name = "INSPECAO_FINAL")
public class InspecaoFinal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INICIO")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FIM")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;

    @Column(name = "QTD_REPROCESSO")
    private int qtdReprocesso;

    @ManyToOne
    @JoinColumn(name = "USUARIO_FK")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getQtdReprocesso() {
        return qtdReprocesso;
    }

    public void setQtdReprocesso(int qtdReprocesso) {
        this.qtdReprocesso = qtdReprocesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.dataInicio != null ? this.dataInicio.hashCode() : 0);
        hash = 37 * hash + (this.dataFim != null ? this.dataFim.hashCode() : 0);
        hash = 37 * hash + this.qtdReprocesso;
        hash = 37 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
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
        final InspecaoFinal other = (InspecaoFinal) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.dataInicio != other.dataInicio && (this.dataInicio == null || !this.dataInicio.equals(other.dataInicio))) {
            return false;
        }
        if (this.dataFim != other.dataFim && (this.dataFim == null || !this.dataFim.equals(other.dataFim))) {
            return false;
        }
        if (this.qtdReprocesso != other.qtdReprocesso) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InspecaoFinal{" + "id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", qtdReprocesso=" + qtdReprocesso + ", usuario=" + usuario + '}';
    }
}
