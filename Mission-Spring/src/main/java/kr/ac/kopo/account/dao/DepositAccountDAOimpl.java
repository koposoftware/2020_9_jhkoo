package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.DepositAccountVO;

@Repository
public class DepositAccountDAOimpl implements DepositAccountDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<DepositAccountVO> selectById(String id) {
		
		List<DepositAccountVO> depositAccountList = sqlSession.selectList("account.dao.DepositAccountDAO.selectById", id);
		
		return depositAccountList;
	}

	@Override
	public int totalBalanceById(String id) {
		
		int depositTotalBalanceById = sqlSession.selectOne("account.dao.DepositAccountDAO.totalBalanceById",id);
		return depositTotalBalanceById;
	}

	@Override
	public List<String> accountNumListById(String id) {
		List<String> accountNumList = sqlSession.selectList("account.dao.DepositAccountDAO.accountNumListById", id);
		return accountNumList;
	}

	@Override
	public void insert(DepositAccountVO depositAccountVO) {
		sqlSession.insert("account.dao.DepositAccountDAO.insert", depositAccountVO);
	}

	@Override
	public DepositAccountVO getInfo(String accountNumber) {
		DepositAccountVO depositAccount = sqlSession.selectOne("account.dao.DepositAccountDAO.getInfo", accountNumber);
		return depositAccount;
	}


	@Override
	public void mainToSub(String id) {
		sqlSession.update("account.dao.DepositAccountDAO.mainToSub", id);
	}

	@Override
	public void updateInfo(DepositAccountVO depositAccountVO) {
		sqlSession.update("account.dao.DepositAccountDAO.update", depositAccountVO);
	}

	@Override
	public String getMainAccountNumber(String id) {
		String mainAccountNumber = sqlSession.selectOne("account.dao.DepositAccountDAO.getMainAccountNumber", id);
		return mainAccountNumber;
	}

	@Override
	public void deleteDepositAccount(String accountNumber) {
		sqlSession.delete("account.dao.DepositAccountDAO.deleteDepositAccount", accountNumber);
	}

	
	
	
}
