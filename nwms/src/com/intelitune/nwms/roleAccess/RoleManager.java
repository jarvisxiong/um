package com.intelitune.nwms.roleAccess;

import java.util.HashMap;
import java.util.HashSet;

import com.intelitune.common.PathHorse;
import com.intelitune.nwms.roleAccess.RoleParser;

public class RoleManager {

	@SuppressWarnings("unchecked")
//	public static HashMap roleMap = RoleParser.getInstance().getRoleNameMap();

	private static String XMLPath = PathHorse.getPath() + "WEB-INF/RoleControl/RoleMapping.xml";
	
	public static String isOperationAlive(String RoleName, String resource, String operation) {
		if (RoleName == null || RoleName.equals("")) {
			return null;
		}
		else {
			return isRoleContainsOperation(RoleName, resource, operation);
		}
	}

	/**
	 * 根据当前的角色从XML中读取并返回相应的访问权限从而进行权限控制。
	 * @author Success
	 * @date 2008-06-07
	 * @param role
	 * @param resource
	 * @param operation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String isRoleContainsOperation(String role, String resource, String operation) {
		String result = null;
		RoleParser rp = new RoleParser(XMLPath);
		HashMap roleMap = rp.getRoleNameMap();
		HashSet set = (HashSet) roleMap.get(role);
		if (set == null) {
			result = null;
		}
		if(set.contains(resource + ":n")) {	//如果是n表示用户无权限访问当前页。
			result = "n";
		}
		else if(set.contains(resource + ":r")) {	//如果是r表示用户可以访问当前页，但是不能进行任何操作。
			result = "r";
		}
		else if(set.contains(resource + ":w")) {	//如果是w表示用户可以访问当前页，能进行任何操作。
			result = "w";
		}
		return result;
	}

	public static void main(String[] argv) {
		System.out.print(isRoleContainsOperation("user","abc","abc"));
	}

}
