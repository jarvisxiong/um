/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.hhz.ump.entity.res.ResApproveInfo;

/**
 * 模板实体基类
 * 
 * @author huangj 2010-8-12
 */
@MappedSuperclass
public class BaseTemplate {
	private ResApproveInfo resApproveInfo;
	private String resApproveInfoId;
	private String templateId;
	private String isAuto;
	//自定义审批人
	private List<ResApproverUser> approverUsers = new ArrayList<ResApproverUser>();
	//关联的网批表单
	private List<ResRelaApproveBill> relaApproveBills = new ArrayList<ResRelaApproveBill>();
	
	/**
	 * @return the isAuto
	 */
	@Transient
	public String getIsAuto() {
		return isAuto;
	}

	/**
	 * @param isAuto
	 *            the isAuto to set
	 */
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}

	/**
	 * 独立于IAutoImport接口的自动导入
	 * 使用方式：
	 * bean文件中--》Override doAutoImport方法
	 * jsp中在form中间加上
	 * <p>
	 * &lt;c:if test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}"> <br>
	 * &lt;input type="hidden" name="templateBean.isAuto" value="true" /> <br>
	 * &lt;/c:if>
	 * </p>
	 */
	@Transient
	public void doAutoImport() {

	};

	private String hotel;// 与酒店有关
	private String trade;// 与行业有关
	private String estate;// 与商业有关
	private String inOut;// 包含预算内、预算外条件

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	// 公司名称
	private String companyName;

	// 预算内
	private String inFlag;

	// 预算外
	private String outFlag;

	// 员工工资
	private String salaryFlag;

	// 自己新增的审批步骤列表
	private String selfStep;

	private String selfStepName;

	public Map<String, String> mapCustomNode = new HashMap<String, String>();

	public String getInFlag() {
		return inFlag;
	}

	public void setInFlag(String inOutFlag) {
		this.inFlag = inOutFlag;
	}

	public String getSalaryFlag() {
		return salaryFlag;
	}

	public void setSalaryFlag(String salaryFlag) {
		this.salaryFlag = salaryFlag;
	}

	public String getOutFlag() {
		return outFlag;
	}

	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

	public String getSelfStep() {
		return selfStep;
	}

	public void setSelfStep(String selfStep) {
		this.selfStep = selfStep;
	}

	public String getSelfStepName() {
		return selfStepName;
	}

	public void setSelfStepName(String selfStepName) {
		this.selfStepName = selfStepName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 中心/项目名称
	 * 
	 * @return
	 */
	@Transient
	public String getResProjectName() {
		return null;
	};

	/**
	 * 中心/项目Cd
	 * 
	 * @return
	 */
	@Transient
	public String getResProjectCd() {
		return null;
	};

	/**
	 * 标题:
	 * 
	 * @return
	 */
	@Transient
	public String getResTitleName() {
		return null;
	};

	@Transient
	public Map<String, String> getMapCustomNode() {
		return mapCustomNode;
	}

	public void setMapCustomNode(Map<String, String> mapCustomNode) {
		this.mapCustomNode = mapCustomNode;
	}
	@Transient
	public ResApproveInfo getResApproveInfo() {
		return resApproveInfo;
	}

	public void setResApproveInfo(ResApproveInfo resApproveInfo) {
		this.resApproveInfo = resApproveInfo;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(nullable = false, length = 50)
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	@Column(name="RES_APPROVE_INFO_ID", unique=true, nullable=false, length=50)
	public String getResApproveInfoId() {
		return resApproveInfoId;
	}

	public void setResApproveInfoId(String resApproveInfoId) {
		this.resApproveInfoId = resApproveInfoId;
	}
	@Transient
	public List<ResApproverUser> getApproverUsers() {
		return approverUsers;
	}

	public void setApproverUsers(List<ResApproverUser> approverUsers) {
		this.approverUsers = approverUsers;
	}
	@Transient
	public List<ResRelaApproveBill> getRelaApproveBills() {
		return relaApproveBills;
	}

	public void setRelaApproveBills(List<ResRelaApproveBill> relaApproveBills) {
		this.relaApproveBills = relaApproveBills;
	}
	public void addRelaApproveBill(ResRelaApproveBill approveBill){
		this.relaApproveBills.add(approveBill);
	}
}
