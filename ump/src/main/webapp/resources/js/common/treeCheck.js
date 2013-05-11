var list='';//用来存储页面加载完之后所选中的节点的记录
/**
 * Description:保存初始化加载的所有选中的指定节点类型的id：如节点nodeType=='role',则保存此节点id，形式为id,id,id...
 * treeId:easyUi-tree的id名称
 * nodeTy:节点叶子的属性nodeType值
 */
function getOldChecked(treeId,nodeTy){
	var nodeList = '';
	var rankUserIds = '';
	var node = $('#'+treeId+'').tree('getChecked');
	for(var i = 0; i<node.length;i++){
		var flag = node[i].attributes.nodeType;
		if(flag==nodeTy)
		nodeList+=node[i].id+','+node[i].text+';';
		rankUserIds+=node[i].id+',';
	}	

	return nodeList;
}
/**
 * Description:获得删除的check节点
 * oldChecked:初始化之后，checked节点(数据形式为id,att...;id,att...;...  其中att为节点属性值)
 * getChecked：提交请求之前，checked节点(数据形式为id,att...;id,att...;...  其中att为节点属性值)
 * return ： delList:删除的节点id，（数据形式：以逗号分隔的执行属性值，如id,id,id,...）
 */
 function getDelCheckAtt(oldChecked,getChecked,att){
	 var idAtt = new Array();//存储一个节点的属性（第一个为id，紧跟其后为传入的属性）
	 	var strs= new Array(); //存储节点（<id,att...>;其中att表示传入节点的属性，可以为多个）
	 	var delList = '';
	 	strs=oldChecked.split(";"); //字符分割      
	 	for (i=0;i<strs.length ;i++ )    
	 		{    
	 		if(strs[i].indexOf(',')==-1)continue;
	 			idAtt = strs[i].split(",");
	 			var flag = getChecked.indexOf(idAtt[att]+",");
//	 			document.write("<br>--"+strs[i]+'在'+getChecked+'的第'+flag+'位'+"--<br/>");
	 			if(flag==-1){
	 				delList=idAtt[att]+','+delList;
	 			}
 		} 
 	return delList;
 }
/**
 * 获得删除节点的text
 * @param oldChecked
 * @param getChecked
 * @param id
 * @param att
 * @returns {String}
 */
 function getDelCheckAttById(oldChecked,getChecked,id,att){
	 var idAtt = new Array();//存储一个节点的属性（第一个为id，紧跟其后为传入的属性）
	 	var strs= new Array(); //存储节点（<id,att...>;其中att表示传入节点的属性，可以为多个）
	 	var delList = '';
	 	strs=oldChecked.split(";"); //字符分割      
	 	for (i=0;i<strs.length ;i++ )    
	 		{    
	 		if(strs[i].indexOf(',')==-1)continue;
	 			idAtt = strs[i].split(",");
	 			var flag = getChecked.indexOf(idAtt[id]+",");
//	 			document.write("<br>--"+strs[i]+'在'+getChecked+'的第'+flag+'位'+"--<br/>");
	 			if(flag==-1){
	 				delList=idAtt[att]+','+delList;
	 			}
 		} 
 	return delList;
 }
/**
 * Description:获得新增的check节点的id列表
 * oldChecked:初始化之后，checked节点(数据形式为id,att...;id,att...;...  其中att为节点属性值)
 * getChecked：提交请求之前，checked节点(数据形式为id,att...;id,att...;...  其中att为节点属性值)
 * return ： addList:新增的节点id，（数据形式：以逗号分隔的执行属性值，如id,id,id,...）
 */
 function getAddCheckAtt(oldChecked,getChecked,att){
		var idAtt = new Array();//存储一个节点的属性（第一个为id，紧跟其后为传入的属性）
		var strs= new Array(); //存储节点（<id,att...>;其中att表示传入节点的属性，可以为多个）
		var addList = '';
		strs=getChecked.split(";"); //字符分割      
		for (i=0;i<strs.length ;i++ )    
			{    
				if(strs[i].length<1)continue;
				idAtt = strs[i].split(",");
				var s = idAtt[att]+',';
				var flag = oldChecked.indexOf(s);
//				document.write("<br>--"+strs[i]+'在'+oldChecked+'的第'+flag+'位'+"--<br/>");
				if(flag==-1){
					addList=idAtt[att]+','+addList;
				}
			} 
		return addList;
	}
 /**
  * 获得添加节点的text
  * @param oldChecked
  * @param getChecked
  * @param id
  * @param att
  * @returns {String}
  */
 function getAddCheckAttById(oldChecked,getChecked,id,att){
		var idAtt = new Array();//存储一个节点的属性（第一个为id，紧跟其后为传入的属性）
		var strs= new Array(); //存储节点（<id,att...>;其中att表示传入节点的属性，可以为多个）
		var addList = '';
		strs=getChecked.split(";"); //字符分割      
		for (i=0;i<strs.length ;i++ )    
			{    
				if(strs[i].length<1)continue;
				idAtt = strs[i].split(",");
				var s = idAtt[att]+',';
				var flag = oldChecked.indexOf(s);
//				document.write("<br>--"+strs[i]+'在'+oldChecked+'的第'+flag+'位'+"--<br/>");
				if(flag==-1){
					addList=idAtt[att]+','+addList;
				}
			} 
		return addList;
	}