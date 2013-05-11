<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	<title>这是测试程序</title>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/gray/easyui.css"/>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>

<script type="text/javascript" src="${ctx}/js/tab4view.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>

	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
</head>
<body>
<table id="comTypeAndPropertyTree"></table>
</body>

<script>
var types = [
             {typeId:0,typeName:'Normal'},
             {typeId:1,typeName:'URL'},
             {typeId:2,typeName:'Symbol'}
         ];
         
       $(function(){
            var lastIndex;
          $('#comTypeAndPropertyTree').treegrid({
           title:'',
           height:550,
           rownumbers: true,
           animate:true,
           url:'${ctx}/ct/treegrid_data.json',
           idField:'id',//id=productId_propId_largetypeId_midlletypeId_comtypeId;
           treeField:'name',
           frozenColumns:[[
                        {title:'序号',field:'id',width:40,
                         formatter:function(value){
                          return '<span style="color:red">'+value+'</span>';
                         }
                        }
           ]],
           columns:[[
               {title:'成本科目(金额)',field:'library',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                if(!row.leaf){
                 if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                }else{
                 return '';
                }
                }},
            {title:'目本成本',field:'displayName',width:100,editor:'text'},
            {title:'type',field:'type',width:100,
             editor:{type:'combobox',options:{valueField:'typeId',textField:'typeName',data:types}},
                formatter:function(value,row){
                 if(row.leaf){
                  for(var i=0; i<types.length; i++){
                if (types[i].typeId == value) return types[i].typeName;
               }
               return types[0].typeName;
              }else{
               return '';
              }
                         }
               },
            {title:'分解总额',field:'expose',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }},
            {title:'合同编号',field:'annotate',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                 if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }
               },
            {title:'合约规划',field:'load',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                 if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }
               },
            {title:'合同名称',field:'nameAvailable',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                 if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }
               },
            {title:'对方签约单位',field:'valueAvailable',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                 if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }
               }/*,
            {title:'Export As OAT',field:'exportAsOAT',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                 if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }
               },
            {title:'Required',field:'required',width:100,editor:{type:'checkbox',options:{on:'1',off:'0'}},
                formatter:function(value,row){
                 if(row.leaf){
                  if(value==1){
                   return '<img src="images/checkbox_checked.jpg"/>';
                  }else{
                   return '<img src="images/checkbox_unchecked.jpg"/>';
                  }
                 }else{
                  return '';
                 }
                }
               },
            {title:'Units',field:'units',width:100,editor:'text',
             formatter:function(value,row){
              if(row.leaf){
               return value;
              }else{
               return '';
              }
             }},
            {title:'VM',field:'vm',width:100,editor:'text',
             formatter:function(value,row){
              if(row.leaf){
               return value;
              }else{
               return '';
              }
             }}*/
           ]]
           ,onClickRow:function(row){//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
            var rowIndex = row.id;
            if (lastIndex != rowIndex){
             $('#comTypeAndPropertyTree').treegrid('endEdit', lastIndex);
            }
           }
           ,onDblClickRow:function(row){//运用双击事件实现对一行的编辑
            var rowIndex = row.id;
            if (lastIndex != rowIndex){
             $('#comTypeAndPropertyTree').treegrid('endEdit', lastIndex);
             $('#comTypeAndPropertyTree').treegrid('beginEdit', rowIndex);
             lastIndex = rowIndex;
            }
           }
           ,onBeforeEdit:function(row){
                  beforEditRow(row);//这里是功能实现的主要步骤和代码
           }
           ,onAfterEdit:function(row,changes){
            var rowId = row.id;
            $.ajax({
                 url:"saveProductConfig.action" ,
                 data: row,
                 success: function(text){
                $.messager.alert('提示信息',text,'info');  
                 }
               });
           }
          });
         });
         function beforEditRow(row){//这个是核心的，很有用的，如有同样需求的话可以借鉴实现
             var libraryCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','library');
                var exposeCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','expose');
                var annotateCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','annotate');
                var loadCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','load');
                var nameAvailableCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','nameAvailable');
                var valueAvailableCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','valueAvailable');
                var exportAsOATCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','exportAsOAT');
                var requiredCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','required');
               
                var unitsCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','units');
                var vmCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','vm');
               
                 var typeCoclum = $('#comTypeAndPropertyTree').treegrid('getColumnOption','type');
               
                var checkboxOptionsObj = new Object();
           checkboxOptionsObj.on='1';
           checkboxOptionsObj.off='0';
             var checkboxEditorObj = new Object();
           checkboxEditorObj.type='checkbox';
           checkboxEditorObj.options=checkboxOptionsObj;
           
          var comboboxOptionsObj = new Object();
           comboboxOptionsObj.valueField='typeId';
           comboboxOptionsObj.textField='typeName';
           comboboxOptionsObj.data=types;
          var comboboxEditorObj = new Object();
           comboboxEditorObj.type='combobox';
           comboboxEditorObj.options=comboboxOptionsObj;
          if(row.leaf){
           libraryCoclum.editor=null;
           exposeCoclum.editor=checkboxEditorObj;
           annotateCoclum.editor=checkboxEditorObj;
           loadCoclum.editor=checkboxEditorObj;
           nameAvailableCoclum.editor=checkboxEditorObj;
           valueAvailableCoclum.editor=checkboxEditorObj;
           exportAsOATCoclum.editor=checkboxEditorObj;
           requiredCoclum.editor=checkboxEditorObj;
           
           unitsCoclum.editor='text';
           vmCoclum.editor='text';
           
           typeCoclum.editor=comboboxEditorObj;
          }else{
           libraryCoclum.editor=checkboxEditorObj;
           exposeCoclum.editor=null;
           annotateCoclum.editor=null;
           loadCoclum.editor=null;
           nameAvailableCoclum.editor=null;
           valueAvailableCoclum.editor=null;
           exportAsOATCoclum.editor=null;
           requiredCoclum.editor=null;
           
           unitsCoclum.editor=null;
           vmCoclum.editor=null;
           
           typeCoclum.editor=null;
          }
           }

</script>
</html>