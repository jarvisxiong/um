package com.hhz.ump.web.bis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisFactReduceManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisTenantManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisFactReduce;
import com.hhz.ump.entity.res.ResApproveInfo;
@Namespace("/bis")
public class BisFactReduceAction extends CrudActionSupport<BisFactReduce> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2413544181078869350L;
	private BisFactReduce entity;
	private String resApproveInfoCdNo;
	
	private String filter_LIKES_contNo;
	private String filter_LIKES_shopName;
	private String bisProjectId;
	private String bisProjectName;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisFactReduceManager bisFactReduceManager;
	@Autowired
	private BisTenantManager bisTenantManager;
	@Override
	public BisFactReduce getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		buildSearch();
		return SUCCESS;
	}
	public String searchFactReduce(){
		buildSearch();
		return "list";
	}
	private void buildSearch() {
		if(StringUtils.isNotBlank(bisProjectId)){
			bisProjectName= bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
		Map<String,Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisFactReduce   r     where  1=1  ");
		if(StringUtils.isNotBlank(bisProjectId)){
			hql.append(" and bisCont.bisProjectId=:bisProjectId " );
			param.put("bisProjectId", bisProjectId);
		}
		if(StringUtils.isNotBlank(filter_LIKES_contNo)){
			hql.append(" and bisCont.contNo=:bisContNo " );
				param.put("bisContNo", filter_LIKES_contNo);
		}
		if(StringUtils.isNotBlank(filter_LIKES_shopName)){
			hql.append(" and exists ( select 1 from BisTenant t ,BisShop s where t.bisTenantId = r.bisCont.bisTenantId  and t.bisShopId = s.bisShopId and (s.nameCn like :bisShopName or t.owner like :bisShopName)) " );
				param.put("bisShopName","%"+filter_LIKES_shopName+"%");
		}
		if(StringUtils.isNotBlank(resApproveInfoCdNo)){
			hql.append(" and resApproveInfo.approveCd||resApproveInfo.serialNo like :resApproveInfoCdNo" );
			param.put("resApproveInfoCdNo", "%"+resApproveInfoCdNo+"%");
		}
		hql.append(" order by updatedDate desc,createdDate desc");
		page= bisFactReduceManager.findPage(page, hql.toString(), param);
	}
	public String getShopName(String bisContId) {
		String shopName = bisTenantManager.getShopName(bisContId);
		if(StringUtils.isBlank(shopName)){
			shopName="无";
		}
		return shopName;
	}
	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}
	@Override
	public String input() throws Exception {
		return "input";
	}
	@Override
	public void prepareSave()throws Exception{
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		try{
			bisFactReduceManager.saveBisFactReduce(entity);
			Struts2Utils.renderText("success");
		}catch(Exception e){
			e.printStackTrace();
			Struts2Utils.renderText("error");
		}
		return null;
	}
	public void batchSave(){
		List<BisFactReduce> factReduces = new ArrayList<BisFactReduce>();
		String bisContIds = Struts2Utils.getParameter("bisContId");
		if(StringUtils.isNotBlank(bisContIds)){
			String resApproveInfoId = Struts2Utils.getParameter("resApproveInfoId");
			String remark = Struts2Utils.getParameter("remark");
			ResApproveInfo approve = new ResApproveInfo();
			if(StringUtils.isNotBlank(resApproveInfoId)){
				approve.setResApproveInfoId(resApproveInfoId);
			}else{
				Struts2Utils.renderText("error");
				return;
			}
			String[] bisContIdsTmp = bisContIds.split(",");
			for(int i = 0 ; i<bisContIdsTmp.length ; i++){
				BisCont cont = new BisCont();
				cont.setBisContId(bisContIdsTmp[i]);
				entity = new BisFactReduce();
				entity.setBisCont(cont);
				entity.setResApproveInfo(approve);
				entity.setRemark(remark);
				factReduces.add(entity);
				bisFactReduceManager.saveBisFactReduce(entity);
			}
			Struts2Utils.renderText("success");
		}else {
			Struts2Utils.renderText("error");
		}
	}
	@Override
	public String delete() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity = bisFactReduceManager.getEntity(getId());
			bisFactReduceManager.delete(entity);
		}else{
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
		if(StringUtils.isNotBlank(getId())){
			entity = bisFactReduceManager.getEntity(getId());
		}else{
			entity = new BisFactReduce();
			String bisContId = Struts2Utils.getParameter("bisContId");
			String resApproveInfoId = Struts2Utils.getParameter("resApproveInfoId");
			if(StringUtils.isNotBlank(bisContId)){
				BisCont cont = new BisCont();
				cont.setBisContId(bisContId.replace(",", ""));
				entity.setBisCont(cont);
			}
			if(StringUtils.isNotBlank(resApproveInfoId)){
				ResApproveInfo approve = new ResApproveInfo();
				approve.setResApproveInfoId(resApproveInfoId);
				entity.setResApproveInfo(approve);
			}
		}
	}

	public String getResApproveInfoCdNo() {
		return resApproveInfoCdNo;
	}

	public void setResApproveInfoCdNo(String resApproveInfoCdNo) {
		this.resApproveInfoCdNo = resApproveInfoCdNo;
	}


	public String getFilter_LIKES_contNo() {
		return filter_LIKES_contNo;
	}

	public void setFilter_LIKES_contNo(String filter_LIKES_contNo) throws UnsupportedEncodingException {
		this.filter_LIKES_contNo =  ChangeCharset.decode2UTF_8(filter_LIKES_contNo);
	}

	public String getFilter_LIKES_shopName() {
		return filter_LIKES_shopName;
	}

	public void setFilter_LIKES_shopName(String filter_LIKES_shopName) throws UnsupportedEncodingException {
		this.filter_LIKES_shopName =  ChangeCharset.decode2UTF_8(filter_LIKES_shopName);
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

}

