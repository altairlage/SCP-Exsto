package scpexsto.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lote.class)
public abstract class Lote_ {

	public static volatile SingularAttribute<Lote, Date> dataProgFim;
	public static volatile SingularAttribute<Lote, Date> dataProgInicio;
	public static volatile ListAttribute<Lote, Produto> produtos;
	public static volatile SingularAttribute<Lote, String> status;
	public static volatile SingularAttribute<Lote, String> observacoes;
	public static volatile SingularAttribute<Lote, String> npedido;
	public static volatile SingularAttribute<Lote, Date> dataProd;
	public static volatile SingularAttribute<Lote, String> id;
	public static volatile SingularAttribute<Lote, String> nSerieFinal;
	public static volatile SingularAttribute<Lote, Date> dataRealInicio;
	public static volatile SingularAttribute<Lote, Date> dataCriacao;
	public static volatile SingularAttribute<Lote, String> nSerieInicial;
	public static volatile SingularAttribute<Lote, Integer> qtd;
	public static volatile SingularAttribute<Lote, Integer> qtdAtual;
	public static volatile SingularAttribute<Lote, Modelo> modelo;
	public static volatile SingularAttribute<Lote, Date> dataRealFim;

}

