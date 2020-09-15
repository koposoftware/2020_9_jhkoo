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

	
}
