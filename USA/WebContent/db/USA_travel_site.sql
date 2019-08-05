------------
--질문 테이블
------------
create table qa(
    qa_num number constraint qa_num_pk primary key, -- 번호PK
    qa_subject varchar2(300), -- 제목
    qa_readcount number, -- 조회수
    qa_date date default sysdate, -- 작성날짜
    qa_writer varchar2(120) constraint qa_writer_fk references mem(mem_name), -- 이름FK
    qa_filename varchar2(520), -- 파일이름
    qa_contents varchar2(4000), -- 내용
    qa_alive number default 0-- 삭제유무
);

----------------
--질문 댓글 테이블
----------------
create table qarep(
    qarep_num number constraint qarep_num_pk primary key, -- 번호PK
    qarep_qanum number constraint qarep_qanum_fk references qa(qa_num), -- 질문게시판 번호FK
    qarep_date date default sysdate, -- 작성날짜
    qarep_contents varchar2(4000), -- 내용
    qarep_writer varchar2(120) constraint qa_writer_fk references mem(mem_name), -- 이름FK
    qarep_writerrep number, -- 부모작성자 번호
    qarep_alive number default 0, -- 삭제유무
    qarep_numref number, -- 대댓글 유무
    qarep_numref_lv number -- 대댓글 순서
);

------------
--거래 테이블
------------
create table tra(
    tra_num number constraint tra_num_pk primary key, -- 번호PK
    tra_subject varchar2(300), -- 제목
    tra_readcount number, -- 조회수
    tra_date date default sysdate, -- 작성날짜
    tra_writer varchar2(120) constraint qa_writer_fk references mem(mem_name), -- 이름FK
    tra_filename varchar2(520), -- 파일이름
    tra_contents varchar2(4000), -- 내용
    tra_alive number, -- 삭제유무
    tra_head varchar2(9) -- 삽니다,팝니다
);

----------------
--거래 댓글 테이블
----------------
create table trarep(
    trarep_num constraint trarep_num_pk primary key, -- 번호PK
    trarep_tranum number constraint trarep_tranum_fk references tra(tra_num), -- 질문게시판 번호FK
    trarep_date date default sysdate, -- 작성날짜
    trarep_contents varchar2(4000), -- 내용
    trarep_writer varchar2(120) constraint qa_writer_fk references mem(mem_name), -- 이름FK
    trarep_writerrep number, -- 부모작성자 번호
    trarep_alive number default 0, -- 삭제유무
    trarep_numref number, -- 대댓글 유무
    trarep_numref_lv number -- 대댓글 순서
);

----------------
--지역(주) 테이블
----------------
create table reg(
    reg_name varchar2(120) constraint reg_name_pk primary key, -- 지역(주)이름
    reg_subject varchar2(300), -- 제목
    reg_contents varchar2(4000), -- 내용
    reg_filename varchar2(520) -- 파일이름
);

------------
--관광 테이블
------------
create table loc(
    loc_name varchar2(120) constraint loc_name_pk primary key, -- 관광이름
    loc_contents varchar2(4000), -- 내용
    loc_filename varchar2(520), -- 파일이름
    loc_regname varchar2(120) constraint loc_regname_fk references reg(reg_name) -- 지역이름FK
);

------------
--리뷰 테이블
------------
create table rev(
    rev_num number constraint rev_num_pk primary key, -- 번호PK
    rev_date date default sysdate, -- 작성날짜
    rev_writer varchar2(120) constraint rev_writer_fk references mem(mem_name), -- 이름FK
    rev_contents varchar2(4000), -- 내용
    rev_alive number, -- 삭제유무
    rev_score number, -- 평점
    rev_locname varchar2(120) references loc(loc_name) -- 관광이름FK
);

------------
--회원 테이블
------------
create table mem(
    mem_id varchar2(40) constraint mem_id_pk primary key, -- 아이디
    mem_pwd varchar2(32) not null, -- 비밀번호
    mem_ph varchar2(32), -- 전화번호
    mem_name varchar2(120), -- 이름   
    mem_addr varchar2(200), -- 주소
    mem_point number default 0, -- 포인트
    mem_alive number default 0, -- 탈퇴유무
    mem_cdate date default sysdate, -- 가입날짜
    mem_ddate date -- 탈퇴날짜
);
