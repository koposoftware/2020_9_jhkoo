package kr.ac.kopo.favorite.vo;

public class FavoriteVO {

	private String toAccountNumber;
	private String toName;
	private String favoriteFlag;
	private String id;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
	
	
	public String getFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(String favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	@Override
	public String toString() {
		return "FavoriteVO [toAccountNumber=" + toAccountNumber + ", toName=" + toName + ", favoriteFlag="
				+ favoriteFlag + ", id=" + id + "]";
	}
	
	
	
	
	
}
