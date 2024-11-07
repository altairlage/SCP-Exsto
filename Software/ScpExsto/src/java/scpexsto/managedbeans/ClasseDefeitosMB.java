package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.ClasseDefeitos;

@ViewScoped
@ManagedBean(name = "classedefeitosMB")
public class ClasseDefeitosMB extends AbstractMB implements Serializable {

    private List<String> tipos;
    private ClasseDefeitos classe;
    private List<ClasseDefeitos> lista;

    @PostConstruct
    public void init() {
        tipos = ClasseDefeitos.getTiposClasseDefeitos();
    }

    public ClasseDefeitos getClasse() {
        return classe;
    }

    public List<ClasseDefeitos> getTodasClasses() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getClasseDefeitosService().readAll();
        }

        return lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getClasseDefeitosService().readAll();
    }

    public void resetClasse() {
        this.classe = new ClasseDefeitos();
    }

    public void setClasse(ClasseDefeitos e) {
        this.classe = e;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void updateClasse() {
        try {
            List<String> erros = ServiceLocator.getClasseDefeitosService().validaCampos(classe);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getClasseDefeitosService().validaClasseExistente(classe)) {
                    ServiceLocator.getClasseDefeitosService().update(classe);
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
            List<String> erros = ServiceLocator.getClasseDefeitosService().validaCampos(classe);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getClasseDefeitosService().validaClasseExistente(classe)) {
                    ServiceLocator.getClasseDefeitosService().create(classe);
                    closeDialog();
                    displayInfoMessageToUser("Classe de defeitos criada com sucesso!");
                    this.getLista();
                    this.resetClasse();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! A classe de defeitos já existe, tente outra descrição!");
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
            ServiceLocator.getClasseDefeitosService().delete(classe.getId());
            closeDialog();
            displayInfoMessageToUser("Classe de defeitos excluída com sucesso!");
            this.getLista();
            this.resetClasse();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }
}
