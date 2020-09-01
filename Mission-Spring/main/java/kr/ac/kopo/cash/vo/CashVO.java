package kr.ac.kopo.cash.vo;

public class CashVO {

	private int no;
	private String id;
	private int cash;
	private String logDate;
	private String content;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CashVO [no=" + no + ", id=" + id + ", cash=" + cash + ", logDate=" + logDate + ", content=" + content
				+ "]";
	}
	
	
	
	
	
}
