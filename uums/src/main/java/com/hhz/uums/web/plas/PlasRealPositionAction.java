 package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasRealPositionManager;
import com.hhz.uums.entity.plas.PlasRealPosition;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.web.CrudActionSupport;

/**
 *-------------------------------------------------------
 * date    			||  author      ||  Description    ||
 * 2011-2-18		|| jiaoxiaofeng ||  create         ||PlasRelPositionAction.java
 * Description::实际职位管理：
 * -------------------------------------------------------
 **/
@SuppressWarnings("unchecked")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-real-position.action", type = "redirect"),})
public class PlasRealPositionAction extends CrudActionSupport<PlasRealPosition> {
	@Autowired
	private PlasRealPositionManager plasRealPositionManager;
	private PlasRealPosition entity;

	private static final long serialVersionUID = -7770697240852497155L;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	

	@Override
	public PlasRealPosition getModel() {
		return entity;
	}
	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = plasRealPositionManager.getEntity(getId());
		} else {
			entity = new PlasRealPosition();
		}
	}
	
	public void prepareDelete() throws Exception{
		prepareModel();
	}
	@Override
	public String delete() throws Exception {
		plasRealPositionManager.deleteRealPosition(getId());
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		
		plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.REALPOS, OperConst.DEL,
				new StringBuffer("[").append(entity.getRealPosCd()).append(",").append(entity.getRealPosName()).append(
						"]删除系统职位与角色关系成功!").toString());
		
		JsonUtil.renderSuccess("删除成功");
		return null;
	}
	@Override
	public String deleteBatch() throws Exception {
		return null;
	}
	@Override
	public void prepareInput() throws Exception{
		prepareModel();
	}
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
		
		page = plasRealPositionManager.findPage(page, filters);

		JsonUtil.renderJson(page,new String[]{"plasRealPosition"});
		return null;
	}


	@Override
	public void prepareSave() throws Exception{
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		plasRealPositionManager.savePlasRealPosition(entity);
		Struts2Utils.renderHtml("success");
		return null;
	}
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isTypeExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//begin add by chen-yk 2011-4-12 增加职位代码、职位名称、显示名称的唯一性校验
		// 取消trim()函数， 因为空值trim异常 后期准备拓展该函数为接口函数
		String newRealPosCd = StringUtils.trimToNull(request.getParameter("realPosCd"));
		String oldRealPosCd = StringUtils.trimToNull(request.getParameter("oldrealPosCd"));
		String newRealPosName = StringUtils.trimToNull(request.getParameter("realPosName"));
		String oldRealPosName = StringUtils.trimToNull(request.getParameter("oldrealPosName"));
		String newRealPosNameShow = StringUtils.trimToNull(request.getParameter("realPosNameShow"));
		String oldRealPosNameShow = StringUtils.trimToNull(request.getParameter("oldrealPosNameShow"));
		if((null==oldRealPosCd)&&!"".equals(newRealPosCd)&&(null!=newRealPosCd)){//新增职位代码的唯一性校验
			if (plasRealPositionManager.isPropertyUnique("realPosCd", newRealPosCd, oldRealPosCd)) {
				JsonUtil.renderSuccess("不存在，可以用");
			} else {
				JsonUtil.renderFailure("存在，不可用");
			}
	    }
		if((null==oldRealPosName)&&!"".equals(newRealPosName)&&(null!=newRealPosName)){//新增职位代码的唯一性校验
			if (plasRealPositionManager.isPropertyUnique("realPosName", newRealPosName, oldRealPosName)) {
				JsonUtil.renderSuccess("不存在，可以用");
			} else {
				JsonUtil.renderFailure("存在，不可用");
			}
	   }
		if((null==oldRealPosNameShow)&&!"".equals(newRealPosNameShow)&&(null!=newRealPosNameShow)){//新增职位代码的唯一性校验
			if (plasRealPositionManager.isPropertyUnique("realPosNameShow", newRealPosNameShow, oldRealPosNameShow)) {
				JsonUtil.renderSuccess("不存在，可以用");
			} else {
				JsonUtil.renderFailure("存在，不可用");
			}
		}
		//end add by chen-yk 2011-4-12 增加职位代码、职位名称、显示名称的唯一性校验
		return null;
	}
	public String batchSave() throws Exception {
		List<PlasRealPosition> insertedRecords = (List<PlasRealPosition>) JsonUtil.getInsertRecords(PlasRealPosition.class);
		List<PlasRealPosition> updatedRecords =  (List<PlasRealPosition>) JsonUtil.getUpdatedRecords(PlasRealPosition.class);
		List<PlasRealPosition> deletedRecords =  (List<PlasRealPosition>) JsonUtil.getDeletedRecords(PlasRealPosition.class);
		plasRealPositionManager.saveOrUpdateRealPositions(insertedRecords, updatedRecords, deletedRecords);
		JsonUtil.renderSuccess("保存成功");
		return null;
	}

	/**
	 * 
	 * 快速查询职位  
	 */
	public void quickSearchRealPosList() {

		String tmpName = Struts2Utils.getParameter("value").trim().toLowerCase();
		List<PlasRealPosition> result = plasRealPositionManager.getPlasRealPositionList(tmpName, "10");
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>(); 
		
		for (PlasRealPosition real : result) {
			map = new HashMap<String, String>();
			map.put("plasRealPositionId", real.getPlasRealPositionId());
			map.put("realPosCd", real.getRealPosCd());
			map.put("realPosName", real.getRealPosName());
			map.put("realPosNameShow", real.getRealPosNameShow());
			tmpList.add(map);
		}
 
		Struts2Utils.renderJson(tmpList);
	}
}
