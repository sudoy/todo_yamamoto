package todo.forms;

import java.util.List;

public class ErrorForm {
	private String title;
	private String details;
	private String value;
	private String limitdate;
	private List<String> err;
	public ErrorForm(String title, String details, String value, String limitdate, List<String> err) {
		this.title = title;
		this.details = details;
		this.value = value;
		this.limitdate = limitdate;
		this.err = err;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLimitdate() {
		return limitdate;
	}
	public void setLimitdate(String limitdate) {
		this.limitdate = limitdate;
	}
	public List<String> getErr() {
		return err;
	}
	public void setErr(List<String> err) {
		this.err = err;
	}

}
