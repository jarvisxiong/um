/**
 * 
 */
package com.hhz.ump.web.cost;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.cost.CostContPlanTplDetailManager;
import com.hhz.ump.dao.cost.CostContPlanTplManager;
import com.hhz.ump.entity.cost.CostContPlanTpl;
import com.hhz.ump.entity.cost.CostContPlanTplDetail;
import com.hhz.ump.entity.cost.CostContPlanTplDetailTreeGrid;
import com.hhz.ump.util.DictContants;

/**
 * 成本合约规划明细
 * 
 * @author qlb 3/28/2012
 * 
 */
public class CostContPlanTplDetailAction extends CrudActionSupport<CostContPlanTplDetail> {

	private static Log log = LogFactory.getLog(CostContPlanTplDetailAction.class);
	private static final long serialVersionUID = -557691039633614170L;
	@Autowired
	CostContPlanTplDetailManager costContPlanTplDetailManager;
	
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	
	@Autowired
	private CostContPlanTplManager costContPlanTplManager;
	
	// 新增加的节点
	private List<CostContPlanTplDetail> addCostContplanRows;
	
	private List<CostContPlanTplDetail> costContPlanTplDetailList;

	private List<CostContPlanTplDetailTreeGrid> costContPlanTplDetailVoList = new ArrayList<CostContPlanTplDetailTreeGrid>();
	
	private CostContPlanTplDetail newCostContPlanTplDetail;
	
	private CostContPlanTpl costContPlanTpl;
	//合同性质
	private Map<String,String> costContCharTypeCdMap;
	//合同分类
	private Map<String,String> costContTypeCdMap;
	//招标前置条件
	private Map<String,String> costInviPreCondCdsMap;
	//甲供材料、设备
	private Map<String,String> costPartMateTypeCdsMap;
	
	//合约模板版本
	private BigDecimal tplVersion;
	//合约模板名称
	private String tplName;
	
	// 当前合约规划下是否已经子节点
	private boolean isHasChilds = false;
	// 新添加合约会规则划显示方式 为True 则显示编辑框
	private boolean isShowInput = false;
	
	private CostContPlanTplDetail entity;
	
	private String costContPlanTplDetailId;
	
	private String costContPlanTplId;   //记录合约规划模版传递过来的id
	
	private boolean flag = false;
	
	private Double contSubTargAmtSum = 0.0;
	
	private static final String SUBJECT_CD = "10";  		//合约目标成本合计 subjectCd
	private static final String ROW_TYPE_CD = "1";		//合约目标成本合计 rowTypeCd
	private static final String SAME_LEVEL_NODE = "sameLevelNode";
	private static final String NEXT_LEVEL_NODE = "nextLevelNode";
	
