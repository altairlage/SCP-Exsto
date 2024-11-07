package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Retrabalho.class)
public abstract class Retrabalho_ {

	public static volatile SingularAttribute<Retrabalho, Long> id;
	public static volatile SingularAttribute<Retrabalho, Date> dataFim;
	public static volatile SingularAttribute<Retrabalho, Usuario> usuario;
	public static volatile SingularAttribute<Retrabalho, Date> dataInicio;
	public static volatile ListAttribute<Retrabalho, Defeito> defeitos;
	public static volatile SingularAttribute<Retrabalho, Boolean> refugou;
	public static volatile SingularAttribute<Retrabalho, String> origem;

}

