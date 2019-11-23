package com.homeworks.galaxy.question;


import com.homeworks.galaxy.converter.ContentParser;
import com.homeworks.galaxy.util.Dictionary;

import java.util.List;

/**
 * 信誉值的问题类：回答关于计算银河系信誉值的问题，如：how many Credits is glob prok Silver ?
 *
 * @author yanzhao.xu
 */
public class CreditsQuestion extends Question {
	
	public CreditsQuestion(String questionContent) {
		super();
		this.questionContent = questionContent;
		this.contentParser = new ContentParser();
	}

	@Override
	public void printAnswer() {
		if (isValidQuestion()) {
			// 计算并打印问题中的信誉总值
			System.out.println(galacticUnitsContent + " is " + calculateResult() * getCreditUnitValue() + " Credits");
		}
	}

	/** 
	 * 获取信誉单位对应的信誉值
	 *
	 * @return 信誉值
	 */ 
	private double getCreditUnitValue() {
		// 获取信誉单位
		List<String> galacticUnits = contentParser.convertUnitsContentToList(galacticUnitsContent);
		String creditUnit = galacticUnits.get(galacticUnits.size() - 1);
		
		return Dictionary.getInstace().getCreditValueByCreditUnit(creditUnit);
	}
}
