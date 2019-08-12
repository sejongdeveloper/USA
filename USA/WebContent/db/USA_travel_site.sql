------------
--회원 테이블
------------
create table mem(
    mem_id varchar2(40) constraint mem_id_pk primary key, -- 아이디
    mem_pwd varchar2(32) not null, -- 비밀번호
    mem_ph varchar2(32), -- 전화번호
    mem_name varchar2(120), -- 이름   
    mem_addr varchar2(200), -- 주소
    mem_filename varchar2(520), -- 파일이름
    mem_point number default 100, -- 포인트
    mem_alive number default 1, -- 탈퇴유무(탈퇴:0)
    mem_cdate date default sysdate, -- 가입날짜
    mem_ddate date -- 탈퇴날짜
);

------------
--질문 테이블
------------
create table qa(
    qa_num number constraint qa_num_pk primary key, -- 번호PK
    qa_subject varchar2(300), -- 제목
    qa_readcount number, -- 조회수
    qa_date date default sysdate, -- 작성날짜
    qa_writer varchar2(120) constraint qa_writer_fk references mem(mem_id), -- 아이디FK
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
    qarep_writer varchar2(120) constraint qarep_writer_fk references mem(mem_id), -- 아이디FK
    qarep_writerrep number constraint qarep_writerrep_fk references qa(qa_num), -- 부모작성자 번호 
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
    tra_sysdate date default sysdate, -- 작성날짜
    tra_writer varchar2(120), -- 아이디FK
    tra_filename varchar2(520), -- 파일이름
    tra_contents varchar2(4000), -- 내용
    tra_alive number, -- 삭제유무
    tra_head varchar2(9) -- 삽니다,팝니다
);

----------------
--거래 댓글 테이블
----------------
create table trarep(
    trarep_num number constraint trarep_num_pk primary key, -- 번호PK
    trarep_tranum number constraint trarep_tranum_fk references tra(tra_num), -- 질문게시판 번호FK
    trarep_date date default sysdate, -- 작성날짜
    trarep_contents varchar2(4000), -- 내용
    trarep_writer varchar2(120) constraint trarep_writer_fk references mem(mem_id), -- 아이디FK
    trarep_writerrep number , -- 부모작성자 번호
    trarep_alive number default 0, -- 삭제유무
    trarep_numref number, -- 대댓글 유무
    trarep_numref_lv number, -- 대댓글 순서
    trarep_writerrepwriter varchar2(40) -- 누구의 대댓글인지 저장하는 컬럼.
);

-----------------
--지역이름 테이블
-----------------
create table regName(
    regName varchar2(120) constraint regName_pk primary key, -- 지역이름PK
    regNameEng varchar2(50),
    regGmt varchar2(20),
    regflight number
);

-----------------
--지역 테이블
-----------------
create table reg(
    reg_num number constraint reg_num_pk primary key, -- 번호PK
    reg_name varchar2(120) constraint reg_reg_Name_fk references regName(regName), -- 지역이름
    reg_subject varchar2(300), -- 제목
    reg_contents varchar2(4000), -- 내용
    reg_filename varchar2(520) -- 파일이름
);
CREATE SEQUENCE reg_num;

------------
--관광 테이블
------------
create table loc(
    loc_name varchar2(120) constraint loc_name_pk primary key, -- 관광이름
    loc_contents varchar2(4000), -- 내용
    loc_filename varchar2(520), -- 파일이름
    loc_regname varchar2(120) constraint loc_regname_fk references regName(regName) -- 지역이름FK
);

------------
--리뷰 테이블
------------
create table rev(
    rev_num number constraint rev_num_pk primary key, -- 번호PK
    rev_date date default sysdate, -- 작성날짜
    rev_writer varchar2(120) constraint rev_writer_fk references mem(mem_id), -- 아이디FK
    rev_contents varchar2(4000), -- 내용
    rev_alive number, -- 삭제유무
    rev_score number, -- 평점
    rev_locname varchar2(120) references loc(loc_name) -- 관광이름FK
);
CREATE SEQUENCE rev_num;

