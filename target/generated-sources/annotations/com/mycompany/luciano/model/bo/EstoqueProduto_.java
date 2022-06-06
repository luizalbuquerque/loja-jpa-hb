package com.mycompany.luciano.model.bo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-04T23:38:05")
@StaticMetamodel(EstoqueProduto.class)
public class EstoqueProduto_ { 

    public static volatile SingularAttribute<EstoqueProduto, Integer> estoqueInicial;
    public static volatile SingularAttribute<EstoqueProduto, Integer> estoqueAtual;
    public static volatile SingularAttribute<EstoqueProduto, Produto> produto;
    public static volatile SingularAttribute<EstoqueProduto, Integer> id;

}