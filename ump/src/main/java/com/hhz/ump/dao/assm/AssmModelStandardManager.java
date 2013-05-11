package com.hhz.ump.dao.assm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.assm.AssmModel;
import com.hhz.ump.entity.assm.AssmModelStandard;

@Service
@Transactional
public class AssmModelStandardManager extends BaseService<AssmModelStandard, String> {
	@Autowired
	private AssmModelStandardDao assmModelStandardDao;

	public void saveAssmModelStandard(AssmModelStandard assmModelStandard) {
		PowerUtils.setEmptyStr2Null(assmModelStandard);
		assmModelStandardDao.save(assmModelStandard);
	}
	
	/**
	 * 保存模型配置列表
	 * @param stands
	 */
	public void saveAssmModelStandards(List<AssmModelStandard> stands){
		if(stands!= null){
			for(AssmModelStandard stand:stands){
				PowerUtils.setEmptyStr2Null(stand);
				assmModelStandardDao.save(stand);
			}
		}
	}

	public void deleteAssmModelStandard(String id) {
		assmModelStandardDao.delete(id);
	}
	
	@Override
	public HibernateDao<AssmModelStandard, String> getDao() {
		return assmModelStandardDao;
	}
	
	
	/**
	 * 搜索项目未创建标准配置的设备型号
	 * @param assmModelId
	 * @return
	 */
	public void findNotExsitProject(AssmModel assmModel){
		
		//搜索语句
		StringBuffer sql=new StringBuffer();
		sql.append(" select p.bis_project_id bpi,p.project_name pn from bis_project  p where ");
		sql.append(" p.bis_project_id not in( ");
		sql.append(" select d.project_cd from assm_model_standard d where d.assm_model_id = :assmModelId");
		sql.append(")");
		
		//搜索条件模型ID
		Map<String,Object> values=new HashMap();		
		values.put("assmModelId", assmModel.getAssmModelId());
		
		//搜索结果集
		List<Object[]> l=assmModelStandardDao.findBySql(sql.toString(), values);
		
		//循环创建模型配置
		if(l!=null&&l.size()>0){
			String projectId;
			//型号配置列表
			List <AssmModelStandard> stands=new ArrayList<AssmModelStandard>();
			AssmModelStandard stand=null;
			for(int i=0;i<l.size();i++){
				Object [] o=l.get(i);
				projectId=(String)o[0];
				stand=new AssmModelStandard();
				stand.setAssmModel(assmModel);
				stand.setProjectCd(projectId);
				stand.setStanCode(assmModel.getAssmCode());
				stand.setEquipName(assmModel.getAssmName());
				stand.setStanNum(BigDecimal.ZERO);
				stands.add(stand);
			}
			//保存未创建的配置列表，初始化数量为0
			this.saveAssmModelStandards(stands);
		}
	}
	
	
	/**
	 * 搜索项目模型配置列表
	 * @param assmModel
	 * @return
	 */
	public List<AssmModelStandardVo> findByassmModel(AssmModel assmModel) {
		
		/**
		 * 原始SQL语句
		 *  select
			  tmp.project_name pn,tmp.assm_model_standard_id amsi,tmp.stan_num stn ,tmp.assm_name asn ,nvl(tmp2.cnt,0) cnt 
			 from (
			 --搜索项目对应的标配
			 select p.project_name,d.assm_model_standard_id,d.stan_num,m.assm_name,m.assm_model_id,p.bis_project_id
			 from assm_model_standard d ,assm_model m ,bis_project p
			 where d.assm_model_id=m.assm_model_id and d.project_cd=p.bis_project_id 
			 and d.assm_model_id = '1212df1000016') tmp
			 
			 left join (
			 --搜索项目、设备类型以及分配数量
			      select acc.project_cd,count(acc.assm_account_id) as cnt from assm_account acc ,assm_model_standard stand,assm_model md 
			      where acc.assm_model_standard_id= stand.assm_model_standard_id
			       and stand.assm_model_id=md.assm_model_id
			       and md.assm_model_id='1212df1000016'
			       group by acc.project_cd
			     
			 ) tmp2 on tmp.bis_project_id=tmp2.project_cd
		 */
		 List<AssmModelStandardVo> result=new ArrayList<AssmModelStandardVo>();
		// 搜索语句
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" tmp.project_name pn,tmp.assm_model_standard_id amsi,tmp.stan_num stn ,tmp.assm_name asn ,nvl(tmp2.cnt,0) cnt  ");
		sql.append(" from ( ");
		sql.append(" select p.project_name,d.assm_model_standard_id,d.stan_num,m.assm_name,m.assm_model_id,p.bis_project_id,p.org_cd ");
		sql.append(" from assm_model_standard d ,assm_model m ,bis_project p ");
		sql.append(" where d.assm_model_id=m.assm_model_id and d.project_cd=p.bis_project_id  ");
		sql.append(" and d.assm_model_id = :assmModelId) tmp ");
		sql.append(" left join ( ");
		sql.append(" select acc.project_cd,count(acc.assm_account_id) as cnt from assm_account acc ,assm_model_standard stand,assm_model md  ");
		sql.append(" where acc.assm_model_standard_id= stand.assm_model_standard_id ");
		sql.append(" and stand.assm_model_id=md.assm_model_id ");
		sql.append(" and md.assm_model_id = :assmModelId");
		sql.append(" group by acc.project_cd ");
		sql.append(" ) tmp2 on tmp.org_cd=tmp2.project_cd ");

