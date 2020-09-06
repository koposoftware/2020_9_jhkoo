package kr.ac.kopo.account.vo;

public class SavingsDetailVO {

	private int logPk;
	private String accountNumber;
	private String logDate;
	private int amount;
	
	
	public int getLogPk() {
		return logPk;
	}
	public void setLogPk(int logPk) {
		this.logPk = logPk;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "SavingsDetailVO [logPk=" + logPk + ", accountNumber=" + accountNumber + ", logDate=" + logDate
				+ ", amount=" + amount + "]";
	}
	
	
	
	
}
