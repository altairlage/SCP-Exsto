package scpexsto.managedbeans;

import com.sun.faces.context.flash.ELFlash;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Lote;
import scpexsto.model.entity.Pem;
import scpexsto.model.entity.Produto;

@ManagedBean(name = "ProdutoMB")
@ViewScoped
public class ProdutoMB extends AbstractMB implements Serializable {

    private Produto produto;
    private List<Produto> lista;
    private List<String> etapasProduto;
    private String modeloSelecionado;
    private String loteSelecionado;
    private List<Lote> todosLotes;
    private List<Pem> listaPems;

    @PostConstruct
    public void init() {
        this.etapasProduto = Produto.getEstapasProduto();
        this.todosLotes = this.getTodosLotes();
    }

    public List<Lote> getTodosLotes() {
        if (this.todosLotes == null) {
            this.todosLotes = ServiceLocator.getLoteService().readAll();
        }
        return this.todosLotes;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getTodosProdutos() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getProdutoService().readAll();
        }

        return this.lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getProdutoService().readAll();
    }

    public void setTodosProdutos(List<Produto> todosProdutos) {
        this.lista = todosProdutos;
    }

    public void resetProduto() {
        this.produto = new Produto();
    }

    public List<String> getEtapasProduto() {
        return etapasProduto;
    }

    public void setEtapasProduto(List<String> etapasProduto) {
        this.etapasProduto = etapasProduto;
    }

    public String setProdutoSelecionadoADetalhar() {
        Produto p = ServiceLocator.getProdutoService().getProdutoByNSerieWithJoinQueryPems(this.produto.getNserie());

        ELFlash.getFlash().put("produtoSelecionado", this.produto);

        if (p != null) {
            ELFlash.getFlash().put("listaPems", p.getPems());
        }
        return "/protected/lider/produto/produtoDetail.xhtml";
    }

    public Produto getProdutoADetalhar() {
        if (this.produto == null) {
            this.produto = (Produto) ELFlash.getFlash().get("produtoSelecionado");
            this.listaPems = (List<Pem>) ELFlash.getFlash().get("listaPems");
        }

        return this.produto;
    }

    public List<Pem> getListaPemsDetail() {
        if (this.listaPems == null) {
            this.listaPems = (List<Pem>) ELFlash.getFlash().get("listaPems");
        }

        return this.listaPems;
    }

    public String voltar() {
        this.produto = new Produto();

        return "/protected/lider/produto/produto.xhtml?faces-redirect=true";
    }

    public void createProduto() {
        try {
            this.produto.setDataCriacao(new Date(System.currentTimeMillis()));
            this.produto.setEtapa(Produto.NAO_INICIADO);

            List<String> erros = ServiceLocator.getProdutoService().validaCampos(this.produto);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getProdutoService().validaProdutoExistente(this.produto)) {
                    ServiceLocator.getProdutoService().create(this.produto);
                    closeDialog();
                    displayInfoMessageToUser("Produto criado com sucesso!");
                    this.getLista();
                    this.resetProduto();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O produto já existe, tente outro número de série!");
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

    public void updateProduto() {
        try {
            List<String> erros = ServiceLocator.getProdutoService().validaCampos(this.produto);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getProdutoService().validaProdutoExistente(this.produto)) {
                    ServiceLocator.getProdutoService().update(this.produto);
                    closeDialog();
                    displayInfoMessageToUser("Produto atualizado com sucesso!");
                    this.getLista();
                    this.resetProduto();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O produto já existe, tente outro número de série!");
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

    public void deleteProduto() {
        try {
            ServiceLocator.getProdutoService().delete(produto.getNserie());
            closeDialog();
            displayInfoMessageToUser("Produto excluído com sucesso!");
            this.getLista();
            this.resetProduto();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public String getLoteSelecionado() {
        return loteSelecionado;
    }

    public void setLoteSelecionado(String loteSelecionado) {
        this.loteSelecionado = loteSelecionado;
    }
}
