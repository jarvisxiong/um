/**
 * 
 */
package com.hhz.ump.web.res;

/**
 * 网批权限bean
 * @author huangjian
 *
 * 2011-8-30
 */
public class ResPermission {
	private boolean isAdmin;//系统管理
	private boolean isBack2Las;//完成追回
	private boolean isQzspAdmin;//网批管理员
	private boolean isQzspMsg;//留言查看
	private boolean isResPerror;//流程错误
	private boolean isResTomeeting;//发起决策会
	private boolean isResToceo;//发给执行总裁
	private boolean isResTopresident;//发给总裁
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isBack2Las() {
		return isBack2Las;
	}
	public void setBack2Las(boolean isBack2Las) {
		this.isBack2Las = isBack2Las;
	}
	public boolean isQzspAdmin() {
		return isQzspAdmin;
	}
	public void setQzspAdmin(boolean isQzspAdmin) {
		this.isQzspAdmin = isQzspAdmin;
	}
	public boolean isQzspMsg() {
		return isQzspMsg;
	}
	public void setQzspMsg(boolean isQzspMsg) {
		this.isQzspMsg = isQzspMsg;
	}
	public boolean isResPerror() {
		return isResPerror;
	}
	public void setResPerror(boolean isResPerror) {
		this.isResPerror = isResPerror;
	}
	public boolean isResTomeeting() {
		return isResTomeeting;
	}
	public void setResTomeeting(boolean isResTomeeting) {
		this.isResTomeeting = isResTomeeting;
	}
	public boolean isResToceo() {
		return isResToceo;
	}
	public void setResToceo(boolean isResToceo) {
		this.isResToceo = isResToceo;
	}
	public boolean isResTopresident() {
		return isResTopresident;
	}
	public void setResTopresident(boolean isResTopresident) {
		this.isResTopresident = isResTopresident;
	}
}
