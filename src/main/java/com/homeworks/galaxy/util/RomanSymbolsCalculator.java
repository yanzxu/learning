package com.homeworks.galaxy.util;

import java.util.List;

/**
 * 罗马符号计算器类： 根据罗马符号集合算出阿拉伯数值
 *
 * @author yanzhao.xu
 */
public class RomanSymbolsCalculator {
	/**
	 * 枚举型罗马符号集合
	 */
	private List<RomanSymbol> romanSymbols;
	
	/**
	 * 符号下标
	 */
	private int symbolIndex ;
	
	/**
	 * 计算结果
	 */
	private double resultValue;
	
	/**
	 * 存储枚举类罗马符号的临时变量
	 */
	private RomanSymbol tempRomanSymbol;

	public RomanSymbolsCalculator(List<RomanSymbol> romanSymbols) {
		this.romanSymbols = romanSymbols;
		this.symbolIndex = 0;
		this.resultValue = 0;
	}

	/** 
	 * 计算罗马符号对应的阿拉伯数值
	 *
	 * @return 阿拉伯数值
	 */ 
	public double calculateRomanSymbolsValue() {
		if (romanSymbols != null && romanSymbols.size() > 0) {
			RomanSymbol firstSymbol = romanSymbols.get(0);

			if (romanSymbols.size() == 1) {
				// 只有一个符号时，该符号对应的阿拉伯数值即是计算结果
				addCurrentValueToResult(firstSymbol);
			} else {
				tempRomanSymbol = firstSymbol;

				for (int loopIndex = 1; loopIndex < romanSymbols.size(); loopIndex++) {
					// 计算相邻两个符号的值
					loopIndex = calculateContiguousSymbolsValue(loopIndex);
				}

				if (symbolIndex < romanSymbols.size()) {
					// 当前符号下标小于符号个数时，将计算结果加上当前值
					addCurrentValueToResult(tempRomanSymbol);
				}
			}
		}

		return resultValue;
	}
	
	/** 
	 * 计算两个相邻符号的值 
	 *
	 * @param loopIndex 循环下标
	 * @return 当前循环下标
	 */ 
	private int calculateContiguousSymbolsValue(int loopIndex) {
		RomanSymbol currentSymbol = romanSymbols.get(loopIndex);

		if (tempRomanSymbol.getSymbolValue() >= currentSymbol.getSymbolValue()) {
			// 前一个符号比当前的大时，将计算结果加上前一个符号值
			addCurrentValueToResult(tempRomanSymbol);
			tempRomanSymbol = currentSymbol;
			symbolIndex++;
		} else {
			// 判断相邻两符号是否符合要求
			if (RomanSymbolValidator.isSubtractable(currentSymbol.name(), tempRomanSymbol.name())) {
				resultValue += (currentSymbol.getSymbolValue() - tempRomanSymbol.getSymbolValue());
				symbolIndex += 2;
			} else {
				resultValue = 0;
			}

			loopIndex++;
			if (loopIndex < romanSymbols.size()) {
				tempRomanSymbol = romanSymbols.get(loopIndex);
			}
		}
		
		return loopIndex;
	}
	
	/** 
	 * 计算结果加上当前符号值
	 *
	 * @param currentSymbol 当前符号
	 */ 
	private void addCurrentValueToResult(RomanSymbol currentSymbol){
		resultValue += currentSymbol.getSymbolValue();
	}

}
