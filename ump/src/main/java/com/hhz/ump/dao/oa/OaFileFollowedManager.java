package com.hhz.ump.dao.oa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.oa.OaFileFollowed;
import com.hhz.ump.util.CodeNameUtil;

@Service
@Transactional
public class OaFileFollowedManager extends BaseService<OaFileFollowed, String> {
    
	public static final short STATUS_NEW = 1; // 状态：新建
	public static final short STATUS_SENT = 2; // 状态：发送未接受
	public static final short STATUS_CONFIRMED = 3; // 状态：已接收/被退回
	public static final short STATUS_COMPLETED = 4; // 状态：已完成流转
    
    @Autowired
    private OaFileFollowedDao oaFileFollowedDao;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;

	private void update2JbpmTask(OaFileFollowed oaFileFollowed) {
		JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(oaFileFollowed.getOaFileFollowId());
		if (oaFileFollowed.getStatus().shortValue()==STATUS_SENT) {
			if (jbpmTask == null) {
				jbpmTask = new JbpmTask();
				jbpmTask.setModuleCd("oaFileFollowed");
				jbpmTask.setModuleName("文件跟踪");
				jbpmTask.setJbpmCd(oaFileFollowed.getSerialNumber() + oaFileFollowed.getSerialNumberNum());
				jbpmTask.setUserCd(oaFileFollowed.getCurrentUiid());
				jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(oaFileFollowed.getCurrentUiid()));
				jbpmTask.setApplyDate(oaFileFollowed.getUpdatedDate());
				jbpmTask.setJbpmId(oaFileFollowed.getOaFileFollowId());
			}
			jbpmTask.setRemark(oaFileFollowed.getContent());
			jbpmTask.setStatusCd(oaFileFollowed.getStatus().toString());
			jbpmTaskManager.saveJbpmTask(jbpmTask);
			jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
			JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
			jbpmTaskCandidate.setUserCd(oaFileFollowed.getReceiver());
			jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(oaFileFollowed.getReceiver()));
			jbpmTaskCandidate.setJbpmTask(jbpmTask);
			jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
		}else{
			if (jbpmTask != null) {
				jbpmTaskManager.delete(jbpmTask);
			}
		}
	}

	/**
	 * 生成某个部门文件跟踪记录的序列号前缀
	 * 
	 * @param deptCd
	 * @return
	 */
    public String getFileSerailNumber(String deptCd) {
	String pref = "WJ-";
	String deptShortNm = CodeNameUtil.getDeptShortNameByCd(deptCd);
	if (StringUtils.isNotBlank(deptShortNm)) {
	    pref = pref + deptShortNm + "-";
	}
	return pref;
    }
    
    /**
     * 批量送出
     * @param batchData
     * @return
     */
    @Transactional
    public boolean batchSend(String batchData) {
	String fileId = null;
	String receiverId = null;
	OaFileFollowed file = null;
	
	JSONArray arr = fetchData(batchData);
	for (int i = 0; i < arr.size(); i ++) {
	    JSONObject o = arr.getJSONObject(i);
	    fileId = o.getString("id");
	    receiverId = o.getString("receiverId");
	    file = getEntity(fileId);
	    
	    if (StringUtils.isBlank(fileId) || StringUtils.isBlank(receiverId)) {
		continue;
	    }
	    
	    file.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_SENT));
	    String curId = file.getCurrentUiid();
	    file.setReceiver(receiverId);
	    StringBuilder desc = new StringBuilder(file.getScheduleDescribe2());
	    desc.append("<BR>" + CodeNameUtil.getUserNameByCd(curId)).append("将文件送到")
	    	.append(CodeNameUtil.getUserNameByCd(receiverId))
	    	.append("(" + DateOperator.formatDate(new Date(), "MM-dd HH:mm") + ")");
	    file.setScheduleDescribe2(desc.toString());
	    String rm = file.getRemark();
	    if (StringUtils.isNotBlank(rm)) {
		if (rm.indexOf("," + receiverId + ",") == -1) {
		    file.setRemark(rm + receiverId + ",");
		}
	    } else {
		file.setRemark("," + receiverId + ",");
	    }
	    saveOaFileFollowed(file);
	}
	return true;
    }
    
    /**
     * 批量确认
     * @param batchData
     * @return
     */
    @Transactional
    public boolean batchConfirm(String batchData) {
	String fileId = null;
	OaFileFollowed file = null;
	
	JSONArray arr = fetchData(batchData);
	for (int i = 0; i < arr.size(); i ++) {
	    JSONObject o = arr.getJSONObject(i);
	    fileId = o.getString("id");
	    file = getEntity(fileId);
	    String reveiver = file.getReceiver();
	    // 只有当前人跟接收人是同一个人时才能接收
	    if (SpringSecurityUtils.getCurrentUiid().equalsIgnoreCase(reveiver)) {
		StringBuilder desc = new StringBuilder(file.getScheduleDescribe2());
		desc.append("<BR>" + CodeNameUtil.getUserNameByCd(reveiver) + "文件收到")
			.append("(" + DateOperator.formatDate(new Date(), "MM-dd HH:mm") + ")");
		file.setScheduleDescribe2(desc.toString());
		file.setCurrentUiid(reveiver);
		file.setReceiver(null);
		file.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_CONFIRMED));
		saveOaFileFollowed(file);
	    }
	}
	
	return true;
    }
    
    /**
     * 批量删除
     * @param batchData
     * @return
     */
    @Transactional
    public boolean batchDelete(String batchData) {
	String fileId = null;
	OaFileFollowed file = null;
	BigDecimal status = null;
	
	JSONArray arr = fetchData(batchData);
	for (int i = 0; i < arr.size(); i ++) {
	    JSONObject o = arr.getJSONObject(i);
	    fileId = o.getString("id");
	    file = getEntity(fileId);
	    
	    status = file.getStatus();
	    // 1. 如果是完成状态，则不能删除
	    // 2. 如果是新建状态，则管理员和创建人都可以删除，而且是物理删除
	    // 3. 如果是其他状态，则只有管理员可以删除，此时是逻辑删除
	    if (status != null) {
		switch (status.shortValue()) {
		case STATUS_NEW:
		    if (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN") 
			    || SpringSecurityUtils.getCurrentUiid().equals(file.getCreator())) {
			delete(file);
		    }
		    break;
		case STATUS_COMPLETED:
		    break;
		default :
		    if (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) {
			file.setDeleteFlg("1");
			saveOaFileFollowed(file);
		    }
		    break;
		}
	    }
	    
	    if (file.getStatus().shortValue() == STATUS_NEW) {
		delete(file);
	    } else {
		file.setDeleteFlg("1");
		saveOaFileFollowed(file);
	    }
	}
	return true;
    }
    
    @SuppressWarnings("cast")
    private JSONArray fetchData(String batchData) {
	if (StringUtils.isNotBlank(batchData)) {
	    JSONObject jo = (JSONObject) JSONSerializer.toJSON(batchData);
	    if (jo != null)
			return (JSONArray)jo.getJSONArray("data");
	}
	return null;
    }
	@SuppressWarnings("unchecked")
	public void updateRemark() throws FileNotFoundException {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "select OA_FILE_FOLLOW_ID,SCHEDULE_DESCRIBE2 from ump.OA_FILE_FOLLOWED";
		List fileList = oaFileFollowedDao.findBySql(sql, params);
		File file = new File("E:\\未识别的人名.txt");
		OutputStream os = new FileOutputStream("E:\\未识别的人名.txt");
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
		for (int i = 0; i < fileList.size(); i++) {
			Object[] objFile = (Object[]) fileList.get(i);
			String describe = (String) objFile[1];
			String fileId = (String) objFile[0];
			if (describe != null && !"".equals(describe)) {
				String[] desStr = describe.split("<BR>");
				List<String> user = new ArrayList<String>();
				for (int j = 0; j < desStr.length; j++) {
					if (desStr[j].indexOf("文件建立") > 0) {
						// 得到用户名
						String[] userStr = desStr[j].split("文件建立");
						user.add(userStr[0]);
					} else if (desStr[j].indexOf("将文件送到") > 0) {
						// 得到用户名
						String[] userStr = desStr[j].split("将文件送到");
						for (int gg = 0; gg < userStr.length; gg++) {
							if (userStr[gg].indexOf("(") > 0) {
								userStr[gg] = userStr[gg].substring(0, userStr[gg].indexOf("("));
								boolean haveUser = false;
								for (int k = 0; k < user.size(); k++) {
									if (userStr[gg].equals(user.get(k))) {
										haveUser = true;
									}
								}
								if (!haveUser) {
									user.add(userStr[gg]);
								}
							}
						}
					}
				}
				if (user != null && user.size() > 0) {
					StringBuffer remarkNow = new StringBuffer("");
					for (int k = 0; k < user.size(); k++) {
						String sqlUser = "select uiid from UAAP.uaap_user where user_name='" + user.get(k) + "'";
						List userList = oaFileFollowedDao.findBySql(sqlUser, params);
						if (userList != null && userList.size() > 0) {
							if (userList.size() == 1) {
								String curUiid = (String) userList.get(0);
								if ("".equals(remarkNow.toString())) {
									remarkNow.append("," + curUiid + ",");
								} else {
									remarkNow.append(curUiid + ",");
								}
							} else {
								writer.append("多用户人员 ：" + user.get(k) + "  id:"
										+ fileId);
							}
						} else {
							// 找不到用户的情况,写入文件
							writer.append("找不到用户人员 ：" + user.get(k) + "  id:"
									+ fileId);
						}

					}
					// String updateAcc =
					// "update ump.OA_FILE_FOLLOWED set remark ='"
					// + remarkNow.toString()
					// + "' where OA_FILE_FOLLOW_ID='" + fileId + "'";
					// oaFileFollowedDao.findBySql(updateAcc, params);
					// getDao().batchExecute(updateAcc, params);
					OaFileFollowed followed = getOaFileFollowed(fileId);
					followed.setRemark(remarkNow.toString());
					saveOaFileFollowed(followed);

				}
			}
			writer.append("                                         ");
		}
		writer.flush();

	}

	@SuppressWarnings("unchecked")
	public void updatePersonRemark(String curUser, String projectSn) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "select OA_FILE_FOLLOW_ID,REMARK from ump.OA_FILE_FOLLOWED WHERE PROJECT_SN='"
				+ projectSn + "'";
		List fileList = oaFileFollowedDao.findBySql(sql, params);
		for (int i = 0; i < fileList.size(); i++) {
			Object[] objFile = (Object[]) fileList.get(i);
			String remark = (String) objFile[1];
			String fileId = (String) objFile[0];
			OaFileFollowed followed = getOaFileFollowed(fileId);
			if (remark == null || "".equals(remark)) {
				followed.setRemark("," + curUser + ",");
			} else {
				followed.setRemark(remark + curUser + ",");
			}
			saveOaFileFollowed(followed);
		}
	}

	/**
	 * 生成某个部门新建文件跟踪记录的序号，每次新增序号递增1
	 * 
	 * @param deptCd
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public int getFileSerialNumberNum(String serialNumber) {
	String hql = "select max(serialNumberNum) from OaFileFollowed where serialNumber='" + serialNumber + "'";
	List result = getDao().getSession().createQuery(hql).list();
	BigDecimal m = (BigDecimal)result.get(0);
	int c = 0;
	if (m != null) {
	    c = Integer.parseInt(m.toString());
	}
	return c + 1;
    }

    public void saveOaFileFollowed(OaFileFollowed oaFileFollowed) {
	PowerUtils.setEmptyStr2Null(oaFileFollowed);
	oaFileFollowedDao.save(oaFileFollowed);
	update2JbpmTask(oaFileFollowed);
    }

    public void deleteOaFileFollowed(String id) {
	oaFileFollowedDao.delete(id);
	jbpmTaskManager.deleteByEntityId(id);
    }

    @Override
    public HibernateDao<OaFileFollowed, String> getDao() {
	return oaFileFollowedDao;
    }

    @Transactional(readOnly = true)
    public OaFileFollowed getOaFileFollowed(String id) {
	return oaFileFollowedDao.get(id);
    }    
}
