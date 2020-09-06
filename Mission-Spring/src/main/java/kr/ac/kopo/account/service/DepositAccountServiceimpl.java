package kr.ac.kopo.account.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.DepositAccountDAO;
import kr.ac.kopo.account.dao.DepositDetailDAO;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.challenge.vo.ChallengeVO;


@Service
public class DepositAccountServiceimpl implements DepositAccountService {

	@Autowired
	private DepositAccountDAO depositAccountDAO;
	@Autowired
	private DepositDetailDAO depositDetailDAO;
	
	@Override
	public List<DepositAccountVO> selectDepositAccountById(String id) {
		
		List<DepositAccountVO> depositAccountList = depositAccountDAO.selectById(id);
		
		return depositAccountList;
	}

	@Override
	public int depositTotalBalanceById(String id) {
		int depositTotalBalanceById = depositAccountDAO.totalBalanceById(id);
		return depositTotalBalanceById;
	}

	@Override
	public List<String> depositAccountNumListById(String id) {
		List<String> depositAccountNumListById = depositAccountDAO.accountNumListById(id);
		return depositAccountNumListById;
	}

	@Override
	public void insertDepositAccount(DepositAccountVO depositAccountVO) {
		depositAccountDAO.insert(depositAccountVO);
		
	}

	@Override
	public DepositAccountVO getDepositAccountInfo(String accountNumber) {
		DepositAccountVO getDepositAccountInfo = depositAccountDAO.getInfo(accountNumber);
		return getDepositAccountInfo;
	}

	@Override
	public void changeDepositManage(DepositAccountVO depositAccountVO) {
		
		// 1. 만약 사용자가 메인계좌를 선택하였따면, 해당 사용자의 모든 계좌 상태를 sub으로 만듬
		if(depositAccountVO.getMainAccount().equals("Y")) {
			depositAccountDAO.mainToSub(depositAccountVO.getId());	
		}
		// 2. 정보 수정
		depositAccountDAO.updateInfo(depositAccountVO);
	}

	
	
	@Override
	public String getMainAccountNumber(String id) {
		String mainAccountNumber = depositAccountDAO.getMainAccountNumber(id);
		return mainAccountNumber;
	}

	@Override
	public void deleteDepositAccount(String accountNumber) {
		depositAccountDAO.deleteDepositAccount(accountNumber);
		
	}

	@Override
	public int lastMonthSumByCategory(ChallengeVO challengeVO) {
		// challengeVO에서 받아온 아이디로 메인 계좌번호 가져오기
		String id = challengeVO.getId();
		String mainAccountNumber = depositAccountDAO.getMainAccountNumber(id);
		
		//가져온 메인계좌번호와 challengeVO에서 받아온 카테고리 type으로 지난달 총 지출액 구하기
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		depositDetailVO.setAccountNumber(mainAccountNumber);
		depositDetailVO.setLogTypeKey(challengeVO.getChallengeType());
		
		int lastMonthSumByCategory = depositDetailDAO.lastMonthSumByCategory(depositDetailVO);
		
		return lastMonthSumByCategory;
	}
	
	
	
	
}
