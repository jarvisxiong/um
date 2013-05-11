package com.hhz.uums.utils;

public class GlobalConstants {
 


	// 以下常量与APP_PARAM(参数配置定义表)保持一致

	// (1)全局参数

	// 应用项目编号
	public static final String GLOBAL_KEY_CURRENT_APP_CD = "GLOBAL_KEY_CURRENT_APP_CD";
	
	// 左上角LOGO图片
	public static final String GLOBAL_LEFT_LOGO		 	= "GLOBAL_LEFT_LOGO";
	
	// 下方版权文字
	public static final String GLOBAL_KEY_COPYING_TEXT 	= "GLOBAL_KEY_COPYING_TEXT";
	
	// 风格样式
	public static final String GLOBAL_KEY_CSS 			= "GLOBAL_KEY_CSS";
	
	// 登录页面提示信息
	public static final String GLOBAL_KEY_MESSAGE 		= "GLOBAL_KEY_MESSAGE";
	
	// 浏览器标题文字
	public static final String GLOBAL_KEY_TITLE 		= "GLOBAL_KEY_TITLE";
	
	
	// (2)应用参数
	
	// 定义请求校验的应用CD
	public static final String APP_UAAP_REQUEST_APP_CD 	= "APP_UAAP_REQUEST_APP_CD";
	
	// 定义存放用户ID的参数名
	public static final String APP_UAAP_SESSION_UIID 	= "APP_UAAP_SESSION_UIID";
	
	// 定义UAAP应用地址
	public static final String APP_UAAP_URL 			= "APP_UAAP_URL";

//	// 机构根节点代码/业务编号/名称
//	public static final String APP_ORG_TREE_ROOT_CD 	= "APP_ORG_TREE_ROOT_CD";
//	public static final String APP_ORG_TREE_ROOT_BIZ_CD = "APP_ORG_TREE_ROOT_BIZ_CD";
//	public static final String APP_ORG_TREE_ROOT_NAME 	= "APP_ORG_TREE_ROOT_NAME";
		
	// (3)序列号CD
	public static final String SEQ_ORG_CD 		= "SEQ_ORG_CD";
	public static final String SEQ_USER_CD 		= "SEQ_USER_CD";
	public static final String SEQ_ROLE_CD 		= "SEQ_ROLE_CD";
	public static final String SEQ_ROLE_GROUP_CD = "SEQ_ROLE_GROUP_CD";
	public static final String SEQ_APP_CD 		= "SEQ_APP_CD";
	public static final String SEQ_DOMAIN_CD 	= "SEQ_DOMAIN_CD";
	public static final String SEQ_BRODCAST_CD 	= "SEQ_BRODCAST_CD";
	public static final String SEQ_SYSPOS_CD 	= "SEQ_SYSPOS_CD";
	
	public static final String SEQ_MODULE_CD	= "SEQ_MODULE_CD";
	public static final String SEQ_MENU_CD 		= "SEQ_MENU_CD";
	public static final String SEQ_PAGE_CD 		= "SEQ_PAGE_CD";
	public static final String SEQ_FUNCTION_CD 	= "SEQ_FUNCTION_CD";

	public static final String SEQ_PARAM_CD 	= "SEQ_PARAM_CD";

	// 树节点
	public static final String NODE_TYPE_ORG 	= "org";
	public static final String NODE_TYPE_USR 	= "user";
	public static final String NODE_TYPE_APP 	= "app";
	public static final String NODE_TYPE_ROLE 	= "role";

	// 管理员角色CD
	public static final String A_ADMIN_AUDIT = "A_ADMIN_AUDIT";// 监审管理员
	public static final String A_ADMIN_SUPER = "A_ADMIN_SUPER";// 超级管理员
	public static final String A_ADMIN = "A_ADMIN"; // 普通管理员
	
