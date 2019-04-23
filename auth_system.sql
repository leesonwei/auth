/*==============================================================*/
/* DBMS name:      ORACLE Version 12c                           */
/* Created on:     2019/4/23 12:07:55                           */
/*==============================================================*/


DROP TABLE "AUTH_APPLICATION" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_AUTHORITY" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_AUTHORITY_ACTION" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_GROUP" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_GROUP_AUTHORITY" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_LOG" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_MENU" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_ROLE" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_ROLE_AUTHORITY" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_USER" CASCADE CONSTRAINTS;

DROP TABLE "AUTH_USER_ROLE" CASCADE CONSTRAINTS;

DROP TABLE "COM_ACTION" CASCADE CONSTRAINTS;

DROP TABLE "COM_AUTOID" CASCADE CONSTRAINTS;

DROP TABLE "COM_DICT" CASCADE CONSTRAINTS;

DROP TABLE "COM_DICT_TYPE" CASCADE CONSTRAINTS;

DROP TABLE "COM_LOG" CASCADE CONSTRAINTS;

/*==============================================================*/
/* Table: "AUTH_APPLICATION"                                    */
/*==============================================================*/
CREATE TABLE "AUTH_APPLICATION" (
   "ID"                 INTEGER               NOT NULL,
   "NAME"               VARCHAR2(100)         NOT NULL,
   "COMPANY_NAME"       VARCHAR2(200),
   "DATA_VERSION"       INTEGER,
   CONSTRAINT PK_AUTH_APPLICATION PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_AUTHORITY"                                      */
/*==============================================================*/
CREATE TABLE "AUTH_AUTHORITY" (
   "ID"                 INTEGER               NOT NULL,
   "NAME"               VARCHAR2(100)         NOT NULL,
   "DESC"               VARCHAR2(200),
   "TYPE"               INTEGER               NOT NULL,
   "PARENT_ID"          INTEGER,
   "DATA_VERSION"       INTEGER               NOT NULL,
   "URL"                VARCHAR2(150),
   "APPLICATION_ID"     INTEGER,
   CONSTRAINT PK_AUTH_AUTHORITY PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_AUTHORITY_ACTION"                               */
/*==============================================================*/
CREATE TABLE "AUTH_AUTHORITY_ACTION" (
   "ID"                 INTEGER               NOT NULL,
   "AUTHORITY_ID"       INTEGER               NOT NULL,
   "ACTION_ID"          VARCHAR2(10)          NOT NULL,
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_AUTHORITY_ACTION PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_GROUP"                                          */
/*==============================================================*/
CREATE TABLE "AUTH_GROUP" (
   "ID"                 VARCHAR2(10)          NOT NULL,
   "NAME"               VARCHAR2(100)         NOT NULL,
   "DESC"               VARCHAR2(200),
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_GROUP PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_GROUP_AUTHORITY"                                */
/*==============================================================*/
CREATE TABLE "AUTH_GROUP_AUTHORITY" (
   "ID"                 INTEGER               NOT NULL,
   "GROUP_ID"           VARCHAR2(10)          NOT NULL,
   "AUTHORITY_ID"       INTEGER               NOT NULL,
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_GROUP_AUTHORITY PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_LOG"                                            */
/*==============================================================*/
CREATE TABLE "AUTH_LOG" (
   "ID"                 INTEGER               NOT NULL,
   "TABLE_NAME"         VARCHAR2(50)          NOT NULL,
   "ACTION_ID"          VARCHAR2(10)          NOT NULL,
   "CONTENT"            VARCHAR2(1000),
   "RESULT"             VARCHAR2(500),
   "OPERATE_TIME"       DATE                  NOT NULL,
   "USER_ID"            VARCHAR2(64)          NOT NULL,
   CONSTRAINT PK_AUTH_LOG PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_MENU"                                           */
/*==============================================================*/
CREATE TABLE "AUTH_MENU" (
   "ID"                 INTEGER               NOT NULL,
   "NAME"               VARCHAR2(64)          NOT NULL,
   "LEVEL"              INTEGER               NOT NULL,
   "ICON"               VARCHAR2(100),
   "ORDER"              INTEGER,
   "PARENT_ID"          INTEGER,
   "AUTHORITY_ID"       INTEGER,
   "DATA_VERSION"       INTEGER               NOT NULL,
   "APPLICATION_ID"     INTEGER,
   CONSTRAINT PK_AUTH_MENU PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_ROLE"                                           */
/*==============================================================*/
CREATE TABLE "AUTH_ROLE" (
   "ID"                 VARCHAR2(10)          NOT NULL,
   "NAME"               VARCHAR2(30)          NOT NULL,
   "DESC"               VARCHAR2(200),
   "LEVEL"              INTEGER               NOT NULL,
   "PARENT_ID"          VARCHAR2(10),
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_ROLE PRIMARY KEY ("ID")
);

COMMENT ON TABLE "AUTH_ROLE" IS
'角色表';

/*==============================================================*/
/* Table: "AUTH_ROLE_AUTHORITY"                                 */
/*==============================================================*/
CREATE TABLE "AUTH_ROLE_AUTHORITY" (
   "ID"                 INTEGER               NOT NULL,
   "ROLE_ID"            VARCHAR2(10)          NOT NULL,
   "AUTHORITY_ID"       INTEGER               NOT NULL,
   "TYPE"               INTEGER,
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_ROLE_AUTHORITY PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_USER"                                           */
/*==============================================================*/
CREATE TABLE "AUTH_USER" (
   "ID"                 VARCHAR2(64)          NOT NULL,
   "NAME"               VARCHAR2(20)          NOT NULL,
   "NICK_NAME"          VARCHAR2(50)          NOT NULL,
   "PASSWORD"           VARCHAR2(50)          NOT NULL,
   "SEX"                INTEGER               NOT NULL,
   "ADDRESS"            VARCHAR2(200),
   "EMAIL"              VARCHAR2(50),
   "PHONE"              VARCHAR2(15),
   "DEPARTMENT_ID"      VARCHAR2(10),
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_USER PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "AUTH_USER_ROLE"                                      */
/*==============================================================*/
CREATE TABLE "AUTH_USER_ROLE" (
   "ID"                 INTEGER               NOT NULL,
   "USER_ID"            VARCHAR2(64)          NOT NULL,
   "ROLE_ID"            VARCHAR2(10)          NOT NULL,
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_AUTH_USER_ROLE PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "COM_ACTION"                                          */
/*==============================================================*/
CREATE TABLE "COM_ACTION" (
   "ID"                 VARCHAR2(10)          NOT NULL,
   "NAME"               VARCHAR2(50)          NOT NULL,
   "VALUE"              INTEGER               NOT NULL,
   "DATA_VERSION"       INTEGER               NOT NULL,
   CONSTRAINT PK_COM_ACTION PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "COM_AUTOID"                                          */
/*==============================================================*/
CREATE TABLE "COM_AUTOID" (
   "ID"                 INTEGER,
   "MODEL_NAME"         VARCHAR2(30),
   "PREFIX"             VARCHAR2(10),
   "LENGTH"             INTEGER,
   "CURRENT"            INTEGER,
   "NEXT"               INTEGER
);

/*==============================================================*/
/* Table: "COM_DICT"                                            */
/*==============================================================*/
CREATE TABLE "COM_DICT" (
   "ID"                 VARCHAR2(30)          NOT NULL,
   "NAME"               VARCHAR2(50)          NOT NULL,
   "FULL_NAME"          VARCHAR2(100),
   "DESC"               VARCHAR2(100),
   "FLAG"               INTEGER,
   "DATA_VERSION"       INTEGER               NOT NULL,
   "PYCODE"             VARCHAR2(100),
   "TYPE_ID"            VARCHAR2(20)          NOT NULL,
   CONSTRAINT PK_COM_DICT PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "COM_DICT_TYPE"                                       */
/*==============================================================*/
CREATE TABLE "COM_DICT_TYPE" (
   "ID"                 VARCHAR2(20)          NOT NULL,
   "NAME"               VARCHAR2(50)          NOT NULL,
   "DESC"               VARCHAR2(100),
   "FLAG"               INTEGER,
   CONSTRAINT PK_COM_DICT_TYPE PRIMARY KEY ("ID")
);

/*==============================================================*/
/* Table: "COM_LOG"                                             */
/*==============================================================*/
CREATE TABLE "COM_LOG" (
   "ID"                 INTEGER               NOT NULL,
   "TABLE_NAME"         VARCHAR2(50)          NOT NULL,
   "ACTION_ID"          VARCHAR2(10)          NOT NULL,
   "CONTENT"            VARCHAR2(1000),
   "RESULT"             VARCHAR2(500),
   "OPERATE_TIME"       DATE                  NOT NULL,
   "USER_ID"            VARCHAR2(64)          NOT NULL,
   CONSTRAINT PK_COM_LOG PRIMARY KEY ("ID")
);

