	$.extend($.fn.validatebox.defaults.rules, {
		    isExists: {
		        validator: function(value, param){
					var v =1;
					var m =2;
					var flg = true;
					var temp = new Array('','','','');
					if(param){
						temp=param;
					}
					var url = temp[0];
					var valueFiled = temp[1];
					var oldValueFiled = temp[2];
					var oldValue = '';
					if(temp[3])
					 oldValue = temp[3];
					//value = encodeURIComponent(value)
					//url = url+'?'+valueFiled+'='+value+'&'+oldValueFiled+'='+oldValue;
					var param = {};
					param[valueFiled] = value;
					param[oldValueFiled] = oldValue;
					$.ajaxSettings.async = false;
					$.post(url,param, function(result) {
						var obj = eval(result);
						if(obj.success){
							flg = true;
		        		}else {
		        			flg = false;
		        		}
					});
					$.ajaxSettings.async = true;
					
				return flg;
		        },
		        message: '已经存在.'
		    }
		});