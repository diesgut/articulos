package com.diesgut.articulos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/articles")
	public ModelAndView getAllArticles() {
		List<Article> articles = articleService.getAllArticles();
		ModelAndView model = new ModelAndView(ARTICLE_PAGE_VIEW);
		model.addObject("articles", articles);
		return model;
	}

	@GetMapping("/article/new")
	public String newArticle(Model model) {
		model.addAttribute("article", new Article());
		return ARTICLE_ADD_FORM_VIEW;
	}

	@PostMapping("/article/create")
	public String createArticle(@Valid Article article, BindingResult result, Model model, RedirectAttributes attr) {
		model.addAttribute("article", new Article());
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.valdation.BindingResult", result);
			attr.addFlashAttribute("article", article);
			return "redirect:/article/new";
		}
		Article newArticle = articleService.createArticle(article);
		model.addAttribute("article", newArticle);
		return "redirect:/article/" + newArticle.getArticleId();
	}

	@GetMapping("/article/{id}")
	public String getArticleById(@PathVariable(value = "id") Long articleId, Model model) {
		model.addAttribute("article", articleService.findById(articleId));
		return ARTICLE_VIEW;
	}

	@GetMapping("/article/{id}/edit")
	public String editArticle(@PathVariable(value = "id") Long articleId, Model model) {
		model.addAttribute("article", articleService.findById(articleId));
		return ARTICLE_ADD_FORM_VIEW;
	}

}
