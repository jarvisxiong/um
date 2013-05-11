package com.hhz.ump.web.desk.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * coremail返回结果集
 * 
 * @author huangjian
 * 
 *         2011-7-15
 */
public class CoremailResult {
	private Long total = 0L;//总记录数
	private Long itemPerPage = 0L;//每页记录数
	
	private Long offset = 0L;//条数偏移量
	private Long results = 0L;//当前记录数
	
	private List<CoremailItem> items = new ArrayList<CoremailItem>();
	private Long totalPageCount = 0L;//总页数
	private Long curPageNo;//当前页


	public Long getCurPageNo() {
		
		if(itemPerPage == 0)
			return 0L;
		else
			return ((offset+results)%itemPerPage == 0)? ((offset+results)/itemPerPage):((offset+results)/itemPerPage+1);
	}

	public void setCurPageNo(Long curPageNo) {
		this.curPageNo = curPageNo;
	}

	public List<CoremailItem> getItems() {
		return items;
	}

	public void setItems(List<CoremailItem> items) {
		this.items = items;
	}

	public Long getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(Long itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public Long getTotalPageCount() {
		
		if(itemPerPage <=0)
			return 0L;
		else
			return ((total % itemPerPage)==0)?(total/itemPerPage):(total/itemPerPage + 1);
	}

	public void setTotalPageCount(Long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}
 
	public Long getResults() {
		return results;
	}

	public void setResults(Long results) {
		this.results = results;
	}

	//是否有下一页
	public boolean getHasNext(){
		if(total > (offset+results))
			return true;
		else 
			return false;
	}
	
	//是否有上一页
	public boolean getHasPre(){
		if(offset > 0)
			return true;
		else
			return false;
	}
}
