//全屏
	function fullScreen(url){
		window.open(url);
	}
	
//打开窗口
	function openWindow(id,desc,url){
		if(window.parent && window.parent.parent.TabUtils){
			window.parent.parent.TabUtils.newTab(id,desc,url);
		}else{
			window.open(url);
		}
	}

//跳转到业态权重管理
function bussinessWeight(){
	openWindow('prodBussinessWeight','业态权重管理',_ctx + '/prod/prod-bussiness-weight!list.action');
}
//跳转到工料基准版本管理
function versionDetail(){
	openWindow('prodVersionDetail','工料基准价版本管理',_ctx + '/prod/prod-version-detail!list.action');
}

function matePrice(){
	openWindow('prodMaterialPrice','工料价格维护',_ctx + '/prod/prod-material-price!list.action');
}



