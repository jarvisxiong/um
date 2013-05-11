<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:22px;}
.btn_red_50_20{
	width: 50px;
	height: 20px;
	line-height: 22px;
	background-color: #449139;
	color: #FFF;
	cursor:pointer;
	text-align: center;
}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	            <th scope="col" align="left"  width="5%" >编号</th>
	            <th scope="col" align="left"  width="5%">建筑面积㎡(图)</th>
	            <th scope="col" align="left"  width="5%">套内面积㎡(图)</th>
	            <!--<th scope="col" align="left"  width="5%">公摊面积㎡</th>-->
	            <s:if test="floorType==1">
	            	<th scope="col" align="left"  width="4%">开租时间</th>
	            	<th scope="col" align="left"  width="4%">规划业态</th>
	            	<th scope="col" align="left"  width="7%">销售单价(元/㎡)</th>
	            	<th scope="col" align="left"  width="6%">租金(元/㎡)</th>
	            	<th scope="col" align="left"  width="6%">物业(元/㎡)</th>
	            	<th scope="col" align="left"  width="4%">商铺拆分</th>
	            </s:if>
	            <s:else>
	            	<th scope="col" align="left"  width="4%">出售时间</th>
	            	<th scope="col" align="left"  width="4%">规划业态</th>
	            	<th scope="col" align="left"  width="4%">房屋结构</th>
	            </s:else>
	            <th scope="col" align="left"  width="3%">操作</th>
	        </tr>
	  </thead>
	  <tbody>
		  <s:if test="floorType==1">
			  <s:iterator value="pageStore.result">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='${storeNo}'  onclick="storeEdit('${bisStoreId}');"  ><div class="ellipsisDiv_full" >${storeNo}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${square}'  onclick="storeEdit('${bisStoreId}');"  ><div class="ellipsisDiv_full" >${square}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${innerSquare}'  onclick="storeEdit('${bisStoreId}');"  ><div class="ellipsisDiv_full" >${innerSquare}</div></td>
		  	    		<!--<td align="left" class="pd-chill-tip" title='${publicSquare}' <c:if test="${ifChild!=1}"> onclick="storeEdit('${bisStoreId}');" </c:if> ><div class="ellipsisDiv_full" >${publicSquare}</div></td>-->
		  	    		<td align="left"  onclick="storeEdit('${bisStoreId}');"  ><s:date name ="openDate" format="yy-MM-dd" /></td>
		  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="layoutCd"/>'  onclick="storeEdit('${bisStoreId}'); " ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="layoutCd"/></div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${sellPrice}'  onclick="storeEdit('${bisStoreId}');"  ><div class="ellipsisDiv_full" >${sellPrice}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${rentStandard}'  onclick="storeEdit('${bisStoreId}');"  ><div class="ellipsisDiv_full" >${rentStandard}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${tenemStandard}'  onclick="storeEdit('${bisStoreId}');" ><div class="ellipsisDiv_full" >${tenemStandard}</div></td>
						<td align="center">
						<s:if test="remark==''">
						  <button class="btn_red_50_20" type="button" onclick="splitFloor('${bisStoreId}');"><s:if test="ifChild==1">还原 </s:if><s:else>拆分</s:else></button>
						</s:if>
						</td>
						<s:if test="ifChild==1">
						<td align="left" class="pd-chill-tip" title='已拆分' ><div class="ellipsisDiv_full" >已拆分</div></td>
						</s:if>
						<s:else>
						<td align="center">
						 <c:if test="${remark==''}">
						<button class="btn_red_50_20" type="button" style="background-color:#ac2727" onclick="deleteFloor('${bisStoreId}');">删除</button>
						</c:if>
						</td>
						</s:else>
						
			    	</tr>
			  </s:iterator>
			  
		  </s:if>
		  <s:else>
			  <s:iterator value="pageFlat.result">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='${flatNo}'  onclick="flatEdit('${bisFlatId}');"><div class="ellipsisDiv_full" >${flatNo}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${square}'  onclick="flatEdit('${bisFlatId}');"><div class="ellipsisDiv_full" >${square}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${innerSquare}'  onclick="flatEdit('${bisFlatId}');"><div class="ellipsisDiv_full" >${innerSquare}</div></td>
		  	    		<!--<td align="left" class="pd-chill-tip" title='${publicSquare}'  onclick="flatEdit('${bisFlatId}');"><div class="ellipsisDiv_full" >${publicSquare}</div></td>-->
		  	    		<td align="left"  onclick="flatEdit('${bisFlatId}');"><s:date name ="openDate" format="yy-MM-dd"/></td>
		  	    		<td align="left" class="pd-chill-tip" title='<div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" value="layoutCd"/>'  onclick="flatEdit('${bisFlatId}');"><div class="ellipsisDiv_full" ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" value="layoutCd"/></div></td>
		  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapHouseStru()" value="houseStruCd"/>'  onclick="flatEdit('${bisFlatId}');"><div class="ellipsisDiv_full" ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapHouseStru()" value="houseStruCd"/></div></td>
						<td align="center"><button class="btn_red_50_20" style="margin-right:5px;background-color:#ac2727" type="button" onclick="deleteFloor('${bisFlatId}');">删除</button></td>
			    	</tr>
			  </s:iterator>
		  </s:else>
		</tbody>
	</table>
	<s:if test="floorType==1">
		<div class="table_pager" style="margin-top:5px;">
		  <input type="hidden" id="storePageNo" value="${pageStore.pageNo}"/>
			<p:page pageInfo="pageStore"/>
		</div>
	</s:if>
	<s:else>
		<div class="table_pager" style="margin-top:5px;">
			<p:page pageInfo="pageFlat"/>
		</div>
	</s:else>
</div>
<script type="text/javascript">
function storeEdit(id){
  <security:authorize ifAnyGranted="A_PROJ_PROP_MODI">
    var page_no=$("#storePageNo").val();
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='storeDetailDiv' style='padding:5px;padding-right:15px;'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 790,
		height : 350,
		title : "编辑商铺信息",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bis/bis-project!storeDetail.action";
			$.post(url,{id:id,currentPageNo:page_no}, function(result) {
				$("#storeDetailDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var req=0;
				$(".requiredStore").each(function(i){
					if(""==$(this).val()&&$(this).css("display")!="none"){
						req=1;
						return false;
					}
				});
				if(req==1){
					alert("请输入红色标识的必选项");
					return false;
				}
				if("2"==$("#equityNature1").val()){
					if(""==$("#owner1").val()){
						alert("请输入业主");
						return false;
					}
					if(""==$("#manageStatus1").val()){
						alert("请输入经营现状");
						return false;
					}
					if(""==$("#ifPractice1").val()){
						alert("请输入是否开业");
						return false;
					} 
				}
				$('#storeDetaForm').ajaxSubmit(function(result) { 
					bisFloorSearch(page_no);
				});
			}
//				doInput(bigTypeId, smallTypeId);
//			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
	</security:authorize>
	
	<security:authorize ifNotGranted="A_PROJ_PROP_MODI">
	ymPrompt.win( {
		icoCls : "",
		message : "<div id='storeDetailDiv' style='padding:5px;padding-right:15px;'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 790,
		height : 350,
		title : "搜索商铺信息",
		afterShow : function() {
			var url = _ctx+"/bis/bis-project!storeDetail.action";
			$.post(url,{id:id}, function(result) {
				$("#storeDetailDiv").html(result);
			});
		}
	});
	</security:authorize>
}
</script>