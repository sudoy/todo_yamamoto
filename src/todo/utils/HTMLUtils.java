package todo.utils;

import todo.forms.EntryForm;
import todo.forms.UpdateForm;

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

	public static String checkedRadio(EntryForm pack,int num) {
		if(pack == null) {
			if(num == 3){return "checked";}
			return "";
		}
		String value = pack.getValue();
		if(value!=null&&1<=Integer.valueOf(value)&&Integer.valueOf(value)<=3) {
			if(Integer.valueOf(value)==num) {
				return "checked";
			}
		}else if(num == 3){
			return "checked";
		}
		return "";
	}
	public static String checkedRadio(UpdateForm pack,int num) {
		int value = pack.getValue();
		if(Integer.valueOf(value)==num) {
			return "checked";
		}
		return "";
	}
}