	public static final String A_ADMIN_UAAP_APP = "A_ADMIN_UAAP_APP";// UAAP应用管理员
	public static final String A_ADMIN_UAAP_ORG = "A_ADMIN_UAAP_ORG";// UAAP机构管理员
//	public static final String A_ADMIN_UAAP_USER = "A_ADMIN_UAAP_USER";// UAAP用户管理员
	public static final String A_ADMIN_UAAP_ROLE = "A_ADMIN_UAAP_ROLE";// UAAP角色管理员
	public static final String A_ADMIN_UAAP_RESOURCE = "A_ADMIN_UAAP_RESOURCE";// UAAP资源管理员
	public static final String A_ADMIN_HR_CHIEF = "A_ADMIN_HR_CHIEF";// 人力资源管理员
	public static final String A_COM_LOGIN = "A_COM_LOGIN";// 新增用户时,默认角色


	public static final String A_USER = "A_USER";
	public static final String A_HELP_USER = "A_HELP_USER";

	//邮箱用户
	public static final String A_MAIL_USER = "A_MAIL_USER";
	
 
	// 	角色编码			角色名称
	//1	A_HR_GROUP		总部人事
	//2	A_BIZ_CENTER	营运中心管理员
	//3	A_HR_ADMIN		HR管理员
	//4	A_HR_AREA		区域人事(A_HR_NQ/A_HR_BQ/A_HR_SY/A_HR_HY/A_HR_YS/A_HR_JD/A_HR_BLHK)
	//5	A_HR_ESTATE		地产人事

	public static final String A_HR_GROUP = "A_HR_GROUP";
	public static final String A_BIZ_CENTER = "A_BIZ_CENTER";
	public static final String A_HR_ADMIN = "A_HR_ADMIN";
	public static final String A_HR_AREA = "A_HR_AREA";
	
	public static final String A_HR_NQ = "A_HR_NQ";
	public static final String A_HR_BQ = "A_HR_BQ";
	public static final String A_HR_SY = "A_HR_SY";
//	public static final String A_HR_HY = "A_HR_HY";//-> A_HR_DW
	public static final String A_HR_YS = "A_HR_YS";
	public static final String A_HR_JD = "A_HR_JD";
//	public static final String A_HR_DW = "A_HR_DW";
	public static final String A_HR_BLHK = "A_HR_BLHK";
	
	
	
	
	public static final String A_HR_ESTATE = "A_HR_ESTATE";	//地产公司人事(项目)
	public static final String A_HR_ESSY = "A_HR_ESSY";		//商业公司人事(项目)
	public static final String A_HR_JDGS = "A_HR_JDGS";		//酒店公司人事(项目)
	public static final String A_HR_YSGS = "A_HR_YSGS";		//事业公司人事(项目)
	public static final String A_HR_BHGS = "A_HR_BHGS";		//百货公司人事
	public static final String A_HR_KTVGS = "A_HR_KTVGS";	//KTV门店人事
	public static final String A_HR_DWGS = "A_HR_DWGS";		//电玩门店人事

	// *********************************业务参数************************************

	// PLAS项目的应用业务编号
	public static final String UAAP_BIZ_CD = "00000";
	// pd
	public static final String PD_BIZ_CD = "00001";
	// 帮助系统
	public static final String HELP_PROJECT_BIZ_CD = "00002";

	// UAAP管理组机构业务编号
	public static final String UAAP_ORG_BIZ_CD 	= "9999999999";
	public static final String UAAP_ORG_NAME = "宝龙集团";
	
	// 其他邮箱(4028778130bf746a0130bf7553d60001)
	// ->其他(4028778130bf746a0130bf786c03000c);
	public static final String ORG_OTHER_EMAIL_GROUP = "4028778130bf746a0130bf7553d60001";
	public static final String ORG_OTHER_EMAIL = "4028778130bf746a0130bf786c03000c";
	
	
	// 全局"高级管理员"业务编号
	public static final String BIZ_CD_A_SUPER_ADMIN = "00001001";

	// 全局"高级监审员"业务编号
	public static final String BIZ_CD_A_SUPER_MONITOR = "00001002";

	// 这里写死决策层和软件组的机构CD(与数据库对应)
	public static String EXEC_ORG_CD = "168";
	public static String SOFT_ORG_CD = "149";

	// 密码验证次数,-1冻结 -9-不存在账号 9999不限次数
	public static int NONE_FAILURE_FROZEN = -1;
	public static int NONE_FAILURE_NOTEXIST = -9;
	public static int NONE_FAILURE_NOCHECK = 9999;

