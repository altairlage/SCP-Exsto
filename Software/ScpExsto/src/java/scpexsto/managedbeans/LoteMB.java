package scpexsto.managedbeans;

import com.sun.faces.context.flash.ELFlash;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Lote;
import scpexsto.model.entity.Modelo;
import scpexsto.model.entity.Produto;

@ManagedBean(name = "loteMB")
@ViewScoped
public class LoteMB extends AbstractMB implements Serializable {

    private Lote lote;
    private List<Lote> lista;
    private List<Produto> listaProdutos;
    private List<String> tiposProduto;
    private List<Modelo> todosModelosObj;
    private Modelo modelo;
    private String modeloSelecionado;

    @PostConstruct
    public void init() {
        this.tiposProduto = new ArrayList<String>();
        this.tiposProduto.add("Automação");
        this.tiposProduto.add("Eletrônica");
        this.tiposProduto.add("Elétrica");
        this.todosModelosObj = ServiceLocator.getModeloService().readAll();
    }

    public String getModeloSelecionado() {
        return this.modeloSelecionado;
    }

    public void setModeloSelecionado(String modelo) {
        this.modeloSelecionado = modelo;
    }

    public List<Lote> getTodosLotes() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getLoteService().readAll();
        }

        return lista;
    }
    
    public List<String> getTodosStatus(){
        return Lote.getStatusAsList();
    }

    public List getTiposProduto() {
        return this.tiposProduto;
    }

    public void getLista() {
        this.lista = ServiceLocator.getLoteService().readAll();
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public void resetLote() {
        this.lote = new Lote();
    }

    public String setLoteSelecionadoADetalhar() {
        Lote l = ServiceLocator.getLoteService().getLoteByIdWithJoinQueryProdutos(this.lote.getId());
        Modelo m = ServiceLocator.getModeloService().getModeloByLoteWithQuery(this.lote.getId());

        ELFlash.getFlash().put("loteSelecionado", this.lote);
        ELFlash.getFlash().put("modelo", m);

        if (l != null) {
            ELFlash.getFlash().put("listaProdutos", l.getProdutos());
        }
        return "/protected/lider/lote/loteDetail.xhtml";
    }

    public Lote getLoteADetalhar() {
        if (this.lote == null) {
            this.lote = (Lote) ELFlash.getFlash().get("loteSelecionado");
            this.listaProdutos = (List<Produto>) ELFlash.getFlash().get("listaProdutos");
            this.modelo = (Modelo) ELFlash.getFlash().get("modelo");
        }

        return this.lote;
    }

    public List<Produto> getListaProdutosDetail() {
        if (this.listaProdutos == null) {
            this.listaProdutos = (List<Produto>) ELFlash.getFlash().get("listaProdutos");
        }

        return listaProdutos;
    }

    public Modelo getModeloDetail() {
        if (this.modelo == null) {
            this.modelo = (Modelo) ELFlash.getFlash().get("modelo");
        }

        return this.modelo;
    }

    public String voltar() {
        this.lote = new Lote();

        return "/protected/lider/lote/lote.xhtml?faces-redirect=true";
    }

    public void createLote() {
        try {
            this.lote.setDataCriacao(new Date(System.currentTimeMillis()));
            this.lote.setQtdAtual(0);

            List<String> erros = ServiceLocator.getLoteService().validaCampos(this.lote);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getLoteService().validaLoteExistente(lote)) {
                    ServiceLocator.getLoteService().create(this.lote);
                    closeDialog();
                    displayInfoMessageToUser("Lote criado com sucesso!");
                    this.getLista();
                    this.resetLote();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O login já foi utilizado, tente outro login!");
                }
            } else {
                keepDialogOpen();
                for (Iterator<String> it = erros.iterator(); it.hasNext();) {
                    displayErrorMessageToUser(it.next());
                }
            }
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void updateLote() {
        try {

            List<String> erros = ServiceLocator.getLoteService().validaCampos(this.lote);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getLoteService().validaLoteExistente(lote)) {
                    ServiceLocator.getLoteService().update(this.lote);
                    closeDialog();
                    displayInfoMessageToUser("Lote atualizado com sucesso!");
                    this.getLista();
                    this.resetLote();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O ID já foi utilizado, tente outro ID!");
                }
            } else {
                keepDialogOpen();
                for (Iterator<String> it = erros.iterator(); it.hasNext();) {
                    displayErrorMessageToUser(it.next());
                }
            }
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void deleteLote() {
        try {
            ServiceLocator.getLoteService().delete(lote.getId());
            closeDialog();
            displayInfoMessageToUser("Lote excluído com sucesso!");
            this.getLista();
            this.resetLote();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public List<Modelo> getTodosModelosObj() {
        return todosModelosObj;
    }

    public void setTodosModelosObj(List<Modelo> todosModelosObj) {
        this.todosModelosObj = todosModelosObj;
    }
}
