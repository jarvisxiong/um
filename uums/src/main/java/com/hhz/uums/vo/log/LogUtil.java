package com.hhz.uums.vo.log;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hhz.core.utils.DateParser;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasUser;

/**
 * @author waveflat
 *
 */
public class LogUtil {

	public static final Log log = LogFactory.getLog(LogUtil.class);
	
	/**
	 * 获取变化的用户信息
	 * @param isNew 是否新建
	 * @param oldUser 旧值
	 * @param newUser 新值
	 * @return
	 */
	public static String getOperateUser(boolean isNew, LogPlasUser oldUser, PlasUser newUser) {
		
//		PlasUser plasUser = newUser.getPlasUser();
		if( oldUser == null){
			oldUser = new LogPlasUser();
		}
		 
		StringBuffer sb = new StringBuffer()
//		.append(getFieldChange(isNew, "统一登录账号",oldUser.getUiid(), newUser.getUiid() ))
		.append(getFieldChange(isNew, "用户业务编号",oldUser.getUserBizCd(), newUser.getUserBizCd() ))
//		.append(getFieldChange(isNew, "用户状态代码",oldUser.getUserStatusCd(), newUser.getStatusCd()))
		.append(getFieldChange(isNew, "用户姓名",oldUser.getUserName(), newUser.getUserName() ))
		.append(getFieldChange(isNew, "在职状态",oldUser.getServiceStatusCd(), newUser.getServiceStatusCd()))
		.append(getFieldChange(isNew, "性别",oldUser.getSexCd(), newUser.getSexCd()))
		.append(getFieldChange(isNew, "出生日期",getDt(oldUser.getBirthday()), getDt(newUser.getBirthday())))
		.append(getFieldChange(isNew, "身份证号码",oldUser.getIdno(), newUser.getIdno() ))
		.append(getFieldChange(isNew, "民族",oldUser.getNationCd(), newUser.getNationCd() ))
		.append(getFieldChange(isNew, "籍贯",oldUser.getNativeProvinceDesc(), newUser.getNativeProvinceDesc() ))
		.append(getFieldChange(isNew, "户口所在地",oldUser.getNativePlaceDesc(), newUser.getNativePlaceDesc()))
		.append(getFieldChange(isNew, "婚姻状况",oldUser.getMarrigeStatusCd(), newUser.getMarrigeStatusCd()))
		.append(getFieldChange(isNew, "学历",oldUser.getSchoolRecordCd(), newUser.getSchoolRecordCd() ))
		.append(getFieldChange(isNew, "毕业院校",oldUser.getGradSchoolDesc(), newUser.getGradSchoolDesc() ))
		.append(getFieldChange(isNew, "专业",oldUser.getMajorDesc(), newUser.getMajorDesc()))
		.append(getFieldChange(isNew, "加入本单位时间",getDt(oldUser.getAttendWorkDate()), getDt(newUser.getAttendWorkDate())))
		.append(getFieldChange(isNew, "员工类型",oldUser.getMemberTypeCd(), newUser.getMemberTypeCd() ))
		.append(getFieldChange(isNew, "职务",oldUser.getWorkDutyDesc(), newUser.getWorkDutyDesc() ))
//		.append(getFieldChange(isNew, "职位",oldUser.getPositionCd(), plasUser.getPositionCd() ))
		.append(getFieldChange(isNew, "职称",oldUser.getProfessionTypeCd(), newUser.getProfessionTypeCd() ))
		.append(getFieldChange(isNew, "政治面貌",oldUser.getPoliticsCd(), newUser.getPoliticsCd() ))
		.append(getFieldChange(isNew, "其他类型",oldUser.getOtherTypeCd(), newUser.getOtherTypeCd()))
//		.append(getFieldChange(isNew, "邮箱地址",oldUser.getEmail(), newUser.getEmail()))
		.append(getFieldChange(isNew, "办公电话号码",oldUser.getFixedPhone(), newUser.getFixedPhone() ))
		.append(getFieldChange(isNew, "常用手机号码",oldUser.getMobilePhone(), newUser.getMobilePhone()))
		.append(getFieldChange(isNew, "其他电话号码",oldUser.getMobilePhone2(), newUser.getMobilePhone2()))
		.append(getFieldChange(isNew, "证件类型",oldUser.getIdCardTypeCd(), newUser.getIdCardTypeCd() ))
		.append(getFieldChange(isNew, "是否特殊用户",oldUser.getSpecialUserFlg(), newUser.getSpecialUserFlg() ))
//		.append(getFieldChange(isNew, "用户类型",oldUser.getUserTypeCd(), newUser.getUserTypeCd() ))
		.append(getFieldChange(isNew, "用户信息来源类型",oldUser.getSourceTypeCd(), newUser.getSourceTypeCd() ))
		.append(getFieldChange(isNew, "默认凭证号",oldUser.getDefaultCredenc(), newUser.getDefaultCredenc() ))
		
//		.append(getFieldChange(isNew, "认证方式",oldUser.getAuthenticTypeCd(), newUser.getAuthenticTypeCd()))
//		.append(getFieldChange(isNew, "登录密码",oldUser.getLoginInPassword(), newUser.getLoginInPassword()))
//		.append(getFieldChange(isNew, "用户锁定时间",getDt(oldUser.getLockedDate()), getDt(newUser.getLockedDate()) ))
//		.append(getFieldChange(isNew, "上次登录时间",getDt(oldUser.getLastLoginDate()), getDt(newUser.getLastLoginDate())))
//		.append(getFieldChange(isNew, "上次签退时间",getDt(oldUser.getLastLogoutDate()), getDt(newUser.getLastLogoutDate()) ))
//		.append(getFieldChange(isNew, "上次访问IP地址",oldUser.getLastLoginIp(), newUser.getLastLoginIp()))
//		.append(getFieldChange(isNew, "用户生效日期",getDt(oldUser.getEffectDate()), getDt(newUser.getEffectDate())) )
//		.append(getFieldChange(isNew, "用户失效日期 ",getDt(oldUser.getInvalidDate()), getDt(newUser.getInvalidDate())))
//		.append(getFieldChange(isNew, "密码连续错误次数",getInt(oldUser.getFailureTimes()), getInt(newUser.getFailureTimes())))
//		.append(getFieldChange(isNew, "绑定mac地址",oldUser.getMacAddress(), newUser.getMacAddress() ))
//		.append(getFieldChange(isNew, "Mac码是否锁定",oldUser.getMacLockedFlg(), newUser.getMacLockedFlg() ))
//		.append(getFieldChange(isNew, "开通邮箱",oldUser.getEmailFlg(), newUser.getEmailFlg() ))
//		.append(getFieldChange(isNew, "是否同步邮箱密码",oldUser.getEmailPasswordSetFlg(), newUser.getEmailPasswordSetFlg()))
//		.append(getFieldChange(isNew, "开通EAS",oldUser.getEasFlg(), newUser.getEasFlg() ))
//		.append(getFieldChange(isNew, "是否同步EAS密码",oldUser.getEasPasswordSetFlg(), newUser.getEasPasswordSetFlg()));
		
		.append(getFieldChange(isNew, "职级",oldUser.getPermissionLevelCd(), newUser.getPermissionLevelCd()));
//		.append(getFieldChange(isNew, "显示序号",getInt(oldUser.getDispOrderNo()), getInt(plasUser.getDispOrderNo())))
//		.append(getFieldChange(isNew, "备注",oldUser.getRemark(), plasUser.getRemark() ))
//		.append(getFieldChange(isNew, "物理机构上级(内码)",oldUser.getUaapPhysicalOrgCd(), plasUser.getOrgCdPhysical()))
//		.append(getFieldChange(isNew, "逻辑上级机构(内码)",oldUser.getOrgCd(), plasUser.getPlasOrg().getOrgCd()))
//		.append(getFieldChange(isNew, "逻辑上级名称",oldUser.getOrgName(), plasUser.getPlasOrg().getOrgName() ));
//		.append(getFieldChange(isNew, "逻辑所属中心(内码)",oldUser.getCenterOrgCd(), plasUser.getCenterOrgCdPhysical()));
		//.append(getFieldChange(isNew, "邮件签名",oldUser.getEmailSignContent(), plasUser.getEmailSignContent() ))
		
		//.append(getFieldChange(isNew, "",oldUser.getCreator(), newUser.getCreator()))
		//.append(getFieldChange(isNew, "",oldUser.getCreatedDeptCd(), newUser.getcreatedDeptCd))
		//.append(getFieldChange(isNew, "",oldUser.getcreatedDate(), newUser.getcreatedDate))
		//.append(getFieldChange(isNew, "",oldUser.getUpdator(), newUser.getUpdator()))
		//.append(getFieldChange(isNew, "",oldUser.getUpdatedDeptCd(), newUser.getUpdatedDeptCd()))
		//.append(getFieldChange(isNew, "",oldUser.getupdatedDate, newUser.getupdatedDate))
		//.append(getFieldChange(isNew, "",oldUser.getrecordVersion, newUser.getrecordVersion))
		
		// 职级发生变化
		try{
			String strChangeLevel = getFieldChange(isNew, "职级",oldUser.getPermissionLevelCd(), newUser.getPermissionLevelCd());
			if(StringUtils.isNotBlank(strChangeLevel)){
				//TODO
//				BisSecGroupUserRelManager secGroupUserRelManager = SpringContextHolder.getBean("secGroupUserRelManager");
//				secGroupUserRelManager.saveChangePermLevel(plasUser.getPlasUserId(), oldUser.getPermissionLevelCd(), newUser.getp());
			}
		}catch(Exception ex){
			log.error(" 同步用户("+newUser.getUiid()+","+ newUser.getUserName()+")的用户级出现异常!" + ex.getCause());
		}
		
		return sb.toString();
	}

