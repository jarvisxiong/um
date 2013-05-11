/**
 * The class's task is get the output file's path,and the name,set the name.
 *  the name is strucked by time,count number. user can set the name by them-
 *  selves.
 */
package com.intelitune.export.jxls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.intelitune.common.PathHorse;
import com.intelitune.export.Document;

/**
 * @author yubing_zhong
 * 
 */
public class DocumentImpl implements Document {

	// define the fields
	public static int count = 0; // generate number

	private String fileName; // file name denpends on the system time

	private String docName; // file name depends on the user

	private String def = "default"; // default name

	/**
	 * make the count and doc synchronized
	 */
	public synchronized static Document getInstance() {
		DocumentImpl doc = new DocumentImpl();
		count = (count + 1) % 100000;
		return doc;
	}

	/**
	 * generate the file name which depends on system time
	 */

	public DocumentImpl() {
		fileName = new SimpleDateFormat("yyyyMMdd_HHmmss_")
				.format(new java.util.Date())
				+ count;
	}
	public String getFileName(){
		return fileName;
	}

	/**
	 * get the name
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return docName;
	}

	/**
	 * set the name
	 * 
	 */
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.docName = name;
	}

	/**
	 * get the path which used in the TemplateImpl
	 */
	public String getPath() {
		// TODO Auto-generated method stub
		if (this.getName()==null || "".equals(this.getName())) {
			return PathHorse.getPath() + "Output/" + getFileName() +".xls";
		} else {
			return PathHorse.getPath() + "Output/" + getFileName() + getName()
					+ ".xls";
		}
	}

	/**
	 * get the url which use to return the file's adress
	 */
	public String getUrl() {
		if (this.getName()==null || "".equals(this.getName())) {
			return "../../Output/" + getFileName()+ def;
		} else {
			return "../../Output/" + getFileName() + getName();
		}

	}

}
