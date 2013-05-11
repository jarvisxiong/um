package com.intelitune.nwms.warehouse;

import java.util.ArrayList;
import java.util.List;

import net.sf.click.Page;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;

import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.ColumnService;
import com.intelitune.nwms.service.ColumnServiceImp;
import com.intelitune.nwms.service.TierService;
import com.intelitune.nwms.service.TierServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class BinList extends Page {
//	Menu me = new Menu();
//	public String menuInclude = me.getWarehouse();
//
//	public String position = this.getMessage("bin_position");
//
//	public PageLink pageLink1 = new PageLink("bin_return", this
//			.getMessage("return"), RowList.class);
//
//	public String row_id;
//
//	public String title;

	public String binTableShow;
	public Form form = new Form();

	// public HiddenField hf=new HiddenField("hf_bin",this);
	// public TableEx table=new TableEx();

//	public RowService rs = new RowServiceImp();
	public BinService bs =  BinServiceImp.getInstance();
	public TierService ts =  TierServiceImp.getInstance();
	public ColumnService cs =  ColumnServiceImp.getInstance();
//	public FloorService fs=new FloorServiceImp();
	public WarehouseService ws= WarehouseServiceImp.getInstance();
//	public BuildingService bbs=new BuildingServiceImp();

	public BinList() {

		// hf.setId("hf_bin_array");
		form.add(new HiddenField("hf_bin_array", String.class));
		Submit submit=new Submit("sbt_modify", this.getMessage("modify"), this,
		"onModify");
		submit.setAttribute("onclick", "return checkChoice();");
		form.add(submit);
	}

	public void onRender() {
//		row_id = this.getContext().getRequestParameter("id");
//		if (row_id != null) {
//			this.getContext().getSession().setAttribute("rowlist_id", row_id);
//		}
//		title = ws.findNameById(this.getContext().getSessionAttribute("warehouselist_id").toString())+"----->"+bbs.fingNameById(this.getContext().getSessionAttribute("buildinglist_id").toString())+"----->"+fs.findNameById(this.getContext().getSessionAttribute("floorlist_id").toString())+"----->"+rs.findNameById(this.getContext().getSessionAttribute(
//				"rowlist_id").toString());
		List<Bin> list = bs.findBinByRow_id((String) this.getContext()
				.getSessionAttribute("rowlist_id"));
		// table.setRowList(list);
		if (list != null && list.size() > 0) {
			binTableShow = "<div style='width:1px;border:1px #333333 solid;'><table cellpadding='0' cellspacing='0' style='background-color:#999999; color:#ffffff;'>";
			String rowId = this.getContext().getSession().getAttribute(
					"rowlist_id").toString();
			// 遍历层
			int t;
			for (t = ts.countTierByRowId(rowId); t > -1; t--) {
				binTableShow += "<tr width='100%'>";
				binTableShow += "<td valign='top'><input type='checkbox' id="+"t"+t+" onclick='choiceTier(this);'/></td>";

				// 遍历列
				for (int c = 1; c <= cs.countColumnByRowId(rowId); c++) {
					// 寻找符合条件的bin
					if (t != 0) {
						binTableShow += "<td width='50' height='20'>";

						for (int i = 0; i < list.size(); i++) {
							if (Integer.parseInt(list.get(i).getTier()
									.getCode().trim()) == t
									&& Integer.parseInt(list.get(i).getColumn()
											.getCode().trim()) == c) {
								Bin b = list.get(i);
								
									binTableShow += "<div id='"
											+ b.getId()+"-"+"t"+t+"-"+"c"+c+"-"
											+ "' style='width:74px;height:92px;  background-image:url(../image/box_default.jpg);' onclick='choiceBin(this);' onmouseover='overBg(this);' onmouseout='outBg(this);' >";
									binTableShow +=
											"&nbsp&nbsp"+"<a class='box_a' target='_parent'  href='binShow.htm?id="+b.getId()+"'>"+b.getTier().getCode()+ "-"
											+ b.getColumn().getCode()+"</a>"+"&nbsp;&nbsp;"+"<a class='box_a' target='_parent' href='modifyOneBin.htm?id="+b.getId()+"'>"+this.getMessage("modify")+"</a>";
												
										binTableShow+="<div id='showState' style='padding-left:39px; padding-top:37px;'>";
									if(b.getState().getCode().equals(Const.NOT_IN_USE+"")){
										binTableShow+="<img src='../image/delete_box.gif'/>";
									}
									if(b.getState().getCode().equals(Const.LOCKED+"")){
										binTableShow+="<img src='../image/locked_box.gif'/>";
									}
										
										binTableShow+="</div>";
									
									
									binTableShow+=	 "</div>";
								
							}
						}
					} else {
						binTableShow += "<td align='center'><input type='checkbox' id="+"c"+c+" onclick='choiceColumn(this);'/>";
					}
					binTableShow += "";
					binTableShow += "</td>";
				}
				binTableShow += "</tr>";
			}
		}
		binTableShow += "</table></div>";
		
		binTableShow +="<div id='show_description'></div>";
	}

	public boolean onModify() {
		String bins = form.getFieldValue("hf_bin_array");
		String[] str = bins.split(",");
		ArrayList<String> result=new ArrayList<String>();;
		for(int i=0;i<str.length;i++){
			String[] temp=str[i].split("-");
			result.add(temp[0]);
		}
		this.getContext().getSession().setAttribute("bin_array", result);
		this.setRedirect(ModifyBin.class);
		return true;
	}
}