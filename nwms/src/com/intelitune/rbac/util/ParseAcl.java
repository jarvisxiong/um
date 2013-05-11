package com.intelitune.rbac.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.intelitune.common.PathHorse;

public class ParseAcl {
	
	public static Document doc = ParseAcl.Parse();
	
	private ParseAcl(){
		
	}
	
	private static Document Parse(){
		
		try{
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc = builder.parse(PathHorse.getPath() + "WEB-INF/XML/access.xml");		
			doc.normalize();
			
			return doc;
		
		}catch(Exception e){
			System.out.println(e.getMessage());
			
			return null;
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
