/**
 * @author shixy
 * @requires jquery.js
 * OA首页需要用到的通用脚本方法
 * 完善中......
 * 
 */
var IndexUtils = {
 
	/**
	 * 显示用户相关信息
	 * @params dom 点击的节点DOM 用来定位显示用户信息的坐标
	 */
	showUserInfo : function(dom,uiid) {

		$("div.userInfo").hide();
		if($("#info_"+uiid).length>0){
			$("#info_"+uiid).show();
			return;
		}
		var newDiv = "<div class='userInfo' id='info_"+uiid+"'>加载中...</div>";
		$(".list_detail").append(newDiv);
		var $curDom = $("#info_"+uiid);
		var off = $(dom).offset();
		$curDom.css({left:"0px",top:off.top}).show();
		
		var result = IndexUtils.getUserInfo(uiid);
		//alert(result);
		var contentDiv = "<img src="+ctx4js+"/images/user/cs1.png' id='img_"+uiid+"' class='infoImg' width='80' height='80'/>" +
				"<span id='name_"+uiid+"'>"+result.userName+"</span><br/>" +
				"<span id='phone_'"+result.phone+"'>"+result.phone+"</span><br/>" +
				"<span id='tel_'"+result.tel+"'>"+result.tel+"</span><br/>" +
				"<span id='dept_'"+result.dept+"'>"+result.dept+"</span><br/>" +
				"<button>Email</button><button id='info_copy_btn_"+uiid+"'>Copy</button><button>聊天</button>";
		$curDom.html(contentDiv).mouseleave(function(){$(this).hide();});
		$("#info_copy_btn_"+uiid).click(function(){
			IndexUtils.copyToClipboard($curDom.text());
			alert("复制成功");
		});
	},
	
	getUserInfo : function(uiid){
		
		/*
		var myurl = _ctx+'/desk/desk!getUserInfo.action';
		$.post(url,{myUiid:uiid},function(result){
			alert(result);
			return result;//如果这么返回,调用的函数体内取不到该返回值.
		});
		*/
		var result = {userName:'张三(2116)',phone:'13900000',tel:'059200000',dept:'总裁办'};
		return result;
	}, 	
		
	/**
	 * 复制文本，兼容各种浏览器
	 */
	copyToClipboard : function(txt) {
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
	},  
	
	showDiv : function(dom,urlObj){
		var html = "<ul>";
		$(urlObj).each(function(i,n){
			html +="<li onclick='return false;'>"+n.title+"</li>";
		});
		
		var domId = $(dom).attr("id");
		var newDiv = "<div class='floatDiv' id='float_"+domId+"'></div>";
		$(document).append(newDiv);
		var off = $(dom).offset();
		var $curDom = $("#float_"+domId);
		$curDom.css({left:off.left+$(dom).width()+"px",top:off.top+"px"}).show();
		
	}
}