	/**
	 * 获取变化的机构信息 
	 * @param isNew 是否新建
	 * @param oldOrg 旧值
	 * @param newOrg 新值
	 * @return
	 */
	public static String getOperateOrg(boolean isNew, LogPlasOrg oldOrg, PlasOrg newOrg) {
		
		if( oldOrg == null){
			oldOrg = new LogPlasOrg();
		}
		 
		StringBuffer sb = new StringBuffer() 
		.append(getFieldChange(isNew,"机构内部编号",oldOrg.getOrgCd(),newOrg.getOrgCd()))
		.append(getFieldChange(isNew,"机构业务编号",oldOrg.getOrgBizCd(),newOrg.getOrgBizCd()))
		.append(getFieldChange(isNew,"机构名称",oldOrg.getOrgName(),newOrg.getOrgName()))
		.append(getFieldChange(isNew,"机构简称",oldOrg.getShortOrgName(),newOrg.getShortOrgName()))
		.append(getFieldChange(isNew,"机构类型",oldOrg.getOrgTypeCd(),newOrg.getOrgTypeCd()))
		.append(getFieldChange(isNew,"机构电话",oldOrg.getPhoneDesc(),newOrg.getPhoneDesc()))
		.append(getFieldChange(isNew,"传真",oldOrg.getFaxDesc(),newOrg.getFaxDesc()))
		.append(getFieldChange(isNew,"主管编号",oldOrg.getOrgMgrId(),newOrg.getOrgMgrId()))
		.append(getFieldChange(isNew,"机构负责人-系统职位",oldOrg.getOrgMgrSysPosId(),newOrg.getOrgMgrSysPosId()))
		.append(getFieldChange(isNew,"机构节点类型",oldOrg.getNodeLevelCd(),newOrg.getNodeLevelCd()))
		.append(getFieldChange(isNew,"机构种类",oldOrg.getOrgKindCd(),newOrg.getOrgKindCd()))
//		.append(getFieldChange(isNew,"上级逻辑机构编号",oldOrg.getParentOrgCd(),newOrg.getParentOrgCdLogical()))
//		.append(getFieldChange(isNew,"上级物理机构编号",oldOrg.getParentPhysicalOrgCd(),newOrg.getParentOrgCdPhysical()))
//		.append(getFieldChange(isNew,"上级主管领导",oldOrg.getParentMainMgrCd(),newOrg.getParentMainMgrCd()))
//		.append(getFieldChange(isNew,"上级分管领导",oldOrg.getParentPartMgrCd(),newOrg.getParentPartMgrCd()))
//		.append(getFieldChange(isNew,"是否上市",oldOrg.getPubFlg(),newOrg.getPubFlg()));
		.append(getFieldChange(isNew,"显示序号",getInt(oldOrg.getSequenceNo()),getInt(BigDecimal.valueOf(newOrg.getSequenceNo()))))
		.append(getFieldChange(isNew,"备注",oldOrg.getRemark(),newOrg.getRemark()));
				
//		.append(getFieldChange(isNew,"创建人",oldOrg.getCreator(),newOrg.getCreator())
//		.append(getFieldChange(isNew,"创建部门CD",oldOrg.getCreatedDeptCd(),newOrg.getCreatedDeptCd())
//		.append(getFieldChange(isNew,"创建时间",getDt(oldOrg.getCreatedDate()),getDt(newOrg.getCreatedDate()))
//		.append(getFieldChange(isNew,"最后修改人",oldOrg.getUpdator(),newOrg.getUpdator())
//		.append(getFieldChange(isNew,"最后修改时间",oldOrg.getUpdatedDeptCd(),newOrg.getUpdatedDeptCd())
//		.append(getFieldChange(isNew,"最后修改部门CD",getDt(oldOrg.getUpdatedDate()),getDt(newOrg.getUpdatedDate()))
//		.append(getFieldChange(isNew,"版本",oldOrg.getRecordVersion(),newOrg.getRecordVersion())
		
	      
		return sb.toString();
	}
	/**
	 * 获取变化的机构信息 
	 * @param isNew 是否新建
	 * @param oldRole 旧值
	 * @param newRole 新值
	 * @return
	 */
	public static String getOperateRole(boolean isNew, LogPlasRole oldRole, PlasRole newRole) {
		
		if( oldRole == null){
			oldRole = new LogPlasRole();
		}
		 
		StringBuffer sb = new StringBuffer()
//		.append(getFieldChange(isNew,"角色ID",oldRole.getUaapRoleId(),newRole.getUaapRoleId()))
		.append(getFieldChange(isNew,"角色编号",oldRole.getRoleCd(),newRole.getRoleCd()))
		.append(getFieldChange(isNew,"角色业务编号",oldRole.getRoleBizCd(),newRole.getRoleBizCd()))
		.append(getFieldChange(isNew,"角色名称",oldRole.getRoleName(),newRole.getRoleName()))
//		.append(getFieldChange(isNew,"角色类型编号",oldRole.getRoleTypeCd(),newRole.getRoleTypeCd()))
//		.append(getFieldChange(isNew,"角色类型名称",oldRole.getRoleTypeName(),newRole.getroleTypeName()))
//		.append(getFieldChange(isNew,"显示序号 ",getInt(oldRole.getDispOrderNo()),getInt(newRole.getDispOrderNo())))
		.append(getFieldChange(isNew,"备注",oldRole.getRemark(),newRole.getRemark()));
		
//		.append(getFieldChange(isNew,"应用业务编号",oldRole.getAppBizCd(),newRole.getUaapApp().getAppBizCd()))
//		.append(getFieldChange(isNew,"应用英文名称",oldRole.getAppEngName(),newRole.getUaapApp().getAppEngName()))
//		.append(getFieldChange(isNew,"应用中文名称",oldRole.getAppChnName(),newRole.getUaapApp().getAppChnName()));
				
//		.append(getFieldChange(isNew,"创建人",oldOrg.getCreator(),newOrg.getCreator())
//		.append(getFieldChange(isNew,"创建部门CD",oldOrg.getCreatedDeptCd(),newOrg.getCreatedDeptCd())
//		.append(getFieldChange(isNew,"创建时间",getDt(oldOrg.getCreatedDate()),getDt(newOrg.getCreatedDate()))
//		.append(getFieldChange(isNew,"最后修改人",oldOrg.getUpdator(),newOrg.getUpdator())
//		.append(getFieldChange(isNew,"最后修改时间",oldOrg.getUpdatedDeptCd(),newOrg.getUpdatedDeptCd())
//		.append(getFieldChange(isNew,"最后修改部门CD",getDt(oldOrg.getUpdatedDate()),getDt(newOrg.getUpdatedDate()))
//		.append(getFieldChange(isNew,"版本",oldOrg.getRecordVersion(),newOrg.getRecordVersion())

		return sb.toString();
	}

