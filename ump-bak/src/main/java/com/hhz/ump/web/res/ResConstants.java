/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.entity.res.ResNode;

/**
 * <p>
 * 网批静态属性
 * </p>
 * 
 * @author huangjian
 * @create 2012-1-10
 */
public class ResConstants {
	public static Map<String, String> map_ht2db = new HashMap<String, String>();
	static {
		map_ht2db.put("JD_ZCGL_HTQS_20", "JD_ZCGL_DB_10");
	}
	public static final String MODULE_TYPE_CD_RES = "0";// 网上审批
	public static final String MODULE_TYPE_CD_HLH = "1";// 合理化建议
	public static final String MODULE_TYPE_CD_TP = "2";// 特别审批
	// 自定义属性
	public static Set<String> allCustomNode;
	public static Set<String> allCustomNode_muti;
	static {
		allCustomNode = new HashSet<String>();
		allCustomNode_muti = new HashSet<String>();
		refreshCustomNode();
	}

	/**
	 * 刷新所有自定义节点的缓存
	 */
	public static void refreshCustomNode() {
		allCustomNode.clear();
		allCustomNode_muti.clear();
		ResNodeManager resNodeManager = SpringContextHolder.getBean("resNodeManager");
		Criterion c = Restrictions.eq("nodeTypeCd", "2");
		List<ResNode> resNodes = resNodeManager.findBy(c);
		for (ResNode resNode : resNodes) {
			allCustomNode.add(resNode.getNodeCd());
			if (resNode.getIsMuti() != null && resNode.getIsMuti()) {
				allCustomNode_muti.add(resNode.getNodeCd());
			}
		}
	}

	// 延期申请状态:正常
	public static final String DELAY_STATUS_CD_NORMAL = "0";
	// 新增
	public static final String RES_APPROVE_STATUS_NEW = "0";

	// 审批中
	public static final String RES_APPROVE_STATUS_ING = "1";

	// 完成审批
	public static final String RES_APPROVE_STATUS_FINISHL = "2";

	// 驳回
	public static final String RES_APPROVE_STATUS_BACK = "3";

	// 已删除
	public static final String RES_APPROVE_STATUS_DELETED = "4";

	// 待确认
	public static final String RES_APPROVE_STATUS_CONFIRM = "5";

	// 已经手
	public static final String RES_APPROVE_STATUS_MYDEAL = "6";

	// 与我有关的，即将审批
	public static final String RES_APPROVE_STATUS_MYDUTY = "7";
	// 我留言过
	public static final String RES_APPROVE_STATUS_SAID = "8";
	// 共享给我的
	public static final String RES_APPROVE_STATUS_SHARE = "9";
	// 推送给我
	public static final String RES_APPROVE_STATUS_PUSH = "10";
	// 延期申请
	public static final String RES_APPROVE_STATUS_DELAY = "11";
	// 签批
	public static final String RES_APPROVE_STATUS_ENDORSE = "12";
	// 其他审批(授权人)
	public static final String RES_APPROVE_STATUS_ACCT = "13";

	// 同意
	public static final String SP_OPTION_AGREE = "1";
	
	//不同意 二级审批人新增功能
	public static final String SP_OPTION_UNAGREE = "-2";
	// 驳回
	public static final String SP_OPTION_BACK = "0";

	// 撤销
	public static final String SP_OPTION_ROLLBACK = "2";
	// 申请
	public static final String SP_OPTION_APPLY = "3";

	// 未审核
	public static final String SP_OPTION_NOT = "-1";

	// 总裁
	public static final String SP_NODE_CD_ZC = "21";

	// 相关副总裁
	public static final String SP_NODE_CD_XGFZC = "23";
	// 相关中心总经理
	public static final String SP_NODE_CD_XGZXZJL = "22";
	// 发单中心负责人
	public static final String SP_NODE_CD_FDZX = "134";
	// 执行董事(成本)
	public static final String SP_NODE_CD_ZXDS_CB = "43";
	// 执行董事(商业)
	public static final String SP_NODE_CD_ZXDS_SY = "15";

	// 发起人新增审批步骤
	public static final String SP_NODE_CD_SADD = "99";

	// 跟踪人整理步骤
	public static final String SP_NODE_CD_SZL = "97";

	// 合同跟踪人
	public static final String SP_NODE_CD_GZR = "94";

	// 转入合同
	public static final String SP_NODE_CD_ZRHT = "101";

	// 合同发布
	public static final String SP_NODE_CD_HTFB = "100";
	// 决策委员会
	public static final String SP_NODE_CD_JCWYH = "30";
	// 合同跟踪人相同的人员
	public static final String[] CONT_RELA_NODES = { SP_NODE_CD_SZL, SP_NODE_CD_ZRHT, SP_NODE_CD_HTFB };
	// 相关专业评审
	public static final String SP_NODE_CD_XGZYPS = "39";
	// 相关中心会签
	public static final String SP_NODE_CD_XGZXHQ = "129";
	// 审批人
	public static final String SP_NODE_CD_SPR = "117";

	// 是否是会签人员
	public static final String SP_MODIFY_MOD_HQ = "HQ";

	// 跟踪人整理
	public static final String SP_MODIFY_MOD_FQ = "FQ";

	// 文件审批
	public static final String SP_TYPE_QTSW_WJSP = "QTSW_WJSP";

	// 会议签到情况审批
	public static final String SP_TYPE_QTSW_HYQD = "QTSW_HYQD";
	// 工作指令单
	public static final String SP_TYPE_QTSW_GZZL = "QTSW_GZZL";
	// 审核
	public static final String SP_RES_VERIFY_SH = "0";
	// 审批
	public static final String SP_RES_VERIFY_SP = "0";
	// 审批步骤类型：必须
	public static final String SP_STEP_OPTION_BX = "0";
	// 审批步骤类型：可选
	public static final String SP_STEP_OPTION_KX = "1";
	// 审批步骤类型：无效
	public static final String SP_STEP_OPTION_WX = "2";
	// 模板：例外事项
	public static final String SP_TEMPLATE_LWSX = "9";
	// 模板：立项申请单
	private static final String SP_TEMPLATE_LXSQ = "288";

	public static final String[] MANUAL_STEP_TEMP = { SP_TEMPLATE_LWSX, SP_TEMPLATE_LXSQ, "199" };

	// 其他节点
	public static final String OTHER_NODE_CD = "other";
	// 其他节点
	public static final String OTHER_NODE_NAME = "其他";
	// 我的记录
	public static final String QUICK_SEARCH_MYRECO = "1";
	// 我的审批
	public static final String QUICK_SEARCH_MYDUTY = "2";
	// 我的历史
	public static final String QUICK_SEARCH_MYDEAL = "3";
	
	//--------网批类别
	/**
	 * 银行付款单
	 */
	public static final String RES_AUTH_TYPE_CD_YHFK="FKGL_220";
}
