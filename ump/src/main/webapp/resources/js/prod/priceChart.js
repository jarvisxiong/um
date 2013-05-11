	var chart;
	
	function viewChart(){
		var yearMonth;
		var dateFrom=$("#input_dateFrom").val();
		var dateTo=$("#input_dateTo").val();
		var areaCd=$("#input_areaCd:selected").val();
		//数据参数
		var data={dateFrom:dateFrom,dateTo:dateTo,areaCd:areaCd};
		var dateurl="${ctx}/prod/prod-report!jsonChartYearMonth.action";	
		//时间
		$.post(dateurl,data,function(result){
			yearMonth=eval(result);
			});	
		//曲线数据
		var url="${ctx}/prod/prod-report!jsonChart.action";
		$.post(url,data,function(result){		
			chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					defaultSeriesType: 'line',
					marginRight: 130,
					marginBottom: 25
				},
				title: {
					text: '单地区多产品业态',
					x: -20 //center
				},
				subtitle: {
					text: '工料价格指数',
					x: -20
				},
				xAxis: {
					categories: yearMonth
				},
				yAxis: {
					title: {
						text: '价格指数'
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
							var dsArr=ds.split('-');
			                return '<b>'+ this.series.name +'</b><br/>'+
			                dsArr[0] +'年'+dsArr[1]+"月:"+ this.y ;
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -10,
					y: 100,
					borderWidth: 0
				},
				series:eval(result)
			});	
		});
		
		}