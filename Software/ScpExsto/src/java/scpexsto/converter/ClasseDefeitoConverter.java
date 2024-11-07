package scpexsto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.ClasseDefeitos;

@FacesConverter(forClass = ClasseDefeitos.class)
public class ClasseDefeitoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        ClasseDefeitos retorno = null;

        try {
            if (string != null) {
                retorno = ServiceLocator.getClasseDefeitosService().readById(new Long(string));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            String retorno = String.valueOf(((ClasseDefeitos) o).getId());
            return retorno;
        }
        return null;
    }

}
