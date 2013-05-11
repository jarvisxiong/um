/**
 * 
 */
package com.hhz.uums.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.uums.dao.bis.BisSmsTmpManager;
import com.hhz.uums.entity.bis.BisSmsTmp;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.web.CrudActionSupport;

@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "bis-sms-tmp.action", type = "redirect"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
				"is", "contentDisposition", "attachment;filename=${downFileName}.xls" }) })
public class BisSmsTmpAction extends CrudActionSupport<BisSmsTmp> {

	private static final long serialVersionUID = 5329186552354300980L;

	@Autowired
	private BisSmsTmpManager bisSmsTmpManager;
	private InputStream is;
	private String downFileName;
	
	private BisSmsTmp entity;

	@Override
	public BisSmsTmp getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity = bisSmsTmpManager.getEntity(getId());
		}
	}

	@Override
	public String deleteBatch() throws Exception {
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
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			page.setOrder(order + "," + Page.DESC);
		}

		page = bisSmsTmpManager.findPage(page, filters);
		JsonUtil.renderJson(page, new String[] { "plasOperateLog" });
		return null;
	}

	/**
	 * 操作日志Excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		List<BisSmsTmp> result = bisSmsTmpManager.find(filters);
		for (BisSmsTmp plasOperateLog : result) {
			bisSmsTmpManager.getDao().evict(plasOperateLog);
		}

		Map beans = new HashMap();
		beans.put("result", result);

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = BisSmsTmpAction.class.getClassLoader().getResourceAsStream(
				"jxlsTemplates/bisSmTmpResult.xls");
		HSSFWorkbook hssfWorkbook = transformer.transformXLS(isTemplate, beans);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		is = new ByteArrayInputStream(data);

		String fileName = "查询结果" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
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
	
	/**
	 * 查看日志明细
	 * @param id
	 * 
	 * @return
	 * @throws Exception 
	 */
	public void prepareDetail() throws Exception{
		prepareModel();
	}
	public String detail(){
		return "detail";
	}

	public BisSmsTmp getEntity() {
		return entity;
	}

	public void setEntity(BisSmsTmp entity) {
		this.entity = entity;
	}
}
