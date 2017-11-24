/*
Navicat Oracle Data Transfer
Oracle Client Version : 12.2.0.1.0

Source Server         : ft
Source Server Version : 110200
Source Host           : 192.168.0.111:1521
Source Schema         : FT_JSPXCMS

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2017-11-07 10:35:19
*/


-- ----------------------------
-- Table structure for PLUG_WXMENU_BUTTON
-- ----------------------------
DROP TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON";
CREATE TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" (
"WXMENU_ID" NUMBER NOT NULL ,
"WXMENU_SITEID" NUMBER NOT NULL ,
"WXMENU_NAME" VARCHAR2(40 BYTE) NOT NULL ,
"WXMENU_TYPE" VARCHAR2(20 BYTE) NOT NULL ,
"WXMENU_LEVEL" CHAR(1 BYTE) NOT NULL ,
"WXMENU_PARENTID" NUMBER NULL ,
"WXMENU_KEY" VARCHAR2(128 BYTE) NULL ,
"WXMENU_URL" VARCHAR2(1024 BYTE) NULL ,
"WXMENU_EDITOR" VARCHAR2(20 BYTE) NOT NULL ,
"WXMENU_ISUPLOAD" CHAR(1 BYTE) NOT NULL ,
"WXMENU_OPERATINGTIME" DATE NOT NULL ,
"WXMENU_UPLOADTIME" DATE NULL ,
"WXMENU_ISDELETE" CHAR(1 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of PLUG_WXMENU_BUTTON
-- ----------------------------
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('1', '1', '菜单1', 'click', '1', null, 'menu_testing1', null, 'a', '1', TO_DATE('2017-10-12 11:14:52', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2017-10-12 11:14:52', 'YYYY-MM-DD HH24:MI:SS'), '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('2', '1', '菜单2', 'click', '2', '3', 'menutesting2', null, 'a', '0', TO_DATE('2017-08-23 09:12:52', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('3', '1', '菜单3', 'view', '1', null, null, 'www.qq.com', 'a', '0', TO_DATE('2017-08-31 08:52:28', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('4', '1', '菜单4', 'click', '2', '5', 'menu4', null, 'a', '0', TO_DATE('2017-08-31 08:52:28', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('5', '1', '菜单5', 'view', '1', null, null, 'www.baidu.com', 'a', '0', TO_DATE('2017-08-31 08:52:28', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('6', '1', '菜单6', 'click', '2', '5', 'menu_tesing6', null, 'a', '0', TO_DATE('2017-08-31 08:52:28', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('7', '1', '菜单7', 'view', '2', '5', null, 'www.google.com', 'a', '0', TO_DATE('2017-09-05 09:01:59', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('8', '1', '菜单8', 'click', '2', '5', 'hoiadhighia', null, 'a', '0', TO_DATE('2017-08-30 12:03:34', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('9', '1', '菜单9', 'view', '2', '4', null, 'www.wiki.com', 'a', '0', TO_DATE('2017-08-31 08:52:28', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('10', '1', '菜单10', 'view', '2', '4', null, 'www.360.com', 'a', '0', TO_DATE('2017-08-31 08:52:17', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('11', '1', '菜单11', 'click', '1', '1', 'MENUTEST11', null, 'a', '0', TO_DATE('2017-11-06 14:29:36', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('33', '1', '最终测试', 'view', '2', '5', null, 'www.finallytest.com', 'a', '0', TO_DATE('2017-08-22 14:49:28', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('34', '1', '最终测试2', 'view', '1', null, null, '.feedBackUrlCallBack', 'a', '0', TO_DATE('2017-08-22 14:48:29', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('23', '1', '按钮测试', 'click', '2', '3', 'TestCreating', null, 'a', '0', TO_DATE('2017-08-31 08:52:17', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('44', '1', '测试2', 'view', '1', null, null, 'www.asoidfo.com', 'a', '0', TO_DATE('2017-08-28 12:08:53', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('63', '1', '四十九', 'view', '1', null, null, 'ihhoiihoih', 'a', '0', TO_DATE('2017-08-22 14:46:06', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('24', '1', '上传测试4', 'click', '2', '5', 'tesngttesngi', null, 'a', '0', TO_DATE('2017-09-05 09:01:42', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('25', '1', '上传测试5', 'click', '2', '85', 'tesing', null, 'a', '0', TO_DATE('2017-10-12 11:15:15', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('26', '1', '上传测试6', 'click', '1', '5', 'TESTING6', null, 'a', '0', TO_DATE('2017-09-05 09:01:42', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('43', '1', '公司简介', 'view', '2', '5', null, '45646546', 'a', '0', TO_DATE('2017-09-05 09:01:42', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('54', '1', 'Buttontest', 'view', '2', '2', null, 'www.return.com', 'a', '0', TO_DATE('2017-08-23 10:16:46', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('73', '1', '测试测试测试', 'view', '2', '3', null, 'www.try.com', 'a', '0', TO_DATE('2017-09-05 09:01:42', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('13', '1', '按钮20', 'view', '2', '11', null, 'www.yy.com', 'a', '0', TO_DATE('2017-08-28 12:09:11', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('14', '1', '按钮23', 'click', '1', null, 'test_Creating', null, 'a', '0', TO_DATE('2017-08-31 15:12:08', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('35', '1', '测试', 'view', '2', '3', null, 'www.ashgoi.com', 'a', '0', TO_DATE('2017-09-05 09:01:42', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('53', '1', 'test', 'view', '1', null, null, 'www.qq.com', 'a', '0', TO_DATE('2017-09-08 10:28:48', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('83', '1', '测试304', 'click', '1', null, 'tobe', null, 'a', '0', TO_DATE('2017-11-06 14:27:25', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('84', '1', '测试304', 'click', '1', null, 'tobe', null, 'a', '0', TO_DATE('2017-09-05 09:31:12', 'YYYY-MM-DD HH24:MI:SS'), null, '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" VALUES ('85', '1', '佛图测试', 'click', '1', null, '123123123', null, 'a', '0', TO_DATE('2017-11-06 14:27:17', 'YYYY-MM-DD HH24:MI:SS'), null, '0');

-- ----------------------------
-- Indexes structure for table PLUG_WXMENU_BUTTON
-- ----------------------------

-- ----------------------------
-- Checks structure for table PLUG_WXMENU_BUTTON
-- ----------------------------
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_ID" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_SITEID" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_NAME" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_TYPE" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_LEVEL" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_EDITOR" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_ISUPLOAD" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_OPERATINGTIME" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD CHECK ("WXMENU_ISDELETE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PLUG_WXMENU_BUTTON
-- ----------------------------
ALTER TABLE "FT_JSPXCMS"."PLUG_WXMENU_BUTTON" ADD PRIMARY KEY ("WXMENU_ID");
