package com.hhz.uums.vo.vw;

import java.util.ArrayList;
import java.util.List;

import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRoleGroup;

public class ConvertVoUtil {

	public static VoApp getVoApp(PlasApp app){
		if(app == null)
			return null;
		VoApp t = new VoApp();
		t.setAppId(app.getPlasAppId());
		t.setAppCd(app.getAppCd());
		t.setAppBizCd(app.getAppBizCd());
		t.setAppChnName(app.getAppChnName());
		t.setAppEngName(app.getAppEngName());
		return t;
	}
 
	public static VoRoleGroup getVoGroup(PlasRoleGroup group){
		if(group == null)
			return null;
		VoRoleGroup t = new VoRoleGroup();
		t.setAppId(group.getParentId());
		t.setGroupId(group.getPlasRoleGroupId());
		t.setGroupCd(group.getRoleGroupCd());
		t.setGroupBizCd(group.getRoleGroupBizCd());
		t.setGroupName(group.getRoleGroupName());
		return t;
		
	}
	
	public static List<VoApp> getVoAppList(List<PlasApp> list){
		List<VoApp> tList = new ArrayList<VoApp>();
		VoApp t = null;
		for (PlasApp app : list) {
			if(app != null){
				t = new VoApp();
				t.setAppId(app.getPlasAppId());
				t.setAppCd(app.getAppCd());
				t.setAppBizCd(app.getAppBizCd());
				t.setAppChnName(app.getAppChnName());
				t.setAppEngName(app.getAppEngName());
				tList.add(t);
			}
		}
		return tList;
	}
	
	
	
	public static List<VoRole> getVoRoleList(List<PlasRole> list){
		
		List<VoRole> tList = new ArrayList<VoRole>();
		VoRole t = null;
		for (PlasRole role : list) {
			if(role != null){
				t = new VoRole();
				t.setAppId(role.getPlasApp().getPlasAppId());
				t.setRoleGroupId(role.getPlasRoleGroup().getPlasRoleGroupId());
				t.setRoleId(role.getPlasRoleId());
				t.setRoleCd(role.getRoleCd());
				t.setRoleName(role.getRoleName());
				tList.add(t);
			}
		}
		return tList;
	}
	public static List<VoRoleGroup> getVoGroupList(List<PlasRoleGroup> list){
		List<VoRoleGroup> tList = new ArrayList<VoRoleGroup>();
		VoRoleGroup t = null;
		for (PlasRoleGroup group : list) {
			if(group != null){
				t = new VoRoleGroup();
				t.setAppId(group.getParentId());
				t.setGroupId(group.getPlasRoleGroupId());
				t.setGroupCd(group.getRoleGroupCd());
				t.setGroupBizCd(group.getRoleGroupBizCd());
				t.setGroupName(group.getRoleGroupName());
				tList.add(t);
			}
		}
		return tList;
	}
}
