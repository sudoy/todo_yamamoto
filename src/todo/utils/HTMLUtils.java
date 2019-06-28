package todo.utils;

import todo.forms.ErrorForm;

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

	public static String checkedRadio(ErrorForm Epack,int num) {
		if(Epack == null) {
			if(num == 3){return "checked";}
			return "";
		}
		String value = Epack.getValue();
		if(value!=null&&1<=Integer.valueOf(value)&&Integer.valueOf(value)<=3) {
			if(Integer.valueOf(value)==num) {
				return "checked";
			}
		}else if(num == 3){
			return "checked";
		}
		return "";
	}
}
