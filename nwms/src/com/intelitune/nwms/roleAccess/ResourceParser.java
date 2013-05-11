package com.intelitune.nwms.roleAccess;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.intelitune.nwms.model.ResourceModel;

public class ResourceParser extends DefaultHandler {

	private ArrayList<ResourceModel> resourceMap;
	private ResourceModel rm;
	private String nodeName;

	public ArrayList getResourceArrayList() {
		return resourceMap;
	}

	public ResourceParser(String xmlFile) {
		doParse(xmlFile);
	}

	private void doParse(String xmlFile) {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(this);
			resourceMap = new ArrayList();
			reader.parse(new InputSource(new java.io.InputStreamReader(new java.io.FileInputStream(xmlFile), "UTF-8")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
	}

	public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException {
		if (qName.equals("node")) {
			rm = new ResourceModel();
			rm.setTreeNodeId(attr.getValue("id"));
			if (attr.getValue("parentid") == null) {
				rm.setTreeParentNodeId("0");
			} else {
				rm.setTreeParentNodeId(attr.getValue("parentid"));
			}
		} else if (qName.equals("nameCN")) {
			nodeName = "nameCN";
		} else if (qName.equals("nameEN")) {
			nodeName = "nameEN";
		} else if (qName.equals("explain")) {
			nodeName = "explain";
		}
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (qName.equals("node")) {
			if (rm != null) {
				resourceMap.add(rm);
			}
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		if (nodeName != null) {
			String nodeValue = new String(ch, start, length);
			if (nodeValue != null && !nodeValue.trim().equals("")) {
				if (nodeName.equals("nameCN")) {
					rm.setNameCN(nodeValue);
				} else if (nodeName.equals("nameEN")) {
					rm.setNameEN(nodeValue);
				} else if (nodeName.equals("explain")) {
					rm.setResourceName(nodeValue);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public ResourceModel getResourceModelByNodeId(String nodeId) {
		ResourceModel rm = null;
		if (nodeId != null) {
			// 遍历角色并产生其访问资源和操作权限。
			List<ResourceModel> listResourceModel = getResourceArrayList();
			for (int i = 0; i < listResourceModel.size(); i++) {
				if (nodeId.trim().equals(listResourceModel.get(i).getTreeNodeId().trim())) {
					rm = listResourceModel.get(i);
					break;
				}
			}
		}
		return rm;
	}
	
	public static void main(String[] argv) {
	}

}
