package kr.ac.kopo.favorite.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.ac.kopo.favorite.vo.FavoriteVO;

public interface FavoriteService {

	/**
	 *  최근 이체 목록 가져오기
	 */
	public List<FavoriteVO> getRecentList(String id);
	
	/**
	 *  즐겨찾기 목록에 추가
	 */
	public void addFavorite(FavoriteVO favoriteVO);
	
	/**
	 * 즐겨찾기 상태 변경
	 */
	public void updateFavorite(FavoriteVO favoriteVO);
	
	/**
	 * 즐겨찾기로 표시된 목록 가져오기
	 */
	public List<FavoriteVO> getFavoriteList(String id);
	
}
