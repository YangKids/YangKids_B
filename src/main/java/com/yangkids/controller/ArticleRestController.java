package com.yangkids.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangkids.model.dto.Article;
import com.yangkids.model.dto.SearchCondition;
import com.yangkids.model.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-article")
@Api(tags = "Article 컨트롤러")
//@CrossOrigin("*")
public class ArticleRestController {

	@Autowired
	private ArticleService articleService;

	@ApiOperation(value = "게시글 목록")
	@GetMapping("/board/{boardId}")
	public ResponseEntity<?> board(@PathVariable int boardId){
		List<Article> list = articleService.getBoardList(boardId);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 상세")
	@GetMapping("/detail/{articleId}")
	public ResponseEntity<Article> detail(@PathVariable int articleId) {
		Article article = articleService.readArticle(articleId);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 등록")
	@PostMapping("/write")
	public ResponseEntity<Article> write(Article article) {
		articleService.writeArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.CREATED);
	}

	@ApiOperation(value = "게시글 삭제")
	@DeleteMapping("/delete/{articleId}")
	public ResponseEntity<Void> delete(@PathVariable int articleId) {
		articleService.removeArticle(articleId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 수정")
	@PutMapping("/update")
	public ResponseEntity<Void> update(@RequestBody Article article) {
		articleService.modifyArticle(article);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 검색", notes = "key: 검색 조건, word: 검색어")
	@GetMapping("/search")
	public ResponseEntity<?> search(SearchCondition condition) {
		List<Article> list = articleService.search(condition);

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}

}
