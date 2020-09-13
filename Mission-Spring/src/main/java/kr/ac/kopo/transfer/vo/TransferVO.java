package kr.ac.kopo.transfer.vo;



import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class TransferVO {

	
	private String accountNumber;
	
	@Length(min = 4, max=4, message = "비밀번호 정확히 입력하세요")	
	private String accountPassword;	// 이체 테이블엔 없음. 확인하기 위한 용도
	
	private String toName;
	
	@Pattern(regexp = "^\\d{3}-\\d{6}-\\d{5}$", message = "xxx-xxxxxx-xxxxx 형태로 입력하세요")
	private String toAccountNumber;
	
	@Min(1)
	private int toAmount;
	private String toType;
	private String toDate;
	
	private String myName;	//이체 테이블에는 없음. 
	private String cardNumber; //이체 테이블에는 없음.

	private int autoTransDay;

	
	
	public int getAutoTransDay() {
		return autoTransDay;
	}

	public void setAutoTransDay(int autoTransDay) {
		this.autoTransDay = autoTransDay;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public int getToAmount() {
		return toAmount;
	}

	public void setToAmount(int toAmount) {
		this.toAmount = toAmount;
	}

	public String getToType() {
		return toType;
	}

	public void setToType(String toType) {
		this.toType = toType;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "TransferVO [accountNumber=" + accountNumber + ", accountPassword=" + accountPassword + ", toName="
				+ toName + ", toAccountNumber=" + toAccountNumber + ", toAmount=" + toAmount + ", toType=" + toType
				+ ", toDate=" + toDate + ", myName=" + myName + ", cardNumber=" + cardNumber + ", autoTransDay="
				+ autoTransDay + "]";
	}

	

		
}
