package com.intelitune.nwms.material;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.click.control.FileField;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.MaterialState;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.UnitPackageState;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.MaterialStateService;
import com.intelitune.nwms.service.MaterialStateServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
import com.intelitune.nwms.service.UnitPackageStateService;
import com.intelitune.nwms.service.UnitPackageStateServiceImpl;
import com.intelitune.nwms.service.UnitService;
import com.intelitune.nwms.service.UnitServiceImpl;

public class ImportMaterial extends BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getSystemSetting();
	
	public String title=this.getMessage("import_material");
	public Form form = new Form("form");
	public Select owner = new Select("owner",this.getMessage("owner")+"<span style='color:red'>*</span>");
	public FileField ff = new FileField("checkFile",this.getMessage("checkFile"));
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public MaterialStateService mss =  MaterialStateServiceImpl.getInstance();
	public MCSService mcs = MCSService.getInstance();
	public UnitPackageService ups =  UnitPackageServiceImpl.getInstance(); 
	public UnitPackageStateService upss =  UnitPackageStateServiceImpl.getInstance();
	public UnitService us =  UnitServiceImpl.getInstance();
	public String excelError="";
	
	public ImportMaterial(){
		form.add(owner);
		form.add(ff);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		form.add(submit);
		owner.add(Option.EMPTY_OPTION);
		InttClientDetailWS client[] = mcs.getAllClient();
		for(int i=0;i<client.length;i++){
			owner.add(new Option(client[i].getId(),client[i].getCnName()));
		}
	}
	
	public boolean onSubmit(){
		java.text.DecimalFormat   formatter   =   new   java.text.DecimalFormat("##############");
		String url="";
		if(owner.getValue().equals("")){
			owner.setError(this.getMessage("error8"));
			return false;
		}
		if(ff.getFileItem().getName().equals(this.getMessage("error10"))){
			ff.setError("");
			return false;
		}
		String path = getContext().getServletContext().getRealPath("/material/importMaterial");
		int a = ff.getFileItem().getName().length();
		int b = ff.getFileItem().getName().lastIndexOf("\\");
		String fName = ff.getFileItem().getName().substring(b+1);
		Date t = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat f = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		String timeStr = f.format(t);
		fName = timeStr+ fName;
		File file = new File(path,fName);
		FileOutputStream fos;
		InputStream is;
		try {
			is = ff.getFileItem().getInputStream();
			byte[] buffer = new byte[1024];
			fos = new FileOutputStream(file);
			int c = 0;
			while ((c = is.read(buffer)) != -1) {
				fos.write(buffer);
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		url= file.getPath();
		//读取EXCEL第一行标题到list_title
		List<MaterialMappingModel> list_title=new ArrayList<MaterialMappingModel>();
		List<ImportMaterialModel> list_model=new ArrayList<ImportMaterialModel>();
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(url));
			HSSFSheet childSheet = workbook.getSheetAt(0);
			for(int i=0;i<childSheet.getRow(0).getPhysicalNumberOfCells();i++){
				HSSFCell cell = childSheet.getRow(0).getCell((short)i);
				MaterialMappingModel mmm=new MaterialMappingModel();
				mmm.setColumn(i);
				mmm.setValue(cell+"");
				list_title.add(mmm);
			}
			//判断格式是否正确
			String[] strs={"产品编号","中文品名","英文品名","单价"};
			for(int i=0;i<strs.length;i++){
				String str=strs[i];
				for(int j=0;j<list_title.size();j++){
					MaterialMappingModel model=list_title.get(j);
					if(str.equals(model.getValue().trim())){
						break;
					}
					if(j==list_title.size()-1){
						excelError=this.getMessage("excel_error");
						return false;
					}
				}
			}
			
			for(int i=1;i<childSheet.getPhysicalNumberOfRows();i++){
				if(childSheet.getRow(i)==null||childSheet.getRow(i).getCell(1)==null||"".equals(formatCell(childSheet.getRow(i).getCell(0)))){
					break;
				}
				for(int k=0;k<list_title.size();k++){
					MaterialMappingModel mmm1=list_title.get(k);
					if("产品编号".equals(mmm1.getValue().trim())){
						String product_code1=formatCell(childSheet.getRow(i).getCell(mmm1.getColumn()));
//						if(product_code1.substring(product_code1.length()-2, product_code1.length()).equals(".0")){
//							String[] str1=product_code1.split("\\.");
//							product_code1=str1[0];
//						}
						List<Material> list_material=ms.findNormalMaterialByOwner(owner.getValue());
						int n=0;
						for(;n<list_material.size();n++){
							Material material=list_material.get(n);
							if(material.getCode().trim().equals(product_code1.trim())){
								break;
							}
						}
						if(n==list_material.size()){
							ImportMaterialModel imm=new ImportMaterialModel();
							for(int j=0;j<list_title.size();j++){
								MaterialMappingModel mmm=list_title.get(j);
								if("产品编号".equalsIgnoreCase(mmm.getValue().trim())){
									String product_code=formatCell(childSheet.getRow(i).getCell(mmm.getColumn()));
//									if(product_code.substring(product_code.length()-2, product_code.length()).equals(".0")){
//										String[] str=product_code.split("\\.");
//										imm.setProductCode(str[0]);
//									}else{
									imm.setProductCode(product_code);
//									}
									
								}
								else if("中文品名".equalsIgnoreCase(mmm.getValue().trim())){
									if(childSheet.getRow(i).getCell(mmm.getColumn())==null){
										imm.setNameCn("");
									}else{
										imm.setNameCn(formatCell(childSheet.getRow(i).getCell(mmm.getColumn())));
									}
								}
								else if("英文品名".equalsIgnoreCase(mmm.getValue().trim())){
									if(childSheet.getRow(i).getCell(mmm.getColumn())==null){
										imm.setNameEn("");
									}else{
										imm.setNameEn(formatCell(childSheet.getRow(i).getCell(mmm.getColumn())));
									}
								}
								else if("单价".equalsIgnoreCase(mmm.getValue().trim())){
									imm.setPrice(formatCell(childSheet.getRow(i).getCell(mmm.getColumn())));
								}
								else if("备注".equalsIgnoreCase(mmm.getValue().trim())){
									imm.setRemark(formatCell(childSheet.getRow(i).getCell(mmm.getColumn())));
								}
								
							}
							list_model.add(imm);
						}
						}
							
						}
					}	
				
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.saveImportMaterial(list_model);
		this.setRedirect(SearchMaterial.class);
		return true;
	}
	
	public void saveImportMaterial(List<ImportMaterialModel> list){
		MaterialState materialState = mss.getMaterialState(MaterialState.ZHENGCHANG);
		for(ImportMaterialModel imm:list){
			Material material = new Material();
			material.setAlias(imm.getNameCn());
			material.setCode(imm.getProductCode());
			material.setCreationTime(new Timestamp(System.currentTimeMillis()));
			material.setDescription(imm.getNameEn());
			material.setInttClientDetailWSId(owner.getValue());
			material.setRemark(imm.getRemark());
			material.setState(materialState);
			if(imm.getPrice()!=null){
				if(!imm.getPrice().equals("")&&!"NULL".equals(imm.getPrice().toUpperCase())){
					material.setValue(Float.parseFloat(imm.getPrice()));
				}
			}
			
			ms.saveMaterial(material);
			UnitPackage unitPackage = new UnitPackage();
			unitPackage.setAlias(imm.getNameCn()+"_默认SKU");
			unitPackage.setBreadth(1f);
			unitPackage.setHeight(1f);
			unitPackage.setLength(1f);
			
//			unitPackage.setRemark(remark.getValue());
			unitPackage.setState(upss.getUnitPackageState(UnitPackageState.ZHENGCHANG));
			unitPackage.setUnit(us.findUnitByCode("0").get(0));
			unitPackage.setVolume(1f);
			unitPackage.setWeight(1f);	
			unitPackage.setMaterial(material);		
			ups.saveUnitPackage(unitPackage);
			if(material.getUnitPackages() != null){
				unitPackage.setIndexId(material.getUnitPackages().size()+1);
			}else{
				unitPackage.setIndexId(1);
			}
			ups.saveUnitPackage(unitPackage);
		}
	}
	
	public String formatCell(HSSFCell cell){
		String str="";
		
		if(cell==null) return str;
		
		if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
			   BigDecimal value =  new BigDecimal(cell.getNumericCellValue());
			  str=value.toString();
		}else{
			str=cell.getStringCellValue();
		}
		return str;
	}
	
	
}
