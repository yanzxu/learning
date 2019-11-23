package com.homeworks.galaxy.converter;

import com.homeworks.galaxy.util.Dictionary;
import com.homeworks.galaxy.util.RomanSymbol;
import com.homeworks.galaxy.util.RomanSymbolValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 内容解析器类：解析输入的内容，并获取相应结果
 *
 * @author yanzhao.xu
 */
public class ContentParser {
    private static final String SPACE = " ";

    /**
     * 字典类对象
     */
    private Dictionary dictionary;

    public ContentParser() {
        dictionary = Dictionary.getInstace();
    }

    /**
     * 将银河系单位转换为对应的罗马符号集合
     *
     * @param galacticUnitsContent 银河系单位
     * @return 罗马符号集合
     */
    public List<String> convertGalacticUnitsToRomanSymbols(String galacticUnitsContent) {
        List<String> galacticUnits = convertUnitsContentToList(galacticUnitsContent);

        return galacticUnits.stream().map(dictionary::getRomanSymbolByGalaticUnit).filter(unit -> unit != null)
                .collect(Collectors.toList());
    }

    /**
     * 将内容中的银河系单位转换为对应的集合
     *
     * @param galacticUnitsContent 银河系单位
     * @return 银河系单位集合
     */
    public List<String> convertUnitsContentToList(String galacticUnitsContent) {
        String[] galacticUnits = galacticUnitsContent.trim().split(SPACE);

        return Arrays.asList(galacticUnits);
    }


    /**
     * 将罗马符号集合转换为对应的枚举型集合
     *
     * @param romanSymbols 罗马符号集合
     * @return 枚举型罗马符号集合
     */
    public List<RomanSymbol> convertRomanSymbolsToEnums(List<String> romanSymbols) {
        // 判断符号的连续性是否符合要求
        if (RomanSymbolValidator.isValidConsecution(romanSymbols)) {
            // 计算并返回枚举型符号集合
            return romanSymbols.stream().map(RomanSymbol::valueOf).filter(symbol -> symbol != null)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<RomanSymbol>();
        }
    }
}
