package main;

import java.io.IOException;

import factory.create.CodeCreater;
import freemarker.template.TemplateException;

public class Main {
	
	public static void main(String[] args) throws IOException, TemplateException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		// xml文件位置
		String sourcePath = "D:\\test\\excel\\dataExcel.xls";
		// 输出位置
		String writePath = "E:\\eclipse_led\\hpms\\src\\main\\java";
//		String writePath2 = "D:\\test\\code"
		
		CodeCreater.StartCreate(sourcePath, writePath);
	}
}
