package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.EquipamentoMedicao;

@ViewScoped
@ManagedBean(name = "equipmedicaoMB")
public class EquipamentoMedicaoMB extends AbstractMB implements Serializable {
    private List<String> tipos;

    private EquipamentoMedicao equip;
    private List<EquipamentoMedicao> lista;

    @PostConstruct
    public void init() {
        tipos = EquipamentoMedicao.getTiposEquipamento();
    }

    public EquipamentoMedicao getEquip() {
        return equip;
    }

    public List<EquipamentoMedicao> getTodosEquips() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getEquipamentoMedicaoService().readAll();
        }

        return lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getEquipamentoMedicaoService().readAll();
    }

    public void ressetEquip() {
        this.equip = new EquipamentoMedicao();
    }

    public void setEquip(EquipamentoMedicao e) {
        this.equip = e;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void updateEquip() {
        try {
            List<String> erros = ServiceLocator.getEquipamentoMedicaoService().validaCampos(equip);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getEquipamentoMedicaoService().validaEquipExistente(equip)) {
                    ServiceLocator.getEquipamentoMedicaoService().update(equip);
                    closeDialog();
                    displayInfoMessageToUser("Atualizado com sucesso!");
                    this.getLista();
                    this.ressetEquip();
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
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void createEquip() {
        try {
            this.equip.setDataCriacao(new Date(System.currentTimeMillis()));
            List<String> erros = ServiceLocator.getEquipamentoMedicaoService().validaCampos(equip);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getEquipamentoMedicaoService().validaEquipExistente(equip)) {
                    ServiceLocator.getEquipamentoMedicaoService().create(equip);
                    closeDialog();
                    displayInfoMessageToUser("Equipamento criado com sucesso!");
                    this.getLista();
                    this.ressetEquip();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O equipamento já existe, tente outro patrimonio!");
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

    public void deleteEquip() {
        try {
            ServiceLocator.getEquipamentoMedicaoService().delete(equip.getPatrimonio());
            closeDialog();
            displayInfoMessageToUser("EquipamentoMedicao excluído com sucesso!");
            this.getLista();
            this.ressetEquip();

//            ServiceLocator.getEquipamentoMedicaoService().update(usuario);
//            closeDialog();
//            displayInfoMessageToUser("Atualizado com sucesso!");
//            lista = this.getTodosEquipamentoMedicaos();
//            ressetEquipamentoMedicao();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }
}
