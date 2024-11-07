package scpexsto.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Altair Lage
 */
@Entity
@Table(name = "TESTE_ELETRICO")
public class TesteEletrico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INICIO")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FIM")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;

    @Column(name = "VOLTAGEM", length = 50)
    private String voltagem;

    @ManyToOne
    @JoinColumn(name = "USUARIO_FK")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TESTE_ELET_EQUIP_MEDICAO",
            joinColumns = {
                @JoinColumn(name = "TESTE_ELET_FK")},
            inverseJoinColumns = {
                @JoinColumn(name = "EQUIP_MEDICAO_FK")})
    private List<EquipamentoMedicao> equipamentos;

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

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EquipamentoMedicao> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoMedicao> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.dataInicio != null ? this.dataInicio.hashCode() : 0);
        hash = 41 * hash + (this.dataFim != null ? this.dataFim.hashCode() : 0);
        hash = 41 * hash + (this.voltagem != null ? this.voltagem.hashCode() : 0);
        hash = 41 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        hash = 41 * hash + (this.equipamentos != null ? this.equipamentos.hashCode() : 0);
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
        final TesteEletrico other = (TesteEletrico) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.dataInicio != other.dataInicio && (this.dataInicio == null || !this.dataInicio.equals(other.dataInicio))) {
            return false;
        }
        if (this.dataFim != other.dataFim && (this.dataFim == null || !this.dataFim.equals(other.dataFim))) {
            return false;
        }
        if ((this.voltagem == null) ? (other.voltagem != null) : !this.voltagem.equals(other.voltagem)) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        if (this.equipamentos != other.equipamentos && (this.equipamentos == null || !this.equipamentos.equals(other.equipamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TesteEletrico{" + "id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", voltagem=" + voltagem + ", usuario=" + usuario + ", equipamentos=" + equipamentos + '}';
    }

}
