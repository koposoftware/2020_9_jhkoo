package kr.ac.kopo.friend.service;

import java.util.List;

import kr.ac.kopo.friend.vo.FriendVO;

public interface FriendService {

	/**
	 *  요청 보내기 (테이블에 추가)
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
	public void disAgreeREquest(FriendVO friendVO);
	
	/**
	 *  나의 요청이 승인된 친구들의 id, 내가 요청을 승락한 친구들의 id 가져오기
	 */
	public List<String> getFriendsIdList(String id);
	
	/**
	 *   나의 요청이 승인된 친구들의 name, 내가 요청을 승락한 친구들의 name 가져오기
	 */
	public List<String> getFriendsNameList(String id);
	
	/**
	 *  매월 1일 도전 초기화
	 */
	public void resetChallenge();
}
