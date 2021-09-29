/*-----------------------------------------------------------------------------------------------
 *
 * ANO: 2021
 *
 * ORIGEM: IFSP-PTB ADS
 *
 * PROPÓSITO: DEFINIÇÃO DE BANCO DE DADOS PARA O SISTEMA RELATÓRIO ACESSÍVEL (RA)
 *
 * AUTOR: GABRIEL MARTINS DOS SANTOS
 *
*-----------------------------------------------------------------------------------------------*/

create database radb;
use radb;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL PARA: TRA_USUARIO
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_usuario (
    id bigint,
    te_email varchar(100) not null,
    nm_nome varchar(100)
);

-- PK
alter table tra_usuario add constraint pk_tra_paciente primary key (id);
alter table tra_usuario modify id bigint auto_increment;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL PARA: TRA_EVENTO
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_evento (
    id bigint,
    dh_evento timestamp,
    ds_evento varchar(100) not null,
    nu_pa_diastolica integer,
    nu_pa_sistolica integer,
    ds_sintoma varchar(100),
    ds_medicamento varchar(100),
    id_exame bigint
);

-- PK
alter table tra_evento add constraint pk_tra_evento primary key (id);
alter table tra_evento modify id bigint auto_increment;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL PARA: TRA_EXAME
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_exame (
    id bigint,
    dh_exame timestamp not null,
    id_externo varchar(100),
    nu_intervalo_afericao integer not null,
    id_usuario bigint,
    id_servico_saude bigint,
    cd_status varchar(3)
);

-- PK
alter table tra_exame add constraint pk_tra_exame primary key (id);
alter table tra_exame modify id bigint auto_increment;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL PARA: TRA_STATUS_EXAME
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_status_exame (
    cd_status varchar(3),
    ds_descricao varchar(100) not null,
    in_permite_escrita boolean not null
);

-- PK
alter table tra_status_exame add constraint pk_tra_status_exame primary key (cd_status);

-- CONFIG DATA
insert into radb.tra_status_exame (cd_status, ds_descricao, in_permite_escrita) values ('AGE', 'AGENDADO', 0);
insert into radb.tra_status_exame (cd_status, ds_descricao, in_permite_escrita) values ('AND', 'ANDAMENTO', 1);
insert into radb.tra_status_exame (cd_status, ds_descricao, in_permite_escrita) values ('ENC', 'ENCERRADO', 0);

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL PARA: TRA_SERVICO_SAUDE
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_servico_saude (
    id bigint,
    nm_completo varchar(255) not null,
    nm_curto varchar(100),
    nu_cnpj varchar(14) not null,
    te_cep varchar(8) not null,
    nm_logradouro varchar(100),
    nm_bairro varchar(100),
    nm_cidade varchar(100),
    nm_estado varchar(50),
    nu_numero_lote int not null,
    te_link_maps varchar(100)
);

-- PK
alter table tra_servico_saude add constraint pk_tra_servico_saude primary key (id);
alter table tra_servico_saude modify id bigint auto_increment;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL PARA: FK CONSTRAINTS
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

-- EVENTO [N] | EXAME [1]
alter table tra_evento
add constraint fk_tra_evento_exame
foreign key (id_exame)
references tra_exame (id);

alter table tra_evento modify id_exame bigint not null;

-- EXAME [N] | USUARIO [1]
alter table tra_exame
add constraint fk_tra_exame_id_usuario
foreign key (id_usuario)
references tra_usuario (id);

alter table tra_exame modify id_usuario bigint not null;

-- EXAME [N] | STATUS [1]
alter table tra_exame
add constraint fk_tra_exame_cd_status
foreign key (cd_status)
references tra_status_exame (cd_status);

alter table tra_exame modify cd_status varchar(3) not null;

-- EXAME [N] | SERVIÇO SAUDE [1]
alter table tra_exame
add constraint fk_tra_exame_id_servico_saude
foreign key (id_servico_saude)
references tra_servico_saude (id);

alter table tra_exame modify id_servico_saude bigint not null;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- TEST DATA
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

insert into tra_usuario (te_email, nm_nome) values('daiana@gmail.com', 'daiana');

insert into tra_servico_saude (
    nm_completo,
    nm_curto,
    nu_cnpj,
    te_cep,
    nm_logradouro,
    nm_bairro,
    nm_cidade,
    nm_estado,
    nu_numero_lote,
    te_link_maps
) values (
    'Clínica HeartCare Serviços de Assistência à Saúde LTDA',
    'HeartCare',
    '09127823563478',
    '01001000',
    'Praça da Sé',
    'Sé',
    'São Paulo',
    'SP',
    223,
    'https://goo.gl/maps/7pWVsXTs7o9QvpGR9'
);

insert into tra_servico_saude (
    nm_completo,
    nm_curto,
    nu_cnpj,
    te_cep,
    nm_logradouro,
    nm_bairro,
    nm_cidade,
    nm_estado,
    nu_numero_lote,
    te_link_maps
) values (
    'Hospitais Associados Saúde Saudável do Brasil LTDA',
    'Hospital Saúde Saudável',
    '56235623784578',
    '06145088',
    'Rua Apóstolo Pedro',
    'Conceição',
    'Osasco',
    'SP',
    444,
    'https://goo.gl/maps/s1dy2rSduDnmDRgS9'
);

insert into tra_servico_saude (
    nm_completo,
    nm_curto,
    nu_cnpj,
    te_cep,
    nm_logradouro,
    nm_bairro,
    nm_cidade,
    nm_estado,
    nu_numero_lote,
    te_link_maps
) values (
    'Clínica de Serviços Médicos Dr. Kleber LTDA',
    'Clínica Dr. Kleber',
    '12341234762376',
    '02405030',
    'Rua Dom Henrique Mourão',
    'Santana',
    'São Paulo',
    'SP',
    345,
    'https://goo.gl/maps/zXbLsGWVnsBP2iyUA'
);