package kr.ac.kopo.friend.vo;

public class FriendVO {

	private int no;
	private String id;
	private String friendId;
	private String friendName;
	private String content;
	private String agreeFlag;
	private String regDate;
	private int expenditureThisMonth;
	
	
	public int getExpenditureThisMonth() {
		return expenditureThisMonth;
	}
	public void setExpenditureThisMonth(int expenditureThisMonth) {
		this.expenditureThisMonth = expenditureThisMonth;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAgreeFlag() {
		return agreeFlag;
	}
	public void setAgreeFlag(String agreeFlag) {
		this.agreeFlag = agreeFlag;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "FriendVO [no=" + no + ", id=" + id + ", friendId=" + friendId + ", friendName=" + friendName
				+ ", content=" + content + ", agreeFlag=" + agreeFlag + ", regDate=" + regDate
				+ ", expenditureThisMonth=" + expenditureThisMonth + "]";
	}
	

	
	
	
}
