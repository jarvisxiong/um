/**
 * @author huangbijin
 * @requires jquery.js  
 * @requires desk.js   - 发送邮件接口
 * @requires common.js - 拷贝名片
 * 
 * 
 * 类名: VisitingCardUtil.js
 * 说明: 显示/隐藏名片
 */



//定义:名片的左上角偏移节点的高度 (单位px)
var CONST_CARD_VIRTUAL_OFFSET = 3;
var CONST_CARD_HORIZON_OFFSET = 5;
//首页顶部logoDIV高度
//var CONST_DOC_TOP_HEIGHT = 48;

var CONST_CARD_CLS_NAME 	  = "visitingCard";
var CONST_CARD_ID_PRE 		  = "pd_visiting_card_";

var VisitingCardUtil = {
	
	getCardId: function(picTypeCd, userCd){
		return CONST_CARD_ID_PRE+ picTypeCd +'_'+userCd;
	},
	/**
	 * 功能: 显示名片
	 * @param: dom - 以dom(html)对象为参照进行浮动显示名片
	 * @param: uiid - 用户UIID
	 * @param: userBizCd - 用户cd
	 */
	show : function(leftPad,picTypeCd,dom,uiid,userCd,isOnline){
		


		$("div."+CONST_CARD_CLS_NAME).hide();
	
		var myDomId =  $(dom).attr("id"); //很重要
		if(!myDomId){
			//alert(" dom id is needed!");
			return;
		}
		
		var myTopValue  = $(dom).offset().top + CONST_CARD_VIRTUAL_OFFSET;
		var myLeftValue = leftPad + CONST_CARD_HORIZON_OFFSET;;

		var cardId = VisitingCardUtil.getCardId(picTypeCd, userCd);
		//.visitingCard 见homeStyle.css 
		if( $("#"+ cardId).length>0){
			$("#"+ cardId).css({"top":myTopValue+"px"});
			$("#"+ cardId).show();
			return;
		}
		
		$.ajax({
		   type: "POST",
		   url: (_ctx+'/desk/desk-wab!showUserPic.action'),
		   data: {uiid:uiid},
		   dataType:'json',
		   success: function(data){
				
				var resultFlg = data.resultFlg;
				if(resultFlg == 'none'){
					return;
				}
				   
				var userName	 = data.userName;
				var orgName      = data.deptName;
				var centOrgName  = data.centOrgName;
				var positionName = data.positionName;
				var userName     = data.userName;
				var fixedPhone   = data.fixedPhone;
				var picFullPath  = data.picFullPath;
				var myShowFlg  	 = data.myShowFlg;
				var sexCd 	     = data.sexCd;
				var email		 = data.email;
				 
				//.visitingCard 见homeStyle.css 
				if( $("#"+ cardId).length>0){
				$("#"+ cardId).show();
					return;
				}

				//eg,"http://localhost:8080/uaap/app/download.action?fileName=20100326162515bLEP.png&realFileName=PD快捷图标_32X32.png&bizModuleCd=uaapUser";
				var imgHTML = "";
 				if( picFullPath!= ''){
					imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + picFullPath +'" />';
				} else{
					//2012-05-29 判断男女
					if(sexCd == '1'){
						imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + _ctx +'/resources/images/desk2/user_pic_male.gif" />';
					}else if(sexCd == '2'){
						imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + _ctx +'/resources/images/desk2/user_pic_female.gif" />';
					}else{
						imgHTML = '';
					}
				}
				
				var employeeClass = "";
				//在线
				if( isOnline == '1'){
					employeeClass = "";
				}else{
					employeeClass = "defaultPic";
				}
				
				var mobilePhone  = data.mobilePhone;
				
				//截取固话/手机号码 
				var shortFixedPhone  = fixedPhone;//VisitingCardUtil.getSubStr(fixedPhone , 20);
				var shortMobilePhone = mobilePhone;//VisitingCardUtil.getSubStr(mobilePhone, 26);

				var arr = new Array();
				arr.push('  <div class="operate"> ');
				//arr.push('       <div title="发送邮件" class="mail" onclick="VisitingCardUtil.hide();VisitingCardUtil.sendEmail(\''+ uiid +'\');" >发送邮件</div><span>|</span>' );
				//arr.push('       <div title="拷贝名片" class="copy" onclick="VisitingCardUtil.hide();VisitingCardUtil.copyCard(\''+ picTypeCd +'\',\''+ userCd +'\');">复制名片</div><span>|</span>' );
				arr.push('	</div>' );
				arr.push('  <div class="content">');
				arr.push('  <div class="' + employeeClass + '">'+imgHTML+' </div>');
				arr.push('  <div class="employeeInfo">');
				arr.push('		<table style="overflow:hidden"> ');
				arr.push('		<tr><td colspan=\'2\'>'+ userName +'</td></tr>' );
				arr.push('		<tr><th valign=\'top\'>座机:</th><td>' + shortFixedPhone  +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>手机:</th><td>' + shortMobilePhone +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>职位:</th><td>' + positionName +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>中心:</th><td>' + centOrgName +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>部门:</th><td>' + orgName     +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>邮箱:</td><td>' + email +'</td></tr>');
				arr.push('		</table> ');

				arr.push(' 	</div> ');
				arr.push('  </div>');
				
				//构造名片内容
				var contentDiv = arr.join("");

				$("#"+ myDomId).append("<div class='"+CONST_CARD_CLS_NAME+"' id='"+ cardId +"'>加载中...</div>");
				
				$("#"+ cardId).css({"top":myTopValue+"px","left":myLeftValue+"px"}).html(contentDiv).show().mouseleave(function(){
					$(this).hide();
				});
 
				return true;
			}
		});
	},

	/**
	 * 隐藏名片 
	 */
	hide : function(){
		$("div."+CONST_CARD_CLS_NAME).hide();
	},

	/**
	 * 功能: 拷贝名片 
	 * @param  userBizCd
	 * @require common.js
	 */
	copyCard : function (picTypeCd,userCd){
		var txt = $("#" + VisitingCardUtil.getCardId(picTypeCd, userCd) +" .employeeInfo").text();
		//alert(txt);
		copyToClipboard(txt);
		//alert("复制成功");
	},

	/**
	 * 发送邮件 
	 * @require desk.js
	 */
	sendEmail : function (uiid){
		//var url = _ctx+"/oa/oa-email!main.action?sendEmailFlag=true&uiid="+uiid;
		//TabUtils.newTab("131","内部邮件",url,true);
	},
	/**
	 * 截取字符长度
	 */
	getSubStr : function(src,n) {
		var r = /[^\x00-\xff]/g;
		if (src.replace(r, "mm").length <= n)
			return src;
		n = n - 3;
		var m = Math.floor(n / 2);
		for ( var i = m; i < src.length; i++) {
			if (src.substr(0, i).replace(r, "mm").length >= n) {
				return src.substr(0, i) + "...";
			}
		}
		return src;
	},
	getPicPath :function(uiid){
		var loadFlg = $("#"+uiid+"_pic").attr("loadFlg");
		if('true' == loadFlg){
			return;
		}
		/*
		if($("#"+uiid+"_pic").html().length>0){
			//$("#"+uiid+"_pic").toggle();
		}else{
		*/
			$.ajax({
			   type: "POST",
			   url: (_ctx+'/desk/desk-wab!showUserPic.action'),
			   data: {uiid:uiid},
			   dataType:'json',
			   success: function(data){
					
					var resultFlg = data.resultFlg;
					if(resultFlg == 'none'){
						return;
					}
					    
					var picFullPath  = data.picFullPath;
					//alert(picFullPath);
					if(picFullPath!=''){
						$("#"+uiid+"_pic").html('<img style="width:90px;height:112px;border:1px solid #cbcbcb;" src="' + picFullPath +'" />');
					}else{
						$("#"+uiid+"_pic").html('<div style="padding:3px;font-size:12px;">暂无照片</div>');
					}
					$("#"+uiid+"_pic").attr('loadFlg','true');
					return true;
				}
			});
		/*
		}
		*/
	},
	showNew : function(uiid){
		
		$.ajax({
		   type: "POST",
		   url: (_ctx+'/desk/desk-wab!showUserPic.action'),
		   data: {uiid:uiid},
		   dataType:'json',
		   success: function(data){
				
				var resultFlg = data.resultFlg;
				if(resultFlg == 'none'){
					return;
				}
				   
				var orgName      = data.deptName;
				var centOrgName  = data.centOrgName;
				var positionName = data.positionName;
				var userName     = data.userName;
				var fixedPhone   = data.fixedPhone;
				var picFullPath  = data.picFullPath;
				var sexCd 	     = data.sexCd;
				var email		 = data.email;
				 

				//eg,"http://localhost:8080/uaap/app/download.action?fileName=20100326162515bLEP.png&realFileName=PD快捷图标_32X32.png&bizModuleCd=uaapUser";
				var imgHTML = "";
				if( picFullPath!= ''){
					imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + picFullPath +'" />';
				} else{
					//2012-05-29 判断男女
					if(sexCd == '1'){
						imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + _ctx +'/resources/images/desk2/user_pic_male.gif" />';
					}else if(sexCd == '2'){
						imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + _ctx +'/resources/images/desk2/user_pic_female.gif" />';
					}else{
						imgHTML = '';
					}
				}
				
				var employeeClass = "";
				var mobilePhone  = data.mobilePhone;
				
				//截取固话/手机号码 
				var shortFixedPhone  = fixedPhone;//VisitingCardUtil.getSubStr(fixedPhone , 20);
				var shortMobilePhone = mobilePhone;//VisitingCardUtil.getSubStr(mobilePhone, 26);

				var arr = new Array();
				arr.push('  <div class="operate"> ');
				//arr.push('       <div title="发送邮件" class="mail" onclick="VisitingCardUtil.hide();VisitingCardUtil.sendEmail(\''+ uiid +'\');" >发送邮件</div><span>|</span>' );
				//arr.push('       <div title="拷贝名片" class="copy" onclick="VisitingCardUtil.hide();VisitingCardUtil.copyCard(\''+ picTypeCd +'\',\''+ userCd +'\');">复制名片</div><span>|</span>' );
				arr.push('	</div>' );
				arr.push('  <div class="content">');
				arr.push('  <div class="' + employeeClass + '">'+imgHTML+' </div>');
				arr.push('  <div class="employeeInfo">');
				arr.push('		<table style="overflow:hidden"> ');
				arr.push('		<tr><td colspan=\'2\'>'+ userName +'</td></tr>' );
				arr.push('		<tr><th valign=\'top\'>座机:</th><td>' + shortFixedPhone  +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>手机:</th><td>' + shortMobilePhone +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>职位:</th><td>' + positionName +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>中心:</th><td>' + centOrgName +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>部门:</th><td>' + orgName     +'</td></tr>');
				arr.push('		<tr><th valign=\'top\'>邮箱:</td><td>' + email +'</td></tr>');
				arr.push('		</table> ');
				arr.push(' 	</div> ');
				arr.push('  </div>');
				
				//构造名片内容
				var contentDiv = arr.join("");

				$("#showContent").html(contentDiv);
 
				return true;
			}
		});
	}
};
