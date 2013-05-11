package com.hhz.ump.entity.oa;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 类名		OaFileFollowedVO
 * 创建者	李劲
 * 创建日期	2010-8-4
 * 描述		用于展示文件跟踪信息的VO类
 */
public class OaFileFollowedVO {

    private String typeCd;
    private String typeName;
    private List<OaFileFollowed> fileList = new ArrayList<OaFileFollowed>();
    
    /**
     * 新增一条文件记录至结果集
     * @param file
     */
    public void addNewFile(OaFileFollowed file) {
	fileList.add(file);
    }
    
    public int getTotalNum() {
	return fileList.size();
    }
    
    public String getTypeCd() {
        return typeCd;
    }
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public List<OaFileFollowed> getFileList() {
        return fileList;
    }
    public void setFileList(List<OaFileFollowed> fileList) {
        this.fileList = fileList;
    }
}