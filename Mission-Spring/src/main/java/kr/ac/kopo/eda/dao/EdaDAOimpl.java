package kr.ac.kopo.eda.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.eda.vo.EdaVO;
import kr.ac.kopo.eda.vo.EmailVO;

@Repository
public class EdaDAOimpl implements EdaDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;	
	
	@Override
	public List<EdaVO> amountByType(String accountNumber) {
		List<EdaVO> amountByTypeList = sqlSession.selectList("eda.dao.EdaDAO.amountByType", accountNumber);
		return amountByTypeList;
	}

	
	@Override
	public List<DepositDetailVO> detailListByCotegory(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> detailListByCotegory = sqlSession.selectList("eda.dao.EdaDAO.detailListByCotegory", depositDetailVO);
		return detailListByCotegory;
	}


	@Override
	public List<DepositDetailVO> detailListByCotegory2(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> detailListByCotegory2 = sqlSession.selectList("eda.dao.EdaDAO.detailListByCotegory2", depositDetailVO);
		return detailListByCotegory2;
	}


	@Override
	public List<DepositDetailVO> categorySumList(String accountNumber) {
		List<DepositDetailVO> categorySumList = sqlSession.selectList("eda.dao.EdaDAO.categorySum", accountNumber);
		return categorySumList;
	}


	@Override
	public void addMailService(EmailVO emailVO) {
		sqlSession.delete("eda.dao.EdaDAO.addMailService", emailVO);
	}


	@Override
	public void deleteMailService(String id) {
		sqlSession.insert("eda.dao.EdaDAO.deleteMailService", id);
	}


	@Override
	public List<EmailVO> getMailList() {
		List<EmailVO> emailList = sqlSession.selectList("eda.dao.EdaDAO.getMailList");
		return emailList;
	}


	@Override
	public int mailServiceBool(String id) {
		int bool = sqlSession.selectOne("eda.dao.EdaDAO.mailServiceBool",id);
		return bool;
	}


	@Override
	public int depositByMonth1(String accountNumber) {
		int depositByMonth1 = sqlSession.selectOne("account.dao.DepositDetailDAO.depositByMonth1", accountNumber);
		return depositByMonth1;
	}
	@Override
	public int depositByMonth2(String accountNumber) {
		int depositByMonth2 = sqlSession.selectOne("account.dao.DepositDetailDAO.depositByMonth2", accountNumber);
		return depositByMonth2;
	}
	@Override
	public int depositByMonth3(String accountNumber) {
		int depositByMonth3 = sqlSession.selectOne("account.dao.DepositDetailDAO.depositByMonth3", accountNumber);
		return depositByMonth3;
	}
	@Override
	public int depositByMonth4(String accountNumber) {
		int depositByMonth4 = sqlSession.selectOne("account.dao.DepositDetailDAO.depositByMonth4", accountNumber);
		return depositByMonth4;
	}
	@Override
	public int withdrawByMonth1(String accountNumber) {
		int withdrawByMonth1 = sqlSession.selectOne("account.dao.DepositDetailDAO.withdrawByMonth1", accountNumber);
		return withdrawByMonth1;
	}
	@Override
	public int withdrawByMonth2(String accountNumber) {
		int withdrawByMonth2 = sqlSession.selectOne("account.dao.DepositDetailDAO.withdrawByMonth2", accountNumber);
		return withdrawByMonth2;
	}
	@Override
	public int withdrawByMonth3(String accountNumber) {
		int withdrawByMonth3 = sqlSession.selectOne("account.dao.DepositDetailDAO.withdrawByMonth3", accountNumber);
		return withdrawByMonth3;
	}
	@Override
	public int withdrawByMonth4(String accountNumber) {
		int withdrawByMonth4 = sqlSession.selectOne("account.dao.DepositDetailDAO.withdrawByMonth4", accountNumber);
		return withdrawByMonth4;
	}
	
	
	
}
