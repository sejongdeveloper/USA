-------------
--USA 계정생성
-------------
--계정명 제한변경
alter session set "_oracle_script"=true;

--계정 만들기 + lock 풀기
create user usa identified by oracle account unlock;

--기본 권한설정
grant connect, resource to usa;

--권한 주기(특정 권한 부여) 
grant  create session,   create table,  create view, create sequence,  create procedure to usa;

--테이블 할당량 무한 변경
alter user usa default tablespace users quota unlimited on users;