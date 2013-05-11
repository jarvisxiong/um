package com.intelitune.nwms.test;

public class Person {

    String name;
    Integer age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String toJson(Person p){
	String start="{\"cell\":[";
	String mid="],\"id\":";
	String pro="\" "+getName()+"\","+"\" "+getAge()+"\"";
	return start+pro+mid+"1}";
    }
    
    
}
