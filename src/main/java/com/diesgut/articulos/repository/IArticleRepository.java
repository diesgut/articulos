package com.diesgut.articulos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diesgut.articulos.model.Article;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {

}