	/**
	 * 获取变化的附件信息
	 * @param isNew
	 * @param oldFile
	 * @param newFile
	 * @return
	 */
	public static String getOperateAttachFile(boolean isNew, AppAttachFile oldFile, AppAttachFile newFile) {
		
		if( oldFile == null){
			oldFile = new AppAttachFile();
		}
		StringBuffer sb = new StringBuffer()      
//		  .append(getFieldChange(isNew,"附件ID",oldFile.getAppAttachFileId(), newFile.getAppAttachFileId()))
	      .append(getFieldChange(isNew,"附件实体ID",oldFile.getBizEntityId(), newFile.getBizEntityId()))
	      .append(getFieldChange(isNew,"模块编号",oldFile.getBizModuleCd(), newFile.getBizModuleCd())) 
	      .append(getFieldChange(isNew,"名称",oldFile.getFileName(), newFile.getFileName()))
	      .append(getFieldChange(isNew,"实际名称",oldFile.getRealFileName(), newFile.getRealFileName()))
	      .append(getFieldChange(isNew,"路径",oldFile.getFilePath(), newFile.getFilePath()))
	      .append(getFieldChange(isNew,"类型",oldFile.getFileTypeName(), newFile.getFileTypeName()))
	      .append(getFieldChange(isNew,"大小",getBig(oldFile.getFileSize()),getBig(newFile.getFileSize())))
	      .append(getFieldChange(isNew,"状态",oldFile.getStatusCd(), newFile.getStatusCd()))
	      .append(getFieldChange(isNew,"备注",oldFile.getRemark(), newFile.getRemark()));
//	      .append(getFieldChange(isNew,"",oldFile.getCreator(), newFile.getCreator()))
//	      .append(getFieldChange(isNew,"",oldFile.getCreatedDeptCd(), newFile.getCreatedDeptCd()))
//	      .append(getFieldChange(isNew,"",oldFile.getCreatedDate(), newFile.getCreatedDate()))
//	      .append(getFieldChange(isNew,"",oldFile.getUpdator(), newFile.getUpdator()))
//	      .append(getFieldChange(isNew,"",oldFile.getUpdatedDeptCd(), newFile.getUpdatedDeptCd()))
//	      .append(getFieldChange(isNew,"",oldFile.getUpdatedDate(), newFile.getUpdatedDate()))
//	      .append(getFieldChange(isNew,"",oldFile.getRecordVersion(), newFile.getRecordVersion()))
	    return sb.toString();
	}
	
	
	/**日期时分秒转字符串
	 * @param date
	 * @return
	 */
	public static String getDt(Date date){
		if(date == null)
			return "";
		else 
			return DateParser.formatDate(date,false);
	}
	/**
	 * @param date
	 * @param format 格式有"yyyy-MM-dd/yyyy-MM-dd HH:mm/yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getDt(Date date,String format){
		if(date == null)
			return "";
		else 
			return DateParser.formatDate(date,format);
	}
	/**
	 * 整型转字符串
	 * @param in
	 * @return
	 */
	public static String getInt(BigDecimal in) {
		if(in == null)
			return "";
		else 
			return in.toString();
	}
	/**
	 * 整型转字符串
	 * @param big
	 * @return
	 */
	public static String getBig(BigDecimal big){
		if(big == null)
			return "";
		else 
			return big.toString();
	}
	
