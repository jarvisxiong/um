package com.hhz.ump.web.bis;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisPayManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisPay;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.vo.VoFact;
import com.hhz.ump.web.vo.VoPay;
import com.opensymphony.xwork2.ActionContext;
@Results({@Result(name = "export", type = "stream", params = {"contentType", "application/vnd.ms-excel", "inputName",
        "excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls"})
})
public class BisPayAction extends CrudActionSupport<BisPay> {

    //工具辅助类
    public HelperUtil helper = new HelperUtil();

    @Autowired
    private BisProjectManager bisProjectManager;
    @Autowired
    private BisPayManager bisPayManager;
    private BisPay entity;
    private List<VoPay> payList;
    private String bisProjectId;
    private String projectName;
    private String chargeTypeName;
    private VoFact voFact;
    private InputStream excelFile;//导出Excel需要的参数
    private String excelFileName;

    
    
    public HelperUtil getHelper() {
		return helper;
	}

	public void setHelper(HelperUtil helper) {
		this.helper = helper;
	}

	public VoFact getVoFact() {
		return voFact;
	}

	public void setVoFact(VoFact voFact) {
		this.voFact = voFact;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getBisProjectId() {
        return bisProjectId;
    }

    public void setBisProjectId(String bisProjectId) {
        this.bisProjectId = bisProjectId;
    }

    public List<VoPay> getPayList() {
        return payList;
    }

    public void setPayList(List<VoPay> payList) {
        this.payList = payList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getChargeTypeName() {
        return chargeTypeName;
    }

    public void setChargeTypeName(String chargeTypeName) {
        this.chargeTypeName = chargeTypeName;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            entity = bisPayManager.getEntity(getId());
            projectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();
        } else {
            entity = new BisPay();
        }
    }

    @Override
    public BisPay getModel() {
        return entity;
    }

    @Override
    public String deleteBatch() throws Exception {
        return null;
    }



    @Override
    public String list() throws Exception {

        Struts2Utils.getRequest().setAttribute("isProjectBusinessCompany", bisProjectManager.getCurrProject() != null);

        if (StringUtils.isBlank(bisProjectId)) {
            BisProject bisProject = bisProjectManager.getCurrProject();
            if (bisProject != null) {
                bisProjectId = bisProject.getBisProjectId();
                projectName = bisProject.getProjectName();
            }
        } else {
            projectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
        }
        return SUCCESS;
    }

    private String getParameter(String name) {
        String parameter = Struts2Utils.getParameter(name);
        if (StringUtils.isBlank(parameter)) {
            parameter = Struts2Utils.getParameter("filter_EQB_" + name);
        }
        return parameter;

    }
    /***
     * 搜索集合
     * Excel模板导出
     * @author +Aspenn
     * @return
     */
    public String search() {
        String sortField = Struts2Utils.getParameter("sort");
        String order = Struts2Utils.getParameter("order");
        String pageNo = Struts2Utils.getParameter("pageNo");
        String rows = Struts2Utils.getParameter("rows");
        String chargeTypeCd = getParameter("chargeTypeCd");
        String month = getParameter("month");
        String year = getParameter("year");
        String bisPId = getParameter("bisProjectId");
        /**指定模板名称*/
    	String templateName = "chargingDetails.xls";
        Map resultMap = new HashMap();
        String isExcel = Struts2Utils.getParameter("isExcel");
        if(isExcel!=null&&!"".equals(isExcel)){//如果是导出文件，页大小设置到最大
        	page.setPageSize(99999999);
        }
        if (StringUtils.isNotBlank(pageNo)) {
            page.setPageNo(Integer.valueOf(pageNo));
        }
        if (rows != null) {
            page.setPageSize(Integer.valueOf(rows));
        }
        // 设置默认排序方式
        if (StringUtils.isEmpty(sortField)) {
            page.setOrderBy("createdDate");
            page.setOrder(Page.DESC);
        } else {
            page.setOrderBy(sortField + ",createdDate");
            page.setOrder(order + "," + Page.DESC);
        }
        StringBuffer hql = new StringBuffer("from BisPay where 1=1 ");
        StringBuffer sql = new StringBuffer("from bis_pay where 1=1 ");
        if(StringUtils.isEmpty(chargeTypeCd)){
        	hql.append("and chargeTypeCd != 'B29'");
        	sql.append("and charge_type_cd <> 'B29'");
        }
        Map<String, Object> pram = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(chargeTypeCd)) {
            hql.append(" and chargeTypeCd =:chargeTypeCd");
            sql.append(" and charge_type_cd =:chargeTypeCd");
            pram.put("chargeTypeCd", chargeTypeCd);
        }
        if (StringUtils.isNotEmpty(month)) {
            hql.append(" and month =:month");
            sql.append(" and month =:month");
            pram.put("month", month);
        }
        if (StringUtils.isNotEmpty(year)) {
            hql.append(" and year =:year");
            sql.append(" and year =:year");
            pram.put("year", year);
        }
        if (StringUtils.isNotEmpty(bisPId)) {
            hql.append(" and bisProjectId =:bisProjectId");
            sql.append(" and bis_project_id =:bisProjectId");
            pram.put("bisProjectId", bisPId);
        }
        if (StringUtils.isEmpty(sortField)) {
            hql.append(" order by createdDate desc");
        } else {
            hql.append(" order by ").append(sortField).append(" ").append(order).append(", createdDate desc");
        }
        page = bisPayManager.findPage(page, hql.toString(), pram);
        Map<String, String> map=DictMapUtil.getMapExpenseType();
        map.put("B29","营业外收入");
        Map<String, String> opMap = DictMapUtil.getBisOperationTypeCd();
        if(isExcel!=null&&!"".equals(isExcel)){
        	List<BisPay> exlist = new ArrayList<BisPay>();
        	for (BisPay bisPay : page.getResult()) {
				bisPay.setChargeTypeCd(map.get(bisPay.getChargeTypeCd())==null?"":map.get(bisPay.getChargeTypeCd()).toString());
				bisPay.setOperationType(opMap.get(bisPay.getOperationType())==null?"":opMap.get(bisPay.getOperationType()).toString());
				exlist.add(bisPay);
			}
        	resultMap.put("result", exlist);
			String fileName = "支出明细-" + DateOperator.formatDate(new Date(), "MMddHHmm");
        	try {
				excelFile = JXLExcelUtil.initJxlsInputStream(resultMap, templateName);
				excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return "export";
        }else{
	        payList = new ArrayList<VoPay>();
	        transfer2Vo(page.getResult(), payList);
	        List list = bisPayManager.findBySql("select sum(budget_money) " + sql.toString(), pram);
	        if (list != null && list.size() > 0 && list.get(0) != null) {
	            ActionContext.getContext().put("budgetMoney", list.get(0).toString());
	        }
	        list = bisPayManager.findBySql("select sum(money) " + sql.toString(), pram);
	        if (list != null && list.size() > 0 && list.get(0) != null) {
	            ActionContext.getContext().put("money", list.get(0).toString());
	        }
	        //JsonUtil2.renderJson(page,new String[]{"appDictDatas"});
	        return "list";
        }
    }

    private void transfer2Vo(List<BisPay> pays, List<VoPay> vos) {
        VoPay vo = null;

        for (BisPay obj : pays) {
            vo = new VoPay();
            vo.setBisProjectId(obj.getBisProjectId());
            vo.setYear(obj.getYear());
            vo.setMonth(obj.getMonth());
            if (null != obj.getMoney()) {
                vo.setMoney(obj.getMoney());
            } else {
                vo.setMoney(new BigDecimal(0));
            }
            if (null != obj.getBudgetMoney()) {
                vo.setBudgetMoney(obj.getBudgetMoney());
            } else {
                vo.setBudgetMoney(new BigDecimal(0));
            }
            vo.setBisPayId(obj.getBisPayId());
            vo.setChargeTypeCd(obj.getChargeTypeCd());
            try {
                vo.setProjectName(bisProjectManager.getEntity(obj.getBisProjectId()).getProjectName());
            } catch (Exception e) {
                continue;
                //数据丢失
            }
            vo.setChargeTypeCdName(DictMapUtil.getMapExpenseType().get(vo.getChargeTypeCd()));
            vo.setOpearateDate(obj.getPayDate());
            vo.setCreator(obj.getCreator());
            vo.setRemark(obj.getOperationType());
            vo.setOperationType(obj.getOperationType());
            vos.add(vo); 
        }
        //ActionContext.getContext().put("budgetMoney",budgetMoney.setScale(2,BigDecimal.ROUND_HALF_UP));
        //ActionContext.getContext().put("money",money.setScale(2,BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public void prepareInput() throws Exception {
        prepareModel();
    }

    @Override
    public String input() throws Exception {
        String pId = Struts2Utils.getParameter("bisProjectId");
        if (StringUtils.isNotBlank(pId)) {
            projectName = bisProjectManager.getEntity(pId).getProjectName();
        }
        return "input";
    }

    public void prepareIncome() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            entity = bisPayManager.getEntity(getId());
            projectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();
        } else {
            entity = new BisPay();
        }
    }

    public String income() throws Exception {
        String pId = Struts2Utils.getParameter("bisProjectId");
        if (StringUtils.isNotBlank(pId)) {
            projectName = bisProjectManager.getEntity(pId).getProjectName();
        }
        return "inputIncome";
    }

    /***
     * 导出支出明细到Excel
     * @author Aspenn
     * @remark never used
     * @return
     */
    public String exportChargingDetails(){
    	/**指定模板名称*/
    	String templateName = "chargingDetails.xls";
        Map resultMap = new HashMap();
        voFact.setNoPage(true);
        page.setPageSize(99999999);
        try {
			excelFile = JXLExcelUtil.initJxlsInputStream(resultMap, templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		String fileName = "支出明细-" + DateOperator.formatDate(new Date(), "MMddHHmm");
        try {
			excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return "export";
    }
    @Override
    public void prepareSave() throws Exception {
        //prepareModel();
        String id = Struts2Utils.getParameter("bisPayId");
        if (StringUtils.isNotBlank(id)) {
            entity = bisPayManager.getEntity(getId());
            projectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();
        } else {
            entity = new BisPay();
            
        }
        entity.setPayDate(DateUtil.getCurrtDate());

    }

    @Override
    public String save() throws Exception {

        bisPayManager.saveBisPay(entity);
        Struts2Utils.renderText("success");
      
        return null;
    }

    @Override
    public String delete() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            bisPayManager.deleteBisPay(getId());
            Struts2Utils.renderHtml("success");
        }
        return null;
    }

    public BisPay getEntity() {
        return entity;
    }

    public void setEntity(BisPay entity) {
        this.entity = entity;
    }

}
