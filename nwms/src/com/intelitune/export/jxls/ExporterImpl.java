/**
 * 
 */
package com.intelitune.export.jxls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.intelitune.export.DataSource;
import com.intelitune.export.Document;
import com.intelitune.export.Exporter;
import com.intelitune.export.Template;

/**
 * @author yubing_zhong
 * 
 */
public class ExporterImpl implements Exporter {

	/**
	 * generate the excel by pass some parameter
	 */
	public String generate(Template template, Map map,
			Document document) throws Exception {
		template.export(map, null, document);
		return document.getUrl() + ".xls"; // return the EXCEL file name
	} 
	
	public String generate(Template template, Collection collection, Map map,
			Document document) throws Exception {
		template.export(map, collection, document);
		return document.getUrl() + ".xls"; // return the EXCEL file name
	} 
	
	public String generate(Template template, DataSource dataSource, Map map,
			Document document) throws Exception {		
		List l = null;
		if (dataSource!=null && dataSource.first()) {
			l = new ArrayList();	
			do {
				l.add(dataSource.getBean());
			} while (dataSource.next());
		}	
		return generate(template, l, map, document);
		
	}
	

}
