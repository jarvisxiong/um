package com.hhz.ump.util;

public class GlobalConstants {
 


	// 以下常量与APP_PARAM(参数配置定义表)保持一致

	// (1)全局参数

	// 应用项目编号
	public static final String GLOBAL_KEY_CURRENT_APP_CD = "GLOBAL_KEY_CURRENT_APP_CD";
	
	// 左上角LOGO图片
	public static final String GLOBAL_LEFT_LOGO = "GLOBAL_LEFT_LOGO";
	
	// 下方版权文字
	public static final String GLOBAL_KEY_COPYING_TEXT = "GLOBAL_KEY_COPYING_TEXT";
	
	// 风格样式
	public static final String GLOBAL_KEY_CSS = "GLOBAL_KEY_CSS";
	
	// 登录页面提示信息
	public static final String GLOBAL_KEY_MESSAGE = "GLOBAL_KEY_MESSAGE";
	
	// 浏览器标题文字
	public static final String GLOBAL_KEY_TITLE = "GLOBAL_KEY_TITLE";
	// 短信发送标识
	public static final String GLOBAL_SMS_FLG = "GLOBAL_SMS_FLG";
	//网批节点配置(1=生效，0=失效)
	public static final String RES_PLAS_NODE_SET = "RES_PLAS_NODE_SET";
	
	
	// (2)应用参数
	
	// 定义请求校验的应用CD
	public static final String APP_UAAP_REQUEST_APP_CD = "APP_UAAP_REQUEST_APP_CD";
	
	// 定义存放用户ID的参数名
	public static final String APP_UAAP_SESSION_UIID = "APP_UAAP_SESSION_UIID";
	
	// 定义UAAP应用地址
	public static final String APP_UAAP_URL = "APP_UAAP_URL";

	// 机构根节点代码/业务编号/名称
	public static final String APP_ORG_TREE_ROOT_CD = "APP_ORG_TREE_ROOT_CD";
	public static final String APP_ORG_TREE_ROOT_BIZ_CD = "APP_ORG_TREE_ROOT_BIZ_CD";
	public static final String APP_ORG_TREE_ROOT_NAME = "APP_ORG_TREE_ROOT_NAME";
	// 各地产公司的父节点
	public static final String APP_ALL_PROJS_ROOT = "APP_ALL_PROJS_ROOT";
	//周二的【执行总裁例会】事务开关
	public static final String APP_CEO_MEETING_NOTIFY = "APP_CEO_MEETING_NOTIFY";

	// (3)序列号CD
	public static final String SEQ_ORG_CD = "SEQ_ORG_CD";
	public static final String SEQ_USER_CD = "SEQ_USER_CD";
	public static final String SEQ_ROLE_CD = "SEQ_ROLE_CD";
	public static final String SEQ_APP_CD = "SEQ_APP_CD";
	public static final String SEQ_DOMAIN_CD = "SEQ_DOMAIN_CD";
	public static final String SEQ_BRODCAST_CD = "SEQ_BRODCAST_CD";
	
	public static final String SEQ_MODULE_CD = "SEQ_MODULE_CD";
	public static final String SEQ_MENU_CD = "SEQ_MENU_CD";
	public static final String SEQ_PAGE_CD = "SEQ_PAGE_CD";
	public static final String SEQ_FUNCTION_CD = "SEQ_FUNCTION_CD";

	public static final String SEQ_PARAM_CD = "SEQ_PARAM_CD";

	// 管理员角色
	public static final String A_ADMIN 			= "A_ADMIN";
	public static final String A_ADMIN_SUPER 	= "A_ADMIN_SUPER";
	public static final String A_ADMIN_UAAP_ORG	= "A_ADMIN_UAAP_ORG";
	public static final String A_ADMIN_UAAP_USER= "A_ADMIN_UAAP_USER";
	public static final String A_ADMIN_UAAP_APP = "A_ADMIN_UAAP_APP";
	public static final String A_ADMIN_UAAP_ROLE= "A_ADMIN_UAAP_ROLE";
	public static final String A_ADMIN_UAAP_RESOURCE = "A_ADMIN_UAAP_RESOURCE";

