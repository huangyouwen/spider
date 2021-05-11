package com.easystar.dao;

import com.easystar.entity.Article;

public interface ArticleDao {

    void insertArticle(Article article);
    Article getArticle(long id);

}
