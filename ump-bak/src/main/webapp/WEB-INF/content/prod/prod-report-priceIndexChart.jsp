<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/js/prod/js/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prod/js/modules/exporting.js"></script>
</head>

<body>
	<div style="display: none;margin-left: 10px;" id="error">没有搜索到符合条件的的数据！</div>
	
 	<div id="container" style="width: 800px; height: 350px; margin-top:30px;margin-bottom: 30px;clear: left;"></div>
	<center>
		<div style="clear: left;text-align: center;">
			<font style="color: red;">友情提示：可点击相应的"地区"，以隐藏或显示图表中对应的曲线！<div id="remark"> </div></font>			
		</div>
	</center>
	
	<script type="text/javascript">
	var chart;
	//构建图表
	function viewChart(mateZoneCdFlg,dateTo,bussinessCd,mateZoneCd,title){
		var yearMonth;
		//var dateFrom=$("#input_dateFrom_3").val();
		//var dateTo=$("#input_dateTo_3").val();
		//var areaCd=$("select#input_areaCd :selected").val();
		//var bussinessCd=$("select#input_bussiness :selected").val();
		//var mateZoneCd="1";//$("select#input_mateZoneCd :selected").val();
		var chartName="";		
		//数据参数
		var data={dateTo:dateTo,bussinessCd:bussinessCd};
		if(mateZoneCdFlg=='1'){
			data['mateZoneCd']=mateZoneCd;
			chartName="工料价格曲线";
			$("#remark").html("人工基准价：60元/工日;砼基准价：410元/m3 ;钢筋基准价：4.800元/kg ;砌块基准价：240元/m3");
			}
		else if(mateZoneCdFlg=='2'){
			chartName="建安单价造价曲线";
			data['constructType']='constructType';
			$("#remark").html("备注:建安单价造价基准价：1530元/m2 ");
			}
		else{
			chartName="工料指数曲线";
			$("#remark").html("备注:工料价格指数基准价：733元/m2");
			}
		var dateurl="${ctx}/prod/prod-report!jsonChartYearMonth.action";	
		
		//时间
		$.post(dateurl,data,function(result){
			yearMonth=eval(result);
			});	
		//曲线数据
		var url="${ctx}/prod/prod-report!jsonChart.action";
		$.post(url,data,function(result){
			if(result==''){$("#error").show();return ;}else{$("#error").hide();}		
			chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					defaultSeriesType: 'line',
					marginRight: 60,
					marginBottom: 25,
					marginTop :100,
					height:350
				},
				title: {
					text: title,//标题
					x: -20, //center
					y:70
				},
				subtitle: {
					text: '',
					x: -20,
					y:440
				},
				exporting:{enabled :false},
				xAxis: {
					categories: yearMonth					
				},
				yAxis: {
					title: {
						text: chartName
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					formatter: function() {
							var ds=this.x;
							var y=ds.substring(0,2);
							var m=ds.substring(2,4);
			                return '<b>'+ this.series.name +'</b><br/>'+
			                y +'年'+m+"月:"+ this.y ;
					}
				},
				legend: {
					layout: 'horizontal',
					align: 'top',
					verticalAlign: 'center',
					x: 20,
					y: 0,
					width:700,
					itemWidth:100, 
					borderWidth: 0
				},
				series:eval(result)
			});	
		});
		
		}
	
	</script>
</body>
	
</html>