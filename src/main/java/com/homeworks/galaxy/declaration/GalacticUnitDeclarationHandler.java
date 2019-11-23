package com.homeworks.galaxy.declaration;


import com.homeworks.galaxy.util.Dictionary;

/**
 * 银河系单位声明处理器类：解析输入内容中关于银河系单位的声明， 如：glob is I。
 *
 * @author yanzhao.xu
 */
public class GalacticUnitDeclarationHandler implements DeclarationHandler {

    /**
     * 字典类对象
     */
    private Dictionary dictionary;

    public GalacticUnitDeclarationHandler() {
        this.dictionary = Dictionary.getInstace();
    }

    /*
     * 解析银河系单位的声明
     *
     * unitDeclaration 声明内容
     */
    @Override
    public void parseDeclaration(String unitDeclaration) {
        // 获取声明内容数组
        String[] declarations = splitDeclarationContent(unitDeclaration);

        if (declarations.length > 1) {
            // 将解析后的声明内容存入字典
            dictionary.addGalaxyAndSymbol(declarations[0].trim(), declarations[1].trim());
        } else {
            System.err.println("The declaration of [" + unitDeclaration + "] is invalid!");
        }
    }
}
