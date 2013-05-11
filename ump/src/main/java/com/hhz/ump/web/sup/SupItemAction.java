package com.hhz.ump.web.sup;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.sup.SupItemManager;
import com.hhz.ump.entity.sup.SupItem;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreeNode;

@Namespace("/sup")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "sup-item!list.action", type = "redirect"),
	@Result(name = "export", type = "stream",params = { "contentType", "application/vnd.ms-excel", 
			"inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
			})
public class SupItemAction extends CrudActionSupport<SupItem> {

	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private SupItemManager supItemManager;

	private SupItem entity;

	private String itemCd;
	
	private InputStream is;
	
	private String downFileName;
	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = supItemManager.getSupItem(getId());
			supItemManager.delete(entity);
			addActionMessage(getText("common.success"));
		}
		return "input";
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = supItemManager.getSupItem(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new SupItem();
		}
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (entity.getSupItemCd() == null || "".equals(entity.getSupItemCd())) {
			entity.setSupItemCd(RandomUtils.generateTmpEntityId());
		}
		if (entity.getSupItemTypeCd() == null || "".equals(entity.getSupItemTypeCd())) {
			entity.setSupItemTypeCd(entity.getSupItemCd());
		}
		supItemManager.saveSupItem(entity);
		return null;
	}
	/**
	 * 导出供应商类型报表
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		//根据搜索条件查找到符合条件的记录
		Map<String, Object> param = new HashMap<String, Object>();
		
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("供应类别");
		node.setOrderNo(new Long(0));
		List<SupItem> itemList =supItemManager.getAll();
		node.setChildren(supItemManager.getChildrenNode(node, false,itemList));
		//String hql ="select a from SupItem a ";
		//List<SupItem> result =supItemManager.find(hql, param);
		List<SupItem> result =new ArrayList<SupItem>();
		doExportData(node.getChildren(),result,0);
		Map beans = new HashMap();
		beans.put("result", result);
		is = JXLExcelUtil.initJxlsInputStream(beans, "supResult.xls");
		String fileName = "类型" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	private void doExportData(List<TreeNode> nodeList,List<SupItem> result,int i){
		StringBuffer aStr =new StringBuffer("");
		for(int j=0;j<i;j++){
			aStr.append("  ");
		}
		i++;
		for(TreeNode node:nodeList){
			if(node.getChildren()!=null){
				SupItem item =new SupItem();
				item.setSupItemName(aStr.toString()+node.getText());
				item.setDispOrderNum(node.getOrderNo());
				item.setSupItemCd(node.getId());
				result.add(item);
				doExportData(node.getChildren(),result,i);
			}
		}
	}

	public String query() {
		if (itemCd != null && !"".equals(itemCd)) {
			if (StringUtils.isBlank(getId())) {
				List<SupItem> result = supItemManager.query(itemCd);
				if (result != null && result.size() > 0) {
					Struts2Utils.renderJson("1");
				}
			}
		}
		return null;
	}

	public SupItem getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getItemTree() throws Exception {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("供应类别");
		node.setOrderNo(new Long(0));
		String hql="select a from SupItem a order by a.dispOrderNum";
		List<SupItem> itemList =supItemManager.find(hql);
		node.setChildren(supItemManager.getChildrenNode(node,false,itemList));
		Struts2Utils.renderJson(node);

		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = supItemManager.getSupItem(getId());
		} else {
			entity = new SupItem();
		}
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

}
