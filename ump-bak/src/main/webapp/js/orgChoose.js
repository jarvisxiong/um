var rootOrgCd;
var isByUser='1';//是否根据登录人员的机构显示相关机构
var nameId,cdId;
var callBack;//回调函数
var isOnlyProject;//是否只显示项目机构
var isMulti;//是否可多选机构(暂未实现)
var isConParent=true;//返回的名字是否要包含上级机构名称
var orgType='Physical';//Logical
function getOrgJson(config) {
	rootOrgCd = isEmpty(config['root'])?'':config['root'];
	window.isByUser =isEmpty(config['isByUser'])?'0': config['isByUser'];
	window.isConParent =isEmpty(config['isConParent'])?'0': config['isConParent'];
	window.orgType =isEmpty(config['orgType'])?'Physical': config['orgType'];
	window.callBack = config['callBack'];
	window.nameId=String(config['nameId']);
	window.cdId=String(config['cdId']);
	if (isNotEmpty(config['isOnlyProject'])){
		window.isOnlyProject=String(config['isOnlyProject']);
	}else{
		window.isOnlyProject='';
	}
	if (isNotEmpty(config['isMulti'])){
		window.isMulti=String(config['isMulti']);
	}else{
		window.isMulti='';
	}
	return initOrg();
}
var tempOrgCd = null;
var tempOrgName = null;
var tempOrgType = null;
var parentCd = null;
var parentName = null;
// 打开机构选择框
function initOrg() {
	tempOrgCd = null;
	tempOrgName = null;
	tempOrgType=null;
	var data={isOnlyOrg:true,orgType:orgType};
	if(isNotEmpty(isOnlyProject)){
		data.isOnlyProject=isOnlyProject;
	}
	if(isNotEmpty(isMulti)){
		data.isMulti=isMulti;
	}
	ymPrompt.confirmInfo( {
		icoCls : "",
		title : "选择机构",
		message : "<div id='deptDiv' style='padding-top: 10px;'></div>",
		useSlide : true,
		winPos : "t",
		width : 350,
		height : 400,
		maxBtn : false,
		allowRightMenu : true,
		handler : selOrgInfo,
		afterShow : function() {
			$.post(_ctx + "/com/user-choose!getOrgTree.action",data, function(
					result) {
				if (result) {
					tree = new TreePanel( {
						renderTo : 'deptDiv',
						'root' : eval('(' + result + ')'),
						'ctx' : _ctx
					});
					tree.render();

					tree.on(function(node) {
						var id = node.attributes.id;
						if ($.trim(id) == '' || $.trim(id) == '0') {
							tempOrgCd = "";
							tempOrgName = "";
							tempOrgType="";
						} else {
							tempOrgCd = node.attributes.entityCd;
							tempOrgName = node.attributes.text;
							tempOrgType=node.attributes.extParam;
							var parentNode=node.parentNode;
							if (isConParent&&!isEmptyObj(parentNode)){
								parentCd=parentNode.attributes.id;
								parentName=parentNode.attributes.text;
								//tempOrgName=parentName+"/"+tempOrgName;
							}
							if (node.isExpand) {
								node.collapse();
							} else {
								node.expand();
							}
						}
					});
				}
			});
		}
	});
}
// 保存机构信息
function selOrgInfo(tp) {
	if (tp == "ok") {
		if (tempOrgName != null && tempOrgCd != null) {
			$("#" +nameId).val(tempOrgName);
			$("#" + cdId).val(tempOrgCd);
		} else {
			$("#" +nameId).val("");
			$("#" + cdId).val("");
		}
	}
}
function isEmpty(str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
function isNotEmpty(str) {
	return (!String.isEmpty(str));
};
function isEmptyObj(obj) {
	return ((function() {
		for ( var k in obj)
			return k
	})() != null ? false : true);
}