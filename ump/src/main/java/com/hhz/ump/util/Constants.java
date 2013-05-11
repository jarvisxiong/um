package com.hhz.ump.util;

import java.util.List;
import java.util.Map;

import org.springside.modules.orm.PropertyFilter;

import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.plan.ExecPlanNode;
import com.hhz.ump.entity.plan.ExecPlanNodeParentVo;

public class Constants {
	public static final String ADMIN = "admin";
	public static final String XUJK = "xujk";
	public static final String TRUE = "true";

	public static final String PATTERN_NUM = "^[-]{0,1}[0-9]+(\\.[0-9]{0,6}){0,1}$";
	/**
	 * 正常字符
	 */
	public static final String PATTERN_NORMAL = "[\u4e00-\u9fa5a-zA-Z0-9,\\.?:;!\\-]+";

	/**
	 * 上传文件最大限制,默认40M
	 */
	public static final Long MAX_FILE_SIZE = 40960000L;

	/**
	 * 数据操作类型
	 */
	public static final String OPERATE_MODE_FLG_READ = "0";

	public static final String OPERATE_MODE_FLG_EDIT = "1";

	public static final Integer PAGE_SIZE = 10;

	public static final String JBPM_ENTITY_ID = "entityId";

	/**
	 * 标记财务是否已经审批
	 */
	public static final String JBPM_FIN_FLG = "finFlg";

	/**
	 * 审批意见:0-申请，1-同意，2-驳回
	 */
	public static final String JBPM_OPTION = "option";

	/**
	 * 机构列表
	 */
	public static final String JBPM_UAAP_ORGS = "uaapOrgs";

	/**
	 * 上一步骤任务ID
	 */
	public static final String JBPM_PRE_TASK_ID = "preTaskId";

	/**
	 * 上一步骤任务动态名称
	 */
	public static final String JBPM_PRE_ACTIVE_NAME = "preActiveName";

	/**
	 * 角色：总裁办主任
	 */
	public static final String JBPM_ROLE_XZZJL = "A_JBPM_XZZX_G";

	/**
	 * 角色：费用会计
	 */
	public static final String JBPM_ROLE_FYKJ = "A_JBPM_FYKJ";

	/**
	 * 角色：费用会计2
	 */
	public static final String JBPM_ROLE_FYKJ2 = "A_JBPM_FYKJ_2";

	/**
	 * 角色：费用会计3
	 */
	public static final String JBPM_ROLE_FYKJ3 = "A_JBPM_FYKJ_3";
	/**
	 * 角色：费用会计4
	 */
	public static final String JBPM_ROLE_FYKJ4 = "A_JBPM_FYKJ_4";

	/**
	 * 角色：工作计划_决策层用户
	 */
	public static final String A_PLAN_WORK_MANAGER = "A_PLAN_WORK_MANAGER";

	/**
	 * 角色：工作计划_各公司/中心用户
	 */
	public static final String A_PLAN_WORK_USER = "A_PLAN_WORK_USER";

	/**
	 * 角色：工作计划_地产用户
	 */
	public static final String A_PLAN_WORK_USER_DC = "A_PLAN_WORK_USER_DC";

	/**
	 * 角色：工作计划_管理员
	 */
	public static final String A_PLAN_WORK_ADMIN = "A_PLAN_WORK_ADMIN";

	/**
	 * 角色：工作计划_浏览用户
	 */
	public static final String A_PLAN_WORK_VIEW = "A_PLAN_WORK_VIEW";
	/**
	 * 角色：工作计划_项目管理中心
	 */
	public static final String A_PLAN_WORK_XMGL = "A_PLAN_WORK_XMGL";
	/**
	 * 角色：权责审批-管理员
	 */
	public static final String A_QZSP_ADMIN = "A_QZSP_ADMIN";
	/**
	 * 角色：权责审批-项目/中心管理员
	 */
	public static final String A_QZSP_V_ADMIN = "A_QZSP_V_ADMIN";

	/**
	 * 技术研发中心,建设开发中心,商业地产研究院,
	 */
	public static final String CENTER_CD_SP_2 = ",12,132,126,";

	/**
	 * 监审部,企业发展部,财务管理中心,资本管理中心,酒店开发中心
	 */
	public static final String CENTER_CD_SP_3 = ",147,107,8,10,134,";
	/**
	 * ,投资发展中心,成本控制中心,营销管理中心,
	 */
	public static final String CENTER_CD_SP_4 = ",11,17,133,";

