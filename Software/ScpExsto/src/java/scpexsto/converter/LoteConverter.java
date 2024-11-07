package scpexsto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Lote;

@FacesConverter(forClass = Lote.class)
public class LoteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Lote retorno = null;

        try {
            if (string != null) {
                retorno = ServiceLocator.getLoteService().readById(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            String retorno = ((Lote) o).getId();
            return retorno;
        }
        return null;
    }

}
