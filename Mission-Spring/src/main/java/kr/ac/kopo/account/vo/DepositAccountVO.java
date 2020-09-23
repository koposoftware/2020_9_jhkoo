package kr.ac.kopo.account.vo;

public class DepositAccountVO {
	
	private String accountNumber;
	private String id;
	private String accountPassword;
	private int balance;
	private String bankBookKey;
	private String nickName;
	private String regDate;
	private String mainAccount;
	private String certification;
	
	
	
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
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
	public String getMainAccount() {
		return mainAccount;
	}
	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}
	@Override
	public String toString() {
		return "DepositAccountVO [accountNumber=" + accountNumber + ", id=" + id + ", accountPassword="
				+ accountPassword + ", balance=" + balance + ", bankBookKey=" + bankBookKey + ", nickName=" + nickName
				+ ", regDate=" + regDate + ", mainAccount=" + mainAccount + ", certification=" + certification + "]";
	}
	
	
		
}
