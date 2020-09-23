package kr.ac.kopo.eda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.DepositAccountDAO;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.eda.dao.EdaDAO;
import kr.ac.kopo.eda.vo.EdaVO;
import kr.ac.kopo.eda.vo.EmailVO;

@Service
public class EdaServiceimpl implements EdaService {

	@Autowired
	private EdaDAO edaDAO;
	@Autowired
	private DepositAccountDAO depositAccountDAO;
	
	
	
	@Override
	public List<EdaVO> amountByType(String id) {
		/**
		 *  이번 달 카테고리별 지출 퍼센트
		 *  1. 아이디로 메인 계좌번호 가져오기
		 *  2. 가져온 계좌번호로 카테고리별 지출액 list 담기
		 *  3. 총 합 구하기
		 */
		// 1. 아이디로 메인 계좌번호 가져오기
		String accountNumber = depositAccountDAO.getMainAccountNumber(id);
		
		// 2. 가져온 계좌번호로 카테고리별 지출액 list 담기
		List<EdaVO> amountByType = edaDAO.amountByType(accountNumber);
		
		// 3. 이번 달 지출 총합 VO에 담기
		int totalThisMonth = 0;
		for(EdaVO edaVO:amountByType) {
			totalThisMonth += edaVO.getTotalAmountByType();
		}
		for(EdaVO edaVO:amountByType) {
			edaVO.setTotalThisMonth(totalThisMonth);
		}
		
		return amountByType;
	}

	
	
	@Override
	public List<DepositDetailVO> detailListByCotegory(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> detailListByCotegory = edaDAO.detailListByCotegory(depositDetailVO);
		return detailListByCotegory;
	}



	@Override
	public List<DepositDetailVO> detailListByCotegory2(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> detailListByCotegory2 = edaDAO.detailListByCotegory2(depositDetailVO);
		return detailListByCotegory2;
	}



	@Override
	public String biggestCategory(String accountNumber) {
		List<DepositDetailVO> categorySumList = edaDAO.categorySumList(accountNumber);
		String biggestCategory = categorySumList.get(0).getLogTypeKey();
		return biggestCategory;
	}



	@Override
	public void addMailService(EmailVO emailVO) {
		edaDAO.addMailService(emailVO);
	}



	@Override
	public void deleteMailService(String id) {
		edaDAO.deleteMailService(id);
	}



	@Override
	public List<EmailVO> getMailList() {
		List<EmailVO> emailList = edaDAO.getMailList();
		return emailList;
	}



	@Override
	public int mailServiceBool(String id) {
		int bool = edaDAO.mailServiceBool(id);
		return bool;
	}



	@Override
	public List<Integer> depositByLast3Month(String accountNumber) {
		int depositByMonth1 = edaDAO.depositByMonth1(accountNumber);
		int depositByMonth2 = edaDAO.depositByMonth2(accountNumber);
		int depositByMonth3 = edaDAO.depositByMonth3(accountNumber);
		int depositByMonth4 = edaDAO.depositByMonth4(accountNumber);
		
		List<Integer> depositByLast3Month = new ArrayList<Integer>();
		depositByLast3Month.add(depositByMonth1);
		depositByLast3Month.add(depositByMonth2);
		depositByLast3Month.add(depositByMonth3);
		depositByLast3Month.add(depositByMonth4);
		
		return depositByLast3Month;
	}
	@Override
	public List<Integer> withdrawByLast3Month(String accountNumber) {
		int withdrawByMonth1 = edaDAO.withdrawByMonth1(accountNumber);
		int withdrawByMonth2 = edaDAO.withdrawByMonth2(accountNumber);
		int withdrawByMonth3 = edaDAO.withdrawByMonth3(accountNumber);
		int withdrawByMonth4 = edaDAO.withdrawByMonth4(accountNumber);
		
		List<Integer> withdrawByLast3Month = new ArrayList<Integer>();
		withdrawByLast3Month.add(withdrawByMonth1);
		withdrawByLast3Month.add(withdrawByMonth2);
		withdrawByLast3Month.add(withdrawByMonth3);
		withdrawByLast3Month.add(withdrawByMonth4);
		
		return withdrawByLast3Month;
	}

	


	
}
