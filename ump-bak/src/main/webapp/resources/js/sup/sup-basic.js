
function cancelSup(){
	window.location.href = window.location.href;
}
function doCancel(dom){
	$(dom).parent().parent().parent().parent().remove();
}
function createFile(sn){
	$("#addAttaFile"+sn).append("<input onchange=fileChange(this,'"+sn+"') name='upload"+sn+"' hidefocus=true type=file size=1 class=mailFile>");
}
function removeFile(dom){
    $(dom).parent().remove();
}
function fileChange(dom,sn){
	var sName,o;
    sName=dom.value.replace(/\\/g,"/").replace(/(.*\/)(.*)/,"$2");
   	if(!checkUploadFile(sName)){
   	   	return;
   	}
   	//若无附件ID，新增附件ID
   	if($.trim($('#fileSn'+sn).val()) == ''){
		var entityId = 'supplies_'+curr_user_cont+'_'+String((new Date().getTime()) ^ Math.random());
		$('#fileSn'+sn).val(entityId);
	}
    o=document.createElement("nobr");
    o.style.cssText="float:left;margin-right:6;padding-top:3;color:darkgreen";
    o.innerHTML="|"+sName+"<font onclick=removeFile(this) class=hand style=color:red;font-weight:bold>"+unescape("×")+"</font>"; 
    dom.style.display="none";
    o.appendChild(dom);
    $("#attaContainer"+sn).append(o);
    createFile(sn);
}
var UPLOAD_FILE_NAMES = new Array();
function checkUploadFile(fileName){
	if(UPLOAD_FILE_NAMES.contains(fileName)){
	return false;
	}
	return true;
} 

function doAddSaveSup(id){
	var audit =$("#supAudit").val();
	$("#supForm").attr("action",_ctx + "/sup/sup-basic!save.action");
	if(""==audit||"0"==audit){
		 $('#supAudit').val("0");
		 var supName =$("#supName").val();
		param={supName:supName};
		$.post(_ctx + "/sup/sup-basic!supNameValidate.action",param,
			   function(result){
				//新增
				if(id==""){
					if(result!="0"){
						alert("该公司名称已存在，请重新输入");
						return false;
					}
				}else{
					if(!(result=="0"||result=="1")){
						alert("该公司名称已存在，请重新输入");
						return false;
					}
				}
				 $('#supForm').submit();
			});
	}else{
		var pdv = new Validate("supForm");
		if(validateSup(id)){
			$('#supForm').submit();
		}
	}
}
function submitSup(id){
	var audit =$("#supAudit").val();
	$('#supAudit').val("1");
	var pdv = new Validate("supForm");
	//pdv.validate()
	if(validateSup(id)){
		$("#supForm").attr("action",_ctx + "/sup/sup-basic!save.action");
		$('#supForm').submit();
	}
}
function checkSup(id){
	$('#supAudit').val("2");
	if(validateSup(id)){
		$("#supForm").attr("action",_ctx + "/sup/sup-basic!save.action");
		$('#supForm').submit();
	}
}
function validateSup(id){
	var supName =$("#supName").val();
	if(""==supName){
		alert("请输入供方名称");
		return false;
	}
	if($("#supTypeSn").val()==""){
		alert("请输入供应类别");
		return false;
	}
	if($("#supExamResu1:checked").attr("checked")||$("#supExamResu2:checked").attr("checked")){
		if(!($("#attaContainer_exam").children().length>0||$("#uploadAttaContainer_exam").children().length>1)){
			alert("请上传现场考察结果附件");
			return false;
		}
		if(""==$("#examResuPerson").val()){
			alert("请填写考察人员");
			return false;
		}
		if(""==$("#examResuContent").val()){
			alert("请填写考察内容");
			return false;
		}
	}else if($("#supExamResu4:checked").attr("checked")){
		if(""==$("#examResuContent").val()){
			alert("请填写考察内容");
			return false;
		}
	}
	if($("#supEvaluate1:checked").attr("checked")||$("#supEvaluate2:checked").attr("checked")){
		if(!($("#attaContainer_eval").children().length>0||$("#uploadAttaContainer_eval").children().length>1)){
			alert("请上传履约评估附件");
			return false;
		}
	}
	$('input[alt="ShowPrompt"]').each(function(){
		if("如该栏目无相关信息，请填写‘无’"==$(this).val()){
			$(this).val("无");
		}
	});
	return true;
}
function doSupType(modId,supTypeSn){
	var selectId ="";
	var selectName ="";
	var finType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"供应商类别",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		showMask : false,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_ctx + "/sup/sup-basic!getSupTree.action?supTypeSn="+supTypeSn, function(result){
				var tree = new TreePanel({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':_ctx
				});
				tree.on(function(node){
					if(node.attributes.children==null||node.attributes.children==""){
					  selectId =node.attributes.finItemCd;
					  selectName =node.attributes.text;
					  finType =node.attributes.finType;
					  
					}
				});
				tree.addListener("check",function(node){
					arrCheck =tree.getCheckedId();
					selectName=tree.getCheckedName();
					selectId=arrCheck;
					finType=tree.getCheckedType();
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			if(selectId!=""){
				$("#supTypeSn").val(selectId);
				
				if(modId!=""){
				    $("#supTypeName"+modId).val(selectName);
				}else{
					$("#supTypeName").val(selectName);
				}
			   $("#tableType").val(finType);
			}
		}
	}
	});
}
function deleteFile(id,dom){
	if(id){
		if(confirm("确定要删除该附件吗？")){
		var param = {id:id,bizModuleCd:"suppliers"};
		$.post(_ctx + "/app/app-attachment!deepDelete.action",param, function(result) {
			 $(dom).parent().remove();
		});
		}
	}
}
var length=0;
var cloneMark =0;
function addContactor(){
	if(cloneMark==0&&length==0){
		length=$(".cont").length-1;
		cloneMark =length;
	}
	var clone =$("#tr_contact"+cloneMark).clone();
	
	$(clone).appendTo("#contact_div").attr("id","tr_contact"+(length+1));
	length++;
	$(clone).find("input").each(function(){
		$(this).attr("value","");
		var tname = $(this).attr("name",function(){
		var name = this.name;
		name = name.replace(name,name.substring(0,14)+length+name.substring(15,name.length));
		return name;
		});
		return tname;
	});
	$(clone).find("button").each(function(i, dom) {
		$(dom).show();
	});
}
function subContactor(contactId,id){
	if(contactId){
		if(confirm("确定要删除该记录吗？")){
			var param = {id:id,contactorId:contactId};
			$.post(_ctx + "/sup/sup-basic!deleteContactor.action",param,function(result){
				self.location=_ctx + "/sup/sup-basic!input.action?id="+id; 
			});
		}
	}
}