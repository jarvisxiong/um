package com.hhz.ump.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.BisCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.dao.bis.BisFactManager;
import com.hhz.ump.dao.bis.BisFactYuSManager;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisIncomeOtherManager;
import com.hhz.ump.dao.bis.BisMultiManager;
import com.hhz.ump.dao.bis.BisMustManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisTenantManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisContMulti;
import com.hhz.ump.entity.bis.BisFact;
import com.hhz.ump.entity.bis.BisFactYuS;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFlatContRel;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisMultiContRel;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.entity.bis.PayRequisitionVo;
import com.hhz.ump.entity.bis.ShopStoreVo;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.JsonUtil2;
import com.hhz.ump.web.vo.VoCombo;
import com.hhz.ump.web.vo.VoFact;
import com.hhz.ump.web.vo.VoFactOpera;
import com.opensymphony.xwork2.ActionContext;

/**
 * 商业系统-收费明细Action控制层
 * 
 * @author dengyh
 * @date 2012-01-01
 * @modified by xuzb
 * @modified date 2012-3-15
 * @see com.hhz.ump.util.BisConstants
 * @see com.hhz.ump.util.DictContants
 * @see com.hhz.ump.util.DateUtil
 * @since JDK1.6
 */

@Results( {
		@Result(name = "yushou-input", type = "freemarker", location = "bis-fact-yushou-input.ftl"),
		@Result(name = "input", type = "freemarker", location = "bis-fact-input.ftl"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile", "contentDisposition",
				"attachment;filename=${excelFileName}.xls" }) })
@SuppressWarnings( { "rawtypes", "unchecked", "hiding", "static-access", "null" })
public class BisFactAction extends CrudActionSupport<BisFact> {

	// 工具辅助类
	public HelperUtil helper = new HelperUtil();

	private static final long serialVersionUID = 7704687126216417085L;
	@Autowired
	private BisFloorManager bisFloorManager;
	@Autowired
	private BisFactManager bisFactManager;
	@Autowired
	private BisMustManager bisMustManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisTenantManager bisTenantManager;
	@Autowired
	private BisShopManager bisShopManager;
	@Autowired
	private BisFlatManager bisFlatManager;
	@Autowired
	private BisContManager bisContManager;
	@Autowired
	private BisMultiManager bisMultiManager;
	@Autowired
	private BisFactYuSManager bisFactYuSManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private BisIncomeOtherManager bisIncomeOtherManager;
	private BisFact entity;
	private BisFactYuS bisFactYuS;
	private VoFact voFact;
	private String bisTenantId;
	private String bisProjectId;
	private String bisProjectName;
	private String chargeTypeCd;
	private String chargeType;
	private String currDetail;
	private String currDetailName;
	private InputStream excelFile;// 导出Excel需要的参数
	private String excelFileName;
	private String floorNum;
	private String importFactType;

	private String nameShop;
	private String storeNo;
	private BigDecimal pageSize;
	private BigDecimal pageNo;
	private String bisFactId;
	private String bisContId;
	private String shopStoreName; // 租户/商铺的搜索，在切换版面是要能保留
	private String reportDateStart; //时间查询开始范围,同时用于保存切换页面时的时间查询保存
	private String reportDateEnd; //时间查询结束范围，同时用于保存切换页面时的查询保存
	private String ifFromReport;
	/**
	 * 导入实收文件
	 */
	private File importFile;
	private String layOutCd;// 费用业态
	private String factYear;
	private String factMonth;
	private String floorId;
	private String dimension;// 应收1-欠费2-实收3-预收4
	private String module;// 商铺 1 公寓2 多径 3
	private File importFact;
	/**合计显示*/
	private Map<String, String> totalCountMap;
	
	/**连带点击事件*/
	private String mustclick;
	/**
	 * 应收维度
	 */
	private static final String DIMENSION_MUST = "2";
	/**
	 * 欠费维度
	 */
	private static final String DIMENSION_OVERDUE = "3";
	/**
	 * 实收维度
	 */
	private static final String DIMENSION_FACT = "1";
	/**
	 * 预收维度
	 */
	private static final String DIMENSION_ADVANCES = "4";
	/**
	 * 缴费通知维度
	 */
	private static final String DIMENSION_ACT_INCOME = "8";
	private static final String DIMENSION_PAY_INCOME = "6";
	private static final String DIMENSION_PAY_NOTI = "5";
	private static final String DIMENSION_BUDGET = "7";// 预算模块
	private static final String DIMENSION_GYSF_RECORD = "9";// 公寓收费记录模块
	private static final String MODULE_STORE = "1";
	private static final String MODULE_FLAT = "2";
	private static final String MODULE_MULTI = "3";
	private boolean NO_PAGE_SIZE = true;
	private Page<VoFact> voPage;
	private Page<PayRequisitionVo> voPayPage;

	private Map<String, String> mapBisTenant = new HashMap<String, String>();
	private List<BisFact> factList = new ArrayList<BisFact>();
	private String inputStatus = "0";

	@Override
	public BisFact getModel() {
		return entity;
	}

	public Page<VoFact> getVoPage() {
		return voPage;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	public String layout() throws Exception {

		Struts2Utils.getRequest().setAttribute("isProjectBusinessCompany", bisProjectManager.getCurrProject() != null);

		if (StringUtils.isBlank(dimension)) {
			dimension = DIMENSION_FACT;
		}
		if (DIMENSION_MUST.equals(dimension))
			return headerMust();
		else if (DIMENSION_OVERDUE.equals(dimension))
			return headerOverDue();
		else if (DIMENSION_FACT.equals(dimension))
			return headerFact();
		else if (DIMENSION_ADVANCES.equals(dimension))
			return headerAdvances();
		else if (DIMENSION_PAY_NOTI.equals(dimension))
			return headPay();
		else if (DIMENSION_PAY_INCOME.equals(dimension))
			return headIncome();
		else if (DIMENSION_BUDGET.equals(dimension))
			return headerBudget();
		//公寓收费记录 -add by liuzhihui 2012-05-29
		else if (DIMENSION_GYSF_RECORD.equals(dimension))
			return headerGysf();
		else if (DIMENSION_ACT_INCOME.equals(dimension))
			return headerActInCome();
		return SUCCESS;

	}

	private String headIncome() {
		return "headIncome";
	}

	private String headerMust() {
		return "headerMust";
	}

	private String headerOverDue() {
		return "headerOverDue";
	}

	private String headerFact() {
		return "headerFact";
	}

	private String headerAdvances() {
		return "headerAdvances";
	}

	private String headPay() {
		return "headerPay";
	}

	public String header() {
		return "header";
	}

	public String headerBudget() {
		return "headerBudget";
	}
	
	/**
	 * 公寓收费记录 -add by liuzhihui 2012-05-29
	 * @return
	 */
	public String headerGysf() {
		return "headerGysf";
	}
	/**
	 * 初始化公寓楼号列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapBuilding() {
		Map<String, String> mapBuilding = bisFloorManager.getMapBuilding();
		return mapBuilding;
	}
	
	public String headerActInCome() {
		return "headerActInCome";
	}

	public void reflesh(){
		//点击预收明细历史记录操作，计算是否有抵充记录，如果有，则计算出，直接更新
    	if(StringUtils.isNotBlank(bisProjectId)){
    		List<BisFactYuS> bisFactYuSList = bisFactYuSManager.getBisFactYuSList(bisProjectId);
    		if(null !=bisFactYuSList && bisFactYuSList.size()>0) {
				for (BisFactYuS vo :bisFactYuSList) {
					try{
						Double dichongMoney = bisFactManager.getAllDichongMoney(bisProjectId,vo.getBisFactId());
						//Log.debug("money: "+vo.getMoney()+" ,surplusMoney: "+vo.getSurplusMoney()+" , dichongMoney: "+dichongMoney+", total : "+(Double.valueOf(vo.getMoney().toString()) - dichongMoney));
			    		bisFactYuSManager.updateBatchProcessing(vo.getBisFactId(),BigDecimal.valueOf(Double.valueOf(vo.getMoney().toString()) - dichongMoney));
					}catch(Exception e){}
				}
    		}
    	}
    	
	}
	
	@Override
	public String list() throws Exception {
		
		//刷新页面
		reflesh();
		
		this.setMustclick(Struts2Utils.getParameter("mustclick"));
		// roleVidate();
		if (StringUtils.isBlank(bisProjectId)) {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if (bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		} else {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
		if (StringUtils.isNotBlank(bisTenantId)) {
			currDetailName = bisShopManager.getEntity(bisTenantManager.getEntity(bisTenantId).getBisShopId()).getNameCn() + " " + currDetailName;
		}
		return layout();
	}

	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}

	@Override
	public String input() throws Exception {
		Struts2Utils.getRequest().setAttribute("chargeTypes", DictMapUtil.getMapChargeType());
		return "input";
	}

	/**
	 * ' 批量新增：针对商铺维度
	 */
	public String inputs() throws Exception {
		List<VoFact> factList = (List<VoFact>) JsonUtil2.getInsertRecords(VoFact.class);
		BigDecimal sum;
		List<BisFact> result;
		StringBuffer sb;
		for (VoFact vo : factList) {
			sum = new BigDecimal(0);
			result = bisFactManager.getFactForStore(vo.getBisProjectId(), vo.getBisTenantId(), vo.getChargeTypeCd(), vo.getFactYear(), vo.getFactMonth(), vo
					.getBisContId());
			sb = new StringBuffer("<div><table><col width='70px'/><col width='40px'/><col width='60px'/><tr><td>实收金额</td><td>实收日期</td><td>审核状态</td></tr>");
			for (BisFact obj : result) {
				sb.append("<tr><td>").append(obj.getMoney()).append("</td><td>").append(DateUtil.parseDateToString(obj.getFactDate())).append("</td><td>")
						.append(DictMapUtil.getMapBisFactStatus(obj.getStatusCd())).append("</td></tr>");
				sum = sum.add(obj.getMoney());
			}
			vo.setFacts(sb.append("</table></div>").toString());

			sum = vo.getMustMoney().subtract(sum);
			if (sum.compareTo(BigDecimal.ZERO) <= 0) {
				sum = new BigDecimal(0);
			}
			vo.setMoney(sum);
		}
		voPage = new Page<VoFact>();
		voPage.setResult(factList);
		return "store-insert";
	}

	/**
	 * Description 批量新增
	 */
	public String toFactList() {
		pageSet();
		String bisTenantId = null;
		String bisFlatId = null;
		String bisMultiId = null;
		switch (Integer.valueOf(layOutCd)) {
		case 1:
			bisTenantId = currDetail;
			break;
		case 2:
			bisFlatId = currDetail;
			break;
		case 3:
			bisMultiId = currDetail;
			break;
		}
		bisProjectName = bisProjectManager.getEntity(voFact.getBisProjectId()).getProjectName();
		factList = bisFactManager.getFact(bisProjectId, bisTenantId, bisFlatId, bisMultiId, chargeTypeCd, factYear, factMonth, voFact.getBisContId());
		voFact.setChargeTypeCdName(DictMapUtil.getMapChargeType(voFact.getChargeTypeCd()));
		if (StringUtils.isNotBlank(voFact.getBisContId())) {
			BisCont con = bisContManager.getEntity(voFact.getBisContId());
			voFact.setContNo(con.getContNo());
			voFact.setContBigTypeCd("[" + DictMapUtil.getMapContBigType().get(con.getContBigTypeCd()) + "]"
					+ DictMapUtil.getMapContSmallType().get(con.getContSmallTypeCd()));
		}
		return "list-input";
	}

	public List<BisFact> getFactList() {
		return factList;
	}

	public void setFactList(List<BisFact> factList) {
		this.factList = factList;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFactManager.getEntity(getId());
		} else {
			entity = new BisFact();
			entity.setFactDate(DateUtil.getCurrtDate());
			entity.setStatusCd(DictContants.BIS_FACT_NO_CHECK + "");
		}
	}

