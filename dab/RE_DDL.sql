/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL FOR: TRA_USUARIO
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_usuario (
    id bigint,
    te_email varchar(100),
    nm_nome varchar(100)
);

-- PK
alter table tra_usuario add constraint pk_tra_paciente primary key (id);
alter table tra_usuario modify id bigint auto_increment;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL FOR: TRA_EVENTO
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_evento (
    id bigint,
    dh_evento timestamp,
    ds_evento varchar(100),
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
-- DDL FOR: TRA_EXAME
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_exame (
    id bigint,
    dh_exame timestamp,
    id_externo varchar(100),
    nu_intervalo_afericao integer,
    id_usuario bigint,
    id_servico_saude bigint,
    cd_status varchar(3)
);

-- PK
alter table tra_exame add constraint pk_tra_exame primary key (id);
alter table tra_exame modify id bigint auto_increment;

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL FOR: TRA_STATUS_EXAME
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

create table tra_status_exame (
    cd_status varchar(3),
    ds_descricao varchar(100),
    in_permite_escrita boolean
);

-- PK
alter table tra_status_exame add constraint pk_tra_status_exame primary key (cd_status);

-- DATA
insert into radb.tra_status_exame (cd_status, ds_descricao, in_permite_escrita) values ('AGE', 'AGENDADO', 0);
insert into radb.tra_status_exame (cd_status, ds_descricao, in_permite_escrita) values ('AND', 'ANDAMENTO', 1);
insert into radb.tra_status_exame (cd_status, ds_descricao, in_permite_escrita) values ('ENC', 'ENCERRADO', 0);

/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/
-- DDL FOR: FK CONSTRAINS
/*-----------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------*/

-- EVENTO [N] | EXAME [1]
alter table tra_evento
add constraint fk_tra_evento_exame
foreign key (id_exame)
references tra_exame (id);

-- EXAME [N] | USUARIO [1]
alter table tra_exame
add constraint fk_tra_exame_id_usuario
foreign key (id_usuario)
references tra_usuario (id);

-- EXAME [N] | STATUS [1]
alter table tra_exame
add constraint fk_tra_exame_cd_status
foreign key (cd_status)
references tra_status_exame (cd_status);

/*
---------- TO-DO:

create table tvamr_servico_saude (
    id bigint generated by default as identity,
    nm_bairro varchar(255),
    te_cep varchar(255),
    nm_cidade varchar(255),
    nu_cnpj varchar(255),
    nm_estado varchar(255),
    nu_latitude bigint,
    nm_logradouro varchar(255),
    nu_longitude bigint,
    nu_numero_lote bigint,
    nm_completo varchar(255),
    nm_curto varchar(255),
    primary key (id)
)

alter table tvamr_exame
add constraint FK4bg7b7omwxi6tvcfohovul3u7
foreign key (id_servico_saude)
references tvamr_servico_saude

*/