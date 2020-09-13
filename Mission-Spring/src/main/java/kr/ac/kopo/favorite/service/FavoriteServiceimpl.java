package kr.ac.kopo.favorite.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.favorite.dao.FavoriteDAO;
import kr.ac.kopo.favorite.vo.FavoriteVO;

@Service
public class FavoriteServiceimpl implements FavoriteService {

	@Autowired
	private FavoriteDAO favoriteDAO;
	
	
	@Override
	public List<FavoriteVO> getRecentList(String id) {
		List<FavoriteVO> recentList = favoriteDAO.getRecentList(id);
		return recentList;
	}


	@Override
	public void addFavorite(FavoriteVO favoriteVO) {
		favoriteDAO.addFavorite(favoriteVO);
	}


	@Override
	public void updateFavorite(FavoriteVO favoriteVO) {
		favoriteDAO.updateFavorite(favoriteVO);
	}


	@Override
	public List<FavoriteVO> getFavoriteList(String id) {
		List<FavoriteVO> favoriteList = favoriteDAO.getFavoriteList(id);
		return favoriteList;
	}




	
}