	@Override
	public String save() throws Exception {
		// 若费用类型为保证金，年月不填
		if (DictContants.BIS_CHARGE_TYPE_02 == chargeTypeCd && DictContants.BIS_CHARGE_TYPE_03 == chargeTypeCd
				&& DictContants.BIS_CHARGE_TYPE_38 == chargeTypeCd) {
			entity.setFactYear("");
			entity.setFactMonth("");
		}
		bisFactManager.saveBisFact(entity);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 批量保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveBatchs() throws Exception {

		Map parameters = ActionContext.getContext().getParameters();
		String bisProjectId = Struts2Utils.getParameter("bisProjectId");
		String layOutCd = Struts2Utils.getParameter("layOutCd");
		String currDetail = Struts2Utils.getParameter("currDetail");
		String bisContId = Struts2Utils.getParameter("bisContId");
		Date factDate = DateParser.defaultDateFormatter.parse(Struts2Utils.getParameter("factDate"));
		String[] indexs = (String[]) parameters.get("index");
		String[] factYears = (String[]) parameters.get("factYear");
		String[] factMonths = (String[]) parameters.get("factMonth");
		String[] chargeTypeCds = (String[]) parameters.get("chargeTypeCd");
		String[] moneys = (String[]) parameters.get("money");
		String[] remarks = (String[]) parameters.get("remark");

		for (int i = 0; i < indexs.length; i++) {
			BisFact bisFact = new BisFact();
			bisFact.setBisProjectId(bisProjectId);
			bisFact.setFactDate(factDate);
			bisFact.setStatusCd(DictContants.BIS_FACT_NO_CHECK);
			bisFact.setFactYear(factYears[i]);
			bisFact.setFactMonth(factMonths[i]);
			bisFact.setChargeTypeCd(chargeTypeCds[i]);
			bisFact.setMoney(new BigDecimal(moneys[i]));
			bisFact.setRemark(remarks[i]);
			bisFact.setBisContId(bisContId);

			switch (Integer.valueOf(layOutCd)) {
			case 1:

				bisFact.setBisTenantId(currDetail);
				if (StringUtils.isNotBlank(currDetail)) {
					String sbisStoreId = "";
					for (String store : currDetail.split("-")) {
						sbisStoreId += store;
					}
					bisFact.setBisStoreId(sbisStoreId);
				}
				// 搜索实收对应的应收记录
				if (StringUtils.isNotBlank(bisFact.getFactYear()) && StringUtils.isNotBlank(bisFact.getFactMonth())
						&& StringUtils.isNotBlank(bisFact.getBisContId())) {
					BisMust bisMust = bisMustManager.getExistsMust(bisFact.getBisContId(), null, null, bisFact.getChargeTypeCd(), bisFact.getFactYear(),
							bisFact.getFactMonth());
					if (bisMust != null) {
						bisFact.setTotalMustMoney(bisMust.getMoney());
						bisProjectId = bisMust.getBisProjectId();
						List<BisFact> bisFactList = bisFactManager.getFact(bisFact.getBisProjectId(), bisFact.getBisTenantId(), null, null, bisFact
								.getChargeTypeCd(), bisFact.getFactYear(), bisFact.getFactMonth(), bisFact.getBisContId());
						BigDecimal totFactMoney = new BigDecimal(0);
						for (BisFact fact : bisFactList) {
							if (null != fact.getMoney()) {
								totFactMoney = totFactMoney.add(fact.getMoney());
							}
						}
						if (null != bisFact.getMoney()) {
							totFactMoney = totFactMoney.add(bisFact.getMoney());
						}
						bisMust.setTotalFactMoney(totFactMoney);
						try {
							bisMustManager.save(bisMust);
						} catch (Exception ex) {
						}
					}
					// 如果实收记录已审核，不允许修改
					bisFactManager.factInput2Store(SpringSecurityUtils.getCurrentUiid(), bisFact);
				}
				break;
			case 2:
				if (StringUtils.isNotBlank(currDetail) && !StringUtils.equalsIgnoreCase(currDetail, "0")) {
					bisFact.setBisFlatId(currDetail);
				}
				// 如果实收记录已审核，不允许修改
				bisFactManager.factInput2Flat(SpringSecurityUtils.getCurrentUiid(), bisFact);
				// 搜索实收对应的应收记录
				if (StringUtils.isNotBlank(bisFact.getFactYear()) && StringUtils.isNotBlank(bisFact.getFactMonth())
						&& StringUtils.isNotBlank(bisFact.getChargeTypeCd())
						&& (StringUtils.isNotBlank(bisFact.getBisFlatId()) || StringUtils.isNotBlank(bisFact.getBisContId()))) {
					BisMust bisMust = bisMustManager.getExistsMust(bisFact.getBisContId(), bisFact.getBisFlatId(), null, bisFact.getChargeTypeCd(), bisFact
							.getFactYear(), bisFact.getFactMonth());
					if (bisMust != null) {
						bisFact.setTotalMustMoney(bisMust.getMoney());
						bisProjectId = bisMust.getBisProjectId();
						List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, bisFact.getBisTenantId(), null, null, bisFact.getChargeTypeCd(),
								bisFact.getFactYear(), bisFact.getFactMonth(), bisFact.getBisContId());
						BigDecimal totFactMoney = new BigDecimal(0);
						for (BisFact fact : bisFactList) {
							if (null != fact.getMoney()) {
								totFactMoney = totFactMoney.add(fact.getMoney());
							}
						}
						if (null != bisFact.getMoney()) {
							totFactMoney = totFactMoney.add(bisFact.getMoney());
						}
						bisMust.setTotalFactMoney(totFactMoney);
						try {
							bisMustManager.save(bisMust);
						} catch (Exception ex) {
						}
						bisFact.setTotalMustMoney(bisMust.getMoney());
					}
					bisFactManager.saveBisFact(bisFact);
				}
				break;
			case 3:
				bisFact.setBisMultiId(currDetail);
				// 若有合同ID，则多经ID为空
				if (StringUtils.isNotBlank(bisFact.getBisContId())) {
					bisFact.setBisMultiId(null);
				}
				bisFactManager.factInput2Multi(SpringSecurityUtils.getCurrentUiid(), bisFact);
				// 搜索实收对应的应收记录
				if (StringUtils.isNotBlank(bisFact.getFactYear()) && StringUtils.isNotBlank(bisFact.getFactMonth())
						&& StringUtils.isNotBlank(bisFact.getChargeTypeCd())
						&& (StringUtils.isNotBlank(bisFact.getBisMultiId()) || StringUtils.isNotBlank(bisFact.getBisContId()))) {
					BisMust bisMust = bisMustManager.getExistsMust(bisFact.getBisContId(), null, bisFact.getBisMultiId(), bisFact.getChargeTypeCd(), bisFact
							.getFactYear(), bisFact.getFactMonth());
					if (bisMust != null) {
						bisFact.setTotalMustMoney(bisMust.getMoney());
						bisProjectId = bisMust.getBisProjectId();
						List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, bisFact.getBisTenantId(), null, null, bisFact.getChargeTypeCd(),
								bisFact.getFactYear(), bisFact.getFactMonth(), bisFact.getBisContId());
						BigDecimal totFactMoney = new BigDecimal(0);
						for (BisFact fact : bisFactList) {
							if (null != fact.getMoney()) {
								totFactMoney = totFactMoney.add(fact.getMoney());
							}
						}
						if (null != bisFact.getMoney()) {
							totFactMoney = totFactMoney.add(bisFact.getMoney());
						}
						bisMust.setTotalFactMoney(totFactMoney);
						try {
							bisMustManager.save(bisMust);
						} catch (Exception ex) {
						}
						bisFact.setTotalMustMoney(bisMust.getMoney());
					}
					bisFactManager.saveBisFact(bisFact);
				}
				break;
			}
		}
		Struts2Utils.renderText("({success:true})");
		return null;
	}

	public void saveBatch() {
		List<BisFact> listInsert = (List<BisFact>) JsonUtil2.getInsertRecords(BisFact.class);
		for (BisFact vo : listInsert) {
			vo.setStatusCd(DictContants.BIS_FACT_NO_CHECK);
			bisFactManager.saveBisFact(vo);
			setId(vo.getBisFactId());
			doUpdateBisMust();
		}
		Struts2Utils.renderText("success");
	}

	@Override
	public String delete() throws Exception {
		prepareModel();
		bisFactManager.factRemove(SpringSecurityUtils.getCurrentUiid(), entity);		
		String chargeTypeCd=entity.getChargeTypeCd();
		String bisFlatId=entity.getBisFlatId();
		String bisContId=entity.getBisContId();
		//公寓搜索条件是如果合同编号存在以合同为主，否则以公寓编号为主
		if(MODULE_FLAT.equals(chargeTypeCd)&& StringUtils.isNotBlank(bisContId)){
			bisFlatId="";
			}
		// 更新应收里面的实收记录
		BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), null, null, entity.getChargeTypeCd(), entity.getFactYear(), entity
				.getFactMonth());
	//	BisMust bisMust = bisMustManager.getExistsMust(bisContId,bisFlatId, entity.getBisMultiId(), entity.getChargeTypeCd(), entity
			//	.getFactYear(), entity.getFactMonth());
		if (null != bisMust) {
			BigDecimal totFactMoney = new BigDecimal(0);
			
			if(MODULE_FLAT.equals(chargeTypeCd)&& StringUtils.isNotBlank(bisContId)){
				bisFlatId="";
			}
			List<BisFact> bisFact1List = bisFactManager.getFact(entity.getBisProjectId(), entity.getBisTenantId(), bisFlatId, entity
					.getBisMultiId(), entity.getChargeTypeCd(), entity.getFactYear(), entity.getFactMonth(),bisContId);
			for (BisFact fact : bisFact1List) {
				totFactMoney = totFactMoney.add(fact.getMoney());
			}
			if (totFactMoney.compareTo(new BigDecimal(0)) != 0) {
				bisMust.setTotalFactMoney(totFactMoney);
			} else {
				bisMust.setTotalFactMoney(null);
			}
			bisMustManager.save(bisMust); // 保存应收款表
		}
		Struts2Utils.renderText("success");
		return null;
	}
	/**
	 * 更新应收
	 */
	public void doUpdateBisMust(){
		try {
			prepareUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	        bisFactManager.factUpdate(SpringSecurityUtils.getCurrentUiid(), getId());
	        //对原数据应收对应的实收进行修改
	        if (factYear != null && !factYear.equals(entity.getFactYear()) || (factMonth != null && !factMonth.equals(entity.getFactMonth()))) {
	            if (StringUtils.isNotBlank(factYear) && StringUtils.isNotBlank(factMonth)
	                    && StringUtils.isNotBlank(entity.getChargeTypeCd()) &&
	                    (StringUtils.isNotBlank(entity.getBisMultiId()) || StringUtils.isNotBlank(entity.getBisContId())
	                            || StringUtils.isNotBlank(entity.getBisFlatId()))) {
	                BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(), factYear, factMonth);
	                if (bisMust != null) {
	                    entity.setTotalMustMoney(bisMust.getMoney());
	                    bisProjectId = bisMust.getBisProjectId();
	                    List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, null, entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(),
	                            factYear, factMonth, entity.getBisContId());
	                    BigDecimal totFactMoney = new BigDecimal(0);
	                    for (BisFact fact : bisFactList) {
	                        if (null != fact.getMoney()) {
	                            totFactMoney = totFactMoney.add(fact.getMoney());
	                        }
	                    }
	                    bisMust.setTotalFactMoney(totFactMoney);
	                    bisMustManager.save(bisMust);
	                }
	            }
	        }
	        //对新数据的应收对应实收进行修改
	        if (StringUtils.isNotBlank(entity.getFactYear()) && StringUtils.isNotBlank(entity.getFactMonth())
	                && StringUtils.isNotBlank(entity.getChargeTypeCd()) &&
	                (StringUtils.isNotBlank(entity.getBisMultiId()) || StringUtils.isNotBlank(entity.getBisContId())
	                        || StringUtils.isNotBlank(entity.getBisFlatId()))) {
	            BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(), entity.getFactYear(), entity.getFactMonth());
	            if (bisMust != null) {
	                entity.setTotalMustMoney(bisMust.getMoney());
	                bisProjectId = bisMust.getBisProjectId();
	                List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, null, entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(), entity.getFactYear(),
	                        entity.getFactMonth(), entity.getBisContId());
	                BigDecimal totFactMoney = new BigDecimal(0);
	                for (BisFact fact : bisFactList) {
	                    if (!fact.getBisFactId().equals(entity.getBisFactId()) && null != fact.getMoney()) {
	                        totFactMoney = totFactMoney.add(fact.getMoney());
	                    }
	                }
	                if (null != entity.getMoney()) {
	                    totFactMoney = totFactMoney.add(entity.getMoney());
	                }
	                bisMust.setTotalFactMoney(totFactMoney);
	                bisMustManager.save(bisMust);
	                entity.setTotalMustMoney(bisMust.getMoney());
	            }
	            bisFactManager.saveBisFact(entity);
	        }
	        
	  
	
}

	public void prepareUpdate() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFactManager.getEntity(getId());
			factMonth = entity.getFactMonth();
			factYear = entity.getFactYear();
		} else
			throw new Exception("this fact not exist!");
	}

	// 修改
	public String update() {
		bisFactManager.factUpdate(SpringSecurityUtils.getCurrentUiid(), getId());
		// 对原数据应收对应的实收进行修改
		if (factYear != null && !factYear.equals(entity.getFactYear()) || (factMonth != null && !factMonth.equals(entity.getFactMonth()))) {
			if (StringUtils.isNotBlank(factYear)
					&& StringUtils.isNotBlank(factMonth)
					&& StringUtils.isNotBlank(entity.getChargeTypeCd())
					&& (StringUtils.isNotBlank(entity.getBisMultiId()) || StringUtils.isNotBlank(entity.getBisContId()) || StringUtils.isNotBlank(entity
							.getBisFlatId()))) {
				BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(),
						factYear, factMonth);
				if (bisMust != null) {
					entity.setTotalMustMoney(bisMust.getMoney());
					bisProjectId = bisMust.getBisProjectId();
					List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, null, entity.getBisFlatId(), entity.getBisMultiId(), entity
							.getChargeTypeCd(), factYear, factMonth, entity.getBisContId());
					BigDecimal totFactMoney = new BigDecimal(0);
					for (BisFact fact : bisFactList) {
						if (null != fact.getMoney()) {
							totFactMoney = totFactMoney.add(fact.getMoney());
						}
					}
					bisMust.setTotalFactMoney(totFactMoney);
					bisMustManager.save(bisMust);
				}
			}
		}
		// 对新数据的应收对应实收进行修改
		if (StringUtils.isNotBlank(entity.getFactYear())
				&& StringUtils.isNotBlank(entity.getFactMonth())
				&& StringUtils.isNotBlank(entity.getChargeTypeCd())
				&& (StringUtils.isNotBlank(entity.getBisMultiId()) || StringUtils.isNotBlank(entity.getBisContId()) || StringUtils.isNotBlank(entity
						.getBisFlatId()))) {
			BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(),
					entity.getFactYear(), entity.getFactMonth());
			if (bisMust != null) {
				entity.setTotalMustMoney(bisMust.getMoney());
				bisProjectId = bisMust.getBisProjectId();
				List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, null, entity.getBisFlatId(), entity.getBisMultiId(), entity.getChargeTypeCd(),
						entity.getFactYear(), entity.getFactMonth(), entity.getBisContId());
				BigDecimal totFactMoney = new BigDecimal(0);
				for (BisFact fact : bisFactList) {
					if (!fact.getBisFactId().equals(entity.getBisFactId()) && null != fact.getMoney()) {
						totFactMoney = totFactMoney.add(fact.getMoney());
					}
				}
				if (null != entity.getMoney()) {
					totFactMoney = totFactMoney.add(entity.getMoney());
				}
				bisMust.setTotalFactMoney(totFactMoney);
				bisMustManager.save(bisMust);
				entity.setTotalMustMoney(bisMust.getMoney());
			}
			bisFactManager.saveBisFact(entity);
		}
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 审核通过
	 */
	public String pass() {
		bisFactManager.factPass(SpringSecurityUtils.getCurrentUiid(), getId());
		Struts2Utils.renderText("success");
		return null;
	}

	public String reject() {
		bisFactManager.factReject(SpringSecurityUtils.getCurrentUiid(), getId());
		Struts2Utils.renderText("success");
		return null;
	}

	public String passAll() {
		String bisFactIds = Struts2Utils.getParameter("bisFactIds");
		String[] tmp = bisFactIds.split(",");
		for (int i = 0; i < tmp.length; i++) {
			bisFactManager.factPass(SpringSecurityUtils.getCurrentUiid(), tmp[i]);
		}
		Struts2Utils.renderText("success");
		return null;
	}

	public String rejectAll() {
		String bisFactIds = Struts2Utils.getParameter("bisFactIds");
		String[] tmp = bisFactIds.split(",");
		for (int i = 0; i < tmp.length; i++) {
			bisFactManager.factReject(SpringSecurityUtils.getCurrentUiid(), tmp[i]);
		}
		return null;
	}

	public String deleteAll() {
		String bisFactIds = Struts2Utils.getParameter("bisFactIds");
		String[] tmp = bisFactIds.split(",");
		for (int i = 0; i < tmp.length; i++) {
			bisFactManager.factRemove(SpringSecurityUtils.getCurrentUiid(), tmp[i]);
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFactManager.getEntity(getId());
			chargeType = DictMapUtil.getMapChargeType(entity.getChargeTypeCd());
		} else {
			entity = new BisFact();
		}
		String mustMoney = Struts2Utils.getParameter("mustMoney");
		if (StringUtils.isNotBlank(mustMoney)) {
			voFact = new VoFact();
			voFact.setMustMoney(new BigDecimal(mustMoney));
		}
	}

	/**
	 * 查找商铺、公寓等合同应收记录 void
	 * 
	 * @throws Exception
	 */
	public void prepareSearchBisMust() throws Exception {
		if (StringUtils.isNotBlank(getBisTenantId())) {
			VoFactOpera result = bisFactManager.factCheckForStore(bisProjectId, bisTenantId, chargeTypeCd, factYear, factMonth);
			if (VoFactOpera.BIS_CONT_CONFLICT_04 == result.getType() || VoFactOpera.BIS_CONT_CONFLICT_05 == result.getType())
				// 此租户当前费用类型所对应的合同有冲突
				throw new Exception(result.getDesc());
		}

	}

	// 设置合同编号与类别
	public void processVoFactCont(Page<VoFact> voPage) {
		if (voPage == null)
			return;
		if (voPage.getResult().size() > 0) {
			voFact = voPage.getResult().get(0);
		}
		if (bisFactManager.notBlank(voFact.getBisContId())) {
			BisCont con = bisContManager.getEntity(voFact.getBisContId());
			voFact.setContNo(con.getContNo());
			voFact.setContBigTypeCd("[" + DictMapUtil.getMapContBigType().get(con.getContBigTypeCd()) + "]"
					+ DictMapUtil.getMapContSmallType().get(con.getContSmallTypeCd()));
		}
	}

	public void prepareEditForStore() throws Exception {
		pageSet();
		if (StringUtils.isNotBlank(voFact.getBisFactId())) {
			BisFact bisFact = bisFactManager.getEntity(voFact.getBisFactId());
			voFact.setMustOrFact(1);
			// 实收
			if (bisFact.getMoney() != null) {
				voFact.setMoney(bisFact.getMoney());
			}
			if (null != bisFact.getBisContId()) {
				// 商铺编号
				voFact.setStoreNo(BisCache.getStoreNos(bisFact.getBisContId()));
				// 合同编号
				voFact.setBisContId(bisFact.getBisContId());
				BisCont bisCont = bisContManager.getEntity(bisFact.getBisContId());
				voFact.setContNo(bisCont.getContNo());
				voFact.setContBigTypeCd("[商铺]" + appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_SMALL_TYPE).get(bisCont.getContBigTypeCd()));
			}
			// 商家名称
			if (null != bisFact.getBisShopId()) {
				BisShop shop = bisShopManager.getEntity(bisFact.getBisShopId());
				voFact.setConnName(shop.getNameCn());
				voFact.setManageCd(DictMapUtil.getMapShopManageType().get(shop.getManageCd()));
				voFact.setShopTypeCd(DictMapUtil.getMapBisShopType().get(shop.getShopTypeCd()));
			}
			voFact.setBisProjectId(bisFact.getBisProjectId());
			voFact.setBisTenantId(bisFact.getBisTenantId());
			voFact.setStatusCd(bisFact.getStatusCd());
			voFact.setStatusCdName(DictMapUtil.getMapBisFactStatus(bisFact.getStatusCd()));
			voFact.setBisFactId(bisFact.getBisFactId());
			voFact.setChargeTypeCd(bisFact.getChargeTypeCd());
			voFact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisFact.getChargeTypeCd()));
			voFact.setMoney(bisFact.getMoney());
			voFact.setFactYear(bisFact.getFactYear());
			voFact.setFactMonth(bisFact.getFactMonth());
			if (StringUtils.isNotBlank(bisFact.getCreator())) {
				voFact.setCreator(CodeNameUtil.getUserNameByCd(bisFact.getCreator()));
			}
			if (bisFact.getTotalMustMoney() != null) {
				voFact.setMustMoney(bisFact.getTotalMustMoney());
			}
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
			// 实收日期
			if(null != bisFact.getFactDate()){
				voFact.setFactDate(format.format(bisFact.getFactDate()));
			}
			if (null != bisFact.getUpdatedDate()) {
				voFact.setUpdateDate(format.format(bisFact.getUpdatedDate()));
			}

			voFact.setLayoutCd("公寓");
			voFact.setCreator(CodeNameUtil.getUserNameByCd(bisFact.getCreator()));
			if (null != bisFact.getCheckUserCd()) {
				voFact.setCheckUserCd(CodeNameUtil.getUserNameByCd(bisFact.getCheckUserCd()));
				//♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢以下行报错null point，请开发人注意♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢
//				voFact.setCheckDate(DateParser.defaultDateFormatter.format(bisFact.getCheckDate()));
			}
			//♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢以下行报错null point，请开发人注意♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢♢
