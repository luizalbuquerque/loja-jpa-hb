package com.portuga.gymnasium.model.bo;

import com.portuga.gymnasium.model.bo.Produto;
import com.portuga.gymnasium.model.bo.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-08T23:06:38")
@StaticMetamodel(ItemVenda.class)
public class ItemVenda_ { 

    public static volatile SingularAttribute<ItemVenda, Float> qtdProduto;
    public static volatile SingularAttribute<ItemVenda, Venda> venda;
    public static volatile SingularAttribute<ItemVenda, Produto> produto;
    public static volatile SingularAttribute<ItemVenda, Integer> id;
    public static volatile SingularAttribute<ItemVenda, Float> valTotalItem;

}