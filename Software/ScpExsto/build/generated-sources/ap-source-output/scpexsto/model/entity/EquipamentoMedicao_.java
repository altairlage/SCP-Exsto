package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EquipamentoMedicao.class)
public abstract class EquipamentoMedicao_ {

	public static volatile ListAttribute<EquipamentoMedicao, TesteEletrico> testeEletricos;
	public static volatile SingularAttribute<EquipamentoMedicao, String> setor;
	public static volatile SingularAttribute<EquipamentoMedicao, String> patrimonio;
	public static volatile SingularAttribute<EquipamentoMedicao, String> status;
	public static volatile SingularAttribute<EquipamentoMedicao, String> tipo;
	public static volatile SingularAttribute<EquipamentoMedicao, Date> dataCriacao;
	public static volatile SingularAttribute<EquipamentoMedicao, String> marca;
	public static volatile SingularAttribute<EquipamentoMedicao, String> modelo;
	public static volatile SingularAttribute<EquipamentoMedicao, Date> dataVencimento;

}

