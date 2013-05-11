package com.intelitune.rbac.impl;

import java.util.Iterator;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.intelitune.rbac.base.AccessController;
import com.intelitune.rbac.base.Operate;
import com.intelitune.rbac.base.Role;
import com.intelitune.rbac.util.ParseAcl;
import com.intelitune.nwms.common.CommonAccount;

public class AccessControllerXmlAcl implements AccessController {


	public boolean checkAccess(CommonAccount commonAccount, String className, Operate ope) {

		Set setUserRole = commonAccount.getRole();
		String opeName = ope.getName();
		String roleName = null;
	
		Document doc = ParseAcl.doc;
		NodeList NodeList = doc.getElementsByTagName("accessRule");	//get accessRule(root) node list
		NodeList roleNodeList = ((Element)NodeList.item(0)).getElementsByTagName("role"); //get role node list
		
		Iterator iterator = setUserRole.iterator();
		while(iterator.hasNext()){
			Role role = (Role)iterator.next();
			roleName = role.getName();
			
			for(int i = 0; i < roleNodeList.getLength(); i++){				
	
				Element roleElement = (Element)roleNodeList.item(i);	//get a role element 
	
				if(!roleElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(roleName))
					continue;
				
				NodeList resourceNodeList = roleElement.getElementsByTagName("resource");	//get resource node list		
				for (int j = 0; j < resourceNodeList.getLength(); j++){
					
					Element resourceElement = (Element)resourceNodeList.item(j);	//get a resource element
	
					if(!resourceElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(className))
						continue;
					
					NodeList operateNodeList = resourceElement.getElementsByTagName("operate");	//get operate node list
					for (int x = 0; x < operateNodeList.getLength(); x++){
						
						Element operateElement = (Element)operateNodeList.item(x);		//get a operate element
	
						if(!operateElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(opeName))
							continue;
						else
							return true;
						
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
