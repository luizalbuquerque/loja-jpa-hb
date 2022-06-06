package com.mycompany.luciano.model.bo;

import com.mycompany.luciano.model.bo.Cliente;
import com.mycompany.luciano.model.bo.ItemVenda;
import com.mycompany.luciano.model.bo.Vendedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-04T23:38:05")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Cliente> cliente;
    public static volatile SingularAttribute<Venda, Vendedor> vendedor;
    public static volatile ListAttribute<Venda, ItemVenda> itensVenda;
    public static volatile SingularAttribute<Venda, Float> valTotal;
    public static volatile SingularAttribute<Venda, Integer> id;
    public static volatile SingularAttribute<Venda, CondicaoPagamento> condicaoPagamento;

}