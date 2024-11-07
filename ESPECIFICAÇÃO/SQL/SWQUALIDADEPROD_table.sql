CREATE TABLE PRODUTO (
  NSERIE             varchar(255) NOT NULL, 
  REVISAO            varchar(30), 
  DATA_PROD          date NOT NULL, 
  REFUGO             int(10), 
  MOTIVO_REFUGO      varchar(255), 
  ETAPA              varchar(255) NOT NULL, 
  STATUS             int(10) NOT NULL, 
  QTD_TESTE_ELE      int(11), 
  MODELO_FK          bigint(19) NOT NULL, 
  LOTE_FK            varchar(255) NOT NULL, 
  INTEGRACAO_FIN_FK  bigint(19), 
  INSPECAO_FINAL_FK  bigint(19), 
  INSPECAO_FINAL_FK2 bigint(19), 
  UNIQUE INDEX (NSERIE));
  
CREATE TABLE USUARIO (
  ID     bigint(19) NOT NULL AUTO_INCREMENT, 
  LOGIN  varchar(40) NOT NULL UNIQUE, 
  EMAIL  varchar(50), 
  NOME   varchar(70) NOT NULL, 
  SENHA  varchar(30) NOT NULL, 
  PERFIL int(10) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE MODELO (
  ID       bigint(19) NOT NULL AUTO_INCREMENT, 
  MODELO   varchar(50) NOT NULL UNIQUE, 
  DESCICAO varchar(255), 
  IT       varchar(255), 
  TIPO     varchar(20) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE LOTE (
  ID             varchar(255) NOT NULL, 
  DATA_CRIACAO   date, 
  NSERIE_INICIAL bigint(19), 
  NSERIE_FINAL   int(10), 
  QTD            int(10), 
  QTDE_ATUAL     int(10), 
  DATA_PROD      date, 
  INDEX (ID));
  
CREATE TABLE PEM (
  NSERIE             varchar(255) NOT NULL, 
  REFUGO             int(10), 
  MOTIVO_REFUGO      varchar(255), 
  ETAPA              varchar(50) NOT NULL, 
  STATUS             int(10) NOT NULL, 
  QTD_TESTE_ELE      int(11), 
  PRODUTO_FK         varchar(255), 
  MODELO_FK          bigint(19) NOT NULL, 
  INSPECAO_VISUAL_FK bigint(19), 
  INSPECAO_FINAL_FK  bigint(19), 
  UNIQUE INDEX (NSERIE));
  
CREATE TABLE RETRABALHO (
  ID          bigint(19) NOT NULL AUTO_INCREMENT, 
  ORIGEM      varchar(255), 
  DATA_INICIO date, 
  DATA_FIM    date, 
  REFUGOU     int(10), 
  USUARIO_FK  bigint(19) NOT NULL, 
  PRODUTO_FK  varchar(255), 
  PEM_FK      varchar(255), 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE INTEGRACAO_FINAL (
  ID             bigint(19) NOT NULL AUTO_INCREMENT, 
  DATA_INICIO    date NOT NULL, 
  DATA_FIM       date NOT NULL, 
  QTD_REPROCESSO int(11), 
  USUARIO_FK     bigint(19) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE INSPECAO_VISUAL (
  ID             bigint(19) NOT NULL AUTO_INCREMENT, 
  DATA_INICIO    date, 
  DATA_FIM       int(10), 
  USUARIO_FK     bigint(19) NOT NULL, 
  QTD_REPROCESSO int(11), 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE INSPECAO_FINAL (
  ID             bigint(19) NOT NULL AUTO_INCREMENT, 
  DATA_INICIO    int(10), 
  DATA_FIM       date, 
  QTD_REPROCESSO int(11), 
  USUARIO_FK     bigint(19) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE TESTE_ELETRICO (
  ID          bigint(19) NOT NULL AUTO_INCREMENT, 
  DATA_INICIO date, 
  DATA_FIM    date, 
  VOLTAGEM    varchar(50), 
  USUARIO_FK  bigint(19) NOT NULL, 
  PRODUTO_FK  varchar(255) NOT NULL, 
  PEM_FK      varchar(255) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE PROGRAMACAO (
  ID               bigint(19) NOT NULL AUTO_INCREMENT, 
  DATA_PROG_INICIO date, 
  DATA_PROG_FIM    date, 
  DATA_REAL_INICIO date, 
  DATA_REAL_FIM    int(10), 
  OBSERVACOES      varchar(255), 
  TIPO_PROD        varchar(50), 
  LOTE_FK          varchar(255) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
  
CREATE TABLE EQUIP_MEDICAO (
  PATRIMONIO      varchar(255) NOT NULL, 
  MARCA           varchar(100), 
  MODELO          varchar(255), 
  SETOR           varchar(255), 
  DATA_VENC_CALIB date, 
  STATUS          varchar(100), 
  UNIQUE INDEX (PATRIMONIO));
  
CREATE TABLE EQUIP_MEDICAO_TESTE_ELETRICO (
  EQUIP_MEDICAOPATRIMONIO varchar(255) NOT NULL, 
  TESTE_ELETRICO_FK       bigint(19) NOT NULL);
  
CREATE TABLE CLASSE_DEFEITOS (
  CODIGO    bigint(20) NOT NULL AUTO_INCREMENT, 
  DESCRICAO varchar(255) NOT NULL, 
  TIPO      varchar(20), 
  PRIMARY KEY (CODIGO), 
  UNIQUE INDEX (CODIGO));
  
CREATE TABLE DEFEITO (
  ID                bigint(19) NOT NULL AUTO_INCREMENT, 
  LOC1              varchar(10), 
  LOC2              varchar(10), 
  LOC3              varchar(10), 
  LOC4              varchar(10), 
  LOC5              varchar(10), 
  CLASSE_DEFEITO_FK bigint(20) NOT NULL, 
  REPARO_FK         bigint(19) NOT NULL, 
  PRIMARY KEY (ID), 
  UNIQUE INDEX (ID));
