package kr.ac.kopo.transfer.service;

import kr.ac.kopo.transfer.vo.TransferVO;

public interface TransferService {

	
	/** 이체
	 *  1. 내 계좌에 이체 내역 남기기
	 *  2. 상대 계좌에 입금 내역 남기기
	 *  3. 내 계좌 잔액 조정하기
	 *  4. 상대 계좌 잔액 조정하기          
	 */
	public void transfer(TransferVO transferVO);
	
	public void transferToSavings(TransferVO transferVO);
	
	/**
	 *  상대 이름 가져오기
	 */
	public String accountOwner(String accountNumber);
	
	
	/**
	 *  예약 이체 신청한 계좌의 정보 업데이트 후, 자동 이체 테이블에 저장
	 */
	public void insertAutoTransfer1(TransferVO transferVO);
	
	/**
	 *  예약 이체(입출금 -> 적금)
	 */
	public void autoTransfer1();
	
	/**
	 *  예약 이체 해지. 예약이체 테이블에서 삭제, 상태 Y->N 변경
	 */
	public void autoTransferDelete(String accountNumber);
	
	/**
	 *  카드 결제하기  
	 *  1. 카드번호로 계좌번호 가져오기 
	 *  2. 결제 내역 남기기
	 *  3. 잔액 조정하기
	 */
	public void payment(TransferVO transferVO);
	
}
