package scpexsto.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LOTE")
public class Lote implements Serializable {

    public static String STATUS_NÃO_INICIADO = "Não iniciado";
    public static String STATUS_EM_PRODUCAO = "Em produção";
    public static String STATUS_FINALIZADO = "Finalizado";

    @Id
    @Column(name = "ID")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CRIACAO", nullable = false, unique = false)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;

    @Column(name = "NSERIE_INICIAL")
    private String nSerieInicial;

    @Column(name = "NSERIE_FINAL")
    private String nSerieFinal;

    @Column(name = "QTD")
    private int qtd;

    @Column(name = "QTD_ATUAL")
    private int qtdAtual;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PROD")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataProd;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lote")
    private List<Produto> produtos;

    @ManyToOne(fetch = FetchType.EAGER)
    private Modelo modelo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PROG_INICIO")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataProgInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PROG_FIM")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataProgFim;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_REAL_INICIO")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataRealInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_REAL_FIM")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataRealFim;

    @Column(name = "OBSERVACOES", length = 255)
    private String observacoes;

    @Column(name = "STATUS", length = 255)
    private String status;

    @Column(name = "NPEDIDO", length = 255)
    private String npedido;

    public static List<String> getStatusAsList() {
        List<String> lista = new ArrayList<String>();
        lista.add(STATUS_NÃO_INICIADO);
        lista.add(STATUS_EM_PRODUCAO);
        lista.add(STATUS_FINALIZADO);

        return lista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNSerieInicial() {
        return getnSerieInicial();
    }

    public void setNSerieInicial(String nSerieInicial) {
        this.setnSerieInicial(nSerieInicial);
    }

    public String getNSerieFinal() {
        return getnSerieFinal();
    }

    public void setNSerieFinal(String nSerieFinal) {
        this.setnSerieFinal(nSerieFinal);
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(int qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public Date getDataProd() {
        return dataProd;
    }

    public void setDataProd(Date dataProd) {
        this.dataProd = dataProd;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getnSerieInicial() {
        return nSerieInicial;
    }

    public void setnSerieInicial(String nSerieInicial) {
        this.nSerieInicial = nSerieInicial;
    }

    public String getnSerieFinal() {
        return nSerieFinal;
    }

    public void setnSerieFinal(String nSerieFinal) {
        this.nSerieFinal = nSerieFinal;
    }

    public Date getDataProgInicio() {
        return dataProgInicio;
    }

    public void setDataProgInicio(Date dataProgInicio) {
        this.dataProgInicio = dataProgInicio;
    }

    public Date getDataProgFim() {
        return dataProgFim;
    }

    public void setDataProgFim(Date dataProgFim) {
        this.dataProgFim = dataProgFim;
    }

    public Date getDataRealInicio() {
        return dataRealInicio;
    }

    public void setDataRealInicio(Date dataRealInicio) {
        this.dataRealInicio = dataRealInicio;
    }

    public Date getDataRealFim() {
        return dataRealFim;
    }

    public void setDataRealFim(Date dataRealFim) {
        this.dataRealFim = dataRealFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNpedido() {
        return npedido;
    }

    public void setNpedido(String npedido) {
        this.npedido = npedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        hash = 89 * hash + (this.getDataCriacao() != null ? this.getDataCriacao().hashCode() : 0);
        hash = 89 * hash + this.getQtd();
        hash = 89 * hash + this.getQtdAtual();
        hash = 89 * hash + (this.getDataProd() != null ? this.getDataProd().hashCode() : 0);
        hash = 89 * hash + (this.getDataProgInicio() != null ? this.getDataProgInicio().hashCode() : 0);
        hash = 89 * hash + (this.getDataProgFim() != null ? this.getDataProgFim().hashCode() : 0);
        hash = 89 * hash + (this.getDataRealInicio() != null ? this.getDataRealInicio().hashCode() : 0);
        hash = 89 * hash + (this.getDataRealFim() != null ? this.getDataRealFim().hashCode() : 0);
        hash = 89 * hash + (this.getObservacoes() != null ? this.getObservacoes().hashCode() : 0);
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
        final Lote other = (Lote) obj;
        if ((this.getId() == null) ? (other.getId() != null) : !this.id.equals(other.id)) {
            return false;
        }
        if (this.getDataCriacao() != other.getDataCriacao() && (this.getDataCriacao() == null || !this.dataCriacao.equals(other.dataCriacao))) {
            return false;
        }
        if (this.getQtd() != other.getQtd()) {
            return false;
        }
        if (this.getQtdAtual() != other.getQtdAtual()) {
            return false;
        }
        if (this.getDataProd() != other.getDataProd() && (this.getDataProd() == null || !this.dataProd.equals(other.dataProd))) {
            return false;
        }
        if (this.getDataProgInicio() != other.getDataProgInicio() && (this.getDataProgInicio() == null || !this.dataProgInicio.equals(other.dataProgInicio))) {
            return false;
        }
        if (this.getDataProgFim() != other.getDataProgFim() && (this.getDataProgFim() == null || !this.dataProgFim.equals(other.dataProgFim))) {
            return false;
        }
        if (this.getDataRealInicio() != other.getDataRealInicio() && (this.getDataRealInicio() == null || !this.dataRealInicio.equals(other.dataRealInicio))) {
            return false;
        }
        if (this.getDataRealFim() != other.getDataRealFim() && (this.getDataRealFim() == null || !this.dataRealFim.equals(other.dataRealFim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lote{" + "id=" + getId()
                + ", dataCriacao=" + getDataCriacao()
                + ", nSerieInicial=" + getnSerieInicial()
                + ", nSerieFinal=" + getnSerieFinal()
                + ", qtd=" + getQtd()
                + ", qtdAtual=" + getQtdAtual()
                + ", dataProd=" + getDataProd()
                + ", dataProgInicio=" + getDataProgInicio()
                + ", dataProgFim=" + getDataProgFim()
                + ", dataRealInicio=" + getDataRealInicio()
                + ", dataRealFim=" + getDataRealFim()
                + ", observacoes=" + getObservacoes() + '}';
    }
}