	/**
	 *监审部
	 */
	public static final String ORG_CD_JSB = "147";
	/**
	 *区域公司
	 */
	public static final String ORG_CD_QYGS = "705";
	/**
	 *宝龙厦门总部
	 */
	public static final String ORG_CD_BLXM = "296";
	/**
	 *宝龙商业
	 */
	public static final String ORG_CD_BLSY = "153";
	/**
	 *宝龙行业
	 */
	public static final String ORG_CD_BLHY = "451";
	/**
	 *宝龙地产
	 */
	public static final String ORG_CD_BLDC = "23";
	/**
	 *各地产公司
	 */
	public static final String ORG_CD_DCGS = "135";
	
	/**
	 * 机构：各商业公司
	 */
	public static final String ORG_CD_SYGS = "512";
	/**
	 * 机构：各酒店公司
	 */
	public static final String ORG_CD_JDGS = "443";
	/**
	 *机构：各百货公司
	 */
	public static final String ORG_CD_BHGS = "459";
	/**
	 *机构：各KTV公司
	 */
	public static final String ORG_CD_KTVGS = "1035";
	
	/**
	 * 角色：会计经理
	 */
	public static final String JBPM_ROLE_KJJL = "A_JBPM_KJJL_M";

	/**
	 * 角色：财务中心副总
	 */
	public static final String JBPM_ROLE_CWZXFZ = "A_JBPM_CWZX_VG";

	/**
	 * 角色：财务中心总经理
	 */
	public static final String JBPM_ROLE_CWZXZJL = "A_JBPM_CWZX_G";

	/**
	 * 角色：财务副总裁
	 */
	public static final String JBPM_ROLE_CWFZC = "A_JBPM_CW_VG";

	/**
	 * 角色：执行总裁
	 */
	public static final String JBPM_ROLE_CEO = "A_JBPM_CEO";

	/**
	 * 角色：出纳
	 */
	public static final String JBPM_ROLE_CN = "A_ROLE_CN";

	/**
	 * 角色：审批
	 */
	public static final String JBPM_ROLE = "A_JBPM_ROLE";

	/**
	 * 角色：人事
	 */
	public static final String ROLE_RENSHI = "A_ROLE_RENSHI";

	/**
	 * 角色：机票
	 */
	public static final String ROLE_JIPIAO = "A_ROLE_JIPIAO";

	/**
	 * 审批状态，完成审批
	 */
	public static final String JBPM_STATUS_COMPLETE = "1";

	/**
	 * 审批状态，审批中
	 */
	public static final String JBPM_STATUS_AUDITING = "0";

	/**
	 * 审批状态，新建
	 */
	public static final String JBPM_STATUS_NEW = "-1";

	/**
	 * 审批状态，驳回
	 */
	public static final String JBPM_STATUS_RETURN = "2";

	/**
	 * 审批状态，取消
	 */
	public static final String JBPM_STATUS_CANCEL = "3";

	/**
	 * 报销审批，已发放
	 */
	public static final String JBPM_STATUS_R_SENT = "4";

	/**
	 * 出差审批，转报销
	 */
	public static final String JBPM_STATUS_T_REIM = "4";

	/**
	 * 员工
	 */
	public static final String DICT_POSITION_CD_COMMON = "100";

	/**
	 * 经理
	 */
	public static final String DICT_POSITION_CD_MANAGER = "201";

	/**
	 * 中心副总经理
	 */
	public static final String DICT_POSITION_CD_VICE_GENERAL_MANAGER = "400";

	/**
	 * 中心总经理
	 */
	public static final String DICT_POSITION_CD_GENERAL_MANAGER = "401";

	/**
	 * 副总裁
	 */
	public static final String DICT_POSITION_CD_VICE_CEO = "420001";

	/**
	 * 执行总裁
	 */
	public static final String DICT_POSITION_CD_CEO = "460001";

	/**
	 * 机构类型：部门
	 */
	public static final String ORG_TYPE_CD_BM = "1";

	/**
	 * 机构类型：中心
	 */
	public static final String ORG_TYPE_CD_ZX = "2";

	/**
	 * 机构类型：分管
	 */
	public static final String ORG_TYPE_CD_FG = "3";

	/**
	 * 机构类型：集团
	 */
	public static final String ORG_TYPE_CD_JT = "4";

	/**
	 * 机构类型：小组
	 */
	public static final String ORG_TYPE_CD_XZ = "5";

	/**
	 * 机构：各地产公司
	 */
	public static final String ORG_BIZ_CD_DCGS = "1099";

	/**
	 * 会议室状态：申请
	 */
	public static final String OA_MEETING_ROOM_SQ = "0";

	/**
	 * 会议室状态：预定成功
	 */
	public static final String OA_MEETING_ROOM_YD = "1";

