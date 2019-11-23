package com.homeworks.galaxy.converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件读取类：从指定文件路径读取输入数据
 *
 * @author yanzhao.xu
 */
public class InputFileReader {

	/**
	 * 输入文件路径
	 */
	private String filePath;
	
	/**
	 * 转换处理器对象
	 */
	private ConversionHandler conversionHandler;

	public InputFileReader(String path) {
		this.filePath = path;
		this.conversionHandler = new ConversionHandler();
	}

	/**
	 * 根据文件路径读取指定文件，并调用处理器进行处理。
	 */
	public void readAndConvertInputContents() {
		try {
			// 读取指定文件作为数据流，并进行遍历转换
			Files.lines(Paths.get(filePath)).forEach(conversionHandler::convertInputContents);
		} catch (IOException exception) {
			System.err.println("Failed to read the input file!");
		}
	}

}
