<%@ page contentType="html/text; charset=UTF-8"%>
<%@ page import="java.net.URL"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="GENERATOR" content="Microsoft FrontPage 5.0" />
<meta name="ProgId" content="FrontPage.Editor.Document" />
<title></title>

<%if(session.getAttribute("conName")==null){
	    session.setAttribute("contName","合同");
	    } 
	 %>
<%
	response.setHeader("Content-disposition",
			"attachment; filename=合同.doc");%>

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<style type="text/css">
<!--
body {
	font-size: 12px;
}

.STYLE4 {
	color: #CC0000;
	font-weight: bold;
}

.inputBorder_readOnly,.approveTable .approveOption {
	font-size: 14px;
}

* {
	padding: 0;
	margin: 0;
}

html {
	font: 12px Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
	background-color: #fff;
	scrollbar-face-color: #C0C0C0;
	scrollbar-highlight-color: #FFFFFF;
	scrollbar-3dlight-color: #C0C0C0;
	scrollbar-darkshadow-color: #737373;
	scrollbar-shadow-color: #a6a6a6;
	scrollbar-arrow-color: #696969;
	scrollbar-track-color: #e0e0e0;
}


button {
	cursor: pointer;
	outline: none;
	border: none;
}

button {
	cursor: pointer;
	outline: none;
	border: none;
}

textarea {
	overflow: auto;
}

img {
	border: none;
}

/*
table{
	border-collapse: collapse;
	table-layout: auto;
}*/
a {
	text-decoration: none;
	color: inherit;
}

/*
input{
	height:16px;
}
*/
.color_blue {
	color: #0167a2;
}

.color_red {
	color: #ff0012;
}

.color_black {
	color: #464646;
}

.noResult {
	padding-top: 20px;
	font-size: 12px;
	color: #21577a;
	text-align: center;
}

.ellipsisDiv {
	overflow: hidden;
	white-space: nowrap;
	max-width: 80%;
	text-overflow: ellipsis;
	height: 24px;
	line-height: 24px;
}

.ellipsisDiv_full {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	height: 24px;
	line-height: 24px;
}

.title_bar {
	height: 27px;
	line-height: 26px;
	background-color: #e4e7ec;
	color: #363636;
	font-size: 14px;
	border-bottom: 1px solid #8c8f94;
}

.bottom_bar {
	width: 100%;
	height: 30px;
	line-height: 30px;
	background-color: #eee;
	font-size: 14px;
}

.search_bar {
	padding-left: 5px;
	height: 26px;
	line-height: 26px;
	background-color: #e4e7ec;
	color: #363636;
	font-size: 12px;
	border-bottom: 1px solid #aaabb0;
}

table.content_table {
	color: #464646;
	table-layout: fixed;
	width: 100%;
	text-align: left;
}

table.content_table tr th {
	padding-left: 4px;
	height: 30px;
	line-height: 30px;
	vertical-align: bottom;
	border-bottom: 1px solid #aaabb0;
	background-image: url("../../images/common/table_title_cutline.gif");
	background-position: left center;
	background-repeat: no-repeat;
	font-weight: normal;
}

table.content_table .mainTr {
	height: 30px;
}

table.content_table .mainTr td { /*padding-left:12px;*/
	cursor: pointer;
	overflow: hidden;
	border-bottom: 1px solid #dddbdc;
	padding-left: 4px;
}

table.content_table .mainTr:hover td {
	background-color: #edeff3;
	color: #40a3de;
}

table.content_table .mainTr td:hover {
	background-color: #d9dee6;
}

table.content_table .detailTr {
	background-color: #f7f7f7;
}

.detail_message_div {
	word-break: break-all;
	word-wrap: break-word;
	white-space: normal;
	border-bottom: 1px dashed #dddbdc;
}

pre {
	word-break: break-all;
	word-wrap: break-word;
	white-space: normal;
}

.table_info {
	margin: 0px 8px;
	height: 24px;
	width: 100%;
	position: absolute;
	bottom: 10px;
}