		// 搜索条件设置，模型ID
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("assmModelId", assmModel.getAssmModelId());

		// 搜索结果集
		List<Object[]> rs = assmModelStandardDao.findBySql(sql.toString(), values);

		// 构建结果集
		if (rs != null && rs.size() > 0) {
			AssmModelStandardVo vo=null;
			for (int i = 0; i < rs.size(); i++) {
				Object[] o=rs.get(i);
				
				vo=new AssmModelStandardVo();
				vo.setProjectName((String)o[0]);
				vo.setAssmModelStandardId((String)o[1]);
				vo.setStanNum((BigDecimal)o[2]);
				vo.setAssmName((String)o[3]);
				vo.setHasNum((BigDecimal)o[4]);				
				result.add(vo);
				
			}
		}

		return result;
	}
	
	/**
	 * 根据项目CD和设备ID得到配置信息
	 * @param projectCd 
	 * @param assmModelId
	 * @return
	 */
	public AssmModelStandardVo getAssmModelStandardByCont(String projectId,String assmModelId){
		List<AssmModelStandardVo> result=new ArrayList<AssmModelStandardVo>();
		// 搜索语句
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" tmp.project_name pn,tmp.assm_model_standard_id amsi,tmp.stan_num stn ,tmp.assm_name asn ,nvl(tmp2.cnt,0) cnt  ");
		sql.append(" from ( ");
		sql.append(" select p.project_name,d.assm_model_standard_id,d.stan_num,m.assm_name,m.assm_model_id,p.bis_project_id,p.org_cd ");
		sql.append(" from assm_model_standard d ,assm_model m ,bis_project p ");
		sql.append(" where d.assm_model_id=m.assm_model_id and d.project_cd=p.bis_project_id  ");
		sql.append(" and d.assm_model_id = :assmModelId and p.bis_project_id = :projectId) tmp ");
		sql.append(" left join ( ");
		sql.append(" select acc.project_cd,count(acc.assm_account_id) as cnt from assm_account acc ,assm_model_standard stand,assm_model md  ");
		sql.append(" where acc.assm_model_standard_id= stand.assm_model_standard_id ");
		sql.append(" and stand.assm_model_id=md.assm_model_id and stand.project_cd = :projectId");
		sql.append(" and md.assm_model_id = :assmModelId");
		sql.append(" group by acc.project_cd ");
		sql.append(" ) tmp2 on tmp.org_cd=tmp2.project_cd ");

		// 搜索条件设置，模型ID
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("assmModelId", assmModelId);
		values.put("projectId", projectId);

		// 搜索结果集
		List<Object[]> rs = assmModelStandardDao.findBySql(sql.toString(), values);

		// 构建结果集
		if (rs != null && rs.size() > 0) {
			AssmModelStandardVo vo=null;
			for (int i = 0; i < rs.size(); i++) {
				Object[] o=rs.get(i);
				vo = new AssmModelStandardVo();
				vo.setProjectName((String)o[0]);
				vo.setAssmModelStandardId((String)o[1]);
				vo.setStanNum((BigDecimal)o[2]);
				vo.setAssmName((String)o[3]);
				vo.setHasNum((BigDecimal)o[4]);				
				result.add(vo);
			}
		}
		AssmModelStandardVo vo = null;
		for (int i = 0; i < result.size(); i++) {
			vo = result.get(0);
		}
		return vo;
	}
	
}

