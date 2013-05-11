package com.hhz.ump.dao.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostBudgetMonthDetail;

@Service
@Transactional
public class CostBudgetMonthDetailManager extends BaseService<CostBudgetMonthDetail, String> {
	@Autowired
	private CostBudgetMonthDetailDao costBudgetMonthDetailDao;
	@Autowired
	private CostProjectSectionManager costProjectSectionManager;

	public void saveCostBudgetMonthDetail(CostBudgetMonthDetail costBudgetMonthDetail) {
		PowerUtils.setEmptyStr2Null(costBudgetMonthDetail);
		costBudgetMonthDetailDao.save(costBudgetMonthDetail);
	}

	public void deleteCostBudgetMonthDetail(String id) {
		costBudgetMonthDetailDao.delete(id);
	}

	@Override
	public HibernateDao<CostBudgetMonthDetail, String> getDao() {
		return costBudgetMonthDetailDao;
	}
	
	/**
	 * 按照月度资金ID搜索
	 * @param map
	 * @return
	 */
	public CostBudgetMonthDetail getCostBudgetCurPeriodById(Map<String, Object> map) {
		String hql = " from CostBudgetMonthDetail cbcp where cbcp.costBudgetMonthDetailId = :costBudgetMonthDetailId";
		List<CostBudgetMonthDetail> cbcps = costBudgetMonthDetailDao.find(hql, map);
		if (cbcps != null && cbcps.size() > 0)
			return cbcps.get(0);
		else
			return null;
	}

