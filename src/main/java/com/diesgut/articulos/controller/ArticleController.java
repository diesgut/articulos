package com.diesgut.articulos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diesgut.articulos.model.Article;
import com.diesgut.articulos.service.impl.ArticleServiceImp;

@Controller
@RequestMapping
public class ArticleController {

	// view templates
	protected static final String ARTICLE_VIEW = "articles/showArticle"; // view template for single article
	protected static final String ARTICLE_ADD_FORM_VIEW = "articles/newArticle"; // form for new article
	protected static final String ARTICLE_EDIT_FORM_VIEW = "articles/editArticle"; // form for editing an article

	protected static final String ARTICLE_PAGE_VIEW = "articles/allArticles"; // list with pagination

	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private ArticleServiceImp articleService;

	@GetMapping("/")
	public ModelAndView index() {
		List<Article> articles = articleService.getAllArticles();
		ModelAndView model = new ModelAndView(INDEX_VIEW);
		model.addObject("articles", articles);
		return model;
	}

}
