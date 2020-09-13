package kr.ac.kopo.challenge.vo;

public class RecommendVO {

	private String bankBookKey;
	private int cnt;
	private String ageGroup;
	private String jobKeyName;
	
	
	

	public String getJobKeyName() {
		return jobKeyName;
	}
	public void setJobKeyName(String jobKeyName) {
		this.jobKeyName = jobKeyName;
	}
	public String getBankBookKey() {
		return bankBookKey;
	}
	public void setBankBookKey(String bankBookKey) {
		this.bankBookKey = bankBookKey;
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
		return "RecommendVO [bankBookKey=" + bankBookKey + ", cnt=" + cnt + ", ageGroup=" + ageGroup + ", jobKeyName="
				+ jobKeyName + "]";
	}
	
	
	

}
