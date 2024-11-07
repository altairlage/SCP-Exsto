package scpexsto.managedbeans;

import com.sun.faces.context.flash.ELFlash;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Modelo;
import scpexsto.model.entity.Pem;

@ManagedBean(name = "pemMB")
@ViewScoped
public class PemMB extends AbstractMB implements Serializable {

    private Pem pem;
    private List<Pem> lista;
    private List<String> etapasPem;
    private String modeloSelecionado;
    private List<Modelo> todosModelosObj;

    @PostConstruct
    public void init() {
        this.etapasPem = Pem.getEstapasPem();

        this.todosModelosObj = ServiceLocator.getModeloService().readAll();
    }

    public List<Modelo> getTodosModelosObj() {
        return todosModelosObj;
    }

    public void setTodosModelosObj(List<Modelo> todosModelosObj) {
        this.todosModelosObj = todosModelosObj;
    }

    public Pem getPem() {
        return this.pem;
    }

    public void setPem(Pem pem) {
        this.pem = pem;
    }

    public List<Pem> getTodosPems() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getPemService().readAll();
        }

        return this.lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getPemService().readAll();
    }

    public void setTodosPems(List<Pem> todosPems) {
        this.lista = todosPems;
    }

    public void resetPem() {
        this.pem = new Pem();
    }

    public List<String> getEtapasPem() {
        return etapasPem;
    }

    public void setEtapasPem(List<String> etapasPem) {
        this.etapasPem = etapasPem;
    }

    public String setPemSelecionadoADetalhar() {
        Pem p = null;
//        Pem p = ServiceLocator.getPemService().getPemByNSerieWithJoinQueryPems(this.pem.getNserie());

        ELFlash.getFlash().put("pemSelecionado", this.pem);

        if (p != null) {
//            ELFlash.getFlash().put("listaPems", p.getPems());
        }
        return "/protected/pem/pemDetail.xhtml";
    }

    public Pem getPemADetalhar() {
        if (this.pem == null) {
            this.pem = (Pem) ELFlash.getFlash().get("pemSelecionado");
            this.lista = (List<Pem>) ELFlash.getFlash().get("listaPems");
        }

        return this.pem;
    }

    public List<Pem> getListaPemsDetail() {
        if (this.lista == null) {
            this.lista = (List<Pem>) ELFlash.getFlash().get("listaPems");
        }

        return this.lista;
    }

    public String voltar() {
        this.pem = new Pem();

        return "/protected/lider/pem/pem.xhtml?faces-redirect=true";
    }

    public void createPem() {
        try {
//            this.pem.setDataCriacao(new Date(System.currentTimeMillis()));

            List<String> erros = ServiceLocator.getPemService().validaCampos(this.pem);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getPemService().validaPemExistente(this.pem)) {
                    ServiceLocator.getPemService().create(this.pem);
                    closeDialog();
                    displayInfoMessageToUser("Pem criado com sucesso!");
                    this.getLista();
                    this.resetPem();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O pem já existe, tente outro número de série!");
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

    public void updatePem() {
        try {
            List<String> erros = ServiceLocator.getPemService().validaCampos(this.pem);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getPemService().validaPemExistente(this.pem)) {
                    ServiceLocator.getPemService().update(this.pem);
                    closeDialog();
                    displayInfoMessageToUser("Pem atualizado com sucesso!");
                    this.getLista();
                    this.resetPem();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O pem já existe, tente outro número de série!");
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

    public void deletePem() {
        try {
            ServiceLocator.getPemService().delete(pem.getNserie());
            closeDialog();
            displayInfoMessageToUser("Pem excluído com sucesso!");
            this.getLista();
            this.resetPem();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }
}
