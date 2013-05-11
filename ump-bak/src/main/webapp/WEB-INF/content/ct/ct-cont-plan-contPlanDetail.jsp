<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

	  		 <table class="shop-table" style="min-width: 350px;"  >
	          <col style="width:100px;text-align:right"/>
		      <col />
		    <tr>
			   <td style="width:100px;text-align:right">合同主体：</td>
			   <td>
			   		${entity.contMainCd}
			   </td>
			</tr>
			<tr>
			   <td style="width:100px;text-align:right">合同内容：</td>
		       <td>
		       		${entity.contContentDesc}
		       </td>
			 </tr>
			 <tr>
			   <td style="width:100px;text-align:right">合同属性：</td>
		       <td>
				${entity.contPropCd}
		      	</td>
			 </tr>
			 <tr>
			   <td style="width:100px;text-align:right">合同界面简要：</td>
		       <td>
			      	${entity.contFaceCd}
		       </td>
			 </tr>
			 <tr>
		  	   <td style="width:100px;text-align:right">招标前置条件：</td>
		       <td>
		           ${entity.contInviPreCd}
		       </td>
			 </tr>
			 <tr>
			   <td style="width:100px;text-align:right">施工面积(㎡)：</td>
			   <td>${entity.consructArea}(㎡)
			   </td>
			 </tr>
			  <tr>
			   <td style="width:100px;text-align:right">调整金：</td>
		       <td >${entity.diviTotalAmt}</td>
			 </tr>
			  <tr>
			   <td rowspan="2" style="width:100px;text-align:right">备注：</td>
		       <td rowspan="2">${entity.remark}</td>
			 </tr>
		 </table>
