package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.ClasseDefeitos;
import scpexsto.model.entity.ClasseSolucao;
import scpexsto.model.entity.Defeito;

@ManagedBean(name = "defeitoMB")
@ViewScoped
public class DefeitoMB extends AbstractMB implements Serializable {

    private Defeito defeito;
    private List<Defeito> lista;
    private List<ClasseDefeitos> todasClassesDefObj;
    private List<ClasseSolucao> todasClassesSolObj;

    @PostConstruct
    public void init() {
        this.todasClassesDefObj = ServiceLocator.getClasseDefeitosService().readAll();
        this.todasClassesSolObj = ServiceLocator.getClasseSolucaoService().readAll();
    }

    public List<ClasseDefeitos> getTodasClassesDefObj() {
        return todasClassesDefObj;
    }

    public void setTodasClassesDefObj(List<ClasseDefeitos> todasClassesObj) {
        this.todasClassesDefObj = todasClassesObj;
    }
    
    public List<ClasseSolucao> getTodasClassesSolObj() {
        return todasClassesSolObj;
    }

    public void setTodasClassesSolObj(List<ClasseSolucao> todasClassesSol) {
        this.todasClassesSolObj = todasClassesSol;
    }

    public Defeito getDefeito() {
        return this.defeito;
    }

    public void setDefeito(Defeito defeito) {
        this.defeito = defeito;
    }

    public List<Defeito> getTodosDefeitos() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getDefeitoService().readAll();
        }
        return this.lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getDefeitoService().readAll();
    }

    public void setTodosDefeitos(List<Defeito> todosDefeitos) {
        this.lista = todosDefeitos;
    }

    public void reset() {
        this.defeito = new Defeito();
    }

    public String voltar() {
        this.defeito = new Defeito();

        return "/protected/gerencia/defeito/defeito.xhtml?faces-redirect=true";
    }

    public void create() {
        try {
            List<String> erros = ServiceLocator.getDefeitoService().validaCampos(this.defeito);

            if (!(erros.size() > 0)) {
                ServiceLocator.getDefeitoService().create(this.defeito);
                closeDialog();
                displayInfoMessageToUser("Defeito criado com sucesso!");
                this.getLista();
                this.reset();

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

    public void update() {
        try {
            List<String> erros = ServiceLocator.getDefeitoService().validaCampos(this.defeito);

            if (!(erros.size() > 0)) {
                ServiceLocator.getDefeitoService().update(this.defeito);
                closeDialog();
                displayInfoMessageToUser("Defeito atualizado com sucesso!");
                this.getLista();
                this.reset();
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

    public void delete() {
        try {
            ServiceLocator.getDefeitoService().delete(this.defeito.getId());
            closeDialog();
            displayInfoMessageToUser("Defeito excluído com sucesso!");
            this.getLista();
            this.reset();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }
}
