drop table if exists `student`;

CREATE TABLE `student`
(
    `id`           bigint unsigned        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `student_name` varchar(16)            NOT NULL DEFAULT '' COMMENT '学生姓名',
    `grade`        smallint unsigned      NOT NULL DEFAULT '0' COMMENT '年级',
    `score`        decimal(5, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '分数',
    `birthday`     date                   NOT NULL DEFAULT '1970-01-01' COMMENT '出生日期',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='学生表';

INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (1, '张三', 1, 98.20, '2014-01-01');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (2, '李四', 2, 83.00, '2013-03-03');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (3, '王二', 3, 74.00, '2012-06-05');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (4, '麻子', 4, 59.00, '2010-12-12');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (5, '张老五', 1, 28.00, '2014-08-31');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (6, '李蛋', 2, 100.00, '2013-01-02');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (7, '张翠花', 3, 60.00, '2012-06-01');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (8, '赵铁牛', 4, 96.00, '2011-05-05');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (9, '赵曦', 1, 32.00, '2014-04-02');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (10, '张宇', 2, 92.00, '2013-05-07');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (11, '左宗', 3, 87.00, '2012-08-07');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (12, '张欣欣', 4, 94.00, '2011-01-04');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (13, '张小花', 1, 99.00, '2014-06-06');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (14, '钱多多', 2, 87.00, '2013-03-09');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (15, '孙石凯', 3, 97.00, '2011-09-03');
INSERT INTO `interview`.`student` (`id`, `student_name`, `grade`, `score`, `birthday`)
VALUES (16, '周富贵', 4, 23.00, '2011-03-03');

SELECT a.`id`,
       a.`student_name`,
       a.`grade`,
       reverse(a.`student_name`)                                           AS student_name,
       date_format(a.`birthday`, '%Y%m%d')                                 AS birthday,
       concat(a.`student_name`, '--', a.`birthday`)                        AS student_name,
       concat(a.`student_name`, '--', date_format(a.`birthday`, '%Y%m%d')) AS student_name,
       (a.`grade` + a.`grade` + 1)                                         AS grade
FROM student AS a;

# id	student_name	grade	student_name(1)	birthday	student_name(2)	    student_name(3)	    grade(1)
# 1	    张三	            1	    三张	            20140101	张三--2014-01-01	    张三--20140101	    3
# 2	    李四	            2	    四李	            20130303	李四--2013-03-03	    李四--20130303	    5
# 3	    王二	            3	    二王	            20120605	王二--2012-06-05	    王二--20120605	    7
# 4	    麻子	            4	    子麻	            20101212	麻子--2010-12-12	    麻子--20101212	    9
# 5	    张老五	        1	    五老张	        20140831	张老五--2014-08-31	张老五--20140831	    3
# 6	    李蛋	            2	    蛋李	            20130102	李蛋--2013-01-02	    李蛋--20130102	    5
# 7	    张翠花	        3	    花翠张	        20120601	张翠花--2012-06-01	张翠花--20120601	    7
# 8	    赵铁牛	        4	    牛铁赵	        20110505	赵铁牛--2011-05-05	赵铁牛--20110505	    9
# 9	    赵曦	            1	    曦赵	            20140402	赵曦--2014-04-02	    赵曦--20140402	    3
# 10	张宇	            2	    宇张	            20130507	张宇--2013-05-07	    张宇--20130507	    5
# 11	左宗	            3	    宗左	            20120807	左宗--2012-08-07	    左宗--20120807	    7
# 12	张欣欣	        4	    欣欣张	        20110104	张欣欣--2011-01-04	张欣欣--20110104	    9
# 13	张小花	        1	    花小张	        20140606	张小花--2014-06-06	张小花--20140606	    3
# 14	钱多多	        2	    多多钱	        20130309	钱多多--2013-03-09	钱多多--20130309	    5
# 15	孙石凯	        3	    凯石孙	        20110903	孙石凯--2011-09-03	孙石凯--20110903	    7
# 16	周富贵	        4	    贵富周	        20110303	周富贵--2011-03-03	周富贵--20110303	    9

SELECT DISTINCT *
FROM student AS a;

# id	student_name	grade	score	birthday
# 1	    张三	            1	    98.20	2014-01-01
# 2	    李四	            2	    83.00	2013-03-03
# 3	    王二      	    3	    74.00	2012-06-05
# 4	    麻子	            4	    59.00	2010-12-12
# 5	    张老五	        1	    28.00	2014-08-31
# 6	    李蛋	            2	    100.00	2013-01-02
# 7	    张翠花	        3	    60.00	2012-06-01
# 8	    赵铁牛	        4	    96.00	2011-05-05
# 9	    赵曦	            1	    32.00	2014-04-02
# 10	张宇	            2	    92.00	2013-05-07
# 11	左宗	            3	    87.00	2012-08-07
# 12	张欣欣	        4	    94.00	2011-01-04
# 13	张小花	        1	    99.00	2014-06-06
# 14	钱多多	        2	    87.00	2013-03-09
# 15	孙石凯	        3	    97.00	2011-09-03
# 16	周富贵	        4	    23.00	2011-03-03

SELECT *
FROM student AS a
WHERE a.`id` >= 1
  AND a.`grade` != a.`id`
  AND a.`score` < 100
  AND a.`score` IS NOT NULL
  AND a.`birthday` IS NOT NULL
  AND a.`id` IN (2, 4, 6, 8, 10, 12, 14, 16)
  AND a.`grade` NOT IN (5, 6, 7, 8)
  AND a.`student_name` LIKE '%张%'
  AND a.`student_name` LIKE '张%'
  AND a.`student_name` NOT LIKE '%赵日天%'
  AND a.`student_name` NOT LIKE '%小花'
  AND MONTH(a.`birthday`) <= DAYOFMONTH(a.`birthday`);

# id	student_name	grade	score	birthday
# 10	张宇	            2	    92.00	2013-05-07
# 12	张欣欣	        4	    94.00	2011-01-04

SELECT a.`grade`,
       LEFT(a.`student_name`, 1) AS `student_name`,
       YEAR(a.`birthday`)        AS `birthday`,
       min(a.`score`)            AS `score`,
       avg(a.`score`)            AS `score`,
       sum(a.`score`)            AS `score`
FROM student AS a
WHERE 1 = 1
GROUP BY a.`grade`, LEFT(a.`student_name`, 1), YEAR(a.`birthday`);

# grade	student_name	birthday	score	score(1)	score(2)
# 1	    张	            2014	    28.00	75.066667	225.20
# 2	    李	            2013	    83.00	91.500000	183.00
# 3	    王	            2012	    74.00	74.000000	74.00
# 4	    麻	            2010	    59.00	59.000000	59.00
# 3	    张	            2012	    60.00	60.000000	60.00
# 4	    赵	            2011	    96.00	96.000000	96.00
# 1	    赵	            2014	    32.00	32.000000	32.00
# 2	    张	            2013	    92.00	92.000000	92.00
# 3	    左	            2012	    87.00	87.000000	87.00
# 4	    张	            2011	    94.00	94.000000	94.00
# 2	    钱	            2013	    87.00	87.000000	87.00
# 3	    孙	            2011	    97.00	97.000000	97.00
# 4	    周	            2011	    23.00	23.000000	23.00

SELECT *
FROM student AS a
WHERE 1 = 1
ORDER BY a.`grade` ASC,
         a.`score` DESC,
         field(a.`id`, 1, 3, 5, 7) DESC;

# id	student_name	grade	score	birthday
# 13	张小花	        1	    99.00	2014-06-06
# 1	    张三	            1	    98.20	2014-01-01
# 9	    赵曦	            1	    32.00	2014-04-02
# 5	    张老五	        1	    28.00	2014-08-31
# 6	    李蛋          	2	    100.00	2013-01-02
# 10	张宇	            2	    92.00	2013-05-07
# 14	钱多多	        2	    87.00	2013-03-09
# 2	    李四      	    2	    83.00	2013-03-03
# 15	孙石凯	        3	    97.00	2011-09-03
# 11	左宗	            3	    87.00	2012-08-07
# 3	    王二	            3	    74.00	2012-06-05
# 7	    张翠花	        3	    60.00	2012-06-01
# 8	    赵铁牛	        4	    96.00	2011-05-05
# 12	张欣欣	        4	    94.00	2011-01-04
# 4	    麻子	            4	    59.00	2010-12-12
# 16	周富贵	        4	    23.00	2011-03-03

select *
from student as a
where 1 = 1
limit 0, 5;

# id	student_name	grade	score	birthday
# 1	    张三	            1	    98.20	2014-01-01
# 2	    李四	            2	    83.00	2013-03-03
# 3 	王二	            3	    74.00	2012-06-05
# 4	    麻子	            4	    59.00	2010-12-12
# 5	    张老五	        1	    28.00	2014-08-31

SELECT avg(a.`score`)        AS `score`,
       min(a.`student_name`) AS `student_name`,
       min(a.`id`)           AS `id`,
       max(a.`birthday`)     AS `birthday`
FROM student AS a
WHERE 1 = 1
GROUP BY a.`grade`
HAVING AVG(a.`score`) >= 60
   AND MIN(a.`student_name`) LIKE '%张%'
   AND MIN(a.`id`) IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
   AND MAX(a.`birthday`) IS NOT NULL;

# score	    student_name	id	birthday
# 64.300000	张三	            1	2014-08-31
# 90.500000	张宇	            2	2013-05-07

DELETE
FROM student
WHERE id = 17;

INSERT INTO student (id, student_name, grade, score, birthday)
VALUES (17, '宋晓佳', 1, 65, now());

select *
from student
where id = 17;

# id	student_name	grade	score	birthday
# 17	宋晓佳	        1	    65.00	2021-06-26

UPDATE student AS a
SET a.`grade`        = 4,
    a.`student_name` = 'JAK2',
    a.`birthday`     = now()
WHERE a.id = 1;

select *
from student
where id = 1;

# id	student_name	grade	score	birthday
# 1	    JAK2	        4	    98.20	2021-06-26

SELECT a.`grade`,
       SUM(a.`score`)    AS score,
       MIN(a.`birthday`) AS birthday
FROM student AS a
WHERE 1 = 1
  AND a.`student_name` LIKE '张%'
GROUP BY a.`grade`
HAVING AVG(a.`score`) >= 60
ORDER BY a.`grade` ASC
LIMIT 0,1;

# grade	score	birthday
# 1	127.00	2014-06-06