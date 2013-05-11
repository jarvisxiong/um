package com.hhz.ump.dao.sup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.entity.sup.SupContactor;
import com.hhz.ump.entity.sup.SupDetail;
import com.hhz.ump.web.cost.vo.VoSupTag;

@Service
@Transactional
public class SupBasicManager extends BaseService<SupBasic, String> {
	@Autowired
	private SupBasicDao supBasicDao;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private SupDetailManager supDetailManager;
	@Autowired
	private SupContactorManager supContactorManager;
	@Transactional(readOnly = true)
	public SupBasic getSupBasic(String id) {
		return supBasicDao.get(id);
	}
	public SupBasic getSupBasicByName(String supName) {
		return supBasicDao.findUniqueBy("supName", supName);
	}
	public void updateSupLevel(String id,String supManaEval,String supExamType,String examUser,String examResult){
		SupBasic  basic=getSupBasic(id);
		basic.setSupManaEval(supManaEval);
		basic.setSupExamResu(supExamType);
		basic.setExamResuPerson(examUser);
		basic.setExamResuContent(examResult);
	}
	public void updateSupLevel(String id,String supManaEval){
		SupBasic  basic=getSupBasic(id);
		basic.setSupManaEval(supManaEval);
	}
	public void saveSupBasic(SupBasic supBasic, List<AppAttachFile> lstAdd) {
		if (lstAdd != null && lstAdd.size() > 0) {
			appAttachFileManager.saveAppAttachFiles(lstAdd);
		}
		PowerUtils.setEmptyStr2Null(supBasic);
		supBasicDao.save(supBasic);
		
		if(supBasic.getSupDetails().get(0)!=null){
			supBasic.getSupDetails().get(0).setSupBasic(supBasic);
			supDetailManager.saveSupDetail(supBasic.getSupDetails().get(0));
		}
		for(SupContactor cont:supBasic.getSupContactors()){
			if(cont!=null){
				if (StringUtils.isNotBlank(cont.getArea())||StringUtils.isNotBlank(cont.getName())||StringUtils.isNotBlank(cont.getEMail())||StringUtils.isNotBlank(cont.getTelephone())) {
					cont.setSupBasic(supBasic);
					supContactorManager.saveSupContactor(cont);
				}
			}
		}
	}

	public void deleteSupBasic(String id) {
		SupBasic basic =getSupBasic(id);
		for(SupContactor contactor:basic.getSupContactors()){
			supContactorManager.delete(contactor);
		}
		for(SupDetail detail:basic.getSupDetails()){
			supDetailManager.delete(detail);
		}
		supBasicDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<SupBasic, String> getDao() {
		return supBasicDao;
	}
	
	


	/**
	 * 类型与属性字段映射
	 * @return
	 */
	public List<VoSupTag> getPropItemList(String sn,String tagfield) {
		
		StringBuffer hqlBuf = new StringBuffer();
		hqlBuf.append(" select distinct "+tagfield+", decode(t."+tagfield+",null,'',t."+tagfield+") ");
		hqlBuf.append("   from sup_basic t ");
		hqlBuf.append("  where t."+tagfield+" is not null ");
		
//		if(StringUtils.isNotBlank(sn)){
//			hqlBuf.append("  and (t.sup_type_sn = :sn or t.sup_type_sn like :leftSn or t.sup_type_sn like :rightSn or t.sup_type_sn like :midSn ) ");
//		}
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sn", sn);
		values.put("leftSn", "%" + sn);
		values.put("rightSn", sn + "%");
		values.put("midSn", "%," +sn + ",%");
		
		List list = getDao().findBySql(hqlBuf.toString(), values);
		List<VoSupTag> rtnList = new ArrayList<VoSupTag>();
		if(list == null)
			return rtnList;
		else{

			//遍历，去英文分隔符(,或;)和中文(；) 不支持中文分隔符(，)
			
			VoSupTag tmp = null;
			Object[] t = null;
			String cds = null;

			String[] arr = null;
			String cd = null;
			
			LinkedHashMap<String, String> cdMap = new LinkedHashMap<String,String>();
			for (int i = 0; i<list.size(); i++) {
				t = (Object[])list.get(i);
				
				cds = (String)t[0];
				if(StringUtils.isNotBlank(cds)){
					arr = StringUtils.split(cds, ",;；");
					if(arr!= null && arr.length > 0){
						for(int j=0; j<arr.length; j++){
							cd = arr[j];
							if(StringUtils.isNotBlank(cd)){
								//不存在，追加
								if(!cdMap.keySet().contains(cd)){
									cdMap.put(cd,cd);
									tmp = new VoSupTag();
									tmp.setTagCd(cd);
									tmp.setTagName(cd);
									rtnList.add(tmp);
								}
							}
						}
					}
				}
			}
			return rtnList;
		}
	}
	
	public static void main(String[] args) {

		String[] arr = StringUtils.split("dd；1111/222,333,tt;4444；aaa", ",;；");
//		System.out.println(arr.length);
		for(int i=0; i<arr.length; i++){
			System.out.println(arr[i]);
		}
	}
	public void save(SupBasic supBasic) {
		PowerUtils.setEmptyStr2Null(supBasic);
		supBasicDao.save(supBasic);
	}

}



