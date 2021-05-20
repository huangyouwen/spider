package com.easystar.dao;

import com.easystar.entity.Article;

import java.util.List;

public interface ArticleDao {

    void insertArticle(Article article);
    Article getArticle(long id);
    Article getArticleByTitle(String title);

    List<Article> findArticlesByLastMonth();

    List<Article> query(int limit);
}
