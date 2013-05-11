package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveStep;
import com.hhz.ump.entity.res.ResConditonType;
import com.hhz.ump.web.res.baseBean.ResApproverUser;

@Service
@Transactional
public class ResApproveStepManager extends BaseService<ResApproveStep, String> {
	@Autowired
	private ResApproveStepDao resApproveStepDao;

	public void saveResApproveStep(ResApproveStep resApproveStep) {
		PowerUtils.setEmptyStr2Null(resApproveStep);
		resApproveStepDao.save(resApproveStep);
	}

	public void deleteResApproveStep(String id) {
		resApproveStepDao.delete(id);
	}

	public void saveResApproveSteps(List<ResApproveStep> resApproveSteps, ResConditonType resConditonType) {
		for (ResApproveStep resApproveStep : resApproveSteps) {
			if (resApproveStep.getResConditonType() == null) {
				resApproveStep.setResConditonType(resConditonType);
			}
			this.saveResApproveStep(resApproveStep);
		}
	}

	public void deleteResApproveSteps(List<ResApproveStep> resApproveSteps) {
		for (ResApproveStep resApproveStep : resApproveSteps) {
			resApproveStep = this.getEntity(resApproveStep.getResApproveStepId());
			this.delete(resApproveStep);
		}
	}

	public void saveOrDeleteResApproveSteps(List<ResApproveStep> insertRecords, List<ResApproveStep> updateRecords,
			List<ResApproveStep> deleteRecords, ResConditonType resConditonType) {
		this.saveResApproveSteps(insertRecords, resConditonType);
		this.saveResApproveSteps(updateRecords, resConditonType);
		this.deleteResApproveSteps(deleteRecords);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResApproveStep, String> getDao() {
		return resApproveStepDao;
	}

	public List<ResApproveStep> getStepsByGroupCd(String resAuthTypeCd, String conditionCd, String groupCd) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ResApproveStep where resConditonType.resAuthType.authTypeCd=?").append(
				" and resConditonType.conditionCd=? and groupNodeCd=? ");
		List<ResApproveStep> approveSteps = find(hql.toString(), resAuthTypeCd, conditionCd, groupCd);
		return approveSteps;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public ResApproveStep getCurStep(String resAuthTypeCd, String conditionCd, String curNodeCd, List<ResApproverUser> approverUsers) {
		ResApproveStep approveStep = null;
		if (approverUsers == null || approverUsers.size() == 0) {
			StringBuffer hql = new StringBuffer();
			hql.append("from ResApproveStep where resConditonType.resAuthType.authTypeCd=?").append(
					" and resConditonType.conditionCd=? and (nodeCd=? or groupNodeCd=?) order by approveLevel asc");
			List<ResApproveStep> approveSteps = find(hql.toString(), resAuthTypeCd, conditionCd, curNodeCd, curNodeCd);
			if (approveSteps.size() != 0) {
				approveStep = approveSteps.get(0);
			} else
				throw new RuntimeException("找不到审批节点");
		} else {
			String[] str = curNodeCd.split(":");
			int level = -1;
			if (str.length > 1) {
				level = Integer.valueOf(str[1]);
			}
			int i = 1;
			for (Iterator<ResApproverUser> iterator = approverUsers.iterator(); iterator.hasNext();) {
				ResApproverUser step = iterator.next();

				if (StringUtils.equals(SpringSecurityUtils.getCurrentUiid(), step.getApproverUserCd()) && level == i) {
					approveStep = new ResApproveStep();
					approveStep.setApproveLevel(Long.valueOf(i));
					approveStep.setNodeCd(step.getApproverUserName() + ":" + String.valueOf(i));
					break;
				}
				i++;
			}
		}
		return approveStep;
	}

	/**
	 * 取得所有步骤,在界面显示审批流
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ResApproveStep> getSteps(String resAuthTypeCd, String conditionCd, List<ResApproverUser> approverUsers) {
		List<ResApproveStep> approveSteps = new ArrayList<ResApproveStep>();
		if (approverUsers == null || approverUsers.size() == 0) {
			StringBuffer hql = new StringBuffer();
			hql.append("from ResApproveStep where resConditonType.resAuthType.authTypeCd=?").append(
					" and resConditonType.conditionCd=? order by approveLevel asc");
			approveSteps = find(hql.toString(), resAuthTypeCd, conditionCd);
		} else {
			int i = 1;
			for (Iterator<ResApproverUser> iterator = approverUsers.iterator(); iterator.hasNext();) {
				ResApproverUser step = iterator.next();
				ResApproveStep approveStep = new ResApproveStep();
				approveStep.setApproveLevel(Long.valueOf(i));
				approveStep.setNodeCd(step.getApproverUserName() + ":" + String.valueOf(i));
				approveSteps.add(approveStep);
				i++;
			}
		}
		return approveSteps;
	}

	/**
	 * 取得下一步骤，如果下一步骤属于某个小组，则把审批节点改为该节点
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public ResApproveStep getNextStep(String resAuthTypeCd, String conditionCd, String curNodeCd, List<ResApproverUser> approverUsers) {
		ResApproveStep approveStep = null;
		if (approverUsers == null || approverUsers.size() == 0) {
			StringBuffer hql = new StringBuffer();
			hql.append("from ResApproveStep where resConditonType.resAuthType.authTypeCd=?").append(
					" and resConditonType.conditionCd=? order by approveLevel asc");
			List<ResApproveStep> approveSteps = find(hql.toString(), resAuthTypeCd, conditionCd);
			boolean found = false;
			if (curNodeCd == null) {
				approveStep = approveSteps.get(0);
			} else {
				for (ResApproveStep resApproveStep : approveSteps) {
					if (found) {
						approveStep = resApproveStep;
						break;
					}
					if (StringUtils.equals(resApproveStep.getNodeCd(), curNodeCd)) {
						found = true;
					}
				}
			}
		} else {
			String[] str = curNodeCd.split(":");
			int level = -1;
			if (str.length > 1) {
				level = Integer.valueOf(str[1]);
			}
			int i = 1;
			for (Iterator<ResApproverUser> iterator = approverUsers.iterator(); iterator.hasNext();) {
				ResApproverUser step = iterator.next();

				if (i > level) {
					approveStep = new ResApproveStep();
					approveStep.setApproveLevel(Long.valueOf(i));
					approveStep.setNodeCd(step.getApproverUserName() + ":" + String.valueOf(i));
					break;
				}
				i++;
			}
		}
		return approveStep;
	}
}
