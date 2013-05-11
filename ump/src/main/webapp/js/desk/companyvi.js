var _PATH = _ctx;
var folderTree;
var parentFolderTree;

// 更新文件列表块
function refreshFileList(folderId) {
	if ($.trim(folderId) == "") {
		return;
	}
	$("#rightContainer").html("<div><img style='width: 16px; height: 16px;' align='absMiddle' src='" + _PATH + "/images/loading.gif'>文件列表载入中...</div>");
	$.post(_PATH + "/oa/company-vi!fileList.action?folderId=" + folderId, function(result) {
		if (result) {
			$("#rightContainer").html(result);			
		}
	});
}
// 增加新的目录
function addNewFolder() {
	$("#folderTitle").html("增加新目录");
	$("#rightContainer").html("<div><img style='width: 16px; height: 16px;' align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>");
	$.get(_PATH + "/oa/company-vi!inputFolder.action", function(result) {
		if (result) {
			$("#rightContainer").html(result);			
		}
	});
}
// 编辑目录
function editFolder() {
	var editedId = $("#editedFolderId").val();
	if ($.trim(editedId) == "" || editedId == "-1") {
		alert("请选择一个有效目录！");
		return;
	}
	
	$("#folderTitle").html("编辑目录信息");
	$("#rightContainer").html("<div><img style='width: 16px; height: 16px;' align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>");
	$.get(_PATH + "/oa/company-vi!inputFolder.action?folderId=" + editedId, function(result) {
		if (result) {
			$("#rightContainer").html(result);			
		}
	});
}
// 打开新窗口选择父目录
function getParent() {
	var folderId = $("#folderId").val();
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择上级目录",
		message:"<div id='parentFolderDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:getParentFolder,
		afterShow:function(){
		$.get(_PATH + "/oa/company-vi!loadFolderTree.action?folderId=" + folderId, function(result) {
			$("#parentFolderDiv").html("");
			if (result) {
				parentFolderTree = new TreePanel({
					renderTo: "parentFolderDiv",
					'root'  : eval("(" + result + ")"),
					'ctx'	: _PATH
				});
				parentFolderTree.render();
				
				parentFolderTree.on(function(node){
					$("#selParentFolderId").val(node.id);
					$("#selParentFolderName").val(node.attributes.text);
				});
			}
		});
		}
	});
}
function getParentFolder(tp) {
	if (tp == "ok") {
		var newId = $("#selParentFolderId").val();
		
		if (newId != "-1") {
			$("#parentFolderId").val($("#selParentFolderId").val());
			$("#parentFolderNameInput").val($("#selParentFolderName").val());
		} else {
			$("#selParentFolderId").val("");
			$("#selParentFolderName").val("");
		}
	} else {
		$("#selParentFolderId").val("");
		$("#selParentFolderName").val("");
	}
}
// 保存目录信息
function saveFolder() {
	var name = $("#folderNameInput").val();
	if ($.trim(name) == "") {
		alert("目录名不能为空！");
		return false;
	}
	
	$("#inputFolderForm").submit();
}
// 清除上级目录输入框
function clearParent() {
	$("#parentFolderNameInput").val("");
	$("#parentFolderId").val("");
	$("#parentFolderName").val("");
}
// 删除目录
function delFolder() {
	if (confirm("确认删除当前目录吗？") == true) {
		$("#inputFolderForm").attr("action", "company-vi!delFolder.action").submit();
	}
}
// 返回主页面
function back() {
	$("#inputFolderForm").attr("action", "company-vi.action").submit();
}
// 更新目录树
function refreshFolderTree() {
	$.post(_PATH + "/oa/company-vi!loadFolderTree.action", function(result) {
		$("#foldersTree").html("");
		if ($.trim(result) != "") {
			folderTree = new TreePanel({
				renderTo: "foldersTree",
				'root'  : eval("(" + result + ")"),
				'ctx'	: _PATH
			});
			folderTree.render();
			folderTree.on(function(node){
				$("#editedFolderId").val(node.id);
				
				if (node.id == "-1") {
					return;
				}
				if(node.isExpand){
					node.collapse();
				} else {
					node.expand();
				}
				
				$("#folderTitle").html(node.attributes.text);
				refreshFileList(node.id);
			});
		} else {
			$("#foldersTree").html("<div style='text-align: center; line-height: 400px; height: 400px; font-weight: bold;'>没有文件夹可选择</div>");
		}
	});
}
// 新增文件记录
function addFile(folderId) {
	if ($.trim(folderId) == "") {
		alert("请选择一个目录");
		return;
	}
	
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"新增文件",
		message:"<div id='addFileDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:350,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:saveFile,
		afterShow:function(){
			$.get(_PATH + "/oa/company-vi!inputFile.action?folderId=" + folderId, function(result) {
				$("#addFileDiv").html(result);
			});
		}
	});
}
//编辑文件信息
function editFile(fileId) {
	var folderId = $("#editedFolderId").val();
	
	if ($.trim(folderId) == "") {
		alert("请选择一个目录");
		return;
	}
	
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"编辑文件信息",
		message:"<div id='editFileDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:350,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:saveFile,
		afterShow:function(){
			$.get(_PATH + "/oa/company-vi!inputFile.action?folderId=" + folderId + "&fileId=" + fileId, function(result) {
				if (result) {
					$("#editFileDiv").html(result);
				}
			});
		}
	});
}
function saveFile(tp) {
	if (tp == "ok") {
		$("#inputFileForm").ajaxSubmit(function(result) {
			if (result == "done") {
				var folderId = $("#editedFolderId").val();
				refreshFileList(folderId);
			}
		});
	}
}

// 删除文件
function delFile(fileId, folderId) {
	if (confirm("确认删除该文件吗?")) {
		$.post(_PATH + "/oa/company-vi!deleteFile.action?fileId=" + fileId, function(result) {
			if (result == "done") {
				refreshFileList(folderId);
			}
		});
	}
}
// 上传附件，上传完成后更新附件列表
function uploadAttach(fileId) {
	$("#inputUploadAttForm").ajaxSubmit(function(result) {
		if (result) {
			var url = _PATH + "/oa/company-vi!fetchAttachList.action?bizEntityId=" + fileId;
			$.get(url, function(result) {
				if (result) {
					$("#inputAttachList").html(result);
					$("#uploadInput").val("");
				}
			});
		}
	});
}
//删除附件，删除完毕后更新附件列表
function deleteAttachment(attachId, bizEntityId) {
	var url = _PATH + "/app/app-attachment!delete.action";
	$.post(url,
		{
			id:				attachId,
			bizModuleCd: 	"companyVi",
			bizEntityId:	bizEntityId
		},
		function(result) {
			if (result) {
				var url = _PATH + "/oa/company-vi!fetchAttachList.action?bizEntityId=" + bizEntityId;
				$.get(url, function(result) {
					if (result) {
						$("#inputAttachList").html(result);
					}
				});
			}
		}
	);
	return false;
}
// 翻页跳转函数
function jumpTo(folderId, pageNo) {
	var p = parseInt(pageNo);
	var t = parseInt($("#totalPageSize").val());
	if (p && t) {
		if (p <= 0 || p > t) {
			alert("请输入1至" + t + "的数字");
			return;
		}
		
		$("#filePageNo").val(pageNo);
		$.get(_PATH + "/oa/company-vi!fileList.action?folderId=" + folderId + "&filePage.pageNo=" + pageNo, function(result) {
			if (result) {
				$("#rightContainer").html(result);
			}
		});
	}
}
$(function() {
	refreshFolderTree();
});