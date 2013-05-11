<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp" %>
		<%@ include file="/common/global.jsp" %>
		<title>标准与库</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/userSelection.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/oa/oa-file.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/jumpTable.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/oa/companymeeting.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/oa/userSelection.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css" />
		<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	</head>

	<body>
			
		<div class="title_bar" id="title_bar_banner">
			<input type="hidden" id="editedFolderId" value="" />
			<div class="fLeft" style="margin-top:5px;">
				<div class="fLeft banTitle">相关内容</div>
				<div class="fLeft tip_success">
					<span style="color: red;" id="succInfoMsg"></span>
				</div>
			</div>
			<div id="folderTitle" class="fLeft current_operate_file_file" style="margin-top:5px;"></div>
			<security:authorize ifAnyGranted="A_COMPANYMEET_ADMIN">
			<div class="fRight" style="margin-top:5px;">
				<input type="button" class="btn_blue" onclick="editFolder();" value="编辑目录" style="width:80px;"/>
				<input type="button" class="btn_blue"  onclick="addNewFolder();" value="新增目录" style="width:80px;"/>
			</div>
			</security:authorize>
		</div>
		
		<table style="width:100%;">
		<tr>
		<td id="main_left" style="overflow:hidden; width:180px;">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<tr>
					<td id="leftPanel" style="width:182px;background-color: #E4E7EC;" valign="top">
						<div id="searchCompanyFileFix" style="border-bottom:1px solid #BFBFBF;">
							<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td style="padding:2px;">
										<input  value="搜索目录..." 
												type="text" 
												style="padding-left:5px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
												onkeyup="searchTreeNode(this)"
												onblur="resetSearchApproveInput(this);"
												onclick="clearSearchApproveInput(this);"
												id="inputSearchVal"
										/>
									</td>
									<td style="width:56px;">
										<div id="inputSearchOperate" class="searchNextNoActive" onclick="searchTreeNode(document.getElementById('inputSearchVal'))">&nbsp;</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
						<tr>
							<td valign="top">
								<div id="divTreeP" style="border:none;background-color:#e4e7ec;overflow-y:auto;overflow-x:hidden;">
									<div id="foldersTree" style="padding-top:5px;">
										<div class="loading">加载目录...</div>
									</div>
								</div>
							</td>
							<td>
								<div id="noteResizeHandler" class="pd-chill-tip" title="您可以拖动,调整宽度" style="width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
							</td>
						</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td id="main_right" valign="top">
			<div id="main_right">
				<div id="rightContainer" class="right_container">
					<div class="inform_div">
						请选择文件夹浏览文件
					</div>
				</div>
			</div>
		</td>
		</tr>
		</table>
		<script type="text/javascript">
			$(function() {
				refreshFolderTree();
				resizeMainLeft();
				resizeMainRight();
	        	
				//左右拖拉
			    $('#main_left').resizable({
			        handler: '#noteResizeHandler',
			        min: { width: 182, height: ($('#main_left').height()) },
			        max: { width: 300, height: ($('#main_left').height()) },
			        onResize: function(e) {
			        	//$('#divTreeP').width($('#main_left').width()-5);
			        }
			    });
			});

			//************************* 搜索定位目录 ************************* 
			var curVal = null;
			var curNode = null;
			
			var searchTreeManager;
			function searchTreeNode(dom){
				if(searchTreeManager)clearTimeout(searchTreeManager);
				searchTreeManager = setTimeout(function(){
					processSearch(dom);
				}, 300);
			}
			function processSearch(dom){
				if($(dom).val().trim() == ''){
					$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
				}else{
					$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');
				}
				
				$(dom).css({color:"#5A5A5A"});
		 		if($(dom).val().trim() == curVal){
					//NONE
				}else{
					curVal = $(dom).val().trim();
					curNode = null;
				}
		 		curNode = folderTree.searchNode(curVal, curNode);
				if(curNode){
					var nodes = curNode.getPathNodes();
					for(var i= 0; i < nodes.length; i++){
						nodes[i].expand();
					}
					folderTree.setFocusNode(curNode);
					var nodeDom = curNode['html-element']['text'];
					var toh = $('#divTreeP').height();
					var top = $('#divTreeP')[0].scrollTop;
					var noh = $(nodeDom).offset().top;
					$('#divTreeP').animate({ scrollTop : top+noh-toh }, "normal");
				}else{
					//$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
				}
			}
			function resetSearchApproveInput(dom){
				if($(dom).val().trim() == ''){
					$(dom).val('搜索目录...');
					$(dom).css({color:"#cccccc"});
				}else{
					$(dom).css({color:"#5A5A5A"});
				}
			}
			function clearSearchApproveInput(dom){
				if( $(dom).val() == '搜索目录...'){
					$(dom).val('');
					$(dom).css({color:"#5A5A5A"});
				}
			}
		</script>
	</body>
</html>