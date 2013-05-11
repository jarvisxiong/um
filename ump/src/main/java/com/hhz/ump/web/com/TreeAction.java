/**
 * 
 */
package com.hhz.ump.web.com;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;

import com.hhz.ump.web.com.vo.TmplateUtil;
import com.hhz.ump.web.com.vo.VoTreeRow;
import com.opensymphony.xwork2.ActionSupport;
 
@Namespace("/com")
public class TreeAction extends ActionSupport {
	
	private static final long serialVersionUID = 6814668752939558143L;
	 
	private List<VoTreeRow> rowList;
	
	public String list(){
		rowList = TmplateUtil.getOrgUserList();
		return "list";
	}

	public List<VoTreeRow> getRowList() {
		return rowList;
	}

	public void setRowList(List<VoTreeRow> rowList) {
		this.rowList = rowList;
	}
	
}