	public static final String A_USER 			= "A_USER";
	public static final String A_COM_LOGIN = "A_COM_LOGIN";// 新增用户时,默认角色

	// 其他邮箱(4028778130bf746a0130bf7553d60001)
	// ->其他(4028778130bf746a0130bf786c03000c);
	public static final String ORG_OTHER_EMAIL_GROUP = "4028778130bf746a0130bf7553d60001";
	public static final String ORG_OTHER_EMAIL = "4028778130bf746a0130bf786c03000c";
	
	
	// *********************************业务参数************************************

	// UAAP项目的应用业务编号
	public static final String UAAP_BIZ_CD = "00000";
	public static final String PD_BIZ_CD = "00001";

	// UAAP管理组机构业务编号
	public static final String UAAP_ORG_BIZ_CD = "9999999999";

	// 全局"高级管理员"业务编号
	public static final String BIZ_CD_A_SUPER_ADMIN = "00001001";

	// 全局"高级监审员"业务编号
	public static final String BIZ_CD_A_SUPER_MONITOR = "00001002";

	// 密码验证次数,-1冻结 -9-不存在账号 9999不限次数
	public static int NONE_FAILURE_FROZEN = -1;
	public static int NONE_FAILURE_NOTEXIST = -9;
	public static int NONE_FAILURE_NOCHECK = 9999;

	// 这里写死决策层和软件组的机构CD(与数据库对应)
	public static String ESTATE_ORG_CD = "23";// 宝龙地产
	public static String PROJECT_ORG_CD = "135";// 各项目公司
	public static String EXEC_ORG_CD = "168";// 决策层
	public static String SOFT_ORG_CD = "149";// 软件组
	public static String WYS_ORG_CD = "243";// 武夷山不做了
	
	public static String DIME_CD_LOGIC = "2";//逻辑维度CD
	public static String DIME_CD_PHYSICS = "1";//物理维度CD

	// *******************************MSN服务应用参数*******************************

	// WEBIM地址前缀
	public static final String GLOBAL_KEY_WEBIM_PRE_URL = "GLOBAL_KEY_WEBIM_PRE_URL";

	// WEBIM上下文
	public static final String GLOBAL_KEY_WEBIM_CONTEXT = "GLOBAL_KEY_WEBIM_CONTEXT";

	// 是否启用锁定用户
	public static final String GLOBAL_KEY_DEFAULT_MAC_LOCKED_FLG = "GLOBAL_KEY_DEFAULT_MAC_LOCKED_FLG";

	// 默认最大密码失败次数
	public static final String GLOBAL_KEY_DEFAULT_FAILURE_TIMES = "GLOBAL_KEY_DEFAULT_FAILURE_TIMES";
	public static String NONE_FAILURE_TIMES = "-1";// 不控制失败次数

	// 执行计划-超级管理员角色
	public static final String A_EXECPLAN_SUP_ADMIN = "A_EXECPLAN_SUP_ADMIN";
	// 执行计划-项目管理员角色(默认)
	public static final String A_EXECPLAN_PJ_ADMIN = "A_EXECP_PJ_ADMIN";
	// 执行计划-信息录入员角色
	public static final String A_EXECPLAN_INPUTOR = "A_EXECPLAN_INPUTOR";
	// 执行计划-浏览用户角色
	public static final String A_EXECPLAN_VIEWER = "A_EXECP_VIEWER";
	// 控制计划-指定项目管理员角色前缀
	public static final String A_EXECPLAN_PJ_ADMIN_PREFIX = "A_EXECP_PJ_AD_";
	

