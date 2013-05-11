package com.intelitune.nwms.common;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.helpers.DefaultHandler;




public class TemplateBean extends DefaultHandler{
	
	private String tempName;
	private String tempPath;
	private String tempPage;
	private String tableClass;
	private String tableStyle;
	private String labelStyle;
	private String detailTableTitleBgColor;
	private String detailTableTitleStyle;
	private String detailTableRowColor;
	private String onmouseover;
	private String onmouseout;
	private String detailTableStyle;
	private String borderColor;
	
	
	public String getTempName(){
		return tempName;
	}
	
	public String getTempPath(){
		return tempPath;
	}
	
	public String getTempPage(){
		return tempPage;
	}
	
	public String getTableClass(){
		return tableClass;
	}
	
	public String getTableStyle(){
		return tableStyle;
	}
	
	public String getLabelStyle(){
		return labelStyle;
	}

	public String getDetailTableTitleBgColor(){
		return detailTableTitleBgColor;
	}

	public String getDetailTableTitleStyle(){
		return detailTableTitleStyle;
	}

	public String getDetailTableRowColor(){
		return detailTableRowColor;
	}

	public String getOnMouseOver(){
		return onmouseover;
	}

	public String getOnMouseOut(){
		return onmouseout;
	}

	public String getDetailTableStyle(){
		return detailTableStyle;
	}

	public String getBorderColor(){
		return borderColor;
	}

	

	public void setTempName(String s){
		tempName = s;
	}
	
	public void setTempPath(String s){
		tempPath = s;
	}
	
	public void setTempPage(String s){
		tempPage = s;
	}
	
	public void setTableClass(String s){
		tableClass = s;
	}
	
	public void setTableStyle(String s){
		tableStyle = s;
	}

	public void setLabelStyle(String s){
		labelStyle = s;
	}

	public void setDetailTableTitleBgColor(String s){
		detailTableTitleBgColor = s;
	}

	public void setDetailTableTitleStyle(String s){
		detailTableTitleStyle = s;
	}

	public void setDetailTableRowColor(String s){
		detailTableRowColor = s;
	}

	public void setOnMouseOver(String s){
		onmouseover = s;
	}

	public void setOnMouseOut(String s){
		onmouseout = s;
	}

	public void setDetailTableStyle(String s){
		detailTableStyle = s;
	}

	public void setBorderColor(String s){
		borderColor = s;
	}

	
}