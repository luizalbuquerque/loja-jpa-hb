package com.portuga.gymnasium.model.bo;

import com.portuga.gymnasium.model.bo.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:59:26")
@StaticMetamodel(EstoqueProduto.class)
public class EstoqueProduto_ { 

    public static volatile SingularAttribute<EstoqueProduto, Integer> estoqueInicial;
    public static volatile SingularAttribute<EstoqueProduto, Integer> estoqueAtual;
    public static volatile SingularAttribute<EstoqueProduto, Produto> produto;
    public static volatile SingularAttribute<EstoqueProduto, Integer> id;

}