	// 成本计划-超级管理员角色
	public static final String A_COSTP_SUP_ADMIN = "A_COSTP_SUP_ADMIN";
	// 成本计划-项目管理员角色(默认)
	public static final String A_COSTP_PJ_ADMIN = "A_COSTP_PJ_ADMIN";
	// 成本计划-信息录入员角色
	public static final String A_COSTP_INPUTOR = "A_COSTP_INPUTOR";
	// 成本计划-浏览用户角色
	public static final String A_COSTP_VIEWER = "A_COSTP_VIEWER";
	// 成本计划-指定项目管理员角色前缀
	public static final String A_COSTP_PJ_ADMIN_PREFIX = "A_COSTP_PJ_AD_";
	

	// EAS用户(获取用角色列表时,返回)
	// public static final String A_EAS_USER = "A_EAS_USER";
	
	// *******************************商业软件角色 *******************************
	
	// ***********商家库***********
	/**
	 * 商家库搜索-个人
	 */
	public static final String A_SHOP_QUERY_PERS = "A_SHOP_QUERY_PERS";
	/**
	 * 商家库搜索-部门
	 */
	public static final String A_SHOP_QUERY_DEPA = "A_SHOP_QUERY_DEPA";
	/**
	 * 商家库搜索-主、次主力店
	 */
	public static final String A_SHOP_QUERY_MAIN = "A_SHOP_QUERY_MAIN";
	/**
	 * 商家库搜索-小商铺
	 */
	public static final String A_SHOP_QUERY_UNIM = "A_SHOP_QUERY_UNIM";
	/**
	 * 商家库搜索-全部
	 */
	public static final String A_SHOP_QUERY_ALL = "A_SHOP_QUERY_ALL";
	/**
	 * 商家库新增-主、次主力店
	 */
	public static final String A_SHOP_INSE_MAIN = "A_SHOP_INSE_MAIN";
	/**
	 * 商家库新增-小商铺
	 */
	public static final String A_SHOP_INSE_UNIM = "A_SHOP_INSE_UNIM";
	/**
	 * 商家库预审核
	 */
	public static final String A_SHOP_AUDITION = "A_SHOP_AUDITION";
	/**
	 * 商家库审核-主、次主力店
	 */
	public static final String A_SHOP_CHECK_MAIN = "A_SHOP_CHECK_MAIN";
	/**
	 * 商家库审核-小商铺
	 */
	public static final String A_SHOP_CHECK_UNIM = "A_SHOP_CHECK_UNIM";
	/**
	 * 商家库管理员
	 */
	public static final String A_SHOP_ADMIN = "A_SHOP_ADMIN";
	/**
	 * 商家评级信息查看
	 */
	public static final String A_SHOP_LEVEL_QUER = "A_SHOP_LEVEL_QUER";
	/**
	 * 商家合同汇总信息查看
	 */
	public static final String A_SHOP_CONT_QUER = "A_SHOP_CONT_QUER";
	/**
	 * 商家费用汇总信息查看
	 */
	public static final String A_SHOP_COST_QUER = "A_SHOP_COST_QUER";
	// ***********商家库***********
	
	// ***********基础资料***********
	/**
	 * 基础资料搜索
	 */
	public static final String A_PROJ_PROP_QUERY = "A_PROJ_PROP_QUERY";
	/**
	 * 基础资料维护
	 */
	public static final String A_PROJ_PROP_MODI = "A_PROJ_PROP_MODI";
	/**
	 * 资料管理维护
	 */
	public static final String A_PROJ_DATA_MODI = "A_PROJ_DATA_MODI";
	// ***********基础资料***********
	
	// ***********合同***********
	/**
	 * 合同搜索-个人
	 */
	public static final String A_CONT_QUERY_PERS = "A_CONT_QUERY_PERS";
	/**
	 * 合同搜索
	 */
	public static final String A_CONT_QUERY_ALL = "A_CONT_QUERY_ALL";
	/**
	 * 合同新增/发起变更
	 */
	public static final String A_CONT_INSERT = "A_CONT_INSERT";
	/**
	 * 合同审核
	 */
	public static final String A_CONT_CHECK = "A_CONT_CHECK";
	/**
	 * 合同驳回
	 */
	public static final String A_CONT_REJECT = "A_CONT_REJECT";
	/**
	 * 合同失效
	 */
	public static final String A_CONT_FAIL = "A_CONT_FAIL";
	// ***********合同***********

