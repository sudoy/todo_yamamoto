package todo.forms;

public class IndexForm {
	private int id;
	private String title;
	private String value;
	private String limitdate;


	public IndexForm(int id, String title, String value, String limitdate) {
		this.id = id;
		this.title = title;
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
