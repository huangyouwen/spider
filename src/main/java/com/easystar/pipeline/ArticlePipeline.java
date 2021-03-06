package com.easystar.pipeline;

import com.easystar.SpiderApplication;
import com.easystar.dao.ArticleDao;
import com.easystar.entity.Article;
import com.easystar.utils.HtmlUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ArticlePipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        if(StringUtils.isNotEmpty(title)) {
            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
            try {
                ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
                String fileName  = System.currentTimeMillis() + ".html";
                Article article = new Article();
                article.setCreateDate(new Date());
                article.setContent(resultItems.get("content"));
                article.setSrcPath(resultItems.getRequest().getUrl());
                article.setTitle(resultItems.get("title"));
                article.setIcon(resultItems.get("icon"));
                article.setFileName(fileName);
                Article temp = articleDao.getArticleByTitle(title);
                if(temp == null) {
                    SpiderApplication.isModifyIndex = true;
                    Map<String,Object> map = new HashMap<>();
                    Template template = SpiderApplication.configuration.getTemplate("article.ftl");
                    map.put("article",article);
                    map.put("subTitle", HtmlUtil.delHTMLTag(article.getContent().replace("\\n","")).substring(0,20));
//                    File file = new File("C://Program Files//Spider//"+fileName);
                    File file = new File("D://Spider//"+fileName);
                    FileWriter fileWriter = new FileWriter(file);
                    template.process(map,fileWriter);
                    fileWriter.close();
                    if(SpiderApplication.isUploadFtp)
                        SpiderApplication.ftpCli.uploadFileToDailyDir("htdocs//article",fileName, new FileInputStream(file));
                    file.delete();
                    articleDao.insertArticle(article);
                    sqlSession.commit();// ?????????????????????????????????????????????????????????
                    String str = SpiderApplication.httpUtil.pushPost("http://www.jinma-online.cn/article/"+fileName,"www.jinma-online.cn","lChtly1kuRQ9ATD8");
                    System.out.println(str);
                }
            }catch (TemplateException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                sqlSession.close();
            }
        }
    }
}
