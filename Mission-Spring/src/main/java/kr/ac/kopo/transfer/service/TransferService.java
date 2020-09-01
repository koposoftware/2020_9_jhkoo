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
	
	/**
	 *  상대 이름 가져오기
	 */
	public String accountOwner(TransferVO transferVO);
}
