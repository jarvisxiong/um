package com.hhz.ump.web.bid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.spring.SpringContextHolder;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidDivisionSupRelManager;
import com.hhz.ump.dao.bid.BidDivisitonManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidProjectManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.entity.bid.BidDivisionSupRel;
import com.hhz.ump.entity.bid.BidDivisiton;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidProject;
import com.hhz.ump.entity.bid.BidSup;

@Namespace("/bid")
@Results( { @Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel","inputName", "excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }) })
public class BidAnalysisAction extends CrudActionSupport<BidDivisionSupRel> {

	private static final long serialVersionUID = -2175787891545829279L;
	private static final Log log = LogFactory.getLog(BidAnalysisAction.class);

	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected BidDivisionSupRelManager bidDivisionSupRelManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected BidProjectManager bidProjectManager;
	List<BidProjectAllVo> bidProjectAllVoList = new ArrayList<BidProjectAllVo>();
	/**
	 * 
	 */
	private String itemIdsTemp;//所选国标大小类
	/**
	 * 标段ID
	 */
	private String bidLedgerId;
	/**
	 * 工程ID
	 */
	private String bidProjectId;
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 工程ID
	 */
	private String bidPorjectId;
	/**
	 * 标段
	 */
	private BidLedger bidLedger;
	private String batchOne;
	private String batchTwo;
	/**
	 * 供应商列表
	 */
	private List<BidSup> bss;
	/**
	 * 供应商数量
	 */
	private Integer supNameNumber;
	/**
	 * 供应商列表
	 */
	private List<BidSupCompareVo> bidDivisionVos;
	/**
	 * 量价比较数据
	 */
	private List<Object[]> bidDivisionVoList;
	/**
	 * 分部分项列表
	 */
	private List<BidDivisitonVo> divisionVoList;
	/**
	 * 清单轮次差异列表
	 */
	private Page<BidDivisiton> divisionList = new Page<BidDivisiton>(1000);
	/**
	 * 总价数据
	 */
	private List<TotalPriceVo> tpVoList;
	/**
	 * 数字格式
	 */
	DecimalFormat df;
	/**
	 * 批号
	 */
	private String batchNo;
	/**
	 * 供应商ID号
	 */
	private String supIds;
	/**
	 * 选择列
	 */
	private String selectProperty;
	/**
	 * 搜索方式(1、1个供应商对应多个批次[batchNo]2、多个供应商对应1个批次[sup])
	 */
	private String searchType;	
	/**
	 * 汇总显示Vo
	 */
	private List<SummaryAnalysisVo> summaryVoList;
	/**
	 * 标段列表
	 */
	private List<BidLedger> listBidLedgers;
	/**
	 * 表头1
	 */
	private String tableHeader1;
	/**
	 * 表头2
	 */
	private String tableHeader2;
	/**
	 * 警告
	 */
	private String alarm;
	/**
	 * 列数
	 */
	private Integer colNumber;
	/**
	 * 工程
	 */
	private List<BidProject> bidPros;
	/**
	 * 标段工程列表
	 */
	private List<BidProject> listProjects;
	/**
	 * 标底统计值
	 */
	private Double biaodiTotal=0.0;
	/**
	 * 搜索类型 1-量/价比较(默认) 2-平方米指标 3-占总价比例 4-投标总价汇总比较
	 */
	private String typeCd;
	/**
	 * 分页搜索
	 */
	private Page voPage = new Page(100);
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;
	
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
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
		return null;
	}

	/**
	 * 根据标段ID搜索标段台帐以及供应商、和本台帐的项目CD相关的标段台帐
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {
		//如果bidLedgerId不为空
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//搜索本投标台帐
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			//获取已经接受的供应商
			bss = filterBidDivisiton(bidLedger);
			//项目CD
			String strOrgCd = bidLedger.getOrgCd();
			//如果项目CD不为空，则根据项目CD搜索标段台帐
			if (StringUtils.isNotBlank(strOrgCd)) {
				Map<String, Object> map = new HashMap<String, Object>();	
				map.put("orgCd", strOrgCd);
				String hql = "from BidLedger where orgCd= :orgCd order by createdDate desc";
				listBidLedgers= bidLedgerManager.find(hql, map);
			}
			//升序搜索工程
			if(bidLedger!=null){
				listProjects = bidProjectManager.getEnableProjectListByName(bidLedger.getBidLedgerId());
			}
		}
		
		return "list";
	}
	
	public String listTrue() throws Exception {
		//如果bidLedgerId不为空
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//搜索本投标台帐
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			//获取已经接受的供应商
			bss = filterBidDivisiton(bidLedger);
			//项目CD
			String strOrgCd = bidLedger.getOrgCd();
			//如果项目CD不为空，则根据项目CD搜索标段台帐
			if (StringUtils.isNotBlank(strOrgCd)) {
				Map<String, Object> map = new HashMap<String, Object>();	
				map.put("orgCd", strOrgCd);
				String hql = "from BidLedger where orgCd= :orgCd order by createdDate desc";
				listBidLedgers= bidLedgerManager.find(hql, map);
			}
		}
		return "listTrue";
	}
	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BidDivisionSupRel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 汇总评优统计搜索
	 *
	 */
	public String summaryAnalysis() {
		//如果标段ID不为空
		if(StringUtils.isNotBlank(this.getBidLedgerId())){
			//校验是否存在标底公司
			if(!bidSupManager.hasBiaoDiSup(this.getBidLedgerId())) {
				Struts2Utils.renderText("<font color='red'>标段不存在标底单位！</font>");
				return null;
			}
			// 根据标段ID获取分部分项列表
			 bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
		}		
		// 如果指定了搜索的供应商
		if (StringUtils.isNotBlank(this.getSupIds())) {
			bss = parseSups();
		} else {
			// 默认标段的客户,筛选有几家供应商
			if(bidLedger!=null) {
				bss = filterBidDivisiton(bidLedger);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 搜索SQL
		StringBuffer sq1 = null;
		// 搜索结果集
		List<Object[]> rs1 = null;
		// 汇总评优显示行
		SummaryAnalysisVo summaryVo = null;
		//汇总评优显示结果集
		summaryVoList = new ArrayList<SummaryAnalysisVo>();		
		//标底公司
		BidSup biaodiSup=bidSupManager.getBiaodiSup(bidLedger.getBidLedgerId());
		//准备各供应商的轮次map值
		Map<String,String> latestBatchNo = bidDivisionSupRelManager.parseBidSupLatestBatchNo(bss,this.getBatchNo());
		//加入标底轮次
		latestBatchNo.put(biaodiSup.getBidSupId(), this.getBatchNo());
		
		// 汇总评优表头-参数true
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "表头", true,bss,latestBatchNo));
		
		// 1、分部分项工程量清单计价表		
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_division_sup_rel", bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		
		// vo转换,加入列表
		if (rs1 != null) {
			summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "分部分项工程量清单计价表", false,bss,latestBatchNo));
		}
		
		// 2、措施项目清单与计价表(一)
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_measure_sup_rel",bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "措施项目清单与计价表(一)", false,bss,latestBatchNo));
		
		// 3、措施项目清单与计价表(二)
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_measure_sup_rel2",bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "措施项目清单与计价表(二)", false,bss,latestBatchNo));
		
		// 4、其他项目清单与计价汇总表
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_other_item_rel", bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "其他项目清单与计价汇总表", false,bss,latestBatchNo));
		
		// 5、零星工程费用表
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_sporadic_sup_rel", bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "零星工程费用表", false,bss,latestBatchNo));
		
		// 6、规费项目清单与计价表
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_fees_sup_rel", bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "规费项目清单与计价表", false,bss,latestBatchNo));
		
		// 7、税金项目清单与计价表
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_taxs_sup_rel", bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "税金项目清单与计价表", false,bss,latestBatchNo));
		
		// 8、标段其他费用
		// 准备搜索预计
		sq1 = bidLedgerManager.constructSummaryAnalysisSql("bid_sup_other_rel", bss,latestBatchNo,bidLedgerId,biaodiSup);
		// 搜索结果集
		rs1 = bidDivisionSupRelManager.findBySql(sq1.toString(), map);
		// vo转换,加入列表
		summaryVoList.add(makeSummaryAnalysisVo(rs1, summaryVo, "标段其他费用", false,bss,latestBatchNo));

		
		//统计值
		for(int i=1;i<summaryVoList.size();i++){
			SummaryAnalysisVo vo=summaryVoList.get(i);
			if(vo.getSupTotalPrice()!=null&&StringUtils.isNotBlank(vo.getSupTotalPrice())) {
				biaodiTotal+=Double.parseDouble(vo.getSupTotalPrice());
			}
		}
		return "summaryAnalysis";
	}

	/**
	 * 构建汇总评优显示行
	 */
	private SummaryAnalysisVo makeSummaryAnalysisVo(List<Object[]> rs, SummaryAnalysisVo rowVo, 
			String itemName,boolean isHeader,List<BidSup> bidSups,Map<String,String>  latestBatchNo) {
		rowVo = new SummaryAnalysisVo();
		String col = null;
		List<String> colList = null;
		// 如果是表头
		if (isHeader) {
			// 项目名称
			rowVo.setItemName("项目名称");
			// 标底单位总计
			rowVo.setSupTotalPrice("标底单位");
			colList = new ArrayList<String>();
			for (BidSup sup : bidSups) {
				// 如果为标底公司，则跳过
				if (bidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {
					continue;
				}
				//明标
				if("1".equals(sup.getBidLedger().getVisableFlg())){
					colList.add(sup.getSupName());
				}else{
					colList.add(sup.getSupName());
				}				
			}
			rowVo.setTotalValues(colList);
		}
		// 如果为表体数据
		else {
			Object[] o = rs.get(0);
			// 项目名称
			rowVo.setItemName(itemName);
			// 标底单位总计
			rowVo.setSupTotalPrice(((BigDecimal) o[0]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			colList = new ArrayList<String>();
			int i = 1;
			// 根据供应商获取总值
			for (BidSup sup : bidSups) {
				// 如果为标底公司，则跳过
				if (bidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {
					continue;
				}
				//如果为分部分项表,则需要考虑加入供应商自定义的数据
				if("分部分项工程量清单计价表".equals(itemName)){
					col = bidDivisionSupRelManager.fetchBidDivisitonCustTotal(sup,latestBatchNo.get(sup.getBidSupId())).add((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP).toString();//((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				}
				else{
					col = ((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				}				
				i++;
				colList.add(col);
			}
			rowVo.setTotalValues(colList);
		}
		return rowVo;
	}
	

	/**
	 * 解析批号转换为SQL条件
	 * 
	 * @param tableName
	 * @return
	 */
	private StringBuffer parseBatchNoSql(String tableName) {
		String or = " or ";
		StringBuffer ledgerBatchNo = new StringBuffer();
		if (StringUtils.isNotBlank(this.getBatchNo())) {
			//解析批次
			String[] batchNos = parseBatchNo(this.getBatchNo());
			int i = 0;
			ledgerBatchNo.append(" and ( ");
			for (String batchNo : batchNos) {
				if (StringUtils.isNotBlank(batchNo)) {
					if (i == 0) {
						ledgerBatchNo.append(tableName + ".batch_no=" + batchNo);
					} else {
						ledgerBatchNo.append(or + tableName + ".batch_no=" + batchNo);
					}
				}
				i++;
			}
			ledgerBatchNo.append(" )");
		}
		return ledgerBatchNo;
	}

	/**
	 * parseBatchNo:(解析批次参数)
	 * 
	 */
	private String[] parseBatchNo(String sourceString) {
		if (StringUtils.isNotBlank(sourceString)) {
			String[] batchNos = sourceString.split(",");
			return batchNos;
		} else
			return null;
	}

	/**
	 * 定制解析供应商
	 *
	 */
	private List<BidSup> parseSups() {
		List<BidSup> sups = null;
		if (StringUtils.isNotBlank(this.getSupIds())) {
			String bidSupId[] = this.getSupIds().split(",");
			BidSup bidSup = null;
			sups = new ArrayList<BidSup>();
			for (String supId : bidSupId) {
				if (StringUtils.isNotBlank(supId)) {
					bidSup = bidSupManager.getEntity(supId);
					sups.add(bidSup);
				}
			}
		}
		return sups;
	}

	/**
	 * 
	 * parseSelectProperties:(解析搜索定制搜索字段)
	 * 
	 * @throws
	 * 
	 */
	public List<String> parseSelectProperties(boolean isDivisionTemp,List<String> splitSelectProperties) {
		List<String> properties = new ArrayList<String>();
		if (splitSelectProperties != null&&splitSelectProperties.size()>0) {
			for (String p : splitSelectProperties) {
				if (StringUtils.isNotBlank(p)) {
					if ("quantitie".equals(p)) {
						properties.add("quantitie");
					}
					if ("unitPrice".equals(p)) {
						if (isDivisionTemp) {
							properties.add("comp_unit_amt");
						} else {
							properties.add("comp_unit_amt");
						}
					}
					if ("totalPrice".equals(p)) {
						properties.add("total_amt");
					}
				}
			}
		}
		return properties;
	}
	
	/**
	 * 
	 *列名转换
	 */
	public  String changeColForTableHeader(String selectedCol){
		if ("quantitie".equals(selectedCol))
			return "工程量";
		if ("unitPrice".equals(selectedCol))
			return "单价";
		if ("totalPrice".equals(selectedCol))
			return "合价";
		else
			return "未知";		
	}
	
	
	/**
	 *拆分选择列
	 */
	public List<String> splitSelectProperties(){
		List<String> properties = new ArrayList<String>();
		//如果获取的选择列不为空
		if (StringUtils.isNotBlank(this.getSelectProperty())) {
			//以","拆分为数组
			String[] pros = this.getSelectProperty().split(",");
			for (String p : pros) {
				//如果不为空,则加入到List列表
				if (StringUtils.isNotBlank(p)) {
					properties.add(p);
				}
			}
		}			
		return properties;
	}

	/**
	 * 
	 * 回标分析之 量价比较
	 */
	public String quantityAnalysis() {
		//页号
		String pageNo=Struts2Utils.getParameter("pageNo");
		//如果不为空，则使用请求的页号
		if(StringUtils.isNotBlank(pageNo)){
			voPage.setPageNo(Integer.parseInt(pageNo));
		}
		//如果标段不为空(正常情况不会为空)
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//校验是否存在标底公司
			if(!bidSupManager.hasBiaoDiSup(this.getBidLedgerId())) {
				Struts2Utils.renderText("<font color='red'>标段不存在标底单位！</font>");
				return null;
			}
			//标段
			BidLedger bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			//标底公司
			BidSup biaodiSup=bidSupManager.getBiaodiSup(bidLedger.getBidLedgerId());
			// 如果指定了搜索的供应商
			if (StringUtils.isNotBlank(this.getSupIds())) {
				bss = parseSups();
			} else {
				// 默认标段的客户,筛选有几家供应商
				//前台计算，所以注销筛选
				bss = filterBidDivisiton(bidLedger);
			}
			//搜索参数表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bidLedgerId", this.getBidLedgerId());
			
			//被定制选择的属性列选择
			List<String> selecedProCols=splitSelectProperties();
			//搜索条件
			Map<String, Class> mapClazz=new HashMap<String, Class>();
			voPage=bidDivisionSupRelManager.getDao().findPageSql(voPage, constructSql(searchType,bss,selecedProCols,biaodiSup).toString(), map, mapClazz);
			//清单VO
			BidDivisitonVo rowVo = null;
			//各供应商的统计数据对象VO
			BidSupCompareVo colVo = null;
			//各供应商的统计数据对象VO列表
			List<BidSupCompareVo> colList = null;
			//清单列表初始化
			divisionVoList = new ArrayList<BidDivisitonVo>();
			//搜索结果集中一行的对象数组
			Object[] result = null;
			//如果搜索的结果集不为空,则开始构建显示用的VO
			if (!voPage.getResult().isEmpty()) {
				for (int i = 0; i < voPage.getResult().size(); i++) {
					result = (Object[]) voPage.getResult().get(i);
					// 根据不同的搜索类型构造行结果集
					rowVo = makeBidDivisitonVo(result, colList, rowVo, colVo, selecedProCols.size(),searchType,selecedProCols,bss);
					divisionVoList.add(rowVo);
				}
			}
		}
		//设置一级列数(即供应商+标底单位+算术平均几家之和)
		this.setColNumber(bss.size()+2);
		// 根据不同的搜索类型返回不同的结果页面
		if ("batchNo".equals(searchType))
			// 一个供应商对应多个批次
			return "batchNosQuantityCompare";
		else
			// 一个批次对应多个供应商
			return "quantityCompare";
	}
	
	/**
	 * 
	 * 导出量价比较到EXCEL 
	 *
	 */
	public String quantityPriceExcel(){
		//如果标段不为空(正常情况不会为空)
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//搜索清单条数,作为一页的条数大小
			Long pageSize=bidDivisitonManager.countBidDivisitons(bidProjectId, batchNo);
			if(pageSize!=null){
				voPage.setPageSize(pageSize.intValue());
			}
			//校验是否存在标底公司
			if(!bidSupManager.hasBiaoDiSup(this.getBidLedgerId())) {
				Struts2Utils.renderText("<font color='red'>标段不存在标底单位！</font>");
				return null;
			}
			//标段
			 bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			//标底公司
			BidSup biaodiSup=bidSupManager.getBiaodiSup(bidLedger.getBidLedgerId());
			// 如果指定了搜索的供应商
			if (StringUtils.isNotBlank(this.getSupIds())) {
				bss = parseSups();
			} else {
				// 默认标段的客户,筛选有几家供应商
				//前台计算，所以注销筛选
				bss = filterBidDivisiton(bidLedger);
			}
			//搜索参数表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bidLedgerId", this.getBidLedgerId());
			
			//被定制选择的属性列选择
			List<String> selecedProCols=splitSelectProperties();
			//搜索条件
			Map<String, Class> mapClazz=new HashMap<String, Class>();
			voPage=bidDivisionSupRelManager.getDao().findPageSql(voPage, constructSql(searchType,bss,selecedProCols,biaodiSup).toString(), map, mapClazz);
			//清单VO
			BidDivisitonVo rowVo = null;
			//各供应商的统计数据对象VO
			BidSupCompareVo colVo = null;
			//各供应商的统计数据对象VO列表
			List<BidSupCompareVo> colList = null;
			//清单列表初始化
			divisionVoList = new ArrayList<BidDivisitonVo>();
			//搜索结果集中一行的对象数组
			Object[] result = null;
			//如果搜索的结果集不为空,则开始构建显示用的VO
			if (!voPage.getResult().isEmpty()) {
				for (int i = 0; i < voPage.getResult().size(); i++) {
					result = (Object[]) voPage.getResult().get(i);
					// 根据不同的搜索类型构造行结果集
					rowVo = makeBidDivisitonVo(result, colList, rowVo, colVo, selecedProCols.size(),searchType,selecedProCols,bss);
					divisionVoList.add(rowVo);
				}
			}
			
			//将结果转化为EXCEL
			ByteArrayOutputStream outExcelFile=null;
			try {
				//获取工程信息
				BidProject bidProject=bidProjectManager.getProjectById(bidProjectId);
				//excel表的名称
				excelFileName="回标分析导出EXCEL";				
				String  sheetName= excelFileName;
				//如果台帐及工程不为空，则附加台帐及工程名称
				sheetName=""+bidLedger.getBidSectionName()+"标段,"+bidProject.getProjectName()+"工程,回标分析";
				//输出文件流
				outExcelFile= new ByteArrayOutputStream();
				//将分析数据填入excel
				fillInExcel(sheetName,divisionVoList,outExcelFile,bss);	
				//将数据读出到数组
				byte[] data = outExcelFile.toByteArray();
				//将数据写入文件，执行导出
				excelFile=new ByteArrayInputStream(data);
				//文件名编码转换，防止乱码
				excelFileName=new String(excelFileName.getBytes("GBK"), "ISO8859-1");
			} catch (Exception e) {
				e.printStackTrace();  
				
			}finally{
				try {
					if (outExcelFile != null) {
						outExcelFile.close();
					}
				} catch (Exception e) {
					log.error(e);
				}
			}
			
		}
		return "export";
	}
	
	/**
	 * 
	 *插入单元格
	 */
	private void addCell(HSSFRow row,HSSFCell cell,HSSFRichTextString text,int colIndex,HSSFCellStyle style,String contentType,String strContent,Double douContent){
		//根据下标创建单元格
		cell= row.createCell(colIndex);
		//设置单元格格式
		cell.setCellStyle(style);
		//设置单行号
		row.setHeightInPoints(25);
		//如果为字符类型单元格,则创建字符串型单元格格式
		if("1".equals(contentType)){
			text= new HSSFRichTextString(strContent);
			cell.setCellValue(text);
		}else{
			cell.setCellValue(douContent);
		}
	      
	      
	}
	/**
	 * 
	 *合并单元格
	 */
	private void mergeCell(HSSFSheet sheet,CellRangeAddress address,int col1,int col2,int col3,int col4){
		//合并区域
		address=new CellRangeAddress(col1,col2,col3,col4);
		//执行合并
        sheet.addMergedRegion(address);
	}
	
	/**
	 * 
	 * 表头数据
	 */
	private void fillInExcel(String  sheetName,List<BidDivisitonVo> tmpDivisionVoList, OutputStream out,List<BidSup> sups) {	
		// 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(sheetName);
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth((short) 15);
	      //冻结
	      sheet.createFreezePane(6, 2);
	      // 生成一个样式
	      HSSFCellStyle style = PoiStyle.buildStyle(workbook);	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = PoiStyle.buildStyle2(workbook);	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style3 = PoiStyle.buildStyle3(workbook);
	      // 生成并设置另一个样式
	      HSSFCellStyle style4 = PoiStyle.buildStyle4(workbook);
	      //行
	      HSSFRow row = sheet.createRow(0);	 
	      //单元格
	      HSSFCell cell=null;
	      //单元格内容
	      HSSFRichTextString text=null;
	      //合并单元区域
	      CellRangeAddress address=null;
	      
	      mergeCell(sheet,address,0,1,0,0);
	      //开始构建表头
	      //序号		  		  
	      addCell(row,cell,text,0,style4,"1","序号",null);
	      //项目编号       
	      addCell(row,cell,text,1,style4,"1","项目编号",null); 
	      //项目名称     
	      addCell(row,cell,text,2,style4,"1","项目名称",null);  
	      //项目特征描述  
	      addCell(row,cell,text,3,style4,"1","项目特征描述",null);	       
	      //计量单位  
	      addCell(row,cell,text,4,style4,"1","计量单位",null); 	       
	      //工程量  
	      addCell(row,cell,text,5,style4,"1","工程量",null);
	      //算术平均值表头
	      addCell(row,cell,text,6,style4,"1","算术平均值",null);
	      addCell(row,cell,text,7,style4,"1"," ",null);
	      mergeCell(sheet,address,0,0,6,7);
	      //标底单位表头
	      addCell(row,cell,text,8,style4,"1","标底单位",null);
	      addCell(row,cell,text,9,style4,"1","",null);
	      mergeCell(sheet,address,0,0,8,9);	      
	      //动态构建公司表头一
	      if(sups!=null&&sups.size()>0){
	        BidSup sup=null;
	        for(int i=0;i<sups.size();i++){
        		sup=sups.get(i);
        		//如果是标底或者咨询公司,则跳过
        		if(BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())){
        			continue;
        		}else{
        			//公司
    		        addCell(row,cell,text,9+(i*2)+1,style4,"1",sup.getSupName(),null);        		        
    		        addCell(row,cell,text,9+(i*2)+2,style4,"1","",null);
        		}
	         }
	        }
	        //第二行表头
	    row = sheet.createRow(1);
	    //设置行高为25
	    row.setHeightInPoints(25);
	    //循环新增6列填补空缺
	    for(int n=0;n<6;n++){
	        cell = row.createCell(n);
	        cell.setCellStyle(style4);
	    }
	    //算术平均的2个单元列
	    addCell(row,cell,text,6,style4,"1","单价",null);
	    addCell(row,cell,text,7,style4,"1","合价",null);
	    //标底的2个单元列
	    addCell(row,cell,text,8,style4,"1","单价",null);
	    addCell(row,cell,text,9,style4,"1","合价",null);
	    //合并单元格
	    mergeCell(sheet,address,0,1,1,1);
	    mergeCell(sheet,address,0,1,2,2);
	    mergeCell(sheet,address,0,1,3,3);
	    mergeCell(sheet,address,0,1,4,4);
	    mergeCell(sheet,address,0,1,5,5);
	    //如果供应商不为空
	    if(sups!=null&&sups.size()>0){
	    	//供应商个数
	       int i=0;
	       //循环供应商
	       for(BidSup sup:sups){
	          //如果是标底或者咨询公司,则跳过
	          if(BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())){
	        	 continue;
	        	}else{
	        		//根据循环个数，计算单元格的位置，并进行添加
        	        addCell(row,cell,text,9+1+(i*2),style4,"1","单价",null);
        	        addCell(row,cell,text,9+2+(i*2),style4,"1","合价",null);
        	        mergeCell(sheet,address,0,0,9+1+(i*2),9+2+(i*2));
        	        i++;
        		}
	          }
	        }
	    //生成表体数据    
        populateData(sheet,tmpDivisionVoList,style,style2,style3);
        try {
        	//将数据写入到EXCEL
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();  
			
		}finally{
			if(workbook!=null){
				workbook=null;
			}
		}
        
		
	}

	/**
	 * EXCEL表体数据生成
	 */
	private void populateData(HSSFSheet sheet, List<BidDivisitonVo> tmpDivisionVoList,HSSFCellStyle style,HSSFCellStyle style2,HSSFCellStyle style3){//(WritableWorkbook wbook, String sheetName, List<BidDivisitonVo> tmpDivisionVoList) throws WriteException{
			//excel行
			HSSFRow row = null;
			//字符单元格
			HSSFRichTextString text =null;
			//单元格
			HSSFCell cell=null;
		 for(int i=0;i<tmpDivisionVoList.size();i++){
			 //分析行数据
			 BidDivisitonVo vo=tmpDivisionVoList.get(i);
			 //创建i+2行
			 row=sheet.createRow(i+2);
			 //行高
			 row.setHeightInPoints(25);
			 //单元1
			 cell = row.createCell(0);
			 //设置格式
	         cell.setCellStyle(style2);
	         //设置序号i+1
	         cell.setCellValue(i+1);
	         
	         //项目编号
	         addCell(row,cell,text,1,style2,"1",vo.getItemNo(),null);
	         //项目名称
	         addCell(row,cell,text,2,style2,"1",vo.getItemName(),null);
	         //项目特征描述
	         addCell(row,cell,text,3,style2,"1",vo.getItemDesc(),null);
	         //计量单位
	         addCell(row,cell,text,4,style2,"1",vo.getMeasurement(),null);
	         //如果工程量不为空，则使用double型
	         if(vo.getQuantitie()!=null) {
	        	 //添加单元格
	        	 addCell(row,cell,text,5,style2,"2",vo.getQuantitie(),null);
			}
	       //如果工程量为空，则使用0
	         else{
				addCell(row,cell,text,5,style2,"2","",0.0);
			}
	         
	       //开始构建单位的单价和合价表头
			 if(vo.getBidSupCompareVos()!=null){
				 List<BidSupCompareVo> l=vo.getBidSupCompareVos();
				 BidSupCompareVo cvo=null;
				 for(int j=0;j<l.size();j++){
					 cvo=l.get(j);
					 //单价
			         if(cvo.getUnitPrice()!=null) {
			        	 addCell(row,cell,text,5+1+(j*2),style2,"2","",cvo.getUnitPrice().doubleValue());
			         }else{
			        	 addCell(row,cell,text,5+1+(j*2),style2,"2","",0.0);
			         }			         
			         //合价
			         if(cvo.getTotalPrice()!=null) {
			        	 addCell(row,cell,text,5+2+(j*2),style2,"2","",cvo.getTotalPrice().doubleValue());
					}else{
						addCell(row,cell,text,5+2+(j*2),style2,"2","",0.0);
					}
			         
				 }
			 }	 
		 }
		
	}

	/**
	 * 
	 * 根据搜索类型调用不同的构造SQL方法
	 * 
	 */
	public StringBuffer constructSql(String searchType,List<BidSup> sups,List<String> selecedProCols,BidSup biaodiSup) {
		//准备各供应商的轮次map值
		Map latestBatchNo=bidDivisionSupRelManager.parseBidSupLatestBatchNo(sups,this.getBatchNo());
		//加入标底的轮次号
		latestBatchNo.put(biaodiSup.getBidSupId(), this.getBatchNo());
		//如果是多个供应商对一个批次
		if ("sup".equals(searchType))
			return constructBatchToMultiSupSql(sups,selecedProCols,latestBatchNo,biaodiSup);
		else
			return constructSupToMultiBatchSql(sups,selecedProCols);			
	}

	/**
	 * 
	 * 拼接搜索一个批次对多个供应商的搜索sql
	 *
	 */
	public StringBuffer constructBatchToMultiSupSql(List<BidSup> sups,List<String> selecedProCols,Map latestBatchNo,BidSup biaodiSup) {
		
		//构建显示表头
		makeTableHeader(sups,selecedProCols,"sup",null);	
		// 构建搜索SQL
		StringBuffer sb = new StringBuffer();
		sb.append("select bd.item_no,bd.item_name,bd.item_desc,bd.unit_desc,bd.QUANTITIE qty, [biaodiCols] [selectCols]  from bid_divisiton bd ");
		sb.append(" join bid_project bp on bd.bid_project_id=bp.bid_project_id and bd.batch_no="+latestBatchNo.get(biaodiSup.getBidSupId()));
		sb.append(" and bp.bid_ledger_id='" + this.getBidLedgerId() +"' and bp.BID_PROJECT_ID = '"+bidProjectId+"' ");
		//加入国标帅选条件
		if(StringUtils.isNotBlank(itemIdsTemp)){
			sb.append(" and ( ");
			String[] idArr =itemIdsTemp.split(",");
			for(int i=0;i<idArr.length;i++){
				if(!"".equals(idArr[i])){
					sb.append(" bd.item_no  like '").append(idArr[i]).append("%' ");
				}
				if(i>=0&&i<idArr.length-1){
					sb.append(" or ");
				}
			}
			sb.append(")");
		}
		//sb.append(" join bid_ledger bl on bl.bid_ledger_id=bp.bid_ledger_id");
		StringBuffer selectCols = new StringBuffer();
		//构建标底搜索字段
		StringBuffer biaodiCols=new StringBuffer();
		
		//标底公司搜索字段构造		
		if(biaodiSup!= null){
			// 标底公司,加入搜索列字段
			if (StringUtils.isNotBlank(this.getSelectProperty())) {
				// 主表为true
				List<String> cols=parseSelectProperties(true,selecedProCols);
				for (String colName : cols) {
					biaodiCols.append("nvl(bdsrd." + colName + ",0) as " + colName + " ,");
				}
			} else {
				biaodiCols.append(" nvl(bdsrd.quantitie,0) as quantitie_,nvl(bdsrd.comp_unit_amt,0) as comp_unit_amt_,nvl(bdsrd.total_amt,0) as total_amt_,");
			}
			sb.append(" left join bid_division_sup_rel bdsrd" );
			sb.append(" on bdsrd.item_no=bd.item_no");
			sb.append(" and bdsrd.bid_sup_id='" + biaodiSup.getBidSupId() + "'");
			sb.append(" and bdsrd.batch_no=");			
			sb.append(latestBatchNo.get(biaodiSup.getBidSupId()));	
			sb.append(" and bdsrd.bid_project_id=bd.bid_project_id");
		}
				
		
		//非标底公司搜索字段构造	
		for (int i = 0; i < sups.size(); i++) {
			BidSup bs = sups.get(i);
			// 如果为标底公司则跳过
			if ("2".equals(bs.getTypeCd())) {
				continue;
			}
			// 表别名
			String tableAlas = "bdsr" + i;
			// 如果搜索的字段为选定的字段
			if (StringUtils.isNotBlank(this.getSelectProperty())) {
				// 子表为false
				List<String> cols=parseSelectProperties(false,selecedProCols);
				
				for (String colName : cols) {
					String column=tableAlas + "." + colName; 
					selectCols.append(" case when "+column+" is null then 0 else "+column + " end as " + colName + "_" + i + " ,");
				}
			}
			// 否则默认查出三个属性
			else {
				selectCols.append("nvl(bdsrd.quantitie,0) as quantitie_" + i + " ,nvl(" + tableAlas
						+ ".comp_unit_amt,0) as comp_unit_amt_" + i + " ,nvl(" + tableAlas + ".total_amt,0) as total_amt_" + i
						+ " ,");
			}
			sb.append(" left join bid_division_sup_rel " + tableAlas);
			sb.append(" on " + tableAlas + ".item_no=bd.item_no");
			sb.append(" and " + tableAlas + ".bid_sup_id='" + bs.getBidSupId() + "'");
			sb.append(" and "+tableAlas+".batch_no=");			
			sb.append(latestBatchNo.get(bs.getBidSupId()));	
			sb.append(" and "+tableAlas+".bid_project_id=bd.bid_project_id");

		}		
		// 标段ID
		sb.append( " order by bd.row_no ");		
		// 将标底的搜索列字符串替换入选择语句
		String strBiaodiCols=biaodiCols.toString();
		//如果没有选择供应商,则将最后一个字符","截取掉 
		if(selectCols.length()<1){
			strBiaodiCols=strBiaodiCols.substring(0, strBiaodiCols.length()-1);
		}		
		sb.replace(sb.indexOf("[biaodiCols]"), sb.indexOf("[biaodiCols]")+"[biaodiCols]".length(), strBiaodiCols);
		
		selectCols.append(" bd.row_no ");
		// 将非标底的搜索列字符串替换入选择语句
		sb.replace(sb.indexOf("[selectCols]"), sb.indexOf("[selectCols]")+"[selectCols]".length(), 
					selectCols.toString().substring(0, selectCols.toString().length() - 1));
		return sb;

	}
	
	/**
	 * 
	 * 添加算术平均值第一表头列
	 */
	public void addColumn(StringBuffer sbTableHeader,String colName,int headLevel){
		//宽度为158
		int width=158;
		sbTableHeader.append("<th colspan='");			
		//如果没有选择列,则默认为2列
		if(this.getSelectProperty()==null) {
			//设置colspan=2
			sbTableHeader.append("2");
		}else{//列宽			
			if(this.getFieldCount()>0){
				sbTableHeader.append(this.getFieldCount());
				width=79*this.getFieldCount();
			}else{
				//设置colspan=2
				sbTableHeader.append("2");
			}
		}
		sbTableHeader.append("' nowrap='nowrap'    title='"+colName+"'><div style='width:"+width+"px;overflow:hidden;line-height: 24px;'>"+colName+"</div></th>");
	}
	
	/**
	 * 
	 * 添加算术平均的第二个表头
	 */
	public void addSecColumn(List<String> selecedProCols,StringBuffer sbTableHeader){
		if(this.getFieldCount()>0){			
			for(String col:selecedProCols){
				//<th>工程量</th>
				sbTableHeader.append("<th class='ellipsisDiv' nowrap='nowrap' style='width:79px;padding-right:0px' >"+changeColForTableHeader(col)+"</th>");
			}		
		}else{
			//sbTableHeader.append("<th  nowrap='nowrap' style='width:79px;padding-right:0px;' title='工程量'><div style='width:79px;overflow:hidden;line-height: 24px;'>工程量</div></th>");
			sbTableHeader.append("<th class='ellipsisDiv'  nowrap='nowrap' style='width:79px;padding-right:0px;'><div style='width:79px;overflow:hidden;line-height: 24px;'>&nbsp单价&nbsp</div></th>");
			sbTableHeader.append("<th  class='ellipsisDiv' nowrap='nowrap' style='width:79px;padding-right:0px;'><div style='width:79px;overflow:hidden;line-height: 24px;'>&nbsp合价&nbsp</div></th>");
		}
	}

	/**
	 * 
	 *根据不同情况构建显示表头
	 */
	private void makeTableHeader(List<BidSup> sups,List<String> selecedProCols,String type,String[] batchNubers) {
		//表头1
		StringBuffer sbTableHeader1=null;
		//表头2
		StringBuffer sbTableHeader2=null;
		//表头1
		sbTableHeader1= new StringBuffer();
		//表头2
		sbTableHeader2 = new StringBuffer();
		//添加算术平均值第一表头列
		addColumn(sbTableHeader1,"算术平均值",1);		
		//算术平均的第二个表头
		addSecColumn(selecedProCols,sbTableHeader2);		
		//如果是一个批次对多个供应商
		if("sup".equals(type)){
			//添加标底公司列
			addColumn(sbTableHeader1,"标底公司",1);	
			//根据供应商循环列
			for(BidSup sup:sups){
				//明标
				if("1".equals(sup.getBidLedger().getVisableFlg())) {
					addColumn(sbTableHeader1,sup.getSupName(),1);
				}else{
					//暗标
					//因为暗标也需要显示，注销
//					addColumn(sbTableHeader1,StringUtils.leftPad(sup.getDispOrderNo().toString(),3,"0"),1);	
					addColumn(sbTableHeader1,sup.getSupName(),1);
				}				
			}			
			//标底公司
			addSecColumn(selecedProCols,sbTableHeader2);
			//根据供应商循环选择列列
			for(BidSup sup:sups){
				addSecColumn(selecedProCols,sbTableHeader2);
			}			
		}else{
			if(batchNubers!=null){
				//供应商名称
				String supName="";
				if(sups!=null&&sups.size()>0){
					supName=sups.get(0).getSupName();
				}
				for(String batchNo:batchNubers){
					addColumn(sbTableHeader1,supName+"第"+batchNo+"次",1);
					//添加第二行表头
					addSecColumn(selecedProCols,sbTableHeader2);					
				}
			}
		}
			
		tableHeader1=sbTableHeader1.toString();
		tableHeader2=sbTableHeader2.toString(); 
		
		
	}

	/**
	 * 
	 * 拼接搜索一个供应商对多个批次的搜索sql
	 */
	public StringBuffer constructSupToMultiBatchSql(List<BidSup> sups,List<String> selecedProCols) {
		
		// 如果是一个供应商对多个批次，根据选择的批次,构建投标供应商定制字段,如果搜索的字段为选定的字段 
		String[] batchNubers = parseBatchNo(this.getBatchNo());
		// 构建显示表头
		makeTableHeader(sups,selecedProCols,"batchNo",batchNubers);	
		// 构建搜索语句
		StringBuffer sql = new StringBuffer();
		sql.append("select [selectCols]  from bid_divisiton bd ");
		sql.append("  join bid_project bp on bd.bid_project_id=bp.bid_project_id");
		sql.append("  join bid_ledger bl on bl.bid_ledger_id=bp.bid_ledger_id");
		StringBuffer selectCols = new StringBuffer().append(" bd.item_no,bd.item_name,bd.item_desc,bd.unit_desc,");		
		// 批次参数
		String batchN = "";
		if (batchNubers != null) {
			for (int i = 0; i < batchNubers.length; i++) {
				if (StringUtils.isNotBlank(batchNubers[i])) {
					batchN = batchNubers[i];					
					// 表别名
					String tableAlas = "bdsr" + i;
					// 如果供应商的选择列不为空
					if (StringUtils.isNotBlank(this.getSelectProperty())) {
						// 解析搜索的列参数,主表为true,子表为false
						List<String> cols = parseSelectProperties(false,selecedProCols);
						// 拼写供应商定制搜索列
						for (String colName : cols) {
							String column=tableAlas + "." + colName;
							selectCols.append(" case when "+column+" is null then 0 else "+column + " end as " + colName + "_" + i + " ,");
						}
					} else {// 如果供应商的选择列为空,则默认查出三个属性
						selectCols.append(tableAlas + ".quantitie as quantitie_" + i + " ,");
						selectCols.append(tableAlas + ".comp_unit_amt as comp_unit_amt_" + i + " ,");
						selectCols.append(tableAlas + ".total_amt as total_amt_" + i + " ,");
					}
					//join语句继续拼接
					sql.append(" left join bid_division_sup_rel " + tableAlas);
					sql.append(" on " + tableAlas + ".bid_divisiton_id=bd.bid_divisiton_id");
					sql.append(" and " + tableAlas + ".bid_sup_id='" + this.getSupIds() + "'");
					sql.append(" and " + tableAlas + ".batch_no=" + batchN);
				}
			}
		}
		//表段
		sql.append(" where bl.bid_ledger_id='" + this.getBidLedgerId() + "'");
		// 替换选择列
		int start=sql.indexOf("[selectCols]");
		int end=start+"[selectCols]".length();
		sql.replace(start, end, selectCols.toString().substring(0, selectCols.toString().length() - 1));
		return sql;
	}
	/**
	 * 
	 *根据结果集构建回标分析的显示VO
	 */
	private BidDivisitonVo makeBidDivisitonVo(Object[] result, List<BidSupCompareVo> colList, BidDivisitonVo rowVo,
			BidSupCompareVo colVo, Integer choosedProsNum, String makeType,List<String> selecedProCols,List<BidSup> listBidSups) {
		//时间格式
		df = this.getDf();
		//回标分析的显示VO
		rowVo = new BidDivisitonVo();
		//项目编号
		rowVo.setItemNo((String) result[0]);
		//项目名称
		rowVo.setItemName((String) result[1]);
		//项目描述
		rowVo.setItemDesc((String) result[2]);
		//单位
		rowVo.setMeasurement((String) result[3]);
		//工程量
		rowVo.setQuantitie((String)result[4]);
		//各个单位的原子显示数据VO
		colList = new ArrayList<BidSupCompareVo>();
		//工程量求和
		BigDecimal quantitieTotal = BigDecimal.ZERO;
		//单价求和
		BigDecimal unitPriceTotal = BigDecimal.ZERO;
		//总价求和
		BigDecimal totalPriceTotal = BigDecimal.ZERO;
		// 以多个供应商对应一个批次
		if ("sup".equals(makeType)) {
			//--------start 构建标底数据列----------
			colVo = new BidSupCompareVo();
			colVo.setHeadName("标底数据");
			colVo.setHeadType("1");	
			// 如果为定制列则需要取的下标
			if (StringUtils.isNotBlank(this.getSelectProperty())) {
				//以选择列开始循环
				for(int i=0;i<selecedProCols.size();i++){
					//按次序得到列名
					String col=selecedProCols.get(i);
					//如果为工程量
					if(StringUtils.isNotBlank(col)&&"quantitie".equals(col)){
						if (5 + i < result.length && result[5 + i] != null) {
							colVo.setQuantitie((BigDecimal)result[5 + i]);
							quantitieTotal = quantitieTotal.add(colVo.getQuantitie());
						}					
					}else
					//如果为单价
					if(StringUtils.isNotBlank(col)&&"unitPrice".equals(col)){
						if (5 + i < result.length && result[5 + i] != null) {
							colVo.setUnitPrice((BigDecimal)result[5 + i]);
							unitPriceTotal = unitPriceTotal.add(colVo.getUnitPrice());
						}					
					}else
					//如果为合价
					if(StringUtils.isNotBlank(col)&&"totalPrice".equals(col)){
						if (5 + i < result.length && result[5 + i] != null) {
							colVo.setTotalPrice((BigDecimal)result[5 + i]);
							totalPriceTotal = totalPriceTotal .add(colVo.getTotalPrice());
						}					
					}
				}
			}else{
				colVo.setQuantitie((BigDecimal)result[5]);
				quantitieTotal = quantitieTotal.add(colVo.getQuantitie());
				colVo.setUnitPrice((BigDecimal)result[5 +1]);
				unitPriceTotal = unitPriceTotal.add(colVo.getUnitPrice());
				colVo.setTotalPrice((BigDecimal)result[5 +2]);
				totalPriceTotal = totalPriceTotal .add(colVo.getTotalPrice());
			}
			colList.add(colVo);
			//--------end 构建标底数据列----------
			
			//--------start 构建供应商数据列----------
			//乘数
			int multor=0;
			for (int k = 0; k < listBidSups.size(); k++) {
				BidSup sup = listBidSups.get(k);
				//标底公司则跳过 
				if (sup != null && BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {
					continue;
				}
				colVo = new BidSupCompareVo();				
				colVo.setHeadName(sup.getSupName());
				colVo.setHeadType("1");
				colVo.setSupName(sup.getSupName());
					//如果定制了选择列
					if (StringUtils.isNotBlank(this.getSelectProperty())) {
						// 以选择列开始循环
						for(int i=0;i<selecedProCols.size();i++){
							String col=selecedProCols.get(i);
							//如果为工程量
							if(StringUtils.isNotBlank(col)&&"quantitie".equals(col)){
								if (5 +choosedProsNum+ multor * choosedProsNum +i< result.length && result[5 +choosedProsNum+ multor * choosedProsNum +i] != null) {
									colVo.setQuantitie((BigDecimal) result[5 +choosedProsNum+ multor * choosedProsNum +i]);
									quantitieTotal = quantitieTotal.add(colVo.getQuantitie());
								}
							}else
							//如果为单价
							if(StringUtils.isNotBlank(col)&&"unitPrice".equals(col)){
								if (5 +choosedProsNum+ multor * choosedProsNum +i < result.length && result[5 +choosedProsNum+ multor * choosedProsNum +i] != null) {
									colVo.setUnitPrice((BigDecimal) result[5 +choosedProsNum+ multor * choosedProsNum +i]);
									unitPriceTotal = unitPriceTotal.add(colVo.getUnitPrice());
								}
							}else
							//如果为合价
							if(StringUtils.isNotBlank(col)&&"totalPrice".equals(col)){
								if (5 +choosedProsNum+ multor * choosedProsNum +i < result.length && result[5 +choosedProsNum+ multor * choosedProsNum +i] != null) {
									colVo.setTotalPrice((BigDecimal) result[5 +choosedProsNum+ multor * choosedProsNum +i]);
									totalPriceTotal = totalPriceTotal .add(colVo.getTotalPrice());
								}else{
									colVo.setTotalPrice(BigDecimal.ZERO);
								}
							}
						}
					}else {// 如果没选择列,默认取三列的值（未选取列的情况）
						//TODO
						//工程量
						if (result[8 + multor * 3] != null) {
							colVo.setQuantitie((BigDecimal) result[8 + multor * 3 ]);
							//工程量加
							quantitieTotal = quantitieTotal.add(colVo.getQuantitie());
						} else {
							colVo.setQuantitie(BigDecimal.ZERO);
						}
						
						//单价
						if (result[8 + k * 3 + 1] != null) {
							colVo.setUnitPrice((BigDecimal) result[8 + k * 3 + 1]);
							//单价加
							unitPriceTotal = unitPriceTotal.add(colVo.getUnitPrice());
						} else {
							colVo.setUnitPrice(BigDecimal.ZERO);
						}
						
						//合价
						if (result[8 + k * 3 + 2] != null) {
							colVo.setTotalPrice((BigDecimal) result[8 + k * 3 + 2]);
							//合价加
							totalPriceTotal = totalPriceTotal .add(colVo.getTotalPrice());
						} else {
							colVo.setTotalPrice(BigDecimal.ZERO);
						}
						
					}
				colList.add(colVo);
				multor++;
			}
			//--------end 构建供应商数据列----------
		}
		String[] batchNubers =null;
		// 以多个批次对应一个供应商
		if ("batchNo".equals(makeType)) {
			batchNubers= parseBatchNo(this.getBatchNo());
			if (batchNubers != null) {
				for (int k = 0; k < batchNubers.length; k++) {
					colVo = new BidSupCompareVo();
					if (listBidSups != null && listBidSups.size() > 0) {
						BidSup sup = listBidSups.get(0);
						if (sup != null) {
							colVo.setSupName(sup.getSupName());
						}
					}
					colVo = new BidSupCompareVo();
					colVo.setHeadName("第" + k + "次");
					colVo.setHeadType("第" + k + "次");
					//如果定制了选择列
					if (StringUtils.isNotBlank(this.getSelectProperty())) {
						// 以选择列开始循环
						for(int i=0;i<selecedProCols.size();i++){
							String col=selecedProCols.get(i);
							//如果为工程量
							if(StringUtils.isNotBlank(col)&&"quantitie".equals(col)){
								if (4 + k * choosedProsNum +i< result.length && result[4 + k * choosedProsNum +i] != null) {
									colVo.setQuantitie((BigDecimal) result[4 + k * choosedProsNum +i]);
									quantitieTotal = quantitieTotal.add(colVo.getQuantitie());
								}
							}else
							//如果为单价
							if(StringUtils.isNotBlank(col)&&"unitPrice".equals(col)){
								if (4 + k * choosedProsNum +i < result.length && result[4 + k * choosedProsNum +i] != null) {
									colVo.setUnitPrice((BigDecimal) result[4 + k * choosedProsNum +i]);
									unitPriceTotal = unitPriceTotal.add(colVo.getUnitPrice());
								}
							}else
							//如果为合价
							if(StringUtils.isNotBlank(col)&&"totalPrice".equals(col)){
								if (4 + k * choosedProsNum +i < result.length && result[4 + k * choosedProsNum +i] != null) {
									colVo.setTotalPrice((BigDecimal) result[4 + k * choosedProsNum +i]);
									totalPriceTotal = totalPriceTotal .add(colVo.getTotalPrice());
								}else{
									colVo.setTotalPrice(BigDecimal.ZERO);
								}
							}
						}
					}else {// 如果没选择列,默认取三列的值（未选取列的情况）
						//TODO
						//工程量
						if (result[4 + k * 3] != null) {
							colVo.setQuantitie((BigDecimal) result[4 + k * 3 ]);
							//工程量加
							quantitieTotal = quantitieTotal.add(colVo.getQuantitie());
						} else {
							colVo.setQuantitie(BigDecimal.ZERO);
						}
						
						//单价
						if (result[4 + k * 3 + 1] != null) {
							colVo.setUnitPrice((BigDecimal) result[4 + k * 3 + 1]);
							//单价加
							unitPriceTotal = unitPriceTotal.add(colVo.getUnitPrice());
						} else {
							colVo.setUnitPrice(BigDecimal.ZERO);
						}
						
						//合价
						if (result[4 + k * 3 + 2] != null) {
							colVo.setTotalPrice((BigDecimal) result[4 + k * 3 + 2]);
							//合价加
							totalPriceTotal = totalPriceTotal .add(colVo.getTotalPrice());
						} else {
							colVo.setTotalPrice(BigDecimal.ZERO);
						}						
					}
					colList.add(colVo);
				}
			}

		}
		// 算术平均值
		colVo = new BidSupCompareVo();
		colVo.setHeadName("算术平均");
		colVo.setHeadType("10");
		// 以多个供应商对应一个批次
		if ("sup".equals(makeType)) {
			if(listBidSups!=null&&listBidSups.size()>0){				
				colVo.setQuantitie(quantitieTotal.divide(BigDecimal.valueOf(listBidSups.size()+1), 1, BigDecimal.ROUND_HALF_UP));
				colVo.setUnitPrice(unitPriceTotal.divide(BigDecimal.valueOf(listBidSups.size()+1), 1, BigDecimal.ROUND_HALF_UP));
				colVo.setTotalPrice(totalPriceTotal .divide(BigDecimal.valueOf(listBidSups.size()+1), 1, BigDecimal.ROUND_HALF_UP));
			}
			
		}else{
			if(batchNubers!=null&&batchNubers.length>1) {
				colVo.setQuantitie(quantitieTotal.divide(BigDecimal.valueOf(batchNubers.length), 1, BigDecimal.ROUND_HALF_UP));
				colVo.setUnitPrice(unitPriceTotal.divide(BigDecimal.valueOf(batchNubers.length), 1, BigDecimal.ROUND_HALF_UP));
				colVo.setTotalPrice(totalPriceTotal.divide(BigDecimal.valueOf(batchNubers.length), 1, BigDecimal.ROUND_HALF_UP));
			}
		}
		
		colList.add(0, colVo);
		rowVo.setBidSupCompareVos(colList);
		return rowVo;
	}
	
	/**
	 * 搜索语句接受邀请的供应商
	 */
	public List<BidSup> filterBidDivisiton(BidLedger bidLedger) {
		List<BidSup> bidSups = new ArrayList<BidSup>();
		if (bidLedger != null) {
			bidSups = bidSupManager.getReceivedSup(bidLedger.getBidSups());			
		}
		return bidSups;

	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public static void main(String args[]) {

		BidAnalysisAction ba = new BidAnalysisAction();
		ba.quantityAnalysis();
	}

	public List<BidSup> getBss() {
		return bss;
	}

	public void setBss(List<BidSup> bss) {
		this.bss = bss;
	}

	public List<BidProject> getBidPros() {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		bidPros = bidProjectManager.find(filters);
		return bidPros;
	}

	public void setBidPros(List<BidProject> bidPros) {
		this.bidPros = bidPros;
	}

	public Integer getSupNameNumber() {
		return supNameNumber;
	}

	public void setSupNameNumber(Integer supNameNumber) {
		this.supNameNumber = supNameNumber;
	}

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public String getBidPorjectId() {
		return bidPorjectId;
	}

	public void setBidPorjectId(String bidPorjectId) {
		this.bidPorjectId = bidPorjectId;
	}

	public List<Object[]> getBidDivisionVoList() {
		return bidDivisionVoList;
	}

	public void setBidDivisionVoList(List<Object[]> bidDivisionVoList) {
		this.bidDivisionVoList = bidDivisionVoList;
	}

	public List<TotalPriceVo> getTpVoList() {
		return tpVoList;
	}

	public void setTpVoList(List<TotalPriceVo> tpVoList) {
		this.tpVoList = tpVoList;
	}

	public List<BidDivisitonVo> getDivisionVoList() {
		return divisionVoList;
	}

	public void setDivisionVoList(List<BidDivisitonVo> divisionVoList) {
		this.divisionVoList = divisionVoList;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getSupIds() {
		return supIds;
	}

	public void setSupIds(String supIds) {
		this.supIds = supIds;
	}

	public String getSelectProperty() {
		return selectProperty;
	}

	public void setSelectProperty(String selectProperty) {
		this.selectProperty = selectProperty;
	}

	public int getFieldCount() {
		if (StringUtils.isNotBlank(selectProperty))
			return this.selectProperty.trim().split(",").length;
		return 0;
	}
	
	public int getActCouNum(){
		if (StringUtils.isNotBlank(selectProperty))
			return this.selectProperty.trim().split(",").length;
		else
			return 2;
		
	}

	public String[] getBatchNos() {
		if (StringUtils.isNotBlank(batchNo))
			return this.batchNo.trim().split(",");
		return null;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getBibSupName() {
		if (bss != null && bss.size() > 0)
			return bss.get(0).getSupName();
		return "未知";
	}

	public DecimalFormat getDf() {
		return new DecimalFormat("###.00");
	}

	public void setDf(DecimalFormat df) {
		this.df = df;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	
	

	public Page getVoPage() {
		return voPage;
	}

	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	public Integer getColNumber() {
		return colNumber;
	}

	public void setColNumber(Integer colNumber) {
		this.colNumber = colNumber;
	}

	public String getTableHeader1() {
		return tableHeader1;
	}

	public void setTableHeader1(String tableHeader1) {
		this.tableHeader1 = tableHeader1;
	}

	public String getTableHeader2() {
		return tableHeader2;
	}

	public void setTableHeader2(String tableHeader2) {
		this.tableHeader2 = tableHeader2;
	}
	

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	/**
	 * 搜索某项目对应的所有标段
	 * 
	 * @param bidProjectCd
	 *            项目公司cd
	 * @return
	 */
	public String listSection() {
		return "listSection";
	}

	public static Map<String, String> getMapSection() {
		BidLedgerManager bidLedgerManager = SpringContextHolder.getBean("bidLedgerManager");
		String bidOrgCd = Struts2Utils.getParameter("bidOrgCd");
		List tmpList = bidLedgerManager.getLedgerListByOrgCd(bidOrgCd);
		Map<String, String> mapSection = new LinkedHashMap<String, String>();
		mapSection.put("", "");
		BidLedger tmp = null;
		if (tmpList != null) {
			for (int i = 0; i < tmpList.size(); i++) {
				tmp = (BidLedger) tmpList.get(i);
				mapSection.put(tmp.getBidLedgerId(), tmp.getBidSectionName());
			}
		}
		return mapSection;
	}

	/**
	 * 搜索回标分析单位
	 * 
	 * @param bidLedgerId
	 * @param typeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadBidAnalysis() throws Exception {
		if (StringUtils.isBlank(typeCd)) {
			typeCd = "1";
		}

		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			bss = filterBidDivisiton(bidLedger);
		}
		return "list";
	}

	public List<SummaryAnalysisVo> getSummaryVoList() {
		return summaryVoList;
	}

	public void setSummaryVoList(List<SummaryAnalysisVo> summaryVoList) {
		this.summaryVoList = summaryVoList;
	}
	
	/**
	 * 
	 * getListBidLedgers:(根据项目CD搜索标段)
	 *  
	 * @param  @return    设定文件  
	 * @return List<BidLedger>    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public List<BidLedger> getListBidLedgers() {		
		return listBidLedgers;
	}

	public void setListBidLedgers(List<BidLedger> listBidLedgers) {
		this.listBidLedgers = listBidLedgers;
	}
	
	/**
	 * 
	 * 搜索构建平方米指标,和占总价比例分析分析的数据行结果集
	 */
	public void makeTotalPriceVoRows(List <BidSup> listBidSup,BidLedger bidLedger,Map<String, Object> map){
		TotalPriceVo totalPriceVo=null;		
		//标底公司
		BidSup sup2=null;
		//获取标底公司
		for(BidSup sup:listBidSup){
			if (bidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {	
				sup2=sup;
				break;
			}
		}
		//搜索结果集
		List<Object[]> rs=null;
		//转换后的显示结果集
		tpVoList=new ArrayList<TotalPriceVo>();
		//根据供应商解析轮次，准备各供应商的轮次map值
		Map latestBatchNo=bidDivisionSupRelManager.parseBidSupLatestBatchNo(listBidSup,this.getBatchNo());
		//循环供应商进行封装数据
		for(BidSup sup:listBidSup){
			if (bidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {	
				continue;
			}else{
				//sup 供应商
				//sup2未标的单位
				//投标轮次
				String batchNumber=latestBatchNo.get(sup.getBidSupId()).toString();
				//根据语句搜索
				rs=bidLedgerManager.findBySql(bidLedgerManager.makePriceTotalSql(bidLedger.getBidLedgerId(),sup,sup2,batchNumber).toString(),map);
				if(rs!=null&&rs.size()>0){
					totalPriceVo=new TotalPriceVo();
					BigDecimal bidDivisonCustTotal=bidDivisionSupRelManager.fetchBidDivisitonCustTotal(sup,batchNumber);
					Object[] o=rs.get(0);
					BigDecimal divisionPercent = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
					BigDecimal measureOnePercent = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
					BigDecimal measureTwoPercent = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
					BigDecimal totalArea=((BigDecimal)o[2]).setScale(2,BigDecimal.ROUND_HALF_UP);
					//明标
					if("1".equals(sup.getBidLedger().getVisableFlg())) {
						totalPriceVo.setSupName(sup.getSupName());
					}else{
						//暗标
						//totalPriceVo.setSupName(StringUtils.leftPad(sup.getDispOrderNo().toString(),3,"0"));	
						totalPriceVo.setSupName(sup.getSupName());
					}
					//totalPriceVo.setSupName(sup.getSupName());
					//参考价
					totalPriceVo.setRefPrice((BigDecimal)o[0]);
					//添加定制的分部分项的总报价
					totalPriceVo.setSupTotalPrice(bidDivisonCustTotal.add((BigDecimal)o[1]));	
					//添加定制的分部分项的数据汇总					
					totalPriceVo.setDivisionTotalPrice(bidDivisonCustTotal.add((BigDecimal)o[3]));
					//措施总价
					totalPriceVo.setMeasureOneTotalPrice((BigDecimal)o[4]);
					//其他项目清单报价
					totalPriceVo.setMeasureTwoTotalPrice((BigDecimal)o[5]);
					//面积价格计算
					if(totalArea!=null&&totalArea.doubleValue()>0.0){
						totalPriceVo.setPerSupTotalPrice(totalPriceVo.getSupTotalPrice().divide(totalArea,  2,
								BigDecimal.ROUND_HALF_UP));
						totalPriceVo.setPerMeterdivisionPrice(totalPriceVo.getDivisionTotalPrice().divide(totalArea,  2,
								BigDecimal.ROUND_HALF_UP));
						totalPriceVo.setPerMeterMeasureOnePrice(totalPriceVo.getMeasureOneTotalPrice().divide(totalArea,2,
								BigDecimal.ROUND_HALF_UP));
						totalPriceVo.setPerMetermeasureTwoPrice(totalPriceVo.getMeasureTwoTotalPrice().divide(totalArea,  2,
								BigDecimal.ROUND_HALF_UP));
					}else{
						totalPriceVo.setPerSupTotalPrice(BigDecimal.ZERO);
						totalPriceVo.setPerMeterdivisionPrice(BigDecimal.ZERO);
						totalPriceVo.setPerMeterMeasureOnePrice(BigDecimal.ZERO);
						totalPriceVo.setPerMetermeasureTwoPrice(BigDecimal.ZERO);
					}
					//百分比计算
					//分部分项
					if(!totalPriceVo.getDivisionTotalPrice().equals(BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP))){
						divisionPercent = (totalPriceVo.getDivisionTotalPrice()).divide(totalPriceVo.getSupTotalPrice(), 4,
								BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
						totalPriceVo.setDivisionPercent(divisionPercent);
					}
					//措施
					if(!totalPriceVo.getMeasureOneTotalPrice().equals(BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP))){
						measureOnePercent = (totalPriceVo.getMeasureOneTotalPrice()).divide(totalPriceVo.getSupTotalPrice(), 4,
								BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
						totalPriceVo.setMeasureOnePercent(measureOnePercent);
					}
					//其他
					if(!totalPriceVo.getMeasureTwoTotalPrice().equals(BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP))){
						measureTwoPercent = (totalPriceVo.getMeasureTwoTotalPrice()).divide(totalPriceVo.getSupTotalPrice(), 4,
								BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
						totalPriceVo.setMeasureTwoPercent(measureTwoPercent);
					}
					tpVoList.add(totalPriceVo);
				}
			}
			
		}
	}
	
	/**
	 * 
	 * 平方米指标统计搜索
	 */
	public String perMeterAnalysis() {
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//校验是否存在标底公司
			if(!bidSupManager.hasBiaoDiSup(this.getBidLedgerId())) {
				Struts2Utils.renderText("<font color='red'>标段不存在标底单位！</font>");
				return null;
			}
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			bss = filterBidDivisiton(bidLedger);
			Map<String, Object> map = new HashMap<String, Object>();	
			//构建平方米指标统计搜索显示列表
			makeTotalPriceVoRows(bss,bidLedger,map);

		}
		return "perMeterAnalysis";
	}
	/**
	 * 
	 * perMeterAnalysis:(清单轮次差异分析搜索/目前只进行双轮次比较)
	 *  
	 * @param  @return    设定文件  
	 * @return String    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public String bidDivisitAnaly() {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer();
		if(StringUtils.isNotBlank(batchNo)){
			String[] idArr =batchNo.split(",");
			batchOne =idArr[0];
			batchTwo =idArr[1];
			map.put("batchOne", Long.parseLong(batchOne));
			map.put("batchTwo", Long.parseLong(batchTwo));
		}
		if(StringUtils.isNotBlank(bidProjectId)){
			map.put("bidProjectId", bidProjectId);
			hql.append(" from BidDivisiton where ( batchNo=:batchOne ");
			hql.append(" and bidProject.bidProjectId=:bidProjectId ");
			hql.append(" and itemNo not in ( select itemNo from BidDivisiton where batchNo=:batchTwo and bidProject.bidProjectId=:bidProjectId ) ");
			hql.append(" ) ");
			hql.append(" or ");
			hql.append(" ( batchNo=:batchTwo and bidProject.bidProjectId=:bidProjectId and itemNo not in ( select itemNo from BidDivisiton where batchNo=:batchOne and bidProject.bidProjectId=:bidProjectId ))");
//			hql.append(" order by itemNo,BidDivisitonId ");
		}
		divisionList = bidDivisitonManager.findPage(divisionList, hql.toString(), map);
		return "bidDivisitAnaly";
	}
	/**
	 * 
	 * perMeterAnalysis:(标段工程汇总)
	 *  
	 * @param  @return    设定文件  
	 * @return String    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public String bidProjectAnaly() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bidLedgerId", bidLedgerId);
		param.put("bidSupId", bidSupId);
		param.put("batchNo", batchNo);
		StringBuffer bidMeasureSql = new StringBuffer()
				.append(" select p.project_name,nvl(g.amtg,0), nvl(a.amt,0), nvl(b.amt2,0),nvl(c.amtc,0),nvl(d.amtd,0),nvl(f.amtf,0),nvl(e.amte,0) from bid_project p left join  ");
		// 分部分项
		bidMeasureSql.append("(");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(bdsr.total_AMT),0) amtg from BID_DIVISION_SUP_REL bdsr ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=bdsr.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId and bdsr.BID_SUP_ID=:bidSupId and bdsr.batch_no=:batchNo group by bp.BID_PROJECT_ID ");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" g on p.BID_PROJECT_ID=g.BID_PROJECT_ID left join ");
		// 措施项目清单计价表(一)统计
		bidMeasureSql.append("(");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(br.AMT),0) amt from Bid_Measure_Sup_Rel br ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=br.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId and br.BID_SUP_ID=:bidSupId  and br.batch_no=:batchNo group by bp.BID_PROJECT_ID ");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" a on p.BID_PROJECT_ID=a.BID_PROJECT_ID left join ");
		// 措施项目清单计价表(二)统计
		bidMeasureSql.append(" ( ");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(br2.TOTAL_AMT),0) amt2 from Bid_Measure_Sup_Rel2 br2 ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=br2.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId  and br2.BID_SUP_ID=:bidSupId  and br2.batch_no=:batchNo group by bp.BID_PROJECT_ID");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" b on p.BID_PROJECT_ID=b.BID_PROJECT_ID left join ");
		// 其他项目清单与计价汇总表
		bidMeasureSql.append(" ( ");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(boir.AMT),0) amtc from BID_OTHER_ITEM_REL boir ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=boir.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId and boir.BID_SUP_ID=:bidSupId  and boir.batch_no=:batchNo group by bp.BID_PROJECT_ID ");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" c on p.BID_PROJECT_ID=c.BID_PROJECT_ID left join ");
		// 零星工程费用表
		bidMeasureSql.append(" ( ");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(bssr.total_AMT),0) amtd from BID_SPORADIC_SUP_REL bssr ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=bssr.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId and bssr.BID_SUP_ID=:bidSupId  and bssr.batch_no=:batchNo group by bp.BID_PROJECT_ID ");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" d on p.BID_PROJECT_ID=d.BID_PROJECT_ID left join ");
		//规费项目
		bidMeasureSql.append(" ( ");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(bfsr.AMT),0) amtf from BID_FEES_SUP_REL bfsr ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=bfsr.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId and bfsr.BID_SUP_ID=:bidSupId  and bfsr.batch_no=:batchNo group by bp.BID_PROJECT_ID ");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" f on p.BID_PROJECT_ID=f.BID_PROJECT_ID left join ");
		// 税金项目清单与计价
		bidMeasureSql.append(" ( ");
		bidMeasureSql.append(" select bp.BID_PROJECT_ID,nvl(sum(btsr.AMT),0) amte from BID_TAXS_SUP_REL btsr ");
		bidMeasureSql.append(" left join bid_project bp on bp.BID_PROJECT_ID=btsr.BID_PROJECT_ID ");
		bidMeasureSql.append(" where bp.bid_ledger_id =:bidLedgerId and btsr.BID_SUP_ID=:bidSupId  and btsr.batch_no=:batchNo group by bp.BID_PROJECT_ID ");
		bidMeasureSql.append(" ) ");
		bidMeasureSql.append(" e on p.BID_PROJECT_ID=e.BID_PROJECT_ID ");
		bidMeasureSql.append(" where p.bid_ledger_id =:bidLedgerId order by  p.project_name asc");
		List bidAllLis = bidLedgerManager.findBySql(bidMeasureSql.toString(), param);
		bidProjectAllVoList=proTotalColl(bidAllLis);
		return "bidProjectAnaly";
	}
	/**
	 * 项目报价汇总表
	 * 
	 * @param list
	 * @param lists
	 * @return
	 */
	private List<BidProjectAllVo> proTotalColl(List bidAllLis){
		List<BidProjectAllVo> proTotalList =new ArrayList<BidProjectAllVo>();
		BidProjectAllVo prototal =null;
		for(int i=0;i<bidAllLis.size();i++){
			Object[] obj =(Object[])bidAllLis.get(i);
			prototal =new BidProjectAllVo();
				prototal.setProjectName((String)obj[0]);
				prototal.setAmtOne((BigDecimal)obj[1]);
				prototal.setAmtTwo((BigDecimal)obj[2]);
				prototal.setAmtThree((BigDecimal)obj[3]);
				prototal.setAmtFour((BigDecimal)obj[4]);
				prototal.setAmtFive((BigDecimal)obj[5]);
				prototal.setAmtSix((BigDecimal)obj[6]);
				prototal.setAmtSeven((BigDecimal)obj[7]);
				proTotalList.add(prototal);
		}
		return proTotalList;
	}
	
	/**
	 * 
	 * 占总价比例分析
	 */
	public String totalPriceAnalysis() {
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//校验是否存在标底公司
			if(!bidSupManager.hasBiaoDiSup(this.getBidLedgerId())) {
				Struts2Utils.renderText("<font color='red'>标段不存在标底单位！</font>");
				return null;
			}
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			//获取应标的供应商
			bss = filterBidDivisiton(bidLedger);
			Map<String, Object> map = new HashMap<String, Object>();	
			//构建占总价比例分析显示列表
			makeTotalPriceVoRows(bss,bidLedger,map);
		}		
		return "totalPriceAnalysis";
	}

	public String getBidProjectId() {
		return bidProjectId;
	}

	public void setBidProjectId(String bidProjectId) {
		this.bidProjectId = bidProjectId;
	}

	public String getItemIdsTemp() {
		return itemIdsTemp;
	}

	public void setItemIdsTemp(String itemIdsTemp) {
		this.itemIdsTemp = itemIdsTemp;
	}

	public Page<BidDivisiton> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(Page<BidDivisiton> divisionList) {
		this.divisionList = divisionList;
	}

	public String getBatchTwo() {
		return batchTwo;
	}

	public void setBatchTwo(String batchTwo) {
		this.batchTwo = batchTwo;
	}

	public String getBatchOne() {
		return batchOne;
	}

	public void setBatchOne(String batchOne) {
		this.batchOne = batchOne;
	}

	public List<BidProjectAllVo> getBidProjectAllVoList() {
		return bidProjectAllVoList;
	}

	public void setBidProjectAllVoList(List<BidProjectAllVo> bidProjectAllVoList) {
		this.bidProjectAllVoList = bidProjectAllVoList;
	}

	public Double getBiaodiTotal() {
		return biaodiTotal;
	}

	public void setBiaodiTotal(Double biaodiTotal) {
		this.biaodiTotal = biaodiTotal;
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

	public List<BidProject> getListProjects() {
		return listProjects;
	}

	public void setListProjects(List<BidProject> listProjects) {
		this.listProjects = listProjects;
	}

	
	
	
	

}