.read_all {
	float: left;
	display: block;
	margin-left: 10px;
	height: 24px;
}

.read_all a {
	color: #000;
	text-decoration: none;
	font-weight: bold;
	background-image: url("../images/desk2/read_all.gif");
	background-position: left center;
	background-repeat: no-repeat;
	padding-left: 20px;
	height: 24px;
	line-height: 24px;
}

.table_pager {
	float: right;
	height: 24px;
	line-height: 24px;
	margin-right: 40px;
}

.table_pager a {
	color: #000;
	text-decoration: none;
}

.table_pager input {
	border: 1px solid #000;
	height: 24px;
	width: 24px;
}

.noResult {
	width: 100%;
	text-align: center;
}

.pop_bg {
	background-color: #fff;
	display: none;
	width: 100%;
	height: 500px;
	left: 0;
	top: 0; /*FF IE7*/
	filter: alpha(opacity = 50); /*IE*/
	opacity: 0.5; /*FF*/
	z-index: 1;
	position: absolute; /*IE6*/
}

.btn_blue {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: #6eb1cf;
	border: #45738d solid 1px;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_green {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: #a4c45f;
	border: 1px solid #597126;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_grey {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: #ccc;
	border: 1px solid #597126;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_orange {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: #ffa613;
	border: #a8460e solid 1px;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_red {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: #ac2727;
	border: #5b0c0c solid 1px;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_35_20 {
	width: 35px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_35_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_35_55 {
	width: 35px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_35_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_55_55 {
	width: 55px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_55_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_55_20 {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_55_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_65_20 {
	width: 65px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_65_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_65_55 {
	width: 65px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_65_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_blue_75_55 {
	width: 75px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_blue_75_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_green_35_55 {
	width: 35px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_green_35_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
}

.btn_green_55_20 {
	width: 55px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_green_55_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_green_65_20 {
	width: 65px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_green_65_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_green_85_20 {
	width: 85px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_green_85_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_green_55_55 {
	width: 55px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_green_55_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_red_35_20 {
	width: 35px;
	height: 20px;
	line-height: 20px;
	background-color: transparent;
	background-image: url('../../images/common/btn_red_35_20.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_red_35_55 {
	width: 35px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_red_35_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_red_55_55 {
	width: 55px;
	height: 55px;
	line-height: 55px;
	background-color: transparent;
	background-image: url('../../images/common/btn_red_55_55.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	text-align: center;
}

.btn_full {
	width: 62px;
	height: 26px;
	line-height: 26px;
	background-color: transparent;
	background-image: url('../../images/common/btn_full.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	float: left;
	text-align: center;
}

.btn_full_white {
	width: 62px;
	height: 26px;
	line-height: 26px;
	background-color: transparent;
	background-image: url('../../images/common/btn_full_white.gif');
	background-repeat: no-repeat;
	background-position: center;
	color: #FFF;
	cursor: pointer;
	float: left;
	text-align: center;
}

.btn_title_bar {
	width: 62px;
	height: 26px;
	line-height: 26px;
	background-color: transparent;
	cursor: pointer;
	text-align: center;
	float: left;
}

.btn_title_bar:hover {
	width: 62px;
	height: 26px;
	line-height: 26px;
	background-color: #fff;
	cursor: pointer;
	text-align: center;
	float: left;
}

.btn_title_bar_4 {
	width: 82px;
	height: 26px;
	line-height: 26px;
	background-color: transparent;
	cursor: pointer;
	text-align: center;
	float: left;
}

.btn_title_bar_4:hover {
	width: 82px;
	height: 26px;
	line-height: 26px;
	background-color: #fff;
	cursor: pointer;
	text-align: center;
	float: left;
}

.btn_bottom_bar {
	width: 75px;
	height: 26px;
	line-height: 26px;
	background-color: transparent;
	cursor: pointer;
	text-align: center;
	float: left;
}

.btn_bottom_bar:hover {
	width: 75px;
	height: 26px;
	line-height: 26px;
	background-color: #fff;
	cursor: pointer;
	text-align: center;
	float: left;
}

.btn_bottom_chk_all {
	width: 30px;
	height: 26px;
	line-height: 26px;
	background-color: #6fb0d0;
	text-align: center;
	float: left;
}

.btn_cutline_3_26 {
	width: 3px;
	height: 26px;
	background-color: transparent;
	background-image: url('../../images/common/btn_cutline_3_26.jpg');
	background-repeat: no-repeat;
	background-position: center;
	text-align: center;
	float: left;
}

a[title $=".doc"],a[title $=".docx"] {
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right -108px;
}

a[title $=".xls"],a[title $=".xlsx"] {
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right 0;
}

a[title $=".pdf"] {
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right -72px
}

a[title $=".mp3"] {
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right -54px
}

a[title $=".swf"] {
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right -18px
}

a[title $=".rar"],a[title $=".zip"] {
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right -90px
}

a[title $=".jpg"],a[title $=".gif"],a[title $=".png"],a[title $=".tif"]
	{
	padding-right: 18px;
	padding-bottom: 3px;
	background: url(../../images/common/icon.png) no-repeat;
	background-position: right -36px
}

.input {
	width: 120px;
}

.loading {
	background: url(../../images/common/loading.gif) no-repeat left center;
	padding-left: 32px;
	height: 32px;
	line-height: 32px;
}

.jump_up {
	background: url(../../images/common/page_up.gif) no-repeat left center;
	padding-left: 20px;
	cursor: pointer;
}

.jump_up:hover {
	background: url(../../images/common/page_up_hover.gif) no-repeat left
		center;
}

.jump_up_none {
	background: url(../../images/common/page_up_none.gif) no-repeat left
		center;
	padding-left: 20px;
	height: 20px;
}

.jump_down {
	background: url(../../images/common/page_down.gif) no-repeat left center
		;
	padding-left: 20px;
	height: 20px;
	cursor: pointer;
}

.jump_down:hover {
	background: url(../../images/common/page_down_hover.gif) no-repeat left
		center;
}

.jump_down_none {
	background: url(../../images/common/page_down_none.gif) no-repeat left
		center;
	padding-left: 20px;
	height: 20px;
}

.spliter_funcs {
	background-image: url("../../images/common/btn_cutline_3_26.jpg");
	background-position: center center;
	background-repeat: no-repeat;
	height: 26px;
	width: 1px;
	padding: 0;
}

.spliter_head {
	background-image: url("../../images/common/head_cut.jpg");
	background-position: center center;
	background-repeat: no-repeat;
	height: 26px;
	width: 1px;
	padding: 0;
}

.btn_main {
	height: 55px;
	width: 55px;
	cursor: pointer;
	outline: none;
	border: none;
	margin: 3px 0 3px 0;
	background-image: url("../../images/common/btn_blue_55_55.gif");
	background-position: left center;
	background-repeat: no-repeat;
}

.btn_main:hover {
	background-image: url("../../images/common/btn_blue_55_55.gif");
	background-position: left center;
	background-repeat: no-repeat;
}

.btn_assist {
	height: 55px;
	width: 55px;
	cursor: pointer;
	outline: none;
	border: none;
	margin: 3px 0 3px 0;
	background-image: url("../../images/common/btn_green_55_55.gif");
	background-position: left center;
	background-repeat: no-repeat;
}

.btn_assist:hover {
	background-image: url("../../images/common/btn_green_55_55.gif");
	background-position: left center;
	background-repeat: no-repeat;
}

.btn_add_blue_bar {
	width: 76px;
	height: 26px;
	cursor: pointer;
	background-image: url("../../images/common/btn_add_blue_bar.gif");
	background-position: left center;
	background-repeat: no-repeat;
	color: #fff;
}

.btn_big_add {
	width: 80px;
	height: 30px;
	line-height: 30px;
	background-image: url("../../images/common/btn_big_add.gif");
	background-position: left center;
	background-repeat: no-repeat;
	color: #FFF;
	cursor: pointer;
	text-align: center;
	font-size: 14px;
}

.btn_big_blue {
	width: 80px;
	height: 30px;
	line-height: 30px;
	background-image: url("../../images/common/btn_big_blue.gif");
	background-position: left center;
	background-repeat: no-repeat;
	color: #FFF;
	cursor: pointer;
	text-align: center;
	font-size: 14px;
}

.shareFont {
	font-size: 10px;
	color: #0167a2;
}

.replyFont {
	font-size: 10px;
	color: #09c4fa;
}

.sort_desc {
	background: url("../../images/common/desc.gif") no-repeat center center;
	height: 24px;
	width: 18px;
	float: left;
}

.sort_asc {
	background: url("../../images/common/asc.gif") no-repeat center center;
	height: 24px;
	width: 18px;
	float: left;
}

.searchNextNoActive {
	background-image: url("../../images/res/search_next_noactive.jpg");
	background-position: 5px center;
	background-repeat: no-repeat;
	cursor: pointer;
	color: white;
	border: 0;
}

.searchNextActive {
	background-image: url("../../images/res/search_next_active.jpg");
	background-position: 5px center;
	background-repeat: no-repeat;
	cursor: pointer;
	color: white;
	border: 0;
}

.win_close {
	float: right;
	background: url(../../images/desk/close_tab.gif) no-repeat center center
		;
	width: 10px;
	height: 25px;
	padding-right: 10px;
	cursor: pointer;
}

.select_70_20 {
	height: 20px;
	width: 70px;
}

.common_tip {
	background-color: #FEE3CD;
	background-image: url("../../images/res/res_tip.png");
	background-position: 5px 5px;
	background-repeat: no-repeat;
	color: #5A5A5A;
	line-height: 18px;
	padding: 5px 0 5px 50px;
	padding-top: 8px;
	text-align: left;
	width: 90%;
	color: red;
	min-height: 40px;
}

select {
	padding: 5px;
}

select option {
	margin: 1px 0 2px 0;
	padding-left: 5px;
}

.main_input_content {
	padding: 0 5px 0 5px;
}

.main_first_col {
	padding-left: 5px;
}

.full_screen {
	background-image: url(../../images/plan/btn_full.gif);
	background-position: 3px center;
	background-repeat: no-repeat;
	cursor: pointer;
	padding-left: 30px;
	padding-right: 3px;
	line-height: 26px;
	height: 26px;
}

.full_screen:hover {
	background-color: white;
}

.split_vertial {
	background-image: url(../../images/common/btn_cutline_3_26.jpg);
	background-position: center center;
	background-repeat: no-repeat;
}

.btn_agree {
	background-color: transparent;
	background-image: url("../../images/common/btn_blue_55_20.gif");
	background-position: center center;
	background-repeat: no-repeat;
	color: #FFFFFF;
	cursor: pointer;
	height: 20px;
	line-height: 20px;
	text-align: center;
	width: 55px;
}

.btn_apply {
	background-color: transparent;
	background-image: url("../../images/common/btn_blue_55_20.gif");
	background-position: center center;
	background-repeat: no-repeat;
	color: #FFFFFF;
	cursor: pointer;
	height: 20px;
	line-height: 20px;
	text-align: center;
	width: 55px;
}

.btn_reject {
	background-color: transparent;
	background-image: url("../../images/common/btn_red_35_20.gif");
	background-position: center center;
	background-repeat: no-repeat;
	color: #FFFFFF;
	cursor: pointer;
	height: 20px;
	line-height: 20px;
	text-align: center;
	width: 55px;
}

.res_tip {
	text-align: left;
	margin: 10px 0;
	width: 95%;
	color: #5a5a5a;
	background-color: #fee3cd;
	background-image: url('../../images/res/res_tip.png');
	background-repeat: no-repeat;
	background-position: 5px 5px;
	padding: 8px 0;
	line-height: 22px;
}

.res_tip div ol {
	margin: 0;
	padding: 0;
}

.res_tip .spread {
	background-image: url('../../images/res/spread.gif');
	background-repeat: no-repeat;
	background-position: right center;
	cursor: pointer;
	vertical-align: middle;
}

.res_tip .spread:hover {
	background-image: url('../../images/res/spread_over.gif');
	background-repeat: no-repeat;
	background-position: right center;
}

.res_tip .packup {
	background-image: url('../../images/res/packup.gif');
	background-repeat: no-repeat;
	background-position: right center;
	cursor: pointer;
	vertical-align: middle;
}

.res_tip .packup:hover {
	background-image: url('../../images/res/packup_over.gif');
	background-repeat: no-repeat;
	background-position: right center;
}

.divSteps * {
	text-align: left;
	font-size: 12px;
}

html {
	background-color: #FFF;
	padding-left: 0px;
	padding-right: 0px;
	padding-top: 0px;
	padding-bottom: 0px;
	margin: 0px;
}

input,select,textarea {
	padding: 0;
	margin: 0;
}

fieldset {
	padding: 5px;
	margin-top: 1px;
	border: 1px solid #A4CDF2;
	background: #fff;
}

fieldset legend {
	color: #1E7ACE;
	font-weight: bold;
	padding: 1px 10px;
	border: 1px solid #A4CDF2;
	background: #fff;
}

fieldset label {
	float: left;
	width: 120px;
	text-align: right;
	padding: 4px;
	margin: 1px;
}

fieldset div {
	clear: none;
	margin-bottom: 2px;
}

table {
	table-layout: fixed;
	empty-cells: show;
	border-collapse: collapse;
	/*margin: 0 auto;*/
}

th {
	text-align: left;
}

.link {
	font-size: 12px;
	color: #0693e3;
	text-decoration: underline;
}

.link:hover {
	cursor: pointer;
}

img {
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	display: inline;
}

.search {
	width: 95%;
	margin: 0px 5px 5px 5px;
}

.search input[type="text"],.search select {
	width: 100%;
}

.search td {
	padding: 3px 10px 0 0px;
}

div {
	margin: 0;
	padding: 0;
}

.buttom {
	border: 1px solid #FFFFFF;
	background-color: #21577a;
	color: #FFFFFF;
	cursor: pointer;
	font-size: 12px;
	font-weight: bold;
	margin-left: 5px;
	padding: 3px;
	text-align: center;
	text-decoration: none;
	line-height: 20px;
}

.buttom:HOVER {
	background: #55eedd;
}

.clear {
	clear: both;
}

table.tb_approver {
	margin-bottom: 10px;
}

table.tb_approver .approver {
	text-align: right;
	border: none;
	padding: 0;
}

table.tb_approver td {
	padding: 0 5px;
	margin: 0;
}

table.tb_approver tr {
	height: 30px;
}

table.mainTable {
	width: 95%;
	border: 2px solid #cccccc;
}

table.mainTable input:focus {
	background-color: #ffffe1;
	border: 1px solid #dbdbdb;
}

table.mainTable textarea:focus {
	background-color: #ffffe1;
	border: 1px solid #dbdbdb;
}

table.mainTable tr {
	height: 30px;
}

table.mainTable .td_title {
	text-align: right;
	color: #333333;
}

table.mainTable td {
	word-wrap: break-word;
	padding: 0 5px;
	margin: 0;
	color: #000;
	border: 1px solid #dbdbdb;
}

table.mainTable th {
	word-wrap: break-word;
	padding: 0 5px;
	margin: 0;
	color: #000;
	font-weight: bold;
	border: 1px solid #dbdbdb;
}

table.mainTable input[type="file"],table.mainTable input[type="text"] {
	width: 100%;
	height: 100%;
}

table.mainTable input[type="checkbox"] {
	
}

.search_input {
	background-image: url("../resources/images/desk/wab/search.gif");
	background-position: 285px center;
	background-repeat: no-repeat;
	border-color: #8C8F94 #BFBFBF #BFBFBF;
	border-style: solid;
}

.inputBorderNoTip {
	border: 0;
	background-color: #DBE5F1;
	text-align: left;
	padding: 4px 0;
	color: #000;
}

.inputBorder,.inputBorderOther {
	border: 0;
	background-color: #DBE5F1;
	text-align: left;
	padding: 4px 0;
	color: #000;
}

.inputBorder[readonly="readonly"] {
	background-color: #dddbdc;
}

.inputBorderApprover {
	border: 1px solid #cccccc;
	background-color: #DBE5F1;
	width: 100%;
	padding: 4px 0;
}

table.tb_noborder {
	width: 100%;
}

table.tb_noborder td {
	border: none;
	padding: 0;
	text-align: left;
}

table.tb_inner {
	border: none;
	width: 100%;
}

table.tb_inner td {
	padding: 0;
	text-align: left;
}

table.tb_checkbox {
	border: none;
	padding: 0;
}

table.tb_checkbox td {
	border: none;
	padding: 0;
	text-align: left;
}

table.mainTable input[readonly="readonly"] {
	background-color: #dddbdc;
}

table.mainTable select {
	width: 100%;
}

table.mainTable textarea {
	width: 100%;
	font-size: 12px;
}

table.commonTable {
	border: 1px solid #cad9ea;
	color: #666;
	margin-left: 5px;
}

table.commonTable th {
	background-image: url(../images/th_bg1.gif);
	background-repeat: : repeat-x;
	height: 25px;
}

/**/
table.commonTable td,table.commonTable th {
	word-wrap: break-word;
	border: 1px solid #cad9ea;
	padding: 0 5px;
}

/*鏄剧ず琛ㄦ牸鐨勮秴閾炬帴棰滆壊*/
.commonTable>tbody>Tr:hover {
	color: #ffffff;
	/*cursor: pointer;*/
	background-color: #9a9fa6;
}

.commonTable .detailTr:hover {
	color: #666;
	cursor: default;
	background-color: #FFF;
}

.split {
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	padding: 2px;
	font-size: 12px;
}

.divTreeP {
	border: 1px solid #aaabb0;
	overflow-x: scroll;
}

.divContent {
	border: 1px solid #aaabb0;
}

.part {
	background-color: #e4e7ec;
	color: #363636;
	/*border-bottom:1px solid #8c8f94;*/
}

.error {
	color: red;
	border: 2px solid red;
}

table.mainTable .error {
	color: red;
	border: 2px solid red;
}

table.mainTable .error * {
	color: red;
}

.required {
	border-left: 2px solid red;
}

.require {
	color: red;
}

table.mainTable .required {
	border-left: 2px solid red;
}

@media print {
	.noprint {
		display: none;
	}
	.divTreeP {
		display: none;
	}
	#tree-div {
		display: none;
	}
	body {
		font-size: 15px;
	}
}

.paging {
	page-break-after: always
}

.subject {
	font-family: "瀹嬩綋";
	font-weight: bold;
	font-size: 26px;
	line-height: 40px;
}

.full_screen {
	background-image: url("../resources/images/common/btn_full_white.gif");
	background-position: 0px center;
	background-repeat: no-repeat;
	cursor: pointer;
	padding-left: 30px;
	padding-right: 4px;
	line-height: 26px;
	height: 26px;
}

.searchUserDiv {
	z-index: 9;
	position: absolute;
	display: none;
	border: 1px solid gray;
	background-color: #e8e8e8
}

.searchUserDiv div {
	border-bottom: 1px solid #fff;
	cursor: pointer;
}

.searchUserDiv div:hover {
	background-color: #4d7b9d;
	color: #fff;
}

.btn_mylist {
	background-color: transparent;
	background-image: url("../resources/images/res/btn_mylist_blue.gif");
	background-position: center center;
	background-repeat: no-repeat;
	color: #FFFFFF;
	cursor: pointer;
	height: 26px;
	line-height: 26px;
	text-align: center;
	width: 86px;
}

.btn_assistant {
	background-color: transparent;
	background-image: url("../resources/images/res/btn_assistant_green.gif")
		;
	background-position: center center;
	background-repeat: no-repeat;
	color: #FFFFFF;
	cursor: pointer;
	height: 26px;
	line-height: 26px;
	text-align: center;
	width: 86px;
}

.list_header2 {
	color: #363636;
	font-size: 12px;
	font-weight: bolder;
	line-height: 24px;
	height: 24px;
	border-top-width: 2px;
	border-top-color: #9db7c6;
	border-top-style: solid;
	margin: 0 10px;
}

.list_header {
	color: #363636;
	font-size: 12px;
	font-weight: bolder;
	line-height: 24px;
	height: 24px;
	border: none;
	margin: 0 10px;
	padding-left: 20p;
}

.list_header_img {
	float: left;
	padding: 7px 10px 0 10px;
}

.contentTextArea {
	height: 100px;
	margin: 2px 0;
}

.orgDiv {
	text-align: left;
	margin: 5px 0;
	background-color: #DDDBDC;
}

.orgDiv * {
	margin: 5px 0;
}

.xheditor-simple {
	height: 200px;
}

.footTip {
	font-size: 12px;
	color: red;
	text-align: left;
}

input[type="checkbox"] {
	vertical-align: middle;
	/*margin-left: 25px;*/
}

.checkbox {
	margin-right: 25px;
}

#quickSearchPanel div.quickSenior {
	color: white;
	padding: 5px;
	background-image: url("../resources/images/res/search_senior.gif");
	background-repeat: no-repeat;
	background-position: 5px 5px;
	cursor: pointer;
}

#quickSearchPanel div.quickSeniorHover {
	background-image: url("../resources/images/res/search_senior_hover.gif")
		;
}

#quickSearchPanel div.quickTitle {
	color: #464646;
	float: right;
	padding: 5px;
}

#quickSearchPanel .quickItem {
	padding: 5px;
	color: #0167a2;
	float: right;
}

#quickSearchPanel div.quickItem:hover {
	cursor: pointer;
	padding: 5px;
	text-decoration: underline;
}

.addApprove {
	background-image: url("../resources/images/res/add_approve_enable.gif");
	background-repeat: no-repeat;
	cursor: pointer;
	color: white;
	padding: 0 5px 0 20px;
	border: 0;
}

.searchNextNoActive {
	background-image:
		url("../resources/images/res/search_next_noactive.jpg");
	background-position: 5px center;
	background-repeat: no-repeat;
	cursor: pointer;
	color: white;
	border: 0;
}

.searchNextActive {
	background-image: url("../resources/images/res/search_next_active.jpg");
	background-position: 5px center;
	background-repeat: no-repeat;
	cursor: pointer;
	color: white;
	border: 0;
}

.inputBorder_readOnly {
	border: 0;
	background-color: #DDDBDC;
	text-align: left;
	margin: 4px 0;
}

.cost-nav {
	list-style-type: none;
	height: 30px;
	padding-left: 5px;
	font-size: 14px;
}

.cost-nav li {
	float: left;
	height: 20px;
	line-height: 20px;
	padding: 0 0px;
	margin: 5px 5px 5px 0;
	border: 1px solid #DDDBDC;
	cursor: pointer;
}

.cost-nav li:hover {
	background-color: #0167A2;
	color: #fff;
}

.cost-nav-click {
	background-color: #0167A2;
	color: #fff;
}

.highlight {
	color: red;
}

.approveTable {
	table-layout: fixed;
	width: 100%;
	padding: 0 0px 0 0px;
	color: #000;
	empty-cells: show;
}

.approveTable td {
	border: none;
	padding-left: 5px;
}

.approveTable tr.passTr {
	background-color: #eeeeee;
}

.approveTable tr.curTr {
	background-color: #fff;
	font-size: 12px;
	color: #000;
}

.approveTable tr:hover {
	background-color: #ffffe1;
}

.approveTable .curTr td:first-child {
	background-color: #fb9032;
	color: #fff;
}

.approveTable .curTr td {
	color: #000;
}

.approveTable .futureTr {
	background-color: #fff;
}

.approveTable .futureTr td:first-child {
	background-color: #eeeeee;
}

.approveTable .bottom {
	border-bottom: #BFC4C8 1px solid;
}

.approveTable td.approveName {
	font-size: 12px;
	font-weight: bold;
	color: #1e5494;
}

.approveTable td {
	font-size: 12px;
	color: #000;
}

.approveTable .approveLine {
	font-size: 12px;
	color: #0693e3;
	text-decoration: underline;
}

.approveTable .approveOption {
	font-size: 12px;
	color: #333333;
	padding-top: 4px;
	padding-bottom: 16px;
}

.approveTable .approveOptionTr:hover {
	background-color: #ffffe1;
}

.divNoPrint div {
	background-color: #eeeeee;
}

.msgTable {
	width: 100%;
}

.msgTable span {
	color: #000;
}

.msgTable .repDiv {
	clear: both;
	border: 1px solid #dddbdc;
	background-color: #ffffee;
	padding: 5px 5px 5px 20px;
}

.msgTable .repDiv div {
	background-color: #ffffee;
}

.divProcess {
	margin-left: 20px;
	padding-bottom: 8px;
	padding-right: 10px;
}

.divProcess div {
	margin: 2px 0;
	padding: 0 4px;
	float: left;
}

.divProcess .divCurNode {
	background-color: #fb9032;
	color: #fff;
}

.divProcess .divNode {
	background-color: #eeeeee;
}

.divProcess .arrow {
	float: left;
}

.titleBar {
	margin: 0;
	padding: 0;
}

.ul_none li {
	list-style: none;
}


	ins,ins table[tableid]{
	background:white !important;
	}
	span[otype=min]{
	background:white !important;
	}
-->
</style>
<script type="text/javascript">
$("ins").remove();
	window.resizeTo(window.screen.availWidth, window.screen.availHeight);
	window.moveTo(0, 0);
</script>
</head>

<body style="background-color: White;">

<div id="title" class="STYLE4"></div>
<br />
<div style="width:100%;margin-left: auto;margin-right: auto;">
<div id="print" style="float:left;margin-left: auto;margin-right: auto;width:800px;">
<div id="detailPanelDiv" style="padding-top: 15px; ">
<div id="div_position" style="padding: 0px 5px 5px 0px;">


<div style="padding: 0px 5px 40px 15px;width:100%;">
<span><span>编号：</span>
<span style="color: blue;">${conPrintInfo.contractNo}</span>&nbsp;
流水号：<span style="color: blue; margin-right: 25px;">${conPrintInfo.randPrintSN}</span>
</span>

 
 
 <div style="display:none" id="noprintDiv">
 <div  style='float:left;'><span>创建人：</span>
<span style="color: blue;">${conPrintInfo.creator}</span></div>
<div  style='float:left;'>
<span>创建日期：</span>
<span style="color: blue;">${conPrintInfo.createdDate} </span></div>
<div  style='float:left;'>
<span>状态：</span>
<span style="color: blue;">完成&nbsp;</span>
</div>

<span style="color: #5a5a5a;float:left;" >
打印人：${conPrintInfo.printUser}，${conPrintInfo.printDate}，
版本号：${conPrintInfo.printRecordVersion}
</span>

</div>
</div>
</div>

<div style="margin-left: auto;margin-right: auto;text-align:center;width:800px;">
<span class="subject">${conPrintInfo.contractName}</span></div>

</div>
${scContractParams.scContractHtml }

</div>
</div>
<script type="text/javascript">
$("ins").attr("comment","").atttr("style","").remove();
	var father = window.opener;
	if (father != null) {
		$("#print").html($(father.document).find("exportDiv").html());
	}
	
</script>
</body>
</html>