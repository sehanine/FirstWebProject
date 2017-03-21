-- 1 create this first
CREATE TABLE vk_main(
  fesno          NUMBER,
  maintitle     VARCHAR2(100) CONSTRAINT vk_main_maintitle_nn NOT NULL,
  maincontent   CLOB,
  mainloc        VARCHAR2(100),
  fesdate        VARCHAR2(50),
  CONSTRAINT vk_main_fesno_pk PRIMARY KEY(fesno)
);
DROP TABLE vk_main CASCADE CONSTRAINTS ;
-- 2
CREATE TABLE vk_image_list(
  fesno     NUMBER,
  image     VARCHAR2(100),
  CONSTRAINT vk_image_list_fk FOREIGN KEY(fesno) REFERENCES vk_main(fesno)
);
DROP TABLE vk_image_list CASCADE CONSTRAINTS;

--3
CREATE TABLE vk_second_list(
  fesno NUMBER,
  second_list_title VARCHAR(20),
  second_list CLOB,
  CONSTRAINT vk_second_list_fk FOREIGN KEY(fesno) REFERENCES vk_main(fesno)
);

CREATE TABLE vk_third_list(
  fesno NUMBER,
  third_list_title VARCHAR(20),
  third_list CLOB,
  CONSTRAINT vk_third_list_fk FOREIGN KEY(fesno) REFERENCES vk_main(fesno)
);

SELECT * FROM vk_main;
SELECT * FROM vk_image_list;
SELECT * FROM vk_second_list;
SELECT * FROM vk_third_list;

SELECT fesno, maintitle FROM vk_main WHERE fesno = 69;
SELECT fesno, image FROM vk_image_list where fesno = 69;
SELECT fesno, second_list FROM vk_second_list where fesno = 69;


INSERT INTO vk_main(fesno, maintitle, maincontent, mainloc, fesdate)
    VALUES ('1', '한국행사', '한국행사 테스트', '서울 신촌', '2017-03-20 ~ 2017-05-16');

SELECT * FROM vk_main;

-- 테이블에 컬럼 추가
ALTER TABLE vk_image_list
ADD image1 VARCHAR2(100);

SELECT * FROM vk_image_list;

-- test inserting
INSERT INTO vk_image_list(fesno, image)
    VALUES(1, 'www.naver.com');

COMMIT;


UPDATE vk_image_list
SET image2 = 'www.naver.com/main'
WHERE fesno = 1;
