package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InspecaoVisual.class)
public abstract class InspecaoVisual_ {

	public static volatile SingularAttribute<InspecaoVisual, Date> dataFim;
	public static volatile SingularAttribute<InspecaoVisual, Usuario> usuario;
	public static volatile SingularAttribute<InspecaoVisual, Date> dataInicio;
	public static volatile SingularAttribute<InspecaoVisual, Long> Id;
	public static volatile SingularAttribute<InspecaoVisual, Integer> qtdReprocesso;

}

