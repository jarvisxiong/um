package com.intelitune.nwms.material;

import java.rmi.RemoteException;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.MaterialState;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.MaterialStateService;
import com.intelitune.nwms.service.MaterialStateServiceImpl;
/**
 * 
 * @author Louis
 * 根据相关条件查询materila，这是料件模块的重要页面，因为后面的很多页面都是有这张页面开始的
 *
 */
public class SearchMaterial extends BorderPage {
	
	public Form form = new Form();
//	public DoubleField value = new DoubleField("value",this.getMessage("value"));
	public Select owner = new Select("owner",this.getMessage("owner"));
	public TextField alias = new TextField("alias",this.getMessage("zwalias"));
	public TextField code = new TextField("code",this.getMessage("code"));
	public Select state = new Select("state",this.getMessage("state"));
	public TableEx table = new TableEx("table");
	public MCSService mcs = MCSService.getInstance();
	public MaterialStateService mss =  MaterialStateServiceImpl.getInstance();
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public ActionLink edit = new ActionLink("edit",this.getMessage("edit"),this,"onEdit");
	public ActionLink view = new ActionLink("view",this.getMessage("view"),this,"onView");
	public String title = this.getMessage("searchmaterial");
	public String position = this.getMessage("searchMet");
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public SearchMaterial() throws RemoteException{
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
//		form.add(value);
		form.add(owner);
		form.add(code);
		form.add(alias);
		form.add(state);
		InttClientDetailWS client[] = mcs.getAllClient();
		owner.add(Option.EMPTY_OPTION);
		for(int i=0;i<client.length;i++){
			owner.add(new Option(client[i].getId(),client[i].getCnName()));
		}
		Submit search = new Submit("search",this.getMessage("search"),this,"onSearch");
		Submit reset = new Submit("reset",this.getMessage("reset"),this,"onReset");
		form.add(search);
		form.add(reset);
		
//		Column column1 = new Column("value",this.getMessage("value"));
		Column column2 = new Column("inttClientDetailWSId",this.getMessage("owner"));
		Column column4 = new Column("code",this.getMessage("code"));
		Column column3 = new Column("alias",this.getMessage("zwalias"));
		Column column8 = new Column("state.description",this.getMessage("state"));
		Column column5 = new Column("description",this.getMessage("description"));
//		Column column6 = new Column("remark",this.getMessage("remark"));
		Column column7 = new Column("action",this.getMessage("action"));
		column2.setDecorator(new Decorator(){
			public String render(Object object, Context context) {
				Material material = (Material)object;
				String id = material.getInttClientDetailWSId();
				if(id!=null){
					InttClientDetailWS er = mcs.findClientById(Integer.parseInt(id));
					return er.getCnName();
				}
				return null;
			}			
		});
		Column column9 = new Column("barCode",this.getMessage("barCode"));
//		table.addColumn(column9);
		column9.setDecorator(new Decorator(){
			public String render(Object object, Context context) {
				Material ma = (Material)object;
				if(ma.getCode()!=null){
					return "<a href='showBarCode.htm?code=" + ma.getCode()+"' target='_blank'><img src='http://www.hmglog.com/barcode/BarcodeServlet?data=" + ma.getCode() +"&width=1&height=50&resolution=200&type=Code128'/></a>";
				}
			return null;
			}
		});
//		table.addColumn(column1);
		table.addColumn(column2);
		table.addColumn(column3);
		table.addColumn(column4);
		table.addColumn(column8);
		table.addColumn(column5);
		table.addColumn(column9);
//		table.addColumn(column6);
//		edit.setImageSrc("../image/edit.gif");
//		view.setImageSrc("../image/view-16px.gif");
		ActionLink [] links = {view,edit};
		column7.setDecorator(new LinkDecorator(table,links,"id"));
		table.addColumn(column7);
		column7.setTextAlign("center");
		List<MaterialState> mli = mss.findAll();
		state.add(Option.EMPTY_OPTION);
		for(int i=0;i<mli.size();i++){
			MaterialState ms = mli.get(i);
			state.add(new Option(ms.getCode(),ms.getDescription()));
		}
//		for(MaterialState ms:mli){
//			state.add(new Option(ms.getCode(),ms.getDescription()));
//		}
	}
	
	public void onRender(){
		if(getContext().getSessionAttribute("searchMaterialHql")!=null){
			String hql = (String)getContext().getSessionAttribute("searchMaterialHql");
			table.setRowList(ms.findByHql(hql));
		}
	}
	
	public boolean onSearch(){
//		Float a = null;
//		String b = null;
//		String c = null;
//		String d = null;
//		if(value.getFloat()!=null){
//			a = value.getFloat();
//		}
//		if(owner.getValue()!=null&&!owner.getValue().equals("")){
//			b = owner.getValue();
//		}
//		if(!alias.getValue().equals("")){
//			c = alias.getValue();
//		}
//		if(!code.getValue().equals("")){
//			d = code.getValue();
//		}
		
//		return true;
		StringBuffer hql = new StringBuffer(" from Material i where 1=1");
//		if(value.getFloat()!=null){
//			hql.append(" and i.value=" + value.getFloat());
//		}
		if(owner.getValue()!=null&&!owner.getValue().equals("")){
			hql.append(" and i.inttClientDetailWSId='" + owner.getValue() + "'");
		}
		if(!alias.getValue().equals("")){
			hql.append(" and i.alias like '%" + alias.getValue() +"%'");
		}
		if(!code.getValue().equals("")){
			hql.append(" and i.code like '%" + code.getValue() + "%'");
		}
		if(!state.getValue().equals("")){
			hql.append(" and i.state.code='" + state.getValue() + "'");
		}
		getContext().setSessionAttribute("searchMaterialHql",hql.toString() );
		return true;
	}
	
	public boolean onEdit(){
		String id = edit.getValue();
		if(id!=null){
			getContext().setSessionAttribute("searchMaterialId", id);
			this.setRedirect("editMaterial.htm");
		}
		return true;
	}
	
	public boolean onReset(){
		getContext().removeSessionAttribute("searchMaterialHql");
		this.setRedirect("searchMaterial.htm");
		return true;
	}
	
	public boolean onView(){
		String id = view.getValue();
		if(id!=null){
			getContext().setSessionAttribute("searchMaterialId", id);
			this.setRedirect("viewMaterial.htm");
		}
		return true;
	}

}
