package kr.ac.kopo.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class MemberVO {

	//Spring form 태그를 이용하여, 백앤드에서 validation체크. 
	
	@Length(min = 4, max= 20, message = "2글자 이상, 20자 이하")
	@Pattern(regexp = "^[a-z0-9]*$", message = "영어 소문자, 숫자로 구성")
	private String id;
	
	
	@Pattern(regexp = "^[가-힣]*$", message = "한글 입력")
	private String name;
	
	@Length(min = 4, max= 20, message = "4글자 이상, 20자 이하")
	private String password;
	
	private String type;

	@Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4}[.-](\\d{4}))$", message = "01x-xxxx-xxxx에 맞게 입력하세요.")
	private String phone;
	
	@Email(message="이메일 포맷에 맞게 입력하세요.")
	private String email;
	
	@NotEmpty(message = "연령대 선택")
	private String ageGroup;
	
	@NotEmpty(message = "성별 선택")
	private String gender;
	
	@NotEmpty(message = "자산 현황 선택")
	private String propertyStatus;
	
	@NotEmpty(message = "직업 분류 선택")
	private String jobKey;

	private String regDate;
	private String lastVisitDate;
	
	private String address;
	
	private String kakaoId;
	private String emailKey;
	
	private int cash;
	
	/////////////////////////////////////////////////////////////
	private int totalBalance;
	private int totalBalanceChange;

	
	
	public int getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(int totalBalance) {
		this.totalBalance = totalBalance;
	}
	public int getTotalBalanceChange() {
		return totalBalanceChange;
	}
	public void setTotalBalanceChange(int totalBalanceChange) {
		this.totalBalanceChange = totalBalanceChange;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	public String getPropertyStatus() {
		return propertyStatus;
	}
	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}
	public String getJobKey() {
		return jobKey;
	}
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
	public String getKakaoId() {
		return kakaoId;
	}
	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	
	
	
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", name=" + name + ", password=" + password + ", type=" + type + ", phone="
				+ phone + ", email=" + email + ", ageGroup=" + ageGroup + ", gender=" + gender + ", propertyStatus="
				+ propertyStatus + ", jobKey=" + jobKey + ", regDate=" + regDate + ", lastVisitDate=" + lastVisitDate
				+ ", address=" + address + ", kakaoId=" + kakaoId + ", emailKey=" + emailKey + ", cash=" + cash
				+ ", totalBalance=" + totalBalance + ", totalBalanceChange=" + totalBalanceChange + "]";
	}
	
	
	

	
	
}
