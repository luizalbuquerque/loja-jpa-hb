package com.mycompany.luciano.model.bo;

import com.mycompany.luciano.model.bo.Fornecedor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-25T19:27:59")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Float> desconto;
    public static volatile SingularAttribute<Compra, Date> dtHr;
    public static volatile SingularAttribute<Compra, Float> valorTotal;
    public static volatile SingularAttribute<Compra, String> serieNF;
    public static volatile SingularAttribute<Compra, Integer> numNF;
    public static volatile SingularAttribute<Compra, Integer> id;
    public static volatile SingularAttribute<Compra, Fornecedor> fornecedor;
    public static volatile SingularAttribute<Compra, CondicaoPagamento> condicaoPagamento;

}