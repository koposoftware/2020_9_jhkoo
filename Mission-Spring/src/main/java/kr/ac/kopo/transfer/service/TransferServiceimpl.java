package kr.ac.kopo.transfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.account.dao.SavingsAccountDAO;
import kr.ac.kopo.transfer.dao.TransferDAO;
import kr.ac.kopo.transfer.vo.TransferVO;

@Service
public class TransferServiceimpl implements TransferService {

	@Autowired
	private TransferDAO transferDAO;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;
	
	@Transactional
	@Override
	public void transfer(TransferVO transferVO) {
		
		//  1. 내 계좌에 이체 내역 남기기. 	
		transferDAO.addMyLog(transferVO);
		
		//  2. 상대 계좌에 입금 내역 남기기. 
		transferDAO.addYourLog(transferVO);
		
		//  3. 내 계좌 잔액 조정하기
		transferDAO.withdrawal(transferVO);
		
		//  4. 상대 계좌 잔액 조정하기          내 이름, 상대 이름
		transferDAO.deposit(transferVO);

	}
	
	public String accountOwner(TransferVO transferVO) {
		String toName = transferDAO.accountOwner(transferVO);
		
		return toName;
	}
	
	
	

	@Override
	public void insertAutoTransfer1(TransferVO transferVO) {
		
		transferDAO.insertAutoTransfer1(transferVO);
	}

	@Transactional
	@Override
	public void autoTransfer1() {
		// DAO에서 자동 이체 테이블 정보 가져오기 : autoTransferList
		List<TransferVO> autoTransferList = transferDAO.autoTransferList();
		
		for(TransferVO transferVO:autoTransferList) {
			
			transferVO.setToType("2");	// 내역 타입은 이체:2
			
			//
			//  1. 입출금 계좌에 이체 내역 남기기. 	
			transferDAO.addMyLog(transferVO);
			
			//  2. 적금 계좌에 입금 내역 남기기. 
			transferDAO.addSavingsLog(transferVO);
			
			//  3. 입출금 계좌 잔액 조정하기
			transferDAO.withdrawal(transferVO);
			
			//  4. 적금 계좌 잔액 조정하기          
			 transferDAO.savings(transferVO);
		}
		
	}

	@Transactional
	@Override
	public void transferToSavings(TransferVO transferVO) {
			//  1. 입출금 계좌에 이체 내역 남기기. 	
			transferDAO.addMyLog(transferVO);
				
			//  2. 적금 계좌에 입금 내역 남기기. 
			transferDAO.addSavingsLog(transferVO);
				
			//  3. 입출금 계좌 잔액 조정하기
			transferDAO.withdrawal(transferVO);
				
			//  4. 적금 계좌 잔액 조정하기          
			 transferDAO.savings(transferVO);
	}

	@Transactional
	@Override
	public void autoTransferDelete(String accountNumber) {
		
		// 2. 상태 Y->N 변경
		savingsAccountDAO.changeAutoTransferBool(accountNumber);
		
		// 1. 예약이체 테이블에서 삭제
		transferDAO.autoTransferDelete(accountNumber);
	}
	
	

}
