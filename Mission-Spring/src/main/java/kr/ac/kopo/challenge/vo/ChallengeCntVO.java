package kr.ac.kopo.challenge.vo;

public class ChallengeCntVO {

	private String bank_book_key;
	private int cnt;
	private String ageGroup;
	
	public String getBank_book_key() {
		return bank_book_key;
	}
	public void setBank_book_key(String bank_book_key) {
		this.bank_book_key = bank_book_key;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	@Override
	public String toString() {
		return "ChallengeCntVO [bank_book_key=" + bank_book_key + ", cnt=" + cnt + ", ageGroup=" + ageGroup + "]";
	}
	
	
}
