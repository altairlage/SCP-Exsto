package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TesteEletrico.class)
public abstract class TesteEletrico_ {

	public static volatile SingularAttribute<TesteEletrico, Long> id;
	public static volatile SingularAttribute<TesteEletrico, Date> dataFim;
	public static volatile SingularAttribute<TesteEletrico, String> voltagem;
	public static volatile SingularAttribute<TesteEletrico, Usuario> usuario;
	public static volatile SingularAttribute<TesteEletrico, Date> dataInicio;
	public static volatile ListAttribute<TesteEletrico, EquipamentoMedicao> equipamentos;

}

