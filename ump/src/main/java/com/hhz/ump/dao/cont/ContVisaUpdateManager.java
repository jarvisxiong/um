package com.hhz.ump.dao.cont;

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
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.vo.VoContVisaUpdate;

@Service
@Transactional
public class ContVisaUpdateManager extends BaseService<ContVisaUpdate, String> {
	@Autowired
	private ContVisaUpdateDao contVisaUpdateDao;

	public void saveContVisaUpdate(ContVisaUpdate contVisaUpdate) {
		PowerUtils.setEmptyStr2Null(contVisaUpdate);
		contVisaUpdateDao.save(contVisaUpdate);
	}

	public void deleteContVisaUpdate(String id) {
		contVisaUpdateDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContVisaUpdate, String> getDao() {
		return contVisaUpdateDao;
	}
 
	/**
	 * 查询合同的签证变更历史
	 * @param tmpBidLedgerId
	 * @return
	 */
	public List<VoContVisaUpdate> getVoVisaList(String contLedgerId) {
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select  ") ;
		
		sb.append(" t.cont_visa_update_id , ");
		sb.append(" t.visa_update_no      , ");
		sb.append(" t.amount_update       , ");
		sb.append(" t.content             , ");
		sb.append(" t.cause               , ");//代码
		
		sb.append(" t.atta_biz_id         , ");
		sb.append(" t.cont_ledger_id      , ");
		sb.append(" t.prepare_fee         , ");
		sb.append(" t.res_approve_cd      , ");
		sb.append(" t.res_approve_id      , ");

		sb.append(" t.pre_visa_content    , ");
		sb.append(" t.approve_check_id    , ");
		sb.append(" t1.display_no    as preDisplayNo, ");
		sb.append(" t2.display_no    as chkDisplayNo, ");
		sb.append(" t3.dict_name          ,");
		
		sb.append(" t.remark              , ");
		sb.append(" t.creator             , ");
		sb.append(" t.created_dept_cd     , ");
		sb.append(" t.created_date        , ");
		sb.append(" t.created_center_cd   , ");
		sb.append(" t.created_position_cd , ");
		sb.append(" t.updated_center_cd   , ");
		sb.append(" t.updated_position_cd , ");
		sb.append(" t.updator             , ");
		sb.append(" t.updated_dept_cd     , ");
		sb.append(" t.updated_date        , ");
		sb.append(" t.record_version        ");
		
		sb.append(" from cont_visa_update t ");
		sb.append(" left join res_approve_info t1 on t1.res_approve_info_id = t.res_approve_id   ");
		sb.append(" left join res_approve_info t2 on t2.res_approve_info_id = t.approve_check_id ");
		sb.append(" left join (select a1.* from app_dict_data a1, app_dict_type a2 where a1.app_dict_type_id = a2.app_dict_type_id and a2.dict_type_cd = :contVisaContent )t3  on t3.dict_cd             = t.cause  ");
		sb.append(" where t.cont_ledger_id = :contLedgerId ");
		sb.append("  order by t.created_date asc ");
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("contLedgerId",contLedgerId);
		values.put("contVisaContent", DictContants.CONT_VISA_CONTENT);//签证变更类别
		
		
		List list = this.findBySql(sb.toString(), values);
		
		
		List<VoContVisaUpdate> voList = new ArrayList<VoContVisaUpdate>();
		
		VoContVisaUpdate vo = null;
		Object[] t = null;
		for (Object o : list) {
			t = (Object[]) o;
			if(t!= null){
				vo = new VoContVisaUpdate();
				vo.setContVisaUpdateId((String)t[0]);
				vo.setVisaUpdateNo((String)t[1]);
				vo.setAmountUpdate(getFormat((BigDecimal)t[2]));
				vo.setContent((String)t[3]);
				vo.setCause((String)t[4]); 
				
				vo.setAttaBizId((String)t[5]);
				vo.setContLedgerId((String)t[6]);
				vo.setPrepareFee(getFormat((BigDecimal)t[7]));
				vo.setResApproveCd((String)t[8]);
				vo.setResApproveId((String)t[9]);

				vo.setPreVisaContent((String)t[10]);
				vo.setApproveCheckId((String)t[11]); 
				vo.setPreDisplayNo(getFormatStr((BigDecimal)t[12]));
				vo.setChkDisplayNo(getFormatStr((BigDecimal)t[13])); 
				vo.setCauseDesc((String)t[14]); 
				
				voList.add(vo);
			}
		}
		return voList;
	}
	
	private BigDecimal getFormat(BigDecimal t){
		if(t == null)
			return new BigDecimal(0);
		
		return t;
	}
	
	private String getFormatStr(BigDecimal t){
		if(t == null)
			return "";
		
		return String.valueOf(t.longValue());
	}

	/**
	 * 查询合同签证列表
	 * @param contLedgerId
	 * @return
	 */
	public List<ContVisaUpdate> getContVisaList(String contLedgerId) {
		
		String hql = " from ContVisaUpdate t where t.contLedger.contLedgerId = :contLedgerId order by t.createdDate asc";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("contLedgerId", contLedgerId);
		List<ContVisaUpdate> list =  getDao().find(hql, values);
		if(list == null)
			return new ArrayList<ContVisaUpdate>();
		return list;
	}
}

