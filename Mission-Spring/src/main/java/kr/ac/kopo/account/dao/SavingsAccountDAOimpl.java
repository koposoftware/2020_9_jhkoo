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
		sqlSession.insert("account.dao.SavingsAccountDAO.insert", savingsAccountVO);
	}
	
	
}
