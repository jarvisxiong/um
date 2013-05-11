package com.hhz.uums.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.app.AppDictType;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasDimeOrgRel;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasOrgDime;
import com.hhz.uums.entity.plas.PlasRealPosition;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.vo.ws.WsAppDictData;
import com.hhz.uums.vo.ws.WsAppDictType;
import com.hhz.uums.vo.ws.WsPlasAcct;
import com.hhz.uums.vo.ws.WsPlasOrg;
import com.hhz.uums.vo.ws.WsPlasOrgDime;
import com.hhz.uums.vo.ws.WsPlasOrgRel;
import com.hhz.uums.vo.ws.WsPlasRealPosition;
import com.hhz.uums.vo.ws.WsPlasRole;
import com.hhz.uums.vo.ws.WsPlasSysPosition;
import com.hhz.uums.vo.ws.WsPlasUser;

public class ConvertVoUtil {
	
	private static Log log = LogFactory.getLog(ConvertVoUtil.class);

	
	/* *********************************** 与接口有关 **************************************************/
	//维度列表
	public static List<WsPlasOrgDime> transOrgDimeList(List<PlasOrgDime> srcList) {
		List<WsPlasOrgDime> destList = new ArrayList<WsPlasOrgDime>();
		for (PlasOrgDime src : srcList) {
			destList.add(transOrgDime(src));
		}
		return destList;
	}
	public static WsPlasOrgDime transOrgDime(PlasOrgDime src) {
		if (src == null)
			return null;
		try {
			WsPlasOrgDime dest = new WsPlasOrgDime();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)

			dest.setPlasOrgDimeId(src.getPlasOrgDimeId());
			dest.setDimeCd(src.getDimeCd());
			dest.setDimeName(src.getDimeName());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());
			
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transOrgDime() throw exception!" + src.getPlasOrgDimeId());
			return null;
		}
	}

	//机构列表
	public static List<WsPlasOrg> transOrgList(List<PlasOrg> srcList) {
		List<WsPlasOrg> destList = new ArrayList<WsPlasOrg>();
		for (PlasOrg src : srcList) {
			destList.add(transOrg(src));
		}
		return destList;
	}
	public static WsPlasOrg transOrg(PlasOrg src) {
		if (src == null)
			return null;
		try {
			WsPlasOrg dest = new WsPlasOrg();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)
			dest.setPlasOrgId(src.getPlasOrgId());
			dest.setOrgCd(src.getOrgCd());
			dest.setOrgBizCd(src.getOrgBizCd());
			dest.setOrgName(src.getOrgName());
			dest.setShortOrgName(src.getShortOrgName());
			dest.setPhoneDesc(src.getPhoneDesc());
			dest.setFaxDesc(src.getFaxDesc());
			dest.setOrgMgrId(src.getOrgMgrId());// uiid
			dest.setActiveBl(src.getActiveBl());
			dest.setOrgTypeCd(src.getOrgTypeCd());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());

			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transOrg() throw exception!" + src.getPlasOrgId());
			return null;
		}
	}
	
	//职位列表(旧)
	public static List<WsPlasRealPosition> transRealPosList(List<PlasRealPosition> srcList) {
		List<WsPlasRealPosition> destList = new ArrayList<WsPlasRealPosition>();
		for (PlasRealPosition src : srcList) {
			destList.add(transRealPos(src));
		}
		return destList;
	}
	public static WsPlasRealPosition transRealPos(PlasRealPosition src) {
		if (src == null)
			return null;
		try {
			WsPlasRealPosition dest = new WsPlasRealPosition();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)

			dest.setPlasRealPositionId(src.getPlasRealPositionId());
			dest.setRealPosCd(src.getRealPosCd());
			dest.setRealPosName(src.getRealPosName());
			dest.setRealPosNameShow(src.getRealPosNameShow());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());
			
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transPos() throw exception!"+ src.getPlasRealPositionId());
			return null;
		}
	}

	//系统职位列表
	public static List<WsPlasSysPosition> transSysPosList(List<PlasSysPosition> srcList) {
		List<WsPlasSysPosition> destList = new ArrayList<WsPlasSysPosition>();
		for (PlasSysPosition src : srcList) {
			destList.add(transPos(src));
		}
		return destList;
	}
	public static WsPlasSysPosition transPos(PlasSysPosition src) {
		if (src == null)
			return null;
		try {
			WsPlasSysPosition dest = new WsPlasSysPosition();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)
			dest.setPlasSysPositionId(src.getPlasSysPositionId());
			dest.setSysPosCd(src.getSysPosCd());
			dest.setSysPosName(src.getSysPosName());
			dest.setActiveBl(src.getActiveBl());
			dest.setSequenceNo(src.getSequenceNo());
			  
			//注意
			dest.setAcctId(src.getPlasAcct().getPlasAcctId());
			dest.setOrgId(src.getPlasOrg().getPlasOrgId());
			
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transPos() throw exception!"+ src.getPlasSysPositionId());
			return null;
		}
	}
	
	//账号列表
	public static List<WsPlasAcct> transAcctList(List<PlasAcct> srcList) {
		List<WsPlasAcct> destList = new ArrayList<WsPlasAcct>();
		for (PlasAcct src : srcList) {
			destList.add(transAcct(src));
		}
		return destList;
	}
	public static WsPlasAcct transAcct(PlasAcct src) {
		if (src == null)
			return null;
		try {
			WsPlasAcct dest = new WsPlasAcct();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)

			dest.setPlasAcctId(src.getPlasAcctId());
			  
			  // plasUser
			dest.setUserId(src.getPlasUser().getPlasUserId());
			dest.setUserCd(src.getPlasUser().getUserCd());
			dest.setUserName(src.getPlasUser().getUserName());
			dest.setOrgId(src.getPlasUser().getPlasOrg().getPlasOrgId());
			dest.setOrgCd(src.getPlasUser().getPlasOrg().getOrgCd());
			dest.setOrgName(src.getPlasUser().getPlasOrg().getOrgName());
			dest.setRealPositonName(src.getPlasUser().getRealPosCd());//TODO
			dest.setIdno(src.getPlasUser().getIdno());
  
  
			dest.setUiid(src.getUiid());
			dest.setAcctSeqNo(src.getAcctSeqNo());
			dest.setCustLoginName(src.getCustLoginName());
		    dest.setStatusCd(src.getStatusCd());
		    dest.setEmail(src.getEmail());
		    dest.setAuthenticTypeCd(src.getAuthenticTypeCd());
		    dest.setLoginInPassword(src.getLoginInPassword());
		    dest.setLockedDate(src.getLockedDate());
		    dest.setLastLoginDate(src.getLastLoginDate());
		    dest.setLastLogoutDate(src.getLastLogoutDate());
		    dest.setLastLoginIp(src.getLastLoginIp());
		    dest.setEffectDate(src.getEffectDate());
		    dest.setInvalidDate(src.getInvalidDate());
		    dest.setFailureTimes(src.getFailureTimes());
		    dest.setMacAddress(src.getMacAddress());
		    dest.setMacLockedFlg(src.getMacLockedFlg());
		    dest.setEasFlg(src.getEasFlg());
		    dest.setEasPasswordSetFlg(src.getEasFlg());
		    dest.setEmailFlg(src.getEmailFlg());
		    dest.setEmailPasswordSetFlg(src.getEmailPasswordSetFlg());
		    dest.setActiveBl(src.getActiveBl());
		    dest.setSequenceNo(src.getSequenceNo());
		    dest.setRemark(src.getRemark());
			   
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transAcct() throw exception!"+ src.getPlasAcctId());
			return null;
		}
	}
	
	//用户列表
	public static List<WsPlasUser> transUserList(List<PlasUser> srcList) {
		List<WsPlasUser> destList = new ArrayList<WsPlasUser>();
		for (PlasUser src : srcList) {
			destList.add(transUser(src));
		}
		return destList;
	}
	public static WsPlasUser transUser(PlasUser src) {
		if (src == null)
			return null;
		try {
			WsPlasUser dest = new WsPlasUser();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)

			dest.setPlasUserId(src.getPlasUserId());

			// plasOrg
			dest.setOrgId(src.getPlasOrg().getPlasOrgId());
			dest.setOrgCd(src.getPlasOrg().getOrgCd());
			dest.setOrgBizCd(src.getPlasOrg().getOrgBizCd());
			dest.setOrgName(src.getPlasOrg().getOrgName());
//			dest.setCenterOrgCd();//plas不回传 

			dest.setUiid(src.getUiid());
			dest.setUserCd(src.getUserCd());
			dest.setUserBizCd(src.getUserBizCd());
			dest.setUserName(src.getUserName());
			dest.setServiceStatusCd(src.getServiceStatusCd());
			dest.setSexCd(src.getSexCd());
			dest.setBirthday(src.getBirthday());
			dest.setIdno(src.getIdno());
			dest.setNationCd(src.getNationCd());
			dest.setNativeProvinceDesc(src.getNativeProvinceDesc());
			dest.setNativePlaceDesc(src.getNativePlaceDesc());
			dest.setMarrigeStatusCd(src.getMarrigeStatusCd());
			dest.setSchoolRecordCd(src.getSchoolRecordCd());
			dest.setGradSchoolDesc(src.getGradSchoolDesc());
			dest.setMajorDesc(src.getMajorDesc());
			dest.setAttendWorkDate(src.getAttendWorkDate());
			dest.setMemberTypeCd(src.getMemberTypeCd());
			dest.setWorkDutyDesc(src.getWorkDutyDesc());
			dest.setRealPosCd(src.getRealPosCd());
			dest.setProfessionTypeCd(src.getProfessionTypeCd());
			dest.setPoliticsCd(src.getPoliticsCd());
			dest.setOtherTypeCd(src.getOtherTypeCd());
			dest.setEmail(src.getEmail());
			dest.setFixedPhone(src.getFixedPhone());
			dest.setMobilePhone(src.getMobilePhone());
			dest.setMobilePhone2(src.getMobilePhone2());
			dest.setIdCardTypeCd(src.getIdCardTypeCd());
			dest.setSpecialUserFlg(src.getSpecialUserFlg());
			dest.setUserTypeCd(src.getUserTypeCd());
			dest.setSourceTypeCd(src.getSourceTypeCd());
			dest.setDefaultCredenc(src.getDefaultCredenc());
			dest.setPermissionLevelCd(src.getPermissionLevelCd());
			dest.setEmailSignContent(src.getEmailSignContent());
			dest.setActiveBl(src.getActiveBl());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());
			dest.setResponsibility(src.getResponsibility());
			
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transUser() throw exception!"+ src.getPlasUserId());
			return null;
		}
	}
	
	//角色列表
	public static List<WsPlasRole> transRoleList(List<PlasRole> srcList) {
		List<WsPlasRole> destList = new ArrayList<WsPlasRole>();
		for (PlasRole src : srcList) {
			destList.add(transRole(src));
		}
		return destList;
	}
	public static WsPlasRole transRole(PlasRole src) {
		if (src == null)
			return null;
		try {
			WsPlasRole dest = new WsPlasRole();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)

			dest.setPlasRoleId(src.getPlasRoleId());

			// plasApp
			dest.setAppId(src.getPlasApp().getPlasAppId());
			dest.setAppCd(src.getPlasApp().getAppCd());
			dest.setAppChnName(src.getPlasApp().getAppChnName());
			dest.setAppEngName(src.getPlasApp().getAppEngName());

			// PlasRoleGroup
			dest.setGroupId(src.getPlasRoleGroup().getPlasRoleGroupId());
			dest.setGroupCd(src.getPlasRoleGroup().getRoleGroupCd());
			dest.setGroupName(src.getPlasRoleGroup().getRoleGroupName());
			dest.setGroupSeqNo(src.getPlasRoleGroup().getSequenceNo());

			dest.setRoleCd(src.getRoleCd());
			dest.setRoleBizCd(src.getRoleBizCd());
			dest.setRoleName(src.getRoleName());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());
			
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transRole() throw exception!"+ src.getPlasRoleId());
			return null;
		}
	}
	//字典列表
	public static List<WsAppDictType> transDictTypeList(List<AppDictType> srcList) {
		List<WsAppDictType> destList = new ArrayList<WsAppDictType>();
		for (AppDictType src : srcList) {
			destList.add(transDictType(src));
		}
		return destList;
	}
	public static WsAppDictType transDictType(AppDictType src) {
		if (src == null)
			return null;
		try {
			WsAppDictType dest = new WsAppDictType();
			
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)
			dest.setAppDictTypeId(src.getAppDictTypeId());
			dest.setDictTypeCd(src.getDictTypeCd());
			dest.setDictTypeName(src.getDictTypeName());
			dest.setDefaultFlg(src.getDefaultFlg());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());
			
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transDictType() throw exception!"+ src.getAppDictTypeId());
			return null;
		}
	}
	public static List<WsAppDictData> transDictDataList(List<AppDictData> srcList) {
		List<WsAppDictData> destList = new ArrayList<WsAppDictData>();
		for (AppDictData src : srcList) {
			destList.add(transDictData(src));
		}
		return destList;
	}
	public static WsAppDictData transDictData(AppDictData src) {
		if (src == null)
			return null;
		try {
			WsAppDictData dest = new WsAppDictData();
//			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)

			dest.setAppDictDataId(src.getAppDictDataId());
			    
			//appDictType
			dest.setDictTypeId(src.getAppDictType().getAppDictTypeId());
			dest.setDictTypeCd(src.getAppDictType().getDictTypeCd());
			dest.setDictTypeName(src.getAppDictType().getDictTypeName());
			
			dest.setDictCd(src.getDictCd());
			dest.setDictName(src.getDictName());
			dest.setDictLevelNum(src.getDictLevelNum());
			dest.setDefaultFlg(src.getDefaultFlg());
			dest.setSequenceNo(src.getSequenceNo());
			dest.setRemark(src.getRemark());
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transDictData() throw exception!"+ src.getAppDictDataId());
			return null;
		}
	}
	public static List<WsPlasOrgRel> transOrgRelList(List<PlasDimeOrgRel> srcList) {
		List<WsPlasOrgRel> destList = new ArrayList<WsPlasOrgRel>();
		for (PlasDimeOrgRel src : srcList) {
			destList.add(transOrgRel(src));
		}
		return destList;
	}
	public static WsPlasOrgRel transOrgRel(PlasDimeOrgRel src) {
		if (src == null)
			return null;
		try {
			WsPlasOrgRel dest = new WsPlasOrgRel();
			PropertyUtils.copyProperties(dest, src);// PropertyUtils.copyProperties(dest,src)
			dest.setDimeId(src.getPlasOrgDime().getPlasOrgDimeId());
			dest.setDimeCd(src.getPlasOrgDime().getDimeCd());
			dest.setDimeName(src.getPlasOrgDime().getDimeName());
			dest.setOrgId(src.getPlasOrg().getPlasOrgId());
			dest.setOrgCd(src.getPlasOrg().getOrgCd());
			dest.setOrgName(src.getPlasOrg().getOrgName());
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.warn(" call function transOrgRel() throw exception!"+ src.getPlasDimeOrgRelId());
			return null;
		}
	}
	
	public static Boolean getBoolean(BigDecimal a){
		if(a == null)
			return new Boolean(false);
		
		if(a.intValue() == 1)
			return new Boolean(true);
		else
			return new Boolean(false);
	}
	
}
