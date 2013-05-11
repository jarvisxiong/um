package com.hhz.ump.dao.assm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.assm.AssmDepreciation;

@Service
@Transactional
public class AssmDepreciationManager extends BaseService<AssmDepreciation, String> {
	@Autowired
	private AssmDepreciationDao assmDepreciationDao;

	public void saveAssmDepreciation(AssmDepreciation assmDepreciation) {
		PowerUtils.setEmptyStr2Null(assmDepreciation);
		assmDepreciationDao.save(assmDepreciation);
	}

	public void deleteAssmDepreciation(String id) {
		assmDepreciationDao.delete(id);
	}
	
	public AssmDepreciation getAssmDepreciationByModelId(String modelId) {
		Map<String,Object> values = new HashMap<String, Object>();
		String hql = "from AssmDepreciation t where t.assmModelId= :assmModelId";
		if (StringUtils.isNotBlank(modelId)) {
			values.put("assmModelId", modelId);
			List<AssmDepreciation> list = assmDepreciationDao.find(hql, values);
			if (list != null && list.size() > 0)
				return list.get(0);
		}
		return null;
	}
	
	@Override
	public HibernateDao<AssmDepreciation, String> getDao() {
		return assmDepreciationDao;
	}
	
}

