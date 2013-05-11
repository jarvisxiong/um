package com.hhz.ump.util;

import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

/**
 * Created by IntelliJ IDEA.
 * User: Zhanggaojiang
 * Date: 12-3-30
 * Time: 下午3:45
 * Html 转换 为 Word
 */
public class HtmlToWordUtil {
    /**
     * 处理文档
     *
     * @param rootElement   HTML 根元素
     * @param xwpfParagraph word文档引用
     */

    public static void proccessDocument(Element rootElement, XWPFParagraph xwpfParagraph) {
        XWPFDocument word = xwpfParagraph.getDocument();
        Integer index = xwpfParagraph.getDocument().getPosOfParagraph(xwpfParagraph);

        List<Element> elements = rootElement.selectNodes("*");
        if (!"".equalsIgnoreCase(rootElement.getText().trim())) {
            xwpfParagraph = new XWPFParagraph(word.getDocument().getBody().insertNewP(index++), word);
            xwpfParagraph.createRun().setText(rootElement.getText());
        }
        //遍历全部HTML元素 根据标签类型 转换为 WORD 可识别 对象。
        for (org.dom4j.Element element : elements) {
            if (element.getName().matches("p|span|strong") && HelperUtil.clearHtml(element.getStringValue()).trim() != "") {//简单段落转换
                xwpfParagraph = new XWPFParagraph(word.getDocument().getBody().insertNewP(index++), word);
                XWPFRun xwpfRun = xwpfParagraph.createRun();
                for(String pstr:element.getText().split("\n")){
                	xwpfRun.setText(pstr);
                	xwpfRun.addCarriageReturn();
                }
            } else if (element.getName().matches("table")) {//遇到Table
                int trCount = getTrCount(element);
                XWPFTable xwpfTable = resetDrawTable(word, element);
                //设置Table 内边距
                CTTblCellMar ctTblCellMar = xwpfTable.getCTTbl().getTblPr().addNewTblCellMar();
                CTTblWidth ctTblWidth = ctTblCellMar.addNewBottom();
                ctTblWidth.setW(BigInteger.valueOf(100));
                ctTblWidth.setType(STTblWidth.DXA);
                ctTblCellMar.setLeft(ctTblWidth);
                ctTblCellMar.setRight(ctTblWidth);
                ctTblCellMar.setRight(ctTblWidth);
                ctTblCellMar.setTop(ctTblWidth);
                //为Table 填充内容
                for (int r = 0; r < trCount; r++) {
                    XWPFTableRow xwpfTableRow = xwpfTable.getRow(r);
                    List<Element> tds = element.selectNodes(element.getUniquePath() + "//tr[" + (r + 1) + "]/td");
                    Integer tds_index = 0;
                    List<XWPFTableCell> xwpfTableCells = xwpfTableRow.getTableCells();
                    for (XWPFTableCell xwpfTableCell : xwpfTableCells) {
                        CTTcPr ctTcPr = null == xwpfTableCell.getCTTc().getTcPr() ? xwpfTableCell.getCTTc().addNewTcPr() : xwpfTableCell.getCTTc().getTcPr();
                        if (!(null != ctTcPr.getVMerge() && ctTcPr.getVMerge().getVal() == null)) {
                            if (tds_index < tds.size()) {
                                xwpfTableCell.setText(tds.get(tds_index++).getStringValue().trim());
                                if (null != ctTcPr.getGridSpan()) {
                                    xwpfParagraph = xwpfTableCell.getParagraphs().get(0);
                                    xwpfParagraph.getCTP().addNewPPr().addNewJc().setVal(STJc.CENTER);
                                }
                            }
                        }
                    }
                }

                XWPFParagraph xwpfParagraph2 = new XWPFParagraph(word.getDocument().getBody().insertNewP(index++), word);
                XWPFRun xwpfRun = xwpfParagraph2.createRun();
                xwpfParagraph2.getCTP().getDomNode().appendChild(xwpfTable.getCTTbl().getDomNode());
            } else if (!element.selectNodes("table").isEmpty()) {
            }
        }
    }