	/**
	 * 取字段变化情况
	 * @param fieldName 字段名称
	 * @param oldValue 旧值
	 * @param newValue 新值
	 * @return
	 */
	public static String getFieldChange(boolean isNew, String fieldName,String oldValue, String newValue){
		if( oldValue == null){
			oldValue = "";
		}
		if( newValue == null){
			newValue = "";
		}
		if(!oldValue.equals(newValue))
			return new StringBuffer(fieldName).append(":").append(oldValue.trim()).append(isNew ? "": "->").append(newValue.trim()).append(",").toString();
		else
			return "";
	}
	
	
	/* *********************************** 与日志有关 **************************************************/

	
	/**
	 * 转换比对前用户对象
	 * @param user
	 * @return
	 */
	public static LogPlasUser transfer(PlasUser src){
		if (src == null)
			return null;
		try {
			LogPlasUser logUser = new LogPlasUser();
			
			PropertyUtils.copyProperties(logUser, src);// PropertyUtils.copyProperties(dest,orig)
//			logUser.setOrgCd(uaapUser.getUaapOrg().getOrgCd());
//			logUser.setOrgBizCd(uaapUser.getUaapOrg().getOrgBizCd());
//			logUser.setOrgName(uaapUser.getUaapOrg().getOrgName());
			
			
			
			
			return logUser;
		} catch (Exception ex) {
			log.error(" 转换比对前用户对象出现异常." + ex.getMessage());
			return null;
		}
	}
	/**
	 * 转换比对前机构对象
	 * @param src
	 * @return
	 */
	public static LogPlasOrg transfer(PlasOrg src){
		if (src == null)
			return null;
		try {
			LogPlasOrg logOrg = new LogPlasOrg();
			PropertyUtils.copyProperties(logOrg, src);// PropertyUtils.copyProperties(dest,orig)
			return logOrg;
		} catch (Exception ex) {
			log.error(" 转换比对前机构对象出现异常." + ex.getMessage());
			return null;
		}
	}
	/**
	 * 转换比对前角色对象
	 * @param uaapRole
	 * @return
	 */
	public static LogPlasRole transfer(PlasRole uaapRole){
		if (uaapRole == null)
			return null;
		try {
			LogPlasRole logRole = new LogPlasRole();
			PropertyUtils.copyProperties(logRole, uaapRole);// PropertyUtils.copyProperties(dest,orig)
			
			
			return logRole;
		} catch (Exception ex) {
			log.error(" 转换比对前角色对象出现异常." + ex.getMessage());
			return null;
		}
	}