	// 随机产生密码长度
	public static int DEFAULT_PASSWORD_LENGTH = 6;

	// *******************************MSN服务应用参数*******************************

	// WEBIM地址前缀
	public static final String GLOBAL_KEY_WEBIM_PRE_URL = "GLOBAL_KEY_WEBIM_PRE_URL";

	// WEBIM上下文
	public static final String GLOBAL_KEY_WEBIM_CONTEXT = "GLOBAL_KEY_WEBIM_CONTEXT";

	// 默认密码
	public static final String GLOBAL_KEY_DEFAULT_PASSWORD = "GLOBAL_KEY_DEFAULT_PASSWORD";

	// 默认最大密码错误次数
	public static final String GLOBAL_KEY_DEFAULT_FAILURE_TIMES = "GLOBAL_KEY_DEFAULT_FAILURE_TIMES";

	// 是否启用锁定用户
	public static final String GLOBAL_KEY_DEFAULT_MAC_LOCKED_FLG = "GLOBAL_KEY_DEFAULT_MAC_LOCKED_FLG";

	// 是否同步exchange邮箱用户
	public static final String GLOBAL_KEY_DEFAULT_SYN_EMAIL_USER = "GLOBAL_KEY_DEFAULT_SYN_EMAIL_USER";
	// 是否同步coremail邮箱用户
	public static final String GLOBAL_KEY_DEFAULT_SYN_CMAIL_USER = "GLOBAL_KEY_DEFAULT_SYN_CMAIL_USER";
	// 是否同步EAS用户
	public static final String GLOBAL_KEY_DEFAULT_SYN_EAS_USER = "GLOBAL_KEY_DEFAULT_SYN_EAS_USER";
	// 是否同步EAS用户
	public static final String GLOBAL_KEY_DEFAULT_SYN_MYSOFT_USER = "GLOBAL_KEY_DEFAULT_SYN_MYSOFT_USER";
	// 是否同步ADOBE用户
	public static final String GLOBAL_KEY_DEFAULT_SYN_ADOBE_USER = "GLOBAL_KEY_DEFAULT_SYN_ADOBE_USER";

	// EXCHANGE地址前缀 (webservice)
	public static final String GLOBAL_KEY_EXCHANGE_PRE_URL = "GLOBAL_KEY_EXCHANGE_PRE_URL";
	// COREMAIL地址前缀 (webservice)
	public static final String GLOBAL_KEY_COREMAIL_PRE_URL = "GLOBAL_KEY_COREMAIL_PRE_URL";

	// EAS地址前缀(webservice)
	public static final String GLOBAL_KEY_EAS_PRE_URL = "GLOBAL_KEY_EAS_PRE_URL";

	// mysoft地址前缀(webservice)
	public static final String GLOBAL_KEY_MYSOFT_PRE_URL = "GLOBAL_KEY_MYSOFT_PRE_URL";

	// adobe livecycle同步数据地址前缀(webservice)
	public static final String GLOBAL_KEY_LIVECYCLE_URL = "GLOBAL_KEY_LIVECYCLE_URL";

	// 是否启用初始化使用随机密码
	public static final String GLOBAL_KEY_DEFAULT_RANDOM_PWD = "GLOBAL_KEY_DEFAULT_RANDOM_PWD";
	
	// 短信发送开关
	public static final String GLOBAL_KEY_SMS_GATE_FLAG = "GLOBAL_KEY_SMS_GATE_FLAG";
	public static final String GLOBAL_KEY_SMS_GATE_FLAG_YES = "1";
	

	// EAS用户(若用户开通EAS,则在PD登录时,默认赋给角色"A_EAS_USER")
	public static final String A_EAS_USER = "A_EAS_USER";
	public static final String A_EAS_USER_NAME = "EAS用户";
	
	public static final String A_EMAIL_USER = "A_EMAIL_USER";
	public static final String A_EMAIL_USER_NAME = "邮件用户";

	public static final String A_MYSOFT_USER = "A_MYSOFT_USER";
	public static final String A_MYSOFT_USER_NAME = "明源用户";

}
