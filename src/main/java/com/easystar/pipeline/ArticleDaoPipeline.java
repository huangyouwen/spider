package com.easystar.pipeline;

import com.easystar.SpiderApplication;
import com.easystar.dao.ArticleDao;
import com.easystar.entity.Article;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


public class ArticleDaoPipeline implements PageModelPipeline<Article> {

    @Override
    public void process(Article article, Task task) {
        SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
        try {
            ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
            articleDao.insertArticle(new Article());
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
        } finally {
            sqlSession.close();
        }

    }
}