	/**
	 * 功能：判断新旧用户邮件信息是否发生变化,若发生变化,则同步到邮件信息
	 * @param isNew
	 * @param oldUser
	 * @param user
	 * 比较内容:uiid,mobile, deptDesc, positionDesc, telPhone, dispName
	 * @return
	 */
	public static boolean isEmailRefChange(boolean isNew,LogPlasUser oldUser,PlasUser user){

		StringBuffer sb = new StringBuffer()      
	      .append(getFieldChange(isNew,"mobile1",oldUser.getMobilePhone(), oldUser.getMobilePhone()))
	      .append(getFieldChange(isNew,"mobile2",oldUser.getMobilePhone2(), oldUser.getMobilePhone2()))
//	      TODO
//	      .append(getFieldChange(isNew,"deptDesc",oldUser.getUaapPhysicalOrgCd(), oldUser.getUaapPhysicalOrgCd()))
//	      .append(getFieldChange(isNew,"positionDesc",oldUser.getPositionCd(), oldUser.getPositionCd()))
	      .append(getFieldChange(isNew,"fixPhone",oldUser.getFixedPhone(), oldUser.getFixedPhone()))
	      .append(getFieldChange(isNew,"userName",oldUser.getUserName(), oldUser.getFixedPhone()));
	      
	    if(StringUtils.isNotBlank(sb.toString()))
			return true;
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
