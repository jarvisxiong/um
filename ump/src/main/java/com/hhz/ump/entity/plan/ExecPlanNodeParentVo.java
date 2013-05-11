package com.hhz.ump.entity.plan;


public class ExecPlanNodeParentVo {

	private int sequenceNo;
    private String execPlanNodeId;
    private String execPlanDetailId;
    private String nodeName;
    private String startDate;
    private String endDate;
    
    
	public String getExecPlanNodeId() {
		return execPlanNodeId;
	}
	public void setExecPlanNodeId(String execPlanNodeId) {
		this.execPlanNodeId = execPlanNodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExecPlanDetailId() {
		return execPlanDetailId;
	}
	public void setExecPlanDetailId(String execPlanDetailId) {
		this.execPlanDetailId = execPlanDetailId;
	}
	public int getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
}
