package com.homeworks.galaxy.util;

/**
 * 罗马符号类：包含指定的罗马符号及其对应的阿拉伯数值
 *
 * @author yanzhao.xu
 *
 */
public enum RomanSymbol {
	
	// 字母为：指定的罗马符号；括号中数字为：符号对应的阿拉伯数值
	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
	
	// 罗马符号对应的阿拉伯数值
	private int symbolValue;
	
	RomanSymbol(int value){
		this.symbolValue = value;
	}
	
	public int getSymbolValue(){
		return symbolValue;
	}
}
