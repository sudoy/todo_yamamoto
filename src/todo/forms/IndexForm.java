package todo.forms;

public class IndexForm {
	int id;
	String title;
	String details;
	String value;
	String limitdate;


	public IndexForm(int id, String title, String details, String value, String limitdate) {
		this.id = id;
		this.title = title;
		this.details = details;
		this.value = value;
		this.limitdate = limitdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
