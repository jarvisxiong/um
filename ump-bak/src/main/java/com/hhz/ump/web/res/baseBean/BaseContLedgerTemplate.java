package com.hhz.ump.web.res.baseBean;

public abstract class BaseContLedgerTemplate extends BaseTemplate implements IAutoImport {

	/**
	 * 合同台账id
	 */
	private String contLedgerId;

	public String getContLedgerId() {
		return contLedgerId;
	}

	public void setContLedgerId(String contLedgerId) {
		this.contLedgerId = contLedgerId;
	}
}
