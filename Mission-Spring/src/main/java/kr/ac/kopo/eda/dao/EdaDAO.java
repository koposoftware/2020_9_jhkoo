package kr.ac.kopo.eda.dao;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.eda.vo.EdaVO;
import kr.ac.kopo.eda.vo.EmailVO;

public interface EdaDAO {

	// 이번달 카테고리별 지출액 리스트
	public List<EdaVO> amountByType(String accountNumber);
	
	// 해당 카테고리의 이번달 내역
	public List<DepositDetailVO> detailListByCotegory(DepositDetailVO depositDetailVO);
	
	// 해당 카테고리의 이번달 + 저번달 내역
		public List<DepositDetailVO> detailListByCotegory2(DepositDetailVO depositDetailVO);
		
	
	// 이번 달 카테고리별 지출 합
	public List<DepositDetailVO> categorySumList(String accountNumber);
	
	// 정기 이메일 서비스 insert
	public void addMailService(EmailVO emailVO);
	
	// 정기 이메일 서비스 취소
	public void deleteMailService(String id);
	
	// 정기 이메일 리스트 
	public List<EmailVO> getMailList();
	
	// 정기 구독 여부 판단
	public int mailServiceBool(String id);
	
	// 이번달, 저번달, 저저번달 수입
	public int depositByMonth1(String accountNumber);
	public int depositByMonth2(String accountNumber);
	public int depositByMonth3(String accountNumber);
	public int depositByMonth4(String accountNumber);
	
	// 이번달, 저번달, 저저번달 지출
	public int withdrawByMonth1(String accountNumber);
	public int withdrawByMonth2(String accountNumber);
	public int withdrawByMonth3(String accountNumber);
	public int withdrawByMonth4(String accountNumber);
}
