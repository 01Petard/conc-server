/*
 Navicat Premium Dump SQL

 Source Server         : 1.94.147.176_3306
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : 1.94.147.176:3306
 Source Schema         : classroom

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 02/04/2025 16:27:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `clazz_id` bigint NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `clazz_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '班级名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`clazz_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (1, '13英语1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (2, '14物流2', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (3, '14电气1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (4, '14网络1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (5, '14计科1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (6, '14计科3', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (7, '15软工2', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (8, '17物流1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (9, '17物流2', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (10, '17物流3', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (11, '17计应1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (12, '18信管1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (13, '18信管2', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (14, '18信管3', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (15, '18计科1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (16, '18软工UI1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (17, '19信管1', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);
INSERT INTO `clazz` VALUES (18, '19信管2', '2025-02-20 13:05:57', '2025-02-20 13:05:57', 0);

-- ----------------------------
-- Table structure for clazz_course
-- ----------------------------
DROP TABLE IF EXISTS `clazz_course`;
CREATE TABLE `clazz_course`  (
  `clazz_id` bigint NOT NULL COMMENT '班级ID',
  `cno` bigint NOT NULL COMMENT '课程编号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`clazz_id`, `cno`) USING BTREE,
  INDEX `fk_course_id`(`cno` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of clazz_course
-- ----------------------------
INSERT INTO `clazz_course` VALUES (1, 11, '2025-02-21 13:24:13', '2025-02-22 13:24:19', 0);
INSERT INTO `clazz_course` VALUES (2, 2, '2025-02-14 13:24:16', '2025-02-16 13:24:22', 0);

-- ----------------------------
-- Table structure for conc
-- ----------------------------
DROP TABLE IF EXISTS `conc`;
CREATE TABLE `conc`  (
  `conc_id` bigint NOT NULL AUTO_INCREMENT COMMENT '专注度数据id',
  `clazz_id` bigint NULL DEFAULT NULL COMMENT '班级id',
  `cno` bigint NULL DEFAULT NULL COMMENT '课程id',
  `sno` char(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`conc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of conc
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cno` bigint NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `cname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程名称',
  `cpno` bigint NULL DEFAULT NULL COMMENT '先修课课号',
  `ccredit` int NOT NULL COMMENT '课程学分',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '数据库OCP考证', NULL, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (2, 'C语言基础', 27, 8, NULL, NULL, 0);
INSERT INTO `course` VALUES (3, 'Linux操作系统', 13, 5, NULL, NULL, 0);
INSERT INTO `course` VALUES (4, 'C#程序设计', 2, 6, NULL, NULL, 0);
INSERT INTO `course` VALUES (5, 'DB_Design', NULL, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (6, '信息系统分析与设计', 10, 6, NULL, NULL, 0);
INSERT INTO `course` VALUES (7, 'JAVA程序设计', 2, 8, NULL, NULL, 0);
INSERT INTO `course` VALUES (8, '电子商务', 27, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (9, '实用英语', NULL, 6, NULL, NULL, 0);
INSERT INTO `course` VALUES (10, '数据结构', 2, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (11, '平板支撑', NULL, 2, NULL, NULL, 0);
INSERT INTO `course` VALUES (12, '邓小平理论', NULL, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (13, '操作系统', 2, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (14, '决胜股市终极课程', NULL, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (15, 'Web前端开发', 2, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (16, '电子商务', NULL, 3, NULL, NULL, 0);
INSERT INTO `course` VALUES (17, '应用统计学', 34, 2, NULL, NULL, 0);
INSERT INTO `course` VALUES (18, 'ERP原理', 39, 3, NULL, NULL, 0);
INSERT INTO `course` VALUES (19, '业务流程管理', 18, 2, NULL, NULL, 0);
INSERT INTO `course` VALUES (20, '信息管理学', NULL, 2, NULL, NULL, 0);
INSERT INTO `course` VALUES (27, '计算机基础', NULL, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (32, '多媒体技术', 27, 5, NULL, NULL, 0);
INSERT INTO `course` VALUES (34, '高等数学', NULL, 6, NULL, NULL, 0);
INSERT INTO `course` VALUES (39, '基础会计', NULL, 2, NULL, NULL, 0);
INSERT INTO `course` VALUES (45, '敏捷开发', 6, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (52, '财务会计', 39, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (53, '现代物流实操', NULL, 4, NULL, NULL, 0);
INSERT INTO `course` VALUES (54, 'DBA实战-数据库管理员之路', NULL, 3, NULL, NULL, 0);
INSERT INTO `course` VALUES (55, 'NoSQL数据库', NULL, 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sno` char(7) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '学生学号',
  `sname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学生姓名',
  `ssex` char(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生性别',
  `sdept` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生所属学院',
  `clazz_id` bigint NULL DEFAULT NULL COMMENT '班级ID',
  `clazz_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生所在班级',
  `sbday` datetime NULL DEFAULT NULL COMMENT '学生出生日期',
  `sbplace` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生生源地',
  `sIDNum` char(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生身份证号',
  `sphone` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学生手机号',
  `faceimg` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '人脸图片地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`sno`) USING BTREE,
  INDEX `ix_sname`(`sname` ASC) USING BTREE,
  INDEX `ix_cs`(`clazz_name` ASC, `sdept` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1311104', '李嘉欣', '女', '人文学院', 1, '13英语1', '1994-05-28 00:00:00', '山西太原', '330204199405281056', '15900002211', '1311104.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1311105', '苏有明', '男', '人文学院', 1, '13英语1', '1995-04-16 00:00:00', '内蒙古包头', '330204199504162036', '15900002222', '1311105.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1311106', '夏小雨', '男', '人文学院', 1, '13英语1', '1996-10-12 00:00:00', '甘肃兰州', '330204199610121046', '15900002233', '1311106.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1400802', '特拉', NULL, '机电学院', NULL, NULL, NULL, NULL, NULL, NULL, '1400802.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 1);
INSERT INTO `student` VALUES ('1401101', '徐若萱', '女', '信息学院', 5, '14计科1', '1996-07-09 00:00:00', '河北唐山', '330203199607090617', '15911113344', '1401101.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1401102', '陈晓东', '男', '信息学院', 5, '14计科1', '1996-03-21 00:00:00', '浙江杭州', '330203199603210034', '15911113355', '1401102.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1409802', '特斯拉', NULL, '机电学院', NULL, NULL, NULL, NULL, NULL, NULL, '1409802.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 1);
INSERT INTO `student` VALUES ('1409891', '柴静', NULL, '信息学院', NULL, NULL, NULL, NULL, NULL, NULL, '1409891.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 1);
INSERT INTO `student` VALUES ('1409892', '马斯克', NULL, '机电学院', NULL, NULL, NULL, NULL, NULL, NULL, '1409892.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 1);
INSERT INTO `student` VALUES ('1411101', '张柏芝', '女', '信息学院', 6, '14计科3', '1996-03-29 00:00:00', '浙江温州', '33020419960329267x', '15911113366', '1411101.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1411201', '王祖贤', '女', '经管学院', 2, '14物流2', '1995-07-25 00:00:00', '西藏拉萨', '330204199507253015', '15900002244', '1411201.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1411301', '刘嘉玲', '女', '信息学院', 4, '14网络1', '1995-07-18 00:00:00', '河北邢台', '330206199507181426', '15911114411', '1411301.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1411302', '周晓', '男', '信息学院', 6, '14计科3', '1997-08-05 00:00:00', '河北秦皇岛', '330206199708051212', '18620810098', '1411302.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1411307', '陆毅', '男', '机电学院', 3, '14电气1', '1996-01-20 00:00:00', '浙江绍兴', '330203199601201848', '15911112211', '1411307.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1411308', '袁莉', '女', '机电学院', 3, '14电气1', '1997-10-01 00:00:00', '江苏南京', '33020319971001094x', '15911112222', '1411308.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1411309', '刘亦菲', '女', '机电学院', 3, '14电气1', '1996-05-26 00:00:00', '江苏南京', '330203199605268062', '15911112233', '1411309.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1511205', '胡晓军', '男', '信息学院', 7, '15软工2', '1997-07-16 00:00:00', '浙江杭州', '330206199707163128', '15900002277', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1511206', '陈红', '女', '信息学院', 7, '15软工2', '1996-11-09 00:00:00', '浙江金华', '330206199711095710', '15900002288', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1511211', '李小鹏', '男', '信息学院', 7, '15软工2', '1996-11-10 00:00:00', '浙江湖州', '33020619961110312x', '15900002299', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1701101', '陈红', '女', '信息学院', 11, '17计应1', '1998-12-02 00:00:00', '浙江宁波', '330102199812020021', '15900001111', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1701102', '黄圣依', '女', '信息学院', 11, '17计应1', '1999-06-09 00:00:00', '浙江杭州', '330102199906090020', '15900001122', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1701103', '刘涛', '女', '信息学院', 11, '17计应1', '1999-09-18 00:00:00', '山东潍坊', '330102199909180182', '15900001133', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1701104', '晓静', '女', '信息学院', 11, '17计应1', '1998-03-10 00:00:00', '广西桂林', '330104199803109928', '15900001144', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1701105', '许晴', '女', '信息学院', 11, '17计应1', '1998-06-24 00:00:00', '北京', '330105199806243004', '15900001155', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1701106', '黎明', '男', '信息学院', 11, '17计应1', '1999-03-15 00:00:00', '香港', '330122199903152826', NULL, 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711101', '赵薇', '女', '经管学院', 8, '17物流1', '1999-02-11 00:00:00', '安徽合肥', '330203199902110925', '15900001177', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711102', '董洁', '女', '经管学院', 8, '17物流1', '1999-02-17 00:00:00', '上海', '330203199902170017', '15900001188', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711103', '王力宏', '男', '经管学院', 8, '17物流1', '1999-05-31 00:00:00', '台湾', '330203199905310027', '15900001199', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711202', '佟小为', '男', '经管学院', 9, '17物流2', '1998-06-07 00:00:00', '北京', '330205199806070617', '15900002255', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711203', '谢霆锋', '男', '经管学院', 9, '17物流2', '1998-11-02 00:00:00', '香港', '330205199811020964', '15900002266', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711306', '李若彤', '女', '经管学院', 10, '17物流3', '1999-08-09 00:00:00', '浙江宁波', '330206199908090317', '15911112200', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1711310', '萧亚轩', '女', '经管学院', 10, '17物流3', '1999-03-31 00:00:00', '山东青岛', '330203199903310029', '15911112244', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1801101', '林志颖', '男', '信息学院', 15, '18计科1', '1999-09-23 00:00:00', '福建厦门', '330203199909230655', '15911112255', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1801102', '陈奕迅', '男', '信息学院', 15, '18计科1', '2000-06-25 00:00:00', '福建漳州', '330203200006252418', '15911112266', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1801103', '古明乐', '男', '信息学院', 15, '18计科1', '2000-01-19 00:00:00', '福建福州', '330124200001191421', '15911112277', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811101', '郭富城', '男', '信息学院', 12, '18信管1', '1999-10-17 00:00:00', '北京', '330204199910173022', '15911112288', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811102', '王晓菲', '女', '信息学院', 12, '18信管1', '1999-11-16 00:00:00', '河北衡水', '330204199911161017', '15911112299', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811201', '蒋勤勤', '女', '信息学院', 13, '18信管2', '2000-01-09 00:00:00', '河北保定', '330206200001094616', '15911112299', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811202', '吴彦祖', '男', '信息学院', 13, '18信管2', '1999-06-01 00:00:00', '山东济南', '330206199906013416', '15911113300', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811301', '刘烨', '男', '信息学院', 14, '18信管3', '1999-12-23 00:00:00', '山东烟台', '330206199912234634', '15911113311', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811302', '周迅', '女', '信息学院', 14, '18信管3', '1999-08-07 00:00:00', '山东威海', '33020619990201141x', '15911113322', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1811303', '朱茵', '女', '信息学院', 14, '18信管3', '2000-02-14 00:00:00', '辽宁大连', '330206200002140914', '15911113333', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1822110', '黄泽校', '男', '金信学院', 16, '18软工UI1', '2022-01-17 00:00:00', '浙江宁波', '330281200005021735', '13248684099', 'hzx.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1911102', '张信哲', '男', '信息学院', 17, '19信管1', '1999-07-14 00:00:00', '山东烟台', '330204199907145027', '15911113377', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);
INSERT INTO `student` VALUES ('1911104', '红孩儿', 'male', '1', NULL, '1', NULL, NULL, NULL, '1', 'PixPin_2025-02-18_10-45-56.png', NULL, NULL, 0);
INSERT INTO `student` VALUES ('1911201', '陈坤', '男', '信息学院', 18, '19信管2', '1990-07-19 00:00:00', '山东烟台', '330204199007199604', '15911113388', 'PixPin_2025-02-25_14-29-33.png', '2025-02-27 13:05:30', '2025-02-27 13:05:41', 0);

-- ----------------------------
-- Table structure for student_score
-- ----------------------------
DROP TABLE IF EXISTS `student_score`;
CREATE TABLE `student_score`  (
  `sno` char(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cno` bigint NOT NULL,
  `score` decimal(4, 1) NULL DEFAULT NULL,
  `point` decimal(2, 1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`sno`, `cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_score
-- ----------------------------
INSERT INTO `student_score` VALUES ('1401102', 10, 67.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1401102', 13, 89.0, 2.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1401102', 45, NULL, NULL, NULL, NULL, 1);
INSERT INTO `student_score` VALUES ('1411101', 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `student_score` VALUES ('1411201', 8, 75.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1411201', 9, 77.7, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1411201', 11, 42.0, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1411201', 27, 84.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1411201', 34, 75.6, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1411201', 39, 69.3, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1411201', 52, 78.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 6, 70.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 8, 74.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 9, 52.0, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 11, 55.0, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 27, 75.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 34, 79.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1511211', 52, 82.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 1, 85.0, 2.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 2, 80.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 6, 91.0, 3.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 7, 87.0, 2.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 8, 57.8, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 10, 69.3, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 11, 63.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 13, 90.0, 3.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701101', 34, 81.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 2, 98.0, 3.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 8, 83.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 9, 76.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 10, 78.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 11, 72.5, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 13, 82.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 27, 79.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1701102', 34, 67.2, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 8, 86.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 9, 60.9, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 11, 52.5, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 27, 87.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 34, 88.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 39, 85.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711101', 52, 66.2, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 1, 90.0, 3.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 6, 64.1, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 7, 80.0, 2.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 8, 78.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 9, 76.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 11, 80.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 27, 84.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 34, 78.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711102', 52, 75.6, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 8, 78.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 9, 70.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 11, 53.0, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 27, 80.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 34, 79.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 39, 63.0, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1711202', 52, 87.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1811102', 8, 75.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1811102', 27, 78.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1811102', 34, 85.0, 2.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1811102', 52, 58.8, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 8, 69.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 9, 81.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 11, 72.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 27, 84.0, 1.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 34, 78.2, 0.0, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 39, 77.0, 1.5, NULL, NULL, 0);
INSERT INTO `student_score` VALUES ('1911201', 52, 71.0, 2.0, NULL, NULL, 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` bigint NOT NULL AUTO_INCREMENT COMMENT '教师编号',
  `tname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教师名称',
  `tsex` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教师性别',
  `ttitle` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教师职称',
  `tbirthday` datetime NULL DEFAULT NULL COMMENT '教师出生日期',
  `tdept` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教师所属部门',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '谭浩强', '男', '教授', '1958-01-01 00:00:00', '计科', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (2, '王珊', '女', '教授', '1962-02-13 00:00:00', '计科', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (3, '萨师煊', '男', '教授', '1953-05-01 00:00:00', '计科', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (4, '严蔚敏', '女', '副教授', '1968-07-02 00:00:00', '软工', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (5, '李琳', '女', '讲师', '1988-11-15 00:00:00', '软工', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (6, '韩万江', '男', '助教', '1992-10-17 00:00:00', '信管', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (7, '陈辉', '男', '副教授', '1980-08-24 00:00:00', '软工', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (8, '谢世凡', '男', '讲师', '1986-09-05 00:00:00', '信管', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (9, '郑卓', '女', '教授', '1976-04-23 00:00:00', '信管', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (10, '梅清', '女', '讲师', '1982-06-16 00:00:00', '网络', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (11, '谢良', '男', '副教授', '1979-03-15 00:00:00', '网络', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (12, '张敏之', '男', '讲师', '1988-05-22 00:00:00', '计科', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (13, '叶晨曦', '男', '讲师', '1987-06-17 00:00:00', '电商', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (14, '熊庆阳', '男', '教授', '1976-09-06 00:00:00', '电商', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (15, '黄盼盼', '女', '助教', '1990-05-04 00:00:00', '英语', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (16, '梅丽', '女', '讲师', '1985-09-05 00:00:00', '英语', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (17, '陈琦志', '男', '教授', '1967-07-19 00:00:00', '英语', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (18, '方严', '男', '副教授', '1972-10-05 00:00:00', '物流', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (19, '程源', '女', '副教授', '1975-05-16 00:00:00', '物流', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (20, '杨慕芳', '女', '讲师', '1984-12-24 00:00:00', '会计', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (21, '黄嘉琪', '女', '副教授', '1978-11-12 00:00:00', '会计', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (22, '姜雨晴', '女', '助教', '1990-06-15 00:00:00', '金融', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (23, '朱佩玲', '女', '讲师', '1980-05-22 00:00:00', '会计', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (24, '陈卿', '男', '教授', '1973-03-17 00:00:00', '金融', 1, NULL, NULL, 0);
INSERT INTO `teacher` VALUES (25, '王启晖', '男', '副教授', '1979-04-09 00:00:00', '网络', 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for teaching
-- ----------------------------
DROP TABLE IF EXISTS `teaching`;
CREATE TABLE `teaching`  (
  `tid` bigint NOT NULL COMMENT '排课编号',
  `cno` char(7) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程编号',
  `cterm` int NULL DEFAULT NULL COMMENT '上课学期',
  `tclass` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上课地点',
  `tno` char(3) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上课老师',
  `period` int NULL DEFAULT NULL COMMENT '课程学时',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teaching
-- ----------------------------
INSERT INTO `teaching` VALUES (1, '0000011', 1, '17物流1', '001', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (2, '0000034', 1, '17物流1', '002', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (3, '0000052', 3, '17物流1', '003', 60, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (4, '0000027', 1, '17物流1', '004', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (5, '0000039', 2, '17物流1', '005', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (6, '0000005', 6, '17物流1', '006', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (7, '0000001', 5, '17物流1', '007', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (8, '0000011', 1, '17物流2', NULL, 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (9, '0000034', 1, '17物流2', '002', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (10, '0000052', 3, '17物流2', '003', 60, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (11, '0000027', 1, '17物流2', '004', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (12, '0000039', 2, '17物流2', '009', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (13, '0000005', 6, '17物流2', '010', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (14, '0000001', 5, '17物流2', '007', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (15, '0000011', 1, '14计科3', '022', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (16, '0000034', 1, '14计科3', NULL, 54, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (17, '0000045', 7, '14计科3', '013', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (18, '0000027', 1, '14计科3', '014', 144, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (19, '0000039', 2, '14计科1', '015', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (20, '0000005', 6, '14计科1', '016', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (21, '0000001', 5, '14计科1', '007', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (22, '0000007', 3, '17物流1', '017', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (23, '0000012', 2, '17物流1', NULL, 54, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (24, '0000005', 6, '17物流1', '019', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (25, '0000008', 3, '17物流1', '020', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (26, '0000032', 2, '17物流1', '007', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (27, '0000004', 3, '17物流1', '021', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (28, '0000003', 3, '17物流1', '022', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (29, '0000007', 3, '17物流2', '017', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (30, '0000012', 2, '17物流2', '018', 54, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (31, '0000005', 6, '17物流2', '010', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (32, '0000008', 3, '17物流2', '020', 108, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (33, '0000032', 2, '17物流2', '007', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (34, '0000004', 3, '17物流2', '021', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (35, '0000003', 3, '17物流2', '022', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (36, '0000002', 2, '14计科1', '023', 144, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (37, '0000010', 2, '14计科1', '014', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (38, '0000013', 2, '14计科1', NULL, 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (39, '0000034', 1, '14计科1', '025', 54, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (40, '0000005', 6, '14计科3', '016', 72, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (41, '0000032', 2, '14计科3', '007', 36, NULL, NULL, 0);
INSERT INTO `teaching` VALUES (42, '0000003', 3, '14计科3', '001', 36, NULL, NULL, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `last_login` datetime NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_USERNAME`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '2021-01-12 22:13:53', '2021-01-16 16:57:32', '2020-12-30 08:38:37', 0);
INSERT INTO `user` VALUES (2, 'test', '123', '2021-01-30 08:20:22', '2021-01-30 08:55:57', '2025-02-20 12:41:36', 0);
INSERT INTO `user` VALUES (3, 'admin1', '$2a$10$pCSQeDMdPU4PEv/sHnd6HOMyOPJzlmt3GhbmmlqNdQxfJTmFfzhX.', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (4, 'admin2', '$2a$10$gxwAL/F/yGBWF.moZObi4.NBr0BzJngBZ95tAovNomWQ8Xs0NFuN6', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (8, 'admin3', '$2a$10$GMzzNWegkb8ZUTkKRD7qiuYJ8KQ.g58fI3aDJm9HDrbfiEaHQUXY6', '2025-03-23 20:56:45', '2025-03-24 15:36:06', '2025-03-24 15:36:06', 0);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `vid` bigint NOT NULL AUTO_INCREMENT COMMENT '视频id',
  `vname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频名称',
  `vimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频图片',
  `class_id` bigint NULL DEFAULT NULL COMMENT '上课班级',
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `status` int NULL DEFAULT 0 COMMENT '是否完成检测',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`vid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, '13英语1', '1.png', 1, '微积分原理', 1, '2025-03-02 15:08:39', '2025-03-02 15:08:39', 0);
INSERT INTO `video` VALUES (2, '14物流2', '2.png', 1, '微积分原理', 1, '2025-03-02 15:35:57', '2025-03-02 15:35:57', 0);
INSERT INTO `video` VALUES (3, '14电气1', '3.png', 2, '数据结构与算法', 1, '2025-03-02 15:35:57', '2025-03-02 15:35:57', 0);
INSERT INTO `video` VALUES (4, '14网络1', '4.png', 3, '计算机科学导论', 1, '2025-03-02 15:35:57', '2025-03-02 15:35:57', 0);
INSERT INTO `video` VALUES (5, '14计科1', '5.png', 4, '人工智能基础', 1, '2025-03-02 15:35:57', '2025-03-02 15:35:57', 0);
INSERT INTO `video` VALUES (6, '14计科3', '6.png', 5, '数据库系统', 1, '2025-03-02 15:35:57', '2025-03-02 15:35:57', 0);
INSERT INTO `video` VALUES (7, '15软工2', '7.png', 6, '计算机操作系统', 0, '2025-03-02 15:35:57', '2025-03-02 15:35:57', 0);

SET FOREIGN_KEY_CHECKS = 1;
