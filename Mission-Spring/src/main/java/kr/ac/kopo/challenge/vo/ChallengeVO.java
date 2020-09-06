package kr.ac.kopo.challenge.vo;

public class ChallengeVO {

	private int challengePk;			//pk
	private String challengeName;		//도전 이름
	private String id;					//도전자 id
	private String challengeEndDate;	//도전 만료일
	private String challengeType;		//도전 타입.(카테고리)
	private int targetAmount;			//목표 금액
	private int nowBalanceByType;		//메인계좌의 도전 내역 카테고리의 이번달 총지출액 합 
	
	private int expectedAmount;
	private boolean challengeFail;
	
	
	
	
	public boolean isChallengeFail() {
		return challengeFail;
	}
	public void setChallengeFail(boolean challengeFail) {
		this.challengeFail = challengeFail;
	}
	public int getExpectedAmount() {
		return expectedAmount;
	}
	public void setExpectedAmount(int expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public int getNowBalanceByType() {
		return nowBalanceByType;
	}
	public void setNowBalanceByType(int nowBalanceByType) {
		this.nowBalanceByType = nowBalanceByType;
	}
	public int getChallengePk() {
		return challengePk;
	}
	public void setChallengePk(int challengePk) {
		this.challengePk = challengePk;
	}
	public String getChallengeName() {
		return challengeName;
	}
	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChallengeEndDate() {
		return challengeEndDate;
	}
	public void setChallengeEndDate(String challengeEndDate) {
		this.challengeEndDate = challengeEndDate;
	}
	public String getChallengeType() {
		return challengeType;
	}
	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}
	public int getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(int targetAmount) {
		this.targetAmount = targetAmount;
	}
	@Override
	public String toString() {
		return "ChallengeVO [challengePk=" + challengePk + ", challengeName=" + challengeName + ", id=" + id
				+ ", challengeEndDate=" + challengeEndDate + ", challengeType=" + challengeType + ", targetAmount="
				+ targetAmount + ", nowBalanceByType=" + nowBalanceByType + ", expectedAmount=" + expectedAmount
				+ ", challengeFail=" + challengeFail + "]";
	}
	
	
	
	
	
	
	
	
	
}
