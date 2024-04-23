-- 등급테이블
create table GRADETB_TEST
(
    GRADENO   NUMBER not null
        constraint GRADETB_TEST_PK
            primary key,
    GRADENAME VARCHAR2(100),
    COMM      VARCHAR2(500)
);
