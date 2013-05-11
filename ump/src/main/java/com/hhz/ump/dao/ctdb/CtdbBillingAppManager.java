package com.hhz.ump.dao.ctdb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.ctdb.CtdbBillingApp;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.res.ResXmlParser;
import com.hhz.ump.web.res.bean.MaterialSettlementApprove;
import com.hhz.ump.web.res.bean.ProjectBalanceBill;

@Service
@Transactional
public class CtdbBillingAppManager extends BaseService<CtdbBillingApp, String> {
	@Autowired
	private CtdbBillingAppDao ctdbBillingAppDao;
	@Autowired
	protected ResApproveInfoManager resApproveInfoManager;

	public void saveCtdbBillingApp(CtdbBillingApp ctdbBillingApp) {
		PowerUtils.setEmptyStr2Null(ctdbBillingApp);
		ctdbBillingAppDao.save(ctdbBillingApp);
	}

	public void deleteCtdbBillingApp(String id) {
		ctdbBillingAppDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtdbBillingApp, String> getDao() {
		return ctdbBillingAppDao;
	}

	/**
	 * 导入结算数据库
	 */
	public void importHistory() {
		//1、材料设备结算审批表(新) 	material-settlement-approve.jsp 	com.hhz.ump.web.res.bean.MaterialSettlementApprove FKGL_CLSBJS
		//   材料设备结算审批表(新) 材料设备结算审批表(不超出合同金额3%) CBCGGL_GCJS_10
		//   材料设备结算审批表(新) 材料设备结算审批表(超出合同金额3%) CBCGGL_GCJS_12
		//2、工程结算审批表(新) 	project-balance-bill.jsp 	com.hhz.ump.web.res.bean.ProjectBalanceBill
		//   工程结算审批表(新)   工程结算审批表(不超出合同金额3%) CBCGGL_GCJS_20
		//   工程结算审批表(新)   工程结算审批表(超出合同金额3%) CBCGGL_GCJS_25
		Map<String,String> typeMap=new HashMap<String,String>();
		typeMap.put("CBCGGL_GCJS_10", "材料设备结算审批表(不超出合同金额3%)");
		typeMap.put("CBCGGL_GCJS_12", "材料设备结算审批表(超出合同金额3%)");
		typeMap.put("CBCGGL_GCJS_20", "工程结算审批表(不超出合同金额3%)");
		typeMap.put("CBCGGL_GCJS_25", "工程结算审批表(超出合同金额3%)");
		//搜索语句
		StringBuffer hql=new StringBuffer();
		hql.append(" from ResApproveInfo i where  i.authTypeCd = :authTypeCd and i.statusCd='2' ");
		
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
			//即将转换保存的结算数据对象
			CtdbBillingApp billing=null;
			for(ResApproveInfo info:approves){
				//select the CtdbBillingApp table to check whether the ResApproveInfo has been exsit 
				if(hasExsit(info.getResApproveInfoId())){
					//nothing to do
				}else{
					try {
						billing=new CtdbBillingApp();
						String entityName="";
						//根据情况，解析bean,然后转换保存
						if("CBCGGL_GCJS_10".equals(type)||"CBCGGL_GCJS_12".equals(type)){
							entityName="com.hhz.ump.web.res.bean.MaterialSettlementApprove";
							MaterialSettlementApprove tmp=(MaterialSettlementApprove) ResXmlParser.parserXml(info, entityName);
							importFixedTypeOne(tmp,billing);
						}else if("CBCGGL_GCJS_20".equals(type)||"CBCGGL_GCJS_20".equals(type)){
							entityName="com.hhz.ump.web.res.bean.ProjectBalanceBill";
							ProjectBalanceBill tmp=(ProjectBalanceBill) ResXmlParser.parserXml(info, entityName);
							importFixedTypeTwo(tmp,billing);
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
	 * @param billingResCd
	 * @return
	 */
	private boolean hasExsit(String billingResCd){
		boolean flag=false;
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("billingResCd", billingResCd);
		String hql1=" from CtdbBillingApp b where b.billingResCd = :billingResCd";
		List l=ctdbBillingAppDao.find(hql1, map);
		if(l!=null&&l.size()>0){
			flag=true;
		}
		return flag;
	}


	private void importFixedTypeTwo(ProjectBalanceBill tmp, CtdbBillingApp cba) {

		cba.setRemark("impHistroy");
		//1.项目名称
		cba.setProjectName(tmp.getProjectName());
		cba.setProjectCd(tmp.getProjectCd());
		// 2.合同名称
		cba.setCtName(tmp.getContName());
		//3.乙方
		cba.setImpSupCd(tmp.getPartB());
		cba.setImpSupName(null);
		//4.承包范围（现结算审批表内无，建议增加，由合同台账自动导入）
		cba.setCtArea(null);
		//5.合同性质
		cba.setCtProperty(tmp.getContCharacter());
		if(StringUtils.isNotBlank(tmp.getArea())){
			try {
				//6.面积（数量，材料结算内无，建议增加）
				cba.setArea(BigDecimal.valueOf(Double.parseDouble(tmp.getArea().trim().replaceAll(",", ""))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//7.结算价（需审批完成后，由经办人手工导入）
		if(StringUtils.isNotBlank(tmp.getGroupMoney())){
			try {
				BigDecimal billingPrice=BigDecimal.valueOf(Double.valueOf(tmp.getGroupMoney().trim().replaceAll(",", "")));
				cba.setBillingPrice(billingPrice);
			} catch (Exception e) {
					Log.error(e);
				}
		}		
		try {
			//8.单位造价（单价=结算价/面积）
			if (StringUtils.isNotBlank(tmp.getNormGroup())) {
				cba.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(tmp.getNormGroup().replaceAll(",", ""))));
			}
		} catch (Exception e) {
			Log.error(tmp.getResApproveInfo().getResApproveInfoId()+"的单位造价吧合法");
			System.out.println(tmp.getResApproveInfo().getResApproveInfoId()+"的单位造价吧合法");
		}
		//9.结算日期（按结算审批表完成的日期导入）
		cba.setBillingDate(tmp.getResApproveInfo().getCompleteDate());
		//10.网批编号
		cba.setBillingResCd(tmp.getResApproveInfo().getResApproveInfoId());
		cba.setApproveCd(tmp.getResApproveInfo().getApproveCd()+tmp.getResApproveInfo().getSerialNo());
		//10.导入类型
		cba.setImportType(tmp.getResApproveInfo().getAuthTypeCd());
		//默认系统创建
		cba.setCreator("system");
		cba.setCreatedDate(new Date());
		cba.setAuthTypeCd(tmp.getResApproveInfo().getAuthTypeCd());
		saveCtdbBillingApp(cba);
		
	}

	private void importFixedTypeOne(MaterialSettlementApprove tmp, CtdbBillingApp cba) {
		
		cba.setRemark("impHistroy");
		//1.项目名称
		cba.setProjectName(tmp.getProjectName());
		cba.setProjectCd(tmp.getProjectCd());
		// 2.合同名称
		cba.setCtName(tmp.getContName());
		//3.乙方
		cba.setImpSupCd(tmp.getPartB());
		cba.setImpSupName(null);
		//4.承包范围（现结算审批表内无，建议增加，由合同台账自动导入）
		cba.setCtArea(null);
		//5.合同性质
		cba.setCtProperty(tmp.getContProperty());
		//6.面积（数量，材料结算内无，建议增加）
		cba.setArea(null);
		//7.结算价（需审批完成后，由经办人手工导入）
		if(StringUtils.isNotBlank(tmp.getGroupMoney())){
			try {
				BigDecimal billingPrice=BigDecimal.valueOf(Double.valueOf(tmp.getGroupMoney().trim().replaceAll(",", "")));
				cba.setBillingPrice(billingPrice);
			} catch (Exception e) {
					Log.error(e);
				}
		}
		//8.单位造价（单价=结算价/面积）
		cba.setUnitPrice(null);
		//9.结算日期（按结算审批表完成的日期导入）
		cba.setBillingDate(tmp.getResApproveInfo().getCompleteDate());
		//10.网批编号
		cba.setBillingResCd(tmp.getResApproveInfo().getResApproveInfoId());
		cba.setApproveCd(tmp.getResApproveInfo().getApproveCd()+tmp.getResApproveInfo().getSerialNo());
		//10.导入类型
		cba.setImportType(tmp.getResApproveInfo().getAuthTypeCd());
		//默认系统创建
		cba.setCreator("system");
		cba.setCreatedDate(new Date());
		cba.setAuthTypeCd(tmp.getResApproveInfo().getAuthTypeCd());
		saveCtdbBillingApp(cba);
		
	}
	
}

