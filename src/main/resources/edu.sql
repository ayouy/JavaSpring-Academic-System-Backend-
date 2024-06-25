-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: edu
-- ------------------------------------------------------
-- Server version	5.7.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--
DROP DATABASE IF EXISTS `edu`;
CREATE DATABASE edu;
use edu;
DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
                         `name` varchar(32) NOT NULL COMMENT '名称',
                         `code` varchar(16) NOT NULL COMMENT '班级编号',
                         `speciality_id` int(11) DEFAULT '-1' COMMENT '专业id',
                         `remark` varchar(256) DEFAULT NULL COMMENT '辅助描述',
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`speciality_id`) REFERENCES `speciality` (`id`) ON DELETE CASCADE,
                         UNIQUE KEY `class_un` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='班级(比如2020级软件工程300班)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'2020300软件工程','2020300',1,NULL),(2,'2020400软件工程T','2020400T',3,NULL),(3,'2021310软件工程','2021310',2,NULL),(4,'2021410软件工程T','2021410T',4,NULL);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `college` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院id',
                           `name` varchar(32) NOT NULL COMMENT '名称',
                           `remark` varchar(256) DEFAULT NULL COMMENT '辅助描述',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='学院(包含教师、班级)' ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'信息工程学院',NULL),(2,'软件学院',NULL);
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

INSERT INTO `college` (`name`, `remark`) VALUES
                                             ('电子工程学院', '新增学院'),
                                             ('机械工程学院', NULL);


--
-- Table structure for table `dean`
--

DROP TABLE IF EXISTS `dean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dean` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教务id',
                        `name` varchar(16) NOT NULL COMMENT '名字',
                        `password` varchar(32) NOT NULL DEFAULT '123456789' COMMENT '密码',
                        `code` varchar(16) NOT NULL COMMENT '工号(登录)',
                        `college_id` int(11) NOT NULL COMMENT '学院ID',
                        `remark` varchar(256) DEFAULT NULL COMMENT '辅助描述',
                        PRIMARY KEY (`id`),
                        FOREIGN KEY(`college_id`) REFERENCES `college`(`id`) ON DELETE CASCADE,
                        UNIQUE KEY `dean_un` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='教务(设置专业、编制班级、开设课程、组织选课等)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dean`
--

