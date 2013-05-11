package com.hhz.ump.dao.mes;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.ump.util.HelperUtil;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-29
 * Time: 上午11:09
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static String getHtmlTemplate(String filepath) {
        try {
            return IOUtils.toString(HelperUtil.class.getClassLoader().getResourceAsStream("htmlTemplates/" + filepath), ChangeCharset.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "会议纪要 Email 群发模板 读取失败";
        }
    }

    public static void main(String[] args) throws Exception {
        String htmlTemplate = getHtmlTemplate("mes/test.html");
        htmlTemplate = htmlTemplate.replace("&nbsp;", "").trim();
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph xwpfParagraph1 = doc.createParagraph();
        xwpfParagraph1.createRun().setText("1");

        XWPFParagraph xwpfParagraph2 = doc.createParagraph();
        xwpfParagraph2.createRun().setText("2");
        xwpfParagraph2.createRun().addCarriageReturn();

        XWPFParagraph xwpfParagraph3 = doc.createParagraph();
        xwpfParagraph3.createRun().setText("3");
        xwpfParagraph3.createRun().addCarriageReturn();

        XWPFParagraph xwpfParagraph4 = new XWPFParagraph(xwpfParagraph2.getCTP(), xwpfParagraph2.getDocument());
        xwpfParagraph4.createRun().setText("4");
        xwpfParagraph4.createRun().addCarriageReturn();
        //xwpfParagraph4.getCTP().getDomNode().appendChild(new XWPFTable(doc.getDocument().getBody().addNewTbl(),doc,3,2).getCTTbl().getDomNode());

        XWPFParagraph xwpfParagraph5 = new XWPFParagraph(xwpfParagraph2.getCTP(), xwpfParagraph2.getDocument());
        xwpfParagraph5.createRun().setText("5");
        xwpfParagraph5.createRun().addCarriageReturn();



        xwpfParagraph5.getCTP().getDomNode().appendChild(new XWPFTable(doc.getDocument().getBody().addNewTbl(),doc,3,2).getCTTbl().getDomNode());

        //HtmlToWordUtil.proccessDocument(DocumentHelper.parseText("<div>" + htmlTemplate + "</div>").getRootElement(), doc);
        doc.write(new FileOutputStream("d://test2.docx"));
    }
}
