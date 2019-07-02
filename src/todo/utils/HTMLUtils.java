package todo.utils;

import todo.forms.EntryForm;
import todo.forms.IndexForm;
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
		if(1<=Integer.valueOf(value)&&Integer.valueOf(value)<=3) {
			if(Integer.valueOf(value)==num) {
				return "checked";
			}
		}else if(num == 3){
			return "checked";
		}
		return "";
	}

	/**
	 *
	 * @param pack UpdateFrom
	 * @param num 使用時の番号
	 * @param limit 最大数
	 * @return
	 */
	public static String selectValue(UpdateForm pack,int num,int limit) {
		if(pack == null) {
			if(num == limit){return "checked";}
			return "";
		}
		String value = String.valueOf(pack.getValue());
		if(1<=Integer.valueOf(value)&&Integer.valueOf(value)<= limit) {
			if(Integer.valueOf(value)==num) {
				return "checked";
			}
		}else if(num == limit){
			return "checked";
		}
		return "";
	}
	/**
	 *
	 * @param pack UpdateFrom
	 * @param num 使用時の番号
	 * @param limit 最大数
	 * @return
	 */
	public static String selectDid(UpdateForm pack,int num,int limit) {
		if(pack == null) {
			if(num == limit){return "checked";}
			return "";
		}
		String value = String.valueOf(pack.getDid());
		if(1<=Integer.valueOf(value)&&Integer.valueOf(value)<= limit) {
			if(Integer.valueOf(value)==num) {
				return "checked";
			}
		}else if(num == limit){
			return "checked";
		}
		return "";
	}

	public static String checked(IndexForm pack) {
		String did = pack.getDid();
		if(did.equals("2")) {
			return "checked";
		}
		return "";
	}

	public static String pushed(String didValue,String i) {
		if(didValue.equals(i)) {
			return "disabled=\"disabled\"";
		}
		return "";
	}
}