	/**
	 * 会议室状态：删除
	 */
	public static final String OA_MEETING_ROOM_SX = "2";

	/**
	 * 会议室状态：作废
	 */
	public static final String OA_MEETING_ROOM_ZF = "3";
	/**
	 * 会议室状态：总裁秘书审核
	 */
	public static final String OA_MEETING_ROOM_Z = "4";
	/**
	 * 会议室状态：执行总裁秘书审核
	 */
	public static final String OA_MEETING_ROOM_ZZ = "5";

	/**
	 * 角色：总裁预约管理员
	 */
	public static final String CHAIRMAN_RES = "A_CHAIRMAN_RES_ADMIN";

	/**
	 * 角色：执行总裁预约管理员
	 */
	public static final String CEO_RES = "A_CEO_RES_ADMIN";

	/**
	 * 角色：总裁预约明细查看
	 */
	public static final String CEO_RES_BROWSER = "A_CEO_RES_BROWSER";

	// 工作计划4个角色
	// 管理员
	public static final String PLAN_WORK_ADMIN = "A_PLAN_WORK_ADMIN";

	// 管理层
	public static final String PLAN_WORK_MANAGER = "A_PLAN_WORK_MANAGER";

	// 各公司/中心用户
	public static final String PLAN_WORK_USER = "A_PLAN_WORK_USER";

	// 其他用户
	public static final String PLAN_WORK_VIEW = "A_PLAN_WORK_VIEW";
	
	//系统管理员
	public static final String SYS_ADMIN = "sys_admin";
	
	//机构管理员
	public static final String GROUP_ADMIN = "group_admin";
	
	
	//成本预算管理员
	public static final String A_COST_BUD_ADMIN = "A_COST_BUD_ADMIN";
	
	//收件人
	public static final String TO = "to";
	
	//抄送人
	public static final String CC = "cc";
	
	//密送人
	public static final String BCC = "bcc";
	
	/**
	 * 总裁
	 */
	public static final String CHAIRMAN = "xujk";

	/**
	 * 执行总裁
	 */
	public static final String CEO = "xuhf";
	public static final String PROD_IP = "192.168.180.240";
	//成本计划：数据来源:半年计划
	public static final String SRC_TYPE_YEAR="2";
	//成本计划：数据来源：执行计划
	public static final String SRC_TYPE_EXEC="3";
	
	
	/*北方:
		郑州中原地产公司	178
		郑州郑东地产公司	554
		洛阳地产公司		196
		蚌埠地产公司		205
		城阳地产公司		212
		苏州地产公司		220
		无锡旺庄地产公司	228
		无锡玉祁地产公司	236
		宿迁地产公司		244
		新乡地产公司		252
		盐城地产公司		259
		烟台地产公司		267
		李沧地产公司		274
		常州地产公司		280
		即墨地产公司		288
		泰安地产公司		187
		济宁地产公司		540
		镇江地产公司		558
		天津城市公司于家堡项目	440


		南方:
		泉州安溪地产公司	294
		重庆地产公司		499
		泉州晋江地产公司	500
		福州地产公司		514
		杭州地产公司		541
		厦门城市公司		296
		厦门城市公司酒店项目	303
		厦门城市公司湖边水库项目537

		上海区域：
		上海曹路项目		673
		上海项目公司		526
		**********************************************/
	//北方区域公司
	//public static final String NORTH_PROJECTS="178,554,196,205,212,220,228,236,244,252,259,267,274,280,288,187,540,558,440";
	//南方区域公司
	//public static final String SOURTH_PROJECTS="294,499,500,514,526,541,673,296,303,537";
	//上海区域公司
	//public static final String SH_PROJECTS="526,673";
	public static List<PropertyFilter> filters;//指令单跟踪模块-触发excel导出的临时值
	public static List<ExecPlanLayout> searchedPlansNew;		//现在正在用的业态
	public static Map<String, String> mapNodeNameNew;
	public static Map<String, ExecPlanDetail> planDetailMapNew;
	public static Map<String, String> mapPrintStartDateNew; // 列表中显示的HTML
	public static Map<String, String> mapPrintEndDateNew; // 列表中显示的HTML
	public static Map<String, ExecPlanNodeParentVo> parentNodeVoMapNew;
	public static Map<String, String> mapPrintStartDate116; // 列表中显示的HTML 
	public static Map<String, String> mapPrintEndDate116;
	public static List<ExecPlanNode> viewPlanNodes16; // 显示的节点列表
	
	public static final String ORG_CD_NFQY="706";//南方区域公司
	public static final String ORG_CD_BFQY="707";//北方区域公司
}
