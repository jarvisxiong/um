package com.intelitune.nwms.role;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {

	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File f = new File("src/com/intelitune/role/test.xml");
		Document doc = builder.parse(f);
		Element root = doc.getDocumentElement();
		NodeList n1 = root.getElementsByTagName("hh");
		Node node = n1.item(0);
		Element hh = (Element)node;
		System.out.println(hh.getAttribute("name"));
		NodeList n2 = node.getChildNodes();
		Node n = n2.item(0);
		System.out.println(n.getNodeValue());
	}

}
