package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.ClasseSolucao;

@ViewScoped
@ManagedBean(name = "classesolucaoMB")
public class ClasseSolucaoMB extends AbstractMB implements Serializable {

    private List<String> tipos;
    private ClasseSolucao classe;
    private List<ClasseSolucao> lista;

    @PostConstruct
    public void init() {
        tipos = ClasseSolucao.getTiposClasseSolucao();
    }

    public ClasseSolucao getClasse() {
        return classe;
    }

    public List<ClasseSolucao> getTodasClasses() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getClasseSolucaoService().readAll();
        }

        return lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getClasseSolucaoService().readAll();
    }

    public void resetClasse() {
        this.classe = new ClasseSolucao();
    }

    public void setClasse(ClasseSolucao e) {
        this.classe = e;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void updateClasse() {
        try {
            List<String> erros = ServiceLocator.getClasseSolucaoService().validaCampos(classe);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getClasseSolucaoService().validaClasseExistente(classe)) {
                    ServiceLocator.getClasseSolucaoService().update(classe);
                    closeDialog();
                    displayInfoMessageToUser("Atualizado com sucesso!");
                    this.getLista();
                    this.resetClasse();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! A classe já existe, tente outra descrição!");
                }
            } else {
                keepDialogOpen();
                for (Iterator<String> it = erros.iterator(); it.hasNext();) {
                    displayErrorMessageToUser(it.next());
                }
            }
        } catch (Exception e) {
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void createClasse() {
        try {
            List<String> erros = ServiceLocator.getClasseSolucaoService().validaCampos(classe);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getClasseSolucaoService().validaClasseExistente(classe)) {
                    ServiceLocator.getClasseSolucaoService().create(classe);
                    closeDialog();
                    displayInfoMessageToUser("Classe de solução criada com sucesso!");
                    this.getLista();
                    this.resetClasse();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! A classe de solução já existe, tente outra descrição!");
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

    public void deleteClasse() {
        try {
            ServiceLocator.getClasseSolucaoService().delete(classe.getId());
            closeDialog();
            displayInfoMessageToUser("Classe de solução excluída com sucesso!");
            this.getLista();
            this.resetClasse();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }
}