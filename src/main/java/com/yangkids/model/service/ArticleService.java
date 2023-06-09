package com.yangkids.model.service;

import java.util.List;

import com.yangkids.model.dto.Article;
import com.yangkids.model.dto.SearchCondition;

//사용자 친화적으로
public interface ArticleService {
	
	//게시판 별 게시글 목록
	public List<Article> getBoardList(int boardId);
	
	//게시글 상세조회 (클릭시 읽는거)
	public Article readArticle(int articleId); 
	
	//게시글 작성
	public int writeArticle(Article article);
	
	//게시글 삭제 
	public int removeArticle(int articleid);
	
	//게시글 수정
	public int modifyArticle(Article article);
	
	//검색 버튼을 눌렀을 때 처리하기 위한 메서드
	public List<Article> search(SearchCondition condition);
	
}
