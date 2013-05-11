<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<ul id="resAuthTree" class="ztree" style="width:95%;height:300px"></ul>
<input type="hidden" id="selectecId">
<input type="hidden" id="sname">



<script type="text/javascript">
var resTreeObj;

var setting = {
		   data:{
			     
				   key:{
					   name:"text",
					   title:""
					   }
		       },
		
		async: {
			enable: true,
			url:"${ctx}/res/res-approve-info-tree!buildTree.action",
		    autoParam:["entityCd=moduleCd", "id=orgId"],
		    otherParam:{"active":"1","moduleTypeCdSrh":"0"},
		    dataFilter:DataFilter 
		},
		callback:{
            onClick:clickHandel
		}
  
	};




	$(document).ready(function(){
		$.fn.zTree.init($("#resAuthTree"), setting);
		resTreeObj = $.fn.zTree.getZTreeObj("resAuthTree");
	});

	function DataFilter(treeId, parentNode, childNodes) {
	    if (childNodes) {
	      if(childNodes.id=='0'){
		      childNodes.open='true';
		      return childNodes;
		      }
	    }
	    return childNodes.children;
	};

	//节点点击处理函数
	function clickHandel(event,treeId,treeNode,clickFlag){
		   resTreeObj.expandNode(treeNode);
	       $("#selectecId").val(treeNode.id);
	       $("#sname").val(treeNode.text);
            
		}


	
	
</script>
	


