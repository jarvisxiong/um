<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="template-header.jsp"%>

<script type="text/javascript">

	var curTypeCd = '${authTypeCd}';
	var commonUrl = '${ctx}/app/download.action?bizModuleCd=resNoPublish';
	var infos = {
			'BLPM_CWGL_10':{fileName:'BLPM_CWGL_10',realFileName:'年度预算审批表'},
			'BLPM_CWGL_20':{fileName:'BLPM_CWGL_20',realFileName:'各部门年度预算审批表'},
			'BLPM_CWGL_30':{fileName:'BLPM_CWGL_30',realFileName:'各部门月度预算审批表'},
			'BLPM_CWGL_50':{fileName:'BLPM_CWGL_50',realFileName:'预借款申请单(个人)'},
			'BLPM_CWGL_60':{fileName:'BLPM_CWGL_60',realFileName:'特别费用申请表'},
			'BLPM_GLTX_10':{fileName:'BLPM_GLTX_10',realFileName:'组织管理文件审批表'},
			'BLPM_GLTX_20':{fileName:'BLPM_GLTX_20',realFileName:'权责手册审批表'},
			'BLPM_GLTX_30':{fileName:'BLPM_GLTX_30',realFileName:'制度文件审批表'},
			'BLPM_GLTX_40':{fileName:'BLPM_GLTX_40',realFileName:'部门内部制度文件审批表'},
			'BLPM_QT_30':{fileName:'BLPM_QT_30',realFileName:'工作指令单'},
			'BLPM_QT_40':{fileName:'BLPM_QT_40',realFileName:'工作反馈单'},
			'BLPM_XZRS_110':{fileName:'BLPM_XZRS_110',realFileName:'员工自带笔记本检验单'},
			'BLPM_XZRS_130':{fileName:'BLPM_XZRS_130',realFileName:'车辆美容项目申请单'},
			'BLPM_XZRS_140':{fileName:'BLPM_XZRS_140',realFileName:'车辆维修申请单'},
			'BLPM_XZRS_150':{fileName:'BLPM_XZRS_150',realFileName:'长途派车单'},
			'BLPM_XZRS_220':{fileName:'BLPM_XZRS_220',realFileName:'A级接待审批表'},
			'BLPM_XZRS_230':{fileName:'BLPM_XZRS_230',realFileName:'B级接待审批表'},
			'BLPM_XZRS_240':{fileName:'BLPM_XZRS_240',realFileName:'C级接待审批表'},
			'BLPM_XZRS_250':{fileName:'BLPM_XZRS_250',realFileName:'人力资源事项印鉴会签单'},
			'BLPM_XZRS_260':{fileName:'BLPM_XZRS_260',realFileName:'行政事项印鉴会签单'},
			'BLPM_XZRS_270':{fileName:'BLPM_XZRS_270',realFileName:'财务事项印鉴会签单'},
			'BLPM_XZRS_280':{fileName:'BLPM_XZRS_280',realFileName:'通用事项印鉴会签单'},
			'BLPM_XZRS_80':{fileName:'BLPM_XZRS_80',realFileName:'新员工入职费用申请单'},
			'BLSY_10_20':{fileName:'BLSY_10_20',realFileName:'工作指令单'},
			'BLSY_10_30':{fileName:'BLSY_10_30',realFileName:'工作反馈单'},
			'BLSY_CWGL_FKGL_10':{fileName:'BLSY_CWGL_FKGL_10',realFileName:'报销及付款审批单'},
			'BLSY_CWGL_FKGL_20':{fileName:'BLSY_CWGL_FKGL_20',realFileName:'银行付款审批单'},
			'BLSY_GLTX_GWPZ_10':{fileName:'BLSY_GLTX_GWPZ_10',realFileName:'商业公司岗位配置及编制方案审批表'},
			'BLSY_GLTX_QZTX_10':{fileName:'BLSY_GLTX_QZTX_10',realFileName:'权责手册审批表'},
			'BLSY_GLTX_ZWSM_10':{fileName:'BLSY_GLTX_ZWSM_10',realFileName:'宝龙商业职位说明书审批表'},
			'BLSY_GLTX_ZZJG_10':{fileName:'BLSY_GLTX_ZZJG_10',realFileName:'宝龙商业总部组织管理文件审批表'},
			'BLSY_GLTX_ZZJG_20':{fileName:'BLSY_GLTX_ZZJG_20',realFileName:'商业公司组织管理文件审批表'},
			'BLSY_JHGL_KFJH_10':{fileName:'BLSY_JHGL_KFJH_10',realFileName:'开业前商业四级计划'},
			'BLSY_JHGL_KFJH_20':{fileName:'BLSY_JHGL_KFJH_20',realFileName:'开业前招商五级计划'},
			'BLSY_JHGL_KFJH_30':{fileName:'BLSY_JHGL_KFJH_30',realFileName:'开业前企划五级计划'},
			'BLSY_JHGL_YYJH_10':{fileName:'BLSY_JHGL_YYJH_10',realFileName:'宝龙商业年度工作计划审批表'},
			'BLSY_JHGL_YYJH_20':{fileName:'BLSY_JHGL_YYJH_20',realFileName:'宝龙商业月度工作计划审批表'},
			'BLSY_JHGL_YYJH_30':{fileName:'BLSY_JHGL_YYJH_30',realFileName:'宝龙商业各中心工作计划审批表'},
			'BLSY_JXGL_10':{fileName:'BLSY_JXGL_10',realFileName:'宝龙商业总部年度考核审批表'},
			'BLSY_JXGL_20':{fileName:'BLSY_JXGL_20',realFileName:'宝龙商业总部各中心考核审批表'},
			'BLSY_JXGL_30':{fileName:'BLSY_JXGL_30',realFileName:'商业公司考核审批表'},
			'BLSY_WYGC_WYGL_20':{fileName:'BLSY_WYGC_WYGL_20',realFileName:'自持型物业管理费标准审批表'},
			'BLSY_XZRL_115':{fileName:'BLSY_XZRL_115',realFileName:'员工自带笔记本检验单(适用于宝龙商业)'},
			'BLSY_XZRL_118':{fileName:'BLSY_XZRL_118',realFileName:'重要来宾接待计划'},
			'BLSY_XZRL_120':{fileName:'BLSY_XZRL_120',realFileName:'A级接待审批表(适用于宝龙商业)'},
			'BLSY_XZRL_125':{fileName:'BLSY_XZRL_125',realFileName:'B级接待审批表(适用于宝龙商业)'},
			'BLSY_XZRL_130':{fileName:'BLSY_XZRL_130',realFileName:'C级接待审批表(适用于宝龙商业)'},
			'BLSY_XZRL_135':{fileName:'BLSY_XZRL_135',realFileName:'出差工作报告(适用于宝龙商业)'},
			'BLSY_XZRL_300':{fileName:'BLSY_XZRL_300',realFileName:'法律事务审批表(适用于商业总部)'},
			'BLSY_XZRL_80':{fileName:'BLSY_XZRL_80',realFileName:'办公资产调拨单(适用于跨业务板块独立核算的公司之间的资产调拨)'},
			'BLSY_XZRL_85':{fileName:'BLSY_XZRL_85',realFileName:'办公资产调拨单(适用于商业板块各独立核算的公司之间的资产调拨)'},
			'BLSY_XZRL_QSLX_10':{fileName:'BLSY_XZRL_QSLX_10',realFileName:'需总部决策法律事务'},
			'CBCGGL_5_15':{fileName:'CBCGGL_5_15',realFileName:'项目月度采购计划审批表'},
			'CBCGGL_5_18':{fileName:'CBCGGL_5_18',realFileName:'甲定乙供品牌确定审批表'},
			'CBCGGL_DTCB_20':{fileName:'CBCGGL_DTCB_20',realFileName:'动态成本月报审批表'},
			'CBCGGL_DTCB_30':{fileName:'CBCGGL_DTCB_30',realFileName:'成本管理措施审批表'},
			'CBCGGL_DTCB_40':{fileName:'CBCGGL_DTCB_40',realFileName:'重大政策调控、经济争议的解决审批表'},
			'CBCGGL_GCJS_30':{fileName:'CBCGGL_GCJS_30',realFileName:'竣工结算申请单'},
			'CBCGGL_GCJS_40':{fileName:'CBCGGL_GCJS_40',realFileName:'结算审价资料清单复核表'},
			'CBCGGL_GCJS_50':{fileName:'CBCGGL_GCJS_50',realFileName:'结算审核意见书'},
			'CBCGGL_GCJS_60':{fileName:'CBCGGL_GCJS_60',realFileName:'结算协议书'},
			'CBCGGL_QQCB_30':{fileName:'CBCGGL_QQCB_30',realFileName:'成本标准设定审批表'},
			'CBCGGL_QQCB_50':{fileName:'CBCGGL_QQCB_50',realFileName:'责任成本设定审批表'},
			'CPDW_CBCP_20':{fileName:'CPDW_CBCP_20',realFileName:'初步产品定位报告审批表'},
			'CPDW_CBCP_60':{fileName:'CPDW_CBCP_60',realFileName:'深化产品定位报告审批表'},
			'CWGL_GQTZ_10':{fileName:'CWGL_GQTZ_10',realFileName:'股权投资及合作意向审批表（意向确定）'},
			'CWGL_GQTZ_20':{fileName:'CWGL_GQTZ_20',realFileName:'股权投资及合作意向合同审批表（合同审批）'},
			'CWGL_YSGL_10':{fileName:'CWGL_YSGL_10',realFileName:'宝龙地产预算'},
			'CWGL_YSGL_20':{fileName:'CWGL_YSGL_20',realFileName:'总部各中心年度预算'},
			'CWGL_YSGL_30':{fileName:'CWGL_YSGL_30',realFileName:'总部各中心月度预算'},
			'CWGL_YSGL_40':{fileName:'CWGL_YSGL_40',realFileName:'地产公司年度预算'},
			'CWGL_YSGL_45':{fileName:'CWGL_YSGL_45',realFileName:'地产公司季度/月度预算'},
			'FKGL_230':{fileName:'FKGL_230',realFileName:'预借款(个人)'},
			'FKGL_TBFY':{fileName:'FKGL_TBFY',realFileName:'特别费用申请表'},
			'FSS_CW_FK_110':{fileName:'FSS_CW_FK_110',realFileName:'特别费用申请表'},
			'FSS_CW_YS_10':{fileName:'FSS_CW_YS_10',realFileName:'下属公司年度预算审批表'},
			'FSS_CW_YS_20':{fileName:'FSS_CW_YS_20',realFileName:'下属公司月度预算审批表'},
			'FSS_QTSW_30':{fileName:'FSS_QTSW_30',realFileName:'工作指令单'},
			'FSS_QTSW_40':{fileName:'FSS_QTSW_40',realFileName:'工作反馈单'},
			'GLTX_GWPZ_10':{fileName:'GLTX_GWPZ_10',realFileName:'地产公司岗位配置及编制方案审批表'},
			'GLZD_QZTX_10':{fileName:'GLZD_QZTX_10',realFileName:'权责手册审批表'},
			'GLZD_XXH_10':{fileName:'GLZD_XXH_10',realFileName:'宝龙地产整体信息化方案审批表'},
			'GLZD_XXH_20':{fileName:'GLZD_XXH_20',realFileName:'各中心信息化应用系统方案审批表'},
			'GLZD_ZYBZ_10':{fileName:'GLZD_ZYBZ_10',realFileName:'企业标准审批表'},
			'GLZD_ZZJG_10':{fileName:'GLZD_ZZJG_10',realFileName:'总部组织管理文件审批表'},
			'GLZD_ZZJG_20':{fileName:'GLZD_ZZJG_20',realFileName:'地产公司组织管理文件审批表'},
			'HT_QT_01':{fileName:'HT_QT_01',realFileName:'竞争性谈判(直接委托)合同评审审批表'},
			'HY_CWGL_10':{fileName:'HY_CWGL_10',realFileName:'宝龙集团年度预算审批表'},
			'HY_CWGL_100':{fileName:'HY_CWGL_100',realFileName:'预借款申请单(个人)'},
			'HY_CWGL_20':{fileName:'HY_CWGL_20',realFileName:'总部各部门年度预算审批表'},
			'HY_CWGL_30':{fileName:'HY_CWGL_30',realFileName:'下属公司年度、季度预算审批表'},
			'HY_CWGL_40':{fileName:'HY_CWGL_40',realFileName:'总部各部门月度预算审批表'},
			'HY_CWGL_50':{fileName:'HY_CWGL_50',realFileName:'下属公司月度预算审批表'},
			'HY_CWGL_80':{fileName:'HY_CWGL_80',realFileName:'特别费用申请表'},
			'HY_GLTX_10':{fileName:'HY_GLTX_10',realFileName:'总部组织管理文件审批表'},
			'HY_GLTX_100':{fileName:'HY_GLTX_100',realFileName:'PD公告发文审批单'},
			'HY_GLTX_135':{fileName:'HY_GLTX_135',realFileName:'EAS用户权限变动申请表(适用于人力资源管理)'},
			'HY_GLTX_20':{fileName:'HY_GLTX_20',realFileName:'下属公司组织管理文件审批表'},
			'HY_GLTX_30':{fileName:'HY_GLTX_30',realFileName:'权责手册审批表'},
			'HY_GLTX_40':{fileName:'HY_GLTX_40',realFileName:'制度文件审批表'},
			'HY_GLTX_60':{fileName:'HY_GLTX_60',realFileName:'工作指令单'},
			'HY_GLTX_70':{fileName:'HY_GLTX_70',realFileName:'工作反馈单'},
			'HY_JHGL_10':{fileName:'HY_JHGL_10',realFileName:'宝龙集团工作计划审批表'},
			'HY_JHGL_20':{fileName:'HY_JHGL_20',realFileName:'总部各部门工作计划审批表'},
			'HY_JHGL_30':{fileName:'HY_JHGL_30',realFileName:'下属公司年度工作计划审批表'},
			'HY_JHGL_40':{fileName:'HY_JHGL_40',realFileName:'下属公司目标责任书审批表'},
			'HY_XZRL_120':{fileName:'HY_XZRL_120',realFileName:'员工自带笔记本检验单'},
			'HY_XZRL_250':{fileName:'HY_XZRL_250',realFileName:'新员工入职费用申请单'},
			'HY_XZRL_275':{fileName:'HY_XZRL_275',realFileName:'办公资产调拨单(适用于集团各独立核算的公司之间的资产调拨)'},
			'HY_XZRL_277':{fileName:'HY_XZRL_277',realFileName:'办公资产调拨单(适用于跨板块独立核算的公司之间的资产调拨)'},
			'HY_XZRL_335':{fileName:'HY_XZRL_335',realFileName:'重要来宾接待计划'},
			'HY_XZRL_340':{fileName:'HY_XZRL_340',realFileName:'A级接待审批表'},
			'HY_XZRL_350':{fileName:'HY_XZRL_350',realFileName:'B级接待审批表'},
			'HY_XZRL_360':{fileName:'HY_XZRL_360',realFileName:'C级接待审批表'},
			'HY_XZRL_450':{fileName:'HY_XZRL_450',realFileName:'人事调动申请表'},
			'HY_XZRL_500':{fileName:'HY_XZRL_500',realFileName:'法律事务审批表(集团总部)'},
			'HY_XZRL_505':{fileName:'HY_XZRL_505',realFileName:'需总部决策法律事务审批表(下属公司)'},
			'HY_XZRL_90':{fileName:'HY_XZRL_90',realFileName:'出差工作报告'},
			'ITGL_100':{fileName:'ITGL_100',realFileName:'PD公告发文审批单'},
			'ITGL_80':{fileName:'ITGL_80',realFileName:'K3用户权限变动申请表'},
			'ITGL_90':{fileName:'ITGL_90',realFileName:'视频会议申请单'},
			'JD_CWGL_10':{fileName:'JD_CWGL_10',realFileName:'企业发展中心预算审批表'},
			'JD_CWGL_30':{fileName:'JD_CWGL_30',realFileName:'酒店开业前预算审批表'},
			'JD_CWGL_40':{fileName:'JD_CWGL_40',realFileName:'酒店开业后预算审批表(年度经营预算)'},
			'JD_CWGL_50':{fileName:'JD_CWGL_50',realFileName:'酒店月度资金计划审批表'},
			'JD_JHJX_20':{fileName:'JD_JHJX_20',realFileName:'企业发展中心工作计划审批表'},
			'JD_JHJX_30':{fileName:'JD_JHJX_30',realFileName:'年度目标责任书和考核审批表'},
			'JD_SJGL_20':{fileName:'JD_SJGL_20',realFileName:'酒店设计成果审批表'},
			'JD_XZRS_10':{fileName:'JD_XZRS_10',realFileName:'企业发展中心组织管理文件审批表'},
			'JD_XZRS_250':{fileName:'JD_XZRS_250',realFileName:'激励政策审批表'},
			'JD_XZRS_40':{fileName:'JD_XZRS_40',realFileName:'人事调动通知单'},
			'JHGL_DCGH_20':{fileName:'JHGL_DCGH_20',realFileName:'地产五年战略发展规划'},
			'JHGL_DCGH_30':{fileName:'JHGL_DCGH_30',realFileName:'酒店五年战略发展规划'},
			'JHGL_DCGH_40':{fileName:'JHGL_DCGH_40',realFileName:'商业五年战略发展规划'},
			'JHGL_JXGL_10':{fileName:'JHGL_JXGL_10',realFileName:'宝龙集团目标责任书审批表'},
			'JHGL_JXGL_20':{fileName:'JHGL_JXGL_20',realFileName:'总部各中心目标责任书审批表'},
			'JHGL_JXGL_40':{fileName:'JHGL_JXGL_40',realFileName:'地产公司目标责任书审批表'},
			'JHGL_JYJH_10':{fileName:'JHGL_JYJH_10',realFileName:'控股公司经营计划审批表'},
			'JHGL_JYJH_30':{fileName:'JHGL_JYJH_30',realFileName:'中心工作计划审批表'},
			'JHGL_WNFZ_10':{fileName:'JHGL_WNFZ_10',realFileName:'集团五年战略发展规划'},
			'JHGL_XMJH_10':{fileName:'JHGL_XMJH_10',realFileName:'项目一级计划会签表'},
			'JHGL_XMJH_20':{fileName:'JHGL_XMJH_20',realFileName:'项目二级计划会签表'},
			'JHGL_XMJH_30':{fileName:'JHGL_XMJH_30',realFileName:'项目三级计划会签表'},
			'JHGL_YYJH_50':{fileName:'JHGL_YYJH_50',realFileName:'地产公司年度工作计划审批表'},
			'JSGL_GCBG_70':{fileName:'JSGL_GCBG_70',realFileName:'现场签证单'},
			'JSGL_GCCH_10':{fileName:'JSGL_GCCH_10',realFileName:'开发建设大纲审批表'},
			'JSGL_GCCH_20':{fileName:'JSGL_GCCH_20',realFileName:'其他单位施工方案审批表'},
			'JSGL_SYGCGZ_10':{fileName:'JSGL_SYGCGZ_10',realFileName:'移交后由商业公司承担费用（涉及外观、效果）'},
			'JSGL_SYGCGZ_20':{fileName:'JSGL_SYGCGZ_20',realFileName:'移交后由商业公司承担（使用功能或其他）'},
			'JSGL_SYGCGZ_30':{fileName:'JSGL_SYGCGZ_30',realFileName:'移交后由地产公司承担费用（涉及外观、效果）'},
			'JSGL_SYGCGZ_40':{fileName:'JSGL_SYGCGZ_40',realFileName:'移交后由地产公司承担费用（使用功能或其他）'},
			'JSGL_SYGCGZ_50':{fileName:'JSGL_SYGCGZ_50',realFileName:'移交后由商户自行承担费用'},
			'QTSW-BDMBSP':{fileName:'QTSW-BDMBSP',realFileName:'表单模板审批表'},
			'QTSW_GZZL':{fileName:'QTSW_GZZL',realFileName:'工作指令单'},
			'QTSW_GZZL_7':{fileName:'QTSW_GZZL_7',realFileName:'工作反馈单'},
			'QTSW_GZZL_70':{fileName:'QTSW_GZZL_70',realFileName:'收文呈批表'},
			'QTSW_HYSQ_10':{fileName:'QTSW_HYSQ_10',realFileName:'决策会初评纪要'},
			'QTSW_HYSQ_30':{fileName:'QTSW_HYSQ_30',realFileName:'决策会违纪罚款单'},
			'QTSW_HYSQ_40':{fileName:'QTSW_HYSQ_40',realFileName:'重要会议申请单'},
			'QTSW_HYSQ_50':{fileName:'QTSW_HYSQ_50',realFileName:'总裁、执行总裁会议申请表'},
			'SJGL_GNGH_10':{fileName:'SJGL_GNGH_10',realFileName:'概念设计、建筑方案设计任务书审批表'},
			'SJGL_GNGH_20':{fileName:'SJGL_GNGH_20',realFileName:'概念设计、建筑方案设计成果评审表'},
			'SJGL_JDBD_10':{fileName:'SJGL_JDBD_10',realFileName:'酒店标段室内设计任务书审批表'},
			'SJGL_JDBD_20':{fileName:'SJGL_JDBD_20',realFileName:'酒店标段室内概念设计成果审批表'},
			'SJGL_JDBD_30':{fileName:'SJGL_JDBD_30',realFileName:'酒店标段室内方案设计成果审批表'},
			'SJGL_JDBD_40':{fileName:'SJGL_JDBD_40',realFileName:'酒店标段室内施工图设计成果审批表'},
			'SJGL_JZFA_30':{fileName:'SJGL_JZFA_30',realFileName:'设计任务书审批表'},
			'SJGL_JZFA_40':{fileName:'SJGL_JZFA_40',realFileName:'设计成果评审表'},
			'SJGL_SNJGFGSJ_10':{fileName:'SJGL_SNJGFGSJ_10',realFileName:'室内、景观、泛光系统设计任务书审批表'},
			'SJGL_SNJGFGSJ_30':{fileName:'SJGL_SNJGFGSJ_30',realFileName:'室内、景观、泛光系统方案设计成果审批表'},
			'SJGL_SNJGFGSJ_40':{fileName:'SJGL_SNJGFGSJ_40',realFileName:'室内、景观、泛光系统施工图设计成果审批表'},
			'SJGL_ZXSJ_10':{fileName:'SJGL_ZXSJ_10',realFileName:'酒店装修和专项设计成果审批表'},
			'SJGL_ZXSJ_30':{fileName:'SJGL_ZXSJ_30',realFileName:'酒店装修和专项设计任务书审批表'},
			'SYGS_10_20':{fileName:'SYGS_10_20',realFileName:'工作指令单'},
			'SYGS_10_30':{fileName:'SYGS_10_30',realFileName:'工作反馈单'},
			'SYGS_CWGL_FKGL_10':{fileName:'SYGS_CWGL_FKGL_10',realFileName:'报销及付款审批单'},
			'SYGS_CWGL_FKGL_20':{fileName:'SYGS_CWGL_FKGL_20',realFileName:'银行付款审批单'},
			'SYGS_GLTX_GWPZ_10':{fileName:'SYGS_GLTX_GWPZ_10',realFileName:'商业公司岗位配置及编制方案审批表'},
			'SYGS_GLTX_ZZJG_20':{fileName:'SYGS_GLTX_ZZJG_20',realFileName:'商业公司组织管理文件审批表'},
			'SYGS_JHGL_KFJH_40':{fileName:'SYGS_JHGL_KFJH_40',realFileName:'开业前筹备五级计划'},
			'SYGS_JHGL_YYJH_40':{fileName:'SYGS_JHGL_YYJH_40',realFileName:'商业公司开业后工作计划审批表'},
			'SYGS_JHGL_YYJH_50':{fileName:'SYGS_JHGL_YYJH_50',realFileName:'商业公司开业后企划计划审批表'},
			'SYGS_JHGL_YYJH_60':{fileName:'SYGS_JHGL_YYJH_60',realFileName:'商业公司开业后招商计划审批表'},
			'SYGS_JXGL_30':{fileName:'SYGS_JXGL_30',realFileName:'商业公司考核审批表'},
			'SYGS_WYGC_WYGL_20':{fileName:'SYGS_WYGC_WYGL_20',realFileName:'自持型物业管理费标准审批表'},
			'SYGS_XZRL_115':{fileName:'SYGS_XZRL_115',realFileName:'员工自带笔记本检验单(适用于宝龙商业)'},
			'SYGS_XZRL_118':{fileName:'SYGS_XZRL_118',realFileName:'重要来宾接待计划'},
			'SYGS_XZRL_120':{fileName:'SYGS_XZRL_120',realFileName:'A级接待审批表(适用于宝龙商业)'},
			'SYGS_XZRL_125':{fileName:'SYGS_XZRL_125',realFileName:'B级接待审批表(适用于宝龙商业)'},
			'SYGS_XZRL_130':{fileName:'SYGS_XZRL_130',realFileName:'C级接待审批表(适用于宝龙商业)'},
			'SYGS_XZRL_135':{fileName:'SYGS_XZRL_135',realFileName:'出差工作报告(适用于宝龙商业)'},
			'SYGS_XZRL_300':{fileName:'SYGS_XZRL_300',realFileName:'法律事务审批表(适用于商业总部)'},
			'SYGS_XZRL_305':{fileName:'SYGS_XZRL_305',realFileName:'法律事务审批表(适用于商业公司)'},
			'SYGS_XZRL_80':{fileName:'SYGS_XZRL_80',realFileName:'办公资产调拨单(适用于跨业务板块独立核算的公司之间的资产调拨)'},
			'SYGS_XZRL_85':{fileName:'SYGS_XZRL_85',realFileName:'办公资产调拨单(适用于商业板块各独立核算的公司之间的资产调拨)'},
			'SYGS_XZRL_QSLX_20':{fileName:'SYGS_XZRL_QSLX_20',realFileName:'商业公司法律事务审批表'},
			'TZGL_10':{fileName:'TZGL_10',realFileName:'挂牌条件、挂牌公告及摘牌资料审批表'},
			'TZGL_HTSP_10':{fileName:'TZGL_HTSP_10',realFileName:'投资类合同'},
			'TZGL_HTSP_20':{fileName:'TZGL_HTSP_20',realFileName:'土地买卖、租赁合同'},
			'TZGL_HTSP_30':{fileName:'TZGL_HTSP_30',realFileName:'政府规费缴纳事项(合同)审批表'},
			'TZGL_TZBZ_10':{fileName:'TZGL_TZBZ_10',realFileName:'城市(区域)评价标准审批表'},
			'TZGL_TZBZ_20':{fileName:'TZGL_TZBZ_20',realFileName:'土地选址标准审批表'},
			'TZGL_XMKY_20':{fileName:'TZGL_XMKY_20',realFileName:'项目预可研审批表'},
			'TZGL_XMTZ_10':{fileName:'TZGL_XMTZ_10',realFileName:'拿地计划审批表'},
			'XZRL_RLZY_140':{fileName:'XZRL_RLZY_140',realFileName:'人事派遣单'},
			'XZRL_RLZY_150':{fileName:'XZRL_RLZY_150',realFileName:'员工内部举荐表'},
			'XZRL_RLZY_160':{fileName:'XZRL_RLZY_160',realFileName:'新员工入职费用申请单'},
			'XZRL_RLZY_45':{fileName:'XZRL_RLZY_45',realFileName:'人事调动通知单'},
			'XZRL_XZGL_10':{fileName:'XZRL_XZGL_10',realFileName:'市内派车单'},
			'XZRL_XZGL_117':{fileName:'XZRL_XZGL_117',realFileName:'员工自带笔记本检验单'},
			'XZRL_XZGL_118':{fileName:'XZRL_XZGL_118',realFileName:'人力资源事项印鉴会签单（适用于集团总部）'},
			'XZRL_XZGL_119':{fileName:'XZRL_XZGL_119',realFileName:'行政事项印鉴会签单（适用于集团总部）'},
			'XZRL_XZGL_120':{fileName:'XZRL_XZGL_120',realFileName:'通用事项印鉴会签单(适用于集团总部)'},
			'XZRL_XZGL_121':{fileName:'XZRL_XZGL_121',realFileName:'印鉴会签单(适用于宝龙集团所属各公司)'},
			'XZRL_XZGL_122':{fileName:'XZRL_XZGL_122',realFileName:'财务事项印鉴会签单（适用于集团总部）'},
			'XZRL_XZGL_123':{fileName:'XZRL_XZGL_123',realFileName:'成本事项印鉴会签单（适用于集团总部）'},
			'XZRL_XZGL_124':{fileName:'XZRL_XZGL_124',realFileName:'成本事项印鉴会签单（适用于区域公司）'},
			'XZRL_XZGL_125':{fileName:'XZRL_XZGL_125',realFileName:'印鉴外借单'},
			'XZRL_XZGL_126':{fileName:'XZRL_XZGL_126',realFileName:'印鉴移交表'},
			'XZRL_XZGL_160':{fileName:'XZRL_XZGL_160',realFileName:'礼品借出清单'},
			'XZRL_XZGL_170':{fileName:'XZRL_XZGL_170',realFileName:'礼品领用清单'},
			'XZRL_XZGL_185':{fileName:'XZRL_XZGL_185',realFileName:'重要来宾接待计划'},
			'XZRL_XZGL_190':{fileName:'XZRL_XZGL_190',realFileName:'A级接待审批表'},
			'XZRL_XZGL_20':{fileName:'XZRL_XZGL_20',realFileName:'长途派车单'},
			'XZRL_XZGL_200':{fileName:'XZRL_XZGL_200',realFileName:'B级接待审批表'},
			'XZRL_XZGL_210':{fileName:'XZRL_XZGL_210',realFileName:'C级接待审批表'},
			'XZRL_XZGL_230':{fileName:'XZRL_XZGL_230',realFileName:'法律事务审批表(地产总部)'},
			'XZRL_XZGL_235':{fileName:'XZRL_XZGL_235',realFileName:'需总部决策法律事务审批表(地产公司)'},
			'XZRL_XZGL_30':{fileName:'XZRL_XZGL_30',realFileName:'长途用车申请单(适用于地产公司)'},
			'XZRL_XZGL_35':{fileName:'XZRL_XZGL_35',realFileName:'长途用车申请单(适用于各地拓展小组)'},
			'XZRL_XZGL_36':{fileName:'XZRL_XZGL_36',realFileName:'车辆美容项目申请单'},
			'XZRL_XZGL_37':{fileName:'XZRL_XZGL_37',realFileName:'车辆维修申请单'},
			'XZRL_XZGL_87':{fileName:'XZRL_XZGL_87',realFileName:'办公资产调拨单（适用于地产板块各独立核算的公司之间的资产调拨）'},
			'XZRL_XZGL_88':{fileName:'XZRL_XZGL_88',realFileName:'办公资产调拨单（适用于跨业务板块独立核算的公司之间的资产调拨）'},
			'XZRL_ZBCC_85':{fileName:'XZRL_ZBCC_85',realFileName:'出差工作报告'},
			'YXGL_XSJG_60':{fileName:'YXGL_XSJG_60',realFileName:'开盘定价/后期销售调价审批表(目标售价/底价)'},
			'YXGL_XSJG_70':{fileName:'YXGL_XSJG_70',realFileName:'开盘定价/后期销售调价审批表(价格表)'},
			'ZCGL_GFKC_20':{fileName:'ZCGL_GFKC_20',realFileName:'供方公司考察评估表(总承包公司类)'},
			'ZCGL_GFKC_30':{fileName:'ZCGL_GFKC_30',realFileName:'供方现场考察评估表(工程类)'},
			'ZCGL_GFKC_40':{fileName:'ZCGL_GFKC_40',realFileName:'供方公司考察评估表(分包公司类)'},
			'ZCGL_GFKC_50':{fileName:'ZCGL_GFKC_50',realFileName:'供方公司考察评估表(监理公司)'},
			'ZCGL_GFKC_60':{fileName:'ZCGL_GFKC_60',realFileName:'供方现场考察评估表(材料设备类)'},
			'ZCGL_GFKC_70':{fileName:'ZCGL_GFKC_70',realFileName:'供方现场考察评估表(监理)'},
			'ZCGL_GFKC_80':{fileName:'ZCGL_GFKC_80',realFileName:'供方现场考察评估表(设计类)'}
	};
</script>

<div align="center" style="width:90%;">
		<table width="100%">
			<tr>
				<td width="100" align="right">
					<img  src="${ctx}/js/prompt/skin/dmm-green/images/info.gif"/>
				</td>
				<td  align="left" style="padding-left:20px;line-height: 30px;">
					<div >该表单未上线,请走纸质流程,谢谢！附上表单模板(点击下载)</div>
					<div >
						<a href="#" style="color:#0a67a2;font-weight: bold;" id="sheetLinkId"></a>
					</div>
					 <script type="text/javascript">
						 	if(infos[curTypeCd]){
						 		var typeInfo = infos[curTypeCd];
						 		var url = commonUrl+'&fileName='+typeInfo.fileName+'.doc&realFileName='+typeInfo.realFileName+'.doc';
						 		$('#sheetLinkId').attr('href',encodeURI(url)).text(typeInfo.realFileName+'.doc');
						 	}
						 </script>
				</td>
			</tr>
		</table>
</div>
<%@ include file="template-footer.jsp"%>