	private static final String jsonStr = "{'CostContPlanTplDetail':[{'subjectCd':'1','rowTypeCd':'1'},{'subjectCd':'2','rowTypeCd':'1'}," +
															        "{'subjectCd':'3','rowTypeCd':'1'},{'subjectCd':'4','rowTypeCd':'1'}," +
															        "{'subjectCd':'5','rowTypeCd':'1'},{'subjectCd':'6','rowTypeCd':'1'}," +
															        "{'subjectCd':'7','rowTypeCd':'1'},{'subjectCd':'8','rowTypeCd':'1'}," +
															        "{'subjectCd':'9','rowTypeCd':'1'}]}";
	

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = costContPlanTplDetailManager.getEntity(getId());
			costContPlanTplDetailManager.delete(entity);
			Struts2Utils.renderJson("{isSuccess:true}");
		} else {
			Struts2Utils.renderJson("{status:false}");
			
		}
		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}
	
	/**
	 * 移出map中的空key
	 * @param map
	 * @return
	 */
	public static Map<String , String> removeNull(Map<String , String>  map){
		if(map != null){
			if(map.keySet().contains("")){
				map.keySet().remove("");
			}
		}
		return map;
	 }

	/**
	 * 合约规划模版统计
	 * @throws Exception
	 */
	public void getCostContDetailCalcAmt() throws Exception{
		String costContPlanTplId = Struts2Utils.getParameter("id");
		if(StringUtils.isNotBlank(costContPlanTplId)){
			costContPlanTplDetailList = costContPlanTplDetailManager.getContPlanTplDetailJsonStr(jsonStr);
			for(CostContPlanTplDetail costContPlanTplDetail:costContPlanTplDetailList){
				String subjectCd = costContPlanTplDetail.getSubjectCd();
				String rowTypeCd = costContPlanTplDetail.getRowTypeCd();
				//统计除【1-9】大类中的rowTypeCd为1的合约子成本额
				Double calcSum = costContPlanTplDetailManager.getContSubTargAmt1_9(costContPlanTplId,subjectCd,subjectCd.concat(rowTypeCd));
				//将统计后的合约子成本额更新到所有大类[1-9]中rowTypeCd为1中
				costContPlanTplDetailManager.updateContSubTargAmt(costContPlanTplId,subjectCd,rowTypeCd,BigDecimal.valueOf(calcSum));
				//统计所有[1-9]大类中rowTypeCd为1的合约子成本总额
				contSubTargAmtSum += costContPlanTplDetailManager.getContSubTargAmt(costContPlanTplId,subjectCd,rowTypeCd);
			}
			//将统计的合约子成本总额更新到合约目标成本合计
			costContPlanTplDetailManager.updateContSubTargAmt(costContPlanTplId,SUBJECT_CD,ROW_TYPE_CD,BigDecimal.valueOf(contSubTargAmtSum));
		}
	}
	
	@Override
	public String list() throws Exception {
		//合约规划模版统计
		getCostContDetailCalcAmt();
		//获取合约规划列表明细
		getContPlanTpl();
		//合同分类
		costContTypeCdMap = removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_CONT_TYPE_CD));
		//合同性质
		costContCharTypeCdMap = removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_CONT_CHAR_TYPE_CD));
		//招标前置条件
		costInviPreCondCdsMap = removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_INVI_PRE_COND_CDS));
		return SUCCESS;
	}
	

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotEmpty(getId())) {
			entity = costContPlanTplDetailManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new CostContPlanTplDetail();
		}
	}

	@Override
	public String save() throws Exception {
		//合同分类
		costContTypeCdMap = removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_CONT_TYPE_CD));
		//合同性质
		costContCharTypeCdMap = removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_CONT_CHAR_TYPE_CD));
		//招标前置条件
		costInviPreCondCdsMap = removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_INVI_PRE_COND_CDS));
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String id = getId();

			prepareModel();
			//专门处理日期类型
			String dateTypeField = "outDrawDate,planStartDate,planEndDate,tendStartDate";
			//专门处理数字类型
			String bigDecimalField = "contSubTargetAmt,contCaliAmt,targCaliDiffAmt,amount";
			//专门处理长整型类型
			String longTypeField = "subjectSequNo,contSequNo";

			String field = Struts2Utils.getParameter("field");
			String val = Struts2Utils.getParameter("val");
			Map<String, Object> values;
			StringBuffer hql = new StringBuffer(" ");
			Query query;
			if (StringUtils.isNotBlank(field)) {

				values = new HashMap<String, Object>();
				hql = new StringBuffer("update CostContPlanTplDetail set ");

				String[] fields = field.split(":");
				String[] vals = val.split(":");
				// 参数KEY
				String valKey = "";
				// String fieldKey="";
				for (int i = 0; i < fields.length; i++) {
					field = fields[i];
					val = "";
					if (i < vals.length) {

						val = vals[i];
					}
					if (i > 0) {

						hql.append(",");
					}
					valKey = "val" + i;
					// fieldKey="field"+i;
					values = new HashMap<String, Object>();
					hql.append(field + " = :" + valKey);

					if (dateTypeField.indexOf(field) > -1) {
						DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
						// 日期型
						values.put(valKey, format2.parse(val));
					} else if (bigDecimalField.indexOf(field) > -1) {
						// 小数型
						values.put(valKey, new BigDecimal(val));
					} else if (longTypeField.indexOf(field) > -1) {
						// 长整型
						values.put(valKey, Long.valueOf(val));
					} else {
						values.put(valKey, val);
					}

				}
				hql.append(" where costContPlanTplDetailId=:id");
				values.put("id", id);
				query = costContPlanTplDetailManager.getDao().createQuery(hql.toString(), values);
				query.executeUpdate();

			}
			strBuffer.append("true,");
			strBuffer.append("id:\"" + id + "\",");
			strBuffer.append("field:\"" + field + "\",");
			strBuffer.append("val:\"" + val + "\",");
			SimpleDateFormat dateformat1 = new SimpleDateFormat("HH:mm:ss ");
			strBuffer.append("updateDate:\"" + dateformat1.format(new Date()) + "\"}");

		} catch (Exception ee) {
			ee.printStackTrace();
			strBuffer.append("false,");
			SimpleDateFormat dateformat1 = new SimpleDateFormat("HH:mm:ss ");
			strBuffer.append("updateDate:\"" + dateformat1.format(new Date()) + "\"}");

		}
		getCostContDetailCalcAmt();
		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	@Override
	public CostContPlanTplDetail getModel() {
		if (StringUtils.isNotBlank(getId())) {
			entity = costContPlanTplDetailManager.getEntity(getId());
		}
		return entity;
	}

	public void saveBatch() {

		Struts2Utils.renderJson("{isSuccess:true}");

	}

	/**
	 * 获取合约规划明细
	 * 
	 * @author qlb 3/29/2012
	 * @param rowTypeCd
	 *            行类型
	 * @param subjectCd
	 *            科目类别
	 * @return 合约规划明细JSON数据列表
	 */
	public void getContPlanTpl() {
		String costContPlanTplId = Struts2Utils.getParameter("id");
		List<CostContPlanTplDetail> costContPLanDetailList = new ArrayList<CostContPlanTplDetail>();
		CostContPlanTplDetail ccpTplDetail = null;
		if(StringUtils.isNotBlank(costContPlanTplId)){
			costContPLanDetailList = costContPlanTplDetailManager.getCostContPlanTplDetail(costContPlanTplId);
			CostContPlanTplDetailTreeGrid ccpTplDetailTreeGrid = null;
			if(costContPLanDetailList!=null){
				for (int i = 0; i < costContPLanDetailList.size(); i++) {
					ccpTplDetail = costContPLanDetailList.get(i);
					ccpTplDetailTreeGrid = new CostContPlanTplDetailTreeGrid();
					ccpTplDetailTreeGrid.setCostContPlanTplDetailId(ccpTplDetail.getCostContPlanTplDetailId());
					ccpTplDetailTreeGrid.setCostContPlanTplId(ccpTplDetail.getCostContPlanTpl().getCostContPlanTplId());
					ccpTplDetailTreeGrid.setDispOrderDesc(ccpTplDetail.getDispOrderDesc());
					ccpTplDetailTreeGrid.setSubjectCd(ccpTplDetail.getSubjectCd());
					ccpTplDetailTreeGrid.setContTypeCd(ccpTplDetail.getContTypeCd());
					ccpTplDetailTreeGrid.setContName(ccpTplDetail.getContName());
					ccpTplDetailTreeGrid.setContCharTypeCd(ccpTplDetail.getContCharTypeCd());
					ccpTplDetailTreeGrid.setContSubTargetAmt(ccpTplDetail.getContSubTargetAmt());
					ccpTplDetailTreeGrid.setLandDesc(ccpTplDetail.getLandDesc());
					ccpTplDetailTreeGrid.setUnitDefineDesc(ccpTplDetail.getUnitDefineDesc());
					ccpTplDetailTreeGrid.setAmount(ccpTplDetail.getAmount());
					ccpTplDetailTreeGrid.setWorkReqDesc(ccpTplDetail.getWorkReqDesc());
					ccpTplDetailTreeGrid.setPartMateTypeCds(ccpTplDetail.getPartMateTypeCds());
					ccpTplDetailTreeGrid.setPartMateTypeIds(ccpTplDetail.getPartMateTypeIds());
					ccpTplDetailTreeGrid.setPartMateTypeNames(ccpTplDetail.getPartMateTypeNames());
					ccpTplDetailTreeGrid.setRelParentContId(ccpTplDetail.getRelParentContId());
					ccpTplDetailTreeGrid.setInviPreCondCds(ccpTplDetail.getInviPreCondCds());
					ccpTplDetailTreeGrid.setInviPreCondIds(ccpTplDetail.getInviPreCondIds());
					ccpTplDetailTreeGrid.setInviPreCondNames(ccpTplDetail.getInviPreCondNames());
					ccpTplDetailTreeGrid.setOutDrawDate(ccpTplDetail.getOutDrawDate());
					ccpTplDetailTreeGrid.setPlanStartDate(ccpTplDetail.getPlanStartDate());
					ccpTplDetailTreeGrid.setPlanEndDate(ccpTplDetail.getPlanEndDate());
					ccpTplDetailTreeGrid.setTendStartDate(ccpTplDetail.getTendStartDate());
					ccpTplDetailTreeGrid.setMemoDesc(ccpTplDetail.getMemoDesc());
					ccpTplDetailTreeGrid.setRowTypeCd(ccpTplDetail.getRowTypeCd());
					ccpTplDetailTreeGrid.setContSequNo(ccpTplDetail.getContSequNo());
					ccpTplDetailTreeGrid.setSubjectSequNo(ccpTplDetail.getSubjectSequNo());
					ccpTplDetailTreeGrid.setCreator(ccpTplDetail.getCreator());
					ccpTplDetailTreeGrid.setCreatedDate(ccpTplDetail.getCreatedDate());
					ccpTplDetailTreeGrid.setRecordVersion(ccpTplDetail.getRecordVersion());
					costContPlanTplDetailVoList.add(ccpTplDetailTreeGrid);
		
				}
			}else{
				costContPlanTplDetailVoList = null;
			}
		}
		// Struts2Utils.renderJson(costContPlanTplDetailVoList);

	}

	/**
	 * 判断是否为父级节合约规划
	 * 
	 * @param costContPLanDetailList
	 * @param rowTypeCd
	 * @param subjectCd
	 * @return
	 */
	private boolean isParent(List<CostContPlanTplDetail> costContPLanDetailList, String rowTypeCd, String subjectCd, String parentId) {
		CostContPlanTplDetail ccpTplDetail = null;
		for (int i = 0; i < costContPLanDetailList.size(); i++) {
			ccpTplDetail = costContPLanDetailList.get(i);
			// 如果父节点不为空则比较父母节点，否根据行号和科目CD比较
			if ((StringUtils.isNotBlank(ccpTplDetail.getRelParentContId()) && parentId.equals(ccpTplDetail.getRelParentContId()))
					|| (StringUtils.isNotBlank(ccpTplDetail.getRowTypeCd()) && ccpTplDetail.getRowTypeCd().equals(rowTypeCd)
							&& StringUtils.isNotBlank(ccpTplDetail.getSubjectCd()) && ccpTplDetail.getSubjectCd().equals(subjectCd)))
				return true;

		}
		return false;
	}

	/**
	 * 获取合约规划明细子节点
	 * 
	 * @author qlb 3/29/2012
	 * @param rowTypeCd
	 *            行类型
	 * @param subjectCd
	 *            科目类别
	 * @return 子合约规划列表
	 */
	private List<CostContPlanTplDetailTreeGrid> getChildren(List<CostContPlanTplDetail> costContPLanDetailList, String rowTypeCd, String subjectCd,
			String parentId) {

		List<CostContPlanTplDetailTreeGrid> ccpsubTplDetailTreegridList = new ArrayList<CostContPlanTplDetailTreeGrid>();
		CostContPlanTplDetail ccpTplDetail = null;
		CostContPlanTplDetailTreeGrid ccpTplDetailTreeGrid = null;

		try {
			for (int i = 0; i < costContPLanDetailList.size(); i++) {
				ccpTplDetail = costContPLanDetailList.get(i);
				if ((StringUtils.isNotBlank(ccpTplDetail.getRelParentContId()) && !parentId.equals(ccpTplDetail.getRelParentContId()))
						|| ((StringUtils.isNotBlank(ccpTplDetail.getRowTypeCd()) && !ccpTplDetail.getRowTypeCd().equals(rowTypeCd)) || (StringUtils
								.isNotBlank(ccpTplDetail.getSubjectCd()) && !ccpTplDetail.getSubjectCd().equals(subjectCd)))) {

					continue;
				}
				ccpTplDetailTreeGrid = new CostContPlanTplDetailTreeGrid();
				ccpTplDetailTreeGrid.setCostContPlanTplDetailId(ccpTplDetail.getCostContPlanTplDetailId());
				ccpTplDetailTreeGrid.setCostContPlanTplId(ccpTplDetail.getCostContPlanTpl().getCostContPlanTplId());
				ccpTplDetailTreeGrid.setDispOrderDesc(ccpTplDetail.getDispOrderDesc());
				ccpTplDetailTreeGrid.setSubjectCd(ccpTplDetail.getSubjectCd());
				ccpTplDetailTreeGrid.setContTypeCd(ccpTplDetail.getContTypeCd());
				ccpTplDetailTreeGrid.setContName(ccpTplDetail.getContName());
				ccpTplDetailTreeGrid.setContCharTypeCd(ccpTplDetail.getContCharTypeCd());
				ccpTplDetailTreeGrid.setContSubTargetAmt(ccpTplDetail.getContSubTargetAmt());
				ccpTplDetailTreeGrid.setLandDesc(ccpTplDetail.getLandDesc());
				ccpTplDetailTreeGrid.setUnitDefineDesc(ccpTplDetail.getUnitDefineDesc());
				ccpTplDetailTreeGrid.setAmount(ccpTplDetail.getAmount());
				ccpTplDetailTreeGrid.setWorkReqDesc(ccpTplDetail.getWorkReqDesc());
				ccpTplDetailTreeGrid.setPartMateTypeCds(ccpTplDetail.getPartMateTypeCds());
				ccpTplDetailTreeGrid.setPartMateTypeIds(ccpTplDetail.getPartMateTypeIds());
				ccpTplDetailTreeGrid.setPartMateTypeNames(ccpTplDetail.getPartMateTypeNames());
				ccpTplDetailTreeGrid.setRelParentContId(ccpTplDetail.getRelParentContId());
				ccpTplDetailTreeGrid.setInviPreCondCds(ccpTplDetail.getInviPreCondCds());
				ccpTplDetailTreeGrid.setInviPreCondIds(ccpTplDetail.getInviPreCondIds());
				ccpTplDetailTreeGrid.setInviPreCondNames(ccpTplDetail.getInviPreCondNames());
				ccpTplDetailTreeGrid.setOutDrawDate(ccpTplDetail.getOutDrawDate());
				ccpTplDetailTreeGrid.setPlanStartDate(ccpTplDetail.getPlanStartDate());
				ccpTplDetailTreeGrid.setPlanEndDate(ccpTplDetail.getPlanEndDate());
				ccpTplDetailTreeGrid.setTendStartDate(ccpTplDetail.getTendStartDate());
				ccpTplDetailTreeGrid.setMemoDesc(ccpTplDetail.getMemoDesc());
				ccpTplDetailTreeGrid.setRowTypeCd(ccpTplDetail.getRowTypeCd());
				ccpTplDetailTreeGrid.setContSequNo(ccpTplDetail.getContSequNo());
				ccpTplDetailTreeGrid.setCreator(ccpTplDetail.getCreator());
				ccpTplDetailTreeGrid.setCreatedDate(ccpTplDetail.getCreatedDate());
				ccpTplDetailTreeGrid.setRecordVersion(ccpTplDetail.getRecordVersion());
				Long rType = Long.valueOf("1");
				if (StringUtils.isNotBlank(ccpTplDetail.getRowTypeCd())) {
					rType = Long.valueOf(ccpTplDetail.getRowTypeCd());
					rType++;
				}
				costContPLanDetailList.remove(i);
				i--;
				if (isParent(costContPLanDetailList, rType.toString(), ccpTplDetail.getSubjectCd(), ccpTplDetail.getCostContPlanTplDetailId())) {

					ccpTplDetailTreeGrid.setChildren(getChildren(costContPLanDetailList, rType.toString(), ccpTplDetail.getSubjectCd(), ccpTplDetail
							.getCostContPlanTplDetailId()));
				}

				ccpsubTplDetailTreegridList.add(ccpTplDetailTreeGrid);

			}

		} catch (Exception ee) {
			return null;
		}
		return ccpsubTplDetailTreegridList;
	}

	/**
	 * 获取合约规划明细子节点
	 * 
	 * @author qlb 3/29/2012
	 * @param rowTypeCd
	 *            行类型
	 * @param subjectCd
	 *            科目类别
	 * @return 子合约规划列表
	 */
	private List<CostContPlanTplDetailTreeGrid> getChildren(String rowTypeCd, String subjectCd, String parentId) {

		List<CostContPlanTplDetailTreeGrid> ccpsubTplDetailTreegridList = new ArrayList<CostContPlanTplDetailTreeGrid>();

		try {

			StringBuffer hql = new StringBuffer("  from CostContPlanTplDetail t where 1=1 ");
			Map<String, Object> values = new HashMap<String, Object>();

			hql.append(" and (t.relParentContId = :parentId ");
			values.put("parentId", parentId);
			if (StringUtils.isNotBlank(subjectCd)) {
				hql.append(" or( t.subjectCd=:subjectCd");
				hql.append("  and t.rowTypeCd=:rowType )");

				values.put("subjectCd", subjectCd);
				values.put("rowType", rowTypeCd);
			}
			hql.append(" ) ");
			hql.append(" order by t.contSequNo asc");
			List<CostContPlanTplDetail> costContPLanDetailList = costContPlanTplDetailManager.find(hql.toString(), values);
			CostContPlanTplDetail ccpTplDetail = null;
			CostContPlanTplDetailTreeGrid ccpTplDetailTreeGrid = null;
			for (int i = 0; i < costContPLanDetailList.size(); i++) {
				ccpTplDetail = costContPLanDetailList.get(i);
				ccpTplDetailTreeGrid = new CostContPlanTplDetailTreeGrid();
				ccpTplDetailTreeGrid.setCostContPlanTplDetailId(ccpTplDetail.getCostContPlanTplDetailId());
				ccpTplDetailTreeGrid.setCostContPlanTplId(ccpTplDetail.getCostContPlanTpl().getCostContPlanTplId());
				ccpTplDetailTreeGrid.setDispOrderDesc(ccpTplDetail.getDispOrderDesc());
				ccpTplDetailTreeGrid.setSubjectCd(ccpTplDetail.getSubjectCd());
				ccpTplDetailTreeGrid.setContTypeCd(ccpTplDetail.getContTypeCd());
				ccpTplDetailTreeGrid.setContName(ccpTplDetail.getContName());
				ccpTplDetailTreeGrid.setContCharTypeCd(ccpTplDetail.getContCharTypeCd());
				ccpTplDetailTreeGrid.setContSubTargetAmt(ccpTplDetail.getContSubTargetAmt());
				ccpTplDetailTreeGrid.setLandDesc(ccpTplDetail.getLandDesc());
				ccpTplDetailTreeGrid.setUnitDefineDesc(ccpTplDetail.getUnitDefineDesc());
				ccpTplDetailTreeGrid.setAmount(ccpTplDetail.getAmount());
				ccpTplDetailTreeGrid.setWorkReqDesc(ccpTplDetail.getWorkReqDesc());
				ccpTplDetailTreeGrid.setPartMateTypeCds(ccpTplDetail.getPartMateTypeCds());
				ccpTplDetailTreeGrid.setPartMateTypeIds(ccpTplDetail.getPartMateTypeIds());
				ccpTplDetailTreeGrid.setPartMateTypeNames(ccpTplDetail.getPartMateTypeNames());
				ccpTplDetailTreeGrid.setRelParentContId(ccpTplDetail.getRelParentContId());
				ccpTplDetailTreeGrid.setInviPreCondCds(ccpTplDetail.getInviPreCondCds());
				ccpTplDetailTreeGrid.setInviPreCondIds(ccpTplDetail.getInviPreCondIds());
				ccpTplDetailTreeGrid.setInviPreCondNames(ccpTplDetail.getInviPreCondNames());
				ccpTplDetailTreeGrid.setOutDrawDate(ccpTplDetail.getOutDrawDate());
				ccpTplDetailTreeGrid.setPlanStartDate(ccpTplDetail.getPlanStartDate());
				ccpTplDetailTreeGrid.setPlanEndDate(ccpTplDetail.getPlanEndDate());
				ccpTplDetailTreeGrid.setTendStartDate(ccpTplDetail.getTendStartDate());
				ccpTplDetailTreeGrid.setMemoDesc(ccpTplDetail.getMemoDesc());
				ccpTplDetailTreeGrid.setRowTypeCd(ccpTplDetail.getRowTypeCd());
				ccpTplDetailTreeGrid.setContSequNo(ccpTplDetail.getContSequNo());
				ccpTplDetailTreeGrid.setCreator(ccpTplDetail.getCreator());
				ccpTplDetailTreeGrid.setCreatedDate(ccpTplDetail.getCreatedDate());
				ccpTplDetailTreeGrid.setRecordVersion(ccpTplDetail.getRecordVersion());
				Long rType = Long.valueOf("1");
				if (StringUtils.isNotBlank(ccpTplDetail.getRowTypeCd())) {
					rType = Long.valueOf(ccpTplDetail.getRowTypeCd());
					rType++;
				}
				costContPLanDetailList.remove(i);
				i--;
				if (isParent(costContPLanDetailList, rType.toString(), ccpTplDetail.getSubjectCd(), ccpTplDetail.getCostContPlanTplDetailId())) {
					ccpTplDetailTreeGrid.setChildren(getChildren(rType.toString(), ccpTplDetail.getSubjectCd(), ccpTplDetail.getCostContPlanTplDetailId()));

				}
				ccpsubTplDetailTreegridList.add(ccpTplDetailTreeGrid);
				// costContPLanDetailList.remove(i);
				// i--;
			}

		} catch (Exception ee) {
			return null;
		}
		return ccpsubTplDetailTreegridList;
	}


	/**
	 * 生成合约规划最大序号
	 * 
	 * @return 最大序号
	 */
	public Long buildMaxContSequence(String subjectCd,String rowType) {

		Long sequence = new Long("1");

		Map<String, Object> values = new HashMap<String, Object>();
		Object[] obj = null;
		StringBuffer sqlBuf = new StringBuffer();

		sqlBuf.append("select ");
		sqlBuf.append(" to_char(max(t.contSequNo))");

		sqlBuf.append(" from CostContPlanTplDetail t where 1=1");
		
		sqlBuf.append("  and t.subjectCd=:subjectCd ");
		sqlBuf.append("  and t.rowTypeCd=:rowType ");

		values.put("subjectCd", subjectCd);
		values.put("rowType", rowType);
		List maxSequenceList = costContPlanTplDetailManager.find(sqlBuf.toString(), values);
		String value = maxSequenceList.toString();
		if (maxSequenceList != null && StringUtils.isNotBlank(value) && value.indexOf("null") < 0 && maxSequenceList.size() > 0) {

			sequence = Long.valueOf((String) maxSequenceList.get(0));
		}
		sequence++;
		return sequence;

	}

	/**
	 * 增加合约规划
	 * 描述:添加单行及甲供材料、设备多选添加多行操作
	 * @return
	 */
	public String addCostContPlanRow() {
		//甲供材料、设备Cd及name,格式为：{'CostContPlanTplDetail':[{'cd0':'10','name0':'空调'},{'cd1'：'20','name1':'空调'}]}
		String selectPartMateTypeJsonStr = Struts2Utils.getParameter("selectPartMateTypeJsonStr");
		//甲供材料、设备名
		String selectPartMateTypeNames = Struts2Utils.getParameter("selectPartMateTypeNames");
		//获取同级或下级标识
		String node = Struts2Utils.getParameter("node");
		String contPlanTplId = Struts2Utils.getParameter("contPlanTplId");
		//添加行标示(0-增加行操作,1-添加甲供材料)
		String rowFlag = Struts2Utils.getParameter("addRowFlag");
		
		log.debug("selectPartMateTypeJsonStr: "+selectPartMateTypeJsonStr);
		costContPlanTplDetailVoList.clear();
		CostContPlanTplDetailTreeGrid costContPlanTplDetailVo;
		if (StringUtils.isNotBlank(getId())) {
			entity = costContPlanTplDetailManager.getEntity(getId());
			if(rowFlag.equals("1")){
				costContPlanTplDetailVo = new CostContPlanTplDetailTreeGrid();
				newCostContPlanTplDetail = new CostContPlanTplDetail();
				// 设置合约规划大类
				CostContPlanTpl costContPlanTpl = new CostContPlanTpl();
				costContPlanTpl.setCostContPlanTplId(contPlanTplId);
				newCostContPlanTplDetail.setCostContPlanTpl(costContPlanTpl);
				// 合约序号
//				newCostContPlanTplDetail.setContSequNo(buildMaxContSequence(entity.getSubjectCd(),entity.getRowTypeCd()));
				newCostContPlanTplDetail.setContSequNo(entity.getContSequNo());
				// 设置父节点
				newCostContPlanTplDetail.setRelParentContId(entity.getCostContPlanTplDetailId());
				// 科目CD
				newCostContPlanTplDetail.setSubjectCd(entity.getSubjectCd());
				
				if (StringUtils.isNotBlank(entity.getRowTypeCd())) {
					newCostContPlanTplDetail.setRowTypeCd(entity.getRowTypeCd());
				}
				
				// 版本号
				newCostContPlanTplDetail.setRecordVersion(Long.valueOf("1"));
				// 科目序号
				newCostContPlanTplDetail.setSubjectSequNo(entity.getSubjectSequNo());
				

				costContPlanTplDetailManager.saveCostContPlanTplDetail(newCostContPlanTplDetail);
//				addCostContplanRows.add(newCostContPlanTplDetail);

				Long rType = Long.valueOf("1");
				if (StringUtils.isNotBlank(newCostContPlanTplDetail.getRowTypeCd())) {
					rType = Long.valueOf(newCostContPlanTplDetail.getRowTypeCd());
				}

				costContPlanTplDetailVo.setCostContPlanTplDetailId(newCostContPlanTplDetail.getCostContPlanTplDetailId());
				costContPlanTplDetailVo.setCostContPlanTplId(newCostContPlanTplDetail.getCostContPlanTpl().getCostContPlanTplId());
				costContPlanTplDetailVo.setSubjectCd(newCostContPlanTplDetail.getSubjectCd());
				costContPlanTplDetailVo.setRowTypeCd(rType.toString());
				costContPlanTplDetailVo.setContSequNo(newCostContPlanTplDetail.getContSequNo());
				costContPlanTplDetailVo.setSubjectSequNo(newCostContPlanTplDetail.getSubjectSequNo());

				costContPlanTplDetailVoList.add(costContPlanTplDetailVo);
			}else if(rowFlag.equals("0")){
				//解析json文件
				addCostContplanRows=costContPlanTplDetailManager.getPartMateTypeJsonStr(selectPartMateTypeJsonStr);
				for (CostContPlanTplDetail costContPlanTplDetail : addCostContplanRows) {
					costContPlanTplDetailVo = new CostContPlanTplDetailTreeGrid();
					newCostContPlanTplDetail=new CostContPlanTplDetail();
					CostContPlanTpl costContPlanTpl = new CostContPlanTpl();
					costContPlanTpl.setCostContPlanTplId(contPlanTplId);
					newCostContPlanTplDetail.setCostContPlanTpl(costContPlanTpl);
					//标段/合同名称
					newCostContPlanTplDetail.setContName(costContPlanTplDetail.getPartMateTypeNames());
					// 设置父节点
					newCostContPlanTplDetail.setRelParentContId(entity.getCostContPlanTplDetailId());
					// 甲供材料、设备Cd
					newCostContPlanTplDetail.setPartMateTypeCds(costContPlanTplDetail.getPartMateTypeCds());
					// 甲供材料、设备Name
					newCostContPlanTplDetail.setPartMateTypeNames(costContPlanTplDetail.getPartMateTypeNames());
					
					// 科目CD
					newCostContPlanTplDetail.setSubjectCd(entity.getSubjectCd());
					// 科目序号(特殊处理：同级节点--当前节点值不变，子节点--当前节点值  + 1)
					if(StringUtils.isNotBlank(node)){
						//同级节点处理
						if(SAME_LEVEL_NODE.trim().equals(node)){
							newCostContPlanTplDetail.setSubjectSequNo(entity.getSubjectSequNo());
						}
						//下级节点处理
						if(NEXT_LEVEL_NODE.trim().equals(node)){
							newCostContPlanTplDetail.setSubjectSequNo(entity.getSubjectSequNo()+1);
						}
					}
					
					if (StringUtils.isNotBlank(entity.getRowTypeCd())) {
						newCostContPlanTplDetail.setRowTypeCd(entity.getRowTypeCd());
					}
					
					// 显示序号
					newCostContPlanTplDetail.setContSequNo(entity.getContSequNo());
					
					// 版本号
					newCostContPlanTplDetail.setRecordVersion(Long.valueOf("1"));
					costContPlanTplDetailManager.saveCostContPlanTplDetail(newCostContPlanTplDetail);
					
					Long rType = Long.valueOf("1");
					if (StringUtils.isNotBlank(newCostContPlanTplDetail.getRowTypeCd())) {
						rType = Long.valueOf(newCostContPlanTplDetail.getRowTypeCd());
					}

					costContPlanTplDetailVo.setCostContPlanTplDetailId(newCostContPlanTplDetail.getCostContPlanTplDetailId());
					costContPlanTplDetailVo.setCostContPlanTplId(newCostContPlanTplDetail.getCostContPlanTpl().getCostContPlanTplId());
					costContPlanTplDetailVo.setContName(costContPlanTplDetail.getPartMateTypeNames());
					costContPlanTplDetailVo.setPartMateTypeCds(costContPlanTplDetail.getPartMateTypeCds());
					costContPlanTplDetailVo.setPartMateTypeNames(costContPlanTplDetail.getPartMateTypeNames());
					costContPlanTplDetailVo.setSubjectCd(newCostContPlanTplDetail.getSubjectCd());
					costContPlanTplDetailVo.setRowTypeCd(rType.toString());
					costContPlanTplDetailVo.setContSequNo(newCostContPlanTplDetail.getContSequNo());
					costContPlanTplDetailVo.setSubjectSequNo(newCostContPlanTplDetail.getSubjectSequNo());

					costContPlanTplDetailVoList.add(costContPlanTplDetailVo);
				}
				
			}
		} else {
			entity = new CostContPlanTplDetail();
		}
		return "addRow";
	}

	/**
	 * 选择甲供料
	 * 
	 * @author qlb 4/11/2012
	 * @return 选择甲供料页面
	 */
	public String selectPartMateType() {
		costContPlanTplDetailId=Struts2Utils.getParameter("costContPlanTplDetailId");
		//甲供材料、设备 
		costPartMateTypeCdsMap=removeNull(appDictTypeManager.getDictDataByTypeCd(DictContants.COST_PART_MATE_TYPE_CDS));
		return "selectPartMateType";

	}
	
	/**
	 * @return the addCostContplanRows
	 */
	public List<CostContPlanTplDetail> getAddCostContplanRows() {
		return addCostContplanRows;
	}

	/**
	 * @param addCostContplanRows
	 *            the addCostContplanRows to set
	 */
	public void setAddCostContplanRows(List<CostContPlanTplDetail> addCostContplanRows) {
		this.addCostContplanRows = addCostContplanRows;
	}

	/**
	 * @return the isHasChilds
	 */
	public boolean isHasChilds() {
		return isHasChilds;
	}

	/**
	 * @param isHasChilds
	 *            the isHasChilds to set
	 */
	public void setHasChilds(boolean isHasChilds) {
		this.isHasChilds = isHasChilds;
	}

	/**
	 * @return the isShowInput
	 */
	public boolean isShowInput() {
		return isShowInput;
	}

	/**
	 * @param isShowInput
	 *            the isShowInput to set
	 */
	public void setShowInput(boolean isShowInput) {
		this.isShowInput = isShowInput;
	}

	public Map<String, String> getCostContCharTypeCdMap() {
		return costContCharTypeCdMap;
	}

	public void setCostContCharTypeCdMap(Map<String, String> costContCharTypeCdMap) {
		this.costContCharTypeCdMap = costContCharTypeCdMap;
	}

	public Map<String, String> getCostContTypeCdMap() {
		return costContTypeCdMap;
	}

	public void setCostContTypeCdMap(Map<String, String> costContTypeCdMap) {
		this.costContTypeCdMap = costContTypeCdMap;
	}

	
	public Map<String, String> getCostInviPreCondCdsMap() {
		return costInviPreCondCdsMap;
	}

	public void setCostInviPreCondCdsMap(Map<String, String> costInviPreCondCdsMap) {
		this.costInviPreCondCdsMap = costInviPreCondCdsMap;
	}

	public Map<String, String> getCostPartMateTypeCdsMap() {
		return costPartMateTypeCdsMap;
	}

	public void setCostPartMateTypeCdsMap(Map<String, String> costPartMateTypeCdsMap) {
		this.costPartMateTypeCdsMap = costPartMateTypeCdsMap;
	}

	/**
	 * @return the costContPlanTplDetailVoList
	 */
	public List<CostContPlanTplDetailTreeGrid> getCostContPlanTplDetailVoList() {
		return costContPlanTplDetailVoList;
	}

	/**
	 * @param costContPlanTplDetailVoList
	 *            the costContPlanTplDetailVoList to set
	 */
	public void setCostContPlanTplDetailVoList(List<CostContPlanTplDetailTreeGrid> costContPlanTplDetailVoList) {
		this.costContPlanTplDetailVoList = costContPlanTplDetailVoList;
	}
	

	public String getCostContPlanTplId() {
		return costContPlanTplId;
	}

	public void setCostContPlanTplId(String costContPlanTplId) {
		this.costContPlanTplId = costContPlanTplId;
	}

	
	public String getCostContPlanTplDetailId() {
		return costContPlanTplDetailId;
	}

	public void setCostContPlanTplDetailId(String costContPlanTplDetailId) {
		this.costContPlanTplDetailId = costContPlanTplDetailId;
	}

	public CostContPlanTpl getCostContPlanTpl() {
		return costContPlanTpl;
	}

	public void setCostContPlanTpl(CostContPlanTpl costContPlanTpl) {
		this.costContPlanTpl = costContPlanTpl;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

	public BigDecimal getTplVersion() {
		return tplVersion;
	}

	public void setTplVersion(BigDecimal tplVersion) {
		this.tplVersion = tplVersion;
	}

	public String getTplName() {
		return tplName;
	}

	public void setTplName(String tplName) {
		this.tplName = tplName;
	}

	/**
	 * 保存、修改模版
	 * @return
	 * @throws Exception
	 */
	public String saveTemplate() throws Exception{
		String id = Struts2Utils.getParameter("costContPlanTplId");
		String tplName = Struts2Utils.getParameter("tplName");
		String tplVersion = Struts2Utils.getParameter("tplVersion");
		if(StringUtils.isNotBlank(id)){
			costContPlanTpl = costContPlanTplManager.getEntity(id);
			
			if(StringUtils.isNotBlank(tplName)){
				costContPlanTpl.setTplName(tplName.trim());
			}
			if(StringUtils.isNotBlank(tplVersion)){
				costContPlanTpl.setTplVersion(BigDecimal.valueOf(Long.valueOf(tplVersion.trim())));
			}
			//修改合约模板
			costContPlanTplManager.saveCostContPlanTpl(costContPlanTpl);
		}else{
			costContPlanTpl = new CostContPlanTpl();
			if(StringUtils.isNotBlank(tplName)){
				costContPlanTpl.setTplName(tplName.trim());
			}
			if(StringUtils.isNotBlank(tplVersion)){
				costContPlanTpl.setTplVersion(BigDecimal.valueOf(Long.valueOf(tplVersion.trim())));
			}
			//合约规划模版版本初始值为1
			costContPlanTpl.setRecordVersion(1);
			//合约模板保存
			costContPlanTplManager.saveCostContPlanTpl(costContPlanTpl);
			String costContPlanTplId = costContPlanTpl.getCostContPlanTplId();
			log.debug("COST_CONT_PLAN_TPL_ID ==> "+costContPlanTplId);
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("status", "success");
			jsonObj.put("costContPlanTplId", costContPlanTplId);
			Struts2Utils.renderJson(jsonObj);
		}
		return null;
	}

	/**
	 * 判断合约规划模板中数据是否存在
	 * @return
	 * @throws Exception
	 */
	public String hasExist() throws Exception{
		String costContPlanTplId = Struts2Utils.getParameter("costContPlanTplId");
		costContPlanTplDetailList = costContPlanTplDetailManager.getCostContPlanTplDetail(costContPlanTplId);
		if(costContPlanTplDetailList!=null) {
			Struts2Utils.renderText("yes");
		}else{
			Struts2Utils.renderText("no");
		}
		return null;
	}
	
	/**
	 * @author wangming 2012.5.16
	 * @return
	 * @throws Exception 
	 */
	public String buildTabs() throws Exception{
		String newBuild=Struts2Utils.getParameter("newBuild");
		if(StringUtils.isNotBlank(newBuild)){
			//0-表示新建模版
			if("0".equals(newBuild.trim())){
				flag = false;
			}
		}else{
			costContPlanTplId = Struts2Utils.getParameter("id");
			costContPlanTpl = costContPlanTplManager.getCostContPlanTpl(costContPlanTplId);
			tplName = costContPlanTpl.getTplName();
			tplVersion = costContPlanTpl.getTplVersion();
			list();
			flag = true;
		}
		return "tabs";
	}
}
