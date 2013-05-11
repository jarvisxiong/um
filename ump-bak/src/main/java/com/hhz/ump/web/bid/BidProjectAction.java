package com.hhz.ump.web.bid;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidProjectManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidProject;

@Namespace("/bid")
public class BidProjectAction   extends CrudActionSupport<BidProject>{

	
	private static final long serialVersionUID = 4617794851821923142L;

	@Autowired 
	protected BidLedgerManager bidLedgerManager;
	
	@Autowired 
	protected BidProjectManager bidProjectManager;

	@Autowired
	protected AppAttachFileManager appAttachFileManager;
	
	private BidProject entity;  
	//投标台帐ID(保存新建工程必须传入)
	private String bidLedgerId;
	//需要修改的键
	private String key;
	//键的值 
	private String value;	
	//投标台帐下所有工程列表
	private List<BidProject> listProjects;
	//投标台帐
	private BidLedger bidLedger;

	
	@Override
	public String delete() throws Exception {
		if(StringUtils.isBlank(getId()))
			throw new Exception(" project id is blank! ");
		else{
			entity = bidProjectManager.getEntity(getId());
			entity.setDeleteFlg("1");//标记已删除
			bidProjectManager.saveBidProject(entity);
			Struts2Utils.renderText("success");
		}
		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/***
	 * 根据投标台帐ID获取工程列表信息
	 */
	@Override
	public String list() throws Exception {
		//搜索标段台帐
		bidLedger=bidLedgerManager.getEntity(getBidLedgerId());
		//搜索标段工程
		listProjects = bidProjectManager.getEnableProjectList(getBidLedgerId());
		return "list";
	}

	/***
	 * 根据传进的ID值，构建实体
	 */
	@Override
	protected void prepareModel() throws Exception {		
		if(StringUtils.isBlank(getId())) {
			BidLedger tmpBidLedger = bidLedgerManager.getEntity(bidLedgerId);
			if(tmpBidLedger == null)
				throw new Exception("bidLedgerId is blank! ");
			else{
				entity = new BidProject();
				entity.setDeleteFlg("0");
				entity.setBidLedger(tmpBidLedger);
			}
		} else{			
			entity = bidProjectManager.getEntity(getId());
		}
	}

	/**
	 * 保存新建工程信息
	 */
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	
	@Override
	public String save() throws Exception {
		if(StringUtils.isNotBlank(entity.getProjectName())){
			//校验工程名是否存在
			if(StringUtils.isNotBlank(bidLedgerId)){
				if(bidProjectManager.hasExsitProject(bidLedgerId, entity.getProjectName())){
					Struts2Utils.renderText("false;工程名重复，不能保存！");
					return null;
				}
			}
		}else{
			Struts2Utils.renderText("false;工程名为空，不能保存！");
			return null;
		}
				
		bidProjectManager.saveBidProject(entity);
		Struts2Utils.renderText("success;工程保存成功！");	
		return null;
	}
	
	
	/**
	 * 根据键值关系更新工程相应字段
	 * @param id
	 * @param fieldName
	 * @param newVal
	 * @param oldVal
	 * @return
	 */
	public void prepareSaveProperty() throws Exception {
		prepareModel();
	}
	public String saveProperty(){	
		
		String fieldName = Struts2Utils.getParameter("fieldName");
		String newVal = Struts2Utils.getParameter("newVal");
		String oldVal = Struts2Utils.getParameter("oldVal");
		String id = Struts2Utils.getParameter("id");
		if("projectName".equals(fieldName)){
			
			if(StringUtils.isBlank(newVal)){
				BidProject bidProject=bidProjectManager.getProjectById(id);
				Struts2Utils.renderText("false;工程名为空，不能保存！;"+bidProject.getBidLedger().getBidLedgerId());
				return null;
			}else{
				entity.setProjectName(newVal.trim());
			}
		}
		else if("projectDesc".equals(fieldName)){
			entity.setProjectDesc(newVal);
		}
		else{
			Struts2Utils.renderText(" fieldName is unknowned![" + fieldName + "]");
			return null;
		}
	     
		bidProjectManager.saveBidProject(entity);
		
		//TODO: 记录日志
		
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public BidProject getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public BidProject getEntity() {
		return entity;
	}

	public void setEntity(BidProject entity) {
		this.entity = entity;
	}
 
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<BidProject> getListProjects() {
		return listProjects;
	}

	public void setListProjects(List<BidProject> listProjects) {
		this.listProjects = listProjects;
	}
	

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}

	/**
	 * 检查工程相关的附件
	 * @param id: 工程id
	 * @param bizModuleCd: 附件类型
	 * 
	 * @throws Exception
	 */
	public void prepareValidateAttach() throws Exception {
		prepareModel();
	}
	public void validateAttach() {
		String bizModuleCd = Struts2Utils.getParameter("bizModuleCd");
		if (entity != null) {
			if("bidProject".equals(bizModuleCd)){
				// 判断是否有该附件
				//bidProject-工程资料(附件)
				List<AppAttachFile> fileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(getId(), "bidProject");
				if (fileList == null || fileList.size() == 0) {
					updateAttachFlgProject("0");
					Struts2Utils.renderText("0");
				} else {
					updateAttachFlgProject("1");
					Struts2Utils.renderText("1");
				}
			} 
			else{
				Struts2Utils.renderText("bidModuleCd is unkowned!");
			}			
		}else{
			Struts2Utils.renderText("bid project is not founded!");
		}
	}

	private void updateAttachFlgProject(String cd){
		if(StringUtils.isBlank(cd))
			return;
		if (!cd.equals(entity.getAttachFlg())) {
			entity.setAttachFlg(cd);
			bidProjectManager.saveBidProject(entity);
		}
	}
	/**
	 * 
	 * projectHasValidateData:(验证工程是否已存在数据) 
	 * @param  @return
	 * @param  @throws Exception    设定文件  
	 * @return String    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public String projectHasValidateData() throws Exception{
		if(StringUtils.isNotBlank(Struts2Utils.getParameter("projectId"))){
			//是否有数据
			if(bidProjectManager.projectHasValidateData(Struts2Utils.getParameter("projectId"))){
				//如果有，则不能删除
				Struts2Utils.renderText("success,1");
				return null;
			}else{
				//如果没有，则能删除
				Struts2Utils.renderText("success,0");
				return null;
			}
		}else{
			Struts2Utils.renderText("error,projectId 为空");
			return null;
		}
	}
	
	
}
