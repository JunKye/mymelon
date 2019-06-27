--mediagroup.sql
-- 미디어 그룹 테이블
create table mediagroup(
	mediagroupno	number			not null primary key, --그룹번
	title 			varchar(255)	not null --그룸제목
);

--행추가 
그릅번호 : 그릅번호 최대값+1
그룹제목 : '2018년 댄스음악';

INSERT INTO MEDIAGROUP(mediagroupno, title)
VALUES((SELECT NVL(max(mediagroupno),0)+1 FROM mediagroup), '2018년 댄스음악');

--목록 
select mediagroupno, title
from mediagroup
order by mediagroupno desc;

DROP TABLE mediagroup;