package scpexsto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.ClasseSolucao;

@FacesConverter(forClass = ClasseSolucao.class)
public class ClasseSolucaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        ClasseSolucao retorno = null;

        try {
            if (string != null) {
                retorno = ServiceLocator.getClasseSolucaoService().readById(new Long(string));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            String retorno = String.valueOf(((ClasseSolucao) o).getId());
            return retorno;
        }
        return null;
    }

}
