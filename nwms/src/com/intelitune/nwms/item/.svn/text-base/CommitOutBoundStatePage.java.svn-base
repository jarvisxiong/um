package com.intelitune.nwms.item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;

import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;

public class CommitOutBoundStatePage extends BorderPage {
	public Form form = new Form();
	private StorageItemService si = StorageItemServiceImp.getInstance();
	
	
	
	public void makeform(){
		form.add(new Submit("submit","调整提交",this,"onSubmit"));
	}
	
	public CommitOutBoundStatePage(){
		makeform();
	}
	
	public boolean onSubmit(){
		List<StorageItem> li = si.findByHql("from StorageItem i where i.state.code='0'");
		int c=0;
		StorageItem si =null;
		
		File f = new File("d:\\ids.txt");
		
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
		
			for(int i=0;i<li.size();i++){
				si = li.get(i);
				if(si.getNextItems().size()!=0) 
					{
						c++;						
						output.write(si.id+"\n");						
					}
			}
			System.out.println(c);
			output.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
}
