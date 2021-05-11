package com.easystar;

import com.easystar.db.MyBatisUtils;
import com.easystar.pipeline.ArticleDaoPipeline;
import com.easystar.pipeline.ArticlePipeline;
import org.apache.ibatis.session.SqlSessionFactory;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;


public class SpiderApplication {

    public static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    }

    public static void main(String[] args) {
        Spider.create(new BaiduTopProcessor()).addPipeline(new ArticlePipeline()).addUrl("http://top.baidu.com/").run();
    }
}