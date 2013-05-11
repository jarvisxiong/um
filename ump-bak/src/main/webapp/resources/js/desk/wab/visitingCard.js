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
	
	getCardId: function(picTypeCd, nodeId){
		return CONST_CARD_ID_PRE+ picTypeCd +'_'+nodeId;
	},
	/**
	 * 功能: 显示名片
	 * @param: dom - 以dom(html)对象为参照进行浮动显示名片
	 * @param: uiid - 用户UIID
	 * @param: userBizCd - 用户cd
	 */
	show : function(leftPad, picTypeCd, dom, uiid, nodeId, isOnline) {

		$("div."+CONST_CARD_CLS_NAME).hide();
	
		var myDomId =  $(dom).attr("id"); //很重要
		if(!myDomId){
			//alert(" dom id is needed!");
			return;
		}
		
		var myTopValue  = $(dom).offset().top + CONST_CARD_VIRTUAL_OFFSET;
		var myLeftValue = leftPad + CONST_CARD_HORIZON_OFFSET;;

		var cardId = VisitingCardUtil.getCardId(picTypeCd, nodeId);
		//.visitingCard 见homeStyle.css 
		if( $("#"+ cardId).length>0){
			$("#"+ cardId).css({"top":myTopValue+"px"});
			$("#"+ cardId).show();
			return;
		}
		
		$.ajax({
		   type: "POST",
		   url: (_ctx+'/desk/desk-wab!showUserPic.action'),
		   data: {uiid: uiid, posId: nodeId},
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
				//var myShowFlg  	 = data.myShowFlg;
				//.visitingCard 见homeStyle.css 
				if( $("#"+ cardId).length>0){
				$("#"+ cardId).show();
					return;
				}

				//eg,"http://localhost:8080/uaap/app/download.action?fileName=20100326162515bLEP.png&realFileName=PD快捷图标_32X32.png&bizModuleCd=uaapUser";
				var imgHTML = "";
				if( picFullPath!= ''){
					imgHTML = '<img style="width:90px;height:112px;border: 1px solid gray;" src="' + picFullPath +'" />';
				} 
				
				var employeeClass = "";
				//在线
				if( isOnline == '1'){
					employeeClass = "employeePicOnline";
				}else{
					employeeClass = "employeePicOffline";
				}
				
				var mobilePhone  = data.mobilePhone;
				
				//截取固话/手机号码 
				var shortFixedPhone  = VisitingCardUtil.getSubStr(fixedPhone , 20);
				var shortMobilePhone = VisitingCardUtil.getSubStr(mobilePhone, 20);
				
				var liShortFixedPhone = "";
				var liShortMobilePhone= ""; 

				if( shortFixedPhone != fixedPhone){
					liShortFixedPhone = ' style= "cursor:pointer" title="' + fixedPhone +'" ';
				}
				if( shortMobilePhone != mobilePhone){
					liShortMobilePhone= ' style= "cursor:pointer" title="' + mobilePhone +'" '; 
				}

				var arr = new Array();
				arr.push('  <div class="operate"> ');
				//arr.push('       <div title="发送邮件" class="mail" onclick="VisitingCardUtil.hide();VisitingCardUtil.sendEmail(\''+ uiid +'\');" >发送邮件</div><span>|</span>' );
				arr.push('       <div title="拷贝名片" class="copy" onclick="VisitingCardUtil.hide();VisitingCardUtil.copyCard(\''+ picTypeCd +'\',\''+ nodeId +'\');">复制名片</div><span>|</span>' );
				arr.push('	</div>' );
				arr.push('  <div class="content">');
				arr.push('  <div class="' + employeeClass + '">'+imgHTML+' </div>');
				arr.push('  <div class="employeeInfo">');
				arr.push('		<table> ');
				arr.push('		<tr class="cardTitle"><td colspan=\'2\' style=\'width:190px;\'>'+ userName +'</td></tr>' );
				arr.push('		<tr class="fixedPhone"   id="fixedPhone" '+liShortFixedPhone+'><td style=\'width:40px\'>座机:</td><td style=\'width:160px\'>' + shortFixedPhone  +'</td></tr>');
				arr.push('		<tr class="mobilePhone"  id="mobilePhone"'+liShortMobilePhone+'><td>手机:</td><td>' + shortMobilePhone +'</td></tr>');
				arr.push('		<tr class="positionName" id="positionName"><td valign=\'top\'>职务:</td><td>' + positionName +'</td></tr>');
				arr.push('		<tr class="centOrgName"  id="centOrgName"><td>中心:</td><td>' + centOrgName +'</td></tr>');
				arr.push('		<tr class="orgName"      id="orgName"	><td>部门:</td><td>' + orgName     +'</td></tr>');
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
	copyCard : function (picTypeCd,nodeId){
		var txt = $("#" + VisitingCardUtil.getCardId(picTypeCd, nodeId) +" .employeeInfo").text();
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
	}
};
