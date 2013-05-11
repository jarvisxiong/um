<#--${pojo.getPackageDeclaration()}-->
package com.hhz.dao.pl;
<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>
@${pojo.importType("org.springframework.stereotype.Service")}
@${pojo.importType("org.springframework.transaction.annotation.Transactional")}
public class ${declarationName}Manager extends ${pojo.importType("com.hhz.service.BaseService")}<${declarationName}, ${pojo.getJavaTypeName(clazz.identifierProperty, true)}> {
	@${pojo.importType("org.springframework.beans.factory.annotation.Autowired")}
	private ${declarationName}Dao ${entityName}Dao;

	// ${declarationName} Manager //
	@Transactional(readOnly = true)
	public ${declarationName} get${declarationName}(${pojo.getJavaTypeName(clazz.identifierProperty, true)} id) {
		return ${entityName}Dao.get(id);
	}
	
	public ${pojo.importType("java.util.List")}<${declarationName}> getAll${declarationName}() {
		return ${entityName}Dao.getAll();
	}
	
	public void save${declarationName}(${declarationName} ${entityName}) {
		${pojo.importType("com.hhz.utils.PowerUtils")}.setEmptyStr2Null(${entityName});
		${entityName}Dao.save(${entityName});
	}

	public void delete${declarationName}(${pojo.getJavaTypeName(clazz.identifierProperty, true)} id) {
		${entityName}Dao.delete(id);
	}
	
	@Override
	public ${pojo.importType("org.springside.modules.orm.hibernate.HibernateDao")}<${declarationName}, ${pojo.getJavaTypeName(clazz.identifierProperty, true)}> getDao() {
		return ${entityName}Dao;
	}
	
}
</#assign>

${pojo.generateImports()}
import ${pojo.getQualifiedDeclarationName()};

${classbody}
