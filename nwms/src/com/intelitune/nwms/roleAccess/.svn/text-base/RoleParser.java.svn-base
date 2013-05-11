package com.intelitune.nwms.roleAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.intelitune.nwms.model.ResourceModel;
import com.intelitune.nwms.model.RoleResourceModel;
import com.intelitune.common.PathHorse;

public class RoleParser extends DefaultHandler {

	private HashMap roleNameMap;

	private HashSet resourceSet;

	private HashSet roleNameSet;

	private String roleName;

	private String description;

	private String sourceName;

	private String operationName;
	
	private String ResourceXMLPath = PathHorse.getPath() + "admin/ResourceMapping.xml";

	public HashMap getRoleNameMap() {
		return roleNameMap;
	}

	public HashSet getRoleNameSet() {
		return roleNameSet;
	}

	public RoleParser(String xmlFile) {
		doParse(xmlFile);
	}

	private void doParse(String xmlFile) {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(this);

			roleNameMap = new HashMap();
			roleNameSet = new HashSet();

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
		if (localName.equals("role")) {
			roleName = attr.getValue("name");
			description = attr.getValue("description");

			roleNameSet.add(roleName + ":" + description);
			resourceSet = new HashSet();
		}
		if (localName.equals("resource")) {
			sourceName = attr.getValue("name");
			resourceSet.add(sourceName);
		}
		if (localName.equals("operation")) {
			operationName = attr.getValue("name");
			resourceSet.add(sourceName + ":" + operationName);
		}
	}

