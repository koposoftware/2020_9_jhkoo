package kr.ac.kopo.transfer.dao;

import java.util.List;

import kr.ac.kopo.transfer.vo.TransferVO;

public interface TransferDAO {

	/**
	 *  이체 보낼 상대의 이름 
	 */
	public String accountOwner(String accountNumber);
	
	
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
	
	
	/**
	 *  DB에 자동이체 정보 insert
	 */
	public void insertAutoTransfer1(TransferVO transferVO);
	
	/**
	 *  예약이체 테이블 정보 가져오기
	 */
	public List<TransferVO> autoTransferList();
	
	
	/**
	 * 적금계좌 입금 내역 남기기
	 */
	public void addSavingsLog(TransferVO transferVO);
	
	/**
	 * 적금 계좌 잔액 조정하기
	 */
	public void savings(TransferVO transferVO);
	
	
	/**
	 *  예약 이체 해지하기
	 */
	public void autoTransferDelete(String accountNumber);
	
	/**
	 *  카드번호로 계좌번호 가져오기
	 */
	public String getAccountNumByCardNum(String cardNumber);
}