//			voFact.setCreateDate(DateParser.defaultDateFormatter.format(bisFact.getCreatedDate()));
			voFact.setRemark(bisFact.getRemark());

		}
		// voPage = bisFactManager.searchFactForStore(page, voFact);
		// processVoFactCont(voPage);
	}

	public String editForStore() {
		//不需要判断是否为空，前台页面的处理更具是否为空进行判断处理的
		Struts2Utils.getRequest().setAttribute("hasDichongFlag", Struts2Utils.getParameter("bisFactYuSId"));
		return "edit";
	}

	public void prepareEditForFlat() throws Exception {
		pageSet();
		voFact.setMustOrFact(1);
		// voPage = bisFactManager.searchFactForFlat(page, voFact);
		BisFact bisFact = bisFactManager.getEntity(bisFactId);

		// 如果有合同ID，则从合同模块里面查找相关的floor、flat数据
		if (StringUtils.isNotBlank(bisFact.getBisContId())) {
			BisCont bisCont = bisContManager.getEntity(bisFact.getBisContId());
			// 合同编号
			voFact.setContNo(bisCont.getContNo());
			voFact.setBisContId(bisFact.getBisContId());
			
			// 公寓编号
			StringBuffer flatNo = new StringBuffer("");
			// 楼号
			StringBuffer floorNum = new StringBuffer("");
			BisFlat bisFlat = null;

			String houseStruCd = "";
			for (BisFlatContRel rel : bisCont.getBisFlatContRels()) {
				bisFlat = rel.getBisFlat();
				flatNo.append(bisFlat.getFlatNo()).append(",");
				BisFloor floor = bisFlat.getBisFloor();
				floorNum.append(floor.getBuildingNum()).append(",");
				houseStruCd = DictMapUtil.getMapHouseStru().get(bisFlat.getHouseStruCd()) + ",";
			}
			if (houseStruCd.endsWith(",")) {
				houseStruCd = houseStruCd.substring(0, houseStruCd.length() - 1);
			}
			if (flatNo.toString().length() > 1) {
				flatNo.delete(flatNo.toString().length() - 1, flatNo.toString().length());
			}
			if (floorNum.toString().length() > 1) {
				floorNum.delete(floorNum.toString().length() - 1, floorNum.toString().length());
			}
			voFact.setFlatNo(flatNo.toString());
			voFact.setBuildingNum(floorNum.toString());
			BisCont con = bisContManager.getEntity(voFact.getBisContId());
			voFact.setContNo(con.getContNo());
			voFact.setContBigTypeCd("[" + DictMapUtil.getMapContBigType().get(con.getContBigTypeCd()) + "]"
					+ DictMapUtil.getMapContSmallType().get(con.getContSmallTypeCd()));
			voFact.setHouseStruCd(houseStruCd);
		} else {
			// 否则，直接根据实收ID获取相关数据
			BisFlat bisFlat = bisFlatManager.getEntity(bisFact.getBisFlatId());
			voFact.setFlatNo(bisFlat.getFlatNo());
			BisFloor floor = bisFlat.getBisFloor();
			voFact.setBuildingNum(floor.getBuildingNum());
		}
		voFact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisFact.getChargeTypeCd()));
		voFact.setBisFactId(bisFact.getBisFactId());
		voFact.setFactYear(bisFact.getFactYear());
		voFact.setFactMonth(bisFact.getFactMonth());
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		// 实收日期
		voFact.setFactDate(format.format(bisFact.getFactDate()));
		if (bisFact.getTotalMustMoney() != null) {
			voFact.setMustMoney(bisFact.getTotalMustMoney());
		}
		if (bisFact.getMoney() != null) {
			voFact.setMoney(bisFact.getMoney());
		}
		voFact.setStatusCd(bisFact.getStatusCd());
		voFact.setStatusCdName(DictMapUtil.getMapBisFactStatus(bisFact.getStatusCd()));
		voFact.setLayoutCd("公寓");

		voFact.setCreator(CodeNameUtil.getUserNameByCd(bisFact.getCreator()));
		if (null != bisFact.getCheckUserCd()) {
			voFact.setCheckUserCd(CodeNameUtil.getUserNameByCd(bisFact.getCheckUserCd()));
			voFact.setCheckDate(DateParser.defaultDateFormatter.format(bisFact.getCheckDate()));
		}
		voFact.setCreateDate(DateParser.defaultDateFormatter.format(bisFact.getCreatedDate()));
		voFact.setRemark(bisFact.getRemark());

		// 房屋结构
	}

	public String editForFlat() {
		return "edit";
	}

	public void prepareEditForMulti() throws Exception {
		pageSet();
		voFact.setMustOrFact(1);
		// voPage = bisFactManager.searchFactForMulti(page, voFact);
		BisFact bisFact = bisFactManager.getEntity(bisFactId);
		BisMulti bisMulti = null;
		// 如果有合同ID，则从合同模块里面查找相关的多经数据
		if (StringUtils.isNotBlank(bisFact.getBisContId())) {
			BisCont bisCont = bisContManager.getEntity(bisFact.getBisContId());
			// 合同编号
			voFact.setContNo(bisCont.getContNo());
			// 多经名称
			StringBuffer multiName = new StringBuffer("");
			for (BisMultiContRel rel : bisCont.getBisMultiContRels()) {
				bisMulti = rel.getBisMulti();
				multiName.append(bisMulti.getMultiName()).append(",");
			}
			if (multiName.toString().length() > 1) {
				multiName.delete(multiName.toString().length() - 1, multiName.toString().length());
			}
			voFact.setMultiName(multiName.toString());

			String area = "";
			for (BisContMulti bisMulti1 : bisCont.getBisContMultis()) {
				area += bisMulti1.getOperationArea();
			}
			voFact.setOperationArea(area);
			if (null != bisMulti && null != bisMulti.getSquare()) {
				voFact.setSquare(bisMulti.getSquare().toString());
			}
		} else {
			// 否则，直接根据多经ID获取相关数据
			bisMulti = bisMultiManager.getEntity(bisFact.getBisMultiId());
			voFact.setMultiName(bisMulti.getMultiName());
			voFact.setOperationArea(bisMulti.getOperationArea());
			if (null != bisMulti.getSquare()) {
				voFact.setSquare(bisMulti.getSquare().toString());
			}
		}

		voFact.setCreator(CodeNameUtil.getUserNameByCd(bisFact.getCreator()));
		if (null != bisFact.getCheckUserCd()) {
			voFact.setCheckUserCd(CodeNameUtil.getUserNameByCd(bisFact.getCheckUserCd()));
			voFact.setCheckDate(DateParser.defaultDateFormatter.format(bisFact.getCheckDate()));
		}
		voFact.setCreateDate(DateParser.defaultDateFormatter.format(bisFact.getCreatedDate()));
		voFact.setRemark(bisFact.getRemark());

		voFact.setBisFactId(bisFact.getBisFactId());
		voFact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisFact.getChargeTypeCd()));
		voFact.setBisMultiId(bisMulti.getBisMultiId());
		// 经营项目
		voFact.setOperationProjectCd(bisMulti.getOperationProjectCd());
		// 承租方renterName
		voFact.setRenterName(bisMulti.getRenterName());
		voFact.setFactYear(bisFact.getFactYear());
		voFact.setFactMonth(bisFact.getFactMonth());
		if (bisFact.getTotalMustMoney() != null) {
			voFact.setMustMoney(bisFact.getTotalMustMoney());
		}
		if (bisFact.getMoney() != null) {
			voFact.setMoney(bisFact.getMoney());
		}
		voFact.setStatusCd(bisFact.getStatusCd());
		voFact.setStatusCdName(DictMapUtil.getMapBisFactStatus(bisFact.getStatusCd()));
		// 收款时间
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		if (null != bisFact.getFactDate()) {
			voFact.setFactDate(format.format(bisFact.getFactDate()));
		}

		processVoFactCont(voPage);
	}

	public String editForMulti() {
		return "edit";
	}

	public void prepareSearchMustForFlat() {
		voFact = new VoFact();
	}

	/**
	 * 公寓维度：搜索收费明细【应收明细、欠费明细、收费历史记录维度搜索都从这里入口】
	 */
	public String searchMustForFlat() {
		pageSet();
		voFact.setFlatNo(voFact.getBisFlatId());
		voFact.setBisFlatId("");
		// JsonUtil2.renderJson(bisFactManager.searchFactForFlat(page,voFact),
		// new String[] {});
		if (voFact.getMustOrFact() == 1) {
			voPage = bisFactManager.searchFactForFlat(page, voFact);
			voPage.orderBy("buildingNum");
			return "flat";
		} else {
			voPage = bisFactManager.searchMustForFlat(page, voFact);
			return "flat-must";
		}
	}

	public void prepareSearchForMulti() {
		voFact = new VoFact();
	}

	/**
	 * 多经维度：搜索收费明细【应收明细、欠费明细、收费历史记录维度搜索都从这里入口】
	 */
	public String searchForMulti() {
		pageSet();
		// JsonUtil2.renderJson(bisFactManager.searchFactForMulti(page,voFact),
		// new String[] {});
		if (StringUtils.isNotBlank(voFact.getBisContId())) {
			voFact.setBisMultiId("");
		}
		if (voFact.getMustOrFact() == 1) {
			voPage = bisFactManager.searchFactForMulti(page, voFact);
			return "multi";
		} else {
			voPage = bisFactManager.searchMustForMulti(page, voFact);
			return "multi-must";
		}
	}

	/**
	 * Description 处理搜索租户结果
	 */
	public int processTenantId() {
		int tenantIdNum = 0;
		StringBuffer tenantIds = new StringBuffer();
		List<ShopStoreVo> tenants1 = null;
		if (bisFactManager.notBlank(voFact.getBisTenantId())) {

			tenants1 = bisTenantManager.getTenants(bisProjectId, voFact.getBisTenantId(), "");

			List<ShopStoreVo> tenants2 = bisTenantManager.getTenants(bisProjectId, "", voFact.getBisTenantId());
			if (tenants1 != null && tenants2 != null) {
				tenants1.addAll(tenants2);
			}

			int i = 0;
			/*
			 * if (tenants1.size() == 1) { voFact.setBisTenantIds("");
			 * voFact.setBisTenantId(tenants1.get(0).getBisTenantId()); } else
			 * if (tenants1.size() > 1) {
			 */

			for (ShopStoreVo tenant : tenants1) {
				if ("".equals(tenantIds.toString())) {

					tenantIds.append(tenant.getBisTenantId());
				} else {
					tenantIds.append(",").append(tenant.getBisTenantId());
				}
				i++;
				if (i > 120) {
					break;
				}
			}
			voFact.setBisTenantIds(tenantIds.toString());
			voFact.setBisTenantId("");
			// }
			tenantIdNum = tenants1.size();
		} else {
			tenants1 = bisTenantManager.getTenants(bisProjectId);
			voFact.setBisTenantIds("");
			voFact.setBisTenantId("");
		}
		return tenantIdNum;
	}

	public void prepareSearchMustForStore() {
		pageSet();
	}

	/**
	 * 商铺维度：查找费用明细【应收明细、欠费明细、收费历史记录维度搜索都从这里入口】
	 */
	public String searchMustForStore() {
		int tenantIdNums = processTenantId();
		// bisFactManager.searchBisMustPage(page, voFact);
		if (tenantIdNums > 120 && voPage.getResult().size() > 0) {
			Struts2Utils.renderText("<span style='display:none' >error</span>");
		}
		if (null != ifFromReport && "true".equalsIgnoreCase(ifFromReport)) {
			voFact.setBisTenantId(Struts2Utils.getRequest().getParameter("bisTenantId"));
			voFact.setBisTenantIds(Struts2Utils.getRequest().getParameter("bisTenantId"));
		}
		//增加了两个权责年月查询范围的时间字段，并set到view对象中
		if(null!=reportDateStart){
			voFact.setReportDateStart(reportDateStart);
		}
		if(null!=reportDateEnd){
			voFact.setReportDateEnd(reportDateEnd);
		}

		if (voFact.getMustOrFact() == 1) {
			// 实收列表
			voPage = bisFactManager.searchFactForStore(page, voFact, "");
			return "store";
		} else if (voFact.getMustOrFact() == 2) {
			// 应收列表
			voPage = bisFactManager.searMustForStore(page, voFact, "");
			return "store-must";
		} else if (voFact.getMustOrFact() == 8) {
			voFact.setStoreNo(getShopStoreName());
			/**此处需要先进行实例化，在manager里面才能正确进行操作，否则报null错误*/
			totalCountMap = new HashMap<String, String>();
			voPage = bisFactManager.searchActIncomeForStore(page, voFact, "");
			return "store-act";
		} else {
			// 应收列表
			voPage = bisFactManager.searchOwe(page, voFact);
			return "store-must";
		}
	}
	/***
	 * 获得合计数据
	 * @return
	 */
	public String totalCount(){
		voFact = new VoFact();
		voFact.setBisProjectId(Struts2Utils.getParameter("bisProjectId"));
		voFact.setStoreNo(Struts2Utils.getParameter("shopStoreName"));
		Map<String, String> totalCount = bisFactManager.getTotalCount(voFact);
		Struts2Utils.renderJson(totalCount);
		return null;
	}
	/***
	 * 商铺类型
	 * for前台的code2Name
	 * @return
	 */
	public Map<String ,String> getMapStoreType() {
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("1","主力店");
		map.put("2","次主力店");
		map.put("3","小商铺");
		return map ;
	}
	/***
	 * 交易类型
	 * for前台的code2Name
	 * @return
	 */
	public Map<String,String> getMapChargeType(){
		return DictMapUtil.getMapChargeType();
	}
	/**
	 * 预收明细
	 */
	public String inputYuShou() {
		return "yushou-input";
	}

	private BisFactYuS getBisFactYuS(String id) {
		if (StringUtils.isNotBlank(id)) {
			bisFactYuS = bisFactYuSManager.getEntity(id);
		} else {
			bisFactYuS = new BisFactYuS();
		}
		return bisFactYuS;
	}

	public void prepareSaveYuShou() {
		getBisFactYuS(getId());
	}

	/**
	 * 保存预收金额
	 */
	public String saveYuShou() {
		bisFactYuSManager.saveBisFactYuS(bisFactYuS);
		return null;

	}

	public String getShopName(String tenantId) {
		return bisShopManager.getShopName(tenantId);
	}

	/**
	 * 导出实收
	 */
	public String exportFact() throws UnsupportedEncodingException {
		pageSet();
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		excelFile = buildTemplateExcel();
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	public void prepareExportFactResult() {
		pageSet();

	}

	/**
	 * 导出实收搜索
	 */
	public String exportFactResult() throws UnsupportedEncodingException {
		String templateName = "";
		Map resultMap = new HashMap();
		voFact.setNoPage(true);
		page.setPageSize(99999999);
		if (MODULE_STORE.equals(module)) {
			processTenantId();
			voPage = bisFactManager.searchFactForStore(page, voFact, "export");
			templateName = "factResultShore.xls";
		} else if (MODULE_FLAT.equals(module)) {
			voFact.setFlatNo(voFact.getBisFlatId());
			voFact.setBisFlatId("");
			voPage = bisFactManager.searchFactForFlat(page, voFact);
			templateName = "factResultFlat.xls";

		} else if (MODULE_MULTI.equals(module)) {
			voPage = bisFactManager.searchFactForMulti(page, voFact);
			templateName = "factResultMulti.xls";
		}

		resultMap.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		if (voPage != null && !voPage.getResult().isEmpty()) {
			resultMap.put("result", voPage.getResult());
		} else {
			List<VoFact> result = new ArrayList<VoFact>();
			result.add(new VoFact());
			resultMap.put("result", result);
		}

		try {
			excelFile = JXLExcelUtil.initJxlsInputStream(resultMap, templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileName = "收费历史记录-" + DateOperator.formatDate(new Date(), "MMddHHmm");
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	public void prepareExportMustResult() {
		pageSet();

	}

	/**
	 * 导出应收搜索
	 */
	public String exportMustResult() throws UnsupportedEncodingException {
		String templateName = "";
		Map resultMap = new HashMap();
		voFact.setNoPage(true);
		page.setPageSize(99999999);
		if (MODULE_STORE.equals(module)) {
			processTenantId();
			voPage = bisFactManager.searMustForStore(page, voFact, "export");
			templateName = "mustResultShore.xls";
		}

		resultMap.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		if (voPage != null && !voPage.getResult().isEmpty()) {
			resultMap.put("result", voPage.getResult());
		} else {
			List<VoFact> result = new ArrayList<VoFact>();
			result.add(new VoFact());
			resultMap.put("result", result);
		}

		try {
			excelFile = JXLExcelUtil.initJxlsInputStream(resultMap, templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileName = "应收记录-" + DateOperator.formatDate(new Date(), "MMddHHmm");
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 导出缴费通知单
	 */
	public String exportPayNoti() throws UnsupportedEncodingException {
		pageSet();
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		excelFile = buildTemplateExcel();
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");

		return "printData4Fact";

	}

	/**
	 * 导出批量实收excle文件
	 */
	private InputStream buildTemplateExcel() {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {
			try {
				printData4Fact(wbook);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("导出实收excel文件失败！");
			} finally {
				JXLExcelUtil.closeWorkBook(wbook);
			}
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}

		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}

	/**
	 * 打印实收数据至工作薄
	 */
	private void printData4Fact(WritableWorkbook wbook) throws Exception {
		if (voFact.getContLayOutCd() == null) {
			voFact.setContLayOutCd(Struts2Utils.getParameter("floorType"));
		}

		String floorTypeName = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE, voFact.getContLayOutCd());
		excelFileName = "实收导入-" + bisProjectName + "-" + floorTypeName + "(" + factYear + "-" + factMonth + ")";

		WritableSheet wsheet0 = wbook.createSheet(floorTypeName, 0);
		wsheet0.getSettings().setHorizontalFreeze(7);
		wsheet0.getSettings().setVerticalFreeze(3);
		wsheet0.getSettings().setProtected(true);
		if (DictContants.BIS_CONT_TYPE_STORE.equals(voFact.getContLayOutCd())) {
			initFactSheet4Store(wsheet0);
		} else if (DictContants.BIS_CONT_TYPE_FLAT.equals(voFact.getContLayOutCd())) {
			initFactSheet4Flat(wsheet0);
		}
	}

	/**
	 * 为商铺的实收历史记录加载数据至sheet
	 */
	private void initFactSheet4Store(WritableSheet wsheet) throws RowsExceededException, WriteException {
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		wsheet.addCell(new Label(1, 0, bisProjectName + "(" + voFact.getFactYear() + "-" + voFact.getFactMonth() + ")", format_head1));

		wsheet.mergeCells(1, 0, 6, 0);
		// wsheet.mergeCells(1, 0, 3, 0);
		// wsheet.mergeCells(0, 1, 1, 2);
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.mergeCells(5, 1, 5, 2);
		wsheet.mergeCells(6, 1, 6, 2);
		wsheet.mergeCells(7, 1, 7, 2);
		wsheet.mergeCells(8, 1, 8, 2);
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.addCell(new Label(1, 1, "商家名称", format_head2));
		wsheet.addCell(new Label(2, 1, "合同编号", format_head2));
		wsheet.addCell(new Label(3, 1, "合同类别", format_head2));
		wsheet.addCell(new Label(4, 1, "费用类别", format_head2));
		wsheet.addCell(new Label(5, 1, "商铺", format_head2));
		wsheet.addCell(new Label(6, 1, "应收金额", format_head2));
		wsheet.addCell(new Label(7, 1, "实收金额", format_head2));
		wsheet.addCell(new Label(8, 1, "实收日期(yyyy-MM-dd)", format_head2));
		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 15);
		wsheet.setColumnView(2, 15);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 15);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 15);
		wsheet.setColumnView(8, 20);

		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);

		voFact.setStatusCd("e");
		page.setPageSize(5000);
		Page<VoFact> tempPage = bisFactManager.searchFactForStore(page, voFact, "export");
		StringBuffer tmpId;
		for (int i = 0; i < tempPage.getResult().size() - 1; i++) {
			VoFact obj = tempPage.getResult().get(i);
			// bisTenantId,bisContId,chargeTypeCd,factYear,factMonth,1
			tmpId = new StringBuffer();
			tmpId.append(obj.getBisTenantId()).append(",").append(obj.getBisContId()).append(",").append(obj.getChargeTypeCd()).append(",").append(
					obj.getFactYear()).append(",").append(obj.getFactMonth()).append(",").append(obj.getBisProjectId()).append(",").append("1");
			wsheet.addCell(new Label(0, i + 3, tmpId.toString(), format_label));
			wsheet.addCell(new Label(1, i + 3, obj.getConnName(), format_label));
			wsheet.addCell(new Label(2, i + 3, obj.getContNo(), format_label));
			wsheet.addCell(new Label(3, i + 3, appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, obj.getContBigTypeCd()), format_label));
			wsheet.addCell(new Label(4, i + 3, obj.getChargeTypeCdName(), format_label));
			wsheet.addCell(new Label(5, i + 3, obj.getStoreNo(), format_label));
			if (obj.getMustMoney() != null) {
				wsheet.addCell(new Label(6, i + 3, obj.getMustMoney().toString(), format_label));
			} else {
				wsheet.addCell(new Label(6, i + 3, "", format_label));
			}
			wsheet.addCell(new Label(7, i + 3, "", format_unlock));
			wsheet.addCell(new Label(8, i + 3, "", format_unlock_date));
		}
	}

	/**
	 * 加载缴费通知数据至sheet
	 */
	private void initPayNotiSheet(WritableSheet wsheet) throws RowsExceededException, WriteException {
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		wsheet.addCell(new Label(1, 0, bisProjectName + "(" + voFact.getFactYear() + "-" + voFact.getFactMonth() + ")", format_head1));

		wsheet.mergeCells(1, 0, 6, 0);
		// wsheet.mergeCells(1, 0, 3, 0);
		// wsheet.mergeCells(0, 1, 1, 2);
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.mergeCells(5, 1, 5, 2);
		wsheet.mergeCells(6, 1, 6, 2);
		wsheet.mergeCells(7, 1, 7, 2);
		wsheet.mergeCells(8, 1, 8, 2);
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.addCell(new Label(1, 1, "商家名称", format_head2));
		wsheet.addCell(new Label(2, 1, "合同编号", format_head2));
		wsheet.addCell(new Label(3, 1, "合同类别", format_head2));
		wsheet.addCell(new Label(4, 1, "费用类别", format_head2));
		wsheet.addCell(new Label(5, 1, "商铺", format_head2));
		wsheet.addCell(new Label(6, 1, "应收金额", format_head2));
		wsheet.addCell(new Label(7, 1, "实收金额", format_head2));
		wsheet.addCell(new Label(8, 1, "实收日期", format_head2));

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 15);
		wsheet.setColumnView(2, 15);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 15);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 10);

		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);

		voFact.setStatusCd("e");
		page.setPageSize(5000);
		Page<VoFact> tempPage = bisFactManager.searchFactForStore(page, voFact, "export");
		StringBuffer tmpId;
		for (int i = 0; i < tempPage.getResult().size() - 1; i++) {
			VoFact obj = tempPage.getResult().get(i);
			// bisTenantId,bisContId,chargeTypeCd,factYear,factMonth,1
			tmpId = new StringBuffer();
			tmpId.append(obj.getBisTenantId()).append(",").append(obj.getBisContId()).append(",").append(obj.getChargeTypeCd()).append(",").append(
					obj.getFactYear()).append(",").append(obj.getFactMonth()).append(",").append(obj.getBisProjectId()).append(",").append("1");
			wsheet.addCell(new Label(0, i + 3, tmpId.toString(), format_label));
			wsheet.addCell(new Label(1, i + 3, obj.getConnName(), format_label));
			wsheet.addCell(new Label(2, i + 3, obj.getContNo(), format_label));
			wsheet.addCell(new Label(3, i + 3, appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, obj.getContBigTypeCd()), format_label));
			wsheet.addCell(new Label(4, i + 3, obj.getChargeTypeCdName(), format_label));
			wsheet.addCell(new Label(5, i + 3, obj.getStoreNo(), format_label));
			if (obj.getMustMoney() != null) {
				wsheet.addCell(new Label(6, i + 3, obj.getMustMoney().toString(), format_label));
			} else {
				wsheet.addCell(new Label(6, i + 3, "", format_label));
			}
			wsheet.addCell(new Label(7, i + 3, "", format_unlock));
			wsheet.addCell(new Label(8, i + 3, "", format_unlock_date));
		}
	}

	private void initFactSheet4Flat(WritableSheet wsheet) throws RowsExceededException, WriteException {
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		wsheet.addCell(new Label(1, 0, bisProjectName + "(" + voFact.getFactYear() + "-" + voFact.getFactMonth() + ")", format_head1));

		wsheet.mergeCells(1, 0, 6, 0);
		// wsheet.mergeCells(1, 0, 3, 0);
		// wsheet.mergeCells(0, 1, 1, 2);
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.mergeCells(5, 1, 5, 2);
		wsheet.mergeCells(6, 1, 6, 2);
		wsheet.mergeCells(7, 1, 7, 2);
		wsheet.mergeCells(8, 1, 8, 2);
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.addCell(new Label(1, 1, "公寓编号", format_head2));
		wsheet.addCell(new Label(2, 1, "合同编号", format_head2));
		wsheet.addCell(new Label(3, 1, "合同类别", format_head2));
		wsheet.addCell(new Label(4, 1, "费用类别", format_head2));
		wsheet.addCell(new Label(5, 1, "商铺", format_head2));
		wsheet.addCell(new Label(6, 1, "应收金额", format_head2));
		wsheet.addCell(new Label(7, 1, "实收金额", format_head2));
		wsheet.addCell(new Label(8, 1, "实收日期", format_head2));

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 15);
		wsheet.setColumnView(2, 15);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 15);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 10);

		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);

		Page<VoFact> tempPage = bisFactManager.searchFactForStore(page, voFact, "export");
		StringBuffer tmpId;
		for (int i = 0; i < tempPage.getResult().size() - 1; i++) {
			VoFact obj = tempPage.getResult().get(i);
			// bisTenantId,bisContId,chargeTypeCd,factYear,factMonth,1
			tmpId = new StringBuffer();
			tmpId.append(obj.getBisTenantId()).append(",").append(obj.getBisContId()).append(",").append(obj.getChargeTypeCd()).append(",").append(
					obj.getFactYear()).append(",").append(obj.getFactMonth()).append(",").append("1");
			wsheet.addCell(new Label(0, i + 3, tmpId.toString(), format_label));
			wsheet.addCell(new Label(1, i + 3, obj.getConnName(), format_label));
			wsheet.addCell(new Label(2, i + 3, obj.getContNo(), format_label));
			wsheet.addCell(new Label(3, i + 3, appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, obj.getContBigTypeCd()), format_label));
			wsheet.addCell(new Label(4, i + 3, obj.getChargeTypeCdName(), format_label));
			wsheet.addCell(new Label(5, i + 3, obj.getStoreNo(), format_label));
			if (obj.getMustMoney() != null) {
				wsheet.addCell(new Label(6, i + 3, obj.getMustMoney().toString(), format_label));
			} else {
				wsheet.addCell(new Label(6, i + 3, "", format_label));
			}
			wsheet.addCell(new Label(7, i + 3, "", format_unlock));
			wsheet.addCell(new Label(8, i + 3, "", format_unlock_date));
		}
	}

	/**
	 * 处理请求参数 所用费用明细搜索条件
	 */
	private void pageSet() {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} else if (NO_PAGE_SIZE) {
			page.setPageSize(Integer.valueOf("30"));

		}
		page.setTotalCount(0);
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("buildingNum,flatNo,sequenceNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}

		voFact = new VoFact();
		String tmp = Struts2Utils.getParameter("bisFactId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisFactId(tmp);
		}
		tmp = Struts2Utils.getParameter("shopStoreName");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisTenantId(tmp.trim());
		}
		tmp = Struts2Utils.getParameter("bisTenantId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisTenantId(tmp.trim());
		}
		tmp = Struts2Utils.getParameter("bisFlatId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisFlatId(tmp);
		}
		tmp = Struts2Utils.getParameter("bisMultiId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisMultiId(tmp);
		}
		tmp = Struts2Utils.getParameter("bisContId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisContId(tmp);
		}
		tmp = Struts2Utils.getParameter("bisProjectId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisProjectId(tmp);
		}
		tmp = Struts2Utils.getParameter("factYear");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setFactYear(tmp);
		}
		tmp = Struts2Utils.getParameter("factMonth");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setFactMonth(tmp);
		}
		tmp = Struts2Utils.getParameter("reportDateStart");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setReportDateStart(tmp);
		}
		tmp = Struts2Utils.getParameter("reportDateEnd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setReportDateEnd(tmp);
		}
		tmp = Struts2Utils.getParameter("chargeTypeCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setChargeTypeCd(tmp);
		}
		tmp = Struts2Utils.getParameter("contLayOutCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setContLayOutCd(tmp);
		}
		tmp = Struts2Utils.getParameter("creator");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setCreator(tmp);
		}
		tmp = Struts2Utils.getParameter("statusCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setStatusCd(tmp);
		}
		tmp = Struts2Utils.getParameter("minMonth");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setMinMonth(tmp);
		}
		tmp = Struts2Utils.getParameter("maxMonth");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setMaxMonth(tmp);
		}
		tmp = Struts2Utils.getParameter("shopTypeCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setShopTypeCd(tmp);
		}
		// 楼号
		tmp = Struts2Utils.getParameter("buildingNum");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBuildingNum(tmp);
		}
		String factDate = Struts2Utils.getParameter("factDate");
		if (factDate != null && bisFactManager.notBlank(factDate)) {
			voFact.setFactDate(factDate);
		}
		String minMoney = Struts2Utils.getParameter("minMoney");
		if (bisFactManager.notBlank(minMoney)) {
			voFact.setMinMoney(new BigDecimal(minMoney));
		}
		String mustMoney = Struts2Utils.getParameter("mustMoney");
		Log.debug("=============mustMoney========"+mustMoney);
		if (bisFactManager.notBlank(mustMoney)) {
			voFact.setMustMoney(new BigDecimal(mustMoney));
		}
		String maxMoney = Struts2Utils.getParameter("maxMoney");
		if (bisFactManager.notBlank(maxMoney)) {
			voFact.setMaxMoney(new BigDecimal(maxMoney));
		}
		String money = Struts2Utils.getParameter("money");
		if (bisFactManager.notBlank(money)) {
			voFact.setMoney(new BigDecimal(money));
		}
		// 应收 实收 区分
		String mustOrFact = Struts2Utils.getParameter("mustOrFact");
		if (bisFactManager.notBlank(mustOrFact)) {
			voFact.setMustOrFact(Integer.valueOf(mustOrFact));
			dimension = mustOrFact;
		} else {
			voFact.setMustOrFact(1);// 默认搜索实收
			dimension = "1";
		}
		// 是否未收齐
		String overdue = Struts2Utils.getParameter("overdue");
		if (bisFactManager.notBlank(overdue)) {
			voFact.setOverdue(overdue);
		}
		String storeId = Struts2Utils.getParameter("storeId");
		if (bisFactManager.notBlank(storeId)) {
			voFact.setBisStoreId(storeId);
		}
		if (voFact.getMustOrFact() == 1) {

			// 实收日期
			String factInBegin = Struts2Utils.getParameter("factInBegin");
			if (bisFactManager.notBlank(factInBegin)) {
				voFact.setInDateBegin(factInBegin);
			}
			String factInEnd = Struts2Utils.getParameter("factInEnd");
			if (bisFactManager.notBlank(factInEnd)) {
				voFact.setInDateEnd(factInEnd);
			}
		} else {
			// 应收日期
			String mustInBegin = Struts2Utils.getParameter("mustInBegin");
			if (bisFactManager.notBlank(mustInBegin)) {
				voFact.setInDateBegin(mustInBegin);
			}
			String mustInEnd = Struts2Utils.getParameter("mustInEnd");
			if (bisFactManager.notBlank(mustInEnd)) {
				voFact.setInDateEnd(mustInEnd);
			}
		}
		// 实收维度审核人
		String checkUserCd = Struts2Utils.getParameter("checkUserCd");
		if (bisFactManager.notBlank(checkUserCd)) {
			voFact.setCheckUserCd(checkUserCd);
		}
		// 公寓楼栋名称，又称楼号
		/*
		 * String buildingNum = Struts2Utils.getParameter("buildingNum"); if
		 * (bisFactManager.notBlank(buildingNum)) {
		 * voFact.setBuildingNum(buildingNum); }
		 */
		String bisContId = Struts2Utils.getParameter("bisContId");
		if (bisFactManager.notBlank(bisContId)) {
			voFact.setBisContId(bisContId);
		}

		if (StringUtils.isBlank(voFact.getBisTenantIds())) {
			voFact.setBisTenantIds(voFact.getBisTenantId());
		}
	}

	public void searchTenant() {
		List<BisTenant> tenants = bisTenantManager.getBisTenantList(getBisProjectId());
		List<VoCombo> vos = new ArrayList<VoCombo>();
		VoCombo tmpVO = null;
		for (BisTenant o : tenants) {
			tmpVO = new VoCombo();
			tmpVO.setId(o.getBisTenantId());
			tmpVO.setText(o.getConnName());
			vos.add(tmpVO);
		}
		Struts2Utils.renderJson(vos);
	}

	public void searchFlat() {
		List<BisFlat> flats = bisFlatManager.getBisFlatList(bisProjectId, "");
		List<VoCombo> vos = new ArrayList<VoCombo>();
		VoCombo tmpVO = null;
		for (BisFlat o : flats) {
			tmpVO = new VoCombo();
			tmpVO.setId(o.getBisFlatId());
			String tempStr = "";
			if (null != o.getBisFloor().getBuildingNum()) {
				tempStr += o.getBisFloor().getBuildingNum();
			}
			if (null != o.getBisFloor().getFloorNum()) {
				tempStr += o.getBisFloor().getFloorNum() + "层 ";
			}
			if (null != o.getFlatNo()) {
				tempStr += o.getFlatNo() + "号";
			}
			tmpVO.setText(tempStr);
			vos.add(tmpVO);
		}
		Struts2Utils.renderJson(vos);
	}

	/**
	 * 搜索某项目下的公寓名称
	 */
	public void searchFlatNo() {
		List<BisFloor> floors = bisFlatManager.getBisFloor(bisProjectId);
		List<VoCombo> vos = new ArrayList<VoCombo>();
		VoCombo tmpVO = null;
		for (BisFloor f : floors) {
			tmpVO = new VoCombo();
			tmpVO.setId(f.getBisFloorId());
			String tempStr = "";

			if (null != f.getBuildingNum()) {
				tempStr += f.getBuildingNum();
			}
			if (null != f.getFloorNum()) {
				tempStr += f.getFloorNum() + "层 ";
			}
			tmpVO.setText(tempStr);
			vos.add(tmpVO);
		}
		Struts2Utils.renderJson(vos);
	}

	/**
	 * 新增公寓的选择框记录
	 */
	public void searchFloorNo() {
		// 搜索有楼层ID的数据
		// List<BisFlat> floots = bisFlatManager.getBisFlatList(bisProjectId,
		// floorId);
		// 搜索所有有关合同的公寓编号
		List<BisCont> contList = bisContManager.getBisContList(bisProjectId, floorId);
		// 搜索与合同无关的公寓编号
		List<BisFlat> flatList = bisFlatManager.getFlatNotCont(bisProjectId, floorId);
		List<VoCombo> vos = new ArrayList<VoCombo>();
		VoCombo tmpVO = null;
		StringBuffer flatNo = new StringBuffer("");
		if (contList != null && contList.size() > 0) {
			for (int i = 0; i < contList.size(); i++) {
				BisCont cont = contList.get(i);
				tmpVO = new VoCombo();
				for (int j = 0; j < cont.getBisFlatContRels().size(); j++) {
					BisFlatContRel rel = cont.getBisFlatContRels().get(j);
					BisFlat flat = rel.getBisFlat();
					flatNo.append(flat.getFlatNo() + ",");
					if (j > 0) {
						i++;
					}
				}
				if (flatNo.length() > 1) {
					flatNo.delete(flatNo.length() - 1, flatNo.length());
				}
				tmpVO.setId(cont.getBisContId());
				tmpVO.setText(cont.getContNo());
				tmpVO.setTextAttach(flatNo.toString());
				tmpVO.setKey("cont");
				vos.add(tmpVO);
				flatNo.delete(0, flatNo.length());

			}
		}
		for (BisFlat fl : flatList) {
			tmpVO = new VoCombo();
			tmpVO.setId(fl.getBisFlatId());
			String tempStr = "";
			if (null != fl.getFlatNo()) {
				tempStr += fl.getFlatNo() + "号";
			}
			tmpVO.setText(tempStr);
			tmpVO.setTextAttach("");
			tmpVO.setKey("flat");
			vos.add(tmpVO);
		}
		Struts2Utils.renderJson(vos);
	}

	/**
	 * 新增多经的选择框记录
	 */
	public void searchMulti() {
		// List<BisMulti> multis =
		// bisMultiManager.getBisMultiList(bisProjectId);
		// 先搜索出有合同的多经
		List<BisCont> contList = bisContManager.getContListByMulti(bisProjectId);
		// 索索出无合同的多经
		List<BisMulti> multiList = bisMultiManager.getBisMultiList(bisProjectId);
		List<VoCombo> vos = new ArrayList<VoCombo>();
		VoCombo tmpVO = null;
		for (BisCont cont : contList) {
			tmpVO = new VoCombo();
			tmpVO.setId(cont.getBisContId());
			tmpVO.setText(cont.getContNo());
			tmpVO.setKey("cont");
			vos.add(tmpVO);
		}
		for (BisMulti o : multiList) {
			tmpVO = new VoCombo();
			tmpVO.setId(o.getBisMultiId());
			tmpVO.setText(o.getMultiName());
			tmpVO.setKey("mul");
			vos.add(tmpVO);
		}
		Struts2Utils.renderJson(vos);
	}

	/**
	 * 录入商铺实收记录:必须有合同、应收等
	 * ------------------------------
	 * 修改人：qilb 4/28/2012
	 * 增加应收参数
	 * @throws Exception
	 */
	public void prepareEntering2Store() throws Exception {
		//应收
		String totalMustMoney=Struts2Utils.getParameter("totalMustMoney");
		if (StringUtils.isNotBlank(bisProjectId)) {
			bisProjectId = bisProjectId.replace(",", "").trim();
		}

		if (StringUtils.isNotBlank(chargeTypeCd)) {
			chargeTypeCd = chargeTypeCd.replace(",", "").trim();
		}
		if (StringUtils.isNotBlank(factYear)) {
			factYear = factYear.replace(",", "").trim();
		}
		if (StringUtils.isNotBlank(factMonth)) {
			factMonth = factMonth.replace(",", "").trim();
		}
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFactManager.getEntity(getId());
		} else {
			entity = new BisFact();
			// entity.setFactDate(DateUtil.getCurrtDate());
			entity.setStatusCd(DictContants.BIS_FACT_NO_CHECK);
			if (StringUtils.isNotBlank(Struts2Utils.getParameter("bisContId"))) {
				entity.setBisContId(Struts2Utils.getParameter("bisContId").replace(",", "").trim());
			}

		}
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("remark"))) {
			entity.setRemark(Struts2Utils.getParameter("remark").trim());
		}
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("bisContId"))) {
			entity.setBisContId(Struts2Utils.getParameter("bisContId").trim());
		}
		//设置应收
		if(StringUtils.isNotBlank(totalMustMoney)){		
			entity.setTotalMustMoney(new BigDecimal(totalMustMoney));
		}
		
	}

	public void entering2Store() {
		String bisTenantId = Struts2Utils.getParameter("layOutCdList");
		if (StringUtils.isNotBlank(bisTenantId)) {
			entity.setBisTenantId(bisTenantId);
			/*
			 * if(StringUtils.isBlank(entity.getBisShopId())){ BisTenant
			 * bisTenant =bisTenantManager.getEntity(bisTenantId);
			 * entity.setBisShopId(bisTenant.getBisShopId()); }
			 */
		}
		// 将页面选择的租户名称保存至商铺编号
		String bisStore = Struts2Utils.getParameter("bisStore");
		if (StringUtils.isNotBlank(bisStore)) {
			String[] tmpbisStores = bisStore.split("-");
			bisStore = "";
			for (int i = 1; i < tmpbisStores.length; i++) {
				bisStore += tmpbisStores[i];
			}
			entity.setBisStoreId(bisStore);
		}
		// 搜索实收对应的应收记录
		if (StringUtils.isNotBlank(entity.getFactYear()) && StringUtils.isNotBlank(entity.getFactMonth()) && StringUtils.isNotBlank(entity.getBisContId())) {
			BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), null, null, entity.getChargeTypeCd(), entity.getFactYear(), entity
					.getFactMonth());
			if (bisMust != null) {
				entity.setTotalMustMoney(bisMust.getMoney());
				bisProjectId = bisMust.getBisProjectId();
				List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, bisTenantId, null, null, entity.getChargeTypeCd(), entity.getFactYear(),
						entity.getFactMonth(), entity.getBisContId());
				BigDecimal totFactMoney = new BigDecimal(0);
				for (BisFact fact : bisFactList) {
					if (null != fact.getMoney()) {
						totFactMoney = totFactMoney.add(fact.getMoney());
					}
				}
				if (null != entity.getMoney()) {
					totFactMoney = totFactMoney.add(entity.getMoney());
				}
				bisMust.setTotalFactMoney(totFactMoney);
				bisMustManager.save(bisMust);
			}
			// 如果实收记录已审核，不允许修改
			bisFactManager.factInput2Store(SpringSecurityUtils.getCurrentUiid(), entity);
		}
		Struts2Utils.renderText("success");
	}

	public void checkContForStore() {
		// 检查合同是否冲突
		// 冲突，返回选择框，待用户指定合同
		// 不冲突，返回success
		VoFactOpera result = bisFactManager.factCheckForStore(bisProjectId, bisTenantId, chargeTypeCd, factYear, factMonth);
		if (VoFactOpera.BIS_CONT_CONFLICT_05 == result.getType() || VoFactOpera.BIS_CONT_CONFLICT_04 == result.getType()) {
			Struts2Utils.renderJson(result.getDesc());
		} else {
			Struts2Utils.renderText("success");
		}
	}

	public void prepareCheckContForFlat() throws Exception {
		prepareModel();
	}

	public void checkContForFlat() {

		// 检查合同是否冲突
		// 冲突，返回选择框，待用户指定合同
		// 不冲突，返回success
		VoFactOpera result = bisFactManager.factCheckForFlat(bisProjectId, entity.getBisFlatId(), chargeTypeCd, factYear, factMonth);
		if (VoFactOpera.BIS_CONT_CONFLICT_05 == result.getType() || VoFactOpera.BIS_CONT_CONFLICT_04 == result.getType()) {
			Struts2Utils.renderJson(result.getDesc());
		} else {
			Struts2Utils.renderText("success");
		}
	}

	public void prepareCheckContForMulti() throws Exception {
		prepareModel();
	}

	public void checkContForMulti() {

		// 检查合同是否冲突
		// 冲突，返回选择框，待用户指定合同
		// 不冲突，返回success
		VoFactOpera result = bisFactManager.factCheckForMulti(bisProjectId, entity.getBisMultiId(), chargeTypeCd, factYear, factMonth);
		if (VoFactOpera.BIS_CONT_CONFLICT_04 == result.getType()) {
			Struts2Utils.renderText("未找到多经合同");
		} else if (VoFactOpera.BIS_CONT_CONFLICT_05 == result.getType()) {
			Struts2Utils.renderJson(result.getDesc());
		} else {
			Struts2Utils.renderText("success");
		}
	}

	/**
	 * 录入公寓实收记录:必须有合同、应收等
	 */
	public void prepareEntering2Flat() throws Exception {
		prepareEntering2Store();
		String bisFlatId = Struts2Utils.getParameter("layOutCdList");
		if (StringUtils.isNotBlank(bisFlatId)) {
			entity.setBisFlatId(bisFlatId);
		}
	}

	public void entering2Flat() {
		// 如果实收记录已审核，不允许修改
		bisFactManager.factInput2Flat(SpringSecurityUtils.getCurrentUiid(), entity);
		// 搜索实收对应的应收记录
		if (StringUtils.isNotBlank(entity.getFactYear()) && StringUtils.isNotBlank(entity.getFactMonth()) && StringUtils.isNotBlank(entity.getChargeTypeCd())
				&& (StringUtils.isNotBlank(entity.getBisFlatId()) || StringUtils.isNotBlank(entity.getBisContId()))) {
			String bisContId=entity.getBisContId();
			String bisFlatId=null;
			//如果合同编号为空，则使用公寓编号
			if(StringUtils.isBlank(bisContId)){				
				bisFlatId=entity.getBisFlatId();
			}
			BisMust bisMust = bisMustManager.getExistsMust(bisContId, bisFlatId, null, entity.getChargeTypeCd(), entity.getFactYear(),
					entity.getFactMonth());
			if (bisMust != null) {
				entity.setTotalMustMoney(bisMust.getMoney());
				bisProjectId = bisMust.getBisProjectId();
				List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, bisTenantId, null, null, entity.getChargeTypeCd(), entity.getFactYear(),
						entity.getFactMonth(), entity.getBisContId());
				BigDecimal totFactMoney = new BigDecimal(0);
				for (BisFact fact : bisFactList) {
					if (null != fact.getMoney()) {
						totFactMoney = totFactMoney.add(fact.getMoney());
					}
				}
				if (null != entity.getMoney()) {
					//因为之前实体的实收已放入到 bisMustManager 搜索已含有新增加的实体
				//	totFactMoney = totFactMoney.add(entity.getMoney());
				}
				bisMust.setTotalFactMoney(totFactMoney);
				bisMustManager.save(bisMust);
				entity.setTotalMustMoney(bisMust.getMoney());
			}
			bisFactManager.saveBisFact(entity);
		}
		Struts2Utils.renderText("success");
	}

	/**
	 * 录入商铺实收记录:必须有合同、应收等
	 */
	public void prepareEntering2Multi() throws Exception {
		prepareEntering2Store();
		String bisMultiId = Struts2Utils.getParameter("layOutCdList");
		if (StringUtils.isNotBlank(bisMultiId)) {
			entity.setBisMultiId(bisMultiId);
		}
	}

    /***
     * 多经保存按钮
     */
    public void entering2Multi() {
        //若有合同ID，则多经ID为空
        if (StringUtils.isNotBlank(entity.getBisContId())) {
            entity.setBisMultiId(null);
        }
        bisFactManager.factInput2Multi(SpringSecurityUtils.getCurrentUiid(), entity);
        //搜索实收对应的应收记录
        if (StringUtils.isNotBlank(entity.getFactYear()) && StringUtils.isNotBlank(entity.getFactMonth())
                && StringUtils.isNotBlank(entity.getChargeTypeCd()) &&
                (StringUtils.isNotBlank(entity.getBisMultiId()) || StringUtils.isNotBlank(entity.getBisContId()))) {
            BisMust bisMust = bisMustManager.getExistsMust(entity.getBisContId(), null, entity.getBisMultiId(), entity.getChargeTypeCd(), entity.getFactYear(), entity.getFactMonth());
            if (bisMust != null) {
                entity.setTotalMustMoney(bisMust.getMoney());
                bisProjectId = bisMust.getBisProjectId();
                List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, bisTenantId, null, null, entity.getChargeTypeCd(), entity.getFactYear(),
                        entity.getFactMonth(), entity.getBisContId());
                BigDecimal totFactMoney = new BigDecimal(0);
                for (BisFact fact : bisFactList) {
                    if (null != fact.getMoney()) {
                        totFactMoney = totFactMoney.add(fact.getMoney());
                    }
                }
                bisMust.setTotalFactMoney(totFactMoney);
                bisMustManager.save(bisMust);
                entity.setTotalMustMoney(bisMust.getMoney());
            }
            bisFactManager.saveBisFact(entity);
        }
        Struts2Utils.renderText("success");
    }
	/**
	 * 审核通过实收记录
	 */
	public void checkPassFact() {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFactManager.getEntity(getId());
			// 校验用户是否有权审核通过TODU
			bisFactManager.factPass(SpringSecurityUtils.getCurrentUiid(), getId());
		}
	}

	/**
	 * Administrator 2012-3-14
	 * 
	 * @return
	 */
	public String toDeduct() {
		entity = bisFactManager.getEntity(getId());
		voFact = new VoFact();
		voFact.setBisFactId(entity.getBisFactId());
		voFact.setBisProjectId(entity.getBisProjectId());
		bisProjectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();
		voFact.setFactMonth(null == entity.getFactMonth() ? "" : entity.getFactMonth());
		voFact.setFactYear(null == entity.getFactYear() ? "" : entity.getFactYear());
		voFact.setChargeTypeCd(entity.getChargeTypeCd());
		voFact.setChargeTypeCdName(DictMapUtil.getMapChargeType(voFact.getChargeTypeCd()));
		voFact.setMoney(entity.getMoney());
		voFact.setBisShopId(entity.getBisShopId());
		voFact.setBisStoreId(entity.getBisStoreId());
		switch (Integer.valueOf(layOutCd)) {
		case 1:
			currDetail = entity.getBisTenantId();
			currDetailName = entity.getBisStoreId();
			break;
		case 2:
			currDetail = entity.getBisFlatId();
			BisFlat fl = bisFlatManager.getEntity(currDetail);
			currDetailName = fl.getBisFloor().getBuildingNum() + " " + fl.getBisFloor().getFloorNum() + " " + fl.getFlatNo();
			break;
		case 3:
			currDetail = entity.getBisMultiId();
			currDetailName = bisMultiManager.getEntity(currDetail).getMultiName();
			break;
		}
		return "deduct";
	}

	public void prepareDeductMoney() {
		entity = new BisFact();
	}

	/**
	 * 扣除预存款 :废弃
	 */
	public String deductMoney() {
		BisFact parent = bisFactManager.getEntity(getId());
		if (bisFactManager.factDeduct(SpringSecurityUtils.getCurrentUiid(), layOutCd, parent, entity, currDetail)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("error");
		}
		return null;
	}

	/**
	 * 缴费通知单列表
	 */
	public String toPrintPays() {
		PayRequisitionVo vo = new PayRequisitionVo();
		vo.setBisProjectId(bisProjectId);
		if (StringUtils.isNotBlank(nameShop)) {
			vo.setShopName(nameShop);
		}
		if (StringUtils.isNotBlank(storeNo)) {
			vo.setStoreNo(storeNo);
		}
		if (StringUtils.isNotBlank(factYear)) {
			vo.setYear(factYear);
		}
		if (StringUtils.isNotBlank(factMonth)) {
			vo.setMonth(factMonth);
		}
		if (null == pageNo) {
			pageNo = new BigDecimal(1);
			vo.setPageNo(pageNo);
		} else {
			vo.setPageNo(pageNo);
		}
		if (null == pageSize) {
			pageSize = new BigDecimal(10);
			vo.setPageSize(pageSize);
		} else {
			vo.setPageSize(pageSize);
		}
		voPayPage = bisFactManager.searchPayRequisitions(vo);
		page.setPageNo(voPayPage.getPageNo());
		page.setPageSize(pageSize.intValue());
		// page.setPageSize(voPage.getPageSize());
		page.setTotalCount(voPayPage.getTotalCount());
		// 搜索年月缴费单
		return "payList";
	}

	/**
	 * 批量导入实收数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String importDataPoi() throws Exception {

		long begin = new Date().getTime();

		int insert = 0;
		int update = 0;
		int r = 0;
		int c = 0;

		int row = 0;
		int col = 0;

		try {

			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			HSSFRow firstrow = hssfSheet.getRow(0);
			String title = firstrow.getCell(0).getStringCellValue();
			String year = "";
			String month = "";
			try {
				String[] date = title.substring(title.indexOf("(") + 1, title.indexOf(")")).split("-");
				year = date[0];
				month = date[1];
				if (Integer.valueOf(month) < 10) {
					month = date[1].replace("0", "");
				}
			} catch (Exception e) {
				e.printStackTrace();
				String errorLocation = "请使用模板导入实收";
				Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
				return null;
			}
			String user = SpringSecurityUtils.getCurrentUiid();
			Date now = new Date();
			// 实收列表数据：需要新增或修改的数据
			List<BisFact> factList = new ArrayList<BisFact>();
			// 应收列表数据
			List<BisMust> mustList = new ArrayList<BisMust>();
			// 若选择覆盖，则要将多出的实收记录删除
			List<BisFact> delFactList = new ArrayList<BisFact>();

			HSSFRow chargeTypeRow = hssfSheet.getRow(2);

			for (row = 3; row <= hssfSheet.getLastRowNum(); row++) {

				HSSFRow hssfRow = hssfSheet.getRow(row);

				if (hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					continue;
				}
				String[] hiddenIds = hssfRow.getCell(0).getStringCellValue().split(";");
				String floorTypeCd = hiddenIds[0].trim();
				String projectId = hiddenIds[1].trim();

				String floorId = "";
				String bisTenantId = "";
				String bisShopId = "";
				String bisFlatId = "";
				String bisMultiId = "";
				String bisContId = "";

				if ("1".equals(floorTypeCd)) { // 商铺
					floorId = hiddenIds[2].trim();
					bisTenantId = hiddenIds[3].trim();
					bisShopId = hiddenIds[4].trim();
					bisContId = hiddenIds[5].trim();
				} else if ("2".equals(floorTypeCd)) { // 公寓
					floorId = hiddenIds[2].trim();
					if ("0".equals(hiddenIds[4].trim())) {
						bisContId = hiddenIds[3].trim();
					} else {
						bisFlatId = hiddenIds[3].trim();
					}
				} else { // 多经
					if ("0".equals(hiddenIds[3].trim())) {
						bisContId = hiddenIds[2].trim();
					} else {
						bisMultiId = hiddenIds[2].trim();
					}
				}

				StringBuffer hql = null;
				Map<String, Object> param = null;
				// 从第6列取得应收费用数据
				for (col = 6; col < hssfRow.getLastCellNum(); col++) {
					c = col;
					HSSFCell hssfCell = hssfRow.getCell(col);
					if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						BigDecimal factFee = new BigDecimal(hssfCell.getNumericCellValue());
						String chargeTypeCd = chargeTypeRow.getCell(col).getStringCellValue();

						/*
						 * if (factFee == null || factFee.compareTo(zero) == 0)
						 * { continue; }
						 */
						List<BisFact> bisFactList;
						BisFact bisFact = null;
						// 若为1，则新增,否则，查找若有实收的数据，将实收金额覆盖
						if ("1".equals(importFactType)) {
							bisFactList = null;
						} else {
							bisFactList = bisFactManager.getExistsFact(bisContId, bisFlatId, bisMultiId, chargeTypeCd, year, month);

							// 若bisFactList有大于一条数据，则删除
							if (bisFactList != null && bisFactList.size() > 0) {
								bisFact = bisFactList.get(0);
								for (int i = 1; i < bisFactList.size(); i++) {
									delFactList.add(bisFactList.get(i));
								}
							}

						}
						// 如果不存在该应收数据，则新增一条数据
						if (bisFact == null) {
							bisFact = new BisFact();
							insert++;
							bisFact.setBisProjectId(projectId);
							if ("1".equals(floorTypeCd)) { // 商铺
								if (StringUtils.isNotBlank(floorId)) {
									bisFact.setBisFloorId(floorId);
								}
								bisFact.setBisTenantId(bisTenantId);
								bisFact.setBisShopId(bisShopId);
								bisFact.setBisContId(bisContId);
								bisFact.setFloorType("1");
							} else if ("2".equals(floorTypeCd)) { // 公寓
								if (StringUtils.isNotBlank(floorId)) {
									bisFact.setBisFloorId(floorId);
								}
								if (StringUtils.isNotBlank(bisFlatId)) {
									bisFact.setBisFlatId(bisFlatId);
								}
								if (StringUtils.isNotBlank(bisContId)) {
									bisFact.setBisContId(bisContId);
								}
								bisFact.setFloorType("2");
							} else { // 多经
								if (StringUtils.isNotBlank(bisMultiId)) {
									bisFact.setBisMultiId(bisMultiId);
								}
								if (StringUtils.isNotBlank(bisContId)) {
									bisFact.setBisContId(bisContId);
								}
								bisFact.setFloorType("3");
							}
							bisFact.setChargeTypeCd(chargeTypeCd);
							bisFact.setFactYear(year);
							bisFact.setFactMonth(month);
							bisFact.setMoney(factFee);
							bisFact.setStatusCd("1"); // 实收 1：已审核 应收 2：已审核
							if ("1".equals(chargeTypeCd) || "3".equals(chargeTypeCd) || "4".equals(chargeTypeCd)) {
								bisFact.setTypeCd("0");
							} else {
								bisFact.setSequenceNo(new Long(r - 3));
								bisFact.setTypeCd("1");
							}
							bisFact.setCreator(user);
							bisFact.setCreatedDate(now);
							bisFact.setUpdatedDate(now);
						} else {
							// 否则修改应收费用
							if (bisFact.getMoney() == null || bisFact.getMoney().compareTo(factFee) != 0 || !"2".equals(bisFact.getStatusCd())) {
								bisFact.setMoney(factFee);
								bisFact.setStatusCd("1"); // 已审核
								bisFact.setUpdator(user);
								bisFact.setUpdatedDate(now);
								update++;

							}
						}
						// 搜索相应的应收记录，并填充之
						param = new HashMap<String, Object>();
						hql = new StringBuffer("");
						hql.append(" from BisMust a where a.mustYear=:mustYear and a.mustMonth=:mustMonth and a.chargeTypeCd=:chargeTypeCd");
						param.put("mustYear", bisFact.getFactYear());
						param.put("mustMonth", bisFact.getFactMonth());
						param.put("chargeTypeCd", bisFact.getChargeTypeCd());
						if (StringUtils.isNotBlank(bisFact.getBisTenantId())) {
							hql.append(" and a.bisTenantId =:bisTenantId");
							param.put("bisTenantId", bisFact.getBisTenantId());
						}
						if (StringUtils.isNotBlank(bisFact.getBisFlatId())) {
							hql.append(" and a.bisFlatId=:bisFlatId");
							param.put("bisFlatId", bisFact.getBisFlatId());
						}
						if (StringUtils.isNotBlank(bisFact.getBisMultiId())) {
							hql.append(" and a.bisMultiId=:bisMultiId");
							param.put("bisMultiId", bisFact.getBisMultiId());
						}
						if (StringUtils.isNotBlank(bisFact.getBisContId())) {
							hql.append(" and a.bisCont.bisContId=:bisContId");
							param.put("bisContId", bisFact.getBisContId());
						}
						List<BisMust> bisMustList = bisMustManager.find(hql.toString(), param);
						if (bisMustList != null && bisMustList.size() == 1) {
							BisMust bisMust1 = bisMustList.get(0);
							bisFact.setTotalMustMoney(bisMust1.getMoney());
							// 若为1，则新增
							BigDecimal totFactMoney = new BigDecimal(0);
							if ("1".equals(importFactType)) {
								// 搜索应收对应的实收
								List<BisFact> bisFact1List = bisFactManager
										.getFact(bisProjectId, bisTenantId, null, null, chargeTypeCd, year, month, bisContId);
								for (BisFact fact : bisFact1List) {
									totFactMoney = totFactMoney.add(fact.getMoney());
								}
							}
							totFactMoney = totFactMoney.add(factFee);
							bisMust1.setTotalFactMoney(totFactMoney);
							mustList.add(bisMust1);
						}
						// 新增或修改应收日期
						++col;
						++c;
						HSSFCell cellDate = hssfRow.getCell(col);

						if (cellDate.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							Struts2Utils.renderText(",error, 未导入成功，请于第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列填写应收日期,");
							return null;
						} else if (cellDate.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							// collDate =
							// firstrow.getCell(10).getDateCellValue();
							if (StringUtils.isBlank(DateOperator.formatDate(cellDate.getDateCellValue(), "yyyy-MM-dd"))) {
								Struts2Utils.renderText(",error, 第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列，应收日期格式错误(填写如2011-11-01),");
								return null;
							}
						} else {
							Struts2Utils.renderText(",error, 第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列应收日期格式错误(填写如2011-11-01),");
							return null;
						}
						bisFact.setFactDate(cellDate.getDateCellValue());

						factList.add(bisFact);
					} else {
						// 若费用没有数据则跳一格（日期）
						++col;
					}
				}
				r = row;
			}
			// 批量保存
			bisFactManager.batchExecute(factList);
			if (mustList != null && mustList.size() > 0) {
				bisIncomeOtherManager.batchExecute(mustList);
			}
			if (delFactList != null && delFactList.size() > 0) {
				// 批量删除
				bisFactManager.batchDelete(delFactList);
			}

			long end = new Date().getTime();

			Struts2Utils.renderText(",success," + insert + "," + update + "," + (end - begin) / 1000 + ",");
		} catch (Exception e) {
			e.printStackTrace();
			String errorLocation = (r + 1) + "行 " + BisConstants.letters.get(c + 1) + "列";
			Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
		}
		return null;
	}
