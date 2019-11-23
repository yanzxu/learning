package com.homeworks.galaxy.util;

import java.util.List;

/**
 * 校验类：校验输入内容中符号的连续性是否符合要求
 * 
 * @author yanzhao.xu
 */
public class RomanSymbolValidator {

	/**
	 * 判断符号的连续性是否符合要求
	 * 
	 * @param romanSymbols 罗马符号集合
	 * @return true：符号；false：不符合
	 */
	public static boolean isValidConsecution(List<String> romanSymbols) {
		// 记录同一符号连续出现的次数
		int repeatCount = 1;
		String tempSymbol = romanSymbols.get(0);
		
		for (int i = 1; i < romanSymbols.size(); i++) {
			String currentSymbol = romanSymbols.get(i);
			
			// 判断两相邻符号是否相同
			if (currentSymbol.equals(tempSymbol)) {
				repeatCount++;
			} else {
				tempSymbol = currentSymbol;
				repeatCount = 1;
			}

			// 判断是否连续出现不能重复的符号
			if (repeatCount == 2 && ("D".equals(currentSymbol) || "L".equals(currentSymbol) || "V".equals(currentSymbol))) {
				System.err.println("'D','L' and 'V' can never be repeated!");
				return false;
			}

			// 判断连续出现的符号是否超过最大次数
			if (repeatCount > 3) {
				System.err.println("The symbols 'I', 'X', 'C, and 'M' can be repeated three times insuccession, but no more!");
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 判断两个相邻的符号是否符合被减规则
	 *
	 * @param curentSymbol 当前符号
	 * @param previousSymbol 前一个符号
	 * @return true：符号；false：不符合
	 */
	public static boolean isSubtractable(String curentSymbol, String previousSymbol) {
		// V L D 不能被减
		if ("V".equals(previousSymbol) || "L".equals(previousSymbol) || "D".equals(previousSymbol)) {
			System.err.println("V, L and D can never be Subtracted!");
			return false;
		} else {
			switch (previousSymbol) {
			case "I":
				// I只能被V和X减去
				if ("V".equals(curentSymbol) || "X".equals(curentSymbol)) {
					return true;
				} else {
					System.err.println("'I' can be subtracted from 'V' and 'X' only!");
					return false;
				}
			case "X":
				// X只能被L和C减去
				if ("L".equals(curentSymbol) || "C".equals(curentSymbol)) {
					return true;
				} else {
					System.err.println("'X' can be subtracted from 'L' and 'C' only!");
					return false;
				}
			case "C":
				// C只能被D和M减去
				if ("D".equals(curentSymbol) || "M".equals(curentSymbol)) {
					return true;
				} else {
					System.err.println("'C' can be subtracted from 'D' and 'M' only!");
					return false;
				}
			default:
				return true;
			}
		}
	}
}
