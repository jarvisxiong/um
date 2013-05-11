package com.intelitune.nwms.dao;

public class Home {

	private final static Home instance = new Home();

	public static final Home getInstance() {
		return instance;
	}

}
