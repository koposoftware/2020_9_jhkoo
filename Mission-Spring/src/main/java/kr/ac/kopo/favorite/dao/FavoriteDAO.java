package kr.ac.kopo.favorite.dao;

import java.util.List;

import kr.ac.kopo.favorite.vo.FavoriteVO;

public interface FavoriteDAO {

	/**
	 * 최근 거래 계좌 리스트 가져오기
	 */
	public List<FavoriteVO> getRecentList(String id);
	
	/**
	 * 즐겨찾기 목록에 추가
	 */
	public void addFavorite(FavoriteVO favoriteVO);
	
	/**
	 * 즐겨찾기 상태 변경. 
	 */
	public void updateFavorite(FavoriteVO favoriteVO);
	
	/**
	 *  즐겨찾는 계좌 리스트 가져오기
	 */
	public List<FavoriteVO> getFavoriteList(String id);
	

}
