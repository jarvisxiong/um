/**
 * 
 */
package com.hhz.ump.web.res;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.ump.entity.res.ResApproveContent;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * xml和实体bean之间转换处理类
 * 
 * @author huangj 2010-12-3
 */
public class ResXmlParser {
	private static Logger logger = Logger.getLogger(ResXmlParser.class);

	/**
	 * 将实体对象转化为xml格式的字符串
	 * 
	 * @param templateBean
	 * @return
	 * @throws Exception
	 */
	public static String initXml(BaseTemplate templateBean) throws Exception {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("templet");
		Element content = root.addElement("content");
		Class templateClass = templateBean.getClass();
		Field[] fields = templateClass.getDeclaredFields();
		Field[] fieldsAll = fields;
		List list = ClassUtils.getAllSuperclasses(templateClass);
		for (Object object : list) {
			Class classTmp = (Class) object;
			if (!classTmp.equals(Object.class)) {
				fieldsAll = (Field[]) ArrayUtils.addAll(fieldsAll, classTmp.getDeclaredFields());
			}
		}
		for (Field field : fieldsAll) {
			String fieldName = field.getName();
			field.setAccessible(true);
			Class fieldType = field.getType();
			if (fieldType == String.class) {
				String filedValue = (String) field.get(templateBean);
				if (filedValue == null) {
					logger.debug("字段" + fieldName + "为null");
					continue;
				}
				content.addElement(fieldName).setText(StringUtils.trim(filedValue));
			} else if (fieldType == Boolean.class) {
				Boolean filedValue = (Boolean) field.get(templateBean);
				if (filedValue == null) {
					logger.debug("字段" + fieldName + "为null");
					continue;
				}
				content.addElement(fieldName).setText(filedValue.toString());
			} else if (fieldType == List.class) {
				// 合同审批单（其他情况）子属性
				ParameterizedType pt = (ParameterizedType) field.getGenericType();
				String subClassName = ((Class) pt.getActualTypeArguments()[0]).getName();
				System.out.println(pt.getActualTypeArguments()[0]);
				List otherProperties = (List) field.get(templateBean);
				Element nodeOther = content.addElement(fieldName);
				nodeOther.addAttribute("class", subClassName);
				for (Object obj : otherProperties) {
					addSubNode(nodeOther, obj);
				}
			} else if (fieldType == Map.class) {
				Element custom = content.addElement(fieldName);
				Map<String, String> mapCustom = (Map<String, String>) field.get(templateBean);
				for (String nodeCd : mapCustom.keySet()) {
					if (StringUtils.isNotBlank(mapCustom.get(nodeCd))) {
						custom.addElement("_" + nodeCd).setText(mapCustom.get(nodeCd));
					}
				}
			}
		}
		document.setXMLEncoding(ChangeCharset.UTF_8);
		String xml = document.asXML().toString();
		return xml;
	}

	/**
	 * 往指定节点添加子节点，并给子节点赋值
	 */
	private static void addSubNode(Element nodeParent, Object obj) throws Exception {
		if (obj == null)
			return;
		Element subNode = nodeParent.addElement(obj.getClass().getName());
		for (Field field : obj.getClass().getDeclaredFields()) {
			String fieldName = field.getName();
			field.setAccessible(true);
			Class otherFieldType = field.getType();
			if (otherFieldType == String.class) {
				String otherFiledValue = (String) field.get(obj);
				if (otherFiledValue == null) {
					logger.info("字段" + fieldName + "为null");
					continue;
				}
				subNode.addElement(fieldName).setText(StringUtils.trim(otherFiledValue));
			}else if (otherFieldType == Boolean.class) {
				Boolean filedValue = (Boolean) field.get(obj);
				if (filedValue == null) {
					logger.debug("字段" + fieldName + "为null");
					continue;
				}
				subNode.addElement(fieldName).setText(filedValue.toString());
			}
		}
	}

	/**
	 * 将xml字符串转化为实体对象，并将主表的id赋值给实体
	 */
	public static BaseTemplate parserXml(ResApproveContent content, String entityName) throws Exception {
		BaseTemplate templateBean = null;
		if (content != null) {
			String xml = Util.clob2String(content.getApproveContent());
			templateBean = (BaseTemplate) Class.forName(entityName).newInstance();
			templateBean.setResApproveInfoId(content.getResApproveInfo().getResApproveInfoId());
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding(ChangeCharset.UTF_8);
			try {
				Document document = DocumentHelper.parseText(xml);
				Element employees = document.getRootElement();
				Element employee = employees.element("content");
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					Field field = getField(templateBean, node.getName());
					if (field != null) {
						if (field.getType() == String.class) {
							PropertyUtils.setProperty(templateBean, node.getName(), node.getText());
						} else if (field.getType() == Boolean.class) {
							PropertyUtils.setProperty(templateBean, node.getName(), node.getText());
						} else if (field.getType() == List.class) {
							String subClassName = node.attributeValue("class");
							List otherProperties = new ArrayList();
							PropertyUtils.setProperty(templateBean, node.getName(), otherProperties);
							for (Iterator k = node.elementIterator(); k.hasNext();) {
								Element subNode = (Element) k.next();
								Object subObj = Class.forName(subClassName).newInstance();
								otherProperties.add(subObj);
								for (Iterator i = subNode.elementIterator(); i.hasNext();) {
									Element subNodeAttr = (Element) i.next();
									PropertyUtils.setProperty(subObj, subNodeAttr.getName(), subNodeAttr.getText());
								}
							}
						} else if (field.getType() == Map.class) {
							Map<String, String> mapCustom = new HashMap<String, String>();
							for (Iterator k = node.elementIterator(); k.hasNext();) {
								Element subNode = (Element) k.next();
								String nodeCd = subNode.getName().substring(1);
								templateBean.getMapCustomNode().put(nodeCd, subNode.getText());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return templateBean;
	}

	/**
	 * 将xml字符串转化为实体对象，并将主表实体赋给当前实体
	 */
	public static BaseTemplate parserXml(ResApproveInfo entity, String entityName) throws Exception {
		BaseTemplate templateBean = null;
		if (entity != null) {
			templateBean = parserXml(entity.getResApproveContents().get(0), entityName);
			templateBean.setResApproveInfo(entity);
		}
		return templateBean;
	}

	/**
	 * 取得指定的属性
	 */
	private static Field getField(BaseTemplate templateBean, String fieldName) {
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
