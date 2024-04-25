create table USERTB
(
    USERNO        NUMBER             not null
        constraint PK_USER
            primary key,
    GRADENO       NUMBER default 1   not null
        constraint FK_USER1
            references GRADE,
    NICKNAME      VARCHAR2(300)      not null,
    NAME          VARCHAR2(100)      not null,
    USERID        VARCHAR2(300)      not null
        unique,
    USERPW        VARCHAR2(500)      not null,
    GENDER        VARCHAR2(1),
    EMAIL         VARCHAR2(500)      not null,
    PHONE         VARCHAR2(50)       not null,
    POSTCODE      NUMBER             not null,
    DETAILADDRESS VARCHAR2(500)      not null,
    EXTRAADDRESS  VARCHAR2(500)      not null,
    PROFILE       CHAR   default 'N' not null,
    SUSER_NO      VARCHAR2(1500),
    BLACK         CHAR   default 'N' not null,
    ADDRESS       VARCHAR2(500)      not null
)
/

comment on column USERTB.USERNO is '시퀀스'
/

comment on column USERTB.USERID is 'UNIQUE'
/

comment on column USERTB.PROFILE is 'Y'
/

comment on column USERTB.BLACK is 'Y/N - 총 신고가 5회 이상이면 정지'
/

INSERT INTO ADMIN.USERTB (USERNO, GRADENO, NICKNAME, NAME, USERID, USERPW, GENDER, EMAIL, PHONE, POSTCODE, DETAILADDRESS, EXTRAADDRESS, PROFILE, SUSER_NO, BLACK, ADDRESS) VALUES (7, 1, 'ABCDDD', '김선진', '2b6344b6-2296-4ea7-bbfe-1bbe623e1a57', 'a9fc18e3-fa8f-4089-8f6f-653f769b2d6e', 'M', 'jxoow153@naver.com', '01012345678', 8792, '123123', ' (봉천동)', 'N', '3451082826', 'N', '서울 관악구 낙성대역길 8');
INSERT INTO ADMIN.USERTB (USERNO, GRADENO, NICKNAME, NAME, USERID, USERPW, GENDER, EMAIL, PHONE, POSTCODE, DETAILADDRESS, EXTRAADDRESS, PROFILE, SUSER_NO, BLACK, ADDRESS) VALUES (6, 1, 'tttt', '김선진', '10fcc178-ab57-490d-836b-12ab2e453cb7', '9140d8eb-3b67-4445-836f-4216fb6e2236', 'M', 'jxoow153@naver.com', '01012345678', 3101, '123123', ' (창신동)', 'N', 'KLZMlgayORQx_Yj23kO7HasYVUUIqnuO-1dloPFv0BM', 'N', '서울 종로구 낙산성곽길 2-1');
INSERT INTO ADMIN.USERTB (USERNO, GRADENO, NICKNAME, NAME, USERID, USERPW, GENDER, EMAIL, PHONE, POSTCODE, DETAILADDRESS, EXTRAADDRESS, PROFILE, SUSER_NO, BLACK, ADDRESS) VALUES (1, 5000, 'admin', 'admin', 'admin', 'admin', 'M', '111@111.111', '11111111111', 6234, '5층', '351강의장', 'n', null, 'N', '서울 강남구 테헤란로14길');
