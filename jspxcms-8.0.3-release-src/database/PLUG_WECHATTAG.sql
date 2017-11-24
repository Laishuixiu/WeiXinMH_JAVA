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

Date: 2017-11-07 10:36:05
*/


-- ----------------------------
-- Table structure for PLUG_WECHATTAG
-- ----------------------------
DROP TABLE "FT_JSPXCMS"."PLUG_WECHATTAG";
CREATE TABLE "FT_JSPXCMS"."PLUG_WECHATTAG" (
"WECHAT_ID" NUMBER NOT NULL ,
"WECHAT_NAME" VARCHAR2(100 BYTE) NOT NULL ,
"WECHAT_COUNT" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of PLUG_WECHATTAG
-- ----------------------------
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('113', 'sdfds', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('108', '测试用标签', '1');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('115', '第三方公司', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('116', '撒地方撒旦法师的', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('112', 'ds', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('114', '撒', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('109', '测试用', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('110', '终极测试', '0');
INSERT INTO "FT_JSPXCMS"."PLUG_WECHATTAG" VALUES ('111', '添加测试', '0');

-- ----------------------------
-- Indexes structure for table PLUG_WECHATTAG
-- ----------------------------

-- ----------------------------
-- Checks structure for table PLUG_WECHATTAG
-- ----------------------------
ALTER TABLE "FT_JSPXCMS"."PLUG_WECHATTAG" ADD CHECK ("WECHAT_ID" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WECHATTAG" ADD CHECK ("WECHAT_NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PLUG_WECHATTAG
-- ----------------------------
ALTER TABLE "FT_JSPXCMS"."PLUG_WECHATTAG" ADD PRIMARY KEY ("WECHAT_ID");
