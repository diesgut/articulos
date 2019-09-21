package com.diesgut.articulos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diesgut.articulos.exception.ResourceNotFoundException;
import com.diesgut.articulos.model.Article;
import com.diesgut.articulos.repository.IArticleRepository;
import com.diesgut.articulos.repository.IArticleRepositoryPageable;
import com.diesgut.articulos.service.IArticleService;

@Service

public class ArticleServiceImp implements IArticleService {

	@Autowired
	private IArticleRepository articleRepository;

	@Autowired
	private IArticleRepositoryPageable articleRepositoryPageable;

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Page<Article> getAllArticlesPageable(Pageable pageable) {
		// list articles=new ();
		// lista.iterator().forEachRemaining(articles::add);
		return articleRepositoryPageable.findAll(pageable);
	}

	@Override
	public Article createArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
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
		if (!article.isPresent()) {
			throw new ResourceNotFoundException("El articulo con el ID:" + id + " no se encuentra");
		}
		return article.get();
	}

}
