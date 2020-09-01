package kr.ac.kopo.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.transfer.dao.TransferDAO;
import kr.ac.kopo.transfer.vo.TransferVO;

@Service
public class TransferServiceimpl implements TransferService {

	@Autowired
	private TransferDAO transferDAO;
	
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

}
