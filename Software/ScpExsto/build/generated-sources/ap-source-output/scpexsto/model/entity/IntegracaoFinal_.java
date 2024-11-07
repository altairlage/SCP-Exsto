package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IntegracaoFinal.class)
public abstract class IntegracaoFinal_ {

	public static volatile SingularAttribute<IntegracaoFinal, Long> id;
	public static volatile SingularAttribute<IntegracaoFinal, Date> dataFim;
	public static volatile SingularAttribute<IntegracaoFinal, Usuario> usuario;
	public static volatile SingularAttribute<IntegracaoFinal, Date> dataInicio;
	public static volatile SingularAttribute<IntegracaoFinal, Integer> qtdReprocesso;

}

