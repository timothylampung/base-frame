
    create table DEX_ACTR (
        ID int8 not null,
        ACTOR_TYPE int4 not null,
        ADDRESS1 varchar(255),
        ADDRESS2 varchar(255),
        ADDRESS3 varchar(255),
        CODE varchar(255) not null,
        EMAIL varchar(255) not null,
        FAX varchar(255),
        IDENTITY_NO varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        MOBILE varchar(255),
        NAME varchar(255) not null,
        PHONE varchar(255),
        POSTCODE varchar(255),
        primary key (ID)
    );

    create table DEX_ASST (
        ID int8 not null,
        CODE varchar(255),
        DESCRIPTION varchar(255),
        primary key (ID)
    );

    create table DEX_ASST_CODE (
        ID int8 not null,
        CODE varchar(255),
        DESCRIPTION varchar(255),
        primary key (ID)
    );

    create table DEX_ATVT (
        ID int8 not null,
        CODE varchar(255),
        DESCRIPTION varchar(255),
        primary key (ID)
    );

    create table DEX_BANK (
        ID int8 not null,
        ADDRESS varchar(255) not null,
        BRANCH varchar(255) not null,
        CODE varchar(255) not null,
        CONTACT_NO varchar(255) not null,
        DESCRIPTION varchar(255) not null,
        EMAIL varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        NAME varchar(255) not null,
        PERSON_IN_CHARGE varchar(255) not null,
        REMARKS varchar(255) not null,
        STATUS int4 not null,
        TYPE int4 not null,
        primary key (ID)
    );

    create table DEX_CNFG (
        ID int8 not null,
        DESCRIPTION varchar(255),
        CONFIG_KEY varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        CONFIG_VALUE varchar(255),
        CONFIG_VALUE_BYTEA bytea,
        CONFIG_VALUE_DOUBLE float8,
        CONFIG_VALUE_LONG int8,
        primary key (ID)
    );

    create table DEX_FORM (
        ID int8 not null,
        CODE varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REF_NO varchar(255) not null,
        primary key (ID)
    );

    create table DEX_GRDE_CODE (
        ID int8 not null,
        CODE varchar(255) not null,
        DESCRIPTION varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        primary key (ID)
    );

    create table DEX_GROP (
        ID int8 not null,
        primary key (ID)
    );

    create table DEX_GROP_MMBR (
        ID int8 not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        GROUP_ID int8,
        PRINCIPAL_ID int8,
        primary key (GROUP_ID, PRINCIPAL_ID)
    );

    create table DEX_LCTN (
        ID int8 not null,
        CODE varchar(255),
        DESCRIPTION varchar(255),
        primary key (ID)
    );

    create table DEX_MODL (
        ID int8 not null,
        CANONICAL_CODE varchar(255) not null,
        CODE varchar(255) not null,
        DESCRIPTION varchar(255),
        ENABLED boolean,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        ORDINAL int4 not null,
        primary key (ID)
    );

    create table DEX_PCPL (
        ID int8 not null,
        ENABLED boolean not null,
        LOCKED boolean not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        NAME varchar(255) not null,
        PRINCIPAL_TYPE int4,
        primary key (ID)
    );

    create table DEX_PCPL_ROLE (
        ID int8 not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        ROLE_TYPE int4,
        PRINCIPAL_ID int8,
        primary key (ID)
    );

    create table DEX_PSTN_CODE (
        ID int8 not null,
        CODE varchar(255) not null,
        DESCRIPTION varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        primary key (ID)
    );

    create table DEX_RFRN_NO (
        ID int8 not null,
        CODE varchar(255) not null,
        CURRENT_VALUE int4,
        DESCRIPTION varchar(255) not null,
        INCREMENT_VALUE int4,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        PREFIX varchar(255),
        REFERENCE_FORMAT varchar(255),
        SEQUENCE_FORMAT varchar(255),
        primary key (ID)
    );

    create table DEX_SMDL (
        ID int8 not null,
        CODE varchar(255),
        DESCRIPTION varchar(255),
        ENABLED boolean,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        ORDINAL int4,
        MODULE_ID int8,
        primary key (ID)
    );

    create table DEX_STAF (
        STAFF_CODE varchar(255),
        ID int8 not null,
        primary key (ID)
    );

    create table DEX_USER (
        EMAIL varchar(255) not null,
        PASSWORD varchar(255),
        REAL_NAME varchar(255) not null,
        ID int8 not null,
        ACTOR_ID int8,
        primary key (ID)
    );

    create table DEX_WODR (
        ID int8 not null,
        CODE varchar(255),
        DESCRIPTION varchar(255),
        primary key (ID)
    );

    alter table DEX_ACTR 
        add constraint uc_DEX_ACTR_1 unique (CODE);

    alter table DEX_BANK 
        add constraint uc_DEX_BANK_1 unique (CODE);

    alter table DEX_GRDE_CODE 
        add constraint uc_DEX_GRDE_CODE_1 unique (CODE);

    alter table DEX_GROP 
        add constraint FK756497546D425AB6 
        foreign key (ID) 
        references DEX_PCPL;

    alter table DEX_GROP_MMBR 
        add constraint FK8997BFFBA9E5D8C7 
        foreign key (GROUP_ID) 
        references DEX_GROP;

    alter table DEX_GROP_MMBR 
        add constraint FK8997BFFBA3843867 
        foreign key (PRINCIPAL_ID) 
        references DEX_PCPL;

    alter table DEX_MODL 
        add constraint uc_DEX_MODL_1 unique (CANONICAL_CODE);

    alter table DEX_MODL 
        add constraint uc_DEX_MODL_2 unique (CODE);

    alter table DEX_PCPL 
        add constraint uc_DEX_PCPL_1 unique (NAME);

    alter table DEX_PCPL_ROLE 
        add constraint FKD7E32D3EA3843867 
        foreign key (PRINCIPAL_ID) 
        references DEX_PCPL;

    alter table DEX_PSTN_CODE 
        add constraint uc_DEX_PSTN_CODE_1 unique (CODE);

    alter table DEX_RFRN_NO 
        add constraint uc_DEX_RFRN_NO_1 unique (CODE);

    alter table DEX_SMDL 
        add constraint FK7569F7AAE2393E1C 
        foreign key (MODULE_ID) 
        references DEX_MODL;

    alter table DEX_STAF 
        add constraint FK756A118EFA49777D 
        foreign key (ID) 
        references DEX_ACTR;

    alter table DEX_USER 
        add constraint uc_DEX_USER_1 unique (EMAIL);

    alter table DEX_USER 
        add constraint FK756AF71397E7F207 
        foreign key (ACTOR_ID) 
        references DEX_ACTR;

    alter table DEX_USER 
        add constraint FK756AF7136D425AB6 
        foreign key (ID) 
        references DEX_PCPL;

    create sequence SQ_DEX_ACTR;

    create sequence SQ_DEX_ASST;

    create sequence SQ_DEX_ASST_CODE;

    create sequence SQ_DEX_ATVT;

    create sequence SQ_DEX_BANK;

    create sequence SQ_DEX_CNFG;

    create sequence SQ_DEX_FORM;

    create sequence SQ_DEX_GRDE_CODE;

    create sequence SQ_DEX_GROP_MMBR;

    create sequence SQ_DEX_LCTN;

    create sequence SQ_DEX_MODL;

    create sequence SQ_DEX_PCPL;

    create sequence SQ_DEX_PCPL_ROLE;

    create sequence SQ_DEX_PSTN_CODE;

    create sequence SQ_DEX_RFRN_NO;

    create sequence SQ_DEX_SMDL;

    create sequence SQ_DEX_WODR;

    alter table DEX_GROP 
        drop constraint FK756497546D425AB6;

    alter table DEX_GROP_MMBR 
        drop constraint FK8997BFFBA9E5D8C7;

    alter table DEX_GROP_MMBR 
        drop constraint FK8997BFFBA3843867;

    alter table DEX_PCPL_ROLE 
        drop constraint FKD7E32D3EA3843867;

    alter table DEX_SMDL 
        drop constraint FK7569F7AAE2393E1C;

    alter table DEX_STAF 
        drop constraint FK756A118EFA49777D;

    alter table DEX_USER 
        drop constraint FK756AF71397E7F207;

    alter table DEX_USER 
        drop constraint FK756AF7136D425AB6;

    drop table if exists DEX_ACTR cascade;

    drop table if exists DEX_ASST cascade;

    drop table if exists DEX_ASST_CODE cascade;

    drop table if exists DEX_ATVT cascade;

    drop table if exists DEX_BANK cascade;

    drop table if exists DEX_CNFG cascade;

    drop table if exists DEX_FORM cascade;

    drop table if exists DEX_GRDE_CODE cascade;

    drop table if exists DEX_GROP cascade;

    drop table if exists DEX_GROP_MMBR cascade;

    drop table if exists DEX_LCTN cascade;

    drop table if exists DEX_MODL cascade;

    drop table if exists DEX_PCPL cascade;

    drop table if exists DEX_PCPL_ROLE cascade;

    drop table if exists DEX_PSTN_CODE cascade;

    drop table if exists DEX_RFRN_NO cascade;

    drop table if exists DEX_SMDL cascade;

    drop table if exists DEX_STAF cascade;

    drop table if exists DEX_USER cascade;

    drop table if exists DEX_WODR cascade;

    drop sequence SQ_DEX_ACTR;

    drop sequence SQ_DEX_ASST;

    drop sequence SQ_DEX_ASST_CODE;

    drop sequence SQ_DEX_ATVT;

    drop sequence SQ_DEX_BANK;

    drop sequence SQ_DEX_CNFG;

    drop sequence SQ_DEX_FORM;

    drop sequence SQ_DEX_GRDE_CODE;

    drop sequence SQ_DEX_GROP_MMBR;

    drop sequence SQ_DEX_LCTN;

    drop sequence SQ_DEX_MODL;

    drop sequence SQ_DEX_PCPL;

    drop sequence SQ_DEX_PCPL_ROLE;

    drop sequence SQ_DEX_PSTN_CODE;

    drop sequence SQ_DEX_RFRN_NO;

    drop sequence SQ_DEX_SMDL;

    drop sequence SQ_DEX_WODR;
