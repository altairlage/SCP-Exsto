ALTER TABLE LOTE ADD PRIMARY KEY (ID);
ALTER TABLE PROGRAMACAO ADD PRIMARY KEY (ID);
ALTER TABLE MODELO ADD PRIMARY KEY (ID);
ALTER TABLE PRODUTO ADD PRIMARY KEY (NSERIE);
ALTER TABLE PEM ADD PRIMARY KEY (NSERIE);
ALTER TABLE EQUIP_MEDICAO ADD PRIMARY KEY (PATRIMONIO);
ALTER TABLE EQUIP_MEDICAO_TESTE_ELETRICO ADD PRIMARY KEY (EQUIP_MEDICAOPATRIMONIO, TESTE_ELETRICO_FK);
ALTER TABLE INTEGRACAO_FINAL ADD PRIMARY KEY (ID);
ALTER TABLE INSPECAO_VISUAL ADD PRIMARY KEY (ID);
ALTER TABLE INSPECAO_FINAL ADD PRIMARY KEY (ID);
ALTER TABLE TESTE_ELETRICO ADD PRIMARY KEY (ID);
ALTER TABLE RETRABALHO ADD PRIMARY KEY (ID);
ALTER TABLE DEFEITO ADD PRIMARY KEY (ID);
ALTER TABLE CLASSE_DEFEITO ADD PRIMARY KEY (CODIGO);
ALTER TABLE USUARIO ADD PRIMARY KEY (ID);