LOCK TABLES `dean` WRITE;
/*!40000 ALTER TABLE `dean` DISABLE KEYS */;
INSERT INTO `dean` VALUES (1,'信工教学秘书','123456789','234001',1,NULL),(2,'软件教学秘书','123456789','234002',2,NULL);
/*!40000 ALTER TABLE `dean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speciality`
--

DROP TABLE IF EXISTS `speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speciality` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id',
                              `name` varchar(32) NOT NULL COMMENT '名称',
                              `grade` varchar(15) NOT NULL COMMENT '年级',
                              `code` varchar(16) NOT NULL COMMENT '学科代码',
                              `college_id` int(11) DEFAULT '-1' COMMENT '学院id',
                              `remark` varchar(256) DEFAULT NULL COMMENT '辅助描述',
                              PRIMARY KEY (`id`),
                              CONSTRAINT `fk_speciality_fk1` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='专业(比如2020级特色软件工程)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speciality`
--

LOCK TABLES `speciality` WRITE;
/*!40000 ALTER TABLE `speciality` DISABLE KEYS */;
INSERT INTO `speciality` VALUES (1,'2020级软件工程',2020,'070401',1,NULL),(2,'2021级软件工程',2021,'070401',1,NULL),(3,'2020级特色软件工程',2020,'070401',2,NULL),(4,'2021级特色软件工程',2021,'070401',2,NULL);
/*!40000 ALTER TABLE `speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
                           `name` varchar(32) NOT NULL COMMENT '姓名',
                           `password` varchar(64) NOT NULL DEFAULT '123456789' COMMENT '密码',
                           `code` varchar(32) NOT NULL COMMENT '学号(登录)',
                           `class_id` int(11) DEFAULT '-1' COMMENT '班级id',
                           `remark` varchar(256) DEFAULT NULL COMMENT '辅助描述',
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`class_id`) REFERENCES `class`(`id`) ON DELETE CASCADE,
                           UNIQUE KEY `student_un` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='学生(班级决定了其专业名称、年级、学院)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'学生1','123456789','202030001',-1,'1'),(2,'学生2','123456789','202030002',-1,'1'),(3,'学生3','123456789','202040001',-1,'3'),(4,'学生4','123456789','202040002',-1,'3'),(5,'学生5','123456789','202131001',-1,'2'),(6,'学生6','123456789','202131002',-1,'2'),(7,'学生7','123456789','202141001',-1,'4'),(8,'学生8','123456789','202141002',-1,'4');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师id',
                           `name` varchar(32) NOT NULL COMMENT '姓名',
                           `password` varchar(64) NOT NULL DEFAULT '123456789' COMMENT '密码',
                           `code` varchar(32) NOT NULL COMMENT '工号(登录)',
                           `college_id` int(11) DEFAULT '-1' COMMENT '学院id',
                           `remark` varchar(256) DEFAULT NULL COMMENT '辅助描述',
                           PRIMARY KEY (id),
                           FOREIGN KEY (college_id) REFERENCES college(id) ON DELETE CASCADE,
                           UNIQUE KEY `teacher_un` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='教师(划归某个教学单位)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'张三','123456789','234010',1,NULL),(2,'李四','123456789','234011',1,NULL),(3,'王五','123456789','234012',2,NULL),(4,'赵六','123456789','234013',2,NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

INSERT INTO `teacher` (`name`, `password`, `code`, `college_id`, `remark`) VALUES
                                                                               ('陈老师', '123456789', '234014', (SELECT id FROM college WHERE name = '电子工程学院'), NULL),
                                                                               ('李老师', '123456789', '234015', (SELECT id FROM college WHERE name = '机械工程学院'), NULL);


CREATE TABLE `course`
(
    `id`      int AUTO_INCREMENT
        PRIMARY KEY,
    `name`    varchar(100)  NOT NULL COMMENT '课程名称',
    `code`    varchar(50)   NOT NULL COMMENT '课程代码',
    `term_id` int           NOT NULL COMMENT '所属学期ID',
    `credit`  decimal(3, 1) NOT NULL COMMENT '学分',
    `college_id` int(11) 		NOT NULL COMMENT '学院ID',
    CONSTRAINT course_ibfk_1 FOREIGN KEY (term_id) REFERENCES term (id) ON DELETE CASCADE,
    CONSTRAINT cousre_ibfk_2 FOREIGN KEY(college_id) REFERENCES college (id) ON DELETE CASCADE
)
    COMMENT '课程表' CHARSET = utf8mb4;

CREATE INDEX term_id
    ON course (term_id);
INSERT INTO `course` (`name`,`code`,`term_id`,`credit`,`college_id`) values('软件工程', 'SE101',1,4.0,1);
INSERT INTO `course` (`name`,`code`,`term_id`,`credit`,`college_id`) values('操作系统', 'CS302',1,3.0,2);


--
-- Dumping routines for database 'edu'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-16 15:23:31
DROP TABLE  IF EXISTS `term` ;
CREATE TABLE `term` (
                        `id` INT(11) NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(100) NOT NULL COMMENT '学期名称，例如2020-2021学年秋季学期',
                        `start_date` DATE NOT NULL COMMENT '开始日期',
                        `end_date` DATE NOT NULL COMMENT '结束日期',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学期表';


INSERT INTO `term` (`name`, `start_date`, `end_date`) VALUES ('2020-2021学年秋季学期', '2020-09-01', '2021-01-15');
INSERT INTO `term` (`name`, `start_date`, `end_date`) VALUES ('2020-2021学年春季学期', '2021-02-01', '2021-06-15');


DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course` (
                                  `id` INT(11) NOT NULL AUTO_INCREMENT,
                                  `teacher_id` INT(11) NOT NULL COMMENT '教师ID',
                                  `course_id` INT(11) NOT NULL COMMENT '课程ID',
                                  FOREIGN KEY (`teacher_id`) REFERENCES `teacher`(`id`) ON DELETE CASCADE,
                                  FOREIGN KEY (`course_id`) REFERENCES `course`(`id`) ON DELETE CASCADE,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程与教师关联表';

INSERT INTO `teacher_course` (`teacher_id`, `course_id`) VALUES (1, 1);
INSERT INTO `teacher_course` (`teacher_id`, `course_id`) VALUES (1, 2);


DROP TABLE IF EXISTS `student_course_score`;
CREATE TABLE `student_course_score` (
                                        `id` INT(11) NOT NULL AUTO_INCREMENT,
                                        `student_id` INT(11) NOT NULL COMMENT '学生ID',
                                        `course_id` INT(11) NOT NULL COMMENT '课程ID',
                                        `score` DECIMAL(5,2) NOT NULL COMMENT '成绩',
                                        FOREIGN KEY (`student_id`) REFERENCES `student`(`id`) ON DELETE CASCADE,
                                        FOREIGN KEY (`course_id`) REFERENCES `course`(`id`) ON DELETE CASCADE,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生课程成绩表';


INSERT INTO `student_course_score` (`student_id`, `course_id`, `score`) VALUES (1, 1, 85.5);
INSERT INTO `student_course_score` (`student_id`, `course_id`, `score`) VALUES (1, 2, 90.0);

DROP TABLE IF EXISTS `class_course`;
CREATE TABLE `class_course` (
                                `id` INT(11) NOT NULL AUTO_INCREMENT,
                                `class_id` INT(11) NOT NULL COMMENT '班级ID',
                                `course_id` INT(11) NOT NULL COMMENT '课程ID',
                                FOREIGN KEY (`class_id`) REFERENCES `class`(`id`) ON DELETE CASCADE,
                                FOREIGN KEY (`course_id`) REFERENCES `course`(`id`) ON DELETE CASCADE,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程与班级关联表';

INSERT INTO `class_course` (`class_id`, `course_id`) VALUES (1, 1);
INSERT INTO `class_course` (`class_id`, `course_id`) VALUES (1, 2);
INSERT INTO `class_course` (`class_id`, `course_id`) VALUES
                                                         (2, 1),
                                                         (2, 2);






INSERT INTO `speciality` (`name`, `grade`, `code`, `college_id`, `remark`) VALUES
                                                                               ('2022级电子工程', 2022, '070501', (SELECT id FROM college WHERE name = '电子工程学院'), '新增专业'),
                                                                               ('2022级机械工程', 2022, '080401', (SELECT id FROM college WHERE name = '机械工程学院'), NULL);

INSERT INTO `dean` (`name`, `password`, `code`, `college_id`, `remark`) VALUES
                                                                            ('电子学院教学秘书', '123456789', '234003', (SELECT id FROM college WHERE name = '电子工程学院'), NULL),
                                                                            ('机械学院教学秘书', '123456789', '234004', (SELECT id FROM college WHERE name = '机械工程学院'), NULL);

INSERT INTO `class` (`name`, `code`, `speciality_id`, `remark`) VALUES
                                                                    ('2022300软件工程', '2022300', (SELECT id FROM speciality WHERE name = '2022级电子工程'), '新增班级'),
                                                                    ('2022400软件工程T', '2022400T', (SELECT id FROM speciality WHERE name = '2022级机械工程'), '新增班级');

INSERT INTO `term` (`name`, `start_date`, `end_date`) VALUES
                                                          ('2021-2022学年秋季学期', '2021-09-01', '2022-01-15'),
                                                          ('2021-2022学年春季学期', '2022-02-01', '2022-06-15'),
                                                          ('2022-2023学年秋季学期', '2022-09-01', '2023-01-15'),
                                                          ('2022-2023学年春季学期', '2022-02-01', '2023-06-15');


INSERT INTO `course` (`name`, `code`, `term_id`, `credit`, `college_id`) VALUES
                                                                             ('电子技术', 'ET101', 1, 4.5, (SELECT id FROM college WHERE name = '电子工程学院')),
                                                                             ('机械原理', 'ME101', 1, 4.0, (SELECT id FROM college WHERE name = '机械工程学院')),
                                                                             ('网络安全', 'CS501', 3, 3.5, (SELECT id FROM college WHERE name = '信息工程学院')),
                                                                             ('机器学习', 'ML101', 3, 4.0, (SELECT id FROM college WHERE name = '软件学院'));


INSERT INTO `student` (`name`, `password`, `code`, `class_id`, `remark`) VALUES
                                                                             ('学生A', '123456789', '202230001', 1, '2022级学生'),
                                                                             ('学生B', '123456789', '202240001', 2, '2022级学生');

INSERT INTO `teacher_course` (`teacher_id`, `course_id`) VALUES
                                                             (1, 1),
                                                             (2, 2);

INSERT INTO `student_course_score` (`student_id`, `course_id`, `score`) VALUES
                                                                            (1, 1, 88.0),
                                                                            (2, 2, 92.5);





INSERT INTO `course` (`name`, `code`, `term_id`, `credit`, `college_id`) VALUES ('软件工程2', 'SE1012', 1, 4.0, 1);
INSERT INTO `course` (`name`, `code`, `term_id`, `credit`, `college_id`) VALUES ('操作系统2', 'CS3022', 1, 3.0, 2);

-- Assuming the above courses are going to be given IDs 3 and 4, respectively.


INSERT INTO `teacher_course` (`teacher_id`, `course_id`) VALUES (1, 3);
INSERT INTO `teacher_course` (`teacher_id`, `course_id`) VALUES (1, 4);

INSERT INTO `college` (`name`, `remark`) VALUES
                                             ('学院1', '备注1'),
                                             ('学院2', '备注2'),
                                             ('学院3', '备注3'),
                                             ('学院4', '备注4'),
                                             ('学院5', '备注5'),
                                             ('学院6', '备注6'),
                                             ('学院7', '备注7'),
                                             ('学院8', '备注8'),
                                             ('学院9', '备注9'),
                                             ('学院10', '备注10');

INSERT INTO `speciality` (`name`, `grade`, `code`, `college_id`, `remark`) VALUES
                                                                               ('专业1', 2023, '070501', 1, '备注1'),
                                                                               ('专业2', 2023, '080401', 2, '备注2'),
                                                                               ('专业3', 2023, '090601', 3, '备注3'),
                                                                               ('专业4', 2023, '100701', 4, '备注4'),
                                                                               ('专业5', 2023, '110801', 5, '备注5'),
                                                                               ('专业6', 2023, '120901', 6, '备注6'),
                                                                               ('专业7', 2023, '131001', 7, '备注7'),
                                                                               ('专业8', 2023, '141101', 8, '备注8'),
                                                                               ('专业9', 2023, '151201', 9, '备注9'),
                                                                               ('专业10', 2023, '161301', 10, '备注10');

INSERT INTO `dean` (`name`, `password`, `code`, `college_id`, `remark`) VALUES
                                                                            ('教务1', '123456789', '0234001', 1, '备注1'),
                                                                            ('教务2', '123456789', '0234002', 2, '备注2'),
                                                                            ('教务3', '123456789', '0234003', 3, '备注3'),
                                                                            ('教务4', '123456789', '0234004', 4, '备注4'),
                                                                            ('教务5', '123456789', '0234005', 5, '备注5'),
                                                                            ('教务6', '123456789', '0234006', 6, '备注6'),
                                                                            ('教务7', '123456789', '0234007', 7, '备注7'),
                                                                            ('教务8', '123456789', '0234008', 8, '备注8'),
                                                                            ('教务9', '123456789', '0234009', 9, '备注9'),
                                                                            ('教务10', '123456789', '0234010', 10, '备注10');

INSERT INTO `class` (`name`, `code`, `speciality_id`, `remark`) VALUES
                                                                    ('班级1', '1001', 1, '备注1'),
                                                                    ('班级2', '1002', 2, '备注2'),
                                                                    ('班级3', '1003', 3, '备注3'),
                                                                    ('班级4', '1004', 4, '备注4'),
                                                                    ('班级5', '1005', 5, '备注5'),
                                                                    ('班级6', '1006', 6, '备注6'),
                                                                    ('班级7', '1007', 7, '备注7'),
                                                                    ('班级8', '1008', 8, '备注8'),
                                                                    ('班级9', '1009', 9, '备注9'),
                                                                    ('班级10', '1010', 10, '备注10');

