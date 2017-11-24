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

Date: 2017-11-07 10:35:42
*/


-- ----------------------------
-- Table structure for PLUG_WECHATUSER
-- ----------------------------
DROP TABLE "FT_JSPXCMS"."PLUG_WECHATUSER";
CREATE TABLE "FT_JSPXCMS"."PLUG_WECHATUSER" (
"WECHAT_SUBCRIBE" NUMBER NOT NULL ,
"WECHAT_OPENID" VARCHAR2(1024 BYTE) NOT NULL ,
"WECHAT_NICKNAME" VARCHAR2(128 BYTE) NULL ,
"WECHAT_SEX" NUMBER NULL ,
"WECHAT_CITY" VARCHAR2(20 BYTE) NULL ,
"WECHAT_COUNTRY" VARCHAR2(20 BYTE) NULL ,
"WECHAT_PROVINCE" VARCHAR2(20 BYTE) NULL ,
"WECHAT_LANGUAGE" VARCHAR2(20 BYTE) NULL ,
"WECHAT_HEADIMGURL" VARCHAR2(1024 BYTE) NULL ,
"WECHAT_SUBCRIBE_TIME" DATE NULL ,
"WECHAT_UNIONID" VARCHAR2(1024 BYTE) NULL ,
"WECHAT_REMARK" VARCHAR2(128 BYTE) NULL ,
"WECHAT_TAGID_LIST" VARCHAR2(2000 BYTE) NULL ,
"WECHAT_ISBLACKLIST" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of PLUG_WECHATUSER
-- ----------------------------

-- ----------------------------
-- Indexes structure for table PLUG_WECHATUSER
-- ----------------------------

-- ----------------------------
-- Checks structure for table PLUG_WECHATUSER
-- ----------------------------
ALTER TABLE "FT_JSPXCMS"."PLUG_WECHATUSER" ADD CHECK ("WECHAT_SUBCRIBE" IS NOT NULL);
ALTER TABLE "FT_JSPXCMS"."PLUG_WECHATUSER" ADD CHECK ("WECHAT_OPENID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PLUG_WECHATUSER
-- ----------------------------
ALTER TABLE "FT_JSPXCMS"."PLUG_WECHATUSER" ADD PRIMARY KEY ("WECHAT_OPENID");
