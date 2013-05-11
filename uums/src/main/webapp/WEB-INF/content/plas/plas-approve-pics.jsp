<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div>
	<s:if test="picAttas!= null && picAttas.size>0">
		<s:url id="pic" action="show" namespace="/app">
			<s:param name="id">${picAttas[0].appAttachFileId}</s:param>
			<s:param name="bizModuleCd">uaapUser</s:param>
		</s:url>
		<a id="big_img_box" target="_blank" href="javascript:window.open('${pic}')"><img src="${pic}"  style="height:120px;" /></a>
	</s:if>
	<s:else>
		<a id="big_img_box" target="_blank" href="${ctx}/images/user_default_photo.jpg"><img src="${ctx}/images/user_default_photo.jpg"  style="height:120px;" /></a>
	</s:else>
</div>
<%--
<div style="margin-top:5px;">
	<ul style="list-style-type:none;margin:0;padding:0;">
		<li id="small_img_box">
			<s:iterator value="picAttas" status="index">
				<a style="width:60px;height:60px;" src="${ctx}/${filePath}/${smallPicName}" bigSrc="${ctx}/${filePath}/${fileName}" class="img-normal" >&nbsp;<s:property value="#index.count"/>&nbsp;</a>
				<!--
				<img style="width:60px;height:60px;" src="${ctx}/${filePath}/${smallPicName}" bigSrc="${ctx}/${filePath}/${fileName}" class="img-normal" />
				 -->
			</s:iterator>
			<!--
			<s:if test="picAttas.size()==0">
				<img src="${ctx}/theme/default/images/admin/update.gif" bigSrc="${ctx}/theme/default/images/admin/update.gif" class="img-normal" />
			</s:if>
			 -->
		</li>
	</ul>
</div>
 --%>
