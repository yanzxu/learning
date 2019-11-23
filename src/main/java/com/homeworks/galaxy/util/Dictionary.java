package com.homeworks.galaxy.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字典类：根据输入的声明内容构建字典，
 * 功能包括： 1.通过银河系单位查找对应的罗马符号； 2.根据罗马符号查找对应的阿拉伯数值；3.根据信誉单位查找信誉值。
 *
 * @author yanzhao.xu
 */
public class Dictionary {

	/**
	 * 存储银河系单位和罗马符号的map，key：银河系单位；value：银河系单位对应的罗马符号
	 */
	private Map<String, String> galaxyAndRomanSymbol;

	/**
	 * 存储银河系信誉单位和对应的信誉值，key：信誉单位；value：信誉值
	 */
	private Map<String, Double> creditUnitAndValue;

	/**
	 * 存储罗马符号和对应的枚举变量，key：罗马符号，value：罗马符号对应的枚举变量
	 */
	private Map<String, RomanSymbol> romanSymbolAndValue;

	// 创建静态字典类实例对象
	private static Dictionary theDictionary = new Dictionary();

	private Dictionary() {
		galaxyAndRomanSymbol = new HashMap<String, String>();
		creditUnitAndValue = new HashMap<String, Double>();
		romanSymbolAndValue = initRomanSymbolAndValue();
	}

	/**
	 * 对外提供获取Dictionary对象的方法
	 *
	 * @return Dictionary 字典类对象
	 */
	public static Dictionary getInstace() {
		return theDictionary;
	}

	/**
	 * 初始化romanSymbolAndValue
	 */
	private Map<String, RomanSymbol> initRomanSymbolAndValue() {
		RomanSymbol[] romanSymbols = RomanSymbol.values();
		
		return Stream.of(romanSymbols).collect(Collectors.toMap(RomanSymbol::name, symbol -> symbol));
	}

	/** 
	 * 向galaxyAndRomanSymbol中添加元素
	 *
	 * @param galaticUnit 银河系单位
	 * @param romanSymbol 罗马符号
	 */ 
	public void addGalaxyAndSymbol(String galaticUnit, String romanSymbol) {
		galaxyAndRomanSymbol.put(galaticUnit, romanSymbol);
	}

	/**
	 * 向creditUnitAndValue中添加元素
	 *
	 * @param creditUnit 信誉单位
	 * @param creditValue 信誉单位对应的值
	 */
	public void addCreditUnitAndValue(String creditUnit, double creditValue) {
		creditUnitAndValue.put(creditUnit, creditValue);
	}

	/**
	 * 通过银河系单位获取对应的罗马符号
	 *
	 * @param galaticUnit 银河系单位
	 * @return 罗马符号
	 */
	public String getRomanSymbolByGalaticUnit(String galaticUnit) {
		return galaxyAndRomanSymbol.get(galaticUnit);
	}   

	/**
	 * 通过罗马符号获取对应的枚举变量
	 *
	 * @param romanSymbol 罗马符号
	 * @return 枚举变量
	 */
	public RomanSymbol getRomanValueByRomanSymbol(String romanSymbol) {
		return romanSymbolAndValue.get(romanSymbol);
	}

	/**
	 * 通过信誉单位获取信誉值
	 *
	 * @param creditUnit 信誉单位
	 * @return 信誉值
	 */
	public double getCreditValueByCreditUnit(String creditUnit) {
		if (creditUnitAndValue.containsKey(creditUnit)) {
			return creditUnitAndValue.get(creditUnit);
		}else {
			return 0;
		}
	}
}
