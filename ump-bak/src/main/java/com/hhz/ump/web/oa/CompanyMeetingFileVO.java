package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaMeetingFile;

/**
 * 
 * 类名		CompanyViFileVO
 * 创建者	huangbijin
 * 创建日期	2010-9-20
 * 描述		用于在前台展示的VO类
 */
public class CompanyMeetingFileVO {
    private OaMeetingFile file;
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
    public OaMeetingFile getFile() {
        return file;
    }
    public void setFile(OaMeetingFile file) {
        this.file = file;
    }
}
