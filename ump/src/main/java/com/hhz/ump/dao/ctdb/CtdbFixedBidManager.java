package com.hhz.ump.dao.ctdb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.ctdb.CtdbFixedBid;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.res.ResXmlParser;
import com.hhz.ump.web.res.bean.BidApproveDesignSheet;
import com.hhz.ump.web.res.bean.BidApproveMaterialDeviceSheet;
import com.hhz.ump.web.res.bean.BidApproveProjectSheet;

@Service
@Transactional
public class CtdbFixedBidManager extends BaseService<CtdbFixedBid, String> {
	@Autowired
	private CtdbFixedBidDao ctdbFixedBidDao;
	@Autowired
	protected ResApproveInfoManager resApproveInfoManager;

	public void saveCtdbFixedBid(CtdbFixedBid ctdbFixedBid) {
		PowerUtils.setEmptyStr2Null(ctdbFixedBid);
		ctdbFixedBidDao.save(ctdbFixedBid);
	}

	public void deleteCtdbFixedBid(String id) {
		ctdbFixedBidDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtdbFixedBid, String> getDao() {
		return ctdbFixedBidDao;
	}
	
	/**
	 * 执行历史定标数据导入
	 */
	public void importHistory(){
		//一下为需要导入的定标数据,1、2、3条公用bean和方法
		//1、定标审批表(战略)----- 定标审批表（项目招标） 	bid-approve-projectbid.jsp 	com.hhz.ump.web.res.bean.BidApproveProjectSheet ZCGL_HTQS_80   
		//2、定标审批表（项目招标） 	bid-approve-projectbid.jsp 	                        com.hhz.ump.web.res.bean.BidApproveProjectSheet ZCGL_HTQS_60
		//3、定标审批表（工程类） 	bid-approve-project-sheet.jsp 	                    com.hhz.ump.web.res.bean.BidApproveProjectSheet ZCGL_HTQS_30
		//4、定标审批表（设计类） 	bid-approve-design-sheet.jsp 	                    com.hhz.ump.web.res.bean.BidApproveDesignSheet ZCGL_HTQS_50
		//5、定标审批表（材料及设备类） 	bid-approve-material-device-sheet.jsp 	com.hhz.ump.web.res.bean.BidApproveMaterialDeviceSheet ZCGL_HTQS_40
		
		//表单类型		
		//String[] authTypeCds={"ZCGL_HTQS_80","ZCGL_HTQS_60","ZCGL_HTQS_30","ZCGL_HTQS_50","ZCGL_HTQS_40"};
		Map<String,String> typeMap=new HashMap<String,String>();
		typeMap.put("ZCGL_HTQS_80", "定标审批表(战略)");
		typeMap.put("ZCGL_HTQS_60", "定标审批表（项目招标）");
		typeMap.put("ZCGL_HTQS_30", "定标审批表（工程类）");
		typeMap.put("ZCGL_HTQS_50", "定标审批表（设计类）");
		typeMap.put("ZCGL_HTQS_40", "定标审批表（材料及设备类）");
		//搜索语句
		StringBuffer hql=new StringBuffer();
		hql.append(" from ResApproveInfo i where  i.authTypeCd = :authTypeCd and  i.statusCd='2' ");
		
		//根据本同的类型搜索，并解析出bean数据
		Map<String,Object> map=null;		
		for(Iterator<String> it = typeMap.keySet().iterator();it.hasNext();){
			String type=it.next();
			//准备搜索条件
			map=new HashMap<String,Object>();
			map.put("authTypeCd", type);
			//执行搜索
			List<ResApproveInfo> approves=resApproveInfoManager.find(hql.toString(), map);
			System.out.println(typeMap.get(type)+"-"+type+" 条数："+approves.size());
			//根据不同情况处理
			//即将转换保存的定标数据对象
			CtdbFixedBid cfb=null;
			for(ResApproveInfo info:approves){
				if(hasExsit(info.getResApproveInfoId())){
					//nothing to do
				}else{
					try {
						cfb=new CtdbFixedBid();
						String entityName="";
						//解析bean
						//定标审批表(战略),定标审批表（项目招标）,定标审批表（工程类）
						if("ZCGL_HTQS_80".equals(type)||"ZCGL_HTQS_60".equals(type)||"ZCGL_HTQS_30".equals(type)){
							entityName="com.hhz.ump.web.res.bean.BidApproveProjectSheet";
							BidApproveProjectSheet tmp=(BidApproveProjectSheet) ResXmlParser.parserXml(info, entityName);
							importFixedTypeOne(tmp,cfb);
						}else{
							//定标审批表（设计类）
							 if("ZCGL_HTQS_50".equals(type)){
								 	entityName="com.hhz.ump.web.res.bean.BidApproveDesignSheet";
								 	BidApproveDesignSheet tmp=(BidApproveDesignSheet)ResXmlParser.parserXml(info, entityName);
									importFixedTypeTwo(tmp,cfb);
								}
							 //定标审批表（材料及设备类）
							 else if("ZCGL_HTQS_40".equals(type)){
									entityName="com.hhz.ump.web.res.bean.BidApproveMaterialDeviceSheet";
									BidApproveMaterialDeviceSheet tmp=(BidApproveMaterialDeviceSheet) ResXmlParser.parserXml(info, entityName);
									importFixedTypeThree(tmp,cfb);
								}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
		}
	}
	
	/**
	 * 查看是否已经存在数据
	 * @param resCd
	 * @return
	 */
	private boolean hasExsit(String resCd){
		boolean flag=false;
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("resCd", resCd);
		String hql1=" from CtdbFixedBid b where b.resCd = :resCd";
		List l=ctdbFixedBidDao.find(hql1, map);
		if(l!=null&&l.size()>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 导入定标审批表（材料及设备类）
	 * @param tmp
	 */
	private void importFixedTypeThree(BidApproveMaterialDeviceSheet tmp,CtdbFixedBid cfb) {		
		
		cfb.setRemark("impHistroy");
		//1.项目名称
		cfb.setOrgName(tmp.getProjectName());
		cfb.setOrgCd(tmp.getProjectCd());
		//2.工程名称（设计阶段）
		cfb.setProjectName(tmp.getEngineeringName());
		//3.中标单位
		cfb.setBidSupCd(tmp.getBidUnit());
		//4.招标范围
		cfb.setBidArea(tmp.getBidRange());		
		//5.施工面积（数量）
		/*if(StringUtils.isNotBlank(this.getConstructionArea())) {
			try{
				BigDecimal constructArea=BigDecimal.valueOf(Double.parseDouble(this.getConstructionArea().trim()));
				cfb.setConstructArea(constructArea);
			}
			catch(Exception e){}
		}*/
		
		//6.中标价
		if(StringUtils.isNotBlank(tmp.getSuccessfulBidPrice())) {
			try{
				BigDecimal bidedPrice=BigDecimal.valueOf(Double.parseDouble(tmp.getSuccessfulBidPrice().trim().replaceAll(",", "")));			
				cfb.setBidedPrice(bidedPrice);
			}
			catch(Exception e){}
		}		
		//7.单位造价（单价）
		if(StringUtils.isNotBlank(tmp.getUnitPrice())) {
			try{
				BigDecimal unitPric=BigDecimal.valueOf(Double.parseDouble(tmp.getUnitPrice().trim().replaceAll(",", "")));			
				cfb.setUnitPrice(unitPric);
			}
			catch(Exception e){}
		}
		//8.定标日期（按“定标审批表”发起的日期导入）
		cfb.setBidDate(tmp.getResApproveInfo().getCreatedDate());
		//9.网批编号
		cfb.setResCd(tmp.getResApproveInfo().getResApproveInfoId());
		cfb.setApproveCd(tmp.getResApproveInfo().getApproveCd()+tmp.getResApproveInfo().getSerialNo());
		//10.导入类型
		cfb.setImportType(tmp.getResApproveInfo().getAuthTypeCd());
		//11、计价模式
		if("true".equals(tmp.getPricingModel1())){
			cfb.setCalcuMode("总价包干");
		}
		if("true".equals(tmp.getPricingModel2())){
			cfb.setCalcuMode("单价包干");
		}
		//默认系统创建
		cfb.setCreator("system");
		cfb.setCreatedDate(new Date());
		cfb.setAuthTypeCd(tmp.getResApproveInfo().getAuthTypeCd());
		//执行保存
		this.saveCtdbFixedBid(cfb);
		
	}
	
	
	/**
	 * 导入定标审批表（设计类）
	 * @param tmp
	 */
	private void importFixedTypeTwo(BidApproveDesignSheet tmp,CtdbFixedBid cfb) {
		
		cfb.setRemark("impHistroy");
		//1.项目名称
		cfb.setOrgName(tmp.getProjectName());
		cfb.setOrgCd(tmp.getProjectCd());
		//2.工程名称（设计阶段）
		//cfb.setProjectName(this.getEngineeringName());
		//3.中标单位
		cfb.setBidSupCd(tmp.getBidUnit());
		cfb.setBidSupName(tmp.getBidUnit());
		//4.招标范围
		//cfb.setBidArea(this.getBidRange());		
		//5.施工面积（数量）
		if(StringUtils.isNotBlank(tmp.getTentativeArea())) {
			try{
				BigDecimal constructArea=BigDecimal.valueOf(Double.parseDouble(tmp.getTentativeArea().trim().replaceAll(",", "")));
				cfb.setConstructArea(constructArea);
			}
			catch(Exception e){}
		}
		
		//6.中标价
		if(StringUtils.isNotBlank(tmp.getSuccessfulBidPrice())) {
			try{
				BigDecimal bidedPrice=BigDecimal.valueOf(Double.parseDouble(tmp.getSuccessfulBidPrice().trim().replaceAll(",", "")));			
				cfb.setBidedPrice(bidedPrice);
			}
			catch(Exception e){}
		}		
		//7.单位造价（单价）
		if(StringUtils.isNotBlank(tmp.getUnilateralCost())) {
			try{
				BigDecimal unitPrice=BigDecimal.valueOf(Double.parseDouble(tmp.getUnilateralCost().trim().replaceAll(",", "")));			
				cfb.setUnitPrice(unitPrice);
			}
			catch(Exception e){}
		}
		//8.定标日期（按“定标审批表”发起的日期导入）
		cfb.setBidDate(tmp.getResApproveInfo().getCreatedDate());
		//9.网批编号
		cfb.setResCd(tmp.getResApproveInfo().getResApproveInfoId());
		cfb.setApproveCd(tmp.getResApproveInfo().getApproveCd()+tmp.getResApproveInfo().getSerialNo());
		//10.导入类型
		cfb.setImportType(tmp.getResApproveInfo().getAuthTypeCd());
		//默认系统创建
		cfb.setCreator("system");
		cfb.setCreatedDate(new Date());
		cfb.setAuthTypeCd(tmp.getResApproveInfo().getAuthTypeCd());
		//执行保存
		this.saveCtdbFixedBid(cfb);
		
	}

	/**
	 * 导入定标审批表(战略),定标审批表（项目招标）,定标审批表（工程类）
	 * @param tmpBean
	 */
	public void importFixedTypeOne(BidApproveProjectSheet tmpBean,CtdbFixedBid cfb){
		
		cfb.setRemark("impHistroy");
		//1.项目名称
		cfb.setOrgName(tmpBean.getProjectName());
		cfb.setOrgCd(tmpBean.getProjectCd());
		//2.工程名称（设计阶段）
		cfb.setProjectName(tmpBean.getEngineeringName());
		//3.中标单位
		cfb.setBidSupCd(tmpBean.getBidUnit());
		//4.招标范围
		cfb.setBidArea(tmpBean.getBidRange());		
		//5.施工面积（数量）
		if(StringUtils.isNotBlank(tmpBean.getConstructionArea())) {
			try{
				BigDecimal constructArea=BigDecimal.valueOf(Double.parseDouble(tmpBean.getConstructionArea().trim()));
				cfb.setConstructArea(constructArea);
			}
			catch(Exception e){}
		}
		
		//6.中标价
		if(StringUtils.isNotBlank(tmpBean.getSuccessfulBidPrice())) {
			try{
				BigDecimal bidedPrice=BigDecimal.valueOf(Double.parseDouble(tmpBean.getSuccessfulBidPrice().trim().replaceAll(",", "")));			
				cfb.setBidedPrice(bidedPrice);
			}
			catch(Exception e){}
		}		
		//7.单位造价（单价）unilateralCost
		if(StringUtils.isNotBlank(tmpBean.getUnilateralCost())) {
			try{
				BigDecimal unitPrice=BigDecimal.valueOf(Double.parseDouble(tmpBean.getUnilateralCost().trim().replaceAll(",", "")));			
				cfb.setUnitPrice(unitPrice);
			}
			catch(Exception e){}
		}
		//8.定标日期（按“定标审批表”发起的日期导入）
		cfb.setBidDate(tmpBean.getResApproveInfo().getCreatedDate());
		//9.网批编号
		cfb.setResCd(tmpBean.getResApproveInfo().getResApproveInfoId());
		cfb.setApproveCd(tmpBean.getResApproveInfo().getApproveCd()+tmpBean.getResApproveInfo().getSerialNo());
		//10.导入类型
		cfb.setImportType(tmpBean.getResApproveInfo().getAuthTypeCd());
		//11、计价模式
		//工程
		if("ZCGL_HTQS_30".equals(tmpBean.getResApproveInfo().getAuthTypeCd())){
			if(StringUtils.isNotBlank(tmpBean.getPricingModel1())&&"true".equals(tmpBean.getPricingModel1())){
				cfb.setCalcuMode("总价包干");
			}	
			else 
				if(StringUtils.isNotBlank(tmpBean.getPricingModel2())&&"true".equals(tmpBean.getPricingModel2())){
					cfb.setCalcuMode("单价包干（出图一个月完成总价包干）");
				}
				else 
					if(StringUtils.isNotBlank(tmpBean.getPricingModel3())&&"true".equals(tmpBean.getPricingModel3())){
						cfb.setCalcuMode("按时结算");
					}
		}else {
			if(StringUtils.isNotBlank(tmpBean.getPricingModel1())&&"true".equals(tmpBean.getPricingModel1())){
				cfb.setCalcuMode("总价包干");
			}	
			else 
				if(StringUtils.isNotBlank(tmpBean.getPricingModel2())&&"true".equals(tmpBean.getPricingModel2())){
					cfb.setCalcuMode("可调总价包干");
				}
				else 
					if(StringUtils.isNotBlank(tmpBean.getPricingModel3())&&"true".equals(tmpBean.getPricingModel3())){
						cfb.setCalcuMode("单价包干");
					}
					else 
						if(StringUtils.isNotBlank(tmpBean.getPricingModel4())&&"true".equals(tmpBean.getPricingModel4())){
							cfb.setCalcuMode("定额计价");
						}
		}
		
		//默认系统创建
		cfb.setCreator("system");
		cfb.setCreatedDate(new Date());
		cfb.setAuthTypeCd(tmpBean.getResApproveInfo().getAuthTypeCd());
		//执行保存
		this.saveCtdbFixedBid(cfb);
	}
	
	
}