/**
 *  现金流月实收导入
 *  ---------------
 *  修改人：qilb 4/28/2012
 *  导入数据库中增加审核人及核审时间
 * @return
 * @throws Exception
 */
	public String importCashPoi() throws Exception {

		long begin = new Date().getTime();

		int insert = 0;
		int update = 0;
		int r = 0;
		int c = 0;
		int row = 0;
		int col = 0;

		try {

			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			HSSFRow firstrow = hssfSheet.getRow(0);
			String title = firstrow.getCell(0).getStringCellValue();
			String factDateStr = "";
			try {
				factDateStr = title.substring(title.indexOf("：") + 1, title.indexOf(")"));
				/*
				 * year = date[0]; month = date[1]; if (Integer.valueOf(month) <
				 * 10) { month = date[1].replace("0", ""); }
				 */
			} catch (Exception e) {
				e.printStackTrace();
				String errorLocation = "请使用模板导入实收";
				Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
				return null;
			}
			String user = SpringSecurityUtils.getCurrentUiid();
			Date now = new Date();
			// 实收列表数据：需要新增或修改的数据
			List<BisFact> factList = new ArrayList<BisFact>();
			// 应收列表数据
			List<BisMust> mustList = new ArrayList<BisMust>();
			// 若选择覆盖，则要将多出的实收记录删除
			List<BisFact> delFactList = new ArrayList<BisFact>();

			HSSFRow chargeTypeRow = hssfSheet.getRow(2);
			// 从第四行开始
			for (row = 4; row <= hssfSheet.getLastRowNum(); row++) {

				HSSFRow hssfRow = hssfSheet.getRow(row);

				if (hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					continue;
				}
				String[] hiddenIds = hssfRow.getCell(0).getStringCellValue().split(";");
				String floorTypeCd = hiddenIds[0].trim();
				String projectId = hiddenIds[1].trim();

				String floorId = "";
				String bisTenantId = "";
				String bisShopId = "";
				String bisFlatId = "";
				String bisMultiId = "";
				String bisContId = "";

				if ("1".equals(floorTypeCd)) { // 商铺
					floorId = hiddenIds[2].trim();
					bisTenantId = hiddenIds[3].trim();
					bisShopId = hiddenIds[4].trim();
					bisContId = hiddenIds[5].trim();
				} else if ("2".equals(floorTypeCd)) { // 公寓
					floorId = hiddenIds[2].trim();
					if ("0".equals(hiddenIds[4].trim())) {
						bisContId = hiddenIds[3].trim();
					} else {
						bisFlatId = hiddenIds[3].trim();
					}
				} else { // 多经
					if ("0".equals(hiddenIds[3].trim())) {
						bisContId = hiddenIds[2].trim();
					} else {
						bisMultiId = hiddenIds[2].trim();
					}
				}

				StringBuffer hql = null;
				Map<String, Object> param = null;
				// 从第6列取得应收费用数据和权责日期
				for (col = 6; col < hssfRow.getLastCellNum(); col++) {
					c = col;
					HSSFCell hssfCell = hssfRow.getCell(col);
					if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						BigDecimal factFee = new BigDecimal(hssfCell.getNumericCellValue());
						String chargeTypeCd = chargeTypeRow.getCell(col).getStringCellValue();
						List<BisFact> bisFactList;
						BisFact bisFact = null;
						// 取得权责年、月
						HSSFCell cellDate = hssfRow.getCell(col + 1);
						String dateStr = cellDate.toString();
						if (dateStr.length() != 7) {
							Struts2Utils.renderText(",error, 第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列，权责日期格式错误(填写如2011-11),");
							return null;
						}
						String year = dateStr.substring(0, 4);
						String month = dateStr.substring(5, 7);
						try {
							Integer.parseInt(year);
							if ("0".equals(month.substring(0, 1))) {
								month = month.substring(1, 2);
							}
							Integer.parseInt(month);
						} catch (Exception e) {
							Struts2Utils.renderText(",error, 第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列，应收日期格式错误(填写如2011-11),");
							return null;
						}

						// 若为1，则新增,否则，查找若有实收的数据，将实收金额覆盖
						if ("1".equals(importFactType)) {
							bisFactList = null;
						} else {
							bisFactList = bisFactManager.getExistsFact(bisContId, bisFlatId, bisMultiId, chargeTypeCd, year, month);

							// 若bisFactList有大于一条数据，则删除
							if (bisFactList != null && bisFactList.size() > 0) {
								bisFact = bisFactList.get(0);
								for (int i = 1; i < bisFactList.size(); i++) {
									delFactList.add(bisFactList.get(i));
								}
							}

						}
						// 如果不存在该应收数据，则新增一条数据
						if (bisFact == null) {
							bisFact = new BisFact();
							insert++;
							bisFact.setBisProjectId(projectId);
							if ("1".equals(floorTypeCd)) { // 商铺
								if (StringUtils.isNotBlank(floorId)) {
									bisFact.setBisFloorId(floorId);
								}
								bisFact.setBisTenantId(bisTenantId);
								bisFact.setBisShopId(bisShopId);
								bisFact.setBisContId(bisContId);
								bisFact.setFloorType("1");
							} else if ("2".equals(floorTypeCd)) { // 公寓
								if (StringUtils.isNotBlank(floorId)) {
									bisFact.setBisFloorId(floorId);
								}
								if (StringUtils.isNotBlank(bisFlatId)) {
									bisFact.setBisFlatId(bisFlatId);
								}
								if (StringUtils.isNotBlank(bisContId)) {
									bisFact.setBisContId(bisContId);
								}
								bisFact.setFloorType("2");
							} else { // 多经
								if (StringUtils.isNotBlank(bisMultiId)) {
									bisFact.setBisMultiId(bisMultiId);
								}
								if (StringUtils.isNotBlank(bisContId)) {
									bisFact.setBisContId(bisContId);
								}
								bisFact.setFloorType("3");
							}
							bisFact.setChargeTypeCd(chargeTypeCd);
							SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
							bisFact.setFactDate(format.parse(factDateStr));
							// 实收权责年月
							bisFact.setFactYear(year);
							bisFact.setFactMonth(month);
							bisFact.setMoney(factFee);
							bisFact.setStatusCd("1"); // 实收 1：已审核 应收 2：已审核
							if ("1".equals(chargeTypeCd) || "3".equals(chargeTypeCd) || "4".equals(chargeTypeCd)) {
								bisFact.setTypeCd("0");
							} else {
								bisFact.setSequenceNo(new Long(r - 3));
								bisFact.setTypeCd("1");
							}
							//审核人
							bisFact.setCheckUserCd(user);
							//审核时间
							bisFact.setCheckDate(now);
							bisFact.setCreator(user);
							bisFact.setCreatedDate(now);
							bisFact.setUpdatedDate(now);
						} else {
							// 否则修改应收费用
							if (bisFact.getMoney() == null || bisFact.getMoney().compareTo(factFee) != 0 || !"2".equals(bisFact.getStatusCd())) {
								bisFact.setMoney(factFee);
								bisFact.setStatusCd("1"); // 已审核
								bisFact.setUpdator(user);
								bisFact.setUpdatedDate(now);	
								//审核人
								bisFact.setCheckUserCd(user);
								//审核时间
								bisFact.setCheckDate(now);
								update++;

							}
						}
						// 搜索相应的应收记录，并填充之
						param = new HashMap<String, Object>();
						hql = new StringBuffer("");
						hql.append(" from BisMust a where a.mustYear=:mustYear and a.mustMonth=:mustMonth and a.chargeTypeCd=:chargeTypeCd");
						param.put("mustYear", bisFact.getFactYear());
						param.put("mustMonth", bisFact.getFactMonth());
						param.put("chargeTypeCd", bisFact.getChargeTypeCd());
						if (StringUtils.isNotBlank(bisFact.getBisTenantId())) {
							hql.append(" and a.bisTenantId =:bisTenantId");
							param.put("bisTenantId", bisFact.getBisTenantId());
						}
						if (StringUtils.isNotBlank(bisFact.getBisFlatId())) {
							hql.append(" and a.bisFlatId=:bisFlatId");
							param.put("bisFlatId", bisFact.getBisFlatId());
						}
						if (StringUtils.isNotBlank(bisFact.getBisMultiId())) {
							hql.append(" and a.bisMultiId=:bisMultiId");
							param.put("bisMultiId", bisFact.getBisMultiId());
						}
						if (StringUtils.isNotBlank(bisFact.getBisContId())) {
							hql.append(" and a.bisCont.bisContId=:bisContId");
							param.put("bisContId", bisFact.getBisContId());
						}
						List<BisMust> bisMustList = bisMustManager.find(hql.toString(), param);
						if (bisMustList != null && bisMustList.size() == 1) {
							BisMust bisMust1 = bisMustList.get(0);
							bisFact.setTotalMustMoney(bisMust1.getMoney());
							// 若为1，则新增
							BigDecimal totFactMoney = new BigDecimal(0);
							if ("1".equals(importFactType)) {
								// 搜索应收对应的实收
								List<BisFact> bisFact1List = bisFactManager
										.getFact(bisProjectId, bisTenantId, null, null, chargeTypeCd, year, month, bisContId);
								for (BisFact fact : bisFact1List) {
									totFactMoney = totFactMoney.add(fact.getMoney());
								}
							}
							totFactMoney = totFactMoney.add(factFee);
							bisMust1.setTotalFactMoney(totFactMoney);
							mustList.add(bisMust1);
						}
						// 新增或修改应收日期
						++col;
						++c;
                        
						factList.add(bisFact);
					} else {
						// 若费用没有数据则跳一格（日期）
						++col;
					}
				}
				r = row;
			}
			// 批量保存
			bisFactManager.batchExecute(factList);
			if (mustList != null && mustList.size() > 0) {
				bisIncomeOtherManager.batchExecute(mustList);
			}
			if (delFactList != null && delFactList.size() > 0) {
				// 批量删除
				bisFactManager.batchDelete(delFactList);
			}

			long end = new Date().getTime();

			Struts2Utils.renderText(",success," + insert + "," + update + "," + (end - begin) / 1000 + ",");
		} catch (Exception e) {
			e.printStackTrace();
			String errorLocation = (r + 2) + "行 " + BisConstants.letters.get(c + 2) + "列";
			Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
		}
		return null;
	}

	public void getMapFeeType() throws Exception {

		Map<String, String> map = DictMapUtil.getMapChargeTypeNew();
		List<VoCombo> vos = new ArrayList<VoCombo>();
		VoCombo tmpVO = null;
		for (Iterator ite = map.entrySet().iterator(); ite.hasNext();) {
			tmpVO = new VoCombo();
			Map.Entry entry = (Map.Entry) ite.next();
			tmpVO.setId(entry.getKey().toString());
			tmpVO.setText(entry.getValue().toString());
			vos.add(tmpVO);
		}
		Struts2Utils.renderJson(vos);
	}

	public String getPrintTemplat() {
		return "pay-fuzhou";
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public File getImportFact() {
		return importFact;
	}

	public void setImportFact(File importFact) {
		this.importFact = importFact;
	}

	public void setVoPage(Page<VoFact> voPage) {
		this.voPage = voPage;
	}

	public VoFact getVoFact() {
		return voFact;
	}

	public BisFact getEntity() {
		return entity;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getInputStatus() {
		return this.inputStatus;
	}

	public void setInputStatus(String inputStatus) {
		this.inputStatus = inputStatus;
	}

	public void setEntity(BisFact entity) {
		this.entity = entity;
	}

	public String getBisTenantId() {
		return bisTenantId;
	}

	public String getCurrDetailName() {
		return currDetailName;
	}

	public void setCurrDetailName(String currDetailName) {
		this.currDetailName = currDetailName;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public String getLayOutCd() {
		return layOutCd;
	}

	public void setLayOutCd(String layOutCd) {
		this.layOutCd = layOutCd;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getCurrDetail() {
		return currDetail;
	}

	public void setCurrDetail(String currDetail) {
		this.currDetail = currDetail;
	}

	public void setBisTenantId(String bisTenantId) {
		this.bisTenantId = bisTenantId;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getChargeTypeCd() {
		return chargeTypeCd;
	}

	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}

	public String getFactYear() {
		return factYear;
	}

	public void setFactYear(String factYear) {
		this.factYear = factYear;
	}

	public String getFactMonth() {
		return factMonth;
	}

	public void setFactMonth(String factMonth) {
		this.factMonth = factMonth;
	}

	public BisFactYuS getBisFactYuS() {
		return bisFactYuS;
	}

	public void setBisFactYuS(BisFactYuS bisFactYuS) {
		this.bisFactYuS = bisFactYuS;
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

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getNameShop() {
		return nameShop;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public BigDecimal getPageSize() {
		return pageSize;
	}

	public void setPageSize(BigDecimal pageSize) {
		this.pageSize = pageSize;
	}

	public BigDecimal getPageNo() {
		return pageNo;
	}

	public void setPageNo(BigDecimal pageNo) {
		this.pageNo = pageNo;
	}

	public Page<PayRequisitionVo> getVoPayPage() {
		return voPayPage;
	}

	public void setVoPayPage(Page<PayRequisitionVo> voPayPage) {
		this.voPayPage = voPayPage;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Map<String, String> getMapBisProject() {
		return bisProjectManager.getMapBisProject();
	}

	public Map<String, String> getMapBisTenant() {
		return mapBisTenant;
	}

	public void loadCreator() {
		Struts2Utils.renderJson(bisFactManager.loadCreatUser(bisProjectId, layOutCd));
	}

	public void loadCheckUser() {
		Struts2Utils.renderJson(bisFactManager.loadCheckUser(bisProjectId, layOutCd));
	}

	public String batchOper() {
		/*
		 * if (StringUtils.isNotBlank(bisProjectId)) { BisProject project =
		 * bisProjectManager.getEntity(bisProjectId);
		 * setBisProjectName(project.getProjectName()); }
		 */
		return "batchOper";
	}

	public String batchAuth() {
		setProjectName();
		return "batchAuth";
	}

	public String batchMust() {
		setProjectName();
		return "batchMust";
	}

	public String batchCash() {
		setProjectName();
		return "batchCash";
	}

	private void setProjectName() {
		if (StringUtils.isNotBlank(bisProjectId)) {
			BisProject project = bisProjectManager.getEntity(bisProjectId);
			setBisProjectName(project.getProjectName());
		}
	}

	public void setImportFactType(String importFactType) {
		this.importFactType = importFactType;
	}

	public void setBisFactId(String bisFactId) {
		this.bisFactId = bisFactId;
	}

	public void setBisContId(String bisContId) {
		this.bisContId = bisContId;
	}

	public String getShopStoreName() {
		return shopStoreName;
	}

	public void setShopStoreName(String shopStoreName) {
		this.shopStoreName = shopStoreName;
	}

	public String getIfFromReport() {
		return ifFromReport;
	}

	public void setIfFromReport(String ifFromReport) {
		this.ifFromReport = ifFromReport;
	}

	public String getMustclick() {
		return mustclick;
	}

	public void setMustclick(String mustclick) {
		this.mustclick = mustclick;
	}

	public Map<String, String> getTotalCountMap() {
		return totalCountMap;
	}

	public void setTotalCountMap(Map<String, String> totalCountMap) {
		this.totalCountMap = totalCountMap;
	}

	public String getReportDateStart() {
		return reportDateStart;
	}

	public void setReportDateStart(String reportDateStart) {
		this.reportDateStart = reportDateStart;
	}

	public String getReportDateEnd() {
		return reportDateEnd;
	}

	public void setReportDateEnd(String reportDateEnd) {
		this.reportDateEnd = reportDateEnd;
	}
	
}
