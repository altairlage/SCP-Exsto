package scpexsto.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Altair Lage
 */
@Entity
@Table(name = "RETRABALHO")
public class Retrabalho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "ORIGEM")
    private String origem;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INICIO")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FIM")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;

    @Column(name = "REFUGOU")
    private boolean refugou;

    @ManyToOne
    @JoinColumn(name = "USUARIO_FK")
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "REPARO_FK")
    private List<Defeito> defeitos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
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

    public boolean isRefugou() {
        return refugou;
    }

    public void setRefugou(boolean refugou) {
        this.refugou = refugou;
    }

    public List<Defeito> getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(List<Defeito> defeitos) {
        this.defeitos = defeitos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.origem != null ? this.origem.hashCode() : 0);
        hash = 41 * hash + (this.dataInicio != null ? this.dataInicio.hashCode() : 0);
        hash = 41 * hash + (this.dataFim != null ? this.dataFim.hashCode() : 0);
        hash = 41 * hash + (this.refugou ? 1 : 0);
        hash = 41 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        hash = 41 * hash + (this.defeitos != null ? this.defeitos.hashCode() : 0);
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
        final Retrabalho other = (Retrabalho) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.origem != other.origem) {
            return false;
        }
        if (this.dataInicio != other.dataInicio && (this.dataInicio == null || !this.dataInicio.equals(other.dataInicio))) {
            return false;
        }
        if (this.dataFim != other.dataFim && (this.dataFim == null || !this.dataFim.equals(other.dataFim))) {
            return false;
        }
        if (this.refugou != other.refugou) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        if (this.defeitos != other.defeitos && (this.defeitos == null || !this.defeitos.equals(other.defeitos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Retrabalho{" + "id=" + id + ", origem=" + origem + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", refugou=" + refugou + ", usuario=" + usuario + ", defeitos=" + defeitos + '}';
    }

}
