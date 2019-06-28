package todo.forms;

public class EntryForm {
	private String title;
	private String details;
	private int value;
	private String limitdate;
	public EntryForm( String title, String details, int value, String limitdate) {
		this.title = title;
		this.details = details;
		this.value = value;
		this.limitdate = limitdate;
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLimitdate() {
		return limitdate;
	}
	public void setLimitdate(String limitdate) {
		this.limitdate = limitdate;
	}


}
