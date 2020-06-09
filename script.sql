drop database lab4_atividade2;
create database lab4_atividade2;

use lab4_atividade2;

CREATE TABLE cli_cliente(
	cli_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    cpf VARCHAR(11),
    endereco VARCHAR(255),
    CONSTRAINT pk_cli_cliente PRIMARY KEY(cli_id)
);

CREATE TABLE for_fornecedor(
	for_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    cnpj VARCHAR(14),
    CONSTRAINT pk_for_fornecedor PRIMARY KEY(for_id)
);

CREATE TABLE ite_item(
	ite_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    preco double,
    for_id BIGINT,
    CONSTRAINT pk_ite_item PRIMARY KEY (ite_id),
    CONSTRAINT fk_for_fornecedor FOREIGN KEY (for_id)
    REFERENCES for_fornecedor(for_id)
);

CREATE TABLE ped_pedido(
	ped_id BIGINT NOT NULL AUTO_INCREMENT,
    cli_id BIGINT,
    CONSTRAINT pk_ped_pedido PRIMARY KEY(ped_id),
    CONSTRAINT fk_cli_cliente FOREIGN KEY(cli_id)
    REFERENCES cli_cliente(cli_id)
);

CREATE TABLE item_pedido(
	ped_id BIGINT NOT NULL,
    ite_id BIGINT NOT NULL,
    CONSTRAINT pk_item_pedido PRIMARY KEY(ped_id, ite_id),
    CONSTRAINT fk_ped_pedido FOREIGN KEY (ped_id)
    REFERENCES ped_pedido(ped_id),
    CONSTRAINT fk_ite_item FOREIGN KEY (ite_id)
    REFERENCES ite_item(ite_id)
);