	// ***********应收款***********
	/**
	 * 应收款查看
	 */
	public static final String A_MUST_QUERY = "A_MUST_QUERY";
	/**
	 * 应收款新增
	 */
	public static final String A_MUST_INSERT = "A_MUST_INSERT";
	/**
	 * 应收款审核
	 */
	public static final String A_MUST_CHECK = "A_MUST_CHECK";
	/**
	 * 应收款驳回
	 */
	public static final String A_MUST_REJECT = "A_MUST_REJECT";
	/**
	 * 应收款删除
	 */
	public static final String A_MUST_DELETE = "A_MUST_DELETE";
	// ***********应收款***********

	// ***********收费***********
	/**
	 * 收费搜索
	 */
	public static final String A_FACT_QUERY = "A_FACT_QUERY";
	/**
	 * 收费新增/抵充/修改
	 */
	public static final String A_FACT_INSERT = "A_FACT_INSERT";
	/**
	 * 收费审核
	 */
	public static final String A_FACT_CHECK = "A_FACT_CHECK";
	/**
	 * 收费驳回
	 */
	public static final String A_FACT_REJECT = "A_FACT_REJECT";
	/**
	 * 收费删除
	 */
	public static final String A_FACT_DELETE = "A_FACT_DELETE";
	// ***********收费***********
	
	// ***********能源费***********
	/**
	 * 能源费搜索
	 */
	public static final String A_ENER_QUERY = "A_ENER_QUERY";
	/**
	 * 能源费导入
	 */
	public static final String A_ENER_IMPORT = "A_ENER_IMPORT";
	// ***********能源费***********

	// ***********支出***********
	/**
	 * 支出搜索
	 */
	public static final String A_EXPE_QUERY = "A_EXPE_QUERY";
	/**
	 * 支出新增/修改
	 */
	public static final String A_EXPE_INSERT = "A_EXPE_INSERT";
	/**
	 * 支出删除
	 */
	public static final String A_EXPE_DELETE = "A_EXPE_DELETE";
	// ***********支出***********
	
	// ***********报表***********
	/**
	 * 集团报表搜索
	 */
	public static final String A_REPO_GROUP_QUERY = "A_REPO_GROUP_QUERY";
	/**
	 * 项目报表搜索
	 */
	public static final String A_REPO_PROJ_QUERY = "A_REPO_PROJ_QUERY";
	/**
	 * 项目报表欠费搜索
	 */
	public static final String A_REPO_ARRE_QUERY = "A_REPO_ARRE_QUERY";
	/**
	 * 项目明细报表搜索
	 */
	public static final String A_REPO_DETA_QUERY = "A_REPO_DETA_QUERY";
	// ***********报表***********
	
	// ***********项目总况***********
	/**
	 * 项目总况信息搜索
	 */
	public static final String A_TOTAL_QUERY = "A_TOTAL_QUERY";
	/**
	 * 项目总况资料信息搜索
	 */
	public static final String A_TOTAL_DATA_QUERY = "A_TOTAL_DATA_QUERY";
	// ***********项目总况***********

	// ***********平面图***********
	/**
	 * 平面图搜索/租户台账搜索
	 */
	public static final String A_PLAN_QUERY = "A_PLAN_QUERY";
	// ***********平面图***********
	
	// ***********租户台账***********
	/**
	 * 租户单据打印 
	 */
	public static final String A_TENE_PRINT = "A_TENE_PRINT";
	/**
	 * 租户退铺发起
	 */
	public static final String A_TENE_OUT_LAUN = "A_TENE_OUT_LAUN";
	/**
	 * 租户退铺费用核销
	 */
	public static final String A_TENE_OUT_VERI = "A_TENE_OUT_VERI";
	/**
	 * 租户退铺审核
	 */
	public static final String A_TENE_OUT_CHECK = "A_TENE_OUT_CHECK";
	// ***********租户台账***********
	
