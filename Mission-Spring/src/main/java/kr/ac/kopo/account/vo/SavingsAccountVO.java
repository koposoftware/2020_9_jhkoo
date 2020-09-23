package kr.ac.kopo.account.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class SavingsAccountVO {

	private String accountNumber;
	
	private String id;

	@Length(min = 4, max=4, message = "4자리 '숫자'만 입력 가능합니다")	
	@Pattern(regexp = "^[0-9]*$")
	private String accountPassword;
	
	@Max(300000)
	private int balance;
	
	private String bankBookKey;
	
	@Pattern(regexp = "^[A-Za-z0-9가-힣]*$", message = "특수기호는 사용할 수 없습니다.")
	private String nickName;
	
	private String regDate;
	
	private int savingDay;			// 지불할 일(ex 매월 15일...)
	private String savingDate;		// 납부 최종 일 -> 현재 + savingMonth
	private double rate;			// 금리
	
	private String autoSaving;		// 이체할 계좌. 곧 자동 이체할 계좌
	
	private int savingMonth;		// 6, 12, 24 개월 (db에 없음)
	
	private String autoSavingBool;
	
	private int avgAmount;		// db에 없음
	
	private String certification;
	
	
	
	
	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public int getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(int avgAmount) {
		this.avgAmount = avgAmount;
	}

	public String getAutoSavingBool() {
		return autoSavingBool;
	}

	public void setAutoSavingBool(String autoSavingBool) {
		this.autoSavingBool = autoSavingBool;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getBankBookKey() {
		return bankBookKey;
	}

	public void setBankBookKey(String bankBookKey) {
		this.bankBookKey = bankBookKey;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getSavingDay() {
		return savingDay;
	}

	public void setSavingDay(int savingDay) {
		this.savingDay = savingDay;
	}

	public String getSavingDate() {
		return savingDate;
	}

	public void setSavingDate(String savingDate) {
		this.savingDate = savingDate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getAutoSaving() {
		return autoSaving;
	}

	public void setAutoSaving(String autoSaving) {
		this.autoSaving = autoSaving;
	}

	public int getSavingMonth() {
		return savingMonth;
	}

	public void setSavingMonth(int savingMonth) {
		this.savingMonth = savingMonth;
	}

	@Override
	public String toString() {
		return "SavingsAccountVO [accountNumber=" + accountNumber + ", id=" + id + ", accountPassword="
				+ accountPassword + ", balance=" + balance + ", bankBookKey=" + bankBookKey + ", nickName=" + nickName
				+ ", regDate=" + regDate + ", savingDay=" + savingDay + ", savingDate=" + savingDate + ", rate=" + rate
				+ ", autoSaving=" + autoSaving + ", savingMonth=" + savingMonth + ", autoSavingBool=" + autoSavingBool
				+ ", avgAmount=" + avgAmount + "]";
	}

	

	

	
	
	
	
}
