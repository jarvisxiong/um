package com.intelitune.nwms.service;

import com.intelitune.nwms.model.ColumnState;

public interface ColumnStateService {
	public ColumnState findColumnStateByCode(int code);
}
