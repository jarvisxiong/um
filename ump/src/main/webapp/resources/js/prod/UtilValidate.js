//浮点数
function formatFloat(dom,tailNum,max){
	var value=$(dom).val();
	var rs=parseFloat(value);
	if(isNaN(rs)){		
		$(dom).val('');
	}else if(rs>max){
		$(dom).val('');
		alert('不能大于'+max);
	}
	else{
		if(tailNum){
			$(dom).val(rs.toFixed(tailNum));
		}
		else{
			$(dom).val(rs.toFixed(6));
		}
	}
		
}

//判断精度
function floatValidate(orData,alertname,firstLen,lastLen){
	//权重判空，精度
	if(orData.length<1){
		alert(alertname+'不能为空！');
		return false;
		}else{			
			//判断大小
			if(eval(orData)<=0){
				alert(alertname+'不能小于0');
				return false;
				}
			}
	//权重判断精度
	if(orData.indexOf('.')>0){
		var da=orData.split('.');
		if(da[0].length>firstLen){//7
			alert(alertname+'整数位不能大于'+firstLen+'位');
			return false;
			}
		
		if(da[1].length>lastLen){//6
			alert(alertname+'小数点后不能大于'+lastLen+'位');
			return false;
			}
		}else{//只有整数
			if(eval(orData)>10000000){
				alert(alertname+'整数位不能大于'+firstLen+'位');
				return false;
			}
		}
	return true;
}

//判断最长，包括中文
function validateLenth(orData,max,alertName){
	//判断最长
	var length = orData.replace(/[^\x00-\xff]/g,"**").length; 	
	if(length>max){
		alert(alertName+' 不能超出'+max+'个字符！');
		return false;
		}
	return true;
}
//判断必填
function mustWirte(orData){	
	var vn=orData.replace('/[/u3000]/g',"");	 
	if(vn.length<1){
		alert('版本号必填！');
		return false;
		} 
	return true;
}