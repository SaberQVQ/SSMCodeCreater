package factory.stringCaseUtil;

/**
 * String 转化工具类
 * 
 */
public class StringCaseUtil {

	/**
	 * 首字母小写 其余每个单词首字母大写
	 * @param s
	 * @return
	 */
	public static String lowcaseFirstCharRemoveMark(String s){
		StringBuilder sb = removeMark(s);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}



	/**
	 * 首字母大写 其余每个单词首字母大写
	 * @param s
	 * @return
	 */
	public static String upcaseFirstCharRemoveMark(String s){
		StringBuilder sb = removeMark(s);
		return sb.toString();
	}


	/**
	 * 首字母大写
	 * @param s
	 * @return
	 */
	public static String upcaseFirstChar(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误");
			return "";
		}
		
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * 首字母小写
	 * @param s
	 * @return
	 */
	public static String lowcaseFirstChar(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误");
			return "";
		}
		
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 *  在数据库设计文件中去除"_"后  每个单词首字母大写
	 * @param s
	 * @return
	 */
	private static StringBuilder removeMark(String s){
		StringBuilder sb = new StringBuilder();
		if (null == s || ("").equals(s)) {
			System.out.println("转换字符参数错误");
			sb.append("");
			return sb;
		}
		String[] strs = s.split("_");
		StringBuilder sbChild;
		for (int i= 0; i < strs.length; i++) {
			sbChild = new StringBuilder(lowcaseAll(strs[i]));
			sbChild.setCharAt(0, Character.toUpperCase(sbChild.charAt(0)));
			sb.append(sbChild);
		}
		return sb;
	}
	
	//全转换大写
	public static String upcaseAll(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误 ");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] >= 97) {
				sb.append((c[i] + "").toUpperCase());
			} else {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}
	
	//全转换小写
	public static String lowcaseAll(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误 ");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// "a" ASCII ==> 97
			if (c[i] < 97) {
				sb.append((c[i] + "").toLowerCase());
			} else {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}

	public static boolean toBoolean(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		if (s.equals("0")) {
			return false;
		} else if (s.equals("1")) {
			return true;
		}
		return Boolean.parseBoolean(s);
	}
	
	public static int toInt(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		return Integer.parseInt(s);
	}
}
