package com.diesgut.articulos.service;

import java.util.List;

import com.diesgut.articulos.model.Article;

public interface IArticleService {

	List<Article> getAllArticles();

	Article createArticle(Article article);

	Article updateArticle(Long idArticle, Article article);

	void deleteArticle(Article article);

	Article findById(Long id);

}
