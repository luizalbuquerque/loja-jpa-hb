package com.mycompany.luciano.model.bo;

import com.mycompany.luciano.model.bo.CaracteristicaProduto;
import com.mycompany.luciano.model.bo.Compra;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-25T19:27:59")
@StaticMetamodel(ItemCompra.class)
public class ItemCompra_ { 

    public static volatile SingularAttribute<ItemCompra, Compra> compra;
    public static volatile SingularAttribute<ItemCompra, Float> qtdProduto;
    public static volatile SingularAttribute<ItemCompra, Float> valUnitario;
    public static volatile SingularAttribute<ItemCompra, CaracteristicaProduto> caracteristicaProduto;
    public static volatile SingularAttribute<ItemCompra, Integer> id;

}