	/**
	 * 无查询条件显示所有记录。
	 * 
	 * @author Success
	 * @date 2008-06-07
	 * @param XMLPath
	 * @param RoleName
	 * @param AccountLanguage
	 * @return
	 * @throws Exception
	 */
	/*
	 * @SuppressWarnings("unchecked") public List<RoleResourceModel>
	 * getRoleResourceList(String XMLPath, String RoleName, String
	 * AccountLanguage) throws Exception { List<RoleResourceModel> result =
	 * null; ArrayList al_rrm = new ArrayList(); RoleResourceModel rrm = null;
	 * String str_resourceName = ""; String str_operation = "";
	 * 
	 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder builder = factory.newDocumentBuilder(); Document document =
	 * builder.parse(XMLPath);
	 * 
	 * NodeList nl_root = document.getElementsByTagName("role-list"); Element
	 * roleList = null; if (nl_root.getLength() > 0) { roleList = (Element)
	 * nl_root.item(0); }
	 * 
	 * NodeList nl_role = roleList.getElementsByTagName("role"); for (int j = 0;
	 * j < nl_role.getLength(); j++) { Element role = (Element) nl_role.item(j);
	 * if (role.getAttribute("name").equals(RoleName)) { NodeList nl_resource =
	 * role.getElementsByTagName("resource"); for (int k = 0; k <
	 * nl_resource.getLength(); k++) { Element resource = (Element)
	 * nl_resource.item(k); rrm = new RoleResourceModel(); //
	 * 遍历ResourceMapping中的name与role中的对应从而取出对应的中英文描述信息。 ResourceParser rp = new
	 * ResourceParser(ResourceXMLPath); Set set = rp.getResourceSet(); Iterator
	 * i = set.iterator(); while (i.hasNext()) { Object o = i.next(); if
	 * (o.toString().split(":")[0].equals(resource.getAttribute("name"))) { if
	 * (AccountLanguage.equals("en")) { str_resourceName =
	 * o.toString().split(":")[4]; } else if (AccountLanguage.equals("cn")) {
	 * str_resourceName = o.toString().split(":")[3]; }
	 * rrm.setResourceDescription(str_resourceName);
	 * 
	 * NodeList nl_operation = resource.getElementsByTagName("operation"); if
	 * (nl_operation.getLength() > 0) { Element operation = (Element)
	 * nl_operation.item(0); if (operation.getAttribute("name").equals("n")) {
	 * if (AccountLanguage.equals("en")) { str_operation = "none"; } else if
	 * (AccountLanguage.equals("cn")) { str_operation = "无权限"; } } else if
	 * (operation.getAttribute("name").equals("r")) { if
	 * (AccountLanguage.equals("en")) { str_operation = "read"; } else if
	 * (AccountLanguage.equals("cn")) { str_operation = "可浏览"; } } else if
	 * (operation.getAttribute("name").equals("w")) { if
	 * (AccountLanguage.equals("en")) { str_operation = "write"; } else if
	 * (AccountLanguage.equals("cn")) { str_operation = "可操作"; } }
	 * rrm.setOperation(str_operation); al_rrm.add(rrm); } else {
	 * rrm.setOperation(""); al_rrm.add(rrm); }
	 * 
	 * break; } } } break; } }
	 * 
	 * result = al_rrm.subList(0, al_rrm.size()); return result; } /* /**
	 * 根据选择的树节点返回相应的查询结果。
	 * 
	 * @author Success @date 2008-06-08 @param XMLPath @param RoleName @param
	 * AccountLanguage @param l @return @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResourceModel> getRoleResourceListByTree(String XMLPath, String RoleName, String AccountLanguage, String[] treeNode) throws Exception {

		if (treeNode.length == 0 || (treeNode.length == 1 && treeNode[0].equals(""))) { // 如果没有查询条件那么显示所有结果。
			return null;
		}

		List<RoleResourceModel> result = null;
		ArrayList al_rrm = new ArrayList();
		RoleResourceModel rrm = null;
		String str_resourceName = "";
		String str_operation = "";

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
			if (role.getAttribute("name").equals(RoleName)) {
				for (int k = 0; k < treeNode.length; k++) {
					addResourceRoleModel(role, role.getElementsByTagName("resource"), rrm, treeNode[k], AccountLanguage, str_resourceName, str_operation, al_rrm);
				}
				break;
			}
		}

		result = al_rrm.subList(0, al_rrm.size());
		return result;
	}

	/**
	 * 在新增点之前判断是否已经有相同的点存在。
	 * 
	 * @param al_rrm
	 * @param tn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasTheSameNode(ArrayList<RoleResourceModel> al_rrm, String tn) {
		boolean result = true;
		// for (int r = 0; r < al_rrm.size(); r++) {
		// ResourceParser rp = new ResourceParser(ResourceXMLPath);
		// if (rp.getSourceName(tn,
		// ResourceXMLPath).equals(al_rrm.get(r).getResourceName())) {
		// result = false;
		// break;
		// }
		// }
		return result;
	}

	@SuppressWarnings("unchecked")
	public void addResourceRoleModel(Element element, NodeList nl, RoleResourceModel rrm, String tn, String AccountLanguage, String str_resourceName, String str_operation, ArrayList al_rrm) {
		try {
			if (!tn.trim().equals("")) {
				if (hasTheSameNode(al_rrm, tn)) {
					nl = element.getElementsByTagName("resource");
					ResourceParser rp = new ResourceParser(ResourceXMLPath);
					ResourceModel rm = rp.getResourceModelByNodeId(tn);
					if(rm != null) {
						for (int q = 0; q < nl.getLength(); q++) {
							Element resource = (Element) nl.item(q);
							// 遍历ResourceMapping中的name与role中的对应从而取出对应的中英文描述信息。
							if (rm.getResourceName().equals(resource.getAttribute("name"))) {
								rrm = new RoleResourceModel();
								rrm.setResourceName(rm.getResourceName());
								rrm.setResourceDescription(rm.getNameCN());
								rrm.setTreeNodeId(rm.getTreeNodeId().trim());

								// set operation
								NodeList nl_operation = resource.getElementsByTagName("operation");
								if (nl_operation.getLength() > 0) {
									Element operation = (Element) nl_operation.item(0);
									if (operation.getAttribute("name").equals("n")) {
										if (AccountLanguage.equals("en")) {
											str_operation = "none";
										} else if (AccountLanguage.equals("cn")) {
											str_operation = "无权限";
										}
									} else if (operation.getAttribute("name").equals("r")) {
										if (AccountLanguage.equals("en")) {
											str_operation = "read";
										} else if (AccountLanguage.equals("cn")) {
											str_operation = "可浏览";
										}
									} else if (operation.getAttribute("name").equals("w")) {
										if (AccountLanguage.equals("en")) {
											str_operation = "write";
										} else if (AccountLanguage.equals("cn")) {
											str_operation = "可操作";
										}
									}
									rrm.setOperation(str_operation);
									al_rrm.add(rrm);
								} else {
									rrm.setOperation("");
									al_rrm.add(rrm);
								}
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (localName.equals("role")) {
			roleNameMap.put(roleName, resourceSet);
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
	}

	public static void main(String[] argv) {
		try {
			// HashMap m=RoleParser.getInstance().getRoleNameMap();
			HashMap m = new RoleParser(PathHorse.getPath() + "WEB-INF/RoleControl/RoleMapping.xml").getRoleNameMap();
			Object o1 = m.get("admin");
			if (o1 != null) {
				HashSet s1 = (HashSet) o1;
				Iterator i = s1.iterator();
				while (i.hasNext()) {
					Object o2 = i.next();
					System.out.println(o2);
				}
			}
			/*
			 * HashSet set=RoleParser.getInstance().getRoleNameSet(); Iterator
			 * i=set. iterator(); while(i.hasNext()){ Object o=i.next();
			 * System.out.println(o); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
