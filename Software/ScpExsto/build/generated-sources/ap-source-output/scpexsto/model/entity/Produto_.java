package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, Integer> qtdTesteEletrico;
	public static volatile SingularAttribute<Produto, Integer> status;
	public static volatile ListAttribute<Produto, Retrabalho> retrabalhos;
	public static volatile SingularAttribute<Produto, String> motivoRefugo;
	public static volatile SingularAttribute<Produto, String> nserie;
	public static volatile ListAttribute<Produto, Pem> pems;
	public static volatile SingularAttribute<Produto, IntegracaoFinal> integracaoFinal;
	public static volatile SingularAttribute<Produto, Lote> lote;
	public static volatile ListAttribute<Produto, TesteEletrico> testesEletricos;
	public static volatile SingularAttribute<Produto, String> etapa;
	public static volatile SingularAttribute<Produto, InspecaoFinal> inspecaoFinal;
	public static volatile SingularAttribute<Produto, Date> dataCriacao;
	public static volatile SingularAttribute<Produto, Boolean> refugo;
	public static volatile SingularAttribute<Produto, InspecaoVisual> inspecaoVisual;

}

