package kr.ac.kopo.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.SavingsAccountDAO;
import kr.ac.kopo.account.vo.SavingsAccountVO;

@Service
public class SavingsAccountServiceimpl implements SavingsAccountService {

	@Autowired
	private SavingsAccountDAO savingsAccountDAO;
	
	@Override
	public List<SavingsAccountVO> selectSavingsAccountById(String id) {
		
		List<SavingsAccountVO> savingsAccountList = savingsAccountDAO.selectById(id);
		
		return savingsAccountList;
	}

	@Override
	public int savingsTotalBalanceById(String id) {
		int savingsTotalBalanceById = savingsAccountDAO.totalBalanceById(id);
		return savingsTotalBalanceById;
	}

	@Override
	public void insertSavingsAccount(SavingsAccountVO savingsAccountVO) {
		savingsAccountDAO.insert(savingsAccountVO);
	}

	@Override
	public SavingsAccountVO getSavingsAccountInfo(String accountNumber) {
		// 정보 가져오기
		SavingsAccountVO savingsAccountInfo = savingsAccountDAO.getInfo(accountNumber);
		// 남은 개월 수 계산해서 set하기
		int savingMonth = savingsAccountDAO.getSavingMonth(accountNumber);
		savingsAccountInfo.setSavingMonth(savingMonth);
		
		// 평균 입금액 가져오기
		int avgAmount = savingsAccountDAO.getAvgAmount(accountNumber);
		savingsAccountInfo.setAvgAmount(avgAmount);
		
		return savingsAccountInfo;
	}

	// 최초 가입 시 자동이체를 위해 등록 정보 가져옴
	@Override
	public SavingsAccountVO getSavingsAccountInfoForTrans(String accountNumber) {
		SavingsAccountVO savingsAccountInfoForTrans = savingsAccountDAO.getInfoForTrans(accountNumber);
		return savingsAccountInfoForTrans;
	}
	
	
	@Override
	public void changeSavingsManage(SavingsAccountVO savingsAccountVO) {
		savingsAccountDAO.updateSavingsInfo(savingsAccountVO);
		
	}


	@Override
	public void changeBool(SavingsAccountVO savingsAccountVO) {
		savingsAccountDAO.changeBool(savingsAccountVO);
	}

	@Override
	public String getNewAccountNumber() {
		String newAccountNumber = savingsAccountDAO.getNewAccountNumber();
		return newAccountNumber;
	}

	
	
	
}
