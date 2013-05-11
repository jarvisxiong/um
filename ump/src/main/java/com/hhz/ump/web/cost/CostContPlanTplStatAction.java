package com.hhz.ump.web.cost;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateParser;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostContPlanTplDetailManager;
import com.hhz.ump.dao.cost.CostContPlanTplStatManager;
import com.hhz.ump.entity.cost.CostContPlanTpl;
import com.hhz.ump.entity.cost.CostContPlanTplStat;

@Namespace("/cost")
public class CostContPlanTplStatAction extends CrudActionSupport<CostContPlanTplStat> {

	private static final long serialVersionUID = -4429113901834138249L;

	private static Log log = LogFactory.getLog(CostContPlanTplStatAction.class);

	@Autowired
	private CostContPlanTplStatManager costContPlanTplStatManager;
	
	@Autowired
	CostContPlanTplDetailManager costContPlanTplDetailManager;

	private List<CostContPlanTplStat> costContPlanTplStatList_01;
	private List<CostContPlanTplStat> costContPlanTplStatList_02;
	private List<CostContPlanTplStat> costContPlanTplStatList_03;
	private CostContPlanTplStat costContPlanTplStat;
	private CostContPlanTpl costContPlanTpl;

	private Double sumContractNewObjAmt=0.0;  //原目标成本合计
	private long maxSubSequNo;  //最大子节点显示序号
	private static final String ROWTYPE_CD_1 = "1";       //定义合约计划大类为【1】的ROWTYPE_CD
	private static final String ROWTYPE_CD_5 = "5";       //定义合约计划大类为【5】的ROWTYPE_CD
	private static final String ROWTYPE_CD_6 = "6";       //定义合约计划大类为【6】的ROWTYPE_CD
	private static final String SUBJECT_CD_CONTRACT_10 = "10";     //定义合约计划大类为【10】的科目类型CD
	private static final String ROW_TYPE_CD_CONTRACT_10 = "1010"; //定义合约计划大类为【10】的子类数据值为【1010】的汇总科目CD(rowtype)
	private static final String ROW_TYPE_CD_CONTRACT_20 = "1020"; //定义合约计划大类为【10】的子类数据值为【1020】的汇总科目CD(rowtype)
	private static final String ROW_TYPE_CD_CONTRACT_30 = "1030"; //定义合约计划大类为【10】的子类数据值为【1030】的汇总科目CD(rowtype)
	private static final String ROW_TYPE_CD_CONTRACT_31 = "1031"; //定义合约计划大类为【10】的子类数据值为【1031】的汇总科目CD(rowtype)
	private static final String ROW_TYPE_CD_CONTRACT_40 = "1040"; //定义合约计划大类为【10】的子类数据值为【1040】的汇总科目CD(rowtype)
	private static final String ROW_TYPE_CD_CONTRACT_50 = "1050"; //定义合约计划大类为【10】的子类数据值为【1050】的汇总科目CD(rowtype)
	private static final String ROW_TYPE_CD_CONTRACT_60 = "1060"; //定义合约计划大类为【10】的子类数据值为【1060】的汇总科目CD(rowtype)
	
