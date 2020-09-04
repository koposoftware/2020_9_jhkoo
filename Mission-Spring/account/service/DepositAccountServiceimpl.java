package kr.ac.kopo.account.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.DepositAccountDAO;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.transfer.vo.TransferVO;

@Service
public class DepositAccountServiceimpl implements DepositAccountService {

	@Autowired
	private DepositAccountDAO depositAccountDAO;
	
	@Override
	public List<DepositAccountVO> selectDepositAccountById(String id) {
		
		List<DepositAccountVO> depositAccountList = depositAccountDAO.selectById(id);
		
		return depositAccountList;
	}

	@Override
	public int depositTotalBalanceById(String id) {
		int depositTotalBalanceById = depositAccountDAO.totalBalanceById(id);
		return depositTotalBalanceById;
	}

	@Override
	public List<String> depositAccountNumListById(String id) {
		List<String> depositAccountNumListById = depositAccountDAO.accountNumListById(id);
		return depositAccountNumListById;
	}

	@Override
	public void insertDepositAccount(DepositAccountVO depositAccountVO) {
		depositAccountDAO.insert(depositAccountVO);
		
	}

	@Override
	public DepositAccountVO getDepositAccountInfo(String accountNumber) {
		DepositAccountVO getDepositAccountInfo = depositAccountDAO.getInfo(accountNumber);
		return getDepositAccountInfo;
	}

	@Override
	public void changeDepositManage(DepositAccountVO depositAccountVO) {
		
		// 1. 만약 사용자가 메인계좌를 선택하였따면, 해당 사용자의 모든 계좌 상태를 sub으로 만듬
		if(depositAccountVO.getMainAccount().equals("Y")) {
			depositAccountDAO.mainToSub(depositAccountVO.getId());	
		}
		// 2. 정보 수정
		depositAccountDAO.updateInfo(depositAccountVO);
	}

	
	
	@Override
	public String getMainAccountNumber(String id) {
		String mainAccountNumber = depositAccountDAO.getMainAccountNumber(id);
		return mainAccountNumber;
	}

	@Override
	public void deleteDepositAccount(String accountNumber) {
		depositAccountDAO.deleteDepositAccount(accountNumber);
		
	}
	
	
	
	
}
