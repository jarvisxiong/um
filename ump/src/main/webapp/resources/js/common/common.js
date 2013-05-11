
/*!
 * JavaScript 扩充 Library v1.0
 * Copyleft (^-^) 2009
 * Date: 2009-09-15 09:35:59 -050
 * author yaoyilang
 * Revision: 1.1
 */
(function () {
	//for old browsers
	window["undefined"] = window["undefined"];
	//String class extend
	String.isInstance = function (_string) {
		return (typeof (_string) === "string");
	};
	String.isEmpty = function (str) {
		return (typeof (str) === "undefined" || str === null || (str.length === 0));
	};
	String.isNotEmpty = function (str) {
		return (!String.isEmpty(str));
	};
	String.prototype.trim = function () {
		return this.replace(/(^[\s]*)|([\s]*$)/g, "");
	};
	String.prototype.getAttribute = function (name) {
		var reg = new RegExp("(^|;|\\s)" + name + "\\s*:\\s*([^;]*)(\\s|;|$)", "i");
		if (reg.test(this)) {
			return RegExp.$2.replace(/[\x0f]/g, ";");
		}
		return null;
	};
	String.prototype.cnLength = function () {
		return ((this.replace(/[^x00-xFF]/g, "**")).length);
	};
	//Array class extend
	Array.isInstance = function (obj) {
		return Object.prototype.toString.call(obj) === '[object Array]';
	};
	Array.prototype.add = function (o) {
		this.push(o);
	};
	Array.prototype.indexOf = function (o) {
		for (var i = 0, len = this.length; i < len; i = i + 1) {
			if (this[i] != null && typeof(this[i].equals)=='function' && this[i].equals(o)) {
				return i;
			}
			if (this[i] == o) {
				return i;
			}
		}
		return -1;
	};
	Array.prototype.equals = function (_array) {
		if(this == _array){
			return true;
		}
		if(!Array.isInstance(_array)){
			return false;
		}
		if(this.length != _array.length){
			return false;
		}
		for (var i = 0, len = this.length; i < len; i = i + 1) {
			var o1 = this[i];
			var o2 = _array[i];
			if(o1 != o2){
				if(!(typeof(o1.equals)=='function' && o1.equals(o2))){
					return false;
				}
			}
		}
		return true;
	};	
	Array.prototype.remove = function (o) {
		var index = this.indexOf(o);
		if (index != -1) {
			this.splice(index, 1);
			return true;
		} else {
			return false;
		}
	};
	Array.prototype.contains = function (o) {
		return this.indexOf(o) != -1;
	};
	Array.prototype.containsAll = function (oArray) {
		if (this == oArray) {
			return true;
		}
		for (var i = 0; i < oArray.length; i = i + 1) {
			var o = oArray[i];
			if (!this.contains(o)) {
				return false;
			}
		}
		return true;
	};
	//Date class extend
	Date.isInstance = function (obj) {
		return	(Object.prototype.toString.call(obj) === '[object Date]');
	};
	Date.prototype.format = function(format) 
	{ 
	  var o = { 
	    "M+" : this.getMonth()+1, //month 
	    "d+" : this.getDate(),    //day 
	    "h+" : this.getHours(),   //hour 
	    "m+" : this.getMinutes(), //minute 
	    "s+" : this.getSeconds(), //second 
	    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
	    "S" : this.getMilliseconds() //millisecond 
	  };
	  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
	    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
	    format = format.replace(RegExp.$1, 
	      RegExp.$1.length==1 ? o[k] : 
	        ("00"+ o[k]).substr((""+ o[k]).length)); 
	  return format; 
	};

	//Function class extend
	Function.isInstance = function (obj) {
		return Object.prototype.toString.call(obj) === '[object Function]';
	};
	//Number class extend
	Number.isInstance = function (obj) {
		return Object.prototype.toString.call(obj) === '[object Number]';
	};
	//Boolean class extend
	Boolean.isInstance = function (obj) {
		return Object.prototype.toString.call(obj) === '[object Boolean]';
	};
})();

/**
 * 复制文本，兼容各种浏览器
 */
function copyToClipboard(txt){
     if(window.clipboardData) {   
         window.clipboardData.clearData();   
         window.clipboardData.setData("Text", txt);   
     } else if(navigator.userAgent.indexOf("Opera") != -1) {   
    	 window.location = txt;   
     } else if (window.netscape) {   
          try {   
               netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");   
          } catch (e) {   
               alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");   
          }   
          var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);   
          if (!clip)   
               return;   
          var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);   
          if (!trans)   
               return;   
          trans.addDataFlavor('text/unicode');   
          var str = new Object();   
          var len = new Object();   
          var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);   
          var copytext = txt;   
          str.data = copytext;   
          trans.setTransferData("text/unicode",str,copytext.length*2);   
          var clipid = Components.interfaces.nsIClipboard;   
          if (!clip)   
               return false;   
          clip.setData(trans,null,clipid.kGlobalClipboard);   
     }   
    // alert("复制成功！");
};
function isPhone(){
 	var sUserAgent  =  navigator .userAgent.toLowerCase();  
    var bIsIpad  =  sUserAgent .match(/ipad/i) == "ipad";    
    var bIsIphoneOs  =  sUserAgent .match(/iphone os/i) == "iphone os";  
    var bIsMidp  =  sUserAgent .match(/midp/i) == "midp";  
    var bIsUc7  =  sUserAgent .match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
    var bIsUc  =  sUserAgent .match(/ucweb/i) == "ucweb";  
    var bIsAndroid  =  sUserAgent .match(/android/i) == "android";  
    var bIsCE  =  sUserAgent .match(/windows ce/i) == "windows ce";  
    var bIsWM  =  sUserAgent .match(/windows mobile/i) == "windows mobile";  
    if (bIsIpad ||bIsIphoneOs || bIsAndroid||bIsMidp||bIsUc7||bIsUc||bIsCE||bIsWM){
    	return true;
    }
    return false;
}
//回车，实现搜索效果
function click_on_return(btn_search) {
   document.onkeydown = function(e){    
 	    var ev = document.all ? window.event : e;  
 	    if(ev.keyCode==13) {// 如（ev.ctrlKey && ev.keyCode==13）为ctrl+Center 触发  
 	 	   $("#"+btn_search).trigger("click")
 	    }  
 	  }  
}
//add by dido
function StringToDate(DateStr) { 
	var converted = Date.parse(DateStr); 
	var myDate = new Date(converted); 
	if (isNaN(myDate)){ 
	var arys= DateStr.split('-'); 
	myDate = new Date(arys[0],--arys[1],arys[2]); 
	} 
	return myDate; 
}
//下列框时间判断是否大于当前时间 add by dido
function verificationTime(id){
	var dateStr = $("#"+id+"").val();
	var date = StringToDate(dateStr);
	var newDate = new Date();
	if(newDate>date){
		alert("所选时间必须大于当前时间");
		$("#"+id+"").attr("value",'');//清空内容 
		return;
	}
}
function changeTwoDecimal(x)
{
	var f_x = parseFloat(x);
	if (isNaN(f_x))
	{
	alert('function:changeTwoDecimal->parameter error');
	return false;
	}
	var f_x = Math.round(x*100)/100;
	return f_x;
}