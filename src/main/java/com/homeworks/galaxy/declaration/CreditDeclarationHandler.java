package com.homeworks.galaxy.declaration;


import com.homeworks.galaxy.converter.ContentParser;
import com.homeworks.galaxy.util.Dictionary;
import com.homeworks.galaxy.util.RomanSymbol;
import com.homeworks.galaxy.util.RomanSymbolsCalculator;

import java.util.List;

/**
 * 银河系信誉声明处理器类：用于解析输入内容中关于信誉的声明， 如：glob glob Silver is 34 Credits。
 *
 * @author yanzhao.xu
 */
public class CreditDeclarationHandler implements DeclarationHandler {

    private static final String SPACE = " ";

    /**
     * 字典类对象
     */
    private Dictionary dictionary;

    /**
     * 内容解析器对象
     */
    private ContentParser contentParser;

    /**
     * 声明内容数组
     */
    private String[] declarations;

    public CreditDeclarationHandler() {
        this.dictionary = Dictionary.getInstace();
        this.contentParser = new ContentParser();
    }

    /*
     * 解析信誉单位的声明
     *
     * declarationContent 声明内容
     */
    @Override
    public void parseDeclaration(String declarationContent) {
        // 获取声明内容数组
        declarations = splitDeclarationContent(declarationContent);

        if (declarations.length > 1) {
            // 获取声明单位集合
            List<String> galacticUnitsList = contentParser.convertUnitsContentToList(declarations[0]);
            // 获取信誉单位
            String creditUnit = galacticUnitsList.get(galacticUnitsList.size() - 1);

            // 将信誉单位及对应的信誉值存入字典
            dictionary.addCreditUnitAndValue(creditUnit, calculateCreditUnitValue());
        } else {
            System.err.println("The declaration of [" + declarationContent + "] is invalid!");
        }
    }

    /**
     * 计算信誉单位对应的信誉值
     *
     * @return 信誉单位对应的信誉值
     */
    private double calculateCreditUnitValue() {
        // 获取声明内容对应的罗马符号集合
        List<String> galacticUnitsList = contentParser.convertGalacticUnitsToRomanSymbols(declarations[0]);
        // 将罗马符号集合转换为对应的枚举型
        List<RomanSymbol> enumRomanSymbols = contentParser.convertRomanSymbolsToEnums(galacticUnitsList);

        // 计算并返回信誉单位对应的信誉值
        return getCreditAmountFromDeclaration(declarations[1]) / calculateCreditNum(enumRomanSymbols);
    }

    /**
     * 计算信誉单位的数量
     *
     * @param enumRomanSymbols 枚举型罗马符号集合
     * @return 信誉单位的数量
     */
    private double calculateCreditNum(List<RomanSymbol> enumRomanSymbols) {
        // 创建计算器类对象
        RomanSymbolsCalculator conversionOfRomanAndArabic = new RomanSymbolsCalculator(enumRomanSymbols);

        // 计算并返回信誉单位的数量
        return conversionOfRomanAndArabic.calculateRomanSymbolsValue();
    }

    /**
     * 获取声明内容中的信誉值
     *
     * @param delcaration 声明内容
     * @return 信誉值
     */
    private double getCreditAmountFromDeclaration(String delcaration) {
        String[] creditAndValue = delcaration.trim().split(SPACE);

        try {
            return Double.parseDouble(creditAndValue[0]);
        } catch (Exception e) {
            System.err.println("Invalid declaration!");
            return 0;
        }
    }
}
