package com.intelitune.export;


public interface Document {
//	use this method can get path exp: http://www.exp.com/export/
	public String getPath();
	
//	public File getFile();
	
//	set the export file name, as usual we setup template file name
	public void setName(String name); 
	
//	use this method can get document file name exp: templateName_<systemTime etc.>.xls
	public String getName();
	
	public String getUrl();
}
