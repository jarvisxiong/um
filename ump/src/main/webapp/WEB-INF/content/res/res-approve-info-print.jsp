<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<style>   
  @media print{    
  .toolbar{display:none;}    
  }    
  .toolbar{border:1px solid #6A9BFA;background:#E8F7E8;}    
  .paging{page-break-after :always}    
  td{font-size:12px;color:#000000;}    
  </style>   
  
<div class='toolbar'>   
<object id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></object>   
<input type=button value=打印 onclick=document.all.WebBrowser.ExecWB(6,1)></input>
<input type=button value=直接打印 onclick=document.all.WebBrowser.ExecWB(6,6)></input>
<input type=button value=页面设置 onclick=document.all.WebBrowser.ExecWB(8,1)></input>
<input type=button value=打印预览 onclick=document.all.WebBrowser.ExecWB(7,1)></input>
</div>
<jsp:include page="res-approve-info-input.jsp"></jsp:include>