package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Modelo;

@ManagedBean(name = "ModeloMB")
@ViewScoped
public class ModeloMB extends AbstractMB implements Serializable {

    private Modelo modelo;
    private List<Modelo> todosModelos;
    private List<String> tiposProduto;

    @PostConstruct
    public void init() {
        this.tiposProduto = new ArrayList<String>();
        this.tiposProduto.add("Automação");
        this.tiposProduto.add("Eletrônica");
        this.tiposProduto.add("Elétrica");
    }

    public List<Modelo> getTodosModelos() {
        if (this.todosModelos == null) {
            this.todosModelos = ServiceLocator.getModeloService().readAll();
        }

        return todosModelos;
    }

    public List<String> getTiposProduto() {
        return this.tiposProduto;
    }

    public void getLista() {
        this.todosModelos = ServiceLocator.getModeloService().readAll();
    }

    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void resetModelo() {
        this.modelo = new Modelo();
    }

    public void createModelo() {
        try {
            List<String> erros = ServiceLocator.getModeloService().validaCampos(this.modelo);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getModeloService().validaModeloExistente(this.modelo)) {
                    ServiceLocator.getModeloService().create(this.modelo);
                    closeDialog();
                    displayInfoMessageToUser("Modelo criado com sucesso!");
                    this.getLista();
                    this.resetModelo();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O modelo já foi utilizado, tente outro modelo!");
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

    public void updateModelo() {
        try {
            List<String> erros = ServiceLocator.getModeloService().validaCampos(this.modelo);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getModeloService().validaModeloExistente(this.modelo)) {
                    ServiceLocator.getModeloService().update(this.modelo);
                    closeDialog();
                    displayInfoMessageToUser("Modelo atualizado com sucesso!");
                    this.getLista();
                    this.resetModelo();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O modelo já foi utilizado, tente outro modelo!");
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

    public void deleteModelo() {
        try {
            ServiceLocator.getModeloService().delete(modelo.getId());
            closeDialog();
            displayInfoMessageToUser("Modelo excluído com sucesso!");
            this.getLista();
            this.resetModelo();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }
}
