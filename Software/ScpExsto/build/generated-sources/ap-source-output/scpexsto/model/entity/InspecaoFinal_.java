package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InspecaoFinal.class)
public abstract class InspecaoFinal_ {

	public static volatile SingularAttribute<InspecaoFinal, Long> id;
	public static volatile SingularAttribute<InspecaoFinal, Date> dataFim;
	public static volatile SingularAttribute<InspecaoFinal, Usuario> usuario;
	public static volatile SingularAttribute<InspecaoFinal, Date> dataInicio;
	public static volatile SingularAttribute<InspecaoFinal, Integer> qtdReprocesso;

}

