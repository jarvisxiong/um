var Style = {
	replace : function() {
		$(".showTable>tbody>tr[class!=detailTr]").bind("mouseover", function(i) {
			$(this).addClass("showTableTrHove");
		});
		$(".showTable>tbody>tr").bind("mouseout", function(i) {
			$(this).removeClass("showTableTrHove");
		});
		$(".button").bind("mouseover", function(i) {
			$(this).addClass("buttonHover");
		});
		$(".button").bind("mouseout", function(i) {
			$(this).removeClass("buttonHover");
		});
	}
}