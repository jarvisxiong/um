package com.hhz.ump.web.fin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.fin.FinProjectAcctRelManager;
import com.hhz.ump.dao.fin.FinProjectManager;
import com.hhz.ump.entity.fin.FinProject;
import com.hhz.ump.util.FinProjectUtil;
import com.hhz.ump.util.IExcelExporter;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

@Namespace("/fin")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "fin-project!list.action", type = "redirect"),
		@Result(name = "Exported", location = "file-project!doCashExcel.action", type = "stream", params = {
				"contentType", "application/vnd.ms-excel",
				"contentDisposition",
				"attachment;filename=${excelFileName}.xls", "inputName",
				"excelFile" })})
public class FinProjectAction extends CrudActionSupport<FinProject> implements
		IExcelExporter {

	private static final long serialVersionUID = 8071052318603147332L;

	@Autowired
	private FinProjectManager finProjectManager;
	@Autowired
	private FinProjectAcctRelManager finProjectAcctRelManager;

	private FinProject entity;
	private String userCd;
	private String userProject;
	private Map<String, String> mapUserCds = new HashMap<String, String>();
	private List<FinProjectUtil> results;
	private String beginTime;
	private String finish;
	private String finished;
	private InputStream outStream;
	private String downFileName;
	private String endTime;
	private int pageSize = 300;
	private String isUser;
	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;
	private String publicFlg;
	private String copyUser;
	private String copyUserCd;
	private String targetUser;
	private String targetUserCd;
	private int copyType;
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	public String copyUserPermission() throws Exception {
		return "copyUserPermission";
	}

	public void saveCopy() throws Exception {
		//源用户
		WsPlasUser copyUser1 = PlasCache.getUserByUiid(getCopyUserCd());
		//目标用户
		WsPlasUser targetUser1 = PlasCache.getUserByUiid(getTargetUserCd());

		List<FinProject> finList = null;
		if(getCopyType() == 0) { // 新增
			finList = finProjectManager.getFinProjectsByUserCd(copyUser1.getUserCd());
			for(FinProject fin : finList){
				fin.setUserCds(fin.getUserCds()+targetUser1.getUserCd()+",");
			}
		} else { // 覆盖
			finList = finProjectManager.getFinProjectsByUserCd(targetUser1.getUserCd());
			for(FinProject fin : finList){
				fin.setUserCds(fin.getUserCds().replace("," + targetUser1.getUserCd() + ",", ","));
			}
			finProjectManager.batchSave(finList);
			finList = finProjectManager.getFinProjectsByUserCd(copyUser1.getUserCd());
			for(FinProject fin : finList){
				fin.setUserCds(fin.getUserCds()+targetUser1.getUserCd()+",");
			}
		}
		for(FinProject fin : finList) { // 去除重复数据 (USER_CD)
			String[] cds = fin.getUserCds().split(",");
			Set<String> set = new HashSet<String>();
			for (int i = 1; i < cds.length; i++) {
				set.add(cds[i]);
			}
			cds = set.toArray(new String[0]);
			String userCds = ",";
			for (int i = 0; i < cds.length; i++) {
				userCds += cds[i] + ",";
			}
			fin.setUserCds(userCds);
		}
		finProjectManager.batchSave(finList);
		Struts2Utils.renderText("ok");
		//return "copyUserPermission";
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter(
				"EQS_deleteFlg", "0"));
		page.setOrderBy("projectCd");
		page.setOrder(Page.DESC);
		page.setPageSize(pageSize);
		page = finProjectManager.findPage(page, filters);
		buildToUserNames(page.getResult());
		return SUCCESS;
	}

	public void buildToUserNames(List<FinProject> finProjectList) {
		for (FinProject finProject : finProjectList) {
			if (finProject.getUserCds() != null) {
				String userCds = finProject.getUserCds();
				String[] users = userCds.split(",");
				List<WsPlasUser> wsUsers = PlasCache.getUserActiveList();
				StringBuffer userName = new StringBuffer("");
				for (int i = 0; i < users.length; i++) {
					for (WsPlasUser wsUser : wsUsers) {
						if (wsUser.getUserCd()!=null&&wsUser.getUserCd().equals(users[i])) {
							userName.append(wsUser.getUserName() + ",");
							break;
						}
					}
				}
				mapUserCds.put(userCds, userName.toString());
			}
		}
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = finProjectManager.getFinProject(getId());
		} else {
			entity = new FinProject();
		}
	}

	public String getUsersTree() {

		List<WsPlasOrg> orgList = PlasCache.getOrgEnableList();
		List<WsPlasUser> userList = PlasCache.getUserActiveList();

		Struts2Utils.renderJson(TreePanelUtil.buildPhysicalOrgUserTree(TreePanelUtil.getRootOrg(), orgList, userList));
		return null;
	}

	public List<TreeNode> getChildrenNode() {
		List<TreeNode> children = new ArrayList<TreeNode>();
		List<WsPlasUser> wsUsers = PlasCache.getUserActiveList();
		for (WsPlasUser wsUaapUser : wsUsers) {
			TreeNode user = new TreeNode();
			user.setId(wsUaapUser.getUiid());
			user.setText(wsUaapUser.getUserName());
			children.add(user);
		}
		return children;
	}

	public String addProjectUsers() {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter(
				"EQS_deleteFlg", "0"));
		page.setPageSize(pageSize);
		page.setOrderBy("projectCd");
		page.setOrder(Page.DESC);
		page = finProjectManager.findPage(page, filters);

		return "users";
	}
	@Override
	public String save() throws Exception {
		if (entity.getSequenceNo() == null) {
			entity.setSequenceNo(new Long(0));
		}
		if(entity.getDeleteFlg()==null){
			entity.setDeleteFlg("0");
		}
		finProjectManager.saveFinProject(entity);
		return RELOAD;
	}

	public FinProject getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = finProjectManager.getFinProject(getId());

		} else {
			entity = new FinProject();
			if (entity.getStatusCd() == null) {
				entity.setStatusCd("0");
			}
		}
	}

	public void getUserProject() {
		if (userCd != null && !"".equals(userCd)) {
			List<FinProject> allProjects = finProjectManager.getProject("", "",
					false,publicFlg);
			List<FinProject> finProjects = finProjectManager.getProject(userCd,
 "", false,publicFlg);
			List<String[]> alist = new ArrayList<String[]>();
			for (FinProject fin : allProjects) {
				boolean isCheck = false;
				String[] str = new String[2];
				for (FinProject finProject : finProjects) {
					if (fin.getProjectCd() == finProject.getProjectCd()) {
						isCheck = true;
						break;
					}
				}
				str[0] = fin.getProjectCd();
				if (isCheck) {

					str[1] = "1";

				} else {
					str[1] = "0";
				}
				alist.add(str);
			}
			if (alist != null && alist.size() > 0) {
				Struts2Utils.renderJson(alist);
			}
		}
	}

	public String projectUsersSave() throws Exception {
		if (userCd != null&& !"".equals(userCd)) {
			//String[] projectCd = userProject.split(",");
			if(userProject==null){
				userProject="";
			}
			userProject =","+userProject.replace(" ", "")+",";
			finProjectManager.batchSaveFinProject(userProject,userCd);
		}
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		page.setPageSize(pageSize);
		page.setOrderBy("projectCd");
		page.setOrder(Page.DESC);
		page = finProjectManager.findPage(page, filters);
		if (isUser != null && "isUser".equals(isUser))
			return "user";
		else
		  return "users";
	}

	public String getProject() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (beginTime == null || "".equals(beginTime)) {
			beginTime = format.format(DateOperator.getDateNow());
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = format.format(DateOperator.getDateNow());
		}
		String currentUserCd = SpringSecurityUtils.getCurrentUserCd();
		// 搜索用户
		results = finProjectManager.getUserProject(beginTime + " 00:00:00",
				endTime + " 23:59:59",
				currentUserCd);
		finish = format.format(DateOperator.getDateNow()) + "-1";
		finished = format.format(DateOperator.getDateNow()) + "-2";
		return "total";
	}

	public String doCashExcel() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (beginTime == null || "".equals(beginTime)) {
			beginTime = format.format(DateOperator.getDateNow());
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = format.format(DateOperator.getDateNow());
		}
		excelFile = buildExportedExcelInputStream();
		Date now = Calendar.getInstance().getTime();
		String d = DateOperator.formatDate(now, "yyyy-MM-dd");
		String fileName = "现金流量表_" + d;
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "Exported";
	}

	public InputStream buildExportedExcelInputStream() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {

			try {
				populateData(wbook);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JXLExcelUtil.closeWorkBook(wbook);
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(data);

		return bis;
	}

	private void populateData(WritableWorkbook wbook) throws Exception {
		WritableSheet wsheet0 = wbook.createSheet(beginTime, 0);
		WritableSheet wsheet1 = wbook.createSheet("简表", 1);
		String currentUserCd = SpringSecurityUtils.getCurrentUserCd();
		finProjectManager.doDayExport(wsheet0, wsheet1, beginTime, endTime,
				currentUserCd,publicFlg);
		WritableSheet wsheet2 = wbook.createSheet("汇总", 2);
		finProjectAcctRelManager.doCashExcel(wsheet2, beginTime, endTime, null);
		List<FinProject> proList = finProjectManager.getProject(currentUserCd,
				"", true,publicFlg);
		int i = 3;
		for (FinProject pro : proList) {
			WritableSheet wsheet3 = wbook.createSheet(pro.getProjectName(), i);
			finProjectAcctRelManager.doCashExcel(wsheet3, beginTime, endTime,
					pro.getProjectCd());
			i++;
		}
	}

	/**
	 * 数据迁移，更新
	 */
	public void updateBalance() {
		String currentUserCd = SpringSecurityUtils.getCurrentUserCd();
		finProjectManager.updateBalance(currentUserCd,publicFlg);
	}
	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public void setUserProject(String userProject) {
		this.userProject = userProject;
	}

	public void setResults(List<FinProjectUtil> results) {
		this.results = results;
	}

	public List<FinProjectUtil> getResults() {
		return results;
	}


	public Map<String, String> getMapUserCds() {
		return mapUserCds;
	}

	public void setMapUserCds(Map<String, String> mapUserCds) {
		this.mapUserCds = mapUserCds;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public InputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(InputStream outStream) {
		this.outStream = outStream;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getIsUser() {
		return isUser;
	}

	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}

	public String getPublicFlg() {
		return publicFlg;
	}

	public void setPublicFlg(String publicFlg) {
		this.publicFlg = publicFlg;
	}

	public String getCopyUser() {
		return copyUser;
	}

	public void setCopyUser(String copyUser) {
		this.copyUser = copyUser;
	}

	public String getCopyUserCd() {
		return copyUserCd;
	}

	public void setCopyUserCd(String copyUserCd) {
		this.copyUserCd = copyUserCd;
	}

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public String getTargetUserCd() {
		return targetUserCd;
	}

	public void setTargetUserCd(String targetUserCd) {
		this.targetUserCd = targetUserCd;
	}

	public int getCopyType() {
		return copyType;
	}

	public void setCopyType(int copyType) {
		this.copyType = copyType;
	}

}
