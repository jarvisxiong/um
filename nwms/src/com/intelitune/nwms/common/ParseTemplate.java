package com.intelitune.nwms.common;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.helpers.DefaultHandler;

import com.intelitune.common.PathHorse;

public class ParseTemplate extends DefaultHandler {
	
	// 创建一个templateBean实例
	TemplateBean tb = new TemplateBean(); 

	
	public void readXMLFile(String tn) throws Exception {
		 String filePath = PathHorse.getPath() + "templates/templates.xml";
		
		// 为解析XML作准备， 创建DocumentBuilderFactory实例,指定DocumentBuilder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			System.err.println(pce); // 出异常时输出异常信息，然后退出，下同
			System.exit(1);
		}
		Document doc = null;
		try {
			doc = db.parse(filePath);
		} catch (DOMException dom) {
			System.err.println(dom.getMessage());
			System.exit(1);
		} catch (IOException ioe) {
			System.err.println(ioe);
			System.exit(1);
		} 
		// 下面是解析XML的全过程，先取根元素
		Element root = doc.getDocumentElement(); 
		// 取元素列表
		NodeList templates = root.getElementsByTagName("template");
		for (int i = 0; i < templates.getLength(); i++) {
			// 依次取每个元素
			Element template = (Element) templates.item(i); 
			
			
			// 取属性
			if (tn.equals(template.getAttribute("name"))){
				tb.setTempName(template.getAttribute("name"));
//				System.out.println(template.getAttribute("name")); 
				
				//取元素
				NodeList tempPath = template.getElementsByTagName("path");
				if (tempPath.getLength() == 1) {
					Element e = (Element) tempPath.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setTempPath(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList tempPage = template.getElementsByTagName("page");
				if (tempPage.getLength() == 1) {
					Element e = (Element) tempPage.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setTempPage(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList tableClass = template.getElementsByTagName("table-class");
				if (tableClass.getLength() == 1) {
					Element e = (Element) tableClass.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setTableClass(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList tableStyle = template.getElementsByTagName("table-style");
				if (tableStyle.getLength() == 1) {
					Element e = (Element) tableStyle.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setTableStyle(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList labelStyle = template.getElementsByTagName("label-style");
				if (labelStyle.getLength() == 1) {
					Element e = (Element) labelStyle.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setLabelStyle(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList detailTableTitleBgColor = template.getElementsByTagName("detail-table-title-bgcolor");
				if (detailTableTitleBgColor.getLength() == 1) {
					Element e = (Element) detailTableTitleBgColor.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setDetailTableTitleBgColor(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList detailTableTitleStyle = template.getElementsByTagName("detail-table-title-style");
				if (detailTableTitleStyle.getLength() == 1) {
					Element e = (Element) detailTableTitleStyle.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setDetailTableTitleStyle(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList detailTableRowColor = template.getElementsByTagName("detail-table-row-color");
				if (detailTableRowColor.getLength() == 1) {
					Element e = (Element) detailTableRowColor.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setDetailTableRowColor(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList onMouseOver = template.getElementsByTagName("onmouseover");
				if (onMouseOver.getLength() == 1) {
					Element e = (Element) onMouseOver.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setOnMouseOver(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList onMouseOut = template.getElementsByTagName("onmouseout");
				if (onMouseOut.getLength() == 1) {
					Element e = (Element) onMouseOut.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setOnMouseOut(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList detailTableStyle = template.getElementsByTagName("detail-table-style");
				if (detailTableStyle.getLength() == 1) {
					Element e = (Element) detailTableStyle.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setDetailTableStyle(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
				NodeList borderColor = template.getElementsByTagName("border-color");
				if (borderColor.getLength() == 1) {
					Element e = (Element) borderColor.item(0);
					Text t = (Text) e.getFirstChild();
					tb.setBorderColor(t.getNodeValue());
//					System.out.println(t.getNodeValue());
				}
			}
			
		}
	}

	public String getTempName(){
		return tb.getTempName();
	}
	
	public String getTempPath(){
		return tb.getTempPath();
	}
	
	public String getTempPage(){
		return tb.getTempPage();
	}
	
	public String getTableClass(){
		return tb.getTableClass();
	}
	
	public String getTableStyle(){
		return tb.getTableStyle();
	}
	
	public String getLabelStyle(){
		return tb.getLabelStyle();
	}

	public String getDetailTableTitleBgColor(){
		return tb.getDetailTableTitleBgColor();
	}
	
	public String getDetailTableTitleStyle(){
		return tb.getDetailTableTitleStyle();
	}
	
	public String getDetailTableRowColor(){
		return tb.getDetailTableRowColor();
	}

	public String getOnMouseOver(){
		return tb.getOnMouseOver();
	}

	public String getOnMouseOut(){
		return tb.getOnMouseOut();
	}
	public String getDetailTableStyle(){
		return tb.getDetailTableStyle();
	}
	public String getBorderColor(){
		return tb.getBorderColor();
	}
	
	

	public String getTemplate(String tn) {
	String template="";
	
	return template;
}

public static void main(String[] args) throws Exception { 
	//建立测试实例
	ParseTemplate xmlTest = new ParseTemplate();
	//初始化向量列表 
	System.out.println("开始读xml文件");
	xmlTest.readXMLFile("crystalBlue");
	System.out.println("读入完毕");
	}

}