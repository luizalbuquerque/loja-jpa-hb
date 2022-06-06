create database academia;
use academia;

create table BAIRRO
(
    idBairro        int auto_increment
        primary key,
    descricaoBairro varchar(40) null
);

create table CIDADE
(
    idCidade        int auto_increment
        primary key,
    descricaoCidade varchar(40) null,
    ufCidade        varchar(20) null
);

create table CONDICAOPAGAMENTO
(
    id        int auto_increment
        primary key,
    descricao varchar(40) null,
    diasAte   int         null,
    diasEntre int         null
);

create table ENDERECO
(
    idEndereco    int auto_increment
        primary key,
    cepCep        varchar(10) null,
    logradouroCep varchar(40) null,
    idBairro      int         null,
    idCidade      int         null,
    constraint ENDERECO_ibfk_1
        foreign key (idBairro) references BAIRRO (idBairro),
    constraint ENDERECO_ibfk_2
        foreign key (idCidade) references CIDADE (idCidade)
);

create index idBairro
    on ENDERECO (idBairro);

create index idCidade
    on ENDERECO (idCidade);

create table MARCA
(
    id        int auto_increment
        primary key,
    descricao varchar(20) null
);

create table PESSOA
(
    id             int auto_increment
        primary key,
    nome           varchar(40) null,
    email          varchar(40) null,
    compleEndereco varchar(40) null,
    idEndereco     int         null,
    cpf            varchar(20) null,
    rg             varchar(20) null,
    dataNasc       varchar(20) null,
    fone1          varchar(20) null,
    fone2          varchar(20) null,
    tipo           varchar(1)  null,
    comVenda       int         null,
    comRecebimento int         null,
    constraint PESSOA_ibfk_1
        foreign key (idEndereco) references ENDERECO (idEndereco)
);

create index idEndereco
    on PESSOA (idEndereco);

create table TAMANHO
(
    id        int auto_increment
        primary key,
    descricao varchar(20) null
);

create table TIPOPRODUTO
(
    id        int auto_increment
        primary key,
    descricao varchar(20) null
);

create table PRODUTO
(
    id             int auto_increment
        primary key,
    descricao      varchar(40) null,
    val            int         null,
    idMarca        int         null,
    idTipoProduto  int         null,
    idTamanho      int         null,
    estoqueInicial int         null,
    constraint PRODUTO_ibfk_1
        foreign key (idMarca) references MARCA (id),
    constraint PRODUTO_ibfk_2
        foreign key (idTipoProduto) references TIPOPRODUTO (id),
    constraint PRODUTO_ibfk_3
        foreign key (idTamanho) references TAMANHO (id)
);

create table ESTOQUEPRODUTO
(
    id             int auto_increment
        primary key,
    idProduto      int null,
    estoqueInicial int null,
    estoqueAtual   int null,
    constraint ESTOQUEPRODUTO_ibfk_1
        foreign key (idProduto) references PRODUTO (id)
);

create index idProduto
    on ESTOQUEPRODUTO (idProduto);

create table ITEMVENDA
(
    id           int auto_increment
        primary key,
    idVenda      int   null,
    idProduto    int   null,
    valTotalItem float null,
    qtdProduto   int   null,
    constraint ITEMVENDA_ibfk_1
        foreign key (idProduto) references PRODUTO (id)
);

create index idProduto
    on ITEMVENDA (idProduto);

create index idVenda
    on ITEMVENDA (idVenda);

create index idMarca
    on PRODUTO (idMarca);

create index idTamanho
    on PRODUTO (idTamanho);

create index idTipoProduto
    on PRODUTO (idTipoProduto);

create table VENDA
(
    id                  int auto_increment
        primary key,
    valTotal            double null,
    idcliente           int    null,
    idcondicaoPagamento int    null,
    idVendedor          int    null,
    idItemVenda         int    null,
    constraint VENDA_ibfk_1
        foreign key (idcliente) references PESSOA (id),
    constraint VENDA_ibfk_2
        foreign key (idcondicaoPagamento) references CONDICAOPAGAMENTO (id),
    constraint VENDA_ibfk_3
        foreign key (idVendedor) references PESSOA (id),
    constraint VENDA_ibfk_4
        foreign key (idItemVenda) references ITEMVENDA (id)
);

alter table ITEMVENDA
    add constraint ITEMVENDA_ibfk_2
        foreign key (idVenda) references VENDA (id);

create index idItemVenda
    on VENDA (idItemVenda);

create index idVendedor
    on VENDA (idVendedor);

create index idcliente
    on VENDA (idcliente);

create index idcondicaoPagamento
    on VENDA (idcondicaoPagamento);


create definer = root@localhost trigger insere_estoque
    after insert
    on PRODUTO
    for each row
BEGIN
    DECLARE v_codigo int;
    DECLARE v_estoque int;
    SET v_codigo := (select max(id) from PRODUTO);
    SET v_estoque := (select estoqueInicial from PRODUTO WHERE id = (select max(id) from PRODUTO));
    INSERT INTO ESTOQUEPRODUTO (idProduto, estoqueInicial, estoqueAtual) values (v_codigo, v_estoque, v_estoque);
END;