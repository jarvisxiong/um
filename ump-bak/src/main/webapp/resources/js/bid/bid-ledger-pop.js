
	//弹出回标分析
	function showLedgerAnalysis(dom,bidLedgerId,orgCd){		
		var _jDom = $(dom);
		var $offset = _jDom.offset();
		$('#popWinAnalysis').hide();
		$('#popWinAnalysis').css({left:$offset.left,top:$offset.top + _jDom.height()+3}).show();
		$('#popWinAnalysis').attr("orgCd",orgCd);
		$('#popWinAnalysis').attr("bidLedgerId",bidLedgerId);
	}

	//打开新页面
	function popWinAnalysis(typeCd){
		$('#popWinAnalysis').hide();
		var bidLedgerId = $('#popWinAnalysis').attr("bidLedgerId");
		var orgCd = $('#popWinAnalysis').attr("orgCd");
		switch(typeCd){
			//量/价比较
			case 1:
				openWindow('bidLedgerAnalysis','回标分析[量/价比较]',_ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId+'&orgCd='+orgCd);
				break;
			//占总价比例分析
			case 2:
				openWindow('bidLedgerAnalysis','回标分析[占总价比例分析]',_ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId+'&orgCd='+orgCd);
				break;
			//平方米指标
			case 3:
				openWindow('bidLedgerAnalysis','回标分析[平方米指标]',_ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId+'&orgCd='+orgCd);
				break;
			//投标总价汇总比较
			case 4:
				openWindow('bidLedgerAnalysis','回标分析[投标总价汇总比较]',_ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId+'&orgCd='+orgCd);
				break;
				//投标总价汇总比较
			case 5:
				openWindow('bidLedgerAnalysis','回标分析[清单轮次差异]',_ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId+'&orgCd='+orgCd);
				break;
				//投标总价汇总比较
			case 6:
				openWindow('bidLedgerAnalysis','回标分析[标段工程汇总]',_ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId+'&orgCd='+orgCd);
				break;
			default:;
		}
	}
	
	//打开窗口
	function openWindow(id,desc,url){
		if(window.parent && window.parent.parent.TabUtils){
			window.parent.parent.TabUtils.newTab(id,desc,url);
		}else{
			window.open(url);
		}
	}
	
	//全屏
	function fullScreen(url){
		window.open(url);
	}
	