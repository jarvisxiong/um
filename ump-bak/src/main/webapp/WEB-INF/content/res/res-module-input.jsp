<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<div style="margin:5px 0">
<fieldset>
	<div style="padding-left: 10px;">
		<form action="${ctx}/res/res-module!save.action" id="editTypeForm" method="post">
			<input type="hidden" name="id" value="<s:property value='resAuthTypeId'/>"/>
			<s:hidden name="recordVersion"></s:hidden>
			<table class="editTable" style="width:100%;">
				<col style="width:70px;">
				<col>
				<tr>
					<td align="right">所属模块：</td>
					<td>
						<input id="parentModuleSearch" style="width:100%;" type="text" value="${resModule.moduleName}" />
						<input type="hidden" id="parentModuleId" value="${resModule.resModuleId}" name="parent_resModuleId" />
					</td>
				</tr>
				<tr>
					<td align="right">序号：</td>
					<td><input style="width:100%;" type="text" value="${sequenceNo}" name="sequenceNo" /> </td>
				</tr>
				<tr>
					<td align="right">分类代码：</td>
					<td><input style="width:100%;" type="text" value="${authTypeCd}" name="authTypeCd" readonly="readonly" onkeyup="validateCd('${authTypeCd}',this,'type')"/> </td>
				</tr>
				<tr>	
					<td align="right">分类名称：</td>
					<td><s:textfield cssStyle="width:100%;" name="authTypeName" id="authTypeEditId"></s:textfield></td>
				</tr>
				<tr>
					<td align="right">模板：</td>
					<td>
<!--					<s:select cssStyle="width:100%;" list="templetMap" emptyOption="true" name="templetCd" listKey="key" listValue="value"></s:select> -->
						<input id="templetName" style="width:100%;" type="text" value="<%=CodeNameUtil.getTempletNameByCd(JspUtil.findString("templetCd")) %>" />
						<input id="templetCd" type="hidden" name="templetCd" value="${templetCd}" />
					</td>
				</tr>
				<tr>
					<td align="right">显示名称：</td>
					<td>
						<input style="width:100%;" name="displayName" value="${displayName}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						是否有效：
					</td>
					<td align="left">
						<s:checkbox name="active"  cssStyle="width:20px;"></s:checkbox> 
						是否发布：<s:checkbox name="publish" cssStyle="width:20px;"></s:checkbox> 
					</td>
				</tr>
				<!--				
				<tr>
					<td align="right">备注：</td>
					<td>
						<textarea rows="2" style="height:60px;width:100%;" name="remark">${remark}</textarea>
					</td>
				</tr>
				-->
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td align="left">
						<button class="buttom" type="button" onclick="saveType(this);">保存</button>
						<button class="buttom" type="button" onclick="delType('${resAuthTypeId}')">删除</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</fieldset>
</div>
<div style="margin:5px 0">
<fieldset>
	<div style="margin:10px 2px 0px 2px;padding-left: 10px;">
		<div id="msg_grid_box">aa</div>
		<div id="condition_grid_box"></div>
		
		<div style="margin-top:20px;">
			<div>以下是审批节点:</div>
			<div id="step_grid_box"></div>
		</div>
		
		<div style="margin-top:20px;">
			<div>以下是额外配置节点:<br/>
			1、在会签中添加：审批节点=相关中心会签，审批人=hongqf，节点审批人=renlm,便宜量=0，会签序号=指定序号-1<br/>
			2、在流程中添加：审批节点=区域总经理，审批人=hongqf，节点审批人=renlm，便宜量=-1，
			</div>
			<div id="center_grid_box"></div>
		</div>
	</div>
	<div id="newConditionDiv" style="display: none">
		<form action="${ctx}/res/res-module!saveCondition.action" id="newContionForm" method="post">
			<input type="hidden" name="resConditonType.resAuthType.resAuthTypeId" id="resAuthTypeIdHidden" value="${resAuthTypeId}"/>
			<table class="editTable">
				<tr>
					<td align="right">权限代码:</td>
					<td><input style="width:200px;" type="text" name="resConditonType.conditionCd"  /></td>
				</tr>
				<tr>
					<td align="right">条件描述:</td>
					<td><input style="width:200px;" type="text" name="resConditonType.conditionName"  /></td>
				</tr>
				<tr>
					<td align="right">条件:</td>
					<td><input style="width:200px;" type="text" name="resConditonType.conditionValue"  /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button class="buttom" type="button" onclick="save(this)">保存</button></td>
				</tr>
			</table>
		</form>
	</div>
</fieldset>
<script type="text/javascript" >

var nodeMap = ${nodeMap};
var groupNodeMap = ${groupNodeMap};
var typeMap = ${typeMap};
var currentConditionTypeId;

