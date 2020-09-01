package kr.ac.kopo.transfer.dao;

import kr.ac.kopo.transfer.vo.TransferVO;

public interface TransferDAO {

	/**
	 *  이체 보낼 상대의 이름 
	 */
	public String accountOwner(TransferVO transferVO);
	
	
	/**
	 *  계좌에 이체 내역 남기기
	 */
	public void addMyLog(TransferVO transferVO);
	
	
	/**
	 *  상대 계좌에 이체 내역 남기기
	 */
	public void addYourLog(TransferVO transferVO);
	
	
	/**
	 *  내 계좌 잔액에서 출금하기
	 */
	public void withdrawal(TransferVO transferVO);
	
	
	/**
	 *  상대 계좌 잔액에 입금하기
	 */
	public void deposit(TransferVO transferVO);
	
	
}
