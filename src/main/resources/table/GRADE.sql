create table GRADE
(
    GRADENO   NUMBER        not null
        constraint PK_GRADE
            primary key,
    GRADENAME VARCHAR2(100) not null,
    COMM      VARCHAR2(1000)
)
/

INSERT INTO ADMIN.GRADE (GRADENO, GRADENAME, COMM) VALUES (5000, '관리자', '총괄관리자');
INSERT INTO ADMIN.GRADE (GRADENO, GRADENAME, COMM) VALUES (1, '신입 회원', '신입 회원');
INSERT INTO ADMIN.GRADE (GRADENO, GRADENAME, COMM) VALUES (2, '일반 회원', '일반 회원, 게시글 3회 이상 작성');
INSERT INTO ADMIN.GRADE (GRADENO, GRADENAME, COMM) VALUES (3, '고급 회원', '고급 회원, 게시글 10회 이상 작성 + 접속일 14일 이상');
INSERT INTO ADMIN.GRADE (GRADENO, GRADENAME, COMM) VALUES (0, '관리자', '일반관리자');