    /**
     * 功能同上方法 重载
     *
     * @param html
     * @param xwpfParagraph
     */
    public static void proccessDocument(String html, XWPFParagraph xwpfParagraph) throws Exception {
        String body = html.replace("&nbsp;", "").replace("nbsp;", "");
        body=body.replace("<o:p></o:p>","").replace("<br />","\n").replace("<br/>","\n");
        proccessDocument(DocumentHelper.parseText("<div>" + body + "</div>").getRootElement(), xwpfParagraph);
    }

    /**
     * 获取 HTML table tr 行数
     *
     * @param element
     * @return
     */
    public static Integer getTrCount(Element element) {
        String xpath = element.getUniquePath();
        return Float.valueOf(element.valueOf("count(" + xpath + "//tr)")).intValue();
    }

    /**
     * 获取 HTML table td 列数
     *
     * @param element
     * @return
     */
    public static Integer getTdCount(Element element) {
        List<Element> tds = element.selectNodes(element.getUniquePath() + "//tr[1]/td");
        int tdCount = 0;
        for (Element child : tds) {
            Attribute attribute = child.attribute("colspan");
            if (null != attribute) {
                tdCount += Integer.valueOf(attribute.getValue());
            } else {
                tdCount += 1;
            }
        }
        return tdCount;
    }


    /**
     * 重新绘制 Table布局
     * 重构，垂直合并|纵向合并
     *
     * @return
     */
    public static XWPFTable resetDrawTable(XWPFDocument word, Element element) {
        int trCount = getTrCount(element);
        int tdCount = getTdCount(element);
        XWPFTable xwpfTable = word.createTable(trCount, tdCount);
        for (int r = 0; r < trCount; r++) {
            XWPFTableRow xwpfTableRow = xwpfTable.getRow(r);
            List<Element> tds = element.selectNodes(element.getUniquePath() + "//tr[" + (r + 1) + "]/td");
            for (int d = 0; d < tds.size(); d++) {
                XWPFTableCell xwpfTableCell = xwpfTableRow.getCell(d);
                Attribute rowspan = tds.get(d).attribute("rowspan");
                Attribute colspan = tds.get(d).attribute("colspan");
                //垂直合并
                if (null != rowspan) {
                    for (int i = r; i < Integer.valueOf(rowspan.getValue()) + 1; i++) {
                        xwpfTableRow = xwpfTable.getRow(i);
                        xwpfTableCell = xwpfTableRow.getCell(d);
                        CTTcPr ctTcPr = null == xwpfTableCell.getCTTc().getTcPr() ? xwpfTableCell.getCTTc().addNewTcPr() : xwpfTableCell.getCTTc().getTcPr();
                        CTVMerge ctvMerge = ctTcPr.addNewVMerge();
                        if (i == r) {
                            ctvMerge.setVal(STMerge.RESTART);
                        }
                    }
                }
                //纵向合并
                if (null != colspan) {
                    List<XWPFTableCell> xwpfTableCells = xwpfTableRow.getTableCells();
                    for (int t = 0; t < xwpfTableCells.size() - 1; t++) {
                        int dropIndex = -1;
                        xwpfTableCell = xwpfTableCells.get(t);
                        CTTcPr ctTcPr = null == xwpfTableCell.getCTTc().getTcPr() ? xwpfTableCell.getCTTc().addNewTcPr() : xwpfTableCell.getCTTc().getTcPr();
                        if ((null == ctTcPr.getVMerge() || null != ctTcPr.getVMerge().getVal()) && null == ctTcPr.getGridSpan()) {
                            if (dropIndex == -1) {
                                dropIndex = t;
                            }
                            for (int i = 0; i < Integer.valueOf(colspan.getValue()) - 1; i++) {
                                xwpfTableRow.getCtRow().removeTc(dropIndex + i);
                                xwpfTableRow.getTableCells().remove(dropIndex + i);
                            }
                            xwpfTableCell = xwpfTableCells.get(dropIndex);
                            ctTcPr = null == xwpfTableCell.getCTTc().getTcPr() ? xwpfTableCell.getCTTc().addNewTcPr() : xwpfTableCell.getCTTc().getTcPr();
                            ctTcPr.addNewGridSpan().setVal(BigInteger.valueOf(Integer.valueOf(colspan.getValue())));
                        }
                    }
                }
            }
        }

        return xwpfTable;
    }
}
