package com.hhz.uums.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.orm.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Struts2中典型CRUD Action的抽象基类.
 * 
 * 主要定义了对Preparable,ModelDriven接口的使用,以及CRUD函数和返回值的命名.
 * 
 * @param <T>
 *            CRUDAction所管理的对象类型.
 * 
 * @author calvin
 */
@SuppressWarnings("serial")
public abstract class CrudActionSupport<T> extends ActionSupport implements ModelDriven<T>, Preparable {

	/**
	 * 进行增删改操作后,以redirect方式重新打开action默认页的result名.
	 */
	public static final String RELOAD = "reload";

	public static final String RELOAD_SUB = "reloadSub";

	private static final String FILTER = "filter_";

	public static Log logger = LogFactory.getLog(CrudActionSupport.class);

	protected Page<T> page = new Page<T>(10);// 每页10条记录

	/**
	 * 用于批量删除的ID数组
	 */
	private String[] ids;

	/**
	 * 用于单个批量删除的ID
	 */
	private String id;

	// 存放在js中显示的国际化信息
	private Map<String, String> mapJsI18n = new HashMap<String, String>();

	public Map<String, String> getMapJsI18n() {
		return mapJsI18n;
	}

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() throws Exception {
		//return list();
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract String deleteBatch() throws Exception;

	// -- CRUD Action函数 --//
	/**
	 * Action函数,显示Entity列表界面. 建议return SUCCESS.
	 */
	public abstract String list() throws Exception;

	/**
	 * Action函数,显示新增或修改Entity界面. 建议return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action函数,新增或修改Entity. 建议return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action函数,删除Entity. 建议return RELOAD.
	 */
	public abstract String delete() throws Exception;

	// -- Preparable函数 --//
	/**
	 * 实现空的prepare()函数,屏蔽所有Action函数公共的二次绑定.
	 */
	public void prepare() throws Exception {
	}

	/**
	 * 在input()前执行二次绑定.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * 在save()前执行二次绑定.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * 等同于prepare()的内部函数,供prepardMethodName()函数调用.
	 */
	protected abstract void prepareModel() throws Exception;

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getIds() {
		return ids;
	}

	public String getId() {
		return StringUtils.trimToNull(id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
}
