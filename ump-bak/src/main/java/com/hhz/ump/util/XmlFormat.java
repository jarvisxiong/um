package com.hhz.ump.util; 
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class XmlFormat { 
    public static String format(String str) 
            throws UnsupportedEncodingException, IOException, DocumentException { 
        // System.out.println(" str :  " + str); 
        SAXReader reader = new SAXReader(); 
        // System.out.println(reader); 
        // 注释：创建一个串的字符输入流 
        StringReader in = new StringReader(str); 
        Document doc = reader.read(in); 
        // System.out.println(doc.getRootElement()); 
        // 注释：创建输出格式 
        OutputFormat formater = OutputFormat.createPrettyPrint(); 
        //formater=OutputFormat.createCompactFormat(); 
        // 注释：设置xml的输出编码
 
        formater.setEncoding("utf-8");
 
        // 注释：创建输出(目标)
 
        StringWriter out = new StringWriter();
 
        // 注释：创建输出流
 
        XMLWriter writer = new XMLWriter(out, formater);
 
        // 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
 
        writer.write(doc);
 
 
 
         writer.close(); 
//        System.out.println(out.toString());
 
        // 注释：返回我们格式化后的结果
 
        return out.toString();
 
    }
 
 
    public static void main(String[] args) throws Exception {
 
        String head="<?xml version=\"1.0\" encoding=\"GBK\"?>";
 
       // String str = "<RequestData><HeadData><UserCode>sh1_admin</UserCode><UserName>sh1_admin</UserName><UserCompanyCode>3107</UserCompanyCode><UserCompanyName>上海分公司一部</UserCompanyName><RequestType>03</RequestType></HeadData><BodyData><ReportId>113100000033</ReportId><Insurant>a5rfg87</Insurant><NumberPlate>沪E78612</NumberPlate><EngineModel></EngineModel><CarVin></CarVin><AccidentDate>2011-02-25 15:07:00</AccidentDate><ReportDate>2011-02-25 15:07:00</ReportDate><Province>310000</Province><City>310100</City><District></District><AccidentPlace>1</AccidentPlace><AccidentLongitude></AccidentLongitude><AccidentLatitude></AccidentLatitude><SurveyLongitude></SurveyLongitude><SurveyLatitude></SurveyLatitude><SceneReportFlag></SceneReportFlag><Reporter></Reporter><ReporterTel></ReporterTel><SurveyPlace></SurveyPlace><OperatorId>3525</OperatorId><OperatorName>sh_admin</OperatorName><ReportDealId>30000800</ReportDealId><ReportDealName>江苏分公司</ReportDealName><CompanyName></CompanyName><CustomerTypeCode></CustomerTypeCode><ForcePolicyId>a5rfg87a5rfg87a5rfg87</ForcePolicyId><BizPolicyId></BizPolicyId><Index>0</Index><FieldName>5</FieldName></BodyData></RequestData>";
 String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><templet><content><projectName>盐城地产公司</projectName><projectCd>259</projectCd><mateModuleName>空调末端</mateModuleName><mateModuleId>297e4b5f364d5e4001364d8eb965003a</mateModuleId><takeUnitName></takeUnitName><takeUnitId></takeUnitId><takeUnitContNo></takeUnitContNo><useCoverage></useCoverage><enterDate></enterDate><purchaseTotalAmt>583.00</purchaseTotalAmt><purchaseStrategyTotalAmt></purchaseStrategyTotalAmt><strategyOutTotalAmt>5.00</strategyOutTotalAmt><contTotalNum></contTotalNum><toThisTotalNum></toThisTotalNum><purchaseTechConfirmFileId></purchaseTechConfirmFileId><partA></partA><partB></partB><orderDesc></orderDesc><strategyMatePrices class=\"com.hhz.ump.web.res.bean.StrategyMatePrice\"><com.hhz.ump.web.res.bean.StrategyMatePrice><materialName>设备名称</materialName><specName>规格</specName><modelName>型号</modelName><totalPrice>578</totalPrice><price>基准单价(元)</price><cgPrice>采购单价(元)</cgPrice><currentPrices>铜价(元)</currentPrices><buyNum>数量</buyNum><f01>f01</f01><f02>f02</f02><f03>f03</f03><f04>f04</f04><h01>设备名称</h01><h02>规格型号</h02><h03>单价(元)</h03><h04>备注</h04><e01>0</e01><e02>0</e02><e03>0</e03><e04>0</e04></com.hhz.ump.web.res.bean.StrategyMatePrice><com.hhz.ump.web.res.bean.StrategyMatePrice><materialName>卧式暗装风机盘管</materialName><specName>-</specName><modelName>YGFC02CC2S</modelName><totalPrice>578</totalPrice><price>578</price><cgPrice>578</cgPrice><currentPrices></currentPrices><buyNum>1</buyNum><f01>卧式暗装风机盘管</f01><f02>YGFC02CC2S</f02><f03>578.0</f03><f04>两管制/常压</f04><h01>设备名称</h01><h02>规格型号</h02><h03>单价(元)</h03><h04>备注</h04><e01>0</e01><e02>0</e02><e03>0</e03><e04>0</e04></com.hhz.ump.web.res.bean.StrategyMatePrice><com.hhz.ump.web.res.bean.StrategyMatePrice><strageTypeCd>2</strageTypeCd><materialName>1</materialName><rowTypeCd>2</rowTypeCd><costMateId>strageout</costMateId><costMatePriceId>strageout19</costMatePriceId><specName>1</specName><modelName>1</modelName><totalPrice>1</totalPrice><price>1</price><cgPrice>1</cgPrice><currentPrices></currentPrices><buyNum>1</buyNum></com.hhz.ump.web.res.bean.StrategyMatePrice><com.hhz.ump.web.res.bean.StrategyMatePrice><strageTypeCd>2</strageTypeCd><materialName>2</materialName><rowTypeCd>2</rowTypeCd><costMateId></costMateId><costMatePriceId></costMatePriceId><specName>2</specName><modelName>2</modelName><totalPrice>4</totalPrice><price>2</price><cgPrice>2</cgPrice><currentPrices></currentPrices><buyNum>2</buyNum></com.hhz.ump.web.res.bean.StrategyMatePrice></strategyMatePrices><approverUsers class=\"com.hhz.ump.web.res.baseBean.ResApproverUser\"/><relaApproveBills class=\"com.hhz.ump.web.res.baseBean.ResRelaApproveBill\"/><mapCustomNode/></content></templet>";
        // System.out.println(str);
 
        format(str);
 
    }
 
 
}
