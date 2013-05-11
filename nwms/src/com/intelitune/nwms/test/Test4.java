package com.intelitune.nwms.test;

import java.util.ArrayList;
import java.util.List;




import net.sf.click.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





public class Test4 extends Page
{
    public List<JQGridRow> rows = new ArrayList<JQGridRow>();
    public JSONObject jsonObj = new JSONObject();
    public JSONArray jarrary=new JSONArray();
    public String jstring="";
public void onRender(){
    Person p=new Person();
    p.setAge(12);
    p.setName("xx");
    Person p1=new Person();
    p1.setAge(15);
    p1.setName("xxyy");
    ArrayList<Person> l=new ArrayList<Person>();
    l.add(p);
    l.add(p1);
    List<JQGridRow> rows = new ArrayList<JQGridRow>();
    int i=0;
    
    for(Person pn:l){
	 JQGridRow row = new JQGridRow(); 
	 row.setId(i);
	List<String> cell=new ArrayList<String>();
	cell.add(pn.getName());
	cell.add(String.valueOf(pn.getAge()));
	row.setCell(cell);
	rows.add(row);
    }
    jarrary= JSONArray.fromObject(rows);
//    try {
//	jsonObj=jarrary.toString();
//    } catch (JSONException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//    }
//    List<JQGridRow> rows = new ArrayList<JQGridRow>();
//    int i=0;
//    
//    for(Person pn:l){
//	jstring =pn.toJson(pn);
//    }
    
//    for()
//    
//    try {
//	System.out.println(jsonObj.toString(4));
//    } catch (JSONException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//    }
//    String s = "{     \"list of lists\" : [         [1, 2, 3],         [4, 5, 6],     ] }";
   
//    try {
//	jsonObj = new JSONObject(s);
//    } catch (JSONException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//    }
//    try {
//	System.out.println(jsonObj.toString(4));
//    } catch (JSONException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//    }
//    try {
//	System.out.println(XML.toString(jsonObj));
//    } catch (JSONException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//    }
}

}
