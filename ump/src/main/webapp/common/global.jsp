<script language="javascript">
var _ctx = '${ctx}';

function autoHeight(specialName){
	var endHeight = 0;
	try{
		var oheight = $(window.top.document).find("#bodyLoad").height();
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			var ch = 0;
			try{
				ch=$(window.top.document).find('#' + menuId+'_frame').get(0).document.body.scrollHeight;
				var ch2= $(document).height();
				if(ch2>ch || ch2==ch-51){ch=ch2;}
			}catch(e){
				ch=$(window.top.document).find('#' + menuId+'_frame').get(0).contentDocument.body.scrollHeight;
				var ch2= $(document).height();
				if(ch2>ch){ch=ch2;}
			}
			if(endHeight == 0){
				endHeight = ch;
			}else if(ch > endHeight){
				endHeight = ch;
			}
			if(endHeight>604){
				$(window.top.document).find("#bodyLoad").height(endHeight+51);
				$(window.top.document).find("#div_left_b").height(endHeight+51);
				$(window.top.document).find('#' + menuId+'_frame').contents().find("body").height(endHeight);
				$(window.top.document).find('#' + menuId+'_frame').height(endHeight);
			}
		}
	}catch(e){}
}

function rePosition(obj){
	obj.focus();
}
</script>