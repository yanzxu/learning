package homework;


import com.homeworks.galaxy.converter.InputFileReader;
import com.homeworks.galaxy.util.Dictionary;
import com.homeworks.galaxy.util.RomanSymbol;
import com.homeworks.galaxy.util.RomanSymbolsCalculator;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestGalaxy {


    @Test
    public void testConversion() {
        // 指定输入文件路径
        String filePath = "galaxyInputContent.txt";
        // 创建文件读取类对象
        TestGalaxy.class.getClassLoader().getResource(filePath).getPath();
        InputFileReader inputFileReader = new InputFileReader(this.getClass().getClassLoader().getResource(filePath).getPath());

        inputFileReader.readAndConvertInputContents();
    }


    /**
     * 测试 将声明中的罗马字符串转换成对应的阿拉伯数值
     */
    @Test
    public void TestConversionOfRomanAndArabic() {
        // 计算罗马符号：II对应的数值
        RomanSymbolsCalculator calculator1 = new RomanSymbolsCalculator(Arrays.asList(RomanSymbol.I, RomanSymbol.I));
        double valueOfII = calculator1.calculateRomanSymbolsValue();
        assertEquals(2.0, valueOfII, 0);

        // 计算罗马符号：IV对应的数值
        RomanSymbolsCalculator calculator2 = new RomanSymbolsCalculator(Arrays.asList(RomanSymbol.I, RomanSymbol.V));
        double valueOfIV = calculator2.calculateRomanSymbolsValue();
        assertEquals(4.0, valueOfIV, 0);

        // 计算罗马符号：IXC对应的数值
        RomanSymbolsCalculator calculator3 = new RomanSymbolsCalculator(Arrays.asList(RomanSymbol.I, RomanSymbol.X, RomanSymbol.C));
        double valueOfIXC = calculator3.calculateRomanSymbolsValue();
        assertEquals(109.0, valueOfIXC, 0);
    }

    /**
     * 测试字典类
     */
    @Test
    public void testDictionary() {
        // 获取字典类实例对象
        Dictionary dictionary = Dictionary.getInstace();
        // 向galaxyAndRomanSymbol中添加元素
        dictionary.addGalaxyAndSymbol("glob", "I");
        dictionary.addGalaxyAndSymbol("prok", "V");
        dictionary.addGalaxyAndSymbol("pish", "X");
        dictionary.addGalaxyAndSymbol("tegi", "L");

        // 断言银河系单位对应的罗马符号
        assertEquals("I", dictionary.getRomanSymbolByGalaticUnit("glob"));

        // 断言罗马符号对应的阿拉伯数值
        assertEquals(50, dictionary.getRomanValueByRomanSymbol("L").getSymbolValue());
        assertEquals(500, dictionary.getRomanValueByRomanSymbol("D").getSymbolValue());
    }


    /**
     * 测试根据罗马符号获取相应的阿拉伯数值
     */
    @Test
    public void testSymbolValue() {
        // 根据符号获取枚举变量
        RomanSymbol valueOfI = RomanSymbol.valueOf("I");
        // 断言枚举变量对应的数值
        assertEquals(1, valueOfI.getSymbolValue());
    }

}
