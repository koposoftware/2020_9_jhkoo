package kr.ac.kopo.account.vo;

public class DepositDetailVO {
	
	private String accountNumber;
	private String logDate;
	private int amount;
	private String logTypeKey;
	private String toAccountNumber;
	private String toName;
	private int count;
	private int sumAmount;
	private int week;
	
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(int sumAmount) {
		this.sumAmount = sumAmount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public String getLogTypeKey() {
		return logTypeKey;
	}
	public void setLogTypeKey(String logTypeKey) {
		this.logTypeKey = logTypeKey;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	@Override
	public String toString() {
		return "DepositDetailVO [accountNumber=" + accountNumber + ", logDate=" + logDate + ", amount=" + amount
				+ ", logTypeKey=" + logTypeKey + ", toAccountNumber=" + toAccountNumber + ", toName=" + toName
				+ ", count=" + count + ", sumAmount=" + sumAmount + ", week=" + week + "]";
	}
	
	
	
	
	
	
	
	
}