	// *****************以下为老版权限*****************
	/**
	 * 决策层
	 */
	public static final String A_BIS_INVESTOR = "A_BIS_INVESTOR";
	/**
	 * 商业集团财务副总
	 */
	public static final String A_BIS_VI_FI_MANAGER = "A_BIS_VI_FI_MANAGER";
	/**
	 * 商业集团财务人员
	 */
	public static final String A_BIS_HQ_FINA = "A_BIS_HQ_FINA";
	/**
	 * 商业集团财务总经理
	 */
	public static final String A_BIS_HQ_FI_MANAGER = "A_BIS_HQ_FI_MANAGER";
	/**
	 * 商业集团副总经理
	 */
	public static final String A_BIS_VI_MANAGER = "A_BIS_VI_MANAGER";
	/**
	 * 商业集团系统管理员
	 */
	public static final String A_BIS_SYSTEM_ADMIN = "A_BIS_SYSTEM_ADMIN";
	/**
	 * 商业集团营运副总
	 */
	public static final String A_BIS_VI_OP_MANAGER = "A_BIS_VI_OP_MANAGER";
	/**
	 * 商业集团营运人员
	 */
	public static final String A_BIS_HQ_OPER = "A_BIS_HQ_OPER";
	/**
	 * 商业集团营运总经理
	 */
	public static final String A_BIS_HQ_OP_MANAGER = "A_BIS_HQ_OP_MANAGER";
	/**
	 * 商业集团招商副总
	 */
	public static final String A_BIS_VI_AD_MANAGER = "A_BIS_VI_AD_MANAGER";
	/**
	 * 商业集团招商人员
	 */
	public static final String A_BIS_HQ_ADVE = "A_BIS_HQ_ADVE";
	/**
	 * 商业集团招商总经理
	 */
	public static final String A_BIS_HQ_AD_MANAGER = "A_BIS_HQ_AD_MANAGER";
	/**
	 * 商业集团总经理
	 */
	public static final String A_BIS_HQ_MANAGER = "A_BIS_HQ_MANAGER";
	/**
	 * 项目财务经理
	 */
	public static final String A_BIS_PROJ_VI_FINA = "A_BIS_PROJ_VI_FINA";
	/**
	 * 项目财务人员
	 */
	public static final String A_BIS_PROJ_FINA = "A_BIS_PROJ_FINA";
	/**
	 * 项目公寓管理人员
	 */
	public static final String A_BIS_PROJ_FLAT = "A_BIS_PROJ_FLAT";
	/**
	 * 项目管理员
	 */
	public static final String A_BIS_PROJ_ADMIN = "A_BIS_PROJ_ADMIN";
	/**
	 * 项目其他费用和支出输入人员
	 */
	public static final String A_BIS_PROJ_MULTI = "A_BIS_PROJ_MULTI";
	/**
	 * 项目商铺管理人员
	 */
	public static final String A_BIS_PROJ_STORE = "A_BIS_PROJ_STORE";
	/**
	 * 项目营运经理
	 */
	public static final String A_BIS_PROJ_VI_OPER = "A_BIS_PROJ_VI_OPER";
	/**
	 * 项目招商经理
	 */
	public static final String A_BIS_PROJ_VI_ADVE = "A_BIS_PROJ_VI_ADVE";
	/**
	 * 项目招商人员
	 */
	public static final String A_BIS_PROJ_ADVE = "A_BIS_PROJ_ADVE";
	/**
	 * 项目总经理
	 */
	public static final String A_BIS_PROJ_MANAGER = "A_BIS_PROJ_MANAGER";
	
	// *******************************商业软件角色 *******************************
	
}
