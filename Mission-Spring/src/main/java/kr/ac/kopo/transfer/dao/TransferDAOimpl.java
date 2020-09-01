package kr.ac.kopo.transfer.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.transfer.vo.TransferVO;

@Repository
public class TransferDAOimpl implements TransferDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public String accountOwner(TransferVO transferVO) {
		
		System.out.println(transferVO.getToAccountNumber());
		String tName = sqlSession.selectOne("transfer.dao.TransferDAO.accountOwner",transferVO);
		System.out.println("tName :" + tName );
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

}
