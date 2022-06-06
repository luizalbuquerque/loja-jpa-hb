package com.mycompany.luciano.model.bo;

import com.mycompany.luciano.model.bo.Bairro;
import com.mycompany.luciano.model.bo.Cidade;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-04T23:38:05")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile SingularAttribute<Endereco, String> cepCep;
    public static volatile SingularAttribute<Endereco, Cidade> cidade;
    public static volatile SingularAttribute<Endereco, Integer> idEndereco;
    public static volatile SingularAttribute<Endereco, Bairro> bairro;
    public static volatile SingularAttribute<Endereco, String> logradouroCep;

}