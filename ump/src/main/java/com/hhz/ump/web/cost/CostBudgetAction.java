package com.hhz.ump.web.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.NumberUtil;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cost.CostBudgetMonthDetailManager;
import com.hhz.ump.dao.cost.CostProjectSectionManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContPay;
import com.hhz.ump.entity.cost.CostBudgetMonthDetail;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.web.cost.vo.CostProject;

@Namespace("/cost")
public class CostBudgetAction extends CrudActionSupport {

	
	private static final long serialVersionUID = 3872408624819873453L;
	
	/**
	 * service注入点
	 */
	@Autowired
	private CostBudgetMonthDetailManager costBudgetMonthDetailManager;
	@Autowired
	private CostProjectSectionManager costProjectSectionManager;
	@Autowired
	private ContLedgerManager contLedgerManager;
	
	
	/**
	 * 月度资金预算分页结果
	 */
	Page<CostBudgetMonthDetail>	cbcpPage=new Page<CostBudgetMonthDetail>();	
	/**
	 * 月度资金预算列表
	 */
	private List<CostBudgetMonthDetail> cbcpList=new ArrayList<CostBudgetMonthDetail>();
	/**
	 * 月度资金预算对象
	 */
	private CostBudgetMonthDetail entity;
	/**
	 * 项目列表(中心列表)
	 */
	private List<CostProject> costProjectList;
 	

	@Override
	public Page<ContLedger> getPage() {
		return page;
	}

	public void setPage(Page<ContLedger> page) {
		this.page = page;
	}
 
	
	@Override
	public String delete() throws Exception {		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {		
		return "listPage";
	}

	@Override
	protected void prepareModel() throws Exception {
	}
	
	/**
	 * 获取项目列表信息
	 * @return
	 */
	public String loadAuthProjectTree() {
		// 根据当前用户信息搜索
		List<CostProjectSection> projects = costProjectSectionManager.getPermSectionList();
		TreePanelNode node = costProjectSectionManager.getSectionTreePanelNode(projects);
		Struts2Utils.renderJson(node);

		 return null;
	}
	
	
	/**
	 * 月度资金模块总入口首页
	 * @return
	 */
	public String load(){
		return "list";
	}
	
	
	
	@Override
	public String save() throws Exception {	
		return null;
	}
	
	/**
	 * 获取相应的模型对象
	 * @Override
	 */	
	@Override
	public Object getModel() {
		//预算ID
		String costBudgetMonthDetailId=Struts2Utils.getParameter("costBudgetMonthDetailId");
		//预算ID不为空
		if(StringUtils.isNotBlank(costBudgetMonthDetailId)){
			//构建搜索条件
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("costBudgetMonthDetailId", costBudgetMonthDetailId);
			//执行搜索,获取预算对象
			entity=costBudgetMonthDetailManager.getCostBudgetCurPeriodById(map);
			
		}
		return entity;
	}
	
	
	
	
	/**
	 * 月度资金预算首页响应
	 * @return
	 */
	public String monthDetail(){
		//获取项目列表
		//getProject();
		return "monthDetail";
	}
	
	
	
	
	
	
	/**
	 * 用于网上审批选择合同编号
	 * 只搜索地产合同台帐
	 * @param value 模糊搜索因子
	 * @param sectionId 项目ID
	 * @param yearMonth 年月 (格式: yyyy-MM)
	 * 
	 * @throws Exception
	 */
	public void quickSearchForCost() throws Exception {
		
		String value = Struts2Utils.getParameter("value");
		
		String tSectionId = Struts2Utils.getParameter("planSectionId");
		String tYearMonth = Struts2Utils.getParameter("planYearMonth");

		List<Map<String, String>> returnMap = new ArrayList<Map<String, String>>();
		
		if(StringUtils.isBlank(tSectionId) || StringUtils.isBlank(tYearMonth)){
			//参数不对
		}else{
			CostProjectSection tSection = costProjectSectionManager.getEntity(tSectionId);
			
			if(tSection!= null){
				// 搜索条件
				Map<String, Object> values = new HashMap<String, Object>();
				// 构造合同模糊搜索语句
				StringBuffer hqlBuf = new StringBuffer()
					.append(" select distinct(t) ")
					.append("    from ContLedger t,ContProjectCode t1 ")
					.append("  where t.projectCd = t1.projectCd ")
					.append("    and t1.codeType = :codeType")
					.append("    and not exists( ")
					.append("      select 1 from CostBudgetMonthDetail tt2 ")
					.append("         where tt2.costBudgetMonth.costProjectSection.projectCd = t1.projectCd")
					.append("           and tt2.contactNo  = t.contNo ")
					.append("           and tt2.costBudgetMonth.budegetYear  = :budegetYear  ")
					.append("           and tt2.costBudgetMonth.budegetMonth = :budegetMonth ")
					.append("           and tt2.costBudgetMonth.costProjectSection.costProjectSectionId = :sectionId ")
					.append("    )")
					.append("    and t.projectCd = :projectCd ");
				
				String[] arr = tYearMonth.split("-");

				values.put("codeType", "1");// 台帐类型 （1为合同台账,2商业台账）默认:1-地产合同台账
				values.put("budegetYear", Long.valueOf(arr[0]));
				values.put("budegetMonth", Long.valueOf(arr[1]));
				values.put("sectionId", tSection.getCostProjectSectionId());
				values.put("projectCd", tSection.getProjectCd());
				
				// 模糊搜索因子
				if (StringUtils.isNotBlank(value)) {
					hqlBuf.append(" and ( t.contName like :contValue or t.contNo like :contValue )");
					values.put("contValue", "%" + value + "%");
				}

				Page<ContLedger> tPage = new Page<ContLedger>();
				// 设置分页(15条)
				tPage.setPageSize(15);
				long l1 = System.currentTimeMillis();
				tPage = contLedgerManager.findPage(tPage, hqlBuf.toString(), values);
				// 构建结果
				long l2 = System.currentTimeMillis();
				System.out.println("搜索耗时间! " + (l2-l1)/1000 + "秒");
				// 构建json数据
				tranResultMap(returnMap, tPage);
			}
		}
		
		Struts2Utils.renderJson(returnMap);
	}
	
	
	
	/**
	 * 根据搜索结果构造需要返回的json数据对象
	 * 
	 * @param list
	 * @param page
	 */
	private void tranResultMap(List<Map<String, String>> list, Page<ContLedger> tmpPage) {
		for (ContLedger cl : tmpPage.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			// 合同台账主键
			map.put("contLedgerId", cl.getContLedgerId());
			// 成本科目
			map.put("contTypeCd2", cl.getContTypeCd2());
			// 合同编号
			map.put("contNo", cl.getContNo());
			// 合同名称
			map.put("contName", cl.getContName());
			// 乙方(收款人)施工单位（乙方）
			map.put("partB", StringUtils.trimToEmpty(cl.getPartB()));
			// 合同总价
			map.put("totalPrice", NumberUtil.formatNumber(cl.getTotalPrice(), "#,##0.00"));
			// 实际合同总价
			map.put("confirmToalPrice", NumberUtil.formatNumber(cl.getUpdateTotal(), "#,##0.00"));
			// 结算价
			map.put("clearPrice", NumberUtil.formatNumber(cl.getClearPrice(), "#,##0.00"));
			// 已完产值合计（元）
			BigDecimal completeNum = new BigDecimal(0);
			// 其中甲供料产值
			BigDecimal matieralNum = new BigDecimal(0);
			// 累计应付款合计(元)
			BigDecimal currentAdd = new BigDecimal(0);
			// 累计实际支付
			BigDecimal currentPay = new BigDecimal(0);
			// 累计支付比率%
			BigDecimal payRate = new BigDecimal(0);
			// 本期：资金应付预算
			BigDecimal currentPayBudget = new BigDecimal(0);
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
			}
			if (completeNum.compareTo(zero) != 0) {
				// (扣：甲供料款(按暂定价)+扣：其他扣款或代扣款+直接支付)/已确认产值(含甲供料)
				payRate = (matieralNum.add(currentAdd).add(currentPay)).divide(completeNum, 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
			}
			map.put("completeNum", NumberUtil.formatNumber(completeNum, "#,##0.00")); // 已确认产值(含甲供料)
			map.put("matieralNum", NumberUtil.formatNumber(matieralNum, "#,##0.00")); // 扣：甲供料款(按暂定价)
			map.put("currentAdd", NumberUtil.formatNumber(currentAdd, "#,##0.00")); // 扣：其他扣款或代扣款
			map.put("currentPay", NumberUtil.formatNumber(currentPay, "#,##0.00")); // 直接支付
			map.put("payRate", NumberUtil.formatNumber(payRate, "#,##0.00")); // 付款比例:
			currentPayBudget = currentAdd.subtract(currentPay);
			map.put("currentPayBudget", NumberUtil.formatNumber(currentPayBudget, "#,##0.00"));

			list.add(map);
		}
	}

	/**
	 * 年度资金预算首页响应
	 * @return
	 */
	public String yearBudget(){
		
		return "yearBudget";
	}
	
	/**
	 * 搜索年度资金预算列表
	 * @return
	 */
	public String yearLoadList(){
		
		return "yearLoadList";
	}
	
	
	
	/**
	 * 新增一条相应项目公司的空数据，并搜索其他的将新增后的此数据返回为第一行
	 * @return
	 */
	public String newYearBudget(){
		
		return "yearLoadList";
	}

	

	public Page<CostBudgetMonthDetail> getCbcpPage() {
		return cbcpPage;
	}

	public void setCbcpPage(Page<CostBudgetMonthDetail> cbcpPage) {
		this.cbcpPage = cbcpPage;
	}

	public List<CostBudgetMonthDetail> getCbcpList() {
		return cbcpList;
	}

	public void setCbcpList(List<CostBudgetMonthDetail> cbcpList) {
		this.cbcpList = cbcpList;
	}

	public CostBudgetMonthDetail getEntity() {
		return entity;
	}

	public void setEntity(CostBudgetMonthDetail entity) {
		this.entity = entity;
	}

	public List<CostProject> getCostProjectList() {
		return costProjectList;
	}

	public void setCostProjectList(List<CostProject> costProjectList) {
		this.costProjectList = costProjectList;
	}

}
