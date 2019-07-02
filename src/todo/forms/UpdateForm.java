package todo.forms;

public class UpdateForm {
	private String id;
	private String title;
	private String details;
	private int value;
	private String limitdate;
	private String did;

	public UpdateForm(String id, String title, String details, int value, String limitdate, String did) {
		super();
		this.id = id;
		this.title = title;
		this.details = details;
		this.value = value;
		this.limitdate = limitdate;
		this.did = did;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}


}
