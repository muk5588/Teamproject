-- 유저테이블
create table USERTB_TEST
(
    USERNO        NUMBER        not null
        primary key,
    NAME          VARCHAR2(500) not null,
    USERID        VARCHAR2(500) not null,
    USERPW        VARCHAR2(500) not null,
    NICKNAME      varchar2(500) not null,
    GENDER        VARCHAR2(500),
    EMAIL         VARCHAR2(500) not null,
    ADDRESS       VARCHAR2(500) not null,
    DETAILADDRESS VARCHAR2(500),
    EXTRAADDRESS  VARCHAR2(500),
    PHONE         VARCHAR2(500),
    POSTCODE      NUMBER,
    ADMINNO       NUMBER,
    REPORTNO      NUMBER
);
create sequence USER_SEQ_TEST;