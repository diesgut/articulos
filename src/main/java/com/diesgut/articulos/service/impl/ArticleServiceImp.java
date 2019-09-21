package com.diesgut.articulos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diesgut.articulos.model.Article;
import com.diesgut.articulos.repository.IArticleRepository;
import com.diesgut.articulos.service.IArticleService;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImp implements IArticleService {

	@Autowired
	private IArticleRepository articleRepository;

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	@Transactional
	public Article createArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	@Transactional
	public Article updateArticle(Long idArticle, Article article) {
		Article articleDB = findById(idArticle);
		articleDB.setCategory(article.getCategory());
		articleDB.setTitle(article.getTitle());
		articleDB.setDescription(article.getDescription());
		articleDB.setContent(article.getContent());
		return articleRepository.save(articleDB);
	}

	@Override
	public void deleteArticle(Article article) {
		articleRepository.delete(article);
	}

	@Override
	public Article findById(Long id) {
		Optional<Article> article = articleRepository.findById(id);
		return article.get();
	}

}
