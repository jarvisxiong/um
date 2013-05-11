package com.intelitune.rbac.base;
import com.intelitune.nwms.common.CommonAccount;
public interface AccessController {
	
	public boolean checkAccess(CommonAccount commonAccount, String className, Operate ope);

}
