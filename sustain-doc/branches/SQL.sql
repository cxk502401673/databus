/*
 Navicat Premium Data Transfer

 Date: 24/06/2019 11:54:37
*/


-- ----------------------------
-- Table structure for DEPLOY_ATTEND_INFO
-- ----------------------------
DROP TABLE "DEPLOY_ATTEND_INFO";
CREATE TABLE "DEPLOY_ATTEND_INFO" (
  "DEPLOY_ID" CHAR(32 BYTE) NOT NULL ,
  "DEPLOY_NAME" VARCHAR2(50 BYTE) ,
  "DEPLOY_CODE" VARCHAR2(50 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_TIME" DATE ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "SORT_NUM" NUMBER ,
  "COMPARE_TYPE" CHAR(1 BYTE) ,
  "SERVICE_NAME" VARCHAR2(50 BYTE) ,
  "METHOD_NAME" VARCHAR2(50 BYTE) ,
  "PARAM_NAME" VARCHAR2(50 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."DEPLOY_NAME" IS '布控app名称';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."DEPLOY_CODE" IS '布控app代码';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."STATUS" IS '状态';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."CREATE_USER_ID" IS '创建人id';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."MODIFY_USER_ID" IS '修改人id';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."SORT_NUM" IS '权重';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."COMPARE_TYPE" IS 'R:redis;I:接口';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."SERVICE_NAME" IS '接口比对类型 service_name';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."METHOD_NAME" IS '接口比对类型 方法名';
COMMENT ON COLUMN "DEPLOY_ATTEND_INFO"."PARAM_NAME" IS '接口比对类型 参数名';

-- ----------------------------
-- Table structure for HOSPITAL_INFO
-- ----------------------------
DROP TABLE "HOSPITAL_INFO";
CREATE TABLE "HOSPITAL_INFO" (
  "HOSPITAL_ID" CHAR(32 BYTE) NOT NULL ,
  "NAME" VARCHAR2(255 BYTE) NOT NULL ,
  "CODE" VARCHAR2(255 BYTE) NOT NULL ,
  "PARENT_ID" CHAR(32 BYTE) ,
  "ADDRESS" VARCHAR2(255 BYTE) ,
  "CONTACTOR" VARCHAR2(50 BYTE) ,
  "CONTACT_TEL" VARCHAR2(32 BYTE) ,
  "POLICE_STATION_NAME" VARCHAR2(100 BYTE) ,
  "POLICE_STATION_CODE" VARCHAR2(50 BYTE) ,
  "CREATE_TIME" DATE ,
  "CREATE_USER" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "MODIFY_USER" CHAR(32 BYTE) ,
  "STATUS" CHAR(1 BYTE) DEFAULT 'N'
 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "HOSPITAL_INFO"."HOSPITAL_ID" IS 'id';
COMMENT ON COLUMN "HOSPITAL_INFO"."NAME" IS '名称';
COMMENT ON COLUMN "HOSPITAL_INFO"."CODE" IS '代码';
COMMENT ON COLUMN "HOSPITAL_INFO"."PARENT_ID" IS '父ID';
COMMENT ON COLUMN "HOSPITAL_INFO"."ADDRESS" IS '地址';
COMMENT ON COLUMN "HOSPITAL_INFO"."CONTACTOR" IS '联系人';
COMMENT ON COLUMN "HOSPITAL_INFO"."CONTACT_TEL" IS '联系方式';
COMMENT ON COLUMN "HOSPITAL_INFO"."POLICE_STATION_NAME" IS '派出所名';
COMMENT ON COLUMN "HOSPITAL_INFO"."POLICE_STATION_CODE" IS '派出所代码';
COMMENT ON COLUMN "HOSPITAL_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "HOSPITAL_INFO"."CREATE_USER" IS '创建人';
COMMENT ON COLUMN "HOSPITAL_INFO"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "HOSPITAL_INFO"."MODIFY_USER" IS '修改人';
COMMENT ON COLUMN "HOSPITAL_INFO"."STATUS" IS '状态';

-- ----------------------------
-- Table structure for PERCEPT_INFO
-- ----------------------------
DROP TABLE "PERCEPT_INFO";
CREATE TABLE "PERCEPT_INFO" (
  "PERCEPT_ID" CHAR(32 BYTE) NOT NULL ,
  "PERCEPT_NAME" VARCHAR2(50 BYTE) ,
  "PERCEPT_CODE" VARCHAR2(50 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_TIME" DATE ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "PERCEPT_INFO"."PERCEPT_NAME" IS '名称';
COMMENT ON COLUMN "PERCEPT_INFO"."PERCEPT_CODE" IS '代码';
COMMENT ON COLUMN "PERCEPT_INFO"."STATUS" IS '状态';
COMMENT ON COLUMN "PERCEPT_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "PERCEPT_INFO"."CREATE_USER_ID" IS '创建人id';
COMMENT ON COLUMN "PERCEPT_INFO"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "PERCEPT_INFO"."MODIFY_USER_ID" IS '修改人id';

-- ----------------------------
-- Table structure for POP_CONTROL
-- ----------------------------
DROP TABLE "POP_CONTROL";
CREATE TABLE "POP_CONTROL" (
  "ID" VARCHAR2(32 BYTE) ,
  "COUNTRY" VARCHAR2(3 BYTE) ,
  "SCARDTYPE" VARCHAR2(4 BYTE) ,
  "SCARDNO" VARCHAR2(18 BYTE) ,
  "SNAME" VARCHAR2(100 BYTE) ,
  "SSEX" VARCHAR2(4 BYTE) ,
  "SBDT" VARCHAR2(8 BYTE) ,
  "ISSMS" VARCHAR2(2 BYTE) DEFAULT 'N' ,
  "BSDT" DATE ,
  "BEDT" DATE ,
  "BRANGE" VARCHAR2(1000 BYTE) ,
  "BCARDNO" VARCHAR2(18 BYTE) ,
  "BNAME" VARCHAR2(100 BYTE) ,
  "BPHONE" VARCHAR2(30 BYTE) ,
  "BORG" VARCHAR2(20 BYTE) ,
  "BORGNAME" VARCHAR2(100 BYTE) ,
  "BSOURCE" VARCHAR2(400 BYTE) ,
  "BSOURCEID" VARCHAR2(1000 BYTE) ,
  "BMEMO" VARCHAR2(100 BYTE) ,
  "CMEMO" VARCHAR2(100 BYTE) ,
  "BSTS" VARCHAR2(4 BYTE) ,
  "STS" VARCHAR2(2 BYTE) ,
  "CR_YHID" VARCHAR2(32 BYTE) ,
  "CR_MC" VARCHAR2(100 BYTE) ,
  "CR_DT" DATE ,
  "CH_YHID" VARCHAR2(32 BYTE) ,
  "CH_MC" VARCHAR2(100 BYTE) ,
  "OPIP" VARCHAR2(100 BYTE) ,
  "BNO" VARCHAR2(30 BYTE) ,
  "CH_DT" DATE ,
  "SPTYPE" VARCHAR2(300 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 8192 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "POP_CONTROL"."ID" IS '系统唯一ID';
COMMENT ON COLUMN "POP_CONTROL"."COUNTRY" IS '国籍';
COMMENT ON COLUMN "POP_CONTROL"."SCARDTYPE" IS '受控人证件类型';
COMMENT ON COLUMN "POP_CONTROL"."SCARDNO" IS '受控人证件号码';
COMMENT ON COLUMN "POP_CONTROL"."SNAME" IS '受控人姓名';
COMMENT ON COLUMN "POP_CONTROL"."SSEX" IS '受控人性别';
COMMENT ON COLUMN "POP_CONTROL"."SBDT" IS '受控人出生日期';
COMMENT ON COLUMN "POP_CONTROL"."ISSMS" IS '是否发送短信';
COMMENT ON COLUMN "POP_CONTROL"."BSDT" IS '布控开始时间';
COMMENT ON COLUMN "POP_CONTROL"."BEDT" IS '布控结束时间';
COMMENT ON COLUMN "POP_CONTROL"."BRANGE" IS '布控类型范围';
COMMENT ON COLUMN "POP_CONTROL"."BCARDNO" IS '布控人证件号码';
COMMENT ON COLUMN "POP_CONTROL"."BNAME" IS '布控人姓名';
COMMENT ON COLUMN "POP_CONTROL"."BPHONE" IS '布控人联系电话';
COMMENT ON COLUMN "POP_CONTROL"."BORG" IS '布控人所属单位';
COMMENT ON COLUMN "POP_CONTROL"."BORGNAME" IS '布控人所属单位名称';
COMMENT ON COLUMN "POP_CONTROL"."BSOURCE" IS '布控来源   jzpt经侦平台 调用存错过程多个来源会自动拼接';
COMMENT ON COLUMN "POP_CONTROL"."BSOURCEID" IS '布控来源数据ID  调用存错过程多个来源会自动拼接';
COMMENT ON COLUMN "POP_CONTROL"."BMEMO" IS '备注';
COMMENT ON COLUMN "POP_CONTROL"."CMEMO" IS '撤销备注';
COMMENT ON COLUMN "POP_CONTROL"."BSTS" IS '布控状态 1布控中  3撤销布控';
COMMENT ON COLUMN "POP_CONTROL"."STS" IS '记录状态';
COMMENT ON COLUMN "POP_CONTROL"."CR_YHID" IS '创建人用户ID';
COMMENT ON COLUMN "POP_CONTROL"."CR_MC" IS '创建人姓名';
COMMENT ON COLUMN "POP_CONTROL"."CR_DT" IS '创建时间';
COMMENT ON COLUMN "POP_CONTROL"."CH_YHID" IS '修改人用户ID';
COMMENT ON COLUMN "POP_CONTROL"."CH_MC" IS '修改人姓名';
COMMENT ON COLUMN "POP_CONTROL"."OPIP" IS '操作人IP';
COMMENT ON COLUMN "POP_CONTROL"."BNO" IS '布控业务编号   即 布控唯一编号';
COMMENT ON COLUMN "POP_CONTROL"."CH_DT" IS '修改时间';
COMMENT ON COLUMN "POP_CONTROL"."SPTYPE" IS '受控人员类型与重点人员RYLX一致';
COMMENT ON TABLE "POP_CONTROL" IS '人员布控信息表';

-- ----------------------------
-- Table structure for REF_DEPLOY_PERCEPT
-- ----------------------------
DROP TABLE "REF_DEPLOY_PERCEPT";
CREATE TABLE "REF_DEPLOY_PERCEPT" (
  "ID" CHAR(32 BYTE) NOT NULL ,
  "DEPLOY_ID" CHAR(32 BYTE) ,
  "PERCEPT_ID" CHAR(32 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "CREATE_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for REF_DEPLOY_REDIS
-- ----------------------------
DROP TABLE "REF_DEPLOY_REDIS";
CREATE TABLE "REF_DEPLOY_REDIS" (
  "ID" CHAR(32 BYTE) NOT NULL ,
  "DEPLOY_ID" CHAR(32 BYTE) ,
  "REDIS_ID" CHAR(32 BYTE) ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "CREATE_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for REF_DEPLOY_WARN
-- ----------------------------
DROP TABLE "REF_DEPLOY_WARN";
CREATE TABLE "REF_DEPLOY_WARN" (
  "ID" CHAR(32 BYTE) NOT NULL ,
  "DEPLOY_ID" CHAR(32 BYTE) ,
  "WARN_APP_ID" CHAR(32 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "CREATE_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "REF_DEPLOY_WARN"."ID" IS 'id';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."DEPLOY_ID" IS '布控ID';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."WARN_APP_ID" IS '预警应用ID';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."STATUS" IS '状态 N:正常；P:停用';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."CREATE_USER_ID" IS '创建人id';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."MODIFY_USER_ID" IS '修改人ID';
COMMENT ON COLUMN "REF_DEPLOY_WARN"."MODIFY_TIME" IS '修改时间';

-- ----------------------------
-- Table structure for REF_WARN_PERCEPT_EXC
-- ----------------------------
DROP TABLE "REF_WARN_PERCEPT_EXC";
CREATE TABLE "REF_WARN_PERCEPT_EXC" (
  "ID" CHAR(32 BYTE) ,
  "WARN_APP_ID" CHAR(32 BYTE) ,
  "PERCEPT_ID" CHAR(32 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "CREATE_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."ID" IS 'id';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."WARN_APP_ID" IS '推送应用ID';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."PERCEPT_ID" IS '感知类型ID';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."STATUS" IS '状态 N-正常 P-停用（无数据默认为N）';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."CREATE_USER_ID" IS '创建人Id';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."MODIFY_USER_ID" IS '修改人ID';
COMMENT ON COLUMN "REF_WARN_PERCEPT_EXC"."MODIFY_TIME" IS '修改时间';

-- ----------------------------
-- Table structure for SYS_DICT_DATA
-- ----------------------------
DROP TABLE "SYS_DICT_DATA";
CREATE TABLE "SYS_DICT_DATA" (
  "DATA_ID" CHAR(32 BYTE) NOT NULL ,
  "DATA_CODE" VARCHAR2(50 BYTE) ,
  "DATA_NAME" VARCHAR2(100 BYTE) ,
  "STATUS" CHAR(1 BYTE) DEFAULT 'N'
 ,
  "CREATE_TIME" DATE ,
  "CREATE_USER" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "MODIFY_USER" CHAR(32 BYTE) ,
  "SORT_NUM" NUMBER(11) ,
  "MEMO" VARCHAR2(500 BYTE) ,
  "TYPE_ID" CHAR(32 BYTE) ,
  "PARENT_ID" CHAR(32 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_DICT_DATA"."DATA_CODE" IS '数据字典code';
COMMENT ON COLUMN "SYS_DICT_DATA"."DATA_NAME" IS '数据字典name';
COMMENT ON COLUMN "SYS_DICT_DATA"."STATUS" IS '状态 N-正常 P-暂停';
COMMENT ON COLUMN "SYS_DICT_DATA"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_DICT_DATA"."CREATE_USER" IS '创建人';
COMMENT ON COLUMN "SYS_DICT_DATA"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "SYS_DICT_DATA"."MODIFY_USER" IS '修改人';
COMMENT ON COLUMN "SYS_DICT_DATA"."SORT_NUM" IS '排序';
COMMENT ON COLUMN "SYS_DICT_DATA"."MEMO" IS '备注';
COMMENT ON COLUMN "SYS_DICT_DATA"."TYPE_ID" IS '数据类型ID';
COMMENT ON COLUMN "SYS_DICT_DATA"."PARENT_ID" IS '父字典ID';

-- ----------------------------
-- Table structure for SYS_DICT_TYPE
-- ----------------------------
DROP TABLE "SYS_DICT_TYPE";
CREATE TABLE "SYS_DICT_TYPE" (
  "TYPE_ID" CHAR(32 BYTE) NOT NULL ,
  "TYPE_CODE" VARCHAR2(50 BYTE) ,
  "TYPE_NAME" VARCHAR2(50 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_TIME" DATE ,
  "CREATE_USER" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "MODIFY_USER" CHAR(32 BYTE) ,
  "SORT_NUM" NUMBER(11) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_DICT_TYPE"."TYPE_CODE" IS '数据字典类型code';
COMMENT ON COLUMN "SYS_DICT_TYPE"."TYPE_NAME" IS '数据字典类型name';
COMMENT ON COLUMN "SYS_DICT_TYPE"."STATUS" IS '数据字典类型状态呢 N-正常 P-暂停';
COMMENT ON COLUMN "SYS_DICT_TYPE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_DICT_TYPE"."CREATE_USER" IS '创建人';
COMMENT ON COLUMN "SYS_DICT_TYPE"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "SYS_DICT_TYPE"."MODIFY_USER" IS '修改人';
COMMENT ON COLUMN "SYS_DICT_TYPE"."SORT_NUM" IS '序号';

-- ----------------------------
-- Table structure for SYS_LOG
-- ----------------------------
DROP TABLE "SYS_LOG";
CREATE TABLE "SYS_LOG" (
  "ID" NUMBER ,
  "TYPE" CHAR(1 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "MODULE_NAME" VARCHAR2(255 BYTE) ,
  "ACTION_NAME" VARCHAR2(255 BYTE) ,
  "SERVICE_ID" VARCHAR2(50 BYTE) ,
  "REMOTE_ADDR" VARCHAR2(50 BYTE) ,
  "USER_AGENT" VARCHAR2(1000 BYTE) ,
  "REQUEST_URI" VARCHAR2(255 BYTE) ,
  "METHOD" VARCHAR2(10 BYTE) ,
  "PARAMS" BLOB ,
  "EXCEPTION" BLOB ,
  "TIME" BLOB ,
  "DEL_FLAG" CHAR(1 BYTE) DEFAULT '0' ,
  "CREATE_BY" VARCHAR2(64 BYTE) ,
  "CREATE_TIME" DATE DEFAULT SYSDATE ,
  "UPDATE_TIME" DATE 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_LOG"."TYPE" IS '日志类型';
COMMENT ON COLUMN "SYS_LOG"."STATUS" IS '操作状态  0 成功 1 失败';
COMMENT ON COLUMN "SYS_LOG"."MODULE_NAME" IS '模块名';
COMMENT ON COLUMN "SYS_LOG"."ACTION_NAME" IS '操作名';
COMMENT ON COLUMN "SYS_LOG"."SERVICE_ID" IS '服务ID';
COMMENT ON COLUMN "SYS_LOG"."REMOTE_ADDR" IS '操作IP地址';
COMMENT ON COLUMN "SYS_LOG"."USER_AGENT" IS '用户代理';
COMMENT ON COLUMN "SYS_LOG"."REQUEST_URI" IS '请求URI';
COMMENT ON COLUMN "SYS_LOG"."METHOD" IS '操作方式';
COMMENT ON COLUMN "SYS_LOG"."PARAMS" IS '操作提交的数据';
COMMENT ON COLUMN "SYS_LOG"."EXCEPTION" IS '异常信息';
COMMENT ON COLUMN "SYS_LOG"."TIME" IS '执行时间';
COMMENT ON COLUMN "SYS_LOG"."DEL_FLAG" IS '删除标记';
COMMENT ON COLUMN "SYS_LOG"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SYS_LOG"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_LOG"."UPDATE_TIME" IS '更新时间';

-- ----------------------------
-- Table structure for SYS_OAUTH_CLIENT_DETAILS
-- ----------------------------
DROP TABLE "SYS_OAUTH_CLIENT_DETAILS";
CREATE TABLE "SYS_OAUTH_CLIENT_DETAILS" (
  "CLIENT_ID" VARCHAR2(256 BYTE) NOT NULL ,
  "RESOURCES_IDS" VARCHAR2(256 BYTE) ,
  "CLIENT_SECRET" VARCHAR2(256 BYTE) ,
  "SCOPE" VARCHAR2(256 BYTE) ,
  "AUTHORIZED_GRANT_TYPES" VARCHAR2(256 BYTE) ,
  "WEB_SERVER_REDIRECT_URI" VARCHAR2(256 BYTE) ,
  "AUTHORITIES" VARCHAR2(256 BYTE) ,
  "ACCESS_TOKEN_VALIDITY" NUMBER(11) ,
  "REFRESH_TOKEN_VALIDITY" NUMBER(11) ,
  "ADDITION_INFORMATION" VARCHAR2(4000 BYTE) ,
  "AUTOAPPROVE" VARCHAR2(256 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for SYS_REDIS_INFO
-- ----------------------------
DROP TABLE "SYS_REDIS_INFO";
CREATE TABLE "SYS_REDIS_INFO" (
  "REDIS_ID" CHAR(32 BYTE) ,
  "REDIS_NAME" VARCHAR2(50 BYTE) ,
  "REDIS_KEY" VARCHAR2(50 BYTE) ,
  "REDIS_TYPE" CHAR(1 BYTE) ,
  "SERVICE_NAME" VARCHAR2(200 BYTE) ,
  "INIT_METHOD" VARCHAR2(200 BYTE) ,
  "REMOVE_ALL_METHOD" VARCHAR2(200 BYTE) ,
  "REMOVE_SINGLE_METHOD" VARCHAR2(200 BYTE) ,
  "KEY_CODE" VARCHAR2(200 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "CREATE_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "REDIS_FLAG" CHAR(1 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_REDIS_INFO"."REDIS_NAME" IS 'redis名称';
COMMENT ON COLUMN "SYS_REDIS_INFO"."REDIS_KEY" IS 'redisKey';
COMMENT ON COLUMN "SYS_REDIS_INFO"."REDIS_TYPE" IS '类型 H:hash;L:list';
COMMENT ON COLUMN "SYS_REDIS_INFO"."STATUS" IS '状态 N:正常;P:暂停;D:删除;';
COMMENT ON COLUMN "SYS_REDIS_INFO"."REDIS_FLAG" IS 'redis标志 S：sys_dict; D:布控数据redis ';

-- ----------------------------
-- Table structure for SYS_RESOURCE
-- ----------------------------
DROP TABLE "SYS_RESOURCE";
CREATE TABLE "SYS_RESOURCE" (
  "ID" CHAR(32 BYTE) ,
  "NAME" VARCHAR2(64 BYTE) NOT NULL ,
  "TYPE" CHAR(1 BYTE) DEFAULT '0'  NOT NULL ,
  "URL" VARCHAR2(255 BYTE) ,
  "METHOD" VARCHAR2(20 BYTE) ,
  "PATH" VARCHAR2(128 BYTE) ,
  "PERMISSION" VARCHAR2(32 BYTE) ,
  "COLOR" VARCHAR2(64 BYTE) ,
  "PARENT_ID" CHAR(32 BYTE) ,
  "ICON" VARCHAR2(32 BYTE) ,
  "COMPONENT" VARCHAR2(128 BYTE) ,
  "SORT" NUMBER(11) ,
  "CREATE_TIME" DATE DEFAULT SYSDATE ,
  "MODIFY_TIME" DATE ,
  "DEL_FLAG" CHAR(1 BYTE) DEFAULT '0'
 ,
  "OLD_ID" NUMBER(11) ,
  "OLD_PARENT_ID" NUMBER(11) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_RESOURCE"."ID" IS '主键';
COMMENT ON COLUMN "SYS_RESOURCE"."NAME" IS '资源名称';
COMMENT ON COLUMN "SYS_RESOURCE"."TYPE" IS '资源类型 0-菜单 1-按钮';
COMMENT ON COLUMN "SYS_RESOURCE"."URL" IS '后台请求url';
COMMENT ON COLUMN "SYS_RESOURCE"."METHOD" IS '请求方式';
COMMENT ON COLUMN "SYS_RESOURCE"."PATH" IS '前端url';
COMMENT ON COLUMN "SYS_RESOURCE"."PERMISSION" IS '按钮权限资源标识';
COMMENT ON COLUMN "SYS_RESOURCE"."COLOR" IS '颜色';
COMMENT ON COLUMN "SYS_RESOURCE"."PARENT_ID" IS '父资源id';
COMMENT ON COLUMN "SYS_RESOURCE"."ICON" IS '图标';
COMMENT ON COLUMN "SYS_RESOURCE"."COMPONENT" IS '组件路径';
COMMENT ON COLUMN "SYS_RESOURCE"."SORT" IS '排序权重';
COMMENT ON COLUMN "SYS_RESOURCE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_RESOURCE"."MODIFY_TIME" IS '更新时间';
COMMENT ON COLUMN "SYS_RESOURCE"."DEL_FLAG" IS '是否删除 1-删除，0-未删除';

-- ----------------------------
-- Table structure for SYS_ROLE
-- ----------------------------
DROP TABLE "SYS_ROLE";
CREATE TABLE "SYS_ROLE" (
  "ROLE_ID" CHAR(32 BYTE) ,
  "ROLE_CODE" VARCHAR2(32 BYTE) NOT NULL ,
  "ROLE_NAME" VARCHAR2(128 BYTE) NOT NULL ,
  "CREATE_TIME" DATE DEFAULT SYSDATE ,
  "MODIFY_TIME" DATE ,
  "DEL_FLAG" CHAR(1 BYTE) DEFAULT '0'
 ,
  "STATUS" CHAR(1 BYTE) DEFAULT 'N'
 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_ROLE"."ROLE_ID" IS '主键';
COMMENT ON COLUMN "SYS_ROLE"."ROLE_CODE" IS '角色code用于springsecurity角色标识码';
COMMENT ON COLUMN "SYS_ROLE"."ROLE_NAME" IS '角色名称';
COMMENT ON COLUMN "SYS_ROLE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_ROLE"."MODIFY_TIME" IS '更新时间';
COMMENT ON COLUMN "SYS_ROLE"."DEL_FLAG" IS '是否删除 1-删除，0-未删除';
COMMENT ON COLUMN "SYS_ROLE"."STATUS" IS 'N-正常显示;S-特殊显示';

-- ----------------------------
-- Table structure for SYS_ROLE_RESOURCE
-- ----------------------------
DROP TABLE "SYS_ROLE_RESOURCE";
CREATE TABLE "SYS_ROLE_RESOURCE" (
  "ROLE_ID" CHAR(32 BYTE) ,
  "RESOURCE_ID" CHAR(32 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE "SYS_USER";
CREATE TABLE "SYS_USER" (
  "USER_ID" CHAR(32 BYTE) NOT NULL ,
  "USERNAME" VARCHAR2(255 BYTE) NOT NULL ,
  "PASSWORD" VARCHAR2(255 BYTE) NOT NULL ,
  "EMAIL" VARCHAR2(255 BYTE) ,
  "MOBILE" VARCHAR2(32 BYTE) NOT NULL ,
  "QQ" VARCHAR2(32 BYTE) ,
  "WECHAT" VARCHAR2(32 BYTE) ,
  "WEIBO" VARCHAR2(32 BYTE) ,
  "AVATAR" VARCHAR2(32 BYTE) ,
  "QQ_OPENID" VARCHAR2(32 BYTE) ,
  "WECHAT_OPENID" VARCHAR2(32 BYTE) ,
  "WEIBO_OPENID" VARCHAR2(32 BYTE) ,
  "CREATE_TIME" DATE DEFAULT SYSDATE ,
  "MODIFY_TIME" DATE ,
  "DEL_FLAG" CHAR(1 BYTE) DEFAULT '0'
 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SYS_USER"."USER_ID" IS '主键';
COMMENT ON COLUMN "SYS_USER"."USERNAME" IS '用户名';
COMMENT ON COLUMN "SYS_USER"."PASSWORD" IS '密码';
COMMENT ON COLUMN "SYS_USER"."EMAIL" IS '邮箱';
COMMENT ON COLUMN "SYS_USER"."MOBILE" IS '手机号码';
COMMENT ON COLUMN "SYS_USER"."QQ" IS 'qq号码';
COMMENT ON COLUMN "SYS_USER"."WECHAT" IS '微信号码';
COMMENT ON COLUMN "SYS_USER"."WEIBO" IS '微博URL';
COMMENT ON COLUMN "SYS_USER"."AVATAR" IS '头像URL';
COMMENT ON COLUMN "SYS_USER"."QQ_OPENID" IS 'QQ openid';
COMMENT ON COLUMN "SYS_USER"."WECHAT_OPENID" IS '微信openid';
COMMENT ON COLUMN "SYS_USER"."WEIBO_OPENID" IS '微博openid';
COMMENT ON COLUMN "SYS_USER"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SYS_USER"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "SYS_USER"."DEL_FLAG" IS '是否删除 0-未删除 1-删除';

-- ----------------------------
-- Table structure for SYS_USER_ROLE
-- ----------------------------
DROP TABLE "SYS_USER_ROLE";
CREATE TABLE "SYS_USER_ROLE" (
  "USER_ID" CHAR(32 BYTE) NOT NULL ,
  "ROLE_ID" CHAR(32 BYTE) NOT NULL 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for SYS_ZUUL_ROUTE
-- ----------------------------
DROP TABLE "SYS_ZUUL_ROUTE";
CREATE TABLE "SYS_ZUUL_ROUTE" (
  "ID" NUMBER NOT NULL ,
  "PATH" VARCHAR2(255 BYTE) NOT NULL ,
  "SERVICE_ID" VARCHAR2(255 BYTE) NOT NULL ,
  "URL" VARCHAR2(255 BYTE) ,
  "STRIP_PREFIX" CHAR(1 BYTE) DEFAULT '1' ,
  "RETRYABLE" CHAR(1 BYTE) DEFAULT '1' ,
  "ENABLED" CHAR(1 BYTE) DEFAULT '1' ,
  "SENSITIVEHEADERS_LIST" VARCHAR2(255 BYTE) ,
  "CREATE_TIME" DATE DEFAULT SYSDATE ,
  "UPDATE_TIME" DATE ,
  "DEL_FLAG" CHAR(1 BYTE) DEFAULT '0'
 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for T_CONTROL
-- ----------------------------
DROP TABLE "T_CONTROL";
CREATE TABLE "T_CONTROL" (
  "id" CHAR(32 BYTE) NOT NULL ,
  "id_card" VARCHAR2(18 BYTE) NOT NULL ,
  "percept_id" VARCHAR2(32 BYTE) NOT NULL ,
  "deploy_attend_info_id" VARCHAR2(32 BYTE) NOT NULL ,
  "reqid" VARCHAR2(32 BYTE) ,
  "json_string" VARCHAR2(4000 BYTE) ,
  "start_date" NUMBER(32) DEFAULT NULL ,
  "end_date" NUMBER(32) DEFAULT NULL ,
  "content" VARCHAR2(255 BYTE) ,
  "state" NUMBER(10) DEFAULT 0  ,
  "insert_date" NUMBER(32) DEFAULT NULL 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "T_CONTROL"."id" IS 'id，唯一';
COMMENT ON COLUMN "T_CONTROL"."id_card" IS '身份证号码';
COMMENT ON COLUMN "T_CONTROL"."percept_id" IS '感知类型ID';
COMMENT ON COLUMN "T_CONTROL"."deploy_attend_info_id" IS '布控app ID';
COMMENT ON COLUMN "T_CONTROL"."reqid" IS '多维中的具体类型';
COMMENT ON COLUMN "T_CONTROL"."json_string" IS '一些其他属性';
COMMENT ON COLUMN "T_CONTROL"."start_date" IS '布控开始时间';
COMMENT ON COLUMN "T_CONTROL"."end_date" IS '布控结束时间';
COMMENT ON COLUMN "T_CONTROL"."content" IS '备注';
COMMENT ON COLUMN "T_CONTROL"."state" IS '人员状态，0表示在控状态，1 表示手动撤控，2表示自动撤控';
COMMENT ON COLUMN "T_CONTROL"."insert_date" IS '插入时间';

-- ----------------------------
-- Table structure for T_UN_CONTROL
-- ----------------------------
DROP TABLE "T_UN_CONTROL";
CREATE TABLE "T_UN_CONTROL" (
  "id" CHAR(32 BYTE) NOT NULL ,
  "t_control_id" CHAR(32 BYTE) NOT NULL ,
  "id_card" VARCHAR2(18 BYTE) NOT NULL ,
  "percept_id" VARCHAR2(32 BYTE) NOT NULL ,
  "deploy_attend_info_id" VARCHAR2(32 BYTE) NOT NULL ,
  "reqid" VARCHAR2(32 BYTE) ,
  "json_string" VARCHAR2(4000 BYTE) ,
  "start_date" DATE ,
  "end_date" DATE ,
  "content" VARCHAR2(255 BYTE) ,
  "state" NUMBER(10) DEFAULT 0   NOT NULL ,
  "insert_date" DATE DEFAULT sysdate   NOT NULL 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "T_UN_CONTROL"."id" IS 'id，唯一';
COMMENT ON COLUMN "T_UN_CONTROL"."t_control_id" IS 't_control表中的id';
COMMENT ON COLUMN "T_UN_CONTROL"."id_card" IS '身份证号码';
COMMENT ON COLUMN "T_UN_CONTROL"."percept_id" IS '感知类型ID';
COMMENT ON COLUMN "T_UN_CONTROL"."deploy_attend_info_id" IS '布控app ID';
COMMENT ON COLUMN "T_UN_CONTROL"."reqid" IS '多维中的具体类型';
COMMENT ON COLUMN "T_UN_CONTROL"."json_string" IS '一些其他属性';
COMMENT ON COLUMN "T_UN_CONTROL"."start_date" IS '布控开始时间';
COMMENT ON COLUMN "T_UN_CONTROL"."end_date" IS '布控结束时间';
COMMENT ON COLUMN "T_UN_CONTROL"."content" IS '备注';
COMMENT ON COLUMN "T_UN_CONTROL"."state" IS '人员状态，1 表示手动撤控，2表示自动撤控';
COMMENT ON COLUMN "T_UN_CONTROL"."insert_date" IS '插入时间';

-- ----------------------------
-- Table structure for USER_HOSPITAL_INFO
-- ----------------------------
DROP TABLE "USER_HOSPITAL_INFO";
CREATE TABLE "USER_HOSPITAL_INFO" (
  "ID" CHAR(32 BYTE) NOT NULL ,
  "USER_ID" CHAR(32 BYTE) NOT NULL ,
  "HOSPITAL_ID" CHAR(32 BYTE) NOT NULL ,
  "CREATE_USER" CHAR(32 BYTE) ,
  "CREATE_TIME" DATE ,
  "MODIFY_USER" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."ID" IS 'ID';
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."USER_ID" IS '用户ID';
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."HOSPITAL_ID" IS '医院ID';
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."CREATE_USER" IS '创建人';
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."MODIFY_USER" IS '修改人';
COMMENT ON COLUMN "USER_HOSPITAL_INFO"."MODIFY_TIME" IS '修改时间';

-- ----------------------------
-- Table structure for WARN_APP_INFO
-- ----------------------------
DROP TABLE "WARN_APP_INFO";
CREATE TABLE "WARN_APP_INFO" (
  "WARN_APP_ID" CHAR(32 BYTE) NOT NULL ,
  "WARN_APP_NAME" VARCHAR2(50 BYTE) ,
  "WARN_APP_CODE" VARCHAR2(50 BYTE) ,
  "WARN_TOPIC_EXCHANGE" VARCHAR2(200 BYTE) ,
  "STATUS" CHAR(1 BYTE) ,
  "CREATE_TIME" DATE ,
  "CREATE_USER_ID" CHAR(32 BYTE) ,
  "MODIFY_TIME" DATE ,
  "MODIFY_USER_ID" CHAR(32 BYTE) ,
  "SORT_NUM" NUMBER ,
  "WARN_QUEUE_NAME" VARCHAR2(200 BYTE) 
)
TABLESPACE "TP_YKJY"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "WARN_APP_INFO"."WARN_APP_ID" IS 'id';
COMMENT ON COLUMN "WARN_APP_INFO"."WARN_APP_NAME" IS '名称';
COMMENT ON COLUMN "WARN_APP_INFO"."WARN_APP_CODE" IS '代码';
COMMENT ON COLUMN "WARN_APP_INFO"."WARN_TOPIC_EXCHANGE" IS '推送交换器名';
COMMENT ON COLUMN "WARN_APP_INFO"."STATUS" IS '状态N :正常；P:暂停；D：删除';
COMMENT ON COLUMN "WARN_APP_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "WARN_APP_INFO"."CREATE_USER_ID" IS '创建人';
COMMENT ON COLUMN "WARN_APP_INFO"."MODIFY_TIME" IS '修改时间';
COMMENT ON COLUMN "WARN_APP_INFO"."MODIFY_USER_ID" IS '修改人';
COMMENT ON COLUMN "WARN_APP_INFO"."SORT_NUM" IS '权重';
COMMENT ON COLUMN "WARN_APP_INFO"."WARN_QUEUE_NAME" IS '推送队列名';

-- ----------------------------
-- Primary Key structure for table DEPLOY_ATTEND_INFO
-- ----------------------------
ALTER TABLE "DEPLOY_ATTEND_INFO" ADD CONSTRAINT "DEPLOY_ATTEND_INFO_ID" PRIMARY KEY ("DEPLOY_ID");

-- ----------------------------
-- Checks structure for table DEPLOY_ATTEND_INFO
-- ----------------------------
ALTER TABLE "DEPLOY_ATTEND_INFO" ADD CONSTRAINT "SYS_C0020382" CHECK ("DEPLOY_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table HOSPITAL_INFO
-- ----------------------------
ALTER TABLE "HOSPITAL_INFO" ADD CONSTRAINT "SYS_C0020315" CHECK ("HOSPITAL_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "HOSPITAL_INFO" ADD CONSTRAINT "SYS_C0020316" CHECK ("NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "HOSPITAL_INFO" ADD CONSTRAINT "SYS_C0020317" CHECK ("CODE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PERCEPT_INFO
-- ----------------------------
ALTER TABLE "PERCEPT_INFO" ADD CONSTRAINT "PERCEPT_INFO_ID" PRIMARY KEY ("PERCEPT_ID");

-- ----------------------------
-- Checks structure for table PERCEPT_INFO
-- ----------------------------
ALTER TABLE "PERCEPT_INFO" ADD CONSTRAINT "SYS_C0020378" CHECK ("PERCEPT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table REF_DEPLOY_PERCEPT
-- ----------------------------
ALTER TABLE "REF_DEPLOY_PERCEPT" ADD CONSTRAINT "REF_DEPLOY_PRECEPT_ID" PRIMARY KEY ("ID");

-- ----------------------------
-- Primary Key structure for table REF_DEPLOY_REDIS
-- ----------------------------
ALTER TABLE "REF_DEPLOY_REDIS" ADD CONSTRAINT "REF_DEPLOY_REDIS_ID" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table REF_DEPLOY_REDIS
-- ----------------------------
ALTER TABLE "REF_DEPLOY_REDIS" ADD CONSTRAINT "SYS_C0020402" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table REF_DEPLOY_WARN
-- ----------------------------
ALTER TABLE "REF_DEPLOY_WARN" ADD CONSTRAINT "REF_DEPLOY_WARN_ID" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SYS_DICT_DATA
-- ----------------------------
ALTER TABLE "SYS_DICT_DATA" ADD CONSTRAINT "SYS_C0019874" CHECK ("DATA_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table SYS_DICT_TYPE
-- ----------------------------
ALTER TABLE "SYS_DICT_TYPE" ADD CONSTRAINT "SYS_C0019807" CHECK ("TYPE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table SYS_OAUTH_CLIENT_DETAILS
-- ----------------------------
ALTER TABLE "SYS_OAUTH_CLIENT_DETAILS" ADD CONSTRAINT "SYS_C0019770" CHECK ("CLIENT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table SYS_RESOURCE
-- ----------------------------
ALTER TABLE "SYS_RESOURCE" ADD CONSTRAINT "SYS_C0019772" CHECK ("NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_RESOURCE" ADD CONSTRAINT "SYS_C0019773" CHECK ("TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table SYS_ROLE
-- ----------------------------
ALTER TABLE "SYS_ROLE" ADD CONSTRAINT "SYS_C0019776" CHECK ("ROLE_CODE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_ROLE" ADD CONSTRAINT "SYS_C0019777" CHECK ("ROLE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_USER
-- ----------------------------
ALTER TABLE "SYS_USER" ADD CONSTRAINT "USER_ID" PRIMARY KEY ("USER_ID");

-- ----------------------------
-- Checks structure for table SYS_USER
-- ----------------------------
ALTER TABLE "SYS_USER" ADD CONSTRAINT "SYS_C0019781" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_USER" ADD CONSTRAINT "SYS_C0019782" CHECK ("PASSWORD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_USER" ADD CONSTRAINT "SYS_C0019783" CHECK ("MOBILE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table SYS_USER_ROLE
-- ----------------------------
ALTER TABLE "SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0020321" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0020322" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table SYS_ZUUL_ROUTE
-- ----------------------------
ALTER TABLE "SYS_ZUUL_ROUTE" ADD CONSTRAINT "SYS_C0019802" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_ZUUL_ROUTE" ADD CONSTRAINT "SYS_C0019803" CHECK ("PATH" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SYS_ZUUL_ROUTE" ADD CONSTRAINT "SYS_C0019804" CHECK ("SERVICE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table T_CONTROL
-- ----------------------------
ALTER TABLE "T_CONTROL" ADD CONSTRAINT "SYS_C0020371" PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table T_CONTROL
-- ----------------------------
ALTER TABLE "T_CONTROL" ADD CONSTRAINT "SYS_C0020366" CHECK ("id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_CONTROL" ADD CONSTRAINT "SYS_C0020367" CHECK ("id_card" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_CONTROL" ADD CONSTRAINT "SYS_C0020368" CHECK ("percept_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_CONTROL" ADD CONSTRAINT "SYS_C0020369" CHECK ("deploy_attend_info_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table T_UN_CONTROL
-- ----------------------------
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020372" CHECK ("id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020373" CHECK ("t_control_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020374" CHECK ("id_card" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020375" CHECK ("percept_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020376" CHECK ("deploy_attend_info_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020377" CHECK ("state" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "T_UN_CONTROL" ADD CONSTRAINT "SYS_C0020381" CHECK ("insert_date" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table USER_HOSPITAL_INFO
-- ----------------------------
ALTER TABLE "USER_HOSPITAL_INFO" ADD CONSTRAINT "SYS_C0020318" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "USER_HOSPITAL_INFO" ADD CONSTRAINT "SYS_C0020319" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "USER_HOSPITAL_INFO" ADD CONSTRAINT "SYS_C0020320" CHECK ("HOSPITAL_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table WARN_APP_INFO
-- ----------------------------
ALTER TABLE "WARN_APP_INFO" ADD CONSTRAINT "SYS_C0020384" CHECK ("WARN_APP_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