//add by huangbijin 2011-03-30 时限
var timeLimitMap = ${timeLimitMap};
var stepOptionCdMap = ${stepOptionCdMap};
var activeMap = ${activeMap};
//新增一二级审批人
var onetwoMap =${onetwoMap};

var dsMsgOption= {
	fields :[
		{name : 'resAuthMsgId'},
		{name : 'sequenceNo'},
		{name : 'msgContent'},
		{name : 'recordVersion'}
	]
};
var colsMsgOption = [
     {id: 'sequenceNo' , header: "序号" , width :"60", editor: {  type :"text" ,validRule:['R','I'] } },
     {id: 'msgContent', header: "描述信息" ,width :"620",  editor: {  type :"text" ,validRule:['R'] } }
];
var gridMsgOption={
	id : "msg_grid_id",
	width: "700",  //"100%", // 700,
	height: "150",  //"100%", // 330,
	container : 'msg_grid_box', 
	loadURL: '${ctx}/res/res-module!input.action?json=msg&&id=${resAuthTypeId}',
	saveURL:'${ctx}/res/res-module!saveMsgs.action',
	replaceContainer : true, 
	dataset : dsMsgOption,
	columns : colsMsgOption,
	pageSize:10,
	//showIndexColumn : true,
	toolbarContent : '| reload | add del save | print',
	toolbarPosition :'top',
	skin : "mac",
	resizable : true,
	beforeSave : function(param){
		param.parentDataId = '${resAuthTypeId}';
		return true;
	},
	beforeInsert:function(record){
		record.sequenceNo=msgSize;
		msgSize++;
		return true;
	}
};
var dsOption= {

	fields :[
		{name : 'resConditonTypeId'  },
		{name : 'conditionCd'  },
		{name : 'conditionName'  },
		{name : 'conditionValue'  }	,
		{name : 'recordVersion'}
	]
};
var colsOption = [
     {id: 'conditionCd' , header: "权限代码" , width :"100", editor: {  type :"text" ,validRule:['R'] } },
     {id: 'conditionName', header: "条件描述" ,width :"288",  editor: {  type :"text" ,validRule:['R'] } },
     {id: 'conditionValue', header: "条件" , width :"290", editor: {  type :"text"  ,validRule:['R']} }
];

var gridOption={
	id : "condition_grid_id",
	width: "700",  //"100%", // 700,
	height: "250",  //"100%", // 330,
	container : 'condition_grid_box', 
	loadURL: '${ctx}/res/res-module!input.action?json=cond&&id=${resAuthTypeId}',
	saveURL:'${ctx}/res/res-module!saveConditions.action',
	replaceContainer : true, 
	dataset : dsOption ,
	columns : colsOption,
	pageSize:50,
	//showIndexColumn : true,
	toolbarContent : '| reload | add del save | print',
	toolbarPosition :'top',
	skin : "mac",
	resizable : true,
	beforeSave : function(param){
		param.parentDataId = '${resAuthTypeId}';
		return true;
	},
	afterRowSelect : function( record,  cell,  row,  colNo,  columnObj,  grid){
		currentConditionTypeId =  record["resConditonTypeId"];
		if(!currentConditionTypeId){
			return;
		}
		var stepGrid=GT.$grid("step_grid_id");
		var centerGrid=GT.$grid("center_grid_id");
		stepGrid.loadURL = "${ctx}/res/res-module!showSteps.action?id="+currentConditionTypeId;
		stepGrid.reload();
	}
};

var dsStepOption= {
		fields :[
			{name : 'resApproveStepId'  },
			{name : 'nodeCd'  },
			{name : 'approveLevel'  },
			{name : 'groupNodeCd'  },
			{name : 'sequenceNo'  },
			{name : 'timeLimit'  },
			{name : 'stepOptionCd'  },
			{name : 'backFlg'  },
			{name : 'recordVersion'},
			{name : 'approveRank'}
		]
	};
