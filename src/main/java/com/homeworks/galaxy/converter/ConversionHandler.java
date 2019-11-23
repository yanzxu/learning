package com.homeworks.galaxy.converter;

import com.homeworks.galaxy.declaration.CreditDeclarationHandler;
import com.homeworks.galaxy.declaration.DeclarationHandler;
import com.homeworks.galaxy.declaration.GalacticUnitDeclarationHandler;
import com.homeworks.galaxy.question.CreditsQuestion;
import com.homeworks.galaxy.question.GalacticUnitsQuestion;
import com.homeworks.galaxy.question.Question;

/**
 * 转换处理器类：根据输入内容调用不同方法对其进行解析处理
 *
 * @author yanzhao.xu
 */
public class ConversionHandler {
	private static final String IS = " is ";

	private static final String HOW = "how";

	private static final String HOW_MANY = "how many";

	private static final String HOW_MUCH = "how much";

	private static final String CREDITS = "Credits";

	private static final String QUESTION_MARK = "?";

	/**
	 * 判断输入内容，并调用相应处理
	 *
	 * @param inputContent 输入内容
	 */
	public void convertInputContents(String inputContent) {

		// 判断输入内容是否合法
		if (inputContent.contains(IS)) {

			// 当前内容为银河系单位的声明,如：glob is I
			if (!inputContent.startsWith(HOW) && !inputContent.endsWith(CREDITS)) {
				handleDeclarations(new GalacticUnitDeclarationHandler(), inputContent);
			}

			// 当前内容为银河系信誉单位的声明,如：glob glob Silver is 34 Credits
			if (!inputContent.startsWith(HOW) && inputContent.endsWith(CREDITS)) {
				handleDeclarations(new CreditDeclarationHandler(), inputContent);
			}

			// 当前内容为计算银河系单位的问题,如：how much is pish tegj glob glob ?
			if (inputContent.startsWith(HOW_MUCH) && inputContent.endsWith(QUESTION_MARK)) {
				handleQuestions(new GalacticUnitsQuestion(inputContent));
			}

			// 当前内容为计算银河系信誉值的问题,如：how many Credits is glob prok Silver ?
			if (inputContent.startsWith(HOW_MANY) && inputContent.endsWith(QUESTION_MARK)) {
				handleQuestions(new CreditsQuestion(inputContent));
			}
		} else {
			System.err.println("I have no idea what you are talking about");
		}
	}

	
	/** 
	* 调用声明处理器对声明内容进行解析
	*
	* @param declarationHandler 声明处理器
	* @param declarationContent 声明内容
	*/ 
	private void handleDeclarations(DeclarationHandler declarationHandler, String declarationContent) {
		declarationHandler.parseDeclaration(declarationContent);
	}

	/** 
	* 打印问题答案
	*
	* @param question 问题对象
	*/ 
	private void handleQuestions(Question question) {
		question.printAnswer();
	}
}
