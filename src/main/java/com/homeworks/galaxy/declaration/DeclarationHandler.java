package com.homeworks.galaxy.declaration;

/**
 * 声明处理器接口：解析输入内容中的声明语句
 * 
 * @author yanzhao.xu
 */
public interface DeclarationHandler {
	
	/** 
	 * 将声明语句通过"is"分隔成数组 
	 *
	 * @param declarationContent 声明内容
	 * @return 声明内容数组
	 */ 
	default String[] splitDeclarationContent(String declarationContent){
		return declarationContent.split(" is ");
	}
	
	/**
	 * 解析声明语句
	 * 
	 * @param declaration 声明内容
	 */
	void parseDeclaration(String declaration);

}
