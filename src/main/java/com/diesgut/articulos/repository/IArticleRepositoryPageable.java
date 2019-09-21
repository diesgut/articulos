package com.diesgut.articulos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.diesgut.articulos.model.Article;

@Repository
public interface IArticleRepositoryPageable extends PagingAndSortingRepository<Article, Long> {

	Page<Article> findAll(Pageable pageable);

}
