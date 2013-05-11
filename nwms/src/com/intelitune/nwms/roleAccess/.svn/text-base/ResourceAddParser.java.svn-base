package com.intelitune.nwms.roleAccess;

import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ResourceAddParser extends DefaultHandler{

	private Map resourceMap;
	private String sourceName;
	private String operationName;

	
	
	public void doParse(String xmlFile,Map resourceMap) {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(this);
			
			this.resourceMap=resourceMap;
			
			reader.parse(new InputSource(new java.io.InputStreamReader(new java.io.FileInputStream(xmlFile), "UTF-8")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		if (localName.equals("resource")){
			sourceName = attr.getValue("name");
			resourceMap.put(sourceName, attr.getValue("description"));
		}
		if (localName.equals("operation")){
			operationName = attr.getValue("name");
			resourceMap.put(sourceName+":"+operationName, attr.getValue("description"));
		}
	}

	public void endElement(String namespaceURI, String localName, String qName){
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