var colsStepOption = [
     {id: 'nodeCd' , header: "审批节点" , width :"180", editor: {  type :"select",options : nodeMap ,validRule:['R'] },renderer : GT.Grid.mappingRenderer(nodeMap)},
     {id: 'approveLevel', header: "级别" ,width :"50",  editor: {  type :"text" ,validRule:['R','I'] }   },
     {id: 'groupNodeCd', header: "节点组" ,width :"140",  editor: {  type :"select" ,options : groupNodeMap } ,renderer : GT.Grid.mappingRenderer(groupNodeMap)},
     {id: 'sequenceNo', header: "顺序号" ,width :"50",   editor: {  type :"text" ,validRule:['I'] }   },
     {id: 'timeLimit', header: "审批时限" ,width :"100",  editor: {  type :"select" ,options : timeLimitMap } ,renderer : GT.Grid.mappingRenderer(timeLimitMap)},
     {id: 'backFlg', header: "驳回" ,width :"40",  editor: {  type :"select" ,options : activeMap } ,renderer : GT.Grid.mappingRenderer(activeMap) },
     {id: 'stepOptionCd', header: "步骤类型" ,width :"80",  editor: {  type :"select" ,options : stepOptionCdMap } ,renderer : GT.Grid.mappingRenderer(stepOptionCdMap)},
     {id: 'approveRank', header: "一二级" ,width :"50",  editor: {  type :"select" ,options : onetwoMap } ,renderer : GT.Grid.mappingRenderer(onetwoMap)}
];
var gridStepOption={
		id : "step_grid_id",
		width: "700",  //"100%", // 700,
		height: "300",  //"100%", // 330,
		container : 'step_grid_box', 
		saveURL:'${ctx}/res/res-module!saveSteps.action',
		replaceContainer : true, 
		dataset : dsStepOption ,
		columns : colsStepOption,
		//showIndexColumn : true,
		toolbarContent : '| reload | add del save | print',
		toolbarPosition :'top',
		skin : "mac",
		resizable : true,
		remotePaging : true,
		beforeSave : function(param){
			param.parentDataId = currentConditionTypeId;
			return true;
		},
		beforeInsert:function(record){
			record.sequenceNo='0';
			record.stepOptionCd='0';
			record.backFlg='1';
			return true;
		}
	};
var dsCenterOption= {
		fields :[
			{name : 'resAutoNodeSetId'},
			{name : 'conditionCd'},
			{name : 'approveNodeCd'},
			{name : 'approveUserCd'},
			{name : 'otherCondtion'},
			{name : 'extraNodeCd'},
			{name : 'extraUserCd'},
			{name : 'sequenceNo'},
			{name : 'levelOffset'}
		]
	};
var colsCenterOption = [
	{id: 'conditionCd' , header: "条件CD" , width :"60", editor: {  type :"text" }},
	{id: 'approveNodeCd' , header: "审批节点" , width :"120", editor: {  type :"select",options : nodeMap ,validRule:['R'] },renderer : GT.Grid.mappingRenderer(nodeMap)},
	{id: 'approveUserCd', header: "审批人" ,width :"60",  editor: {  type :"text"}   },
	{id: 'otherCondtion', header: "其他条件" ,width :"80",  editor: {  type :"text"}   },
	{id: 'extraNodeCd' , header: "节点" , width :"140", editor: {  type :"select",options : nodeMap ,validRule:['R'] },renderer : GT.Grid.mappingRenderer(nodeMap)},
	{id: 'extraUserCd', header: "节点审批人" ,width :"80",  editor: {  type :"text" }   },
	{id: 'levelOffset', header: "偏移量" ,width :"50",  editor: {  type :"text" ,validRule:['R','I'] }   },
	{id: 'sequenceNo', header: "会签序号" ,width :"60",  editor: {  type :"text" ,validRule:['R','I'] }   }
];
var gridCenterOption={
		id : "center_grid_id",
		width: "700",  //"100%", // 700,
		height: "240",  //"100%", // 330,
		container : 'center_grid_box', 
		saveURL:'${ctx}/res/res-module!saveAutoNode.action',
		loadURL: '${ctx}/res/res-module!showAutoNode.action?id=${resAuthTypeId}',
		replaceContainer : true, 
		dataset : dsCenterOption ,
		columns : colsCenterOption,
		//showIndexColumn : true,
		toolbarContent : '| reload | add del save | print',
		toolbarPosition :'top',
		skin : "mac",
		resizable : true,
		pageSize:50,
		remotePaging : true,
		beforeSave : function(param){
			param.parentDataId = '${resAuthTypeId}';
			return true;
		},
		beforeInsert:function(record){
			record.approveNodeCd='157';
			record.extraNodeCd='187';
			record.levelOffset='-1';
			record.sequenceNo='0';
			return true;
		}
	};

var conditionGrid=new GT.Grid( gridOption );
var stepGrid=new GT.Grid( gridStepOption );
var centerGrid=new GT.Grid( gridCenterOption );
var msgGrid=new GT.Grid( gridMsgOption );
var msgSize=msgGrid.getAllRows().length+1;
$('#parentModuleSearch').quickSearch(
		'${ctx}/res/res-module!searchModule.action',
		['moduleName','moduleCd'],
		{resModuleId:'parentModuleId',moduleName:'parentModuleSearch'});
$('#templetName').quickSearch(
		'${ctx}/res/res-bill-templet!quickSearch.action',
		['templetName','templetCd'],
		{templetCd:'templetCd',templetName:'templetName'});
//////////////////////////////////////////////////////////

</script>
</div>



