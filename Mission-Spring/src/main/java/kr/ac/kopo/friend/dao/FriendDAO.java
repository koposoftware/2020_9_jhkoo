package kr.ac.kopo.friend.dao;

import java.util.List;

import kr.ac.kopo.friend.vo.FriendVO;

public interface FriendDAO {

	/**
	 *  친구 테이블 목록에 추가
	 */
	public void insertRequest(FriendVO friendVO);
	
	/**
	 *  내가 보낸 요청 가져오기
	 */
	public List<FriendVO> getRequestList(String id);
	
	/**
	 *  내가 받은 요청 가져오기
	 */
	public List<FriendVO> getRequestedList(String id);
	
	/**
	 *  친구가 보낸 요청 승인하기
	 */
	public void agreeRequest(FriendVO friendVO);
	
	/**
	 *  친구가 보낸 요청 거절하기
	 */
	public void disAgreeRequest(FriendVO friendVO);
	
	/**
	 *  내가 보낸 요청 중 승인된 친구 아이디
	 */
	public List<String> getFriendsIdList1(String id);
	
	/**
	 * 내가 받은 요청 중 승인한 친구 아이디
	 */
	public List<String> getFriendsIdList2(String id);
	
	/**
	 *  내가 보낸 요청 중 승인된 친구 이름
	 */
	public List<String> getFriendsNameList1(String id);
	
	/**
	 * 내가 받은 요청 중 승인한 친구 이름
	 */
	public List<String> getFriendsNameList2(String id);
	
	/**
	 * 매월 1일 도전 초기화
	 */
	public void resetChallenge();
}
