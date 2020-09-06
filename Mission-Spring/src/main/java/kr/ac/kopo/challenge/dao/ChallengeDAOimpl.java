package kr.ac.kopo.challenge.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.challenge.vo.ChallengeCntVO;
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

	@Override		//이거 오류!!!!
	public String ageGroupDepositAccount(String id) {
		List<ChallengeCntVO> challengeCntAccountList = sqlSession.selectList("challenge.dao.ChallengeDAO.ageGroupDepositAccount", id);
		ChallengeCntVO ageGroupDepositAccount = challengeCntAccountList.get(0);	// 많이 가입한 순으로 정렬되어있기 때문에 첫번째 가져옴
		String ageGroupDepositAccountType = ageGroupDepositAccount.getBank_book_key();
		
		return ageGroupDepositAccountType;
	}
	
	
}
