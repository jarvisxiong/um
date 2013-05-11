package com.hhz.ump.web.cont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.NumberUtil;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.cont.ContAddAgreementManager;
import com.hhz.ump.dao.cont.ContContractTypeManager;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContPayManager;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.dao.cont.ContRemarkManager;
import com.hhz.ump.dao.cont.ContVisaUpdateManager;
import com.hhz.ump.dao.pm.PmMateEntryManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.cont.ContAddAgreement;
import com.hhz.ump.entity.cont.ContContractType;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContLedgerVo;
import com.hhz.ump.entity.cont.ContPay;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.sc.ScContractTempletInfo;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.LedgerReportUtil;
import com.hhz.ump.web.vo.VoContVisaUpdate;

@Namespace("/cont")
@Results({
		@Result(name = "show", location = "cont-ledger!input.action", type = "redirect", params = { "id", "${id}" }),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" }),
		@Result(name = "exportVisaList", type = "stream",params = { "contentType", "application/vnd.ms-excel", "inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
})
public class ContLedgerAction extends CrudActionSupport<ContLedger> {

	private static final long serialVersionUID = 8071052318603147332L;

	@Autowired
	private ResApproveInfoManager resApproveInfoManager;
	
	@Autowired
	private ContLedgerManager contLedgerManager;

	@Autowired
	private ContPayManager contPayManager;

	@Autowired
	private ContAddAgreementManager contAddAgreementManager;

	@Autowired
	private ContVisaUpdateManager contVisaUpdateManager;

	@Autowired
	private ContContractTypeManager contContractTypeManager;

	@Autowired
	private ContProjectCodeManager contProjectCodeManager;

	@Autowired
	private ContRemarkManager contRemarkManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private ScContractTempletInfoManager scContractTempletInfoManager;
	
	@Autowired
	private PmMateEntryManager pmMateEntryManager;

	private ContLedger entity;

	private Map<String, String> contTypeMap = new HashMap<String, String>();

	private ArrayList<String> contAttaList = new ArrayList<String>();

	// 翻页
	private String pageNo;
	// 选择科目
	private String typeByCd;
	// 选择台账类型
	private String ledgerType;
	// 选择的项目公司,可多个
	private String contTypes;
	// 合同名称
	private String contName;
	// 合同编号
	private String contNo;
	// 乙方
	private String partB;
	// 合同状态
	private String contStatus;
	// 合同进度
	private String contProcess;
	// 审批状态
	private String auditStatus;
	// 实际供方
	private String realProvName;
	// 是否战略
	private String strageFlg;

	// add by huangbijin 仅查询公司下的合同
	private String onlyCompanyFlg;

	private String downFileName;

	private String projectCd;
	private String isstandard;

	private InputStream is;

	// 合同台账导入路径
	private File importCont;

	// 网上审批合同台帐
	private ContLedger contLedger;
	
	// 网批表单: 链接进入合同台账，带入签证变更号ID，用于聚焦合同签证变更列表内的签证变更记录.
	private String contVisaId;
	public List<ContVisaUpdate> contVisaList;
	

	//  网批表单: 补充协议ID
	private String contAddId;
	public List<ContAddAgreement> contAddList;
	
	
	// 网批查询号
	public String resDiplayNo;
	

	@Override
	public ContLedger getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		page = new Page<ContLedger>(10);
		if (StringUtils.isNotBlank(getPageNo())) {
			page.setPageNo(Integer.valueOf(getPageNo()).intValue());
		}
		Map<String, Object> param = new HashMap<String, Object>();
		page = contLedgerManager.findPage(page, contQueryCond(false), param);
		return "list";
	}

	public String captain() {
		return "captain";
	}
 
	public void statiContStatus() {
		// 搜索 合同状态统计
		List<LedgerReportUtil> reportUtil = contLedgerManager.statiContStatus(projectCd, ledgerType);
		Struts2Utils.renderJson(reportUtil);
	}

	/**
	 * 合同份数统计
	 */
	public void statiContType() {
		List<LedgerReportUtil> reportUtil = contLedgerManager.statiContType(projectCd, ledgerType);
		Struts2Utils.renderJson(reportUtil);
	}

	public void statiContTotal() {
		List<LedgerReportUtil> reportUtil = contLedgerManager.statiContTotal(projectCd, ledgerType);
		Struts2Utils.renderJson(reportUtil);
	}

	public void statiContProc() {
		List<LedgerReportUtil> reportUtil = contLedgerManager.statiContProc(projectCd, ledgerType);
		Struts2Utils.renderJson(reportUtil);
	}

	/**
	 * @return the isstandard
	 */
	public String getIsstandard() {
		return isstandard;
	}

	/**
	 * @param isstandard
	 *            the isstandard to set
	 */
	public void setIsstandard(String isstandard) {
		this.isstandard = isstandard;
	}

	/**
	 * 拼装搜索条件
	 * 
	 * @return
	 */
	private String contQueryCond(boolean isExport) {
		StringBuffer hql = new StringBuffer().append("select a from ContLedger a ");
		if (StringUtils.isNotBlank(contTypes)) {
			String[] types = contTypes.replaceAll(" ", "").split(",");
			// 对types遍历，分出搜索条件为公司的和类别的
			List<String> typeProject = new ArrayList<String>();
			List<String> typeCode = new ArrayList<String>();
			for (int i = 0; i < types.length; i++) {
				// 由类别为10位随机数，则若长度为10则为类别
				if (types[i].length() != 10) {
					typeProject.add(types[i]);
				} else {
					typeCode.add(types[i]);
				}
			}
			hql.append(",ContProjectCode c where a.projectCd=c.projectCd");
			hql.append(" and (");
			if ((typeProject != null && typeProject.size() > 0) && (typeCode.size() <= 0)) {
				for (int i = 0; i < typeProject.size(); i++) {
					hql.append(" c.projectCd='").append(typeProject.get(i)).append("'");
					if (i != (typeProject.size() - 1)) {
						hql.append(" or ");
					}
				}
			} else {
				if (typeProject != null && typeProject.size() > 0) {
					for (int i = 0; i < typeProject.size(); i++) {
						hql.append(" c.projectCd='").append(typeProject.get(i)).append("'");
						hql.append(" or ");
					}
				}
				for (int i = 0; i < typeCode.size(); i++) {
					hql.append(" a.contTypeCd like '%").append(typeCode.get(i)).append("%'");
					if (i != (typeCode.size() - 1)) {
						hql.append(" or ");
					}
				}
			}
			hql.append(" )");
		} else {
			hql.append(",ContProjectCode c where a.projectCd=c.projectCd ");
			// 如果是地产公司员工，则寻找它所拥有的其它公司权限值，得到相关数据操作权限
			List<HashMap<String, String>> centerList = contProjectCodeManager.queryAuthority(ledgerType);

			Iterator iter = centerList.iterator();
			int i = 0;
			if (centerList != null && centerList.size() > 0) {
				hql.append(" and (");
				Map map = null;
				while (iter.hasNext()) {
					map = (Map) iter.next();
					String key = map.keySet().iterator().next().toString();
					if (i != 0) {
						hql.append(" or ");
					}
					hql.append(" a.projectCd='" + key + "'");
					i++;
				}
				hql.append(")");
			}
		}
		if (StringUtils.isNotBlank(ledgerType)) {
			hql.append(" and c.codeType='").append(ledgerType).append("'");
		}

		// add by huangbijin 2012-06-04 直接挂在项目公司下
		boolean onlyFlg = false;
		if (StringUtils.isNotBlank(onlyCompanyFlg)) {
			// 若选中
			if ("on".equals(onlyCompanyFlg)) {
				hql.append(" and a.contTypeCd is null ");
				onlyFlg = true;
			}
		}

		if (!onlyFlg) {
			// hql.append(" and a.contTypeCd is not null ");
		}

		// 合同类别
		if (StringUtils.isNotBlank(typeByCd)) {
			hql.append(" and a.contTypeCd2='").append(typeByCd).append("'");
		}
		if (contName != null && (!("".equals(contName) || "0".equals(contName)))) {
			hql.append(" and a.contName like '%").append(contName).append("%'");
		}
		if (StringUtils.isNotBlank(contNo)) {
			hql.append(" and a.contNo like '%").append(contNo.trim()).append("%'");
		}
		if (StringUtils.isNotBlank(partB)) {
			hql.append(" and a.partB like '%").append(partB.trim()).append("%'");
		}
		if (StringUtils.isNotBlank(contStatus)) {
			hql.append(" and a.contStatus ='").append(contStatus.trim()).append("'");
		}
		if (StringUtils.isNotBlank(contProcess)) {
			hql.append(" and a.procStatus ='").append(contProcess.trim()).append("'");
		}
		if (StringUtils.isNotBlank(auditStatus)) {
			hql.append(" and a.contAuditStatus='").append(auditStatus.trim()).append("'");
		}
		if (StringUtils.isNotBlank(realProvName)) {
			hql.append(" and a.realProvName like '%").append(realProvName.trim()).append("%'");
		}
		if (StringUtils.isNotBlank(strageFlg)) {
			hql.append(" and a.strageFlg='").append(strageFlg.trim()).append("'");
		}
		
		hql.append(" and a.enableFlg = '1' " );//是否可用：1-可用

		// 如果角色为搜索角色，那么只能搜索已经审核的数据
		/*
		 * if(SpringSecurityUtils.hasRole("A_CONTRACT_QUERY")||SpringSecurityUtils.hasRole("A_CONTRACT_QUERYEXP")){ hql.append(" and a.contAuditStatus='2' "); }
		 */
		if (isExport) {
			hql.append(" order by a.projectCd,a.contNo asc ");
		} else {
			hql.append(" order by a.projectCd,a.contNo asc ");
		}

		return hql.toString();
	}

	/**
	 * 拼装搜索Excel的条件
	 * 
	 * @return
	 */
	private String contQueryCondByExcel(boolean isExport) {
		StringBuffer sql = new StringBuffer()
				.append("select a.cont_type_cd2,a.project_cd,a.cont_no,a.cont_name,")
				.append("       a.part_b,a.cont_property,a.total_price,a.agree_total,a.visa_total,a.update_rate, ")
				.append("       a.update_total,a.clear_price,a.cont_status,a.proc_status,a.guar_begin_date,a.guar_end_date,a.perf_bond,")
				// 已完成产值合计completeprice、甲供料材料当期产值partaamount、其中增减款curaddamount
				.append("       sum(c.complete_num),sum(c.matieral_num),sum(c.current_pay),sum(c.current_add),a.cont_ledger_id,a.target_cost,a.pay_num,d.pay_money ")
				.append("  from cont_ledger a, cont_project_code b, cont_pay c, ")
				.append("       (select d.cont_ledger_id,sum(d.pay_money) as pay_money from cont_settlement d group by d.cont_ledger_id) d ")
				.append(" where a.enable_flg = '1' and a.project_cd = b.project_cd and a.cont_ledger_id = c.cont_ledger_id(+) and a.cont_ledger_id = d.cont_ledger_id(+) ");

		if (contTypes != null && !"".equals(contTypes)) {
			String[] types = contTypes.replaceAll(" ", "").split(",");
			// 对types遍历，分出搜索条件为公司的和类别的
			List<String> typeProject = new ArrayList<String>();
			List<String> typeCode = new ArrayList<String>();
			for (int i = 0; i < types.length; i++) {
				// 由类别为10位随机数，则若长度为10则为类别
				if (types[i].length() != 10) {
					typeProject.add(types[i]);
				} else {
					typeCode.add(types[i]);
				}
			}
			sql.append(" and (");
			if ((typeProject != null && typeProject.size() > 0) && (typeCode.size() <= 0)) {
				for (int i = 0; i < typeProject.size(); i++) {
					sql.append(" a.project_Cd='").append(typeProject.get(i)).append("'");
					if (i != (typeProject.size() - 1)) {
						sql.append(" or ");
					}
				}
			} else {
				if (typeProject != null && typeProject.size() > 0) {
					for (int i = 0; i < typeProject.size(); i++) {
						sql.append(" a.project_Cd='").append(typeProject.get(i)).append("'");
						sql.append(" or ");
					}
				}
				for (int i = 0; i < typeCode.size(); i++) {
					sql.append(" a.cont_Type_Cd like '%").append(typeCode.get(i)).append("%'");
					if (i != (typeCode.size() - 1)) {
						sql.append(" or ");
					}
				}
			}
			sql.append(" )");
		}
		if (StringUtils.isNotBlank(typeByCd)) {
			sql.append(" and a.cont_Type_Cd2='").append(typeByCd).append("'");
		}
		if (StringUtils.isNotBlank(ledgerType)) {
			sql.append(" and b.code_type='").append(ledgerType).append("'");
		}
		if (contName != null && (!("".equals(contName) || "0".equals(contName)))) {
			sql.append(" and a.cont_Name like '%").append(contName).append("%'");
		}
		if (StringUtils.isNotBlank(contNo)) {
			sql.append(" and a.cont_No like '%").append(contNo).append("%'");
		}
		if (StringUtils.isNotBlank(contStatus)) {
			sql.append(" and a.cont_Status ='").append(contStatus).append("'");
		}
		if (StringUtils.isNotBlank(contProcess)) {
			sql.append(" and a.proc_Status ='").append(contProcess).append("'");
		}
		if (StringUtils.isNotBlank(partB)) {
			sql.append(" and a.part_B like '%").append(partB).append("%'");
		}
		if (StringUtils.isNotBlank(auditStatus)) {
			sql.append(" and a.cont_Audit_Status='").append(auditStatus).append("'");
		}
		
		// 通过UIID,得到该员工的相关权限
		List<HashMap<String, String>> centerList = contProjectCodeManager.queryAuthority(ledgerType);
		Iterator iter = centerList.iterator();
		int i = 0;
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			String key = map.keySet().iterator().next().toString();
			if (i == 0) {
				sql.append(" and (");
			} else {
				sql.append(" or ");
			}
			sql.append(" a.project_Cd='" + key + "'");
			i++;
		}
		if (i != 0) {
			sql.append(")");
		}

		// 屏蔽 导出全部合同(不仅仅2-已完已结) hidden by huangbijin 2012-03-26
		// 如果角色为搜索角色，那么只能搜索已经审核的数据
		if (SpringSecurityUtils.hasRole("A_CONTRACT_QUERY") || SpringSecurityUtils.hasRole("A_CONTRACT_QUERYEXP")) {
			// sql.append(" and a.cont_Audit_Status='2' ");
		}
		sql.append(" group by A.CONT_TYPE_CD2,a.project_Cd,a.cont_No,a.cont_Name,a.part_B,a.cont_Property,a.total_Price,");
		sql.append("a.agree_Total,a.visa_Total,a.update_Rate,a.update_Total,a.clear_Price,a.cont_Status,a.proc_Status,");
		sql.append("a.guar_Begin_Date,a.guar_End_Date,a.perf_Bond,a.cont_Ledger_Id,a.target_cost,a.PAY_NUM,d.pay_Money ");
		if (isExport) {
			sql.append(" order by A.CONT_TYPE_CD2,a.project_Cd,a.cont_No asc ");
		} else {
			sql.append(" order by a.project_Cd,b.type_Name,a.cont_No asc ");
		}
		return sql.toString();
	}

	/**
	 * 合同模块初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		return "main";
	}

	/**
	 * 合同明细信息
	 * @param id
	 * @param resVisaNo  网批传过来的签证变更号
	 */
	@Override
	public String input() throws Exception {
		/*
		 * orgEstateOrgList = WorkPlanUtil.getOrgEstateOrgList(); for(WsPlasOrg wsUaapOrg : orgEstateOrgList){ orgEstateOrgMap.put(wsUaapOrg.getOrgCd(),
		 * wsUaapOrg.getOrgName()); }
		 */

		buildToTypeMap(entity);
		// buildToAttaId(entity);
		// 搜索是否有附件，若有附件，则显示有附件图标
		if (StringUtils.isNotBlank(entity.getContLedgerId())) {
			
			// 协议书ID 0
			if (StringUtils.isNotBlank(entity.getAgreeAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getAgreeAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			// 专用条款ID 1
			if (StringUtils.isNotBlank(entity.getSpecialAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getSpecialAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			// 结算附件 2
			if (StringUtils.isNotBlank(entity.getClearAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getClearAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			// 保修协议 3
			if (StringUtils.isNotBlank(entity.getRepairAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getRepairAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			
			// 定标查询编号
			String resId = entity.getResApproveId();
			if(StringUtils.isNotBlank(resId)){
				ResApproveInfo res = resApproveInfoManager.getResApproveInfoById(resId);
				if(res != null){
					resDiplayNo = String.valueOf(res.getDisplayNo());
				}
			}

			// addby huangibjin 2012-06-21 签证变更历史(按照创建时间升序)
			// 签证变更历史
			contVisaList =  contVisaUpdateManager.getContVisaList(entity.getContLedgerId());

			// addby huangibjin 2012-06-21 签证变更历史(按照创建时间升序)
			// 补充协议历史
			contAddList =  contAddAgreementManager.getContAddList(entity.getContLedgerId());
			
		} else {
			// 新建
			for (int i = 0; i < 4; i++) {
				contAttaList.add("0");
			}
		}
		/**
		 * 获取合同库合同的类别
		 */
		if (StringUtils.isNotBlank(entity.getContractTempletInfoId())) {

			ScContractTempletInfo conTempletInfo = scContractTempletInfoManager.getEntity(entity
					.getContractTempletInfoId());
			isstandard = "" + conTempletInfo.getIsstandard();

		}
		return INPUT;
	}

	private void buildToTypeMap(ContLedger ledger) {
		if (StringUtils.isNotBlank(getId()) && StringUtils.isNotBlank(ledger.getContTypeCd())) {
			List<String> typeNameList = contContractTypeManager.getTypeList(ledger.getContTypeCd(),
					new ArrayList<String>());
			StringBuffer typeName = new StringBuffer("");
			if (typeNameList != null && typeNameList.size() > 0) {
				for (int i = typeNameList.size(); i > 0; i--) {
					typeName.append(typeNameList.get(i - 1));
					typeName.append(" ");
				}
			}
			contTypeMap.put(ledger.getContTypeCd(), typeName.toString());
		}

	}

	/**
	 * 根据类型cd获取类型名称
	 */
	private String getTypeName(String contTypeCd) {
		StringBuffer typeName = new StringBuffer("");
		if (StringUtils.isNotBlank(contTypeCd)) {
			List<String> typeNameList = contContractTypeManager.getTypeList(contTypeCd, new ArrayList<String>());
			if (typeNameList != null && typeNameList.size() > 0) {
				for (int i = typeNameList.size(); i > 0; i--) {
					typeName.append(typeNameList.get(i - 1));
					typeName.append(" ");
				}
			}
		}
		return typeName.toString();
	}

	private void buildToAttaId(ContLedger ledger) {
		// 协议书附件ID
		if (ledger.getAgreeAttaId() == null || "".equals(ledger.getAgreeAttaId())) {
			ledger.setAgreeAttaId(RandomUtils.generateTmpEntityId());
		}
		// 专用条款附件ID
		if (ledger.getSpecialAttaId() == null || "".equals(ledger.getSpecialAttaId())) {
			ledger.setSpecialAttaId(RandomUtils.generateTmpEntityId());
		}
		// 结算附件ID
		if (ledger.getClearAttaId() == null || "".equals(ledger.getClearAttaId())) {
			ledger.setClearAttaId(RandomUtils.generateTmpEntityId());
		}
		// 保修协议附件ID
		if (ledger.getRepairAttaId() == null || "".equals(ledger.getRepairAttaId())) {
			ledger.setRepairAttaId(RandomUtils.generateTmpEntityId());
		}
	}

	/**
	 * 搜索合同编号的唯一性
	 * 
	 * @param contNo
	 * @param contLedgerId
	 * @return 0-未超过农夫 其他-重复
	 * 
	 */
	public void contNoValidate() {
		String tContNo = Struts2Utils.getParameter("contNo");
		String tContLedgerId = Struts2Utils.getParameter("contLedgerId");

		// 是否重复
		boolean dumpFlg = contLedgerManager.validateContNo(tContLedgerId, tContNo);
		if (dumpFlg) {
			Struts2Utils.renderText("failure: Dumplicated contract no,please choose another one!");
		} else {
			// 0-未重复
			Struts2Utils.renderText("0");
		}
	}

	@Override
	public String save() throws Exception {

		if (entity.getContPriceType() == null || "".equals(entity.getContPriceType())) {
			entity.setContPriceType("1");
		}
		// 添加计划竣工日期
		if (entity.getPlanBeginDate() != null && entity.getPeriod() != null) {
			//
			if (!"".equals(entity.getPeriod()) && !"-".equals(entity.getPeriod())) {
				Date a = DateOperator.addDays(entity.getPlanBeginDate(), Integer.parseInt(entity.getPeriod()));
				entity.setPlanEndDate(a);
			}
		}
		// 重新计算变更后费用
		BigDecimal upTotal = new BigDecimal(0);
		upTotal = upTotal.add(format(entity.getTotalPrice()));

		// 签证变更累加
		BigDecimal visaTotal = new BigDecimal(0);
		ContVisaUpdate tmpVisa = null;
		List<ContVisaUpdate> visaList = entity.getContVisaUpdates();
		if (visaList != null) {
			for (int i = 0; i < visaList.size(); i++) {
				tmpVisa = visaList.get(i);
				if (tmpVisa != null) {
					visaTotal = visaTotal.add(format(tmpVisa.getAmountUpdate()));
					upTotal = upTotal.add(format(tmpVisa.getAmountUpdate()));
				}
			}
		}
		// 补充协议累加
		ContAddAgreement tmpAdd = null;
		List<ContAddAgreement> addList = entity.getContAddAgreements();
		if (addList != null) {
			BigDecimal addTotal = new BigDecimal(0);
			for (int i = 0; i < addList.size(); i++) {
				tmpAdd = addList.get(i);
				if (tmpAdd != null) {
					addTotal = addTotal.add(format(tmpAdd.getAmountUpdate()));
					upTotal = upTotal.add(format(tmpAdd.getAmountUpdate()));
				}
			}
			entity.setAgreeTotal(addTotal);
		}
		entity.setUpdateTotal(upTotal);

		// 如果进入状态为空，则默认为0：未开工状态
		if (StringUtils.isBlank(entity.getProcStatus())) {
			entity.setProcStatus("0");
		}
		// 如果合同状态为已完已结，则进度状态为已竣工
		if (StringUtils.isNotBlank(entity.getContStatus()) && "2".equals(entity.getContStatus())) {
			entity.setProcStatus("4");
		}
		// 搜索合同台账contTypeCd是否是在CONT_PROJECT_CODE里面，若再，则不添加contTypeCd
		if (StringUtils.isNotBlank(entity.getContTypeCd())) {
			List<ContProjectCode> codeList = contProjectCodeManager.getProjectCode(entity.getContTypeCd());
			if (codeList != null && codeList.size() > 0) {
				entity.setProjectCd(entity.getContTypeCd());
				entity.setContTypeCd("");
			} else {
				// 添加projectCd项目
				ContContractType type = contContractTypeManager.getTypeVo(entity.getContTypeCd());
				entity.setProjectCd(type.getProjectCd());
			}
		}

		if (entity.getContPaies() != null && entity.getContPaies().size() > 0) {
			// 已完成产值
			BigDecimal completeNum = new BigDecimal(0);
			for (ContPay pay : entity.getContPaies()) {
				if (pay.getCompleteNum() != null) {
					completeNum = completeNum.add(pay.getCompleteNum());
				}
			}
			entity.setCompleteNum(completeNum);
		} else {
			entity.setCompleteNum(new BigDecimal(0));
		}

		contLedgerManager.saveContLedger(entity);
		setId(entity.getContLedgerId());
		/**
		 * 获取合同库合同的类别
		 */
		if (StringUtils.isNotBlank(entity.getContractTempletInfoId())) {

			ScContractTempletInfo conTempletInfo = scContractTempletInfoManager.getEntity(entity
					.getContractTempletInfoId());
			isstandard = "" + conTempletInfo.getIsstandard();

		}
		setId(entity.getContLedgerId());
		return "show";
	}

	private BigDecimal format(BigDecimal t) {
		if (t == null)
			return new BigDecimal(0);
		else
			return t;
	}

	/**
	 * 导出合同报表 或 导出合同台账财务需要的付款信息
	 * 
	 * @param excelByFin
	 *            true-财务导出
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		// 若excelByFin有值，则为财务导出数据
		String excelByFin = Struts2Utils.getParameter("excelByFin");
		// 根据搜索条件查找到符合条件的记录
		Map<String, Object> param = new HashMap<String, Object>();
		List<ContLedgerVo> results = contLederExcel(contLedgerManager.findBySql(contQueryCondByExcel(true), param));
		// 对成本科目进行合计
		List<ContLedgerVo> totalResults = new ArrayList<ContLedgerVo>();
		// 给orgEstateOrgMap附值
		/*
		 * orgEstateOrgList = WorkPlanUtil.getOrgEstateOrgList(); for(WsPlasOrg wsUaapOrg : orgEstateOrgList){ orgEstateOrgMap.put(wsUaapOrg.getOrgCd(),
		 * wsUaapOrg.getOrgName()); }
		 */
		int i = 1;
		// 以下是total记录的初始化值
		String subjectTypeName = "";
		ContLedgerVo totalVo = new ContLedgerVo();
		// 初始化totalVo的值
		totalVo.setVisaTotal(new BigDecimal(0));
		totalVo.setTotalPrice(new BigDecimal(0));
		// totalVo.setUpdateRate(new BigDecimal(0));
		totalVo.setPartAAmount(new BigDecimal(0));
		totalVo.setCurPayAmount(new BigDecimal(0));
		totalVo.setCurAddAmount(new BigDecimal(0));
		totalVo.setCompletePrice(new BigDecimal(0));
		totalVo.setAgreeTotal(new BigDecimal(0));
		totalVo.setUpdateTotal(new BigDecimal(0));
		totalVo.setClearPrice(new BigDecimal(0));
		totalVo.setPerfBond(new BigDecimal(0));
		totalVo.setTargetCost(new BigDecimal(0));
		BigDecimal completePrice = new BigDecimal(0);
		// 补充协议总价
		for (ContLedgerVo ledger : results) {
			// 通过typeCd得到对应的typeName
			// ContContractType typeVo =contContractTypeManager.getTypeVo(ledger.getContTypeCd());
			// 若科目有变化则新建一个合计,计算合计内容
			if (StringUtils.isNotBlank(ledger.getContTypeName())) {
				if (!subjectTypeName.equals(ledger.getContTypeName())) {
					// 若不是第一条记录，则不新增;
					if (i != 1) {
						// 累计支付比率=（甲供料产值+其间直接支付+增减款）/已完成产值合计*100
						if (totalVo.getCompletePrice().compareTo(new BigDecimal(0)) != 0) {
							BigDecimal totalPay = totalVo.getPartAAmount().add(totalVo.getCurPayAmount());
							totalPay = totalPay.add(totalVo.getCurAddAmount());
							totalVo.setCompleteRate(totalPay.divide(totalVo.getCompletePrice(), 2,
									BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
						}
						totalResults.add(totalVo);
						totalVo = new ContLedgerVo();
						// 初始化totalVo的值
						totalVo.setVisaTotal(new BigDecimal(0));
						totalVo.setTotalPrice(new BigDecimal(0));
						// totalVo.setUpdateRate(new BigDecimal(0));
						totalVo.setPartAAmount(new BigDecimal(0));
						totalVo.setCurPayAmount(new BigDecimal(0));
						totalVo.setCurAddAmount(new BigDecimal(0));
						totalVo.setCompletePrice(new BigDecimal(0));
						totalVo.setAgreeTotal(new BigDecimal(0));
						totalVo.setTargetCost(new BigDecimal(0));
						totalVo.setClearPrice(new BigDecimal(0));
						totalVo.setUpdateTotal(new BigDecimal(0));
						totalVo.setPerfBond(new BigDecimal(0));
						completePrice = new BigDecimal(0);
					}
					subjectTypeName = ledger.getContTypeName();
					totalVo.setContTypeName(subjectTypeName);
				}
				totalVo.setPerfBond(totalVo.getPerfBond().add(ledger.getCompletePrice()));
				totalVo.setAgreeTotal(totalVo.getAgreeTotal().add(ledger.getAgreeTotal()));
				totalVo.setVisaTotal(totalVo.getVisaTotal().add(ledger.getVisaTotal()));
				totalVo.setTargetCost(totalVo.getTargetCost().add(ledger.getTargetCost()));
				// 合计变更比率先放着
				// totalVo.setUpdateRate(new BigDecimal(0));

				totalVo.setUpdateTotal(totalVo.getUpdateTotal().add(ledger.getUpdateTotal()));
				totalVo.setClearPrice(totalVo.getClearPrice().add(ledger.getClearPrice()));
				totalVo.setCompletePrice(totalVo.getCompletePrice().add(ledger.getCompletePrice()));
				totalVo.setPartAAmount(totalVo.getPartAAmount().add(ledger.getPartAAmount()));
				totalVo.setCurPayAmount(totalVo.getCurPayAmount().add(ledger.getCurPayAmount()));
				totalVo.setCurAddAmount(totalVo.getCurAddAmount().add(ledger.getCurAddAmount()));
				// 已完成产值合计
				BigDecimal completeNum = new BigDecimal(0);
				completePrice = completePrice.add(completeNum);
				if (ledger.getTotalPrice() != null) {
					totalVo.setTotalPrice(totalVo.getTotalPrice().add(ledger.getTotalPrice()));
					totalVo.setPerfBond(totalVo.getTotalPrice());
				}
				i++;
			}
		}
		// 新增最后一条记录
		// 累计支付比率=（甲供料产值+其间直接支付+增减款）/已完成产值合计*100
		if (totalVo.getCompletePrice().compareTo(new BigDecimal(0)) != 0) {
			BigDecimal totalPay = totalVo.getPartAAmount().add(totalVo.getCurPayAmount());
			totalPay = totalPay.add(totalVo.getCurAddAmount());
			totalVo.setCompleteRate(totalPay.divide(totalVo.getCompletePrice(), 2, BigDecimal.ROUND_HALF_UP).multiply(
					new BigDecimal(100)));
		}
		totalResults.add(totalVo);
		Map beans = new HashMap();
		beans.put("ledger", results);
		beans.put("total", totalResults);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		if (StringUtils.isNotBlank(excelByFin)) {
			is = JXLExcelUtil.initJxlsInputStream(beans, "contFinResult.xls");
		} else {
			is = JXLExcelUtil.initJxlsInputStream(beans, "contResult.xls");
		}
		String fileName = "合同台账-" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 导入合同台账财务需要的付款信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void importxcelByFin() throws Exception {
		FileInputStream inpFile = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow rows = null;
		HSSFCell cells = null;
		try {
			inpFile = new FileInputStream(importCont);
			workbook = new HSSFWorkbook(inpFile);

			sheet = workbook.getSheetAt(0);
			// 总行数
			int rowsCount = sheet.getLastRowNum();
			String ledgerId = null;
			String realPayNum = null;
			BigDecimal bigDecimal = null;
			// 搜索是否符合模板标准
			rows = sheet.getRow(1);
			if (rows.getCell(0) == null || !"ID".equals(rows.getCell(0).toString()) || rows.getCell(16) == null
					|| !"累计实际支付款(元)".equals(rows.getCell(16).toString())) {
				Struts2Utils.renderText("格式不对，请导出报表后填写实际支付金额后导入");
			} else {
				ContLedger ledger = null;
				for (int i = 2; i <= rowsCount; i++) {
					rows = sheet.getRow(i);
					try {
						ledgerId = rows.getCell(0).toString();
						cells = rows.getCell(16); // 16为“累计实际支付款”列
						if (cells != null) {
							if (cells.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
								// 如果该列类型为“公式”类型则用getNumericCellValue()取值
								realPayNum = String.valueOf(cells.getNumericCellValue());
							} else {
								realPayNum = cells.toString();
							}
							if (StringUtils.isNotBlank(realPayNum)) {
								bigDecimal = new BigDecimal(realPayNum);
								// 将科学计数法转成普通的数字
								BigDecimal bigRealPayNum = bigDecimal.setScale(2, 2);
								if (StringUtils.isNotBlank(ledgerId) && StringUtils.isNotBlank(realPayNum)
										&& isNum(bigRealPayNum.toString())) {
									ledger = contLedgerManager.getContLedger(ledgerId);
									if (ledger != null) {
										ledger.setPayNum(bigRealPayNum);
										contLedgerManager.saveContLedger(ledger);
									}
								}
							}
						}
					} catch (Exception e) {
						// Struts2Utils.renderText("格式不对，请导出报表后填写导入");
						// e.printStackTrace();
						Log.error("合同编号为：" + rows.getCell(3).toString() + "的合同，值为：" + bigDecimal + "字段长度超出指定长度");
						continue;
					}
				}
				Struts2Utils.renderText("导入成功");
			}
		} catch (Exception e) {
			Struts2Utils.renderText("格式不对，请导出报表后填写实际支付金额后导入");
			// e.printStackTrace();
		} finally {
			try {
				if (inpFile != null) {
					inpFile.close();
				}
				if (workbook != null) {
					workbook = null;
				}
				if (sheet != null) {
					sheet = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	private boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[.\0-9]*");
		Matcher num = pattern.matcher(str);
		if (num.matches())
			return true;
		else
			return false;
	}

	private List<ContLedgerVo> contLederExcel(List list) {
		List<ContLedgerVo> ledgerList = new ArrayList<ContLedgerVo>();
		if (list != null && list.size() > 0) {
			Map<String, String> map = null;
			// 匹配合同类型，若为1，则为地产数据，2为商业数据
			if (StringUtils.isNotBlank(ledgerType) && "1".equals(ledgerType)) {
				map = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
			} else {
				map = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_BIS_TYPE);
			}

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				ContLedgerVo ledger = new ContLedgerVo();
				ledger.setContTypeCd((String) obj[0]);
				if (obj[0] != null) {
					ledger.setContTypeName(map.get(obj[0]));
				} else {
					ledger.setContTypeName("");
				}
				ledger.setProjectCd((String) obj[1]);
				ledger.setContNo((String) obj[2]);
				ledger.setContName((String) obj[3]);
				ledger.setPartB((String) obj[4]);
				ledger.setContProperty((String) obj[5]);
				ledger.setTotalPrice((BigDecimal) obj[6]);
				ledger.setAgreeTotal(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
				ledger.setVisaTotal(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
				ledger.setUpdateRate(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
				ledger.setUpdateTotal(obj[10] == null ? new BigDecimal(0) : (BigDecimal) obj[10]);
				ledger.setClearPrice(obj[11] == null ? new BigDecimal(0) : (BigDecimal) obj[11]);

				ledger.setContStatus((String) obj[12]);
				ledger.setProcStatus((String) obj[13]);
				ledger.setGuarBeginDate((Date) obj[14]);
				ledger.setGuarEndDate((Date) obj[15]);
				ledger.setPerfBond(obj[16] == null ? new BigDecimal(0) : (BigDecimal) obj[16]);
				ledger.setCompletePrice(obj[17] == null ? new BigDecimal(0) : (BigDecimal) obj[17]);
				ledger.setPartAAmount(obj[18] == null ? new BigDecimal(0) : (BigDecimal) obj[18]);
				ledger.setCurPayAmount(obj[19] == null ? new BigDecimal(0) : (BigDecimal) obj[19]);
				ledger.setCurAddAmount(obj[20] == null ? new BigDecimal(0) : (BigDecimal) obj[20]);
				ledger.setContLedgerId((String) obj[21]);
				ledger.setTargetCost(obj[22] == null ? new BigDecimal(0) : (BigDecimal) obj[22]);
				ledger.setPayNum(obj[23] == null ? new BigDecimal(0) : (BigDecimal) obj[23]);
				if (ledger.getGuarBeginDate() != null) {
					ledger.setGuarDate(DateOperator.formatDate(ledger.getGuarBeginDate(), "yyyy-MM-dd"));
				}
				if (ledger.getGuarEndDate() != null) {
					ledger.setGuarDate(ledger.getGuarDate() + " - "
							+ DateOperator.formatDate(ledger.getGuarEndDate(), "yyyy-MM-dd"));
				}
				// obj[24]合同结算的本次付款申请总额
				ledger.setSettAmount(obj[24] == null ? new BigDecimal(0) : (BigDecimal) obj[24]);
				ledger.setSerialNumber(i + 1);
				// 变更比率
				if (ledger.getCompletePrice() != null && ledger.getCompletePrice().compareTo(new BigDecimal(0)) != 0) {
					BigDecimal totalPay = ledger.getPartAAmount().add(ledger.getCurPayAmount());
					totalPay = totalPay.add(ledger.getCurAddAmount());
					ledger.setCompleteRate(totalPay.divide(ledger.getCompletePrice(), 2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(100)));
				} else {
					ledger.setCompleteRate(new BigDecimal(0));
				}
				// 得到公司名称
				ledger.setProjectName(CodeNameUtil.getDeptNameByCd(ledger.getProjectCd()));
				// 附合同性质
				Map<String, String> types = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_CONT_PROPERTY);
				ledger.setContProperty(types.get(ledger.getContProperty()));
				// 合同状态
				Map<String, String> types1 = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_CONT_STATUS);
				// ledger.setContStatus(types1.get(ledger.getContStatus()));
				ledger.setContStatusName(types1.get(ledger.getContStatus()));
				// 进度状态
				Map<String, String> types2 = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_PROC_STATUS);
				ledger.setProcStatusName(types2.get(ledger.getProcStatus()));
				ledgerList.add(ledger);
			}
		}
		return ledgerList;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
//			contLedgerManager.deleteContLedger(getId());
			deleteCont();
			addActionMessage(getText("common.success"));
		}
		return null;
	}

	public String deleteRemark() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			contRemarkManager.deleteContRemark(getId());
		}
		return null;
	}

	/**
	 * 删除visa合同
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteVisa() throws Exception {
		String contVisaUpdateId = Struts2Utils.getParameter("contVisaUpdateId");
		// 补充协议动态修改后，合同的补充协议已累计和变更后合同总价也要做修改
		if (StringUtils.isNotBlank(getId())) {
			entity = contLedgerManager.getEntity(getId());
			if (entity.getAgreeTotal() == null) {
				entity.setAgreeTotal(new BigDecimal(0));
			}
			if (entity.getTotalPrice() == null) {
				entity.setTotalPrice(new BigDecimal(0));
			}
			if (entity.getVisaTotal() == null) {
				entity.setVisaTotal(new BigDecimal(0));
			}
			ContVisaUpdate visa = contVisaUpdateManager.getEntity(contVisaUpdateId);
			// 签证变更协议
			if (visa.getAmountUpdate() != null) {
				entity.setVisaTotal(entity.getVisaTotal().subtract(visa.getAmountUpdate()));
				// 更新变更后合同总价
				if (entity.getUpdateTotal().intValue() != 0) {
					entity.setUpdateTotal(entity.getUpdateTotal().subtract(visa.getAmountUpdate()));
				}
			}
			// 变更比率
			String contProperty = entity.getContProperty();
			if (contProperty != null) {
				if ("2".equals(contProperty) || "3".equals(contProperty)) {
					BigDecimal updateTotal = entity.getAgreeTotal();
					BigDecimal updateRate = new BigDecimal(0);
					if (entity.getVisaTotal() != null && updateTotal != null && updateTotal.intValue() != 0) {
						updateRate = entity.getVisaTotal().divide(updateTotal, 2, BigDecimal.ROUND_HALF_UP)
								.multiply(new BigDecimal(100));
					}
					entity.setUpdateRate(updateRate);
				} else {
					BigDecimal updateTotal = entity.getTotalPrice().add(entity.getAgreeTotal());
					BigDecimal updateRate = new BigDecimal(0);
					if (entity.getVisaTotal() != null && updateTotal != null && updateTotal.intValue() != 0) {
						updateRate = entity.getVisaTotal().divide(updateTotal, 2, BigDecimal.ROUND_HALF_UP)
								.multiply(new BigDecimal(100));
					}
					entity.setUpdateRate(updateRate);
				}
			}
			contLedgerManager.updateContLedger(getId(), "visa", contVisaUpdateId);
		}
		return null;
	}

	/**
	 * 删除agree合同
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteAgree() throws Exception {
		String contAddAgreementId = Struts2Utils.getParameter("contAddAgreementId");
		// 补充协议动态修改后，合同的补充协议已累计和变更后合同总价也要做修改
		if (StringUtils.isNotBlank(getId())) {
			entity = contLedgerManager.getEntity(getId());
			if (entity.getAgreeTotal() == null) {
				entity.setAgreeTotal(new BigDecimal(0));
			}
			if (entity.getTotalPrice() == null) {
				entity.setTotalPrice(new BigDecimal(0));
			}
			if (entity.getVisaTotal() == null) {
				entity.setVisaTotal(new BigDecimal(0));
			}
			ContAddAgreement agree = contAddAgreementManager.getEntity(contAddAgreementId);
			if (agree != null && agree.getAmountUpdate() != null) {
				entity.setAgreeTotal(entity.getAgreeTotal().subtract(agree.getAmountUpdate()));
				// 更新变更后合同总价
				if (entity.getUpdateTotal().intValue() != 0) {
					entity.setUpdateTotal(entity.getUpdateTotal().subtract(agree.getAmountUpdate()));
				}
			}
			// 变更比率
			String contProperty = entity.getContProperty();
			if (contProperty != null) {
				if ("2".equals(contProperty) || "3".equals(contProperty)) {
					BigDecimal updateTotal = entity.getAgreeTotal();
					BigDecimal updateRate = new BigDecimal(0);
					if (entity.getVisaTotal() != null && updateTotal != null && updateTotal.intValue() != 0) {
						updateRate = entity.getVisaTotal().divide(updateTotal, 2, BigDecimal.ROUND_HALF_UP)
								.multiply(new BigDecimal(100));
					}
					entity.setUpdateRate(updateRate);
				} else {
					BigDecimal updateTotal = entity.getTotalPrice().add(entity.getAgreeTotal());
					BigDecimal updateRate = new BigDecimal(0);
					if (entity.getVisaTotal() != null && updateTotal != null && updateTotal.intValue() != 0) {
						updateRate = entity.getVisaTotal().divide(updateTotal, 2, BigDecimal.ROUND_HALF_UP)
								.multiply(new BigDecimal(100));
					}
					entity.setUpdateRate(updateRate);
				}
			}
			contLedgerManager.updateContLedger(getId(), "agree", contAddAgreementId);
		}
		return null;
	}

	public String deletePay() throws Exception {
		String contPayId = Struts2Utils.getParameter("contPayId");
		contPayManager.deleteContPay(contPayId);
		return null;
	}

	/**
	 * 用于网上审批选择合同编号
	 * 
	 * @param value
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String codeType = Struts2Utils.getParameter("codeType");
		String contNo = Struts2Utils.getParameter("contNo");

		// 是否需要审核权限
		String isAudit = Struts2Utils.getParameter("isAudit");
		page.setPageSize(15);
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer()
				.append("select a from ContLedger a,ContProjectCode b where a.projectCd=b.projectCd  ");
		if (StringUtils.isBlank(codeType)) {
			hql.append(" and b.codeType=:codeType");
			param.put("codeType", "1");// 1合同台账,2商业台账
		} else if (!"0".equals(codeType)) {
			hql.append(" and b.codeType=:codeType");
			param.put("codeType", codeType);
		}
		// 如果有合同编号，则精确搜索
		if (StringUtils.isNotBlank(contNo)) {

			hql.append(" and( a.contName =:contValue");
			hql.append(" or a.contNo =:contValue )");
			param.put("contValue", contNo);

		} else if (StringUtils.isNotBlank(value)) {// 否则模糊搜索
			hql.append(" and( a.contName like :contValue");
			hql.append(" or a.contNo like :contValue )");
			param.put("contValue", "%" + value + "%");
		}
		if (isAudit == null || !"noAudit".equals(isAudit)) {
			hql.append(" and a.contAuditStatus=:contAuditStatus");// 已审核
			param.put("contAuditStatus", "2");// 2-已审核
		}
		
		param.put("enableFlg","1");//1-可用
		hql.append(" and a.enableFlg = :enableFlg ");

		page = contLedgerManager.findPage(page, hql.toString(), param);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (ContLedger cl : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("contLedgerId", cl.getContLedgerId()); //合同台账Id（主键）
			map.put("contNo", cl.getContNo()); // 合同编号
			map.put("contName", StringUtils.trimToEmpty(cl.getContName())); // 合同名称
			map.put("contLedgerId", cl.getContLedgerId()); // 合同台账主键
			map.put("projectCd", StringUtils.trimToEmpty(cl.getProjectCd())); // 项目cd
			if (StringUtils.isNotBlank(cl.getProjectCd())) {
				map.put("projectName", CodeNameUtil.getDeptNameByCd(cl.getProjectCd())); // 项目名称
			}
			// map.put("contTypeName", getTypeName(cl.getContTypeCd())); //分期及类型
			map.put("partB", StringUtils.trimToEmpty(cl.getPartB())); // 乙方(收款人)
			map.put("rangeNum", StringUtils.trimToEmpty(cl.getRangeNum())); // 范围/数量
			map.put("totalPrice", NumberUtil.formatNumber(cl.getTotalPrice(), "#,##0.00")); // 合同总价
			map.put("payWay", StringUtils.trimToEmpty(cl.getPayWay())); // 付款方式
			map.put("guarBeginDate", DateOperator.formatDate(cl.getGuarBeginDate(), "yyyy-MM-dd")); // 保修期开始日期
			map.put("guarEndDate", DateOperator.formatDate(cl.getGuarEndDate(), "yyyy-MM-dd")); // 保修期结束日期
			map.put("clearPrice", NumberUtil.formatNumber(cl.getClearPrice(), "#,##0.00")); // 结算价
			map.put("updateTotal", NumberUtil.formatNumber(cl.getUpdateTotal(), "#,##0.00")); // 变更后的合同总价
			map.put("realBeginDate", DateOperator.formatDate(cl.getRealBeginDate(), "yyyy-MM-dd")); // 实际开工日期
			map.put("realEndDate", DateOperator.formatDate(cl.getRealEndDate(), "yyyy-MM-dd")); // 实际竣工日期
			map.put("period", StringUtils.trimToEmpty(cl.getPeriod())); // 合同工期(天)
			map.put("partC", StringUtils.trimToEmpty(cl.getPartC())); // 丙方
			map.put("realProvName", StringUtils.trimToEmpty(cl.getRealProvName())); // 实际供方
			map.put("contStatus", cl.getContStatus());// 合同状态 0未完结，1已完未结，2已完已结
			// 计划开工日期
			map.put("planBeginDate", DateOperator.formatDate(cl.getPlanBeginDate(), "yyyy-MM-dd"));
			// 计划竣工日期
			map.put("planEndDate", DateOperator.formatDate(cl.getPlanEndDate(), "yyyy-MM-dd"));
			// 附合同性质
			Map<String, String> types = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_CONT_PROPERTY);
			map.put("contProperty", StringUtils.trimToEmpty(types.get(cl.getContProperty()))); // 合同性质
			map.put("pricingModel", cl.getContProperty());
			map.put("visaTotal", NumberUtil.formatNumber(cl.getVisaTotal(), "#,##0.00")); // 签证变更价累计
			map.put("amountUpdateRate", NumberUtil.formatNumber(cl.getUpdateRate(), "#,##0")); // 累计变更比例
			// 若有定标，则放定标审批表
			if (null != cl.getResApproveId()) {
				map.put("bidApprIdFromCont", cl.getResApproveId());
				map.put("bidApprNoFromCont", cl.getResApproveCd());
			}

			BigDecimal completeNum = new BigDecimal(0);
			BigDecimal matieralNum = new BigDecimal(0);
			BigDecimal currentAdd = new BigDecimal(0);
			BigDecimal currentPay = new BigDecimal(0);
			BigDecimal payRate = new BigDecimal(0);
			BigDecimal zero = new BigDecimal(0);
			for (ContPay contPay : cl.getContPaies()) {
				if (contPay.getCompleteNum() != null) {
					completeNum = completeNum.add(contPay.getCompleteNum());
				}
				if (contPay.getMatieralNum() != null) {
					matieralNum = matieralNum.add(contPay.getMatieralNum());
				}
				if (contPay.getCurrentAdd() != null) {
					currentAdd = currentAdd.add(contPay.getCurrentAdd());
				}
				if (contPay.getCurrentPay() != null) {
					currentPay = currentPay.add(contPay.getCurrentPay());
				}
				// if(contPay.getPayRate() != null) {
				// payRate = payRate.add(contPay.getPayRate());
				// }
			}
			if (completeNum.compareTo(zero) != 0) {
				// (扣：甲供料款(按暂定价)+扣：其他扣款或代扣款+直接支付)/已确认产值(含甲供料)
				payRate = (matieralNum.add(currentAdd).add(currentPay)).divide(completeNum, 2,
						BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
			}
			map.put("completeNum", NumberUtil.formatNumber(completeNum, "#,##0.00")); // 已确认产值(含甲供料)
			map.put("matieralNum", NumberUtil.formatNumber(matieralNum, "#,##0.00")); // 扣：甲供料款(按暂定价)
			map.put("currentAdd", NumberUtil.formatNumber(currentAdd, "#,##0.00")); // 扣：其他扣款或代扣款
			map.put("currentPay", NumberUtil.formatNumber(currentPay, "#,##0.00")); // 直接支付
			map.put("payRate", NumberUtil.formatNumber(payRate, "#,##0.00")); // 付款比例:
			if(cl.getPayNum()!=null)
			 {
				map.put("currentRealPay", cl.getPayNum().toString());//当前实际付款
			}
			// 变更
			if (cl.getContVisaUpdates() != null) {
				BigDecimal preFeeNum = new BigDecimal(0);
				for (ContVisaUpdate visa : cl.getContVisaUpdates()) {
					preFeeNum = preFeeNum.add(visa.getPrepareFee() == null ? zero : visa.getPrepareFee());
				}
				// 累计预估费用
				map.put("preFeeNum_before", preFeeNum.toString());
				// 预估变更比例
				if (cl.getUpdateTotal() != null && cl.getUpdateTotal().compareTo(zero) != 0) {
					map.put("preFeeRate_before", preFeeNum.divide(cl.getUpdateTotal(), 2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(100)).toString());
				} else {
					map.put("preFeeRate_before", zero.toString());
				}

			} else {
				// 累计预估费用
				map.put("preFeeNum_before", zero.toString());
				// 预估变更比例
				map.put("preFeeRate_before", zero.toString());
			}
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 网批里面搜索变更编号字段
	 * 
	 * @param ledgerId
	 * 
	 */
	public void searchVisaById() throws Exception {

		String tLedgerId = Struts2Utils.getParameter("ledgerId");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		if (StringUtils.isNotBlank(tLedgerId)) {
			ContLedger tLedger = contLedgerManager.getEntity(tLedgerId);
			if (tLedger != null) {
				Map<String, String> map = null;
				BigDecimal t = null;
				for (ContVisaUpdate tVisaUpdate : tLedger.getContVisaUpdates()) {
					t = tVisaUpdate.getAmountUpdate();// 变更增减金额(元)
					// 注意： 仅获取"变更增减金额(元)"为空或0的数据。
					if (t == null || t.intValue() == 0) {
						map = new HashMap<String, String>();
						map.put("visaNoId", tVisaUpdate.getContVisaUpdateId());
						map.put("visaContent", tVisaUpdate.getContent());
						map.put("visaNo", tVisaUpdate.getVisaUpdateNo());
						map.put("prepareFee", NumberUtil.formatNumber(tVisaUpdate.getPrepareFee(), "#,##0.00"));
						if (StringUtils.isNotBlank(tVisaUpdate.getPreVisaContent())) {
							String[] preVisaContent = tVisaUpdate.getPreVisaContent().split(";/;");
							map.put("area", preVisaContent[0]);
							map.put("partATrustPart", preVisaContent[1]);
							map.put("partAOperator", preVisaContent[2]);
							map.put("consultRate", preVisaContent[3]);
						} else {
							map.put("area", "");
							map.put("partATrustPart", "");
							map.put("partAOperator", "");
							map.put("consultRate", "");
						}
						list.add(map);
					}
				}
			}
		}
		Struts2Utils.renderJson(list);
	}

	// 更新合同类别数据
	public void updateData() {
		contLedgerManager.updateData();
	}

	public Map<String, String> getMapContProject() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// List<ContProjectCode> codeList =contProjectCodeManager.getAll();
		List<ContProjectCode> codeList;
		if (StringUtils.isNotBlank(ledgerType)) {
			String hql = " from ContProjectCode where codeType='" + ledgerType + "'";
			codeList = contProjectCodeManager.find(hql);
		} else {
			codeList = contProjectCodeManager.getAll();
		}
		if (codeList != null) {
			/*
			 * orgEstateOrgList = PlasCache.getLogicalDirectOrgListByOrgCd("135"); for(WsPlasOrg wsUaapOrg : orgEstateOrgList){
			 * orgEstateOrgMap.put(wsUaapOrg.getOrgCd(), wsUaapOrg.getOrgName()); }
			 */
			map.put("", "总和");
			for (ContProjectCode code : codeList) {
				map.put(code.getProjectCd(), CodeNameUtil.getDeptNameByCd(code.getProjectCd()));
			}
		}
		return map;
	}
	
	public List getCostList(){
		
		List<ContProjectCode> codeList = null;
		if (StringUtils.isNotBlank(ledgerType)) {
			Map<String, Object> values = new HashMap<String,Object>();
			values.put("ledgerType",ledgerType);
			String hql = " from ContProjectCode where codeType = :ledgerType ";
			codeList = contProjectCodeManager.find(hql, ledgerType);
		} else {
			codeList = contProjectCodeManager.getAll();
		}
		return codeList;
	}
	

	/**
	 * 快速搜索领用单位 (网上下单用)strategy-online-order.jsp
	 * 
	 * @param value
	 *            领用单位名称/合同编号
	 * @return
	 * @throws Exception
	 */
	public String quickSearchPartB() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String codeType = Struts2Utils.getParameter("codeType");
		page.setPageSize(20);
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer()
				.append("select a from ContLedger a,ContProjectCode b where a.projectCd=b.projectCd ");
		if (StringUtils.isBlank(codeType)) {
			hql.append(" and b.codeType=:codeType");
			param.put("codeType", "1");// 1为合同台账,2商业台账
		}
		if (StringUtils.isNotBlank(value)) {
			hql.append(" and( a.partB like :contValue");
			hql.append(" or a.contNo like :contValue )");
			param.put("contValue", "%" + value + "%");
		}
		
		param.put("enableFlg","1");//1-可用
		hql.append(" and a.enableFlg = :enableFlg ");
		
		page = contLedgerManager.findPage(page, hql.toString(), param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (ContLedger cl : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("contNo", cl.getContNo()); // 合同编号
			map.put("contName", cl.getContName()); // 合同名称
			map.put("contLedgerId", cl.getContLedgerId()); // 合同台账主键
			map.put("projectCd", StringUtils.trimToEmpty(cl.getProjectCd())); // 项目cd
			map.put("partB", StringUtils.trimToEmpty(cl.getPartB())); // 乙方(收款人)
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = contLedgerManager.getEntity(getId());
		} else {
			entity = new ContLedger();
		}

	}

	public String getContTypes() {
		return contTypes;
	}

	public void setContTypes(String contTypes) {
		this.contTypes = contTypes;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContStatus() {
		return contStatus;
	}

	public void setContStatus(String contStatus) {
		this.contStatus = contStatus;
	}

	public String getContProcess() {
		return contProcess;
	}

	public void setContProcess(String contProcess) {
		this.contProcess = contProcess;
	}

	public Map<String, String> getContTypeMap() {
		return contTypeMap;
	}

	public void setContTypeMap(Map<String, String> contTypeMap) {
		this.contTypeMap = contTypeMap;
	}

	public Map<String, String> getMapVisaCause() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_VISA_CONTENT);
	}

	// 地产公司合同类别
	public Map<String, String> getMapTypeByEstate() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
	}

	// 商业公司合同类别
	public Map<String, String> getMapTypeByBis() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_BIS_TYPE);
	}

	// 酒店公司合同类别
	public Map<String, String> getMapTypeByHotel() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_HOTEL_TYPE);
	}

	public ArrayList getContAttaList() {
		return contAttaList;
	}

	public void setContAttaList(ArrayList contAttaList) {
		this.contAttaList = contAttaList;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getNowUiid() {
		return SpringSecurityUtils.getCurrentUiid();
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public boolean getPlanEndFlg(Date planEndDate) {
		boolean endFlg = false;

		Date newDate = DateOperator.addMonthes(planEndDate, 3);
		if (newDate.compareTo(DateOperator.getDateNow()) < 0) {
			endFlg = true;
		}
		return endFlg;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getTypeByCd() {
		return typeByCd;
	}

	public void setTypeByCd(String typeByCd) {
		this.typeByCd = typeByCd;
	}

	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Map<String, String> getProjectMap() {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("");
		hql.append("from ContProjectCode a where 1=1");
		if (StringUtils.isNotBlank(ledgerType)) {
			hql.append(" and a.codeType=:ledgerType");
			param.put("ledgerType", ledgerType);
		}
		List<ContProjectCode> projectList = contProjectCodeManager.find(hql.toString(), param);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (ContProjectCode project : projectList) {
			map.put(project.getProjectCd(), project.getProjectName());
		}
		return map;
	}

	public void prepareEntity() throws Exception {

		String contNoP = ChangeCharset.decode2UTF_8(Struts2Utils.getParameter("contNo"));
		List<ContLedger> contList = new ArrayList<ContLedger>();
		if ((StringUtils.isNotBlank(contNoP))) {
			String hql = "select a from ContLedger a where a.contNo=?";
			contList = contLedgerManager.find(hql, contNoP);
			if (contList != null && contList.size() > 0) {
				entity = contList.get(0);
			} else {
				entity = new ContLedger();
			}
		}
	}

	public String entity() {
		buildToTypeMap(entity);
		// 搜索是否有附件，若有附件，则显示有附件图标
		if (StringUtils.isNotBlank(entity.getContLedgerId())) {
			// 协议书ID 0
			if (StringUtils.isNotBlank(entity.getAgreeAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getAgreeAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			// 专用条款ID 1
			if (StringUtils.isNotBlank(entity.getSpecialAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getSpecialAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			// 结算附件 2
			if (StringUtils.isNotBlank(entity.getClearAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getClearAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
			// 保修协议 3
			if (StringUtils.isNotBlank(entity.getRepairAttaId())) {
				List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getRepairAttaId());
				if (file != null && file.size() > 0) {
					contAttaList.add("1");
				} else {
					contAttaList.add("0");
				}
			} else {
				contAttaList.add("0");
			}
		} else {
			// 新建
			for (int i = 0; i < 4; i++) {
				contAttaList.add("0");
			}
		}
		return INPUT;
	}

	public String detail() throws Exception {
		String contNoP = Struts2Utils.getParameter("contNo");
		List<ContLedger> contList = new ArrayList<ContLedger>();
		if ((StringUtils.isNotBlank(contNoP))) {
			String hql = "select a from ContLedger a where a.contNo=?";
			contList = contLedgerManager.find(hql, contNoP);
			if (contList != null && contList.size() > 0) {
				contLedger = contList.get(0);
			} else {
				contLedger = new ContLedger();
			}
		}
		return "detail";
	}

	/**
	 * @return the contLedger
	 */
	public ContLedger getContLedger() {
		return contLedger;
	}

	/**
	 * @param contLedger
	 *            the contLedger to set
	 */
	public void setContLedger(ContLedger contLedger) {
		this.contLedger = contLedger;
	}

	public File getImportCont() {
		return importCont;
	}

	public void setImportCont(File importCont) {
		this.importCont = importCont;
	}

	public String getRealProvName() {
		return realProvName;
	}

	public void setRealProvName(String realProvName) {
		this.realProvName = realProvName;
	}

	public String getStrageFlg() {
		return strageFlg;
	}

	public void setStrageFlg(String strageFlg) {
		this.strageFlg = strageFlg;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getOnlyCompanyFlg() {
		return onlyCompanyFlg;
	}

	public void setOnlyCompanyFlg(String onlyCompanyFlg) {
		this.onlyCompanyFlg = onlyCompanyFlg;
	}

	/**
	 * 导出合同台账的签证变更历史
	 * 
	 * add by huangbijin 2012-06-07 成本指令项目公司汇总变更记录。
	 * @return
	 */
	public String exportContVisaList() {

		String tmpBidLedgerId = Struts2Utils.getParameter("bidLedgerId");
		if(StringUtils.isBlank(tmpBidLedgerId))
			return null;
  
		ContLedger tmpLedger  =  contLedgerManager.getEntity(tmpBidLedgerId);
		if(tmpLedger == null)
			return null;

		String title ="导出" + tmpLedger.getContName() +"(合同编号：" +tmpLedger.getContNo()+ ")签证变更历史";
		
		try { 
			
			List<VoContVisaUpdate> voList =  contVisaUpdateManager.getVoVisaList(tmpBidLedgerId);
			
			// long l1 = System.currentTimeMillis();
			Map<String, Object> beanMap = new HashMap<String, Object>();
			beanMap.put("title", title);
			beanMap.put("result", voList);

			is = JXLExcelUtil.initJxlsInputStream(beanMap, "contVisaList.xls");
			downFileName = new String(title.getBytes("GBK"), "ISO8859-1");

			// long l2 = System.currentTimeMillis();
			// log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileName +", 耗时 " + (l2-l1)/1000.00 + " 秒");
		} catch (Exception e) {
//			log.error(title, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "exportVisaList";
	}

	public String getContVisaId() {
		return contVisaId;
	}

	public void setContVisaId(String contVisaId) {
		this.contVisaId = contVisaId;
	}

	public List<ContVisaUpdate> getContVisaList() {
		return contVisaList;
	}

	public void setContVisaList(List<ContVisaUpdate> contVisaList) {
		this.contVisaList = contVisaList;
	}

	public List<ContAddAgreement> getContAddList() {
		return contAddList;
	}

	public void setContAddList(List<ContAddAgreement> contAddList) {
		this.contAddList = contAddList;
	}

	public String getContAddId() {
		return contAddId;
	}

	public void setContAddId(String contAddId) {
		this.contAddId = contAddId;
	}

	public String getResDiplayNo() {
		return resDiplayNo;
	}

	public void setResDiplayNo(String resDiplayNo) {
		this.resDiplayNo = resDiplayNo;
	}
	
	
	/**
	 * 删除合同(逻辑删除)
	 * @param contId 合同编号
	 * @return
	 */
	public String deleteCont(){
		String contId = Struts2Utils.getParameter("contId");
		if(StringUtils.isBlank(contId)){
			Struts2Utils.renderText("参数不对!");
			return null;
		}
		
		ContLedger t = contLedgerManager.getContLedger(contId);
		if(t == null){
			Struts2Utils.renderText("找不到合同!");
			return null;
		}
		
		String enableFlg = t.getEnableFlg();
		if("0".equals(enableFlg)){
			Struts2Utils.renderText("合同已删除,不需要重复删除!");
			return null;
		}
		
		t.setEnableFlg("0");//0-不可用
		contLedgerManager.saveContLedger(t);

		Struts2Utils.renderText("success");
		return null;
	}
	
}
