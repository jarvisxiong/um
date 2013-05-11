package com.intelitune.nwms.test;

import java.util.List;

public class JQGridJSONModel {

    private String page; 
    private String total; 
    private Integer records; 
    private List<JQGridRow> rows;
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public Integer getRecords() {
        return records;
    }
    public void setRecords(Integer records) {
        this.records = records;
    }
    public List<JQGridRow> getRows() {
        return rows;
    }
    public void setRows(List<JQGridRow> rows) {
        this.rows = rows;
    }
    
    
    
}
