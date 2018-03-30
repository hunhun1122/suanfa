package com.association_analysis.apriori;

import java.io.File;
import java.io.IOException;

/**
 * apriori关联规则挖掘算法调用类
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args) throws IOException{
		String filePath = "D:\\JavaAiSpace\\SuanFa\\src\\com\\apriori\\testInput.txt";
		
		AprioriTool tool = new AprioriTool(filePath, 2);
		tool.printAttachRule(0.7);

 
		}
}
