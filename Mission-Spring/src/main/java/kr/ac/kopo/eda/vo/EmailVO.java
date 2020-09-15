package kr.ac.kopo.eda.vo;

public class EmailVO {

	private String id;
	private String toMail;
	private String title;
	private String content;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "EmailVO [id=" + id + ", toMail=" + toMail + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
}
