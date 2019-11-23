package com.homeworks.galaxy.question;


import com.homeworks.galaxy.converter.ContentParser;
import com.homeworks.galaxy.util.RomanSymbol;
import com.homeworks.galaxy.util.RomanSymbolsCalculator;

import java.util.ArrayList;
import java.util.List;


/**
 * 问题类：用于计算并回答问题。
 *
 * @author yanzhao.xu
 */
public abstract class Question {
    private static final String EMPTY = "";

    private static final String IS = " is ";

    private static final String QUESTION_MARK = "?";

    /**
     * 问题内容
     */
    protected String questionContent;

    /**
     * 问题中的银河系单位
     */
    protected String galacticUnitsContent;

    /**
     * 内容解析器对象
     */
    ContentParser contentParser;

    /**
     * 打印问题答案
     */
    public abstract void printAnswer();

    /**
     * 判断问题是否有效
     *
     * @return true:有效；false：无效;
     */
    protected boolean isValidQuestion() {
        // 获取问题中的银河系单位
        this.galacticUnitsContent = getGalacticUnitsFromQuestion();

        if (EMPTY.equals(galacticUnitsContent.trim())) {
            System.err.println("the question of [" + questionContent + "] is invalid!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 从问题中提取银河系单位
     *
     * @return 银河系单位
     */
    private String getGalacticUnitsFromQuestion() {
        int startIndex = questionContent.indexOf(IS);
        int endIndex = questionContent.indexOf(QUESTION_MARK);

        return questionContent.substring(startIndex + 3, endIndex).trim();
    }

    /**
     * 计算问题结果
     *
     * @return 计算结果
     */
    protected double calculateResult() {
        // 获取问题中的罗马符号集合
        List<RomanSymbol> enumRomanSymbols = getRomanSymbolsFromQuestion();

        // 计算并返回结果
        return new RomanSymbolsCalculator(enumRomanSymbols).calculateRomanSymbolsValue();
    }

    /**
     * 获取问题中的罗马符号集合
     *
     * @return 枚举型罗马符号集合
     */
    private List<RomanSymbol> getRomanSymbolsFromQuestion() {
        // 将银河系单位转换为罗马符号
        List<String> romanSymbols = contentParser.convertGalacticUnitsToRomanSymbols(galacticUnitsContent);

        if (romanSymbols.size() == 0) {
            return new ArrayList<RomanSymbol>();
        } else {
            return contentParser.convertRomanSymbolsToEnums(romanSymbols);
        }
    }
}
