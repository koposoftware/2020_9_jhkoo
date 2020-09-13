package kr.ac.kopo.challenge.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.challenge.vo.RecommendVO;
import kr.ac.kopo.challenge.vo.ChallengeVO;

@Repository
public class ChallengeDAOimpl implements ChallengeDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ChallengeVO> myChallenge(String id) {
		List<ChallengeVO> myChallenge = sqlSession.selectList("challenge.dao.ChallengeDAO.myChallenge", id);
		return myChallenge;
	}

	@Override
	public void insertMyChallenge(ChallengeVO challengeVO) {
		System.out.println(challengeVO);
		sqlSession.insert("challenge.dao.ChallengeDAO.insertMyChallenge", challengeVO);
	}

	@Override
	public void challengeDelete() {
		sqlSession.delete("challenge.dao.ChallengeDAO.challengeDelete");
	}

	@Override		
	public String ageGroupDepositAccount(String myAgeGroup) {
		List<RecommendVO> ageGroupDepositAccountList = sqlSession.selectList("recommend.dao.recommendDAO.ageGroupDepositAccount", myAgeGroup);
		
		// 많이 가입한 순으로 정렬되어있기 때문에 첫번째 가져옴
		RecommendVO ageGroupDepositAccount = ageGroupDepositAccountList.get(0);	
		String bestBankBook = ageGroupDepositAccount.getBankBookKey();
		
		return bestBankBook;
	}

	@Override
	public String jobSavingsAccount(String myJob) {
		
		List<RecommendVO> jobSavingsAccountList = sqlSession.selectList("recommend.dao.recommendDAO.jobSavingsAccount", myJob);
		
		// 가장 많이 가입한 순으로 정렬되어 있으므로 첫번째 가져옴
		RecommendVO jobSavingsAccount = jobSavingsAccountList.get(0);
		String bestBankBook = jobSavingsAccount.getBankBookKey();
		
		return bestBankBook;
	}
	
	
	
}
