package com.intelitune.export.jxls;

import java.io.FileInputStream;
import net.sf.jxls.transformer.Configuration;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.intelitune.common.PathHorse;
import com.intelitune.export.Document;
import com.intelitune.export.Template;

/**
 * @author yubing_zhong
 * 
 * 
 */

public class TemplateImpl implements Template {

	private String name; // tempalate name

	private String path; // files path

	private String templateFile; // full file name

	public TemplateImpl(String name) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**
	 * the structer for TemplateImpl to init the name
	 */
	public TemplateImpl() {
//		this.name = "ebook1.xls";
	}

	/**
	 * get the template name
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * get the template path
	 */

	public String getPath() {
		// TODO Auto-generated method stub
		return PathHorse.getPath();
	}

	/**
	 * set the template name
	 */
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	/**
	 * set the template path
	 */

	public void setPath(String path) {
		// TODO Auto-generated method stub
		this.path = path;
	}

	/**
	 * get the full template name
	 * 
	 * @return templateFile
	 */

	public String getTemplateFile() { // get the the power path of the file
		templateFile = this.getPath() + "WEB-INF/EXCEL/" + getName();
		return templateFile;
	}

	/**
	 * export the data  using the jxls
	 */

	public void export(Map map, Collection collection, Document document)
			throws Exception {
		if (collection!=null)
			map.put("record", collection);
		
		Configuration config = new Configuration();
		config.setUTF16( true );
		XLSTransformer transformer = new XLSTransformer( config );
		
		FileInputStream input = new FileInputStream(getTemplateFile());
		FileOutputStream fileOut = new FileOutputStream(document.getPath());
		try {
			HSSFWorkbook workbook = transformer.transformXLS(input, map);
			workbook.write(fileOut);
			input.close();
			fileOut.close();
		} catch (Exception e) {
			input.close();
			fileOut.close();
			throw e;
		}
	}
	
	public void export(Map map, Collection collection1, Collection collection2,Document document)
	throws Exception {
if (collection1!=null)
	map.put("xia", collection1);
if(collection2!=null){
	map.put("shang", collection2);
}
Configuration config = new Configuration();
config.setUTF16( true );
XLSTransformer transformer = new XLSTransformer( config );

FileInputStream input = new FileInputStream(getTemplateFile());
FileOutputStream fileOut = new FileOutputStream(document.getPath());
try {
	HSSFWorkbook workbook = transformer.transformXLS(input, map);
	workbook.write(fileOut);
	input.close();
	fileOut.close();
} catch (Exception e) {
	input.close();
	fileOut.close();
	throw e;
}
}

}
