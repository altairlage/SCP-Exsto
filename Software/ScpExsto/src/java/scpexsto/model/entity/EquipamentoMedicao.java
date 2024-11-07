package scpexsto.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Altair Lage
 */
@Entity
@Table(name = "EQUIP_MEDICAO")
public class EquipamentoMedicao implements Serializable {

    public static final String TIPO_OSCILOSCOPIO = "Osciloscópio";
    public static final String TIPO_GER_FUNCAO = "Gerador de Funções";
    public static final String TIPO_MULTIMETRO = "Multímetro";
    public static final String TIPO_FONTE = "Fonte";
    
    
    @Id
    @Column(name = "PATRIMONIO", nullable = false, unique = true)
    private String patrimonio;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "SETOR")
    private String setor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_VENC_CALIB", nullable = false)
    private Date dataVencimento;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(name = "TIPO_EQUIP", nullable = false)
    private String tipo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TESTE_ELET_EQUIP_MEDICAO",
            joinColumns = {
                @JoinColumn(name = "EQUIP_MEDICAO_FK")},
            inverseJoinColumns = {
                @JoinColumn(name = "TESTE_ELET_FK")})
    private List<TesteEletrico> testeEletricos;

    
    public static List<String> getTiposEquipamento() {
        List<String> lista = new ArrayList<String>();

        lista.add(TIPO_OSCILOSCOPIO);
        lista.add(TIPO_GER_FUNCAO);
        lista.add(TIPO_MULTIMETRO);
        lista.add(TIPO_FONTE);

        return lista;
    }
    
    
    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TesteEletrico> getTesteEletricos() {
        return testeEletricos;
    }

    public void setTesteEletricos(List<TesteEletrico> testeEletricos) {
        this.testeEletricos = testeEletricos;
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
        hash = 37 * hash + (this.getPatrimonio() != null ? this.getPatrimonio().hashCode() : 0);
        hash = 37 * hash + (this.getMarca() != null ? this.getMarca().hashCode() : 0);
        hash = 37 * hash + (this.getModelo() != null ? this.getModelo().hashCode() : 0);
        hash = 37 * hash + (this.getSetor() != null ? this.getSetor().hashCode() : 0);
        hash = 37 * hash + (this.getDataVencimento() != null ? this.getDataVencimento().hashCode() : 0);
        hash = 37 * hash + (this.getStatus() != null ? this.getStatus().hashCode() : 0);
        hash = 37 * hash + (this.getTipo() != null ? this.getTipo().hashCode() : 0);
        hash = 37 * hash + (this.getTesteEletricos() != null ? this.getTesteEletricos().hashCode() : 0);
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
        final EquipamentoMedicao other = (EquipamentoMedicao) obj;
        if ((this.getPatrimonio() == null) ? (other.getPatrimonio() != null) : !this.patrimonio.equals(other.patrimonio)) {
            return false;
        }
        if ((this.getMarca() == null) ? (other.getMarca() != null) : !this.marca.equals(other.marca)) {
            return false;
        }
        if ((this.getModelo() == null) ? (other.getModelo() != null) : !this.modelo.equals(other.modelo)) {
            return false;
        }
        if ((this.getSetor() == null) ? (other.getSetor() != null) : !this.setor.equals(other.setor)) {
            return false;
        }
        if (this.getDataVencimento() != other.getDataVencimento() && (this.getDataVencimento() == null || !this.dataVencimento.equals(other.dataVencimento))) {
            return false;
        }
        if ((this.getStatus() == null) ? (other.getStatus() != null) : !this.status.equals(other.status)) {
            return false;
        }
        if ((this.getTipo() == null) ? (other.getTipo() != null) : !this.tipo.equals(other.tipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EquipamentoMedicao{" + "patrimonio=" + getPatrimonio() + ", marca=" + getMarca() + ", modelo=" + getModelo() + ", setor=" + getSetor() + ", dataVencimento=" + getDataVencimento() + ", status=" + getStatus() + ", tipo=" + getTipo() + ", testeEletricos=" + getTesteEletricos() + '}';
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
