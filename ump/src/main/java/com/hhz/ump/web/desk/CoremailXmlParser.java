/**
 * 
 */
package com.hhz.ump.web.desk;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.DateOperator;
import com.hhz.ump.web.desk.bean.CoremailItem;
import com.hhz.ump.web.desk.bean.CoremailResult;

/**
 * @author huangjian
 * 
 *         2011-7-14
 */
public class CoremailXmlParser {
	private static Logger logger = Logger.getLogger(CoremailXmlParser.class);

	public static CoremailResult parserXml(String xmlContent) throws Exception {
		CoremailResult result = new CoremailResult();
		if (xmlContent != null) {
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding(ChangeCharset.UTF_8);
			try {
				Document document = DocumentHelper.parseText(xmlContent);
				Element root = document.getRootElement();
				Element var = root.element("var");
				Element meta = var.element("meta");
				parseElement(meta,result);
				
				Element messages = var.element("messages");
				for (Iterator k = messages.elementIterator(); k.hasNext();) {
					Element subNode = (Element) k.next();
					CoremailItem item=new CoremailItem();
					parseElement(subNode,item);
					Element flags = subNode.element("flags");
					parseElement(flags,item);
					result.getItems().add(item);
				}
			} catch (Exception e) {
				logger.error("xmlContent:"+xmlContent);
				logger.error(e.getMessage(),e);
			}
		}
		return result;
	}

	private static void parseElement(Element meta, Object result) {
		try {
			for (Iterator j = meta.elementIterator(); j.hasNext();) {
				Element node = (Element) j.next();
				Field field = getField(result, node.getName());
				if (field != null) {
					if (field.getType() == Long.class) {
						PropertyUtils.setProperty(result, node.getName(), Long.valueOf(node.getText()));
					} else if (field.getType() == String.class) {
						PropertyUtils.setProperty(result, node.getName(), node.getText());
					} else if (field.getType() == Date.class) {
						Date date = DateOperator.parse(node.getText(), true);
						PropertyUtils.setProperty(result, node.getName(), date);
					}else if (field.getType() == Boolean.class) {
						PropertyUtils.setProperty(result, node.getName(), BooleanUtils.toBoolean(node.getText()));
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	private static Field getField(Object templateBean, String fieldName) {
		Field field = null;
		try {
			field = templateBean.getClass().getDeclaredField(fieldName);
		} catch (Exception e) {
			List list = ClassUtils.getAllSuperclasses(templateBean.getClass());
			for (Object object : list) {
				Class classTmp = (Class) object;
				try {
					field = classTmp.getDeclaredField(fieldName);
				} catch (Exception e1) {
				}
			}
		}
		return field;
	}
}
