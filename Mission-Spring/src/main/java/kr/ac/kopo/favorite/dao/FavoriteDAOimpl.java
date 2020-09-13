package kr.ac.kopo.favorite.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.favorite.vo.FavoriteVO;

@Repository
public class FavoriteDAOimpl implements FavoriteDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<FavoriteVO> getRecentList(String id) {
		List<FavoriteVO> recentList = sqlSession.selectList("favorite.dao.FavoriteDAO.getRecentList", id);
		
		return recentList;
	}

	@Override
	public void addFavorite(FavoriteVO favoriteVO) {
		sqlSession.insert("favorite.dao.FavoriteDAO.addFavorite", favoriteVO);
	}

	@Override
	public void updateFavorite(FavoriteVO favoriteVO) {
		sqlSession.update("favorite.dao.FavoriteDAO.updateFavorite", favoriteVO);	
	}

	@Override
	public List<FavoriteVO> getFavoriteList(String id) {
		List<FavoriteVO> favoriteList = sqlSession.selectList("favorite.dao.FavoriteDAO.getFavoriteList", id);
		return favoriteList;
	}

	
}
