package scpexsto.model;

import scpexsto.model.service.ClasseDefeitosService;
import scpexsto.model.service.ClasseSolucaoService;
import scpexsto.model.service.DefeitoService;
import scpexsto.model.service.EquipamentoMedicaoService;
import scpexsto.model.service.LoteService;
import scpexsto.model.service.ModeloService;
import scpexsto.model.service.PemService;
import scpexsto.model.service.ProdutoService;
import scpexsto.model.service.UsuarioService;

public class ServiceLocator {

    public static UsuarioService getUsuarioService() {
        return new UsuarioService();
    }

    public static DefeitoService getDefeitoService() {
        return new DefeitoService();
    }

    public static LoteService getLoteService() {
        return new LoteService();
    }

    public static ModeloService getModeloService() {
        return new ModeloService();
    }

    public static ProdutoService getProdutoService() {
        return new ProdutoService();
    }

    public static PemService getPemService() {
        return new PemService();
    }

    public static EquipamentoMedicaoService getEquipamentoMedicaoService() {
        return new EquipamentoMedicaoService();
    }
    
    public static ClasseDefeitosService getClasseDefeitosService(){
        return new ClasseDefeitosService();
    }
    
    public static ClasseSolucaoService getClasseSolucaoService(){
        return new ClasseSolucaoService();
    }
}