select * from mem;
insert into mem(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename, mem_point, mem_alive, mem_cdate, mem_ddate) 
values('조규민', '1111', '010-1111-1111', '조규민', '서울', '없음', 100, 1, '2019-08-07', null);
insert into mem(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename, mem_point, mem_alive, mem_cdate, mem_ddate) 
values('김세종', '2222', '010-2222-2222', '김세종', '서울', '없음', 100, 1, '2019-08-07', null);
insert into mem(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename, mem_point, mem_alive, mem_cdate, mem_ddate) 
values('김승수', '3333', '010-3333-3333', '김승수', '서울', '없음', 100, 1, '2019-08-07', null);
insert into mem(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename, mem_point, mem_alive, mem_cdate, mem_ddate) 
values('박예원', '4444', '010-4444-4444', '박예원', '서울', '없음', 100, 1, '2019-08-07', null);

select * from regname;
insert into regname(regname, regNameEng, regGmt, regflight) values ('뉴욕', 'new york', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('하와이', 'hawaii', '-10', 9);
insert into regname(regname, regNameEng, regGmt, regflight) values ('캘리포니아', 'califonia', '-8', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('델라웨어', 'Delaware', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('펜실베이니아', 'Pennsylvania', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('뉴저지', 'New Jersey', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('조지아', 'Georgia', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('코네티컷', 'Connecticut', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('매사추세츠', 'Massachusetts', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('메릴랜드', 'Maryland', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('사우스캐롤라이나', 'South Carolina', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('뉴햄프셔', 'New Hampshire', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('버지니아', 'Virginia', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('노스캐롤라이나주', 'North Carolina', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('로드아일랜드', 'Rhode Island', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('버몬트', 'Vermont', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('켄터키', 'Kentucky', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('테네시', 'Tennessee', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('오하이오', 'Ohio', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('루이지애나', 'Louisiana', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('인디애나', 'Indiana', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('미시시피', 'Mississippi', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('일리노이', 'Illinois', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('앨라배마', 'Alabama', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('메인', 'Maine', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('미주리', 'Missouri', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('아칸소', 'Arkansas', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('미시간', 'Michigan', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('플로리다', 'Florida', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('텍사스', 'Texas', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('아이오와', 'Iowa', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('위스콘신', 'Wisconsin', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('미네소타', 'Minnesota', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('오리건', 'Oregon', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('캔자스', 'Kansas', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('웨스트버지니아', 'West Virginia	', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('네바다', 'Nevada', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('네브래스카', 'Nebraska', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('콜로라도', 'Colorado', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('노스다코타', 'North Dakota', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('사우스다코타', '	South Dakota', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('몬태나', '	Montana', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('워싱턴', 'Washington', '-5', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('아이다호', '	Idaho', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('와이오밍', 'Wyoming', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('유타', 'Utah', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('오클라호마', 'Oklahoma', '-6', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('뉴멕시코', 'New Mexico', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('애리조나', 'Arizona', '-7', 15);
insert into regname(regname, regNameEng, regGmt, regflight) values ('알래스카', 'Alaska', '-8', 15);

select * from reg;
insert into reg(reg_num, reg_name, reg_subject, reg_contents, reg_filename) values(reg_num.NEXTVAL, '뉴욕', '역사와 문화', '미국 최대의 항구 도시, 미국을 대표하는 대도시 뉴욕! 미국 독립 13주State 중 하나인 뉴욕에 속하며 뉴욕 주와 구분하여 뉴욕 시New York City, NYC라고도 부른다. 1524년 이탈리아의 항해사 지오반니 베라자노에 의해 처음 발견된 이후 이민자들의 이주 및 무역을 통해 꾸준히 발전을 이루었다. 1664년 영국에 의해 점령되며 당시 영국의 왕 찰스 2세의 동생 요크York 공에게 통치권이 넘어가며 뉴욕New York이라는 이름을 얻었다. 빅 애플Big Apple, 세계의 수도The Capital Of The World, 잠들지 않는 도시The City That Never Sleeps 등 다양한 별명도 가지고 있는 곳! 1790년까지 미국의 수도였으며 현재는 미국에서 인구가 가장 많은 도시이자 각종 문화와 경제의 중심! 세계의 수도라는 명성에 걸맞게 전 세계의 금융, 무역, 사회, 예술, 미디어, 기술 등 수많은 분야의 심장과도 같은 곳이라 해도 과언이 아니다. 1946년 국제 연합(UN)의 본부가 세 워지며 세계의 정치까지 집결되는 도시가 되었다. 자유의 여신상을 비롯하여 엠파이어 스테이트 빌딩, 센트럴 파크, 메트로폴리탄 박물관, 타임스 스퀘어 등 누구나 이름만 들으면 알만한 수많은 랜드 마크가 위치하고 있다. 때문에 세계의 여행자에게 꼭 한 번 방문하고 싶은 도시, 꿈의 도시로 꼽히며 해마다 수천만 명의 관광객을 맞이하고 있다.', '뉴욕.jpg');
insert into reg(reg_num, reg_name, reg_subject, reg_contents, reg_filename) values(reg_num.NEXTVAL, '뉴욕', '지리', '대서양과 바로 이어지는 허드슨 강을 끼고 있어 접근성이 뛰어나 예부터 무역이 활발히 이루어진 뉴욕. 시의 전체 구역은 맨해튼, 스태튼 아일랜드, 롱 아일랜드 세 섬으로 걸쳐 있으며 뉴욕 시는 다시 다섯 개의 자치구Borough로 나뉜다. 맨해튼Manhattan, 브루클린Brooklyn, 퀸즈Queens, 브롱스 Bronx, 스태튼 아일랜드Staten Island로 이루어져 있으며 각 자치구가 특유의 색을 유지하고 있다.', '자유의 여신상.jpg');
insert into reg(reg_num, reg_name, reg_subject, reg_contents, reg_filename) values(reg_num.NEXTVAL, '뉴욕', '계절별 날씨', '뉴욕은 대륙성 기후 지역으로 대체로 우리 나라와 비슷하며 봄, 여름, 가을, 겨울 사계절이 모두 있다. 다만 우리 나라에 비해 여름과 겨울이 좀 더 긴 편이다. 특히 겨울이 상대적으로 춥게 느껴지는데, 높은 빌딩 사이로 부는 바람의 영향으로 체감 기온이 실제 기온보다 낮다. 그렇기 때문에 겨울에 뉴욕을 방문할 때에는 옷을 따뜻하게 여러 겹 껴입는 것을 추천. 5월까지도 추운 기운이 남아있는 날이 많으므로 주의하자. 
여름은 7~8월의 최고 기온은 30도에 육박하며 35도까지 오르는 날도 더러 있다. 대도시인만큼 거리의 콘크리트에 복사된 열 때문에 체감기온은 더욱 올라간다. 하지만 우리 나라에 비해 습도가 낮아 불쾌지수가 적어 강한 햇볕을 쬐는 것에 거부감이 없다면 돌아다니는 데에 크게 불편하지 않을 수 있다. 물론 자외선 차단제로 피부를 보호하는 것은 필수! 계절에 따라 강수량의 편차가 크지 않다는 점이 특징이며 장마가 따로 없지만 때때로 폭우 같은 소나기가 내리는 경우가 있다. 만약을 대비해 비상시 사용할 작은 우산을 챙겨 다니자.', '공원.jpg');
insert into reg(reg_num, reg_name, reg_subject, reg_contents, reg_filename) values(reg_num.NEXTVAL, '뉴욕', '휴일과 축제', '뉴욕은 주 단위로 지정된 휴일이 따로 있지 않으며 미국 법적 공휴일에만 쉰다. 국경일이 주말일 경우 날짜를 옮겨 쉬거나 주중의 휴일을 주말이나 주초와 나란히 붙여 쉬는 Floationg Holiday로 지정하는 경우도 있다. 예를 들어 7월 4일 독립 기념일이 토요일이면 금요일이 휴일, 일요일이면 월요일이 휴일이다. 미국 전역에서는 독립기념일과 추수감사절, 성탄절을 기점으로 대규모의 세일이 진행된다. 특히 현충일의 경우 여름 세일의 시작을 알리는 신호탄이기도 하다. ', '맥도날드.jpg');

select * from loc;
insert into loc(loc_name, loc_contents, loc_filename, loc_regname) values('자유의 여신상', '명불허전 관광명소. 자유의 나라 미국을 의미하는 상징물로 리버티 섬에 위풍당당하게 서있다. 미국 독립 100주년을 기념해 프랑스에서 선물로 주었으며, 작가는 프레데릭 바르톨디, 설계는 에펠 탑을 설계한 귀스타브 에펠이 맡았다. 오른손에는 횃불을, 왼손에는 독립선언서를 들고 있는 이 여인의 내부에는 엘리베이터가, 왕관에는 전망대가 설치되어 있으며, 받침대 안은 박물관으로 운영되고 있다. 내부로 들어갈 수 있는 인원이 시간당 제한되고 있기 때문에 사전예약은 필수이다. 최소 2~3개월 전에 인터넷 예약을 통해서만 내부로 들어갈 수 있다. 그 크기가 어마어마해 섬 안에서는 사진을 제대로 찍을 수 없으니 페리를 타고 올 때 미리 찍어두자.', '자유의 여신상.jpg', '뉴욕');
insert into loc(loc_name, loc_contents, loc_filename, loc_regname) values('타임스퀘어', '뉴욕 여행의 필수 스팟이자 뉴욕의 랜드마크! 뉴욕 타임스 본사가 이곳으로 이전하면서 타임 스퀘어라는 이름을 갖게 됐다. 삼각형 모양의 광장에 화려한 네온사인과 광고가 펼쳐지며 여행자를 매혹시킨다.세계에서 가장 비싼 광고비가 드는 곳으로도 유명하며 현재 삼성, LG 광고가 나오고 있다. 여행자가 가장 많이 몰리는 곳이기 때문에 비지터 센터와 각종 기념품 숍 등 다양한 볼거리가 가득하다. 광장과 연결되어있는 브로드웨이를 따라 위치한 극장에서 공연을 관람할 수도 있어 24시간 화려한 뉴욕이 느껴지는 곳이다.', '타임스퀘어.jpg', '뉴욕');
insert into loc(loc_name, loc_contents, loc_filename, loc_regname) values('브로드웨이', '런던 웨스트엔드와 함께 세계 뮤지컬을 이끌고 있는 브로드웨이! 세계적 수준의 공연들이 화려하게 펼쳐지는 곳이다. 타임스 스퀘어 주변으로 50개에 달하는 극장이 있으며 대형 뮤지컬뿐만 아니라 소극장 공연도 있다. 대부분 공연의 티켓이 빨리 매진되기 때문에 최대한 이른 시간에 가서 예매해야 한다. 뮤지컬 극장에 가서 하는 방법과 타임스 스퀘어에 있는 티켓부스를 이용하는 방법이 있으며 인터넷 예매도 가능하니 참고하자.', '브로드웨이.jpg', '뉴욕');
insert into loc(loc_name, loc_contents, loc_filename, loc_regname) values('뉴욕 증권거래소', '월 스트리트의 메인 스팟이라고 볼 수 있는 뉴욕 증권거래소. 수많은 영화에도 등장했었던 이곳은 세계 최대 규모의 증권거래소로 위풍당당한 건물과 커다란 성조기가 특징이다. 내부 출입은 금지되어 있지만 뉴욕을 대표하는 장소인 만큼 외부 기념 사진을 찍고 가자.', '뉴욕 증권거래소.jpg', '뉴욕');

select * from rev;
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-01-19', '조규민', '캐꿀잼', '1', 5, '자유의 여신상');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-02-02', '조규민', '캐존잼', '1', 4, '자유의 여신상');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-03-03', '조규민', '좋아요', '1', 3, '자유의 여신상');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-03-27', '조규민', '재밌어', '1', 2, '자유의 여신상');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-03-30', '조규민', '행복해', '1', 1, '자유의 여신상');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-03-06', '조규민', '캐꿀잼', '1', 5, '타임스퀘어');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-04-07', '조규민', '캐존잼', '1', 5, '타임스퀘어');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-04-08', '조규민', '좋아요', '1', 5, '타임스퀘어');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-04-09', '조규민', '재밌어', '1', 5, '타임스퀘어');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-04-13', '조규민', '행복해', '1', 1, '타임스퀘어');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-05-01', '조규민', '캐꿀잼', '1', 3, '브로드웨이');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-05-07', '조규민', '캐존잼', '1', 3, '브로드웨이');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-05-17', '조규민', '좋아요', '1', 3, '브로드웨이');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-06-21', '조규민', '재밌어', '1', 3, '브로드웨이');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-07-10', '조규민', '행복해', '1', 1, '브로드웨이');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-07-13', '조규민', '캐꿀잼', '1', 1, '뉴욕 증권거래소');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-07-21', '조규민', '캐존잼', '1', 1, '뉴욕 증권거래소');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-08-01', '조규민', '좋아요', '1', 1, '뉴욕 증권거래소');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-08-07', '조규민', '재밌어', '1', 2, '뉴욕 증권거래소');
insert into rev(rev_num, rev_date, rev_writer, rev_contents, rev_alive, rev_score, rev_locname) values(REV_NUM.nextval, '2019-08-09', '조규민', '행복해', '1', 1, '뉴욕 증권거래소');

commit;