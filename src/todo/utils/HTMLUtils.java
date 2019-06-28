package todo.utils;

public class HTMLUtils {
	public static String valueFormat(int value) {
		String star = "";
		for(int i = 0;i<value;i++) {
			star +="★";
		}
		return star;
	}
	public static String limitdateFormat(String limitdate) {
		if(limitdate==null) {return "";}
		return limitdate.replace("-", "/");
	}
}
