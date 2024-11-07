package scpexsto.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pem.class)
public abstract class Pem_ {

	public static volatile SingularAttribute<Pem, Produto> produto;
	public static volatile SingularAttribute<Pem, Integer> qtdTesteEletrico;
	public static volatile ListAttribute<Pem, TesteEletrico> testesEletricos;
	public static volatile SingularAttribute<Pem, Integer> status;
	public static volatile ListAttribute<Pem, Retrabalho> retrabalhos;
	public static volatile SingularAttribute<Pem, InspecaoFinal> inspecaoFinal;
	public static volatile SingularAttribute<Pem, String> etapa;
	public static volatile SingularAttribute<Pem, String> motivoRefugo;
	public static volatile SingularAttribute<Pem, Boolean> refugo;
	public static volatile SingularAttribute<Pem, String> nserie;
	public static volatile SingularAttribute<Pem, Modelo> modelo;
	public static volatile SingularAttribute<Pem, InspecaoVisual> inspecaoVisual;

}

