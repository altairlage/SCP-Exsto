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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

    public static final String NAO_INICIADO = "Não iniciado";
    public static final String INSP_VISUAL_APR = "Aprovado na inspeção visual";
    public static final String INSP_VISUAL_RET = "Em retrabalho da inspeção visual";
    public static final String INSP_FINAL_APR = "Aprovado na inspeção final";
    public static final String INSP_FINAL_RET = "Em retrabalho na inspeção final";
    public static final String INT_FINAL_APR = "Aprovado na integração final";
    public static final String INT_FINAL_RET = "Em retrabalho na integração final";
    public static final String TESTE_ELE_APR = "Aprovado no teste elétrico";
    public static final String TESTE_ELE_RET = "Em retrabalho no teste elétrico";

    @Id
    @Column(name = "NSERIE", nullable = false, unique = true)
    private String nserie;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CRIACAO")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;

    @Column(name = "REFUGO")
    private boolean refugo;

    @Column(name = "MOTIVO_REFUGO", length = 255)
    private String motivoRefugo;

    @Column(name = "ETAPA", length = 255)
    private String etapa;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "QTD_TESTE_ELE")
    private int qtdTesteEletrico;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lote lote;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Pem> pems;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INTEGRACAO_FIN_FK", nullable = true)
    private IntegracaoFinal integracaoFinal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INSPECAO_FIN_FK", nullable = true)
    private InspecaoFinal inspecaoFinal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INSPECAO_VIS_FK", nullable = true)
    private InspecaoVisual inspecaoVisual;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUTO_FK", nullable = true)
    private List<TesteEletrico> testesEletricos;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUTO_FK", nullable = true)
    private List<Retrabalho> retrabalhos;

    public static List<String> getEstapasProduto() {
        List<String> lista = new ArrayList<String>();

        lista.add(NAO_INICIADO);
        lista.add(INSP_FINAL_APR);
        lista.add(INSP_FINAL_RET);
        lista.add(INSP_VISUAL_APR);
        lista.add(INSP_VISUAL_RET);
        lista.add(INT_FINAL_APR);
        lista.add(INT_FINAL_RET);
        lista.add(TESTE_ELE_APR);
        lista.add(TESTE_ELE_RET);

        return lista;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isRefugo() {
        return refugo;
    }

    public void setRefugo(boolean refugo) {
        this.refugo = refugo;
    }

    public String getMotivoRefugo() {
        return motivoRefugo;
    }

    public void setMotivoRefugo(String motivoRefugo) {
        this.motivoRefugo = motivoRefugo;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQtdTesteEletrico() {
        return qtdTesteEletrico;
    }

    public void setQtdTesteEletrico(int qtdTesteEletrico) {
        this.qtdTesteEletrico = qtdTesteEletrico;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<Pem> getPems() {
        return pems;
    }

    public void setPems(List<Pem> Pems) {
        this.pems = Pems;
    }

    public IntegracaoFinal getIntegracaoFinal() {
        return integracaoFinal;
    }

    public void setIntegracaoFinal(IntegracaoFinal integracaoFinal) {
        this.integracaoFinal = integracaoFinal;
    }

    public InspecaoFinal getInspecaoFinal() {
        return inspecaoFinal;
    }

    public void setInspecaoFinal(InspecaoFinal inspecaoFinal) {
        this.inspecaoFinal = inspecaoFinal;
    }

    public InspecaoVisual getInspecaoVisual() {
        return inspecaoVisual;
    }

    public void setInspecaoVisual(InspecaoVisual inspecaoVisual) {
        this.inspecaoVisual = inspecaoVisual;
    }

    public List<TesteEletrico> getTestesEletricos() {
        return testesEletricos;
    }

    public void setTestesEletricos(List<TesteEletrico> testesEletricos) {
        this.testesEletricos = testesEletricos;
    }

    public List<Retrabalho> getRetrabalhos() {
        return retrabalhos;
    }

    public void setRetrabalhos(List<Retrabalho> retrabalhos) {
        this.retrabalhos = retrabalhos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.nserie != null ? this.nserie.hashCode() : 0);
        hash = 29 * hash + (this.dataCriacao != null ? this.dataCriacao.hashCode() : 0);
        hash = 29 * hash + (this.refugo ? 1 : 0);
        hash = 29 * hash + (this.motivoRefugo != null ? this.motivoRefugo.hashCode() : 0);
        hash = 29 * hash + (this.etapa != null ? this.etapa.hashCode() : 0);
        hash = 29 * hash + this.status;
        hash = 29 * hash + this.qtdTesteEletrico;
        hash = 29 * hash + (this.lote != null ? this.lote.hashCode() : 0);
        hash = 29 * hash + (this.pems != null ? this.pems.hashCode() : 0);
        hash = 29 * hash + (this.integracaoFinal != null ? this.integracaoFinal.hashCode() : 0);
        hash = 29 * hash + (this.inspecaoFinal != null ? this.inspecaoFinal.hashCode() : 0);
        hash = 29 * hash + (this.inspecaoVisual != null ? this.inspecaoVisual.hashCode() : 0);
        hash = 29 * hash + (this.testesEletricos != null ? this.testesEletricos.hashCode() : 0);
        hash = 29 * hash + (this.retrabalhos != null ? this.retrabalhos.hashCode() : 0);
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
        final Produto other = (Produto) obj;
        if ((this.nserie == null) ? (other.nserie != null) : !this.nserie.equals(other.nserie)) {
            return false;
        }
        if (this.dataCriacao != other.dataCriacao && (this.dataCriacao == null || !this.dataCriacao.equals(other.dataCriacao))) {
            return false;
        }
        if (this.refugo != other.refugo) {
            return false;
        }
        if ((this.motivoRefugo == null) ? (other.motivoRefugo != null) : !this.motivoRefugo.equals(other.motivoRefugo)) {
            return false;
        }
        if ((this.etapa == null) ? (other.etapa != null) : !this.etapa.equals(other.etapa)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.qtdTesteEletrico != other.qtdTesteEletrico) {
            return false;
        }
        if (this.lote != other.lote && (this.lote == null || !this.lote.equals(other.lote))) {
            return false;
        }
        if (this.pems != other.pems && (this.pems == null || !this.pems.equals(other.pems))) {
            return false;
        }
        if (this.integracaoFinal != other.integracaoFinal && (this.integracaoFinal == null || !this.integracaoFinal.equals(other.integracaoFinal))) {
            return false;
        }
        if (this.inspecaoFinal != other.inspecaoFinal && (this.inspecaoFinal == null || !this.inspecaoFinal.equals(other.inspecaoFinal))) {
            return false;
        }
        if (this.inspecaoVisual != other.inspecaoVisual && (this.inspecaoVisual == null || !this.inspecaoVisual.equals(other.inspecaoVisual))) {
            return false;
        }
        if (this.testesEletricos != other.testesEletricos && (this.testesEletricos == null || !this.testesEletricos.equals(other.testesEletricos))) {
            return false;
        }
        if (this.retrabalhos != other.retrabalhos && (this.retrabalhos == null || !this.retrabalhos.equals(other.retrabalhos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "nserie=" + nserie + ", dataProducao=" + dataCriacao + ", refugo=" + refugo + ", motivoRefugo=" + motivoRefugo + ", etapa=" + etapa + ", status=" + status + ", qtdTesteEletrico=" + qtdTesteEletrico + ", lote=" + lote + ", Pems=" + pems + ", integracaoFinal=" + integracaoFinal + ", inspecaoFinal=" + inspecaoFinal + ", inspecaoVisual=" + inspecaoVisual + ", testesEletricos=" + testesEletricos + ", retrabalhos=" + retrabalhos + '}';
    }

}
