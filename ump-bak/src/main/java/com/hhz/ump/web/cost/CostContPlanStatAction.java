package com.hhz.ump.web.cost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostContPlanStatManager;
import com.hhz.ump.entity.cost.CostContPlanStat;
/**
 * 处理合约汇总转发
 * @author Aspenn
 *
 */
public class CostContPlanStatAction  extends CrudActionSupport<CostContPlanStat>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8241731564557273166L;
	@Autowired
	/** 合约规划操作对象 */
	private CostContPlanStatManager costContPlanStatManager;
	/** 合约汇总列表对象 */
	private CostContPlanStat costContPlanStat;
	/** 合约汇总列表集合 */
	private List<CostContPlanStat> costContPlanStatList;

	
	@Override
	public String delete() throws Exception {
		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		
		return null;
	}

	@Override
	public String input() throws Exception {
		
		return null;
	}

	@Override
	public String list() throws Exception {
		
		return "list";
	}
	
	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public String save() throws Exception {
		
		return null;
	}

	@Override
	public CostContPlanStat getModel() {
		
		return null;
	}
	/**get and set methods*/
	public List<CostContPlanStat> getCostContPlanStatList() {
		return costContPlanStatList;
	}

	public void setCostContPlanStatList(List<CostContPlanStat> costContPlanStatList) {
		this.costContPlanStatList = costContPlanStatList;
	}

	public CostContPlanStatManager getCostContPlanStatManager() {
		return costContPlanStatManager;
	}

	public void setCostContPlanStatManager(
			CostContPlanStatManager costContPlanStatManager) {
		this.costContPlanStatManager = costContPlanStatManager;
	}

	public CostContPlanStat getCostContPlanStat() {
		return costContPlanStat;
	}

	public void setCostContPlanStat(CostContPlanStat costContPlanStat) {
		this.costContPlanStat = costContPlanStat;
	}

}
