package scpexsto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Modelo;

@FacesConverter(forClass = Modelo.class)
public class ModeloConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Modelo retorno = null;

        try {
            if (string != null) {
                Modelo modelo = new Modelo();
                retorno = ServiceLocator.getModeloService().readById(new Long(string));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return ((Modelo) o).getId().toString();
        }
        return null;
    }

}