	/**
	 * 根据搜索条件分页搜索月度资金计划列表
	 * 
	 * @param cbcpPage
	 * @param values
	 * @return
	 */
	public Page<CostBudgetMonthDetail> findPageCbcp(Page<CostBudgetMonthDetail> cbcpPage, Map<String, Object> values) {
		// 搜索语句
		StringBuffer hql = new StringBuffer().append(" from CostBudgetMonthDetail c where 1=1 ");
		// 准备年月搜索
		if (values.get("budegetYear") != null && values.get("budegetMonth") != null) {
			try {
				// 年月加入搜索语句
				hql.append(" and c.costBudgetMonth.budegetYear = :budegetYear ")
				.append(" and c.costBudgetMonth.budegetMonth = :budegetMonth");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 准备科目名称搜索
		if (StringUtils.isNotBlank((String)values.get("subjectCd"))) {
			hql.append(" and c.subjectCd = :subjectCd ");
		}
		// 合同编号
		if (values.get("contactNo") != null) {
			hql.append(" and c.contactNo like :contactNo ");
		}
		// 合同名称
		if (values.get("contactName") != null) {
			hql.append(" and c.contactName like :contactName ");
		}
		//乙方
		if (values.get("partb") != null) {
			hql.append(" and c.partb like :partb ");
		}
		//项目
		//限制搜索的项目权限
		if (values.get("sectionBizCd") != null) {
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId in (:sectionBizCd ) ");
		} else{
			//所有授权的项目
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId in (:sectionIdList) ");
			values.put("sectionIdList", costProjectSectionManager.getSectionIdList());
		}
		//排序
		hql.append(" order by c.costBudgetMonth.budegetYear desc,c.costBudgetMonth.budegetMonth desc ,c.costBudgetMonth.costProjectSection.costProjectSectionId desc");
		// 执行搜索
		return costBudgetMonthDetailDao.findPage(cbcpPage, hql.toString(), values);
	}
	
	/**
	 * 保存无合同的月度资金计划
	 * @param detail
	 * @param sequenceNo
	 * @param costBudgetMonthId
	 */
	public void saveNoct(CostBudgetMonthDetail detail, Long sequenceNo,String costBudgetMonthId) {
		//条件
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("sequenceNo", sequenceNo);
		values.put("costBudgetMonthId", costBudgetMonthId);
		//更新语句
		StringBuffer sb=new StringBuffer()
		.append(" update CostBudgetMonthDetail d ")
		.append("    set d.sequenceNo = (d.sequenceNo + 1) ")
		.append("  where d.sequenceNo > :sequenceNo ")
		.append("     and d.costBudgetMonth.costBudgetMonthId = :costBudgetMonthId ");
		//现将所有大于本序号的序号+1
		costBudgetMonthDetailDao.batchExecute(sb.toString(), values);
		//执行保存
		this.saveCostBudgetMonthDetail(detail);
		
	}

	
	/**
	 * 按照合同编号搜索月度资金计划
	 * @param contNo
	 * @return
	 */
	public CostBudgetMonthDetail exsitContNo(String contNo,String costProjectSectionId,String yearMonth) {
		//搜索语句
		StringBuffer hql=new StringBuffer()
		.append(" from CostBudgetMonthDetail d where 1=1 ");
		//条件
		Map<String,Object> values=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(contNo)){
			values.put("contactNo", contNo);
			hql.append("and d.contactNo = :contactNo");
		}
		if(StringUtils.isNotBlank(contNo)){
			values.put("costProjectSectionId", costProjectSectionId);
			hql.append(" and d.costBudgetMonth.costProjectSection.costProjectSectionId = :costProjectSectionId");
		}
		if(StringUtils.isNotBlank(yearMonth)){
			String [] yms=yearMonth.split("-");
			values.put("budegetYear", Long.valueOf(yms[0]));
			values.put("budegetMonth", Long.valueOf(yms[1]));
			hql.append(" and d.costBudgetMonth.budegetYear = :budegetYear");
			hql.append(" and d.costBudgetMonth.budegetMonth = :budegetMonth");
		}
		
		//执行搜索
		List<CostBudgetMonthDetail> l=costBudgetMonthDetailDao.find(hql.toString(), values);
		if(l!=null&& l.size()>0)
			return  l.get(0);
		else
			return null;
	}

	/**
	 * 获取返回当前月度资金计划序列号
	 * @param costBudgetMonthId
	 * @return
	 */
	public Long getcurrentSequenceNo(String costBudgetMonthId) {
		//条件
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("costBudgetMonthId", costBudgetMonthId);
		//搜索语句
		StringBuffer hql=new StringBuffer()
		.append("select  nvl(max(d.sequenceNo),1),1 from CostBudgetMonthDetail d ")
		.append(" where d.costBudgetMonth.costBudgetMonthId = :costBudgetMonthId");
		//执行搜索
		List<Object[]> l=costBudgetMonthDetailDao.find(hql.toString(), values);
		if(l!=null&& l.size()>0)
			return  (Long)l.get(0)[0]+1;
		else
			return 1L;
		
	}

	
	/**
	 * 根据条件搜索月度资金计划明细
	 * @param values
	 * @return
	 */
	public List<CostBudgetMonthDetail> findDetailByCriter(Map<String, Object> values) {
		StringBuffer hql = new StringBuffer().append(" from CostBudgetMonthDetail c where 1=1 ");			
		if(values.get("costBudgetMonthDetailId")!=null){
			hql.append(" and c.costBudgetMonthDetailId = :costBudgetMonthDetailId");
		}
		if(values.get("costProjectSectionId")!=null){
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId = :costProjectSectionId");
		}
		if(values.get("ym")!=null){
			String [] yms=((String)values.get("ym")).split("-");
			values.put("budegetYear", Long.valueOf(yms[0]));
			values.put("budegetMonth", Long.valueOf(yms[1]));
			hql.append(" and c.costBudgetMonth.budegetYear = :budegetYear");
			hql.append(" and c.costBudgetMonth.budegetMonth = :budegetMonth");
		}
		//monthId不为空
		if(values.get("monthId")!=null){
			hql.append(" and c.costBudgetMonth.costBudgetMonthId = :monthId");
		}
		hql.append(" order by c.sequenceNo desc");
		
		return costBudgetMonthDetailDao.find(hql.toString(), values);
	}

	/**
	 * 删除指定的月度资金预算明细
	 * @param detailid
	 */
	public void deleteDetail(String detailid) {
		String hql="delete from CostBudgetMonthDetail d where d.costBudgetMonthDetailId = :costBudgetMonthDetailId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("costBudgetMonthDetailId", detailid);
		costBudgetMonthDetailDao.batchExecute(hql, map);
		
	}
	
	/**
	 * 获取当前最大的序号
	 * @return
	 */
	public Long getcurrentSequenceNo() {
		//条件
		Map<String,Object> values=new HashMap<String,Object>();
		//搜索语句
		StringBuffer hql=new StringBuffer()
		.append("select  nvl(max(d.sequenceNo),1),1 from CostBudgetMonthDetail d ");
		//执行搜索
		List<Object[]> l=costBudgetMonthDetailDao.find(hql.toString(), values);
		if(l!=null&& l.size()>0)
			return  (Long)l.get(0)[0]+1;
		else
			return 1L;
	}

	/**
	 * 按照相应的项目和年月搜索已经存在的合同号
	 * @param projectCd
	 * @param ym
	 * @return
	 */
	public List<String> listAllExsitContNo(String projectCd, String ym) {
		List<String> rs=new ArrayList<String>();
		//搜索语句
		StringBuffer hql = new StringBuffer().append(" from CostBudgetMonthDetail c where 1=1 ");	
		//搜索条件
		Map<String,Object> values=new HashMap<String,Object>();
		
		if(StringUtils.isNotBlank(projectCd)){
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId = :costProjectSectionId");
			values.put("costProjectSectionId", projectCd);
		}
		if(StringUtils.isNotBlank(ym)){
			String [] yms=ym.split("-");
			values.put("budegetYear", Long.valueOf(yms[0]));
			values.put("budegetMonth", Long.valueOf(yms[1]));
			hql.append(" and c.costBudgetMonth.budegetYear = :budegetYear");
			hql.append(" and c.costBudgetMonth.budegetMonth = :budegetMonth");
		}
		List<CostBudgetMonthDetail> tempList=costBudgetMonthDetailDao.find(hql.toString(), values);
		if(tempList!=null&&tempList.size()>0){
			for(CostBudgetMonthDetail d:tempList){
				rs.add(d.getContactNo());
			}			
		}
		return rs;
	}

	/**
	 * 搜索满足条件的记录总条数
	 * @param values
	 */
	public List listAllByMap(Map<String, Object> values) {
		// 搜索语句
		StringBuffer hql = new StringBuffer().append(" from CostBudgetMonthDetail c where 1=1 ");
		// 准备年月搜索
		if (values.get("budegetYear") != null && values.get("budegetMonth") != null) {
			try {
				// 年月加入搜索语句
				hql.append(" and c.costBudgetMonth.budegetYear = :budegetYear ")
				.append(" and c.costBudgetMonth.budegetMonth = :budegetMonth");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 准备科目名称搜索
		if (values.get("subjectName") != null) {
			hql.append(" and c.subjectName like :subjectName ");
		}
		// 合同编号
		if (values.get("contactNo") != null) {
			hql.append(" and c.contactNo like :contactNo ");
		}
		// 合同名称
		if (values.get("contactName") != null) {
			hql.append(" and c.contactName like :contactName ");
		}
		//乙方
		if (values.get("partb") != null) {
			hql.append(" and c.partb like :partb ");
		}
		//项目
		//限制搜索的项目权限
		if (values.get("sectionBizCd") != null) {
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId in (:sectionBizCd ) ");
		} else{
			//所有授权的项目
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId in (:sectionIdList) ");
			values.put("sectionIdList", costProjectSectionManager.getSectionIdList());
		}
		return costBudgetMonthDetailDao.find(hql.toString(), values);
	}

}
