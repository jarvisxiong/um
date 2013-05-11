<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>权责信息配置</title>
      
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/js/ztree/css/demo.css" type="text/css"/>
	<link rel="stylesheet" href="${ctx}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bis/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/kindeditor/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.core-3.3.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	
	<style>
	    .btnpanel{
	       border:1px solid #A4CDF2;
	       padding:10px;
	       margin-top:10px;
	    }
	    .btu{
		    background-color: #21577A;
		    border: 1px solid #FFFFFF;
		    color: #FFFFFF;
		    cursor: pointer;
		    font-size: 12px;
		    font-weight: bold;
		    line-height: 20px;
		    margin-left: 5px;
		    padding: 3px;
		    text-align: center;
		    text-decoration: none;
	    }
	    
	    .right{
	      padding:5px;
	    }
	#tabList {
		line-height: 24px;
		width:500px;
		/*border-bottom:1px solid #cbcbcb;*/
	}
	#tabList li{
	    display: inline;
	    list-style: none;
	    border:1px solid;
	    padding:5px;
	    background-color:#f0f0f0;
	}
	#tabList li:hover{
		cursor: pointer;
	    background-color:#f5f5f5;
	}
	
	#tabList li.selected{
	    background-color:#ffffff;
	    border-bottom: 1px solid white;
	    font-weight: bolder;
	}  
	
    #tabList li.first{
	   margin-left:10px;
	}  
	
	.editPanel ,.savePanel{
	  /*border:1px solid #cbcbcb;*/
      border:1px solid;
	  width:100%;
	  padding:15px 5px;
	  background:#FFFFFF;
	}
	
	body{
	background:#F9F9F9;
	font-size: 12px;
	padding:10px;
	 }
	 
	 .content_wrap{
	   margin-top:10px;
	 }
	
	
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
    div#rMenu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
    input[type="text"]{
       border:1px solid #cbcbcb;
       height:20px;
    }
    table td{
      vertical-align:middle;
    }
	</style>
</head>
<body>
<div class="content_wrap" style="width:100%">
	<div style="float:left;margin-right:20px">
		<ul id="treeDemo" class="ztree" style="background:#F9F9F9;width:300px"></ul>
	</div>
	<div style="width:600px;float:left">
	
		
			<ul id="tabList">
			  <li class="selected first" model="0">新增权责</li>
			  <li model="1">修改权责</li>
			</ul>
		
			<input type="hidden" id="saveModel" name="saveModel" value="0"/>
			
			<div class="editPanel">
			  	<form id="resRightForm" name="resRightForm" method="post" action="${ctx}/res/res-rights!save.action">
		   		<input type="hidden" id="id" name="id"/>
				 	<table width="600" border="0" cellspacing="5">
	                <tr>
	                  <td width="89" align="right">名称：</td>
	                  <td width="450">
	                    <input name="rightsName" type="text" id="rightsName"  style="width:405px"/>                  </td>
	                  <td width="60">&nbsp;</td>
	                </tr>
	                <tr>
	                  <td align="right">父节点：</td>
	                  <td><input name="parentName" type="text" id="parentName" style="width:405px" readonly="readonly" onclick="showParent()"/>
	                  <input name="parentId" type="hidden" id="parentId" /></td>
	                  <td>&nbsp;</td>
	                </tr>
	                <tr>
	                  <td align="right">排序：</td>
	                  <td><input name="sequenceNo" type="text" id="sequenceNo" style="width:405px"/></td>
	                  <td>&nbsp;</td>
	                </tr>
	                <tr>
	                  <td align="right">是否可用：</td>
	                  <td>	<input name="ifActive_input" id="ifActive_radio_1" type="radio" value="1" checked="checked" />是
	                      	<input type="radio" id="ifActive_radio_0" name="ifActive_input" value="0" /> 否	                    </td>
	                  <td>&nbsp;</td>
	                </tr>
	                <tr>
	                  <td align="right" valign="top">res_auth_type_id：</td>
	                  <td><input name="resAuthTypeName" type="text" id="resAuthTypeName" onclick="showResAuthTree()" readonly="readonly" style="width:405px;float:left"/>
	                    <input name="resAuthTypeId" type="hidden" id="resAuthTypeId" /></td>
	                  <td><input type="button" name="c1" id ="resAuthType" clear='1' class="btu"  value=" 清除 " style="float:left" /></td>
	                </tr>
	                <tr>
	                  <td align="right" valign="top">res_option_id：</td>
	                  <td><input name="resOptionName" type="text" id="resOptionName" onclick="showOption()"  readonly="readonly" style="width:405px;float:left"/>
	                    <input name="resOptionId" type="hidden" id="resOptionId" /></td>
	                  <td><input type="button" name="btuSave3" id ="resOption" clear='1'  class="btu" value=" 清除 " style="float:left" /></td>
	                </tr>
	                <tr>
	                  <td align="right" valign="top">res_option_value_id： </td>
	                  <td><input name="resOptionValueName" type="text" id="resOptionValueName" onclick="showOptionValue()" readonly="readonly" style="width:405px;float:left"/>
	                    <input name="resOptionValueId" type="hidden" id="resOptionValueId" /></td>
	                  <td><input type="button" name="btuSave4" id ="resOptionValue" clear='1' class="btu" value=" 清除 " style="float:left" /></td>
	                </tr>
	                <tr>
	                  <td align="right">参数名转换后：</td>
	                  <td>&nbsp;</td>
	                  <td>&nbsp;</td>
	                </tr>
	                <tr>
	                  <td align="right">&nbsp;</td>
	                  <td><input type="button" name="btuSave" id ="btuSave" class="btu" value=" 保存 " style="float:left" /> 
	                      <input type="reset" name="btuReset" id ="btuReset" class="btu" value=" 重置 " style="float:left;display:" /><div id="saveInfo" style="float:left"></div></td>
	                  <td>&nbsp;</td>
	                </tr>
             	 	</table>
				</form>
			</div>
	</div>
