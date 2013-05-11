var inputCompPrefix = "htmlPageTopContainer_pageForm_";
var noneGif = "../Theme?img=none.gif";
var errorGif = "../Theme?img=error.gif";

function gotoUrl(url) {
	location.href=url;
}

function checkAll(checkboxAll) {
	var a = document.getElementsByTagName("input");
	for (i=0;i<a.length;i++) {
		if (a[i].type == "checkbox") a[i].checked = checkboxAll.checked;
	}
}

function setValue(component,value) {
	document.all[inputCompPrefix + component].value = value;
}
 
function hrefConfirmDelete(href) {
	if (confirm(aMessage["confirmDelete"]))
		document.location=href;
} 

function confirmDelete() {
	return confirm(aMessage["confirmDelete"]);
} 

function lookup(name,href,submitPage) {
	document.all[inputCompPrefix + name].value = showModalDialog(href);
	if (submitPage)
		pageForm.submit();
}

function formatnumber(value,num)
{
var a,b,c,i;
a = value.toString();
b = a.indexOf('.');
c = a.length;
if (num==0)
{
if (b!=-1)
a = a.substring(0,b);
}
else
{
if (b==-1)
{
a = a + ".";
for (i=1;i<=num;i++)
a = a + "0";
}
else
{
a = a.substring(0,b+num+1);
for (i=c;i<=b+num;i++)
a = a + "0";
}
}
return a;
}

function setVolumn(volumn,h,l,b) {
		hh=getValue(document.all[inputCompPrefix + h]);
		ll=getValue(document.all[inputCompPrefix + l]);
		bb=getValue(document.all[inputCompPrefix + b]);
		document.all[inputCompPrefix + h].value= formatnumber(hh,2);
		document.all[inputCompPrefix + l].value= formatnumber(ll,2);
		document.all[inputCompPrefix + b].value= formatnumber(bb,2);
		document.all[inputCompPrefix + volumn].value = formatnumber(hh*ll*bb,2);
}

function returnLookupValue(name) {
	if (document.all[inputCompPrefix + name]!=null) {
		if (document.all[inputCompPrefix + name][0]!=null)
  			returnValue=getValue(document.all[inputCompPrefix + name][0]);
  		else
  			returnValue=getValue(document.all[inputCompPrefix + name]);
  	}
  	else {
  		if (document.all[name][0]!=null)
	  		returnValue=getValue(document.all[name][0]);
	  	else
	  		returnValue=getValue(document.all[name]);
  	}
  	if (returnValue=="")
  	 	alert(aMessage["chooseAnItem"]);
  	else {
  		close();
  		return false;
  	}
}

function getErrorMessage(key) {
	return aMessage[key];
}

var aUsage = {
		"int":"^([+-]?)\\d+$",											
		"int+":"^([+]?)\\d+$",											
		"int-":"^-\\d+$",												
		"num":"^([+-]?)\\d*\\.?\\d+$",									
		"num+":"^([+]?)\\d*\\.?\\d+$",									
		"num-":"^-\\d*\\.?\\d+$",										
		"float":"^([+-]?)\\d*\\.\\d+$",									
		"float+":"^([+]?)\\d*\\.\\d+$",									
		"float-":"^-\\d*\\.\\d+$",										
																		
		"email":"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",	
		"color":"^#[a-fA-F0-9]{6}",										
		"url":"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",	
		"chinese":"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",				
		"ascii":"^[\\x00-\\xFF]+$",										
		"zipcode":"^\\d{6}$",											
		"mobile":"^0{0,1}13[0-9]{9}$",									
		"ip4":"^\(([0-1]?\\d{0,2})|(2[0-5]{0,2}))\\.(([0-1]?\\d{0,2})|(2[0-5]{0,2}))\\.(([0-1]?\\d{0,2})|(2[0-5]{0,2}))\\.(([0-1]?\\d{0,2})|(2[0-5]{0,2}))$",				
																		
		"notempty":"^.+$",											
		"image":"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",	
		"doc":"(.*)\\.(doc)$",	
		"xml":"(.*)\\.(xml)$",	
		"excel":"(.*)\\.(xls)$",	
		"rar":"(.*)\\.(rar|zip|7zip|tgz)$",								
		"date":"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
		"datetime":"^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}[ ]{1}[0-9]{1,2}:[0-9]{1,2}$"
	};
	
function checkForm(scope) {
  var els = document.images;
  var test= true;
  var t = true;
  
  for (var i=0;i<els.length;i++)
    if (els[i].getAttribute("scope")==scope) {
      t = doCheck(els[i],inputCompPrefix + els[i].name);
      if (test&&(!t)) setFocus(document.all[inputCompPrefix + els[i].name]);
      test=test&&t;
    }
  return test;
}

function doCheck(img,name) {
  var sUsage = img.getAttribute("usage");	
  var sReg	= "";
  var el = document.all[name];
  
  if (typeof(el)=="undefined"&&el==null) {
  	alert("Input Component Missing!");
  	return false;
  }

  if(typeof(sUsage)!="undefined"&&sUsage!=null)
  {
	if(aUsage[sUsage]!=null)			
		sReg = aUsage[sUsage];
	else {
		alert("Usage Not Define: "+sUsage);
		return false;
	}
  }
  else
  	sReg = img.getAttribute("Exp");

  if (typeof(sReg)!="undefined"&&sReg!=null){
	var sVal = getValue(el);
	var reg = new RegExp(sReg,"i");
	if(reg.test(sVal)) {
  		img.src=noneGif;
	    img.alt="";
	    return true;
	}
	else {
  		img.src=errorGif;
  		var errorKey=img.getAttribute("messageKey");
  		if(typeof(errorKey)!="undefined"&&errorKey!=null)
	      img.alt=getErrorMessage(errorKey);
	    else if (typeof(sUsage)!="undefined"&&sUsage!=null&&aMessage[sUsage]!=null)
	      img.alt=aMessage[sUsage];
	    else
	      img.alt="Error!"
	    return false;
	}
  }
  else
    alert("No Exp find.");
    
  return false;
}

	function getValue(el){
		var sType = el.type;
		switch(sType){

			case "text":
			case "hidden":
			case "password":
			case "file":
			case "textarea": return el.value;

			case "checkbox":
			case "radio": return getRadioValue(el);
			case "select-one":
			case "select-multiple": return getSelectValue(el);
		}

		function getRadioValue(el){
			var sValue = "";
			var tmpels = document.getElementsByName(el.name);
			for(var i=0;i<tmpels.length;i++){
				if(tmpels[i].checked){
					sValue = tmpels[i].value;
				}
			}
			return sValue;
		}

		function getSelectValue(el){
			var sValue = "";
			for(var i=0;i<el.options.length;i++){

				if(el.options[i].selected && el.options[i].value!=""){
					sValue += "0";
				}
			}
			return sValue;
		}
	}


	function setFocus(el){

		var sType = el.type;
		switch(sType){

			case "text":
			case "hidden":
			case "password":
			case "file":
			case "textarea": 
				try{el.focus();var rng = el.createTextRange(); rng.collapse(false); rng.select();}catch(e){};
				break;
			

			case "checkbox":
			case "radio": 
				var els = document.getElementsByName(el.name);
				for(var i=0;i<els.length;i++){
					if(els[i].disabled == false){
						els[i].focus();
						break;
					}
				}
				break;
			case "select-one":
			case "select-multiple":
				el.focus();
				break;
		}
	}
