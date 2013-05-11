package com.hhz.uums.web.plas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateParser;
import com.hhz.uums.dao.plas.PlasLoginLogManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.entity.plas.PlasLoginLog;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.web.CrudActionSupport;

public class PlasLoginLogAction extends CrudActionSupport<PlasLoginLog> {

	private static final long serialVersionUID = 5344863822561732605L;
	@Autowired
	private PlasLoginLogManager loginLogManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	private PlasLoginLog entity;
	
	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public PlasLoginLog getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		
		return null;
	}

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		

		String uiid = Struts2Utils.getParameter("uiid");
		String userName = Struts2Utils.getParameter("userName");
		String gtDate = Struts2Utils.getParameter("gtDate");
		String ltDate = Struts2Utils.getParameter("ltDate");
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("uiid", uiid);
		values.put("userName", userName);
		values.put("gtDate", gtDate);
		values.put("ltDate", ltDate);
		

		if(StringUtils.isNotBlank(gtDate)){
			values.put("gtDate", DateParser.parse(gtDate, "yyyy-MM-dd") );
		}
		if(StringUtils.isNotBlank(ltDate)){
			values.put("ltDate", DateParser.parse(ltDate, "yyyy-MM-dd") );
		}
		
		StringBuffer hqlBuf = new StringBuffer()
		.append(" from PlasLoginLog t where 1=1 ");
		 

		if(StringUtils.isNotBlank(uiid)){
			hqlBuf.append(" and (uiid = :uiid) ");
		}
		
		if(RoleUtil.isAdmin()||RoleUtil.isAdminSupser()){
			//管理员可任意查询
		}
		else{
			List<String> tmpOrgIdList = plasOrgManager.getMgrOrgIdList(DictContants.TREE_DIME_LOGICAL,SpringSecurityUtils.getCurUiid());
			String[] idList = new String[tmpOrgIdList.size()];
			for(int i=0; i<tmpOrgIdList.size(); i++){
				idList[i] = tmpOrgIdList.get(i);
			}
			values.put("tmpOrgIdList", idList);
			hqlBuf.append(" and exists(select 1 from PlasUser tt where tt.uiid = t.uiid and tt.plasOrg.plasOrgId in (:tmpOrgIdList)) ");
		}

		if(StringUtils.isNotBlank(gtDate)){
			hqlBuf.append(" and ( loginDate >= :gtDate or loginDate >= :gtDate) ");
		}
		if(StringUtils.isNotBlank(ltDate)){
			hqlBuf.append(" and ( loginDate <= :ltDate or loginDate <= :ltDate) ");
		}


		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} 
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
//			page.setOrderBy("createdDate");
//			page.setOrder(Page.DESC);
			hqlBuf.append(" order by createdDate desc ");//注意：这里根据排序显示
		} else {
//			page.setOrderBy(sortField);
//			page.setOrder(order);
			hqlBuf.append(" order by "+sortField+" "+ order);//注意：这里根据排序显示
		}
		
		page =  loginLogManager.findPage(page, hqlBuf.toString(), values);
		
		
		JsonUtil.renderJson(page);
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

}