</div>

<div id="rMenu">
	<ul>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
	</ul>
</div>

<script type="text/javascript">
var _ctx = '${ctx}';
var treeObj;
var setting = {
		   data:{
			     simpleData:{
			         enable:true,
			         idKey:"resRightsId",
			         pIdKey:"parentId"
				     },
				   key:{
					   name:"rightsName",
					   title:""
					   }
		       },
		
		async: {
			enable: true,
			url:"${ctx}/res/res-rights!buildTree.action",
		    autoParam:["resRightsId", "name=n", "level=lv"],
		    otherParam:{"otherParam":"zTreeAsyncTest"}
		},
		callback:{
                onClick:clickHandel,
                onRightClick:onRightClickHandel
			}
     
	};



	$(document).ready(function(){
		$("input[clear='1']").hide();
		$.fn.zTree.init($("#treeDemo"), setting);
		  treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		  rMenu = $("#rMenu");
           $("#tabList li").click(function(){
            	if( 'selected' != $(this).attr('class')){ 
    				$(this).siblings().removeClass('selected');
    				$(this).addClass('selected');
    				var saveModel = $(this).attr("model");
    				$("#saveModel").val(saveModel);
    				$("#btuReset").trigger('click');
    				$("input[type='hidden'][name!='saveModel']").val("");
    				if('1' == saveModel){
    					$("input[clear='1']").show();
        				}
    				else{
    					$("input[clear='1']").hide();
        				}
            	}
                
           });
           $("#btuSave").click(function(){
                 $("#resRightForm").ajaxSubmit(function(result){
                 $('#saveInfo').html('<font color="red">保存成功!</font>').show().fadeOut(3000);
              	   var nodes = treeObj.getNodesByParam("resRightsId", $("#parentId").val(), null);
              	   treeObj.reAsyncChildNodes(nodes[0],"refresh");
              	   if($("#saveModel").val()=='1'){
                       var  snodes = treeObj.getSelectedNodes();
                       treeObj.removeNode(snodes[0]);
                  	   }
            	});
            });

           $("input[clear='1']").click(function(){
                  var filed =  $(this).attr('id');
                  $("#"+filed+"Name").val('');
                  $("#"+filed+"Id").val('');
                  if("resOption" == filed){
                	  $("#resOptionValueName").val('');
                        $("#resOptionValueId").val('');
                      }
                  
               });
	});

	//节点点击处理函数
	function clickHandel(event,treeId,treeNode,clickFlag){
		
	         treeObj.expandNode(treeNode);
             if($("#saveModel").val()=='0'){
                  $("#parentName").val(treeNode.rightsName);
                  $("#parentId").val(treeNode.resRightsId);
                 }else{
                  $("#id").val(treeNode.resRightsId);
                  $("#rightsName").val(treeNode.rightsName);
                  $("#sequenceNo").val(treeNode.sequenceNo);
                  if(treeNode.ifActive){
                      $("#ifActive_radio_1").attr('checked','checked');
                      }else{
                       $("#ifActive_radio_0").attr('checked','checked');
                          }
                  var nodes = treeObj.getNodesByParam("resRightsId", treeNode.parentId, null);
                  $("#parentName").val(nodes[0].rightsName);
                  $("#parentId").val(nodes[0].resRightsId);
                  if(treeNode.resAuthTypeId){
	                  $.post( _ctx+'/res/res-rights!getResAuthName.action',{'resAuthTypeId':treeNode.resAuthTypeId},function(result){
	                	  $("#resAuthTypeName").val(result);
	                      });
                  }else{
                	  $("#resAuthTypeName").val('');
                      }
				  $("#resAuthTypeId").val(treeNode.resAuthTypeId);
                  if(treeNode.resOptionId){
                	  $.post( _ctx+'/res/res-rights!getResOptionName.action',{'resOptionId':treeNode.resOptionId},function(result){
	                	  $("#resOptionName").val(result);
	                      });
                      }
                  else{
                	    $("#resOptionName").val('');
                      }
                  $("#resOptionId").val(treeNode.resOptionId);
                  if(treeNode.resOptionValueId){
                	  $.post( _ctx+'/res/res-rights!getResOptionValueName.action',{'resOptionValueId':treeNode.resOptionValueId},function(result){
	                	  $("#resOptionValueName").val(result);
	                      });                    
                      }
                  else{
                	  $("#resOptionValueName").val('');
                      }
                  $("#resOptionValueId").val(treeNode.resOptionValueId);
                  
                  }
		}

	function OnAsyncSuccessHandel(event, treeId, treeNode, msg){
           treeObj.expandAll(true);
		}


	

	function showResAuthTree() {
		ymPrompt
				.confirmInfo({
					icoCls : "",
					title : '网批系统',
					message : "<div id='singleAttachDiv'><img align='absMiddle' src='"
							+ _ctx + "/images/loading.gif'></div>",
					useSlide : true,
					winPos : "c",
					width : 400,
					height : 400,
					maxBtn : false,
					allowRightMenu : true,
					afterShow : function() {
						var url = _ctx+ "/res/res-rights!resTree.action";					
						$.post(url, function(result) {
							$("#singleAttachDiv").html(result);
						});
					},
					handler : function(btn){
						//确定
						if (btn == 'ok') {
							var sId = $("#selectecId").val();
							var sname = $("#sname").val();
							if(sId!='')
							{
							   $("#resAuthTypeName").val(sname);
							   $("#resAuthTypeId").val(sId);
							}
							else{
                             alert("请选择网批！");
                             return;
								}
						}
						//取消
						else if (btn == 'cancel') {

						} else {

						}
						$("#singleAttachDiv").html('');
						ymPrompt.close();

						},
					autoClose : false
				});
	}

	
	function showOption(){
		ymPrompt.win({message: _ctx+ '/res/res-option.action?flag=1',width:600,height:500,title:'选项',handler:callBack,iframe:true});
		}

	function showOptionValue(){
		if($("#resOptionName").val()==''){
              //  alert("请先选择");
            returu;
			}
		ymPrompt.win({message: _ctx+ '/res/res-option!valueSelect.action?id='+$("#resOptionId").val(),width:500,height:350,title:'网批选项内容表',handler:optionValueCallBack,iframe:true});
		}

	//子窗口关闭后执行的函数
	//subPageInfo为子窗口返回的信息
	function callBack(subPageInfo) {
	    if (subPageInfo == 'close') return;
	    if (subPageInfo == '') return;

	    //业务逻辑....
	    $("#resOptionName").val(subPageInfo.name);
	    $("#resOptionId").val(subPageInfo.id);
	    $("#resOptionValueName").val('');
	    $("#resOptionValueId").val('');
	}

    function optionValueCallBack(subPageInfo){
    	 if (subPageInfo == 'close') return;
 	     if (subPageInfo == '') return;

 	    $("#resOptionValueName").val(subPageInfo.name);
	    $("#resOptionValueId").val(subPageInfo.id);
 	     
        }
    
	function showParent(){
		if($("#saveModel").val()=='0'){
                return;
			}
		ymPrompt.win({message: _ctx+ '/res//res-rights!tree.action',width:400,height:400,title:'父节点',handler:callBackParent,iframe:true});
		}

	function callBackParent(subPageInfo){
		 if (subPageInfo == 'close') return;
		    if (subPageInfo == '') return;

		    $("#parentName").val(subPageInfo.name);
            $("#parentId").val(subPageInfo.id);
		}
     //右键鼠标显示删除节点按钮
	function onRightClickHandel(event, treeId, treeNode){
        if(treeNode ){
             treeObj.selectNode(treeNode);
             showRMenu("node", event.clientX, event.clientY);
                
            }
		}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_del").hide();
			$("#m_check").hide();
			$("#m_unCheck").hide();
		} else {
			$("#m_del").show();
			$("#m_check").show();
			$("#m_unCheck").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

		$("body").bind("mousedown", onBodyMouseDown);
	}
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}

	function removeTreeNode() {
		hideRMenu();
		var nodes = treeObj.getSelectedNodes();
		if (nodes && nodes.length>0) {
		    if(confirm("确认删除？")){
				$.post( _ctx+'/res/res-rights!removeNode.action',{'id':nodes[0].resRightsId},function(result){
                          if('success'==result){
                        	  //treeObj.removeNode(nodes[0]);
                        	  var pnodes = treeObj.getNodesByParam("resRightsId", nodes[0].parentId, null);
                        	  treeObj.reAsyncChildNodes(pnodes[0],"refresh");
                              }else{
                                alert(result);
                              }
					});
		    }
			
		}
	}

	function clearOptionValue(){
		  $("#resOptionValueName").val('');
          $("#resOptionValueId").val('');
		}
</script>
	
</body>

</html>

