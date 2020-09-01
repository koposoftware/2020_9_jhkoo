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
	
	
}