	//定义所有每个大类中的土地成本合约常量值【1-9】--土地成本（原目标）（以JSON格式存储）
	private static final String contractAmtJsonStr="{'CostContPlanTplStat':[{'subjectCd':'1','rowTypeCd':'2'},{'subjectCd':'2','rowTypeCd':'2'}," +
	   																	   "{'subjectCd':'3','rowTypeCd':'2'},{'subjectCd':'4','rowTypeCd':'2'}," +
	   																	   "{'subjectCd':'5','rowTypeCd':'2'},{'subjectCd':'6','rowTypeCd':'2'}," +
	   																	   "{'subjectCd':'7','rowTypeCd':'2'},{'subjectCd':'8','rowTypeCd':'2'}," +
	   																	   "{'subjectCd':'9','rowTypeCd':'2'}]}";
	
//	private static final String jsonStr="{'CostContPlanTplStat':[{'subjectCd':'1','rowTypeCd':'1','contSubTargAmt':'111'},{'subjectCd':'2','rowTypeCd':'1','contSubTargAmt':'211'}," +
//	                                                            "{'subjectCd':'3','rowTypeCd':'1','contSubTargAmt':'311'},{'subjectCd':'4','rowTypeCd':'1','contSubTargAmt':'411'}," +
//	                                                            "{'subjectCd':'5','rowTypeCd':'1','contSubTargAmt':'511'},{'subjectCd':'6','rowTypeCd':'1','contSubTargAmt':'611'}," +
//	                                                            "{'subjectCd':'7','rowTypeCd':'1','contSubTargAmt':'711'},{'subjectCd':'8','rowTypeCd':'1','contSubTargAmt':'811'}," +
//	                                                            "{'subjectCd':'9','rowTypeCd':'1','contSubTargAmt':'911'}]}";

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
		
