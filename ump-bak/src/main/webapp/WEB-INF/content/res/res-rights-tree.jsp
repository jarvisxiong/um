<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <title>权责信息配置</title>
      
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/js/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
		line-height: 25px;
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
	
	.editPanel ,.savePanel{
	  /*border:1px solid #cbcbcb;*/
      border:1px solid;
	  width:100%;
	  padding:5px;
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
	</style>
</head>
<body>

		<ul id="treeDemo" class="ztree" style="background:#F9F9F9;width:95%;height:290px;margin-top:0px"></ul>
		<input type="hidden" id = "selectedName"/>
		<input type="hidden" id = "selectedId"/>
		<div align="center">
		<input value="确定" type="button" class="btnStyle" style="margin-right:10px"  onclick="btnOkClick()"/>
		<input value="取消" type="button" class="btnStyle" onclick="btnCancel()"/>
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
                onClick:clickHandel
			}
     
	};



	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
		  treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		 
	});

	//节点点击处理函数
	function clickHandel(event,treeId,treeNode,clickFlag){
		
	         treeObj.expandNode(treeNode);
	         $("#selectedName").val(treeNode.rightsName);
	         $("#selectedId").val(treeNode.resRightsId);
           
		}

	function btnOkClick(){
		var info ={};
		info.name = $("#selectedName").val();
		info.id = $("#selectedId").val();
		window.parent.ymPrompt.doHandler(info, true);
		}


   function btnCancel(){
	   window.parent.ymPrompt.doHandler('', true);
	   }
	


	
</script>
	
</body>

</html>

