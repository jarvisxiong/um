package com.intelitune.nwms.role;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.intelitune.common.PathHorse;

public class RoleParser
{

	private HashMap roleMap = new HashMap();

	private static RoleParser instance = new RoleParser();

	public static RoleParser getInstance()
	{
		return instance;
	}

	public RoleParser()
	{
		try
		{
			try
			{
				doParse();
			} catch (TransformerFactoryConfigurationError e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doParse() throws ParserConfigurationException, SAXException,
			IOException, TransformerFactoryConfigurationError,
			TransformerException
	{
		PathHorse ph = new PathHorse();
		String path = ph.getPath();
		InputStream in = new BufferedInputStream(new FileInputStream(path
				+ "/path.properties"));
		Properties p = new Properties();
		p.load(in);
		String rolePath = path + p.getProperty("Role");
		File f = new File(rolePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(rolePath);

		Element root = doc.getDocumentElement();
		NodeList n1 = root.getElementsByTagName("Role");
		for (int i = 0; i < n1.getLength(); i++)
		{
			Element e1 = (Element) n1.item(i);
			Element e2 = null;
			NodeList n2 = e1.getElementsByTagName("resource");
			for (int j = 0; j < n2.getLength(); j++)
			{
				e2 = (Element) n2.item(j);
				NodeList n3 = e2.getElementsByTagName("operation");
				HashSet operationSet = new HashSet();
				for (int k = 0; k < n3.getLength(); k++)
				{
					Element e3 = (Element) n3.item(k);
					String o = e3.getAttribute("name");
					operationSet.add(o);
				}
				roleMap.put(e1.getAttribute("type") + ":"
						+ e2.getAttribute("name"), operationSet);
			}
		}

	}

	public static void main(String ars[]) throws ParserConfigurationException,
			SAXException, IOException, TransformerFactoryConfigurationError,
			TransformerException
	{
		RoleParser r = RoleParser.getInstance();
		HashMap map = r.getRoleMap();
		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext())
		{
			String s = (String) iter.next();
			System.out.println(s);
			Set se = (Set) map.get(s);
			Iterator it = se.iterator();
			while (it.hasNext())
			{
				String g = (String) it.next();
				System.out.println(g);
			}
		}
		r.writeRole(r.getRoleMap());
	}

	public boolean getOperation(String role, String operation)
	{
		HashMap map = this.getRoleMap();
		Set set = (Set) map.get(role);
		HashSet se = new HashSet();
		Iterator iter = set.iterator();
		boolean a = false;
		while (iter.hasNext())
		{
			String s = (String) iter.next();
			if (s.equals(operation))
				a = true;
			break;
		}
		return a;
	}

	public void writeRole(HashMap map) throws IOException,
			ParserConfigurationException, SAXException,
			TransformerFactoryConfigurationError, TransformerException
	{
		HashMap mm = instance.getRoleMap();
		Set<String> set = mm.keySet();
		Set<String> se = map.keySet();

		for (String type : set)
		{
			for (String ty : se)
			{
				if (type.equals(ty))
				{
					mm.put(type, map.get(ty));
				}
			}
		}

		PathHorse ph = new PathHorse();
		String path = ph.getPath();
		InputStream in = new BufferedInputStream(new FileInputStream(path
				+ "/path.properties"));
		Properties p = new Properties();
		p.load(in);
		String rolePath = path + p.getProperty("Test");
		File f = new File(rolePath);

		if (!f.exists())
		{
			f.createNewFile();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		Document xml = builder.newDocument();
		Element root = xml.createElement("Role-list");
		xml.appendChild(root);
		Set<String> stt = mm.keySet();

		for (String s : stt)
		{
			String d[] = s.split(":", 0);
			Element role = xml.createElement("Role");
			role.setAttribute("type", d[0]);
			NodeList list = root.getElementsByTagName("Role");
			boolean a = true;
			Element element = null;

			for (int i = 0; i < list.getLength(); i++)
			{
				element = (Element) list.item(i);
				String li = element.getAttribute("type");
				if (li.equals(d[0]))
				{
					a = false;
					break;
				}
			}

			if (a == true)
			{
				root.appendChild(role);
				Element resource = xml.createElement("resource");
				resource.setAttribute("name", d[1]);
				role.appendChild(resource);
				Set<String> operationSet = (Set) mm.get(s);
				for (String ti : operationSet)
				{
					Element ele = xml.createElement("operation");
					ele.setAttribute("name", ti);
					resource.appendChild(ele);
				}
			} else
			{
				Element resource = xml.createElement("resource");
				resource.setAttribute("name", d[1]);
				element.appendChild(resource);
				Set<String> operationSet = (Set) mm.get(s);
				for (String ti : operationSet)
				{
					Element ele = xml.createElement("operation");
					ele.setAttribute("name", ti);
					resource.appendChild(ele);
				}
			}
		}

		t.transform(new DOMSource(xml), new StreamResult(
				new FileOutputStream(f)));

	}

	public HashMap getRoleMap()
	{
		return roleMap;
	}

	public void setRoleMap(HashMap roleMap)
	{
		this.roleMap = roleMap;
	}

}
