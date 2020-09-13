package kr.ac.kopo.eda.vo;

public class EdaVO {

	private String category;
	private String logTypeKey;
	private int totalAmountByType;
	private int totalThisMonth;
	
	
	public String getLogTypeKey() {
		return logTypeKey;
	}
	public void setLogTypeKey(String logTypeKey) {
		this.logTypeKey = logTypeKey;
	}
	public int getTotalThisMonth() {
		return totalThisMonth;
	}
	public void setTotalThisMonth(int totalThisMonth) {
		this.totalThisMonth = totalThisMonth;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getTotalAmountByType() {
		return totalAmountByType;
	}
	public void setTotalAmountByType(int totalAmountByType) {
		this.totalAmountByType = totalAmountByType;
	}
	@Override
	public String toString() {
		return "EdaVO [category=" + category + ", logTypeKey=" + logTypeKey + ", totalAmountByType=" + totalAmountByType
				+ ", totalThisMonth=" + totalThisMonth + "]";
	}
	
	
	
	
	
	
}
