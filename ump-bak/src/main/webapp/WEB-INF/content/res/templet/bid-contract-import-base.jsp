<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
 功能：从定从审批表结束后创建合同文本并生成对应的合同台账，并且抓取网批中的附件及相关字段
 步骤：
 1.确定好要从定标审批表中要抓取的字段和附件后，只需要在要抓取的字段上增加resname="字段名"或resContLedField="字段名"，对要抓取的附件上增加 resattachname="附件名称".
    注：增加resname属性的字段在创建合同文本时是用来初始化对应span[resname="字段名"]填空文本区 的值
             增加resContLedField 属性的字段中在合同文本创建成功后作为生成合同台账的字段值
  	  增加resattachname属性在创建合同合同文本时作为合同文本的附件被抓取（网批附件名称与合同文本库中的附件类型一一对应，如果对应不上则自动把附件归属“其他”类附件类型）
      如： resname="projectName" ,resContLedField="projectName",resattachname="预算和批准文件" 为网批中增加自己的标志位

2.增加页面底部加入<%@include file="bid-contract-import-base.jsp"%>创建合同文本加脚本代码

3.当1，2都完成后，用户一个定标审批单走完后，点击”创建合同“时从再执行第4个操作具体见（doImportContLib）
。
 --%>
<s:if test="statusCd==2">
<script>

//合同编号唯一性校验
function contNoValidate(dom){
	if($(dom).val()){
		$.post(_ctx + "/cont/cont-ledger!contNoValidate.action?contNo=" + $(dom).val(),
			function(result){
				//合同新增，不是0条记录
				if(result!="0"){
					alert("该编号已存在，请重新输入");
					$(dom).val("");
				}
	     });
	}
}

/**
 * 当前定标审批表导入合同文本库
 */
function doImportContLib(resNo,ifNoContractPrice,ifNoContLeger){
   //已导入则直接结束
	if($("#btn_importContLib").attr("isImported") == "1"){
           return;
		}
	try{
		if(null==ifNoContractPrice || !ifNoContractPrice){
			if(isEmpty($.trim($("#contractPrice").val()))){
				alert("请填入审定价");
		        return;
			}
		}
		if(null==ifNoContLeger){
			ifNoContLeger = false;
		}
	}catch(e){}
   /**对网批中必填项文本值进行校验
    if(Validate != undefined){
	var pdv = new Validate("templetForm");
	if (!pdv.validate()) {
		//如果有未填项则不允许导入
	return;
	} 
    }
   **/

	/**
	第1步：把合同台账、合同文本库填空文本框所需字段封状成JSON格式数据
	**/
	//自动填充合同输入框的值
	var autoFileFields="[";
	//自动关联网批附件
	var  autoRelationAttachs="[";


	
	//创建合同文本填空所需字段
	var createContLedFiels="[";
	$.each($("input[resname]"),function(i,input){ 
		if(i>0){
 	   autoFileFields+=",";
         }
		autoFileFields+="{";
              
               autoFileFields+="fieldName:'"+$(input).attr("resname")+"',fieldVal:'"+$(input).val()+"'}";
		});


	autoFileFields+="]";
	
	//附件
   $.each($("td[resattachname]"),function(i,td){
	   if(i>0){
      	 autoRelationAttachs+=",";
           }
	   autoRelationAttachs+="{";
        
         autoRelationAttachs+="attachName:'"+ $(td).attr("resattachname")+"',";
         autoRelationAttachs+="attachId:'"+$(td).attr("value")+"'}";
	   });
   		autoRelationAttachs+="]";

        //合同台账字段
   		$.each($("input[resContLedField]"),function(i,input){

   			if(i>0){
   				    createContLedFiels+=",";
   		         }
   			createContLedFiels+="{";
   		              
   			createContLedFiels+="fieldName:'"+$(input).attr("resContLedField")+"',fieldVal:'"+$(input).val()+"'}";
  				});
   		createContLedFiels+="]";
		// end 1
   		
   		/**
   		第2步
   		把导入合同台账所需字段、值及合同文本库填空文本框所需字段、值保存后台cookie中
   		**/
   		$.ajax({
			url :_ctx + "/sc/sc-contract-templet-info!getResAttachType.action",
			type : 'POST',
			data : {resAttachList:autoRelationAttachs,autoFileFields:autoFileFields,createContLedFiels:createContLedFiels},
			async : false, // ajax同步
			timeout : 1000,
			error : function(responseText) {
				autoRelationAttachs=responseText;
			},

			success : function(responseText) {
				autoRelationAttachs=responseText;
				}
			});//end 2
	 /**
	 第3步：进入合同文本库创建合同并自动生成合同台账
	 **/
	var getContUrl = "${ctx}/sc/sc-contract-templet-info.action";
	getContUrl+="?resFields="+autoFileFields+"&resRela="+autoRelationAttachs.replace(/\"/ig,"'")+"&resNo="+resNo+"&ifNoContLeger="+ifNoContLeger;
	if (parent.TabUtils == null)
		window.open(getContUrl);
	else
		parent.TabUtils.newTab("scconInfo_Select", "合同文本库", getContUrl, true);
	//设置导入状态，1正在导入，防止重复导入
	$("#btn_importContLib").attr("isImported","1");
}
/**
 * 从合同文库中创建合同后调用此方法
   @param contLedgerNo 合同台账编号
   @param contractPrice 合同总价
   @param isSuccess true--合同台账导入成功  false--合同台账导入失败
 */
function doContImported(contLedgerNo,contractPrice,isSuccess){
      //如果合同台账创建成功，则设置合同编号则时移除”创建合同“按钮
	if(isSuccess){
		var ctLedgerMsg="<span>合同台账编号："+contLedgerNo+"</span>";
		
		$("#btn_importContLib").parent().next().html(ctLedgerMsg);
		$("#contractPrice").val(contractPrice);
		$("#contractPrice").attr("readonly","true");
		//移除创建合同按钮
		$("#btn_importContLib").remove();
		}else{
              //创建失败，则设置其状态为0，可再次重试
			$("#btn_importContLib").attr("isImported","0");
			}
}

//标准合同查看
function getContById(istandard,id,hisId){
  if(istandard=="0" || istandard==""){
	  var getContUrl="${ctx}/sc/sc-contract-templet-info!readNonStandardCon.action?scContId="+ id ;
		  	if(parent == null || parent.TabUtils==null)
		  		window.open(getContUrl);
		  	else
		  		parent.TabUtils.newTab("sc-contract-templet-info","非标准合同查看",getContUrl,true);
  }else{
	  var getContUrl="${ctx}/sc/sc-contract-templet-info!markContract.action?scContId="
			+ id 
			+ "&contractTempletHisId=" + hisId
			+ "&statusCd=${statusCd}";
			
		if(parent == null || parent.TabUtils==null)
			window.open(getContUrl);
		else
		  	parent.TabUtils.newTab("sc-contract-templet-info","标准合同查看",getContUrl,true);
	}
}
</script>
</s:if>