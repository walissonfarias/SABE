CREATE TABLE USUARIO(
ID INTEGER NOT NULL PRIMARY KEY IDENTITY,
NOME VARCHAR(60) NOT NULL,
LOGIN VARCHAR(10) NOT NULL,
SENHA VARCHAR(32) NOT NULL,
CARGO VARCHAR(2) NOT NULL);
===================================================================
-- QUANDO EXCLUIR BENEFICIARIO, SERA EXCLUIDO SUA RELACAO COM A TABELA B_B
CREATE TABLE BENEFICIO(
ID INTEGER NOT NULL PRIMARY KEY IDENTITY,
NOME VARCHAR(60) NOT NULL,
TIPO VARCHAR(60) NOT NULL,
VALOR DOUBLE NOT NULL
);
====================================================================
-- QUANDO FOR EXCLUIDO O BENEFICIARIO SERA ESCLUIDO SUA CHAVE EM TODAS AS TABELAS
CREATE TABLE BENEFICIARIO( 
ID INTEGER NOT NULL PRIMARY KEY IDENTITY,
NIS CHAR(11) NOT NULL UNIQUE,
NOME VARCHAR(60) NOT NULL,
RUA VARCHAR(60),
NUMERO INTEGER,
BAIRRO VARCHAR(60),
ZONA CHAR(1) NOT NULL,
LOCALIDADE VARCHAR(60) NOT NULL,
RENDA_FAMILIAR DOUBLE,
RENDA_PER_CAPTA DOUBLE);
====================================================================
INSERT INTO "PUBLIC"."BENEFICIARIO"
( "NIS", "NOME", "RUA", "NUMERO", "BAIRRO", "ZONA","LOCALIDADE", "QTDE_MEMBROS_DA_FAMILIA", "RENDA_FAMILIAR", 
"RENDA_PER_CAPTA" )VALUES ( '11111111112','WEMERSON','JOSE AUGUSTO','790','vv','U','Centro','3','788.00','222.00')

CONSULTAS
SELECT BE.NOME AS BENEFICIO, BA.NOME, BA.NIS FROM BENEFICIO BE LEFT JOIN BENEFICIO_BENEFICIARIO BE_BA ON 
BE.ID=BE_BA.ID_BENEFICIO LEFT JOIN BENEFICIARIO BA ON BE_BA.ID_BENEFICIARIO=BA.ID;
====================================================================

CREATE TABLE BENEFICIO_BENEFICIARIO(
ID_BENEFICIO INTEGER NOT NULL, 
ID_BENEFICIARIO INTEGER NOT NULL, 
PRIMARY KEY(ID_BENEFICIO, ID_BENEFICIARIO),
FOREIGN KEY(ID_BENEFICIO) REFERENCES BENEFICIO(ID)ON DELETE CASCADE,
FOREIGN KEY(ID_BENEFICIARIO) REFERENCES BENEFICIARIO(ID)ON DELETE CASCADE);
=====================================================================
-- QUANDO EXCLUIDO OS CAMPOS DA TABELA RESULTADO TBM SERAM EXCLUIDOS
CREATE TABLE PEDIDO(
ID INTEGER NOT NULL PRIMARY KEY IDENTITY,
DATA_PEDIDO DATE NOT NULL,
SITUACAO VARCHAR(200) NOT NULL,
ID_BENEFICIARIO INTEGER NOT NULL,
FOREIGN KEY(ID_BENEFICIARIO) REFERENCES BENEFICIARIO(ID)ON DELETE CASCADE);

INSERT INTO "PUBLIC"."PEDIDO"
( "SITUACAO", "ID_BENEFICIARIO" )
VALUES ('VERIFICAR SE DANILO RESIDE NA CASA', '2' );
======================================================================
CREATE TABLE RESULTADO(
ID INTEGER NOT NULL PRIMARY KEY IDENTITY,
DATA_RESULTADO DATE NOT NULL,
RESULTADO VARCHAR(200),
DECISAO VARCHAR(30),
ID_PEDIDO INTEGER NOT NULL REFERENCES PEDIDO(ID)ON DELETE CASCADE);

INSERT INTO "PUBLIC"."RESULTADO"
("RESULTADO", "DECISAO" )
VALUES ('DANILO MORA NA CASA', 'RECADASTRAR');
=======================================================================
CREATE TABLE AVERIGUACAO(
ID_PEDIDO INTEGER NOT NULL,
ID_RESULTADO INTEGER NOT NULL,
PRIMARY KEY(ID_PEDIDO, ID_RESULTADO),
FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDO(ID),
FOREIGN KEY(ID_RESULTADO) REFERENCES RESULTADO(ID));
================================
PE1,BE7,B2
CREATE TABLE RESULT(
ID INTEGER NOT NULL IDENTITY,
ID_PEDIDO INTEGER NOT NULL,
RESULTADO VARCHAR(200),
DECISAO VARCHAR(30),
CONSTRAINT PK_PEDIDO PRIMARY KEY(ID, ID_PEDIDO),
CONSTRAINT FK_PEDIDO FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID));


SELECT P.DATA_PEDIDO, BRIO.NIS, BRIO.NOME,
               BRIO.ZONA, BRIO.LOCALIDADE, BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, P.SITUACAO FROM BENEFICIARIO BRIO 
               JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO LEFT JOIN RESULTADO R ON R.ID_PEDIDO=P.ID
               WHERE P.DATA_PEDIDO >='2016-01-01' AND P.DATA_PEDIDO <= '2016-12-12'  AND R.ID IS NULL


SELECT B.NOME AS BENEFICIARIO, P.SITUACAO AS SITUACAO, R.RESULTADO AS RESULTADO, R.DECISAO DECISAO
FROM BENEFICIARIO B INNER JOIN PEDIDO P ON B.ID = P.ID_BENEFICIARIO JOIN AVERIGUACAO A ON P.ID=A.ID_PEDIDO JOIN RESULTADO R ON A.ID_RESULTADO=R.ID

SELECT BRIO.ID, BRIO.NIS, BRIO.NOME, BRIO.RUA, BRIO.NUMERO, BRIO.BAIRRO, BRIO.ZONA, 
BRIO.LOCALIDADE, BRIO.QTDE_MEMBROS, BRIO.RENDA_FAMILIAR, BRIO.RENDA_PER_CAPTA, B.ID, B.NOME, B.TIPO, B.VALOR FROM BENEFICIARIO BRIO
JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID


INSERT INTO "PUBLIC"."VENDA"
( "NUMERO", "DATA", "NOME_CLIENTE", "CPF_CLIENTE", "VALOR", "FORMA_PAGAMENTO", "OBSERVACAO" )
VALUES (3, '2016-07-22', 'walisson', '111.111.111-11', 20, 'AV', 'sdfgf')

# DATE                    esta no formato AAAA-MM-DD
# DATETIME                esta no formato AAAA-MM-DD HH:MM:SS
# TIMESTAMP               esta no formato AAAAMMDDHHMMS

