package kr.ac.kopo.transfer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public String accountOwner(String accocuntNumber) {
		String toName = transferDAO.accountOwner(accocuntNumber);
		
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

		
		// 오늘 요일
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
		Date date = new Date();
		String strDate = format.format(date);
		int day = Integer.parseInt(strDate.substring(8));
		
		
		// 오늘 '일'과 예약이체 '일' 이 같으면 이체 수행		
		for(TransferVO transferVO:autoTransferList) { 
			
			// 입출금 -> 적금
			if(transferVO.getAutoTransDay() == day && transferVO.getAccountNumber().length() == 16 && transferVO.getToAccountNumber().length() == 13) {
	
				// 내역 타입은 이체:2
				transferVO.setToType("2");	
			
				//  1. 입출금 계좌에 이체 내역 남기기. 	
				transferDAO.addMyLog(transferVO);
					
				//  2. 적금 계좌에 입금 내역 남기기. 
				transferDAO.addSavingsLog(transferVO);
					
				//  3. 입출금 계좌 잔액 조정하기
				transferDAO.withdrawal(transferVO);
					
				//  4. 적금 계좌 잔액 조정하기          
				 transferDAO.savings(transferVO);
			
			} else {	// 입출금 -> 입출금
				
				//  1. 내 계좌에 이체 내역 남기기. 	
				transferDAO.addMyLog(transferVO);
				
				//  2. 상대 계좌에 입금 내역 남기기. 
				transferDAO.addYourLog(transferVO);
				
				//  3. 내 계좌 잔액 조정하기
				transferDAO.withdrawal(transferVO);
				
				//  4. 상대 계좌 잔액 조정하기          내 이름, 상대 이름
				transferDAO.deposit(transferVO);
			}
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
		
		// 1. 상태 Y->N 변경
		savingsAccountDAO.changeAutoTransferBool(accountNumber);
		
		// 2. 예약이체 테이블에서 삭제
		transferDAO.autoTransferDelete(accountNumber);
	}

	
	
	@Transactional
	@Override
	public void payment(TransferVO transferVO) {
		
		// 1. 카드번호로 계좌번호 가져오기 
		String cardNumber = transferVO.getCardNumber();
		String accountNumber = transferDAO.getAccountNumByCardNum(cardNumber);
		transferVO.setAccountNumber(accountNumber);

		// 2. 결제 내역 남기기
		transferDAO.addMyLog(transferVO);
		
		// 3. 잔액 조정하기
		transferDAO.withdrawal(transferVO);
	}
	
	

}
