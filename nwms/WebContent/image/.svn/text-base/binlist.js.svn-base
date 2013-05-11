var mybin = new Array();	
	function choiceBin(v){
		var id = v.id;		
		var result = false;
		for(var i=0;i<mybin.length;i++){		
			if(mybin[i]==id) 
			{			
				mybin=mybin.del(i);
				result=true;
				document.getElementById(id).style.backgroundImage='url(../image/box_default.jpg)';
				//alert(mybin);
			}	
		}
		if(result==false){
			mybin.push(id);
			document.getElementById(id).style.backgroundImage='url(../image/box_click.jpg)';
			//document.getElementById(id).value=mybin;
			//alert(document.getElementById(v.id).style["background-image"]);
			//document.getElementById(id).style["background-image"]="url(../image/box_click.jpg);";
			//"width:54px;height:62px;  background-image:url(../image/box_click.jpg);";
		}
		document.getElementById("hf_bin_array").value=mybin;
		//alert(document.getElementById("hf_bin_array").value);
	}	
	
	function overBg(v){
		var id = v.id;	
		document.getElementById(id).style.backgroundImage='url(../image/box_mouse.jpg)';
	}
	
	function outBg(v){
		var id = v.id;	
		var result=false;
		for(var i=0;i<mybin.length;i++){
			if(mybin[i]==id){				
				result=true;
			}
		}
		if(result==false){
			document.getElementById(id).style.backgroundImage='url(../image/box_default.jpg)';
		}else{
			document.getElementById(id).style.backgroundImage='url(../image/box_click.jpg)';
		}
		
	}
	
	
	Array.prototype.del=function(n) {  //n表示第几项，从0开始算起。
//prototype为对象原型，注意这里为对象增加自定义方法的方法。
  if(n<0)  //如果n<0，则不进行任何操作。
    return this;
  else
    return this.slice(0,n).concat(this.slice(n+1,this.length));
    /**//*
      concat方法：返回一个新数组，这个新数组是由两个或更多数组组合而成的。
      　　　　　　这里就是返回this.slice(0,n)/this.slice(n+1,this.length)
     　　　　　　组成的新数组，这中间，刚好少了第n项。
      slice方法： 返回一个数组的一段，两个参数，分别指定开始和结束的位置。
    */
}
//我们来试一试这个自己增加的方法


//选中行或列
//;
	function choiceTier(v){
		var check_id=v.id;
		if(v.id=="t0"){
			if(v.checked==true){
				mybin = new Array();
				var objs=document.all.tags("input");
				for(var i=0;i<objs.length;i++){
					if(objs[i].type=="checkbox"&&(objs[i].id.indexOf("t")||objs[i].id.indexOf("c"))){
						objs[i].checked=true;	
					}
				}
				
				var divs=document.all.tags("div");
				
				for(var j=0;j<divs.length;j++){
					if(divs[j].id.indexOf("-t")!=-1&&divs[j].id.indexOf("-c")!=-1){								
						//alert(mybin);
						mybin.push(divs[j].id);						
						document.getElementById(divs[j].id).style.backgroundImage='url(../image/box_click.jpg)';
					}
				}
		
				
			}else{
				mybin=new Array();
				var objs=document.all.tags("input");
				for(var i=0;i<objs.length;i++){
					if(objs[i].type=="checkbox"&&(objs[i].id.indexOf("t")||objs[i].id.indexOf("c"))){
						objs[i].checked=false;	
					}
				}
				
				var divs=document.all.tags("div");
				
				for(var j=0;j<divs.length;j++){
					if(divs[j].id.indexOf("-t")!=-1&&divs[j].id.indexOf("-c")!=-1){											
						document.getElementById(divs[j].id).style.backgroundImage='url(../image/box_default.jpg)';
					}
				}
				
			}
			
			document.getElementById("hf_bin_array").value=mybin;				
				return;
		}
	
		//表示打勾
		if(v.checked==true){ 
		       var result=false;
				//获得所有DIV对象
				var objs =document.all.tags("div");
				//循环遍历div对象id
				for(var i=0;i<objs.length;i++){
					 result=false;
				//str为获得的div的id
					var str=objs[i].id;
					//如果str里包含-tXX则进入
					if(str.indexOf("-"+check_id+"-")!=-1){
					//	alert(str);
						for(var j=0;j<mybin.length;j++)
						{
							var bin=mybin[j];
							if(bin==str){
								result=true;
							}
						}
						if(result==false){
							mybin.push(str);
							document.getElementById(str).style.backgroundImage='url(../image/box_click.jpg)';
						}
					}
				}	
				//alert(mybin);
				document.getElementById("hf_bin_array").value=mybin;
		}
		if(v.checked==false){
			var result=false;
			var objs=document.all.tags("div");
			for(var i=0;i<objs.length;i++){
				result=false;
				var str=objs[i].id;
				if(str.indexOf("-"+check_id+"-")!=-1){
					for(var j=0;j<mybin.length;j++){
						if(str==mybin[j]){
							mybin=mybin.del(j);
							document.getElementById(str).style.backgroundImage='url(../image/box_default.jpg)';
						}
					}
				}
			}
			document.getElementById("hf_bin_array").value=mybin;
		}
	
	}	
		
//选择或取消列

	function choiceColumn(v){
		var check_id=v.id;
		//表示打勾
		if(v.checked==true){ 
		       var result=false;
				//获得所有DIV对象
				var objs =document.all.tags("div");
				//循环遍历div对象id
				for(var i=0;i<objs.length;i++){
					 result=false;
				//str为获得的div的id
					var str=objs[i].id;
					//如果str里包含-tXX则进入
					if(str.indexOf("-"+check_id+"-")!=-1){
					//	alert(str);
						for(var j=0;j<mybin.length;j++)
						{
							var bin=mybin[j];
							if(bin==str){
								result=true;
							}
						}
						if(result==false){
							mybin.push(str);
							document.getElementById(str).style.backgroundImage='url(../image/box_click.jpg)';
						}
					}
				}	
				//alert(mybin);
				document.getElementById("hf_bin_array").value=mybin;
		}
		if(v.checked==false){
			var result=false;
			var objs=document.all.tags("div");
			for(var i=0;i<objs.length;i++){
				result=false;
				var str=objs[i].id;
				if(str.indexOf("-"+check_id+"-")!=-1){
					for(var j=0;j<mybin.length;j++){
						if(str==mybin[j]){
							mybin=mybin.del(j);
							document.getElementById(str).style.backgroundImage='url(../image/box_default.jpg)';
						}
					}
				}
			}
			document.getElementById("hf_bin_array").value=mybin;
		}
		
		
	}
	
	
	
	function checkChoice(){
	//	var sub=document.getElementById("form_sbt_modify");
	//	alert(sub);
		form.target='_parent';
		if(mybin.length==0){
			alert("please choose one or more");
			return false;
		}else{
			return true;
		}
	}
	
	
	//function setTarget(){
	//	alert('aaa');
		//var sub=document.getElementById("form_sbt_modify").value;
		//sub.setAttribute("target","_parent");
//	}