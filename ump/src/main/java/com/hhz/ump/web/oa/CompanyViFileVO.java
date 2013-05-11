package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaFilingFile;

/**
 * 
 * 类名		CompanyViFileVO
 * 创建者	lijin
 * 创建日期	2010-5-13
 * 描述		用于在前台展示的VO类
 */
public class CompanyViFileVO {
    private OaFilingFile file;
    private List<AppAttachFile> attachList = new ArrayList<AppAttachFile>();
    
    public void addAttach(AppAttachFile attach) {
	attachList.add(attach);
    }
    public List<AppAttachFile> getAttachList() {
        return attachList;
    }
    public void setAttachList(List<AppAttachFile> attachList) {
        this.attachList = attachList;
    }
    public OaFilingFile getFile() {
        return file;
    }
    public void setFile(OaFilingFile file) {
        this.file = file;
    }
}
