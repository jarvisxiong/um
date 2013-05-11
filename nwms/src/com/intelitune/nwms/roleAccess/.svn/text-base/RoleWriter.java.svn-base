package com.intelitune.nwms.roleAccess;

import java.io.File;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.intelitune.nwms.model.ResourceModel;
import com.intelitune.common.PathHorse;

public class RoleWriter {
	private String XMLFile;
	private Document document;

	private String ResourceXMLPath = PathHorse.getPath() + "admin/ResourceMapping.xml";

	public RoleWriter(String XMLFile) throws Exception {
		this.XMLFile = XMLFile;
		setDocument(XMLFile);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(String XMLFile) throws Exception {
		if (XMLFile != null) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(XMLFile));
			this.document = document;
		}
	}

	public String getName(Node node) {
		String nameValue = null;
		if (node != null) {
			NamedNodeMap map = node.getAttributes();
			for (int i = 0; i < map.getLength(); i++) {
				Node attributeNode = map.item(i);
				String nodeName = attributeNode.getNodeName();
				if (nodeName.equals("name")) {
					nameValue = attributeNode.getNodeValue();
					return nameValue;
				}
			}
		}
		return nameValue;
	}

	public String getSrc(Node node) {
		String src = null;
		if (node != null) {
			NamedNodeMap map = node.getAttributes();
			for (int i = 0; i < map.getLength(); i++) {
				Node attributeNode = map.item(i);
				if (attributeNode != null) {
					String nodeName = attributeNode.getNodeName();
					if (nodeName.equals("src")) {
						src = attributeNode.getNodeValue();
						return src;
					}
				}
			}
		}
		return src;
	}

	public Node getTargetNode(String tagName, String name) throws Exception {
		Node node = null;
		Document document = getDocument();
		NodeList nodeList = document.getElementsByTagName(tagName);
		if (name != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				String nameAttribute = getName(node);
				if (nameAttribute.equals(name)) {
					return node;
				}
			}
		} else {
			if (nodeList.getLength() == 1) {
				return nodeList.item(0);
			}
		}

		return node = null;
	}

	public Node getChidNode(Node parentNode, String childTagName, String childName) throws Exception {
		Node childNode = null;
		if (parentNode != null) {
			NodeList parentNodeList = parentNode.getChildNodes();
			for (int i = 0; i < parentNodeList.getLength(); i++) {
				childNode = parentNodeList.item(i);
				String nodeName = childNode.getNodeName();
				if (nodeName.equals(childTagName)) {
					String name = getName(childNode);
					if (childName.equals(name)) {
						return childNode;
					}
				}
			}
		}
		return childNode = null;
	}

	public HashSet getAdditional(String tagName) {
		HashSet additionalSet = new HashSet();
		if (tagName != null) {
			Document document = getDocument();
			NodeList nodeList = document.getElementsByTagName(tagName);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String src = getSrc(node);
				if (src != null) {
					additionalSet.add(src);
				}
			}
		}
		return additionalSet;
	}

	public Element createElement(String nodeTagName, String nodeName) {
		Element element = document.createElement(nodeTagName);
		element.setAttribute("name", nodeName);
		return element;
	}

	public void write() throws Exception {
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(XMLFile));
		transformer.transform(source, result);
	}

	public void addNode(String parentTagName, String parentName, String tagName, String name) throws Exception {
		Node node = getTargetNode(parentTagName, parentName);
		if (node != null) {
			Element element = createElement(tagName, name);
			node.appendChild(element);
			write();
		}
	}

	public boolean addNode(String parentTagName, String parentName, String tagName, String name, String description) throws Exception {
		Node node = getTargetNode(parentTagName, parentName);
		if (getChidNode(node, tagName, name) != null) {
			return false;
		}
		if (node != null) {
			Element element = createElement(tagName, name);
			element.setAttribute("description", description);
			node.appendChild(element);
			write();
			return true;
		}
		return false;
	}

	public void addNode(String firstDegreeTagName, String firstDegreeName, String secondDegreeTagName, String secondDegreeName, String tagName, String Name) throws Exception {
		Node node = getTargetNode(firstDegreeTagName, firstDegreeName);
		if (node != null) {
			Node childNode = getChidNode(node, secondDegreeTagName, secondDegreeName);
			if (childNode != null) {
				Element element = createElement(tagName, Name);
				childNode.appendChild(element);
				write();
			}
		}
	}

	public void deleteNode(String parentTagName, String parentName, String childTagName, String childName) throws Exception {
		Node parentNode = getTargetNode(parentTagName, parentName);
		if (parentNode != null) {
			Node childNode = getChidNode(parentNode, childTagName, childName);
			if (childNode != null) {
				parentNode.removeChild(childNode);
				write();
			}
		}
	}

	public void deleteNode(String firstDegreeTagName, String firstDegreeName, String secondDegreeTagName, String secondDegreeName, String tagName, String Name) throws Exception {
		Node firstNode = getTargetNode(firstDegreeTagName, firstDegreeName);
		if (firstNode != null) {
			Node secondNode = getChidNode(firstNode, secondDegreeTagName, secondDegreeName);
			if (secondNode != null) {
				Node node = getChidNode(secondNode, tagName, Name);
				if (node != null) {
					secondNode.removeChild(node);
					write();
				}
			}
		}
	}

	public void setNodeAttribute(String tagName, String name, String key, String value) throws Exception {
		Node node = getTargetNode(tagName, name);
		if (node != null) {
			Element element = (Element) node;
			element.setAttribute(key, value);
			write();
		}
	}

	/**
	 * 杈撳嚭XML鏍煎紡鐨勫鎴锋枃浠�
	 * 
	 * @date 2008-04-30
	 * @author success
	 * @param XMLPath
	 * @throws Exception
	 */
	public void createClientXML(String XMLPath, List l) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element roleList = document.createElement("role-list");
		document.appendChild(roleList);

		// for(int i = 0; i < l.size(); i++) {
		Element role = document.createElement("role");
		role.setAttribute("name", "");
		role.setAttribute("description", "");
		roleList.appendChild(role);

		Element resource = document.createElement("resource");
		resource.setAttribute("name", "");
		roleList.appendChild(resource);

		Element operation = document.createElement("operation");
		operation.setAttribute("name", "");
		resource.appendChild(operation);
		// }
		writeToXML(document, XMLPath);
	}

	/**
	 * 修改或增加对应的role mapping xml文件
	 * 
	 * @author Success
	 * @date 2008-06-08
	 * @param XMLPath
	 * @param l
	 * @param RoleName
	 * @param RoleDescription
	 * @param Operation
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void modifyRoleMappingXML(String XMLPath, String[] tn, String RoleName, String RoleDescription, String Operation) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(XMLPath);

		NodeList nl_root = document.getElementsByTagName("role-list");
		Element roleList = null;
		if (nl_root.getLength() > 0) {
			roleList = (Element) nl_root.item(0);
		}

		NodeList nl_role = roleList.getElementsByTagName("role");
		for (int j = 0; j < nl_role.getLength(); j++) {
			Element role = (Element) nl_role.item(j);
			if (role.getAttribute("name").equals(RoleName)) { // 如果是当前XML中的角色那么更新该节点的状态，不去影响其它的角色在XML中的状态。
				for (int i = 0; i < tn.length; i++) {
					ResourceParser rp = new ResourceParser(ResourceXMLPath);
					ResourceModel rm = rp.getResourceModelByNodeId(tn[i]);
					if (rm != null) {
						String nresource = rm.getResourceName();
						NodeList nl_resource = role.getElementsByTagName("resource"); // 找出所有在ResourceMapping中为resource的节点。
						for (int k = 0; k < nl_resource.getLength(); k++) {
							Element resource = (Element) nl_resource.item(k);
							if (resource.getAttribute("name").equals(nresource)) {
								role.removeChild(resource);
								resource = document.createElement("resource");
								resource.setAttribute("name", nresource);
								role.appendChild(resource);
								Element operation = document.createElement("operation");
								operation.setAttribute("name", Operation);
								resource.appendChild(operation);
							}
						}
					}
				}
				writeToXML(document, XMLPath);
				break;
			}
		}
	}

	/**
	 * 为某个角色初始所有权限，并且都为无法操作。
	 * 
	 * @author Success
	 * @date 2008-06-06
	 * @param XMLPath
	 * @param l
	 * @param RoleName
	 * @param RoleDescription
	 * @param Operation
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void addRoleMappingXML(String XMLPath, String RoleName, String RoleDescription, String RoleFunction) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(XMLPath);

		NodeList nl_root = document.getElementsByTagName("role-list");
		Element roleList = null;
		if (nl_root.getLength() > 0) {
			roleList = (Element) nl_root.item(0);
		}

		Element role = document.createElement("role");
		role.setAttribute("name", RoleName);
		role.setAttribute("description", RoleDescription);
		roleList.appendChild(role);

		ResourceParser rp = new ResourceParser(ResourceXMLPath);
		// 遍历角色并产生其访问资源和操作权限。
		List<ResourceModel> listResourceModel = rp.getResourceArrayList();
		for (int i = 0; i < listResourceModel.size(); i++) {
			Element resource = document.createElement("resource");
			resource.setAttribute("name", listResourceModel.get(i).getResourceName());
			role.appendChild(resource);
			Element operation = document.createElement("operation");
			operation.setAttribute("name", RoleFunction);
			resource.appendChild(operation);
		}

		writeToXML(document, XMLPath);
	}

	public void writeToXML(Document document, String XMLFile) throws Exception {
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(XMLFile));
		transformer.transform(source, result);
	}

	public static void main(String[] args) throws Exception {
		// RoleWriter rw = new RoleWriter(PathHorse.getPath() +
		// "WEB-INF/RoleControl/RoleMapping.xml");
		// RoleWriter rw=new RoleWriter("./WEB-INF/RoleControl/Role.xml");
		// rw.addNode("role", "user", "resource", "newResource");
		// rw.addNode("role", "admin", "resource",
		// "com.intelitune.userManager.ui.userListController", "operation",
		// "delete");
		// rw.deleteNode("role", "user", "resource", "newResource");
		// rw.deleteNode("role", "admin", "resource",
		// "com.intelitune.userManager.ui.userListController", "operation",
		// "delete");
		// rw.addNode("role-list", null, "role", "test","description");
		// rw.deleteNode("role-list", null, "role", "test");
		// rw.setNodeAttribute("role", "test", "description","a");

		/*
		 * HashSet set=rw.getAdditional("additional-role-list"); Iterator
		 * iterator=set.iterator(); if(iterator.hasNext()){ String
		 * s=(String)iterator.next(); System.out.println(s); }
		 */
	}

}
