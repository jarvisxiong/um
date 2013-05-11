package com.hhz.ump.web.exl;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisShopConnManager;
import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisShopSortManager;
import com.hhz.ump.dao.bis.BisShopSortRelManager;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisShopSort;
import com.hhz.ump.entity.bis.BisShopSortRel;
@Namespace("/exl")
public class BisShopInfoAction extends CrudActionSupport{
	@Autowired
	private BisShopManager bisShopManager;
	@Autowired
	private BisShopConnManager bisShopConnManager;
	@Autowired
	private BisShopSortManager bisShopSortManager;
	@Autowired
	private BisShopSortRelManager bisShopSortRelManager;
	private BisShopSortRel parenRel = null;
	private BisShopSort sort = null;
	private BisShop shop = null;
	//数据源
	String fileName = "D:\\shop2011-2.xls";
	//经过错误处理的结果
	String errorFileName = "D:\\shop2011-2-error.xls";
	//指定解析的工作薄
	Integer sheetNow = 13;
	//指定开始解析行号
	int overlookno = 1;
	//指定记录导入失败的列
	private int sheetLine = 19;
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
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
	protected void prepareModel() throws Exception {
	}
	
	private class ResultVO{
		public boolean flag;
		public String mess;
		public Integer no;
		public ResultVO(boolean flag,String mess){
			this.flag = flag ;this.mess = mess;
		}
		public ResultVO(boolean flag,String mess,Integer no){
			this.flag = flag ;this.mess = mess;this.no = no;
		}
	}
}

