package com.easystar.pipeline;

import com.easystar.SpiderApplication;
import com.easystar.dao.ArticleDao;
import com.easystar.entity.Article;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;

public class ArticlePipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title"); if(!"".equals(title))
        if(!"".equals(title)) {
            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
            try {
                ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);

                Article article = new Article();
                article.setCreateDate(new Date());
                article.setContent(resultItems.get("content"));
                article.setSrcPath(resultItems.getRequest().getUrl());
                article.setTitle(resultItems.get("title"));
                articleDao.insertArticle(article);
                sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
            } finally {
                sqlSession.close();
            }
        }
    }
}
