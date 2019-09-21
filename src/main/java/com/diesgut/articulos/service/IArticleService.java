package com.diesgut.articulos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diesgut.articulos.model.Article;

public interface IArticleService {

	List<Article> getAllArticles();
	
	 Page<Article> getAllArticlesPageable(Pageable pageable);

	Article createArticle(Article article);

	Article updateArticle(Long idArticle, Article article);

	void deleteArticle(Article article);

	Article findById(Long id);

}
