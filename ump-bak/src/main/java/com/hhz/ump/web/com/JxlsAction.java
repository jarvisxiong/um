/**
 * 
 */
package com.hhz.ump.web.com;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangj 2010-6-9
 */
@Results( { @Result(name = ActionSupport.SUCCESS, type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
		"is", "contentDisposition", "attachment;filename=${downFileName}.xls"}) })
public class JxlsAction extends ActionSupport {
/*
	private static final long serialVersionUID = 2553329518521961168L;

	private InputStream is;

	private String downFileName;
	private List initData(){
		List departments = new ArrayList();
		Department department = new Department("IT");
		Employee chief = new Employee("Derek", 35, 3000, 0.30);
		department.setChief(chief);
		department.addEmployee(new Employee("Elsa", 28, 1500, 0.15));
		department.addEmployee(new Employee("Oleg", 32, 2300, 0.25));
		department.addEmployee(new Employee("Neil", 34, 2500, 0.00));
		department.addEmployee(new Employee("Maria", 34, 1700, 0.15));
		department.addEmployee(new Employee("John", 35, 2800, 0.20));
		departments.add(department);
		department = new Department("HR");
		chief = new Employee("Betsy", 37, 2200, 0.30);
		department.setChief(chief);
		department.addEmployee(new Employee("Olga", 26, 1400, 0.20));
		department.addEmployee(new Employee("Helen", 30, 2100, 0.10));
		department.addEmployee(new Employee("Keith", 24, 1800, 0.15));
		department.addEmployee(new Employee("Cat", 34, 1900, 0.15));
		departments.add(department);
		department = new Department("BA");
		chief = new Employee("Wendy", 35, 2900, 0.35);
		department.setChief(chief);
		department.addEmployee(new Employee("Denise", 30, 2400, 0.20));
		department.addEmployee(new Employee("LeAnn", 32, 2200, 0.15));
		department.addEmployee(new Employee("Natali", 28, 2600, 0.10));
		department.addEmployee(new Employee("Martha", 33, 2150, 0.25));
		departments.add(department);
		return departments;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TreePanelNode panelNode = TreePanelUtil.getCachePhysicalTree();
		Map beans = new HashMap();
		beans.put("panelNode", panelNode);

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = JxlsAction.class.getClassLoader().getResourceAsStream("jxlsTemplates/orgUser.xls");
		//固定sheet页
		HSSFWorkbook hssfWorkbook = transformer.transformXLS(isTemplate, beans);
		
		
		
		List sheetNames = new ArrayList();
        List maps = new ArrayList();
        List departments= initData();
        for(int i = 0; i < departments.size(); i++){
            Map map = new HashMap();
            Department department = (Department) departments.get( i );
            map.put("department", department );
            sheetNames.add( department.getName() );
            map.put("name", "Number " + i);
            maps.add( map );
        }
        //动态Sheet页测试
//        InputStream isTemplate = JxlsAction.class.getClassLoader().getResourceAsStream("jxlsTemplates/dongtaiTest.xls");
//        HSSFWorkbook hssfWorkbook = transformer.transformMultipleSheetsList(isTemplate, departments, sheetNames, "department", new HashMap(), 0);                
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		is = new ByteArrayInputStream(data);
		String fileName = "通讯录";
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return SUCCESS;
	}

	public InputStream getIs() {
		return is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	*/
}