		String costContPlanTplId = Struts2Utils.getParameter("id");
		System.out.println("  costContPlanTplId  : "+costContPlanTplId);
		if(StringUtils.isNotBlank(costContPlanTplId)){
			/*******************在执行搜索操作之前进行数据更新操作 (数据迁移)---start***********************/
			//调用合约规划明细中传递过来的json格式文件(eg:"{'CostContPlanTplStat':[{'subjectCd':'1','rowTypeCd':'1','contSubTargAmt':'111'}]}")
			String jsonStr=costContPlanTplDetailManager.getContSubTargAmt(costContPlanTplId);
			costContPlanTplStatList_01=costContPlanTplStatManager.getJsonStr(jsonStr);
			for (int i = 0; i < costContPlanTplStatList_01.size(); i++) {
				costContPlanTplStat=costContPlanTplStatList_01.get(i);
				String subjectCd=costContPlanTplStat.getSubjectCd();
				String rowTypeCd=costContPlanTplStat.getRowTypeCd();
				BigDecimal contSubTargAmt=costContPlanTplStat.getContSubTargAmt();
				//批量将数据作更新处理
				costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,subjectCd, rowTypeCd,contSubTargAmt);
				//搜索并统计出所有大类【1-9】中的【土地成本（原目标）+ 调减至其他二级科目成本 + 从其他科目调增成本】的累加和
				Double sumAmtDiff=costContPlanTplStatManager.getSumContractSubTargAmt(costContPlanTplId,subjectCd, ROWTYPE_CD_1, ROWTYPE_CD_5, ROWTYPE_CD_6);
				//搜索所有大类【1-9】土地成本（合约）额
				Double contractAmt=costContPlanTplStatManager.getContSubTargAmt(costContPlanTplId,subjectCd,ROWTYPE_CD_1);
				//将更新入库的数据进行批量统计计算处理
				costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,subjectCd, ROWTYPE_CD_5,BigDecimal.valueOf(sumAmtDiff-contractAmt));
			}
			
			/********************在执行搜索操作之前进行数据更新操作 ---end**********************/
			
			
			//统计所有大类第一个子节点的合约目标成本合计
			BigDecimal sumContractObjAmt=costContPlanTplStatManager.calcContractObjAmt(jsonStr);
			//更新合约计划土地成本总额（大类为10且子类为1010）
			costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_10, sumContractObjAmt);
			
			//统计所有大类第一个子节点的原目标成本合计
			costContPlanTplStatList_02=costContPlanTplStatManager.getJsonStr(contractAmtJsonStr);
			for (int i = 0; i < costContPlanTplStatList_02.size(); i++) {
				costContPlanTplStat=costContPlanTplStatList_02.get(i);
				String subjectCd=costContPlanTplStat.getSubjectCd();
				String rowTypeCd=costContPlanTplStat.getRowTypeCd();
				if(StringUtils.isNotEmpty(subjectCd)&&StringUtils.isNotEmpty(rowTypeCd)){
					sumContractNewObjAmt+=costContPlanTplStatManager.getContSubTargAmt(costContPlanTplId,subjectCd,rowTypeCd);
				}
			}
			//更新原目标成本合计
			costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_20, BigDecimal.valueOf(sumContractNewObjAmt));
			
			//搜索合约计划大类为【10】，子类ROWTYPECD为【1031】目标成本额调整合计
			Double sumSubTargAmt=costContPlanTplStatManager.getSumSubTargAmt(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_31);
			//将统计出的目标成本调整额的值更新到子类ROWTYPECD为【1030】中
			costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_30, BigDecimal.valueOf(sumSubTargAmt));
			//统计合约目标与目标差额（公式:原目标成本合计 + 目标成本调整合计 - 合约目标成本合计）
			Double contractObjAmtDiff=sumContractNewObjAmt+sumSubTargAmt-Double.valueOf(sumContractObjAmt.toString());
			costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_40, BigDecimal.valueOf(contractObjAmtDiff));
	
			//搜索合约计划大类为【10】，子类ROWTYPECD为【1050】不可预见费
			Double unknowAmt=costContPlanTplStatManager.getContSubTargAmt(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_50);
			//统计部可预见费余额【10】【1060】（公式：合约目标与目标差额  + 不可预见费）
			costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId,SUBJECT_CD_CONTRACT_10, ROW_TYPE_CD_CONTRACT_60, BigDecimal.valueOf(contractObjAmtDiff+unknowAmt));
		
			//搜索所有数据
			costContPlanTplStatList_03=costContPlanTplStatManager.searchCostContPlanStatList(costContPlanTplId);
			if(costContPlanTplStatList_03!=null){
				log.info("[" + DateParser.formatDate(new Date(), "yyyyMMdd") + "] 当前合约规划记录：" + costContPlanTplStatList_03.size() + "条！");
			}else{
				log.info("[" + DateParser.formatDate(new Date(), "yyyyMMdd") + "] 当前合约规划记录：" + costContPlanTplStatList_03 + "条！");
			}
		}
		
		return "list";
	}
	@Override
	protected void prepareModel() throws Exception {
		
	}

	@Override
	public void prepareSave() throws Exception {
		//主键
		String statId = Struts2Utils.getParameter("contPlanTplStatId");
		//外键
		String costContPlanTplId_F = Struts2Utils.getParameter("costContPlanTplId");
		log.info("主键: contPlanTplStatId = " + statId+",外键:costContPlanTplId = "+costContPlanTplId_F);
		if(null!=statId&&!statId.equals("")&&!statId.equals("null")){
			costContPlanTplStat = costContPlanTplStatManager.getEntity(statId);
			String calcSubjectCd = Struts2Utils.getParameter("subjectCd");
			String calcRowTypeCd = Struts2Utils.getParameter("rowTypeCd");
			String calcDiff = Struts2Utils.getParameter("calcDiff");
			log.info("calcSubjectCd = "+calcSubjectCd+" ,calcRowTypeCd = "+calcRowTypeCd+" ,calcDiff = "+calcDiff);
			if(StringUtils.isNotEmpty(calcSubjectCd)&&StringUtils.isNotEmpty(calcRowTypeCd)){
				//以存储过程方式批处理
				if(calcDiff==""||calcDiff.equals("NaN")){
					calcDiff="0.0";
				}else{
					if(StringUtils.isNotBlank(costContPlanTplId_F)){
						costContPlanTplStatManager.updateBatchProcessing(costContPlanTplId_F,calcSubjectCd, calcRowTypeCd, BigDecimal.valueOf(Double.valueOf(calcDiff)));
					}
				}
			}
		}else{
			//获取项目合约规划Id（从父节点中获取）
			String costContPlanTplId=Struts2Utils.getParameter("costContPlanTplId");
			//获取科目类型CD
			String subjectCd=Struts2Utils.getParameter("subjectCd");
			//获取汇总科目CD
			String rowTypeCd=Struts2Utils.getParameter("rowTypeCd");
			//获取科目显示序号
			String subjectSequNoStr=subjectCd;
			
			log.info("合约规划ID（costContPlanTplId）： "+costContPlanTplId+" ,科目类型CD（subjectCd）: "+subjectCd+"  ,汇总科目CD（rowTypeCd）: "+rowTypeCd);
			//获取最大的子类显示序号
			if(null!=subjectCd&&!subjectCd.equals("")&&!subjectCd.equals("null")){
				String rowTypeStr=String.valueOf(Double.valueOf(rowTypeCd));
				if(StringUtils.isNotBlank(costContPlanTplId)){
					maxSubSequNo=costContPlanTplStatManager.getMaxSubSequNo(costContPlanTplId,subjectCd,rowTypeCd);
				}
			}
			
			
			costContPlanTplStat=new CostContPlanTplStat();
			costContPlanTpl=new CostContPlanTpl();
			costContPlanTpl.setCostContPlanTplId(costContPlanTplId);
			costContPlanTplStat.setCostContPlanTpl(costContPlanTpl);
			costContPlanTplStat.setSubjectCd(subjectCd);
			if(null!=rowTypeCd&&!rowTypeCd.equals("")&&!rowTypeCd.equals("null")){
				costContPlanTplStat.setRowTypeCd(String.valueOf(Long.parseLong(rowTypeCd)+1));
			}
			if(null!=subjectSequNoStr&&!subjectSequNoStr.equals("")&&!subjectSequNoStr.equals("null")){
				long subjectSequNo=Long.parseLong(subjectSequNoStr);
				costContPlanTplStat.setSubjectSequNo(subjectSequNo);
			}
			costContPlanTplStat.setSubSequNo(maxSubSequNo+1);
			
		}
	}

	/**
	 * 描述：合约计划汇总保存
	 * 如果数据库中存在数据则更新，反之则插入
	 * @author wangming 2012-05-08
	 * @return
	 * @throws Exception
	 */
	@Override
	public String save() throws Exception {
		costContPlanTplStatManager.saveCostContPlanTplStat(costContPlanTplStat);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 描述：删除合约计划新增行
	 * @author wangming 2012-05-08
	 * @return
	 * @throws Exception
	 */
	public String delRow() throws Exception{
		//获取合约计划主键ID
		String contPlanTplStatId=Struts2Utils.getParameter("costContPlanTplStatId");
		if(StringUtils.isNotEmpty(contPlanTplStatId)){
			costContPlanTplStatManager.deleteCostContPlanTplStat(contPlanTplStatId);
			Struts2Utils.renderText("success");
		}
		return null;
	}
	
	@Override
	public CostContPlanTplStat getModel() {

		return null;
	}

	
	
	public List<CostContPlanTplStat> getCostContPlanTplStatList_01() {
		return costContPlanTplStatList_01;
	}

	public void setCostContPlanTplStatList_01(List<CostContPlanTplStat> costContPlanTplStatList_01) {
		this.costContPlanTplStatList_01 = costContPlanTplStatList_01;
	}

	public List<CostContPlanTplStat> getCostContPlanTplStatList_02() {
		return costContPlanTplStatList_02;
	}

	public void setCostContPlanTplStatList_02(List<CostContPlanTplStat> costContPlanTplStatList_02) {
		this.costContPlanTplStatList_02 = costContPlanTplStatList_02;
	}

	public List<CostContPlanTplStat> getCostContPlanTplStatList_03() {
		return costContPlanTplStatList_03;
	}

	public void setCostContPlanTplStatList_03(List<CostContPlanTplStat> costContPlanTplStatList_03) {
		this.costContPlanTplStatList_03 = costContPlanTplStatList_03;
	}

	public CostContPlanTplStat getCostContPlanTplStat() {
		return costContPlanTplStat;
	}

	public void setCostContPlanTplStat(CostContPlanTplStat costContPlanTplStat) {
		this.costContPlanTplStat = costContPlanTplStat;
	}

}
