package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.SavingsAccountVO;

@Repository
public class SavingsAccountDAOimpl implements SavingsAccountDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<SavingsAccountVO> selectById(String id) {
		
		List<SavingsAccountVO> savingsAccountList = sqlSession.selectList("account.dao.SavingsAccountDAO.selectById", id);
		
		return savingsAccountList;
	}

	@Override
	public int totalBalanceById(String id) {
		
		int savingsTotalBalanceById = sqlSession.selectOne("account.dao.SavingsAccountDAO.totalBalanceById",id);
		
		return savingsTotalBalanceById;
	}

	@Override
	public void insert(SavingsAccountVO savingsAccountVO) {
		System.out.println("DAO : " + savingsAccountVO);
		sqlSession.insert("account.dao.SavingsAccountDAO.insert", savingsAccountVO);
	}

	@Override
	public SavingsAccountVO getInfo(String accountNumber) {
		SavingsAccountVO savingsAccount = sqlSession.selectOne("account.dao.SavingsAccountDAO.getInfo", accountNumber);
		return savingsAccount;
	}

	@Override
	public SavingsAccountVO getInfoForTrans(String accountNumber) {
		SavingsAccountVO savingsAccountForTrans = sqlSession.selectOne("account.dao.SavingsAccountDAO.getInfoForTrans", accountNumber);
		return savingsAccountForTrans;
	}

	@Override
	public void updateSavingsInfo(SavingsAccountVO savingsAccountVO) {
		sqlSession.update("account.dao.SavingsAccountDAO.updateInfo",savingsAccountVO);
	
	}

	@Override
	public int getSavingMonth(String accountNumber) {
		int savingMonth = sqlSession.selectOne("account.dao.SavingsAccountDAO.getSavingMonth", accountNumber);
		return savingMonth;
	}

	@Override
	public int getAvgAmount(String accountNumber) {
		int avgAmount = sqlSession.selectOne("account.dao.SavingsAccountDAO.avgAmount", accountNumber);
		return avgAmount;
	}

	@Override
	public void changeBool(SavingsAccountVO savingsAccountVO) {
		sqlSession.update("account.dao.SavingsAccountDAO.changeBool", savingsAccountVO);
		
	}

	@Override
	public String getNewAccountNumber() {
		String newAccountNumbser = sqlSession.selectOne("account.dao.SavingsAccountDAO.getNewAccountNumber");
		return newAccountNumbser;
	}

	@Override
	public void changeAutoTransferBool(String accountNumber) {
		sqlSession.update("account.dao.SavingsAccountDAO.changeAutoTransferBool", accountNumber);
	}
	
	
	
	
}
