<#--${pojo.getPackageDeclaration()}-->
package com.hhz.uums.dao.plas;
<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>
@${pojo.importType("org.springframework.stereotype.Service")}
@${pojo.importType("org.springframework.transaction.annotation.Transactional")}
public class ${declarationName}Manager extends ${pojo.importType("com.hhz.core.service.BaseService")}<${declarationName}, ${pojo.getJavaTypeName(clazz.identifierProperty, true)}> {
	@${pojo.importType("org.springframework.beans.factory.annotation.Autowired")}
	private ${declarationName}Dao ${entityName}Dao;

	public void save${declarationName}(${declarationName} ${entityName}) {
		${pojo.importType("com.hhz.core.utils.PowerUtils")}.setEmptyStr2Null(${entityName});
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
