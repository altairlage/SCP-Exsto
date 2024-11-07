package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Lote;

@ManagedBean(name = "indexMB")
@ViewScoped
public class IndexMB extends AbstractMB implements Serializable {
    
    private List<Lote> lista;
    
    public void getLista() {
        
        HashMap<String, Object> criterion = new HashMap<String, Object>();
        criterion.put("status", Lote.STATUS_EM_PRODUCAO);
        
        this.lista = ServiceLocator.getLoteService().readByCriteria(criterion, "dataProgFim", true);
        
    }

    public List<Lote> getLotesEmProd(){
        if(this.lista == null){
            this.getLista();        
        }
        
        return this.lista;
    }
}
