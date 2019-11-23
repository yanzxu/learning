package com.homeworks.galaxy.question;


import com.homeworks.galaxy.converter.ContentParser;

/**
 * 银河系单位的问题类：回答关于计算银河系单位的问题，如：how much is pish tegj glob glob ?
 *
 * @author yanzhao.xu
 */
public class GalacticUnitsQuestion extends Question {

	public GalacticUnitsQuestion(String questionContent) {
		super();
		this.questionContent = questionContent;
		this.contentParser = new ContentParser();
	}

	@Override
	public void printAnswer() {
		if (isValidQuestion()) {
			// 计算并打印问题中的信誉总值
			System.out.println(galacticUnitsContent + " is " + calculateResult());
		}
	}
}
