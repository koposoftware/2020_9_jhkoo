package kr.ac.kopo.transfer.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.transfer.vo.TransferVO;

@Repository
public class TransferDAOimpl implements TransferDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public String accountOwner(String accountNumber) {
		
		String tName = sqlSession.selectOne("transfer.dao.TransferDAO.accountOwner",accountNumber);	
		return tName;
	}

	@Override
	public void addMyLog(TransferVO transferVO) {
		sqlSession.insert("transfer.dao.TransferDAO.addMyLog", transferVO);
	}

	@Override
	public void addYourLog(TransferVO transferVO) {
		sqlSession.insert("transfer.dao.TransferDAO.addYourLog", transferVO);
	}

	@Override
	public void withdrawal(TransferVO transferVO) {
		sqlSession.update("transfer.dao.TransferDAO.withdrawal", transferVO);
	}

	@Override
	public void deposit(TransferVO transferVO) {
		sqlSession.update("transfer.dao.TransferDAO.deposit", transferVO);
	}

	@Override
	public void insertAutoTransfer1(TransferVO transferVO) {
		sqlSession.insert("transfer.dao.TransferDAO.autoTransfer1", transferVO);
	}

	@Override
	public List<TransferVO> autoTransferList() {
		List<TransferVO> autoTransferList = sqlSession.selectList("transfer.dao.TransferDAO.autoTransferList"); 
		
		return autoTransferList;
	}

	@Override
	public void addSavingsLog(TransferVO transferVO) {
		sqlSession.insert("transfer.dao.TransferDAO.addSavingsLog", transferVO);
	}

	@Override
	public void savings(TransferVO transferVO) {
		sqlSession.update("transfer.dao.TransferDAO.savings", transferVO);
	}

	@Override
	public void autoTransferDelete(String accountNumber) {
		sqlSession.delete("transfer.dao.TransferDAO.autoTransferDelete", accountNumber);
		
	}

	@Override
	public String getAccountNumByCardNum(String cardNumber) {
		String accountNumber = sqlSession.selectOne("transfer.dao.TransferDAO.getAccountNumByCardNum", cardNumber);
		return accountNumber;
	}

	
	
	
}
