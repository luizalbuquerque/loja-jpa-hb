package com.mycompany.luciano.model.bo;

import com.mycompany.luciano.model.bo.Marca;
import com.mycompany.luciano.model.bo.Tamanho;
import com.mycompany.luciano.model.bo.TipoProduto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-04T23:38:05")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, Float> val;
    public static volatile SingularAttribute<Produto, Marca> marca;
    public static volatile SingularAttribute<Produto, Tamanho> tamanho;
    public static volatile SingularAttribute<Produto, Integer> estoqueInicial;
    public static volatile SingularAttribute<Produto, Integer> id;
    public static volatile SingularAttribute<Produto, TipoProduto> tipoProduto;
    public static volatile SingularAttribute<Produto, String> descricao